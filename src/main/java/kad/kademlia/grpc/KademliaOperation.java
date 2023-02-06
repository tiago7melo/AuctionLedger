package kad.kademlia.grpc;

import java.awt.event.KeyListener;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import blockchain.Block;
import blockchain.BlockchainHandler;
import blockchain.Transaction;
import kad.exceptions.InvalidPortException;
import kad.kademlia.ID;
import kad.kademlia.KeyComparator;
import kad.kademlia.Storage;
import kad.kademlia.StorageEntry;
import kad.kademlia.network.Challenge;
import kad.kademlia.network.Node;
import ledger.Settings;
import blockchain.utils.TransactionComparator;
import blockchain.utils.Utils;
import blockchain.utils.miningInfo;

/*
 * This class utilizes the KademliaClient RPCs to perform more complex Kademlia Operations
 */

public class KademliaOperation {
	
	private Utils utils;
	private Node localNode;
	
	public KademliaOperation(Node localNode) {
		this.utils = new Utils();
		this.localNode = localNode;
	}
	
	/*public Node joinOperation(Node bootstrapNodeInfo) {
		KademliaClient client = new KademliaClient(this.localNode, bootstrapNodeInfo);
		Node result = client.join();
		client.channel.shutdownNow();
		
		return result;
	}*/
	
	public Node joinOperation(Node bootstrapNodeInfo) {
		KademliaClient client = new KademliaClient(this.localNode, bootstrapNodeInfo);
		Challenge challenge = client.joinRequest();
		
		System.out.println("Trying to solve challenge" + challenge.getChallenge());
		int solution = challenge.solve(challenge.getChallenge());
		
		System.out.println("[bootstrap] found solution " + solution);

		challenge.setSolution(solution);
		
		Node nodeinfo = client.joinValidate(challenge);
		
		client.channel.shutdownNow();
		return nodeinfo;
	}
	
	public boolean pingOperation(Node destinationNode) {
		//create connection stream to destination node
		KademliaClient client = new KademliaClient(this.localNode,destinationNode);
		//ping destination node, giving him the information of homeNode
		//hence why we pass homeNode as an argument
		boolean status = client.ping();
		client.channel.shutdownNow();
		
		return status;
	}
	
	
	public Node findNodeOperation(ID targetID, List<Node> unasked) throws InvalidPortException {

		List<Node> unaskedNodes = unasked;
		
		//stop criteria: we have no more nodes to ask
		if(unaskedNodes.isEmpty()) {
			System.out.println("[find] unasked nodes is empty");
			TreeSet<Node> allNodes = new TreeSet<>(new KeyComparator(targetID)); 
			allNodes.addAll(this.localNode.getRoutingTable().allNodes());
			
			Node foundNode = allNodes.first();
			
			return foundNode;
		}
		
		TreeSet<Node> orderedNodes = new TreeSet<>(new KeyComparator(targetID));
		orderedNodes.addAll(unaskedNodes);
		Node destinationNode = orderedNodes.first();
		System.out.println("[find] contacting " + destinationNode.getID().getInt());

		KademliaClient client = new KademliaClient(this.localNode, destinationNode);
		
		Node foundNode = client.findNode(targetID);
		client.channel.shutdownNow();
		
		foundNode.setSeenNow();
		foundNode.resetStaleCount();
		
		KeyComparator comparator = new KeyComparator(targetID);
		if(comparator.compare(destinationNode, foundNode) <= 0) {
			System.out.println("[find] didn't find a closer node. ending");
			return destinationNode;
		}
		
		unaskedNodes.remove(destinationNode);
		
		// if its a node we didnt know about, aka its not on our routing table yet
		// it wasn't added to unasked before, so add it
		if(this.localNode.getRoutingTable().contains(foundNode.getID()) == false) {
			System.out.println("[find] adding to unasked: " + foundNode.getIdAsInt().intValue());
			unaskedNodes.add(foundNode);
		}
		this.localNode.getRoutingTable().update(foundNode);
		
		//stop criteria: found our target node;
		if(foundNode.getID().getInt().intValue() == targetID.getInt().intValue()) {
			System.out.println("[find] found our target.");
			return foundNode;
		}
		

		//recursive call
		return findNodeOperation(targetID,unaskedNodes);
	}
	
