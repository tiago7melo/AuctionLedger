package kad.kademlia.network;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.json.simple.parser.ParseException;

import auction.Auction;
import auction.Item;
import blockchain.Block;
import blockchain.BlockchainHandler;
import blockchain.Transaction;
import kad.exceptions.InvalidPortException;
import kad.kademlia.ID;
import kad.kademlia.Storage;
import kad.kademlia.grpc.KademliaOperation;
import kad.kademlia.grpc.KademliaServer;
import kad.kademlia.network.AuctionHandler.SubscribedAuction;
import ledger.Settings;

public class NodeInstance extends Node {
	private BootstrapNode bootstrap;
	private KademliaServer server;
	private KademliaOperation operations;
	private AuctionHandler auctionHandler;
	private BlockchainHandler blockchainHandler; // blockchain handler
	private boolean running;
	private boolean isbootstrap;
	private Storage storage;

	// Constructor for the first (bootstrap) node on the network
	public NodeInstance (int port, String address, boolean isbootstrap) throws InvalidPortException {
		super(new Host(port, address));
		super.setID(new ID(0));
		this.storage = new Storage();
		this.operations = new KademliaOperation(this);
		this.blockchainHandler = new BlockchainHandler(this, true, storage);
		this.auctionHandler = new AuctionHandler(this.getID());
		this.server = new KademliaServer(this,storage,operations, blockchainHandler);
		this.running = false;
		this.isbootstrap = isbootstrap;
	}
	
	// Constructor for non-bootstrap nodes
	public NodeInstance (int port, String address, Node bootstrap) throws InvalidPortException {
		super(new Host(port, address));
		
		this.operations = new KademliaOperation(this);
		Node localNodeInfo = operations.joinOperation(bootstrap);
		super.setID(localNodeInfo.getID());
		super.startTable(localNodeInfo.getRoutingTable());
		this.auctionHandler = new AuctionHandler(this.getID());
		/*List<Node> neighbours = super.getRoutingTable().allNodes();
		for(Node n : neighbours) {
			boolean status = operations.pingOperation(n);
		}*/
		this.storage = new Storage();
		
		// blockchain bootstrapping
		List<Node> closestNodes = this.getRoutingTable().closestNodes(super.getID(), 1);
		Node blockchainBootstrap = closestNodes.get(0);
		List<Block> blockchain = this.operations.getBlockchainOperation(blockchainBootstrap);
		List<Transaction> transactionPool = this.operations.getTransactionPoolOperation(blockchainBootstrap);
		
		this.blockchainHandler = new BlockchainHandler(this, true, storage, blockchain);
		blockchainHandler.getTransactionPool().addAll(transactionPool);
		
		this.server = new KademliaServer(this,storage,operations, blockchainHandler);
		this.running = false;
		this.isbootstrap = false;
	}
	
	// Constructor for any bootstrap node besides the first one
	protected NodeInstance (int port, String address, BootstrapNode bootstrap, boolean isbootstrap) throws InvalidPortException {
		super(new Host(port, address));
		this.bootstrap = bootstrap;
		super.setID(this.bootstrap.generateID());
		

		this.storage = new Storage();
		this.operations = new KademliaOperation(this);
		
		this.blockchainHandler = new BlockchainHandler(this, true, storage);
		this.server = new KademliaServer(this,storage,operations, blockchainHandler);
		this.running = false;
		this.isbootstrap = isbootstrap;
	}
	
    public boolean start() {
    	//TODO: Start ping cycle
    	//TODO: Update routing table

    	Thread thread = new Thread(){
		    public void run(){
		      System.out.println("Thread Running - GRPC Server");
		      server.start();
		  	
		    }
		  };
		thread.start();
		
		Thread republishthread = new Thread(){
		    public void run(){
		      System.out.println("Thread Running - Republish");
		      operations.republishOperation(storage);
		  	  
			    try {
					Thread.sleep(Settings.REPUBLISH_PERIOD_MILLIS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		  };
		//republishthread.start();
		
		Thread pingthread = new Thread(){
		    public void run(){
		      System.out.println("Thread Running - Period Check Alive");
		  	  operations.checkAliveOperation();
			    try {
					Thread.sleep(Settings.CHECK_ALIVE_PERIOD_MILLIS);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		  };
		//pingthread.start();
    	
    	return this.running = true;
    }
    
    public boolean isRunning() {
    	return this.running;
    }
    
    public BootstrapNode getBootstrap() {
		return bootstrap;
	}

	public KademliaServer getServer() {
		return server;
	}

	public KademliaOperation getOperations() {
		return operations;
	}

	public AuctionHandler getAuctionHandler() {
		return auctionHandler;
	}

	public BlockchainHandler getBlockchainHandler() {
		return blockchainHandler;
	}

	public boolean isIsbootstrap() {
		return isbootstrap;
	}

	public Storage getStorage() {
		return storage;
	}
    
    public boolean stop() {
    	//TODO: get closest nodes
    	//TODO: send closing signal and closest nodes to bootstrap node
    	this.server.stop();
    	
    	return this.running = false;
    }
    
    public List<Node> allContacts() {
    	return super.getRoutingTable().allNodes();
    }
    
    public void newOperation() {
    	this.operations = new KademliaOperation(this);
    }
    
    public boolean isBootstrap() {
    	return this.isbootstrap;
    }
    
    public Auction createAuction(Item item) throws NoSuchAlgorithmException, ParseException {
    	return this.auctionHandler.createAuction(item);
    }
    
    public Auction getOwnAuction(String id) {
    	return this.auctionHandler.getOwnAuction(id);
    }
    
    public SubscribedAuction getSubscribedAuction(String id) {
    	return this.auctionHandler.getSubscribedAuction(id);
    }
}
