package blockchain.utils;

import java.util.Comparator;

import blockchain.Transaction;

public class TransactionComparator implements Comparator<Transaction> {


    public TransactionComparator() {
    }

    @Override
    public int compare(Transaction t1, Transaction t2)
    {
 
        int id1 = t1.getID();
        int id2 = t2.getID();
        
        int sender1 = t1.getAuctioneer();
        int sender2 = t2.getAuctioneer();
        
        if(sender1 < sender2) {
        	return -1;
        }
        else if(sender1 > sender2) {
        	return 1;
        }
        else {
        	 if(id1 < id2) {
             	return -1;
             }
             else if(id1 > id2) {
             	return 1;
             }
        }
        
        return 0;
    }
    
}

