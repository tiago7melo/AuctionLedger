package blockchain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import blockchain.Transaction;
import blockchain.utils.TransactionComparator;
import blockchain.utils.Utils;
import blockchain.utils.miningInfo;
import kad.exceptions.InvalidPortException;
import kad.kademlia.Storage;
import kad.kademlia.grpc.KademliaOperation;
import kad.kademlia.network.Node;
import ledger.Settings;

/*
 * Class that handles blockchain events that happen in the network.
 */

public class BlockchainHandler {
	
	private TreeSet<Transaction> transactionPool = new TreeSet<>(new TransactionComparator());
	private List<Transaction> transactionBlock = new ArrayList<>(); // taken from transactionPool to generate a block
	private boolean isMiner;
	private BlockBuilder miner;
	
	private List<Block> blockchain;
	private String previousHash;
	private Block latestBlock; 
	
	
	private Utils utils;
	Node localNode;
	Storage storage;
	
	//TODO: placeholder, make factory if we need it somewhere else
	private int broadcastMsgCounter;
	
	public BlockchainHandler(Node localNode, boolean isMiner, Storage storage) {
		// constructor when there isn't an existing block chain in the network
		Utils utils = new Utils();
		this.utils = utils;
		this.isMiner = isMiner;
		this.localNode = localNode;
		this.storage = storage;
		
		this.broadcastMsgCounter = 0;
	
		this.blockchain = new ArrayList<>();
		this.previousHash = Settings.genesisHash;
		// genesis block has empty transactionBlock
		this.latestBlock = new Block(transactionBlock,Settings.genesisHash,Settings.genesisHash,0, new Date());
		blockchain.add(latestBlock);
		
		// This is always created for calculateBlockHash, only mines if isMiner is set as true
		this.miner = new BlockBuilder(previousHash, new Date());	
	}
	
	public BlockchainHandler(Node localNode, boolean isMiner, Storage storage, List<Block> existingChain) {
		// constructor assuming an already fetched existing chain in the network
		
		Utils utils = new Utils();
		this.utils = utils;
		this.isMiner = isMiner;
		this.localNode = localNode;
		this.storage = storage;
		
		this.broadcastMsgCounter = 0;
		
		this.blockchain = existingChain;
		this.latestBlock = existingChain.get(existingChain.size()-1);
		// hash of latest block. 
		this.previousHash = existingChain.get(existingChain.size()-1).getHash();
		
		//validate blockchain, discard if not valid. log failure
		boolean validatedBlockchain = validateBlockchain(blockchain);
		if(!validatedBlockchain) {
			System.out.println("Blockchain unsuccessfuly validated");
			
			System.out.println("[blockchain] bootstrap: failed to validate existing chain");
			
			this.blockchain = new ArrayList<>();
			this.previousHash = Settings.genesisHash;
			//empty transactionBlock
			this.latestBlock = new Block(transactionBlock,Settings.genesisHash,Settings.genesisHash,0, new Date());
			blockchain.add(latestBlock);
		}
		
		// only mines if isMiner is set as true
		this.miner = new BlockBuilder(previousHash, new Date());
	}
	
	public List<Block> getBlockchain() {
		return this.blockchain;
	}
	
