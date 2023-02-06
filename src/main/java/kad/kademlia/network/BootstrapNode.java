package kad.kademlia.network;

import kad.exceptions.InvalidPortException;
import kad.kademlia.ID;

public class BootstrapNode extends NodeInstance {
	private IDFactory factory;
	
	public BootstrapNode(int port, String address, BootstrapNode bootstrap) throws InvalidPortException {
		super(port, address, bootstrap, true);
	}
	
	/*
	 * You should ONLY use this constructor for the first node on the network
	 */
	public BootstrapNode(int port, String address) throws InvalidPortException {
		super(port, address, true);
	}
	
	// Tiago - this is used at the KademliaService level
	/*protected boolean createFactory() {
		if (this.factory == null) {
			this.factory = new IDFactory();
			return true;
		}
		return false;
	}*/
	
	/*public boolean start() {
		return super.start(super.getBootstrapNode());
	}*/
	
	public boolean stop() {
		return super.stop();
	}
	
	public boolean isRunning() {
		return super.isRunning();
	}
	
	public ID generateID() {
		return this.factory.generate();
	}
}
