package test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import blockchain.Block;
import blockchain.Transaction;
import blockchain.utils.Utils;
import kad.exceptions.InvalidPortException;
import kad.kademlia.ID;
import kad.kademlia.network.Host;
import kad.kademlia.network.Node;
import kad.kademlia.network.NodeInstance;
import ledger.Settings;

public class KademliaOpDemo {
	
	int transactionIDCounter = 0;
	
	
	public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			//System.out.println("Is this node a bootstrap? 1-yes 2-no");
			int isBootstrap = 2;
			
			System.out.println("Choose a port to start the service.");
			int port = scan.nextInt();
			
			System.out.println("Find node testing. Which node to find?");
			int nodeToFind = scan.nextInt();

			NodeInstance node;
				try {
					
					if(isBootstrap == 1) {
						System.out.println("Bootstrap node started");
						//node = new BootstrapNode(port, "localhost");
						node = new NodeInstance(1001,"localhost",true);
					}
					else {
						System.out.println("Normal node started");
						Node bootstrapNodeInfo = new Node(new ID(0), new Host(1001,"localhost"));
						node = new NodeInstance(port,"localhost",bootstrapNodeInfo);
					}
					System.out.println("ID: " + node.getID().getInt());
				  	node.start();
				  	node.getOperations().findNodeOperation(new ID(nodeToFind), node.getRoutingTable().allNodes());
				  	
				  	/* placeholder transactions and block for store and broadcast testing*/
				    List<Integer> participants = new ArrayList<>();
					participants.add(1);
					participants.add(2);
					participants.add(3);
					participants.add(4);
					
				  	Transaction t1 = new Transaction(1,participants,5, 4);
					Transaction t2 = new Transaction(2,participants,6, 3);
					Transaction t3 = new Transaction(3,participants,7, 2);	
				  	
					 List<Transaction> transactions = new ArrayList<>();
					 transactions.add(t1); 
					 transactions.add(t2);
					 transactions.add(t3);
					 
					 Utils utils = new Utils();
					 Date bl1_date = new Date();
					 String bl1_hash = utils.calculateBlockHash(30, bl1_date, Settings.genesisHash, transactions);
					 Block bl1 = new Block(transactions,Settings.genesisHash,bl1_hash,30,bl1_date);
					 
					 System.out.println("Testing store operation");
					 node.getOperations().storeOperation(bl1, node.getStorage());
					 
					 //node.operations.broadcastTransactionOperation(123, t1);
					 int transactionBroadcastMsgCounter = 0;
					 
					 /*while(true) {
						 System.out.println("Enter anything to simulate next transaction");
						 scan.nextInt();
						 Transaction transaction = utils.emulateNewTransaction(node);
						 System.out.println("Sent transaction:" + transaction.toString());
						 transactionBroadcastMsgCounter++;
						 node.newOperation();
						 node.operations.broadcastTransactionOperation(transactionBroadcastMsgCounter, transaction, node.blockchainNode);
						 
					 }*/
	 	
				} catch (InvalidPortException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}

	}
	
}