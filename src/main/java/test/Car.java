package test;

import org.json.simple.JSONObject;

import auction.Item;

class Car extends Item {
	private String brand;

	public Car(long id, String name, String brand) {
		super(id, name);
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public String toString() {
		return "\tID: " + super.getID() + "\n\tName: " + super.getName() + "\n\tBrand: " + this.brand + "\n";
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toJSON() {
		JSONObject root = new JSONObject();
		
		root.put("id", super.getID());
		root.put("name", super.getName());
		root.put("brand", this.brand);

		return root.toJSONString();
	}
}
