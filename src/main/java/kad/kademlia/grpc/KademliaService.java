package kad.kademlia.grpc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.simple.parser.ParseException;

import com.google.protobuf.ByteString;

import blockchain.Block;
import blockchain.BlockchainHandler;
import blockchain.Transaction;
import blockchain.utils.Utils;
import blockchain.utils.miningInfo;
import io.grpc.stub.StreamObserver;
import kad.exceptions.InvalidPortException;
import kad.generated.KadID;
import kad.generated.KademliaGrpc.KademliaImplBase;
import kad.generated.Message;
import kad.generated.NodeInfo;
import kad.generated.Ping;
import kad.generated.RequestStatus;
import kad.generated.SecurityChallenge;
import kad.generated.StorableValue;
import kad.generated.Value;
import kad.generated.foundValue;
import kad.kademlia.ID;
import kad.kademlia.Storage;
import kad.kademlia.StorageEntry;
import kad.kademlia.network.Challenge;
import kad.kademlia.network.Host;
import kad.kademlia.network.IDFactory;
import kad.kademlia.network.Node;
import ledger.Settings;

class KademliaService extends KademliaImplBase {
	
	Node homeNode;
	Storage storage;
	KademliaOperation operation;
	BlockchainHandler blockchainNode;
	
	Utils utils;
	
	// prevent broadcast duplicates
	List<String> seenBroadcastBlocks = Collections.synchronizedList(new ArrayList());
	List<String> seenBroadcastTransactions = Collections.synchronizedList(new ArrayList());
	List<String> savedAuctions = new ArrayList<>();
	
	boolean receivingBroadcast;
	
	// only used in bootstrap nodes
	IDFactory idFactory = new IDFactory();
	private int securityChallengeID = 0; 
	private HashMap<Integer, Challenge> securityChallenges = new HashMap<>();
	
