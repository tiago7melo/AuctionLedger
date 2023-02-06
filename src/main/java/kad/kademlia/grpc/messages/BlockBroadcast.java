package kad.kademlia.grpc.messages;

import blockchain.Block;

public class BlockBroadcast {
	
	private int type;
	private int id;
	Block block;
	
	public BlockBroadcast(int id, Block block) {
		this.id = id;
		this.type = 4;
		this.block = block;
		
	}
	
	public int getType() {
		return this.type;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getContent() {
		return this.block.toString();
	}

}