	public void onTransactionReceived(Transaction transaction) {
		System.out.println("[blockchain] adding transaction " + transaction.getID());
		transactionPool.add(transaction);
		System.out.println("[blockchain] pooled transactions: " + transactionPool.size());
		
		// check if we can generate block and try to generate
		
		//TODO: wait for block to be done mining before mining another
		
		if(transactionPool.size() >= Settings.BLOCK_SIZE) {
			
			
			List<Transaction> transactions = getNFirstTransactions();
			
			if(this.isMiner) {
				//try to mine block
				miner.updateTimestamp();
				
				System.out.println("--------------------------------------------");
				System.out.println("[blockchain] starting mining for:");
				for ( Transaction t : transactions) {
					System.out.println(t.toString());
				}
				System.out.println("previousHash: " + this.latestBlock.getHash());
				System.out.println("timestamp" + miner.getTimestamp());
				System.out.println("--------------------------------------------");
				
				
				miner.setTransactions(transactions);
				miner.setPreviousHash(this.latestBlock.getHash());
				Thread thread = new Thread(){
				    public void run(){
				      System.out.println("Thread Running - Block Mining");
				      String minedHash = miner.mineBlock(0);
				      
						if(miner.mining) {
							System.out.println("[blockchain] block mined, calculated nounce: " + miner.getMinedNonce());
							System.out.println("[blockchain] block mined, calculated hash: " + minedHash);
							System.out.println("[blockchain] Block mined, forwarding to onBlockGenerated");
							onBlockGenerated(miner.getMinedNonce(),minedHash);
							miner.mining = false;
						}
				  	
				    }
				  };
				thread.start();
			}
		}
		
	}
	
	
	public void onBlockReceived(int receivedNonce, Date receivedTimestamp) {
		
		System.out.println("--------------------------------------------");
		System.out.println("[blockchain] validating received block:");
		System.out.println("nounce: " + receivedNonce);
		for ( Transaction t : transactionBlock) {
			System.out.println(t.toString());
		}
		System.out.println("previousHash: " + this.latestBlock.getHash());
		System.out.println("timestamp" + receivedTimestamp);
		System.out.println("--------------------------------------------");
		
		String receivedBlockHash = this.utils.calculateBlockHashDebug(receivedNonce,receivedTimestamp,this.latestBlock.getHash(),transactionBlock);
		System.out.println("[blockchain] calculated hash: " + receivedBlockHash);
		
			if(utils.leadingZerosCheck(receivedBlockHash))  {
				
				// when confirmed correct, update Blockchain
				Block newBlock = new Block(transactionBlock,this.latestBlock.getHash(),receivedBlockHash,receivedNonce,receivedTimestamp);
				latestBlock = newBlock;
				previousHash = latestBlock.getHash();
				blockchain.add(newBlock);
				this.broadcastMsgCounter++;
				System.out.println("[blockchain] validated received block, interrupting miner");
				// stop mining process because another node mined it first
				miner.mining = false;
				miner.setPreviousHash(latestBlock.getHash());
			}
			else {
				System.out.println("[blockchain] failed validating received Block");
			}

		}
		
	
	/*
	 * nounce - the nounce this node calculated for block currently being generated
	 * hash - hash generated
	 */
	public void onBlockGenerated(int nounce, String hash) {
		// post mining behaviour
		System.out.println("Block generated with nounce " + nounce);
		Date generationTimestamp = miner.getTimestamp();
		//broadcast block to the network so it can be added to blockchain
		KademliaOperation operation = new KademliaOperation(localNode);
		this.broadcastMsgCounter++;
		operation.broadcastBlockOperation(broadcastMsgCounter, new miningInfo(nounce,generationTimestamp));
		
		
		// update blockchain
		Block newBlock = new Block(transactionBlock,this.latestBlock.getHash(),hash,nounce,generationTimestamp);
		latestBlock = newBlock;
		previousHash = latestBlock.getHash();
		blockchain.add(newBlock);
		miner.mining = false;
		miner.setPreviousHash(latestBlock.getHash());
		
		System.out.println("[blockchain] Generated block is broadcasted. Store");
		// store block in kademlia DHT
		/*try {
			operation.storeOperation(newBlock, storage);
		} catch (InvalidPortException e) {
			e.printStackTrace();
		}*/
	}
	
	public TreeSet<Transaction> getTransactionPool() {
		return this.transactionPool;
	}
	
	public List<Transaction> getTransactionPoolAsList() {
		List<Transaction> result = new ArrayList<>(transactionPool);
		
		return result;
	}
	
	/*  take Settings.BLOCK_SIZE transactions from pool
	 *  every time we're waiting for data to mine
	 *  and our transactions pooled hit Settings.BLOCK_SIZE */
	List<Transaction> getNFirstTransactions() {
		
		List<Transaction> result = new ArrayList<Transaction>();
	
		for(int i = 0; i < Settings.BLOCK_SIZE; i++) {
			result.add(transactionPool.pollFirst());
		}
		
		this.transactionBlock = result;
		return result;
	}
	
	
	/*
	 * Called on bootstrap to iterate through the blockchain
	 * and verify correctness before joining.
	 */
	boolean validateBlockchain(List<Block> blockchain) {
		
		if(blockchain.size() < 2) {
			System.out.println("[bootstrap] existing blockchain only has 1 block");
			
			Block genesisBlock = blockchain.get(0);
			
			if(genesisBlock.getPreviousHash().equals(Settings.genesisHash) &&
			    genesisBlock.getHash().equals(Settings.genesisHash)) {
				return true;
			}
			
			System.out.println("[validation] genesis block doesn't have genesis hash");
			return false;
		}
		
		boolean status = true;
		for(int i = 0; i < blockchain.size() - 2; i++) {
			Block previousBlock = blockchain.get(i);
			Block currentBlock = blockchain.get(i+1);
			status = validateBlock(previousBlock,currentBlock);
		}
		
		return status;
	}
	
	
	/* validates previousBlock in the chain with an already validated latest currentBlock */
	boolean validateBlock(Block previousBlock, Block currentBlock) {
		
		if(!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
			System.out.println("[validation] current block previous hash doesn't match with previous block");
			return false;
		}
		
		String calculatedPreviousBlockHash = utils.calculateBlockHash(previousBlock.getNonce(),
																      previousBlock.getTimestamp(),
																      previousBlock.getPreviousHash(), 
																      previousBlock.getTransactionBlock());
	
		if(previousBlock.getHash().equals(Settings.genesisHash))
			return true;
		
		if(!previousBlock.getHash().equals(calculatedPreviousBlockHash)) {
			System.out.println(previousBlock.getHash());
			System.out.println(calculatedPreviousBlockHash);
			
			System.out.println("[validation] calculated block hash doesn't match stored block hash");
			return false;
		}
		
		return true;
	}

}
