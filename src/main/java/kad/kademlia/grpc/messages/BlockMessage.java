package kad.kademlia.grpc.messages;

import blockchain.Block;

public class BlockMessage {
	private int type;
	private int id;
	Block block;
	
	public BlockMessage(int id, Block block) {
		this.id = id;
		this.type = 3;
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
