package blockchain;

import java.util.Date;
import java.util.List;
import blockchain.utils.Utils;
import ledger.Settings;

public class BlockBuilder {
    private String hash;
    private String previousHash;
    private int prefix;
    private int minedNounce;
    private Date timestamp;
    
    List<Transaction> transactions;
    
    public boolean mining = false;
    
    private Utils utils;
    
    public BlockBuilder(String previousHash, Date timestamp) {
    	this.previousHash = previousHash;
    	this.prefix = Settings.POW_DIFFICULTY;
    	this.hash = "";
    	this.timestamp = timestamp;
    	utils = new Utils();
    }
    
    public void setTransactions(List<Transaction> transactions) {
    	this.transactions = transactions;
    }
    
    public void setPreviousHash(String previousHash) {
    	this.previousHash = previousHash;
    }
    
    public void updateTimestamp() {
    	this.timestamp = new Date();
    }
    
    public Date getTimestamp() {
    	return this.timestamp;
    }
 
    
    public boolean leadingZerosCheck(String hash) {
    	String prefixString = new String(new char[prefix]).replace('\0', '0');
        if (!hash.substring(0, prefix).equals(prefixString)) {
            return false;
        }
        
        return true;
    }
    
    
    public String mineBlock(int nounce) {
    	this.mining = true;
    	utils.calculateBlockHashDebug(nounce, timestamp, previousHash, transactions);
    	
    	//while hash leading zeros != BlockChainSettings.POW_DIFFICULTY
        do  {
            nounce++;
            this.hash = utils.calculateBlockHash(nounce,timestamp,previousHash,transactions);
        } while ((!leadingZerosCheck(hash) && mining));
        
        
        this.minedNounce = nounce;
        return this.hash;
    }
    
    /*
     * Triggered by manager when a block is mined.
     */
    public int getMinedNonce() {
    	return this.minedNounce;
    }
    
    
    
}
