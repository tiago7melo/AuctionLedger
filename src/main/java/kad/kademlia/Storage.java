package kad.kademlia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import blockchain.Block;

public class Storage {

    private HashMap<String, StorageEntry> storage;
	
	public Storage() {
		this.storage = new HashMap<>();
	}
	
	public boolean contains(String hash) {
		return this.storage.containsKey(hash);
	}
	
	public void put(StorageEntry entry) {
		entry.setLastUpdated();
		this.storage.put(entry.getHash(), entry);
	}
	
	// WARNING:should be used after contains, or have a null check outside
	public StorageEntry get(String hash) {
		if(this.storage.containsKey(hash)) {
			return this.storage.get(hash);
		}
		else return null; 
	}
	
	public void remove(String hash) {
		this.storage.remove(hash);
		
	}
	
	public List<Block> getAll() {
		List<Block> result = new ArrayList<>();
		
		for(Map.Entry<String, StorageEntry> entry : storage.entrySet()) {
			result.add(entry.getValue().getBlock());
		}
		
		
		return result;
	}
	
	public boolean update(String hash) {
		if(!this.contains(hash)) {
			return false;
		}
		
		StorageEntry entry = this.storage.get(hash);
		entry.setLastUpdated();
		this.storage.remove(hash);
		this.storage.put(entry.getHash(), entry);
		
		return true;
	}
	
	public boolean updateRepublish(String hash) {
		if(!this.contains(hash)) {
			return false;
		}
		
		StorageEntry entry = this.storage.get(hash);
		entry.setLastUpdated();
		this.storage.remove(hash);
		this.storage.put(entry.getHash(), entry);
		
		return true;
	}
}
