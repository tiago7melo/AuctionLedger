package kad.kademlia.grpc.messages;

import blockchain.Transaction;

public class TransactionBroadcast {
	private int type;
	private int id;
	Transaction transaction;
	
	public TransactionBroadcast(int id, Transaction transaction) {
		this.id = id;
		this.type = 2;
		this.transaction = transaction;
		
	}
	
	public int getType() {
		return this.type;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getContent() {
		return this.transaction.toString();
	}

}

