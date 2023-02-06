package kad.kademlia;

import java.util.Date;
import java.util.Objects;
import blockchain.Block;

public class StorageEntry {
    
    private final ID key;

    private final ID ownNodeID;

    private final String hash;
	
    private final Block block;
	
    private Date lastRepublished;
	
    private Date lastUpdated;

    public StorageEntry(ID ownNodeID, ID key, Block block) {
        this.ownNodeID = ownNodeID;
		this.key = key;
		this.block = block;
		this.hash = block.getHash();

        this.lastRepublished =  new Date();
        this.lastUpdated =  new Date();
    }

    public String getHash() {
		return hash;
	}

	public Block getBlock() {
		return this.block;
	}

	public Date getlastRepublished() {
		return lastRepublished;
	}

    

    //confirms if key is valid
    @Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass())
			return false;
            StorageEntry other = (StorageEntry) obj;
		return Objects.equals(getID(), other.getID());
	}

    public void setlastRepublished(Date lastRepublished) {
		this.lastRepublished = lastRepublished;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated() {
		this.lastUpdated = new Date();
	}

    public ID getID() {
		return key;
	}

    public ID ownNodeID() {
		return ownNodeID;
	}

}
