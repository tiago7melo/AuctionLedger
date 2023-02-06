package auction;

public abstract class Item {
	private final long id;
	private String name;
	
	public Item(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public long getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public abstract String toString();
	
	public abstract String toJSON();
}
