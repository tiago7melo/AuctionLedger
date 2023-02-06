package kad.kademlia.grpc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;

import com.google.protobuf.ByteString;

import blockchain.Block;
import blockchain.Transaction;
import blockchain.utils.Utils;
import blockchain.utils.miningInfo;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import kad.generated.KademliaGrpc;
import kad.generated.NodeInfo;
import kad.generated.Ping;
import kad.generated.RequestStatus;
import kad.generated.SecurityChallenge;
import kad.generated.StorableValue;
import kad.generated.Value;
import kad.generated.foundValue;
import kad.exceptions.InvalidPortException;
import kad.generated.KadID;
import kad.generated.Message;
import kad.kademlia.ID;
import kad.kademlia.network.Challenge;
import kad.kademlia.network.Host;
import kad.kademlia.network.Node;
import kad.kademlia.network.RoutingTable;
import ledger.Settings;

public class KademliaClient {
	private final KademliaGrpc.KademliaStub stub;
	private final KademliaGrpc.KademliaBlockingStub blockingStub;
	
	public ManagedChannel channel;
	private Node destinationNode;
	Node localNode;
	
	private boolean storeResult;
	private boolean broadcastResult;
	private Block findResult;

	private boolean joined;
	
	private Utils utils;
	
	//ExecutorService executor;
	
	public KademliaClient(Node local, Node destination) {
		this.joined = true;
		if(local.getID() == null) {
			System.out.println("This node has not joined the network.");
			this.joined = false;
		}
		
		this.destinationNode = destination;
		this.localNode = local;
		this.utils = new Utils();
		
		Host conn = this.destinationNode.getHost();
		
	    ManagedChannel channel = ManagedChannelBuilder.forAddress(conn.getIP().getHostAddress(),
	    														  conn.getPort()).usePlaintext().build();
	    
	    this.channel = channel;
	    System.out.println("Client created for communicating with " + destination.getHost().getPort());
		this.stub = KademliaGrpc.newStub(channel);
		this.blockingStub = KademliaGrpc.newBlockingStub(channel);
		
	}
	
