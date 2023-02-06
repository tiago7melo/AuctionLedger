package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.json.simple.parser.ParseException;
import com.google.protobuf.ByteString;
import blockchain.Block;
import blockchain.BlockBuilder;
import blockchain.Transaction;
import blockchain.utils.Utils;
import blockchain.utils.miningInfo;
import kad.exceptions.InvalidPortException;
import kad.kademlia.ID;
import kad.kademlia.KeyComparator;
import kad.kademlia.network.RoutingTable;
import ledger.Settings;

public class BasicStructureDemo {
	public static void main(String[] args) throws InvalidPortException {
			
	    // data transfer JSON conversion tests
		     miningInfo m = new miningInfo(1359,new Date());
		     String s = m.toString();
		     System.out.println(m.timestamp);
		     System.out.println(s);
		     
		     try {
				miningInfo m2 = new miningInfo(s);
				String s2 = m2.toString();
				System.out.println(s2);
				 System.out.println(m2.timestamp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
		   
		   /*ID p2 = new ID(2);
		   ID p3 = new ID(3);
		   ID p4 = new ID(4);*/
		     
		  
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
		   System.out.println("bl1_hash:" + bl1_hash);
		   
		   Block bl1 = new Block(transactions,Settings.genesisHash,bl1_hash,30,bl1_date);
		   
		   String bl1_str = bl1.toString();
		   System.out.println(bl1_str);
		   
		   ID bl1_key = utils.hashToKadKey(bl1_hash);
		   System.out.println("bl1_key:" + bl1_key.getInt().intValue());
		   
		   try {
			Block bl1_new = new Block(bl1_str);
			System.out.println(bl1_new.toString());
			
			for(Transaction t : bl1_new.getTransactionBlock()) {
				System.out.println(t.toString());
			}
			
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		   	  
		   
		   System.out.println("--------------------------");
		   String st1 = t1.toString();
		   System.out.println(st1);
		   
		   try {
			Transaction t1_new = new Transaction(st1);
			String st_new = t1_new.toString();
			System.out.println(st_new);
			
		System.out.println("--------------------------");
			
			for(int p : t2.getParticipants()) {
				System.out.println(p);
				
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		   
		   ID p1 = new ID(1);
		   ID p4 = new ID(4);
		   System.out.println("----");
		   System.out.println(p1.getDistance(p4));
		   System.out.println(p4.getDistance(p1));
		   System.out.println("----");
		   
		   
		   RoutingTable table_p1 = new RoutingTable(p1);
		   System.out.println(table_p1.findBucket(p4));
		   
		   BlockBuilder miner = new BlockBuilder(Settings.genesisHash,bl1_date);
		   miner.setTransactions(transactions);
		   
		   String powed_hash = "00006772fbb1319aa1cda793a244e39fe7837a24961bb848aa5dc50c679d13de";
		   System.out.println(miner.leadingZerosCheck(powed_hash));
		   
		   String mined_hash = miner.mineBlock(0);
		   System.out.println(mined_hash);
		   System.out.println("Mined nounce " + miner.getMinedNonce());
			
		   KeyComparator comparator = new KeyComparator(new ID(5));
		   
		   Scanner scan = new Scanner(System.in);
		   
		   /*Host dummyHost  = new Host(1005,"127.0.0.1");
		   while (true) {
			   System.out.println("Next int?");
			   ID id = new ID(scan.nextInt());
			   System.out.println("Compare to?");
			   ID id2 = new ID(scan.nextInt());
			   int comparation = comparator.compare(new Node(id,dummyHost),new Node(id2,dummyHost));
			   System.out.println("result:" + comparation);
			   
			   if(comparation <= 0) {
				   System.out.println("id1 is closer to target than id2");
			   }   
		   }*/
		   
			List<String> seenBroadcastTransactions = new ArrayList<>();
			ByteString senderIDString = ByteString.copyFrom(p1.getBytes());
			
			byte[] senderID = senderIDString.toByteArray();
			
			
			

	}
}