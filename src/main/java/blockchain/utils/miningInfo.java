package blockchain.utils;

import java.util.Date;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class miningInfo {
	public int nounce;
	public Date timestamp;
	
	
	public miningInfo() {
		this.nounce = -10000; //makeshift error code idk
	}
	
	
	public miningInfo(int nounce, Date timestamp) {
		this.nounce = nounce;
		this.timestamp = timestamp;
	}
	
	public miningInfo(String miningInfoJSON) throws ParseException {
		this.fromString(miningInfoJSON);
	}
	
	@Override
	public String toString() {
		JSONObject obj = new JSONObject();
		
		obj.put("nounce", this.nounce);
		obj.put("timestamp",this.timestamp.getTime());
		
		return obj.toJSONString();
	}
	
	public void fromString(String json) throws ParseException {		
		JSONParser parser = new JSONParser();
		JSONObject obj;
		
		miningInfo result = new miningInfo();

		obj = (JSONObject) parser.parse(json);
			
		int nounce =  ((Long) obj.get("nounce")).intValue();
		long timestamp_long = (long) obj.get("timestamp");
		Date timestamp = new Date(timestamp_long);
			
		this.nounce = nounce;
		this.timestamp = timestamp;

	}
	
}