	public void shutdown() {
		try {
			this.channel.shutdownNow().awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Node join() {
		byte[] emptyID = new byte[Settings.ID_BYTES];
		Arrays.fill(emptyID, (byte) 0);  // ID with all 0s, placeholder until given ID by bootstrap
		
		NodeInfo.Builder nodeInfoMsg = NodeInfo.newBuilder();
		nodeInfoMsg.setID(ByteString.copyFrom(emptyID));
		nodeInfoMsg.setIP(localNode.getHost().getIP().getHostAddress());
		nodeInfoMsg.setPort(localNode.getHost().getPort());
		
		System.out.println("Called join RPC");
	
		Iterator<NodeInfo> node_infos;
		node_infos = blockingStub.join(nodeInfoMsg.build());
		
		while(node_infos.hasNext()) {
			NodeInfo info = node_infos.next();
			
			try {
				

				ID receivedID = new ID(info.getID().toByteArray());
				Host receivedHost = new Host(info.getPort(),info.getIP());
				Node receivedNode = new Node(receivedID,receivedHost);
				
				if(joined) {
					
					System.out.println(destinationNode.getHost().getPort() + " [join] neighbour " 
							+ new BigInteger(info.getID().toByteArray()).intValue());
					localNode.getRoutingTable().update(receivedNode);
					
				} else {
					System.out.println(destinationNode.getHost().getPort() + " [join] attributed ID " 
							+ new BigInteger(info.getID().toByteArray()).intValue());
					
					localNode.startTable(new RoutingTable(receivedNode.getID()));
					localNode.setID(receivedNode.getID());
					joined = true;
				}
				
			} 
			catch (InvalidPortException e) { 
				e.printStackTrace(); 
			}
			
		}
		
		System.out.println("Join RPC finished");
		
		return localNode;
	}
	
	public boolean ping() {
		Ping.Builder ping = Ping.newBuilder();
		
		byte[] nodeId = this.localNode.getID().getBytes();
		ping.setId(ByteString.copyFrom(nodeId));
		ping.setIP(this.localNode.getHost().getIP().getHostAddress());
		ping.setPort(this.localNode.getHost().getPort());
		
		Ping pingResponse = blockingStub.ping(ping.build());
		
		boolean pingResult = false; 
		
		try {
			ID receivedID = new ID(pingResponse.getId().toByteArray());
			Host receivedHost = new Host(pingResponse.getPort(),pingResponse.getIP());
			Node receivedNode = new Node(receivedID,receivedHost);
			this.localNode.getRoutingTable().update(receivedNode);
			pingResult = true;
			
		} catch (InvalidPortException e) {
			pingResult = false;
			e.printStackTrace();
		}
		
		
		return pingResult;
	}
	
	public Node findNode(ID targetId) throws InvalidPortException {
		KadID.Builder lookupIdMsg = KadID.newBuilder();
		lookupIdMsg.setId(ByteString.copyFrom(targetId.getBytes()));
		
		NodeInfo result = blockingStub.findNode(lookupIdMsg.build());
		
		Host receivedConnection = new Host(result.getPort(),result.getIP());
		ID receivedId = new ID(result.getID().toByteArray());
		Node receivedNode = new Node(receivedId,receivedConnection);
		
		System.out.println("[find] for target " + targetId.getInt() + ": found node with ID " + receivedId.getInt() 
							+ " and address " + receivedConnection.getIP().getHostAddress() + 
							" : " + receivedConnection.getPort());
		
		this.localNode.getRoutingTable().update(receivedNode);
		
		
		return receivedNode;
		
	}
	
	public Block findValue(String hash) {
		// get kademlia ID key of block hash
		ID key = utils.hashToKadKey(hash);
		
		Value.Builder kadKeyMsg = Value.newBuilder();
		ByteString keyId = ByteString.copyFrom(key.getBytes());
		kadKeyMsg.setKey(keyId);
		kadKeyMsg.setHash(hash);
		
		
		foundValue result = blockingStub.findValue(kadKeyMsg.build());
		
		if(result.getIsFound()) {
			try {
				return new Block(result.getValue());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else {
			
			try {
				ID foundID = new ID(result.getNodeID().toByteArray());
				Host foundHost = new Host(result.getPort(),result.getNodeAdress());
				Node foundNode = new Node (foundID, foundHost);
				this.localNode.getRoutingTable().update(foundNode);
			} catch (InvalidPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		return new Block (null,"notFound","notFound",-1, new Date());
	}
	
	public boolean store(Block block) {
		String blockString = block.toString();
		ID key = utils.hashToKadKey(block.getHash());
		
		StorableValue.Builder storeMsg = StorableValue.newBuilder();
		storeMsg.setKey(ByteString.copyFrom(key.getBytes()));
		storeMsg.setValue(block.toString());
		
		storeResult = false;
		
		stub.store(storeMsg.build(), new StreamObserver<RequestStatus>() {

			@Override
			public void onNext(RequestStatus value) {
				storeResult = value.getStatus();
				
			}

			@Override
			public void onError(Throwable t) {
				storeResult = false;
			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
			}
		});
		
		return storeResult;
	}
	
	public boolean broadcastTransaction(Transaction transaction, Node sender, int broadcastMsgId) {
		broadcastResult = false;
		
		Message.Builder msg = Message.newBuilder();
		msg.setSenderID(sender.getID().getInt().intValue());
		msg.setSenderAddress(sender.getHost().getIP().getHostAddress());
		msg.setSenderPort(sender.getHost().getPort());
		msg.setMessageType(2);
		msg.setMessageID(broadcastMsgId);
		msg.setTimestamp(new Date().getTime());
		msg.setMsg(transaction.toString());
		
		RequestStatus broadcastResult = blockingStub.broadcastMessage(msg.build());
		boolean result = broadcastResult.getStatus();
		
		return result;
	}
	
	/*public boolean broadcastBlock(Block block, ID senderID, int broadcastMsgId) {
		broadcastResult = false;
		
		Message.Builder msg = Message.newBuilder();
		msg.setSenderID(ByteString.copyFrom(senderID.getBytes()));
		msg.setMessageType(4);
		msg.setMessageID(broadcastMsgId);
		msg.setTimestamp(new Date().getTime());
		msg.setMsg(block.toString());
		
		stub.broadcastMessage(msg.build(),new StreamObserver<RequestStatus>() {
			@Override
			public void onNext(RequestStatus value) {
				broadcastResult = value.getStatus();
			}

			@Override
			public void onError(Throwable t) {
				broadcastResult = false;
			}

			@Override
			public void onCompleted() {
				// TODO Auto-generated method stub
			}
		});
		
		return broadcastResult;
	}*/
	
	public boolean broadcastMineInfo(miningInfo mineInfo, Node sender, int broadcastMsgId) {
		broadcastResult = false;
		
		Message.Builder msg = Message.newBuilder();
		msg.setSenderID(sender.getID().getInt().intValue());
		msg.setSenderAddress(sender.getHost().getIP().getHostAddress());
		msg.setSenderPort(sender.getHost().getPort());
		msg.setMessageType(6);
		msg.setMessageID(broadcastMsgId);
		msg.setTimestamp(mineInfo.timestamp.getTime());
		msg.setMsg(mineInfo.toString());
		
		RequestStatus broadcastResult = blockingStub.broadcastMessage(msg.build());
		boolean result = broadcastResult.getStatus();
		
		return result;
	}
	
	
	public List<Block> getBlockChain() {
		Ping.Builder ping = Ping.newBuilder();
		
		List<Block> blockchainResult = new ArrayList<>();
		
		byte[] nodeId = this.localNode.getID().getBytes();
		ping.setId(ByteString.copyFrom(nodeId));
		ping.setIP(this.localNode.getHost().getIP().getHostAddress());
		ping.setPort(this.localNode.getHost().getPort());
		
		
		Iterator<foundValue> blockchainResponse = blockingStub.getBlockChain(ping.build());
		
		while(blockchainResponse.hasNext()) {
			String blockString = blockchainResponse.next().getValue();
			try {
				blockchainResult.add(new Block(blockString));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return blockchainResult;
	}
	
	public List<Transaction> getTransactionPool() {
		Ping.Builder ping = Ping.newBuilder();
		
		List<Transaction> poolResult = new ArrayList<>();
		
		byte[] nodeId = this.localNode.getID().getBytes();
		ping.setId(ByteString.copyFrom(nodeId));
		ping.setIP(this.localNode.getHost().getIP().getHostAddress());
		ping.setPort(this.localNode.getHost().getPort());
		
		
		Iterator<foundValue> poolResponse = blockingStub.getTransactionPool(ping.build());
		
		while(poolResponse.hasNext()) {
			foundValue response = poolResponse.next();
			if(response.getIsFound() == false) {
				return poolResult;
			}
			
			String transactionString = response.getValue();
			try {
				poolResult.add(new Transaction(transactionString));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return poolResult;
	}
	
	public Challenge joinRequest() {
		byte[] emptyID = new byte[Settings.ID_BYTES];
		Arrays.fill(emptyID, (byte) 0);  // ID with all 0s, placeholder until given ID by bootstrap
		
		NodeInfo.Builder nodeInfoMsg = NodeInfo.newBuilder();
		nodeInfoMsg.setID(ByteString.copyFrom(emptyID));
		nodeInfoMsg.setIP(localNode.getHost().getIP().getHostAddress());
		nodeInfoMsg.setPort(localNode.getHost().getPort());
		
		SecurityChallenge response = blockingStub.joinRequest(nodeInfoMsg.build());
		
		Challenge challenge = new Challenge(response.getChallengedID(),
											localNode.getHost().getIP().getHostAddress(),
											localNode.getHost().getPort(),
											response.getChallenge());
		
		return challenge;
		
	}
	
	public Node joinValidate(Challenge solved_challenge) {
		SecurityChallenge.Builder challengeMsg = SecurityChallenge.newBuilder();
		challengeMsg.setChallengedID(solved_challenge.getChallengeID());
		challengeMsg.setChallenge(solved_challenge.getChallenge());
		challengeMsg.setSolution(solved_challenge.getSolution());
		challengeMsg.setNodeAdress(solved_challenge.getAddress());
		challengeMsg.setPort(solved_challenge.getPort());
		
		System.out.println("Called join RPC");
		
		Iterator<NodeInfo> node_infos;
		node_infos = blockingStub.joinValidate(challengeMsg.build());
		
		while(node_infos.hasNext()) {
			NodeInfo info = node_infos.next();
			
			try {
				

				ID receivedID = new ID(info.getID().toByteArray());
				Host receivedHost = new Host(info.getPort(),info.getIP());
				Node receivedNode = new Node(receivedID,receivedHost);
				
				if(joined) {
					
					System.out.println(destinationNode.getHost().getPort() + " [join] neighbour " 
							+ new BigInteger(info.getID().toByteArray()).intValue());
					localNode.getRoutingTable().update(receivedNode);
					
				} else {
					System.out.println(destinationNode.getHost().getPort() + " [join] attributed ID " 
							+ new BigInteger(info.getID().toByteArray()).intValue());
					
					localNode.startTable(new RoutingTable(receivedNode.getID()));
					localNode.setID(receivedNode.getID());
					joined = true;
				}
				
			} 
			catch (InvalidPortException e) { 
				e.printStackTrace(); 
			}
			
		}
		
		System.out.println("Join RPC finished");
		
		return localNode;
		
	}


}
