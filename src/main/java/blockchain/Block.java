package blockchain;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import blockchain.utils.Utils;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import blockchain.utils.miningInfo;

/*
 * This class represents a built block.
 */

public class Block {
	private List<Transaction> transactions;
	private String previousHash;
	private String hash;
	private Date timestamp;
	private int nounce;
	
	

	public Block(List<Transaction> transactions, String previousHash, String hash, int nonce, Date timestamp) {
		this.transactions = transactions;
		this.previousHash = previousHash;
		this.hash = hash;
		this.nounce = nonce;
		this.timestamp = timestamp;
	}
	
	public Block(String blockJSON) throws ParseException {
		this.fromString(blockJSON);
	}
	
	@Override
	public String toString() {
		JSONObject obj = new JSONObject();
		
		obj.put("previousHash", this.previousHash);
		obj.put("hash",this.hash);
		obj.put("timestamp",this.timestamp.getTime());
		obj.put("nounce",this.nounce);
		
		JSONArray transaction_list = new JSONArray();
		for(Transaction transaction : transactions) {
			transaction_list.add(transaction.toString());
		}
		
		obj.put("transactions",transaction_list);
		return obj.toJSONString();
	}
	
	public void fromString(String json) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(json);
		
		String previousHash = (String) obj.get("previousHash");
		String hash = (String) obj.get("hash");
		long timestamp_long = (long) obj.get("timestamp");
		Date timestamp = new Date(timestamp_long);
		int nounce =  ((Long) obj.get("nounce")).intValue();
		
		
		List<Transaction>  transactions = new ArrayList<>();
		JSONArray transaction_list = (JSONArray) obj.get("transactions");
		Iterator<String> transaction_it = transaction_list.iterator();
		
		while(transaction_it.hasNext()) {
			String transactionString = (String) transaction_it.next();
			transactions.add(new Transaction(transactionString));
		}
		
		
		this.previousHash = previousHash;
		this.hash = hash;
		this.timestamp = timestamp;
		this.nounce = nounce;
		this.transactions = transactions;
	}
	
	
	public String getHash() {
		return this.hash;
	}
	
	public String getPreviousHash() {
		return this.previousHash;
	}
	
	public int getNonce() {
		return this.nounce;
	}
	
	public List<Transaction> getTransactionBlock() {
		return this.transactions;
	}
	
	public Date getTimestamp() {
		return this.timestamp;
	}
	
}
