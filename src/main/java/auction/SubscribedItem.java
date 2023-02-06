package auction;

import org.json.simple.JSONObject;

public class SubscribedItem extends Item {
	/*
	 * This class should only be used by the auction handler!
	 */
	public SubscribedItem(long id, String name) {
		super(id, name);
	}

	@Override
	public String toString() {
		return "\tID: " + super.getID() + "\n\tName: " + super.getName() + "\n";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toJSON() {
		JSONObject root = new JSONObject();
		
		root.put("id", super.getID());
		root.put("name", super.getName());

		return root.toJSONString();
	}
}
