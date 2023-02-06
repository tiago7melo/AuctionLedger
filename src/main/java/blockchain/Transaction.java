package blockchain;

import java.util.ArrayList;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.protobuf.ByteString;

import blockchain.utils.miningInfo;
import kad.kademlia.ID;

public class Transaction {
	String networkID;
	private int id;
	int auctioneer;
	List<Integer> participants;
	int winner;
	
	public Transaction(int id, List<Integer> participants, int auctioneer, int winner) {
		this.networkID = auctioneer + ":" + id;
		this.id = id;
		this.auctioneer = auctioneer;
		this.participants = participants;
		this.winner = winner;
	}
	
	public Transaction(String transactionJSON) throws ParseException {
		this.fromString(transactionJSON);
	}
	
	public int getID() {
		return this.id;
	}
	
	public int getAuctioneer() {
		return this.auctioneer;
	}
	
	public List<Integer> getParticipants() {
		return this.participants;
	}
	
	
	@Override
	public String toString() {
		JSONObject obj = new JSONObject();
		
		obj.put("id",this.id);
		
		//String auctioneer_s = Base64.getEncoder().encodeToString(this.auctioneer.getBytes());
		obj.put("auctioneer", this.auctioneer);
		
		//String winner_s = Base64.getEncoder().encodeToString(this.winner.getBytes());
		obj.put("winner", this.winner);
		
		JSONArray participant_list = new JSONArray();
		for(int participant : this.participants) {
			//String participant_s = Base64.getEncoder().encodeToString(participant.getBytes());
			participant_list.add(participant);
		}
		
		obj.put("participants",participant_list);
		
		return obj.toJSONString();
	}
	
	public void fromString(String json) throws ParseException {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(json);
		
		int id =  ((Long) obj.get("id")).intValue();
		
		//ByteString auctioneer_id = (ByteString) obj.get("auctioneer");
		//String auctioneer_s = (String) obj.get("auctioneer");
		//byte[] auctioneer_id = Base64.getDecoder().decode(auctioneer_s);
		int auctioneer =  ((Long) obj.get("auctioneer")).intValue();
		
		//ByteString winner_id = (ByteString) obj.get("winner");
		//String winner_s = (String) obj.get("winner");
		//byte[] winner_id = Base64.getDecoder().decode(winner_s);
		
		int winner = ((Long) obj.get("winner")).intValue();
		
		List<Integer> participants = new ArrayList<>();
			
		JSONArray participant_list = (JSONArray) obj.get("participants");
		Iterator<Long> participant_it = participant_list.iterator();
			
		while(participant_it.hasNext()) {
			
			//String participant_s = participant_it.next();
			//byte[] participant_id = Base64.getDecoder().decode(participant_s);
			participants.add(((Long) participant_it.next()).intValue());

		}
		
		
		this.id = id;
		this.winner = winner;
		this.auctioneer = auctioneer;
		this.networkID = auctioneer + ":" + id;
		this.participants = participants;
		//Transaction result = new Transaction(id,participants,auctioneer,winner);
			
		//return result;
	}
}