	public KademliaService(Node homeNode, Storage storage, KademliaOperation operation, BlockchainHandler blockchainNode) {
		this.homeNode = homeNode;
		this.storage = storage;
		this.operation = operation;
		this.blockchainNode = blockchainNode;
		
		this.receivingBroadcast = false;
		
		
		this.utils = new Utils();
	}
	
	
	public void join(NodeInfo request, StreamObserver<NodeInfo> responseObserver) {
		System.out.println("Join request from " + request.getPort());
		//first send info about the node's generated ID
		ID newID = idFactory.generate();
		// needs to be done before we update the routingTable with the newNode, or closestNodes is going to return newNode
		List<Node> closestNodes = homeNode.getRoutingTable().closestNodes(newID, Settings.JOIN_CLOSEST_NODES_ALPHA);
		closestNodes.add(homeNode);
		
		try {
			
			Node newNode = new Node(newID, new Host(request.getPort(),request.getIP()));
			homeNode.getRoutingTable().update(newNode);
		} catch (InvalidPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		NodeInfo.Builder nodeInfoMsg = NodeInfo.newBuilder();
		nodeInfoMsg.setID(ByteString.copyFrom(newID.getBytes()));
		nodeInfoMsg.setIP(request.getIP());
		nodeInfoMsg.setPort(request.getPort());
		
		responseObserver.onNext(nodeInfoMsg.build());
		
		
		// then the closest kademlia nodes
		
		for(Node node : closestNodes) {
			NodeInfo.Builder nodeMsg = NodeInfo.newBuilder();
			nodeMsg.setID(ByteString.copyFrom(node.getID().getBytes()));
			nodeMsg.setIP(node.getHost().getIP().toString());
			nodeMsg.setPort(node.getHost().getPort());
			
			responseObserver.onNext(nodeMsg.build());
			
		}
		
		responseObserver.onCompleted();
	}
	
	
	
	public void ping(Ping request, StreamObserver<Ping> responseObserver) {
		Ping.Builder pingMsg = Ping.newBuilder();
		pingMsg.setId(ByteString.copyFrom(homeNode.getID().getBytes()));
		pingMsg.setIP(homeNode.getHost().getIP().toString());
		pingMsg.setPort(homeNode.getHost().getPort());
		
		
		try {
			ID receivedID = new ID(request.getId().toByteArray());
			System.out.println("[ping] from " + receivedID.getInt().intValue());
			Host receivedHost = new Host(request.getPort(),request.getIP());
			Node receivedNode = new Node(receivedID,receivedHost);
			this.homeNode.getRoutingTable().update(receivedNode);
		} catch (InvalidPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		responseObserver.onNext(pingMsg.build());
		responseObserver.onCompleted();
	}
	
	 
		// function structure needs to be Request, Response
		// only that, no other arguments
		// which mans we need to pass homeNode and storage
	 public void findNode(KadID request, StreamObserver<NodeInfo> responseObserver) {
		//give the closest ID you can find and let the client handle it
	 	ID targetID = new ID(request.getId().toByteArray());
	 	List<Node> foundNodes = homeNode.getRoutingTable().closestNodes(targetID, 1);
	 	Node closestNode = foundNodes.get(0);
	 	
	 	NodeInfo.Builder nodeInfoMsg = NodeInfo.newBuilder();
	 	nodeInfoMsg.setID(ByteString.copyFrom(closestNode.getID().getBytes()));
	 	nodeInfoMsg.setIP(closestNode.getHost().getIP().toString());
	 	nodeInfoMsg.setPort(closestNode.getHost().getPort());
	 	
	 	responseObserver.onNext(nodeInfoMsg.build());
	 	responseObserver.onCompleted();
	 }
	 
	 public void findValue(Value request, StreamObserver<foundValue> responseObserver) {
		 byte[] key  = request.getKey().toByteArray();
		 ID keyID = new ID(key);
		 String hash = request.getHash();
		 
		 foundValue.Builder foundValueMsg = foundValue.newBuilder();
		 
		if(this.storage.contains(hash)) {
			Block foundBlock = this.storage.get(hash).getBlock();
			
			foundValueMsg.setIsFound(true);
			foundValueMsg.setKey(ByteString.copyFrom(key)); 
			foundValueMsg.setValue(foundBlock.toString()); 
			
			// below are not necessary in this case
			foundValueMsg.setNodeID(ByteString.copyFrom(homeNode.getID().getBytes()));
			foundValueMsg.setNodeAdress(homeNode.getHost().getIP().toString());
			foundValueMsg.setPort(homeNode.getHost().getPort());
			
			responseObserver.onNext(foundValueMsg.build());
		}
		else {
			List<Node> closestNodes = this.homeNode.getRoutingTable().closestNodes(keyID, 1);
			Node closestNode = closestNodes.get(0);
			
			foundValueMsg.setIsFound(false);
			foundValueMsg.setKey(ByteString.copyFrom(key)); //filler, not checked in this case.
			foundValueMsg.setValue(""); // filler, not checked in this case
			
			foundValueMsg.setNodeID(ByteString.copyFrom(closestNode.getID().getBytes()));
			foundValueMsg.setNodeAdress(closestNode.getHost().getIP().toString());
			foundValueMsg.setPort(closestNode.getHost().getPort());
			
			responseObserver.onNext(foundValueMsg.build());
		}
		 
		 
		 responseObserver.onCompleted();
	 }
	 
	 public void store(StorableValue request, StreamObserver<RequestStatus> responseObserver) {
		 // store in our system logic
		 // parse content information, create structure to store
		 // and then store
		 
		 boolean status = false;
		 try {
			String blockString = request.getValue();
			Block block = new Block(blockString);
			ID key = this.utils.hashToKadKey(block.getHash());
			
			StorageEntry storage_entry = new StorageEntry(homeNode.getID(),key,block);
			storage.put(storage_entry);
			
			status = true;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			status = false;
			e.printStackTrace();
		}
		 	
		 RequestStatus.Builder statusMsg = RequestStatus.newBuilder();
		 statusMsg.setStatus(status);
		 responseObserver.onNext(statusMsg.build());
		 responseObserver.onCompleted();
	 }
	 
	 public void broadcastMessage(Message request, StreamObserver<RequestStatus> responseObserver) {
		 int messageType = request.getMessageType();
		 int messageID = request.getMessageID();
		 int senderID = request.getSenderID();
		 String senderAddress = request.getSenderAddress();
		 int senderPort = request.getSenderPort();
		 
		 RequestStatus.Builder statusMsg = RequestStatus.newBuilder();
		 
		 //is this a broadcast that localNode sent?
		 if(new ID(senderID).getInt().intValue() == homeNode.getID().getInt().intValue()) {
			 statusMsg.setStatus(false);
			 responseObserver.onNext(statusMsg.build());
			 responseObserver.onCompleted();
			 return;
		 }
		 
		 Node senderNode = null;
		 //update routing table
		 try {
			senderNode = new Node(new ID(senderID), new Host(senderPort,senderAddress));
			this.homeNode.getRoutingTable().update(senderNode);
		} catch (InvalidPortException e1) {
			e1.printStackTrace();
		}
		 
		 // only used when message type suits the structure
		 // in that case, these get replaced
		 Transaction transaction = new Transaction(-1 ,null, 1, 1);
		 miningInfo mineInfo = new miningInfo(-1, new Date());
		 
		 boolean forwardFlag = false; // are we forwarding this message?
		 if(messageType == 2) {
			synchronized(seenBroadcastTransactions) {
				 if (!seenBroadcastTransactions.contains(messageType + ":" + senderID + " : " + messageID)) {
					 seenBroadcastTransactions.add(messageType + ":" + senderID + " : " + messageID);
					 System.out.println("Receiving new transaction from " + senderID +
							 			" with message id " + messageID + ":" + messageType);
					 System.out.println(request.getMsg());
					 forwardFlag = true;
					 
					 try {
						transaction = new Transaction(request.getMsg());
						
						// behaviour on transaction received
						blockchainNode.onTransactionReceived(transaction);
						
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
					 statusMsg.setStatus(true);
				 }
				 else {
					 statusMsg.setStatus(false);
				 }
			 }
		 }
		 else if(messageType == 6) {
			 synchronized(seenBroadcastBlocks) {
				 try {
					mineInfo = new miningInfo(request.getMsg());
				 } catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 if (!seenBroadcastBlocks.contains(messageID + ":" + mineInfo.nounce)) {
					 seenBroadcastBlocks.add(messageID + ":" + mineInfo.nounce);
					 System.out.println("Receiving new block from " + senderID +
					 			" with message id " + messageID);
			
							
					 	// behaviour on block received
					 	blockchainNode.onBlockReceived(mineInfo.nounce, mineInfo.timestamp);
							 
			
					 
					 statusMsg.setStatus(true);
				 }
				 else {
					 statusMsg.setStatus(false);
				 }
			 }
		 }

		 
		 
		 else {
			 responseObserver.onError(null);
		 }
		 
		 responseObserver.onNext(statusMsg.build());
		 responseObserver.onCompleted();
		 
		 // only forward if its a new message
		 if(!forwardFlag) {
			 //this.receivingBroadcast = false;
			 return;
		 }
		 
		 //forward broadcast
		 List<Node> allNodes = this.homeNode.getRoutingTable().allNodes();
		 for(Node node : allNodes) {
			 KademliaClient client = new KademliaClient(this.homeNode,node);

			 if(messageType == 2) {
				 boolean status = client.broadcastTransaction(transaction, senderNode, messageID);
			 }
			 else if(messageType == 6) {
				 boolean status = client.broadcastMineInfo(mineInfo, senderNode, messageID);
			 }
			 client.channel.shutdownNow();
		 }
		 
		 System.out.println("Broadcast message processed");
	 }
	 
	 public void message(Message request, StreamObserver<Message> responseObserver) {
		 int messageType = request.getMessageType();
		 int messageID = request.getMessageID();
		 byte[] senderID = request.toByteArray();
		 
		 if(messageType == 7) {
			 // TODO: wait for auction system to be more developed
			 // behaviour onBidReceived
			 
			 // check if we have any auction open with that auction ID
			 
			 // onBidReceived behaviour
		 }
		 
		 
	 }
	 
	 
	 public void getBlockChain(Ping request, StreamObserver<foundValue> responseObserver) {
		 List<Block> blockchain = this.blockchainNode.getBlockchain();
		 
		 for(Block block : blockchain) {
			 foundValue.Builder valueMsg = foundValue.newBuilder();
			 
			 valueMsg.getIsFound();
			 ID key = utils.hashToKadKey(block.getHash());
			 valueMsg.setKey(ByteString.copyFrom(key.getBytes()));
			 valueMsg.setValue(block.toString());
			 
			 valueMsg.setNodeID(ByteString.copyFrom(homeNode.getID().getBytes()));
			 valueMsg.setNodeAdress(homeNode.getHost().getIP().toString());
			 valueMsg.setPort(homeNode.getHost().getPort());
			 
			 
			 responseObserver.onNext(valueMsg.build());
		 }
		
		 responseObserver.onCompleted();
	 }
	 
	 public void getTransactionPool(Ping request, StreamObserver<foundValue> responseObserver) {
		 List<Transaction> pool = this.blockchainNode.getTransactionPoolAsList();
		 System.out.println("get transaction pool request from" + new ID(request.getId().toByteArray()).getInt().intValue());
		 if(pool.isEmpty()) {
			 System.out.println("Our transaction pool is empty");
			 foundValue.Builder valueMsg = foundValue.newBuilder();
			 valueMsg.setIsFound(false);
			 valueMsg.setKey(ByteString.copyFrom(new ID(0).getBytes()));
			 valueMsg.setValue("");
			 
			 valueMsg.setNodeID(ByteString.copyFrom(homeNode.getID().getBytes()));
			 valueMsg.setNodeAdress(homeNode.getHost().getIP().toString());
			 valueMsg.setPort(homeNode.getHost().getPort());
			 
			 responseObserver.onNext(valueMsg.build());
		 }
		 else {
			 System.out.println("Transferring transactions");
			 for(Transaction transaction : pool) {
				 foundValue.Builder valueMsg = foundValue.newBuilder();
				 
				 valueMsg.setIsFound(true);
				 valueMsg.setKey(ByteString.copyFrom(new ID(0).getBytes()));
				 valueMsg.setValue(transaction.toString());
				 
				 valueMsg.setNodeID(ByteString.copyFrom(homeNode.getID().getBytes()));
				 valueMsg.setNodeAdress(homeNode.getHost().getIP().toString());
				 valueMsg.setPort(homeNode.getHost().getPort());
				 
				 
				 responseObserver.onNext(valueMsg.build());
			 }
		 }
		
		 responseObserver.onCompleted();
	 }
	 
	 
	 public void joinRequest(NodeInfo request, StreamObserver<SecurityChallenge> responseObserver) {
		 String receivedIP = request.getIP();
		 int receivedPort = request.getPort();
		 
		 securityChallengeID++;
		 Challenge newChallenge = new Challenge(securityChallengeID,receivedIP,receivedPort);
		 securityChallenges.put(newChallenge.getChallengeID(), newChallenge);
		 
		 System.out.println("[verifying] challenging node with " + newChallenge.getChallenge());
		 SecurityChallenge.Builder challengeMsg = SecurityChallenge.newBuilder();
		 challengeMsg.setChallengedID(newChallenge.getChallengeID());
		 challengeMsg.setChallenge(newChallenge.getChallenge());
		 challengeMsg.setSolution(-1);
		 
		 challengeMsg.setNodeAdress(newChallenge.getAddress());
		 challengeMsg.setPort(newChallenge.getPort());
		 
		 responseObserver.onNext(challengeMsg.build());
		 responseObserver.onCompleted();
	 }
	 
		public void joinValidate(SecurityChallenge request, StreamObserver<NodeInfo> responseObserver) {
			// check if security challenge exists, is valid and is solved
			if(!securityChallenges.containsKey(request.getChallengedID())) {
				responseObserver.onError(null);
			}
			Challenge challenge = securityChallenges.get(request.getChallengedID());
			System.out.println("[verifying] retrieved challenge with id" + challenge.getChallengeID());
			if(challenge.getPort() != request.getPort()) {
				responseObserver.onError(null);
				return;
			}
			
			if(!challenge.getAddress().equals(request.getNodeAdress())) {
				responseObserver.onError(null);
				return;
			}
			
			if(challenge.getChallenge() != request.getChallenge()) {
				responseObserver.onError(null);
				return;
			}
			
			String solvedhash = challenge.verify(request.getSolution(), challenge.getChallenge());
			System.out.println("Verifying security challenge " + challenge.getChallenge());
			System.out.println("For solution " + request.getSolution());
			System.out.println("Solved hash: " + solvedhash);
			
			if(challenge.getSolution() != -1) {
				System.out.println("This challenged was already solved");
				responseObserver.onError(null);
				return;
			}
			
			// if this has already been solved or it was solved incorrectly
			if(!challenge.leadingZerosCheck(solvedhash)) {
				responseObserver.onError(null);
				return;
			}
			
			challenge.setSolution(request.getSolution());
			securityChallenges.put(challenge.getChallengeID(),challenge);
			
			ID newID = idFactory.generate();
			// needs to be done before we update the routingTable with the newNode, or closestNodes is going to return newNode
			List<Node> closestNodes = homeNode.getRoutingTable().closestNodes(newID, Settings.JOIN_CLOSEST_NODES_ALPHA);
			closestNodes.add(homeNode);
			
			try {
				
				Node newNode = new Node(newID, new Host(request.getPort(),challenge.getAddress()));
				homeNode.getRoutingTable().update(newNode);
			} catch (InvalidPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			NodeInfo.Builder nodeInfoMsg = NodeInfo.newBuilder();
			nodeInfoMsg.setID(ByteString.copyFrom(newID.getBytes()));
			nodeInfoMsg.setIP(challenge.getAddress());
			nodeInfoMsg.setPort(request.getPort());
			
			responseObserver.onNext(nodeInfoMsg.build());
			
			// then the closest kademlia nodes
			for(Node node : closestNodes) {
				NodeInfo.Builder nodeMsg = NodeInfo.newBuilder();
				nodeMsg.setID(ByteString.copyFrom(node.getID().getBytes()));
				nodeMsg.setIP(node.getHost().getIP().toString());
				nodeMsg.setPort(node.getHost().getPort());
				
				responseObserver.onNext(nodeMsg.build());
				
			}
			
			responseObserver.onCompleted();
		}
}