	public Block findValueOperation(String hash, List<Node> asked) {
		ID key = utils.hashToKadKey(hash);
		
		List<Node> askedNodes = asked;
		List<Node> unaskedNodes = this.localNode.getRoutingTable().allNodes();
		unaskedNodes.removeAll(askedNodes);
		
		//stop criteria: no more nodes to ask
		if(unaskedNodes.isEmpty()) {
			return new Block (null,"notFound","notFound",-1, new Date());
		}
		
		TreeSet<Node> orderedNodes = new TreeSet<>(new KeyComparator(key));
		orderedNodes.addAll(unaskedNodes);
		Node destinationNode = orderedNodes.first();
		
		KademliaClient client = new KademliaClient(this.localNode, destinationNode);
		
		Block foundBlock = client.findValue(hash);
		client.channel.shutdownNow();
		
		destinationNode.setSeenNow();
		destinationNode.resetStaleCount();
		this.localNode.getRoutingTable().update(destinationNode);
		askedNodes.add(destinationNode);
		
		
		// stop criteria: found our value
		if(foundBlock.getPreviousHash() != "notFound" && foundBlock.getHash() != "notFound") {
			return foundBlock;
		} 
		
		//recursive call
		return findValueOperation(hash,askedNodes);
	}
	
	
	public boolean storeOperation(Block block, Storage storage) throws InvalidPortException {
		
		ID key = utils.hashToKadKey(block.getHash());
		List<Node> allNodes = this.localNode.getRoutingTable().allNodes();
		Node destinationNode = findNodeOperation(key,allNodes);
		
		System.out.println("[store] for key " + key.getInt().intValue());
		System.out.println("[store] closest node to key is " + destinationNode.getIdAsInt().intValue());
		
		
		KeyComparator comparator = new KeyComparator(key);
		// what if local node is the closest to store key?
		if (comparator.compare(localNode, destinationNode) <= 0) {
			System.out.println("[store] local node is closer than any node in network. storing here.");
			StorageEntry storage_entry = new StorageEntry(localNode.getID(),key,block);
			storage.put(storage_entry);
			return true;
		}
		
		KademliaClient client = new KademliaClient(this.localNode, destinationNode);
		
		boolean status = client.store(block);
		client.channel.shutdownNow();
		
		return status;
	}
	
	
	public void broadcastTransactionOperation(int broadcastMsgID, Transaction transaction, BlockchainHandler blockchainNode) {
		List<Node> nodes = this.localNode.getRoutingTable().allNodes();
		
		blockchainNode.onTransactionReceived(transaction);
		
		for(Node node : nodes) {

			KademliaClient client = new KademliaClient(this.localNode, node);

			boolean status = client.broadcastTransaction(transaction,this.localNode,broadcastMsgID);
			client.channel.shutdownNow();
		}
		
	}
	
	public void broadcastBlockOperation(int broadcastMsgID, miningInfo mineInfo) {
		List<Node> nodes = this.localNode.getRoutingTable().allNodes();
		
		for(Node node : nodes) {

			KademliaClient client = new KademliaClient(this.localNode, node);

			boolean status = client.broadcastMineInfo(mineInfo, this.localNode, broadcastMsgID);
			client.channel.shutdownNow();		
			//TODO: handle status
		}
		
	}
	
	// TODO: complete when we get auction
	/*public void broadcastAuctionOperation(int broadcastMsgID, Auction auction) {
		List<Node> nodes = this.localNode.getRoutingTable().allNodes();
		
		for(Node node : nodes) {

			KademliaClient client = new KademliaClient(this.localNode, node);
			client.broadcastAuction(auction,this.localNode.getID(), broadcastMsgID);

			//boolean status = client.broadcastTransaction(transaction,this.localNode.getID(),broadcastMsgID);
			
			//TODO: handle status
		}
		
	}*/
	
	/*public void messageBidOperation(Bid bid) */
	
	//to be recurring, with certain periodicity
	public void checkAliveOperation() {
		System.out.println("[operation] running period alive check for neighbours");
		List<Node> nodes = localNode.getRoutingTable().allNodes();
		
		for(Node node : nodes) {

			KademliaClient client = new KademliaClient(localNode, node);

			boolean status = client.ping();
			
			if(!status) {
				node.incrementStaleCount();
				this.localNode.getRoutingTable().update(node);
			}
			
			client.channel.shutdownNow();
		}
		
		nodes = localNode.getRoutingTable().allNodes();
		
		for(Node node : nodes) {
			if(node.getStaleCount() >= Settings.STALE_THRESHOLD) {
				System.out.println("[operation] node " + node.getIdAsInt().intValue() + " failed, finding replacement");
				this.localNode.getRoutingTable().delete(node);
				
				// Find node will replace the failed node with the closest node available to failed
				try {
					Node replacement = this.findNodeOperation(node.getID(), nodes);
					this.localNode.getRoutingTable().update(replacement);
				} catch (InvalidPortException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	// to be recurring, with certain periodicity
	public void republishOperation(Storage storage) {
		List<Block> blocks = storage.getAll();
		
		System.out.println("[operation] trying to republish contents");
		
		for(Block block : blocks) {
			try {
				this.storeOperation(block,storage);
				storage.updateRepublish(block.getHash());
			} catch (InvalidPortException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public List<Block> getBlockchainOperation(Node chainBootstrap) { 
		KademliaClient client = new KademliaClient(localNode,chainBootstrap);
		List<Block> result = client.getBlockChain();
		
		for(Block b : result) {
			System.out.println("[bootstrap] received block with hash " +  b.getHash());
		}
		
		client.channel.shutdownNow();
		return result;
	}
	
	public List<Transaction> getTransactionPoolOperation(Node poolBootstrap) { 
		KademliaClient client = new KademliaClient(localNode,poolBootstrap);
		List<Transaction> result = client.getTransactionPool();
		
		for(Transaction t : result) {
			System.out.println("[bootstrap] received transaction: " + t.toString());
		}
		client.channel.shutdownNow();
		return result;
	}
	
}
