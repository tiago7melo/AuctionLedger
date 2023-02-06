package blockchain.utils;

import java.io.UnsupportedEncodingException;


import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.json.simple.*;


import blockchain.Transaction;
import kad.kademlia.ID;
import kad.kademlia.network.Node;
import kad.kademlia.network.NodeInstance;
import ledger.Settings;

public class Utils {
	
	int transactionIdCounter = 0;
	
	public Utils() {}
	
	public String transactionsToString(List<Transaction> transactions) {
		String result = "";
		for(Transaction t : transactions) {
			result += t.toString() + ":";
			
		}
		
		return result;
	}
	
	String trimByBytes(String str, int lengthOfBytes) {
	    byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
	    ByteBuffer buffer = ByteBuffer.wrap(bytes);

	    if (lengthOfBytes < buffer.limit()) {
	        buffer.limit(lengthOfBytes);
	    }

	    CharsetDecoder decoder = StandardCharsets.UTF_8.newDecoder();
	    decoder.onMalformedInput(CodingErrorAction.IGNORE);

	    try {
	        return decoder.decode(buffer).toString();
	    } catch (CharacterCodingException e) {
	        // We will never get here.
	        throw new RuntimeException(e);
	    }
	}
	
	public ID hashToKadKey(String hash) {
		String trimmedHash = trimByBytes(hash,16);
		byte[] hashBytes = trimmedHash.getBytes();
		return new ID(hashBytes);	
	}
	
	public String calculateBlockHash(int nounce, Date timestamp, String previousHash, List<Transaction> transactions) { 	
    	
    	String dataToHash = previousHash +  timestamp.toString() + transactionsToString(transactions)
          + nounce;
          
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }
	
	public String calculateBlockHashDebug(int nounce, Date timestamp, String previousHash, List<Transaction> transactions) { 	
    	
    	String dataToHash = previousHash +  timestamp.toString() + transactionsToString(transactions)
          + nounce;
    	
    	System.out.println("DATA TO HASH");
    	System.out.println(dataToHash);
          
        MessageDigest digest = null;
        byte[] bytes = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            bytes = digest.digest(dataToHash.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        
        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }
        return buffer.toString();
    }
	
    public boolean leadingZerosCheck(String hash) {
    	String prefixString = new String(new char[Settings.POW_DIFFICULTY]).replace('\0', '0');
        if (!hash.substring(0, Settings.POW_DIFFICULTY).equals(prefixString)) {
            return false;
        }
        
        return true;
    }
	 
	
	public Transaction emulateNewTransaction(NodeInstance node) {
		
		System.out.println("Generating new transaction.");
		List<Node> allContacts = node.allContacts();
		
		Collections.shuffle(allContacts);
		
		List<Integer> pickRandomContacts = new ArrayList<>();
		int contactAmount = 3;
		
		for(Node n : allContacts) {
			if(contactAmount == 0) break;
			
			pickRandomContacts.add(n.getIdAsInt().intValue());
			
			contactAmount--;
		}
		
		int auctioneer = node.getIdAsInt().intValue();
		List<Integer> participants = pickRandomContacts;
		
		Collections.shuffle(pickRandomContacts);
		
		int winner = pickRandomContacts.get(0);
		
		transactionIdCounter++;
		int transactionBroadcastMsgCounter = 0;
		
		Transaction transaction = new Transaction(transactionIdCounter,participants,auctioneer,winner);
		
		return transaction;
		//node.operations.BroadcastTransactionOperation
		
	}

}
