package kad.kademlia.grpc.messages;

import blockchain.Transaction;

public class TransactionMessage {
	
	private int type;
	private int id;
	Transaction transaction;
	
	public TransactionMessage(int id, Transaction transaction) {
		this.id = id;
		this.type = 1;
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
