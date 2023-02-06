package kad.kademlia.network;

import kad.kademlia.ID;

public class IDFactory {
	private ID current;
	
	public IDFactory() {
		this.current = new ID(0);
	}
	
	public ID generate() {
		this.current = new ID(this.current.getInt().intValue() + 1);
		return this.current;
	}
	
	public ID getCurrentID() {
		return this.current;
	}
}
