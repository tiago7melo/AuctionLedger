package kad.kademlia.network;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import auction.Auction;
import auction.Item;
import auction.SubscribedItem;
import kad.exceptions.InvalidBidException;
import kad.exceptions.SubscriptionException;
import kad.kademlia.ID;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AuctionHandler {
	private final List<Auction> auctions;
	private List<SubscribedAuction> subscribed;
	private final ID nodeID;
	
	class SubscribedAuction {
		private Auction auction;
		private Host host;
		
		public SubscribedAuction(Auction auction, Host host) {
			this.auction = auction;
			this.host = host;
		}
		
		public Auction getAuction() {
			return auction;
		}
		
		public Host getHost() {
			return host;
		}
		
		@Override
		public String toString() {
			return this.auction.toString();
		}
	}
	
	public AuctionHandler(ID nodeID) {
		this.auctions = new ArrayList<Auction>();
		this.subscribed = new ArrayList<SubscribedAuction>();
		this.nodeID = nodeID;
	}
	
	public Auction getOwnAuction(String id) {
		for (Auction a : this.auctions) {
			if (a.getID().equals(id)) {
				return a;
			}
		}
		
		return null;
	}
	
	public SubscribedAuction getSubscribedAuction(String id) {
		for (SubscribedAuction a : this.subscribed) {
			if (a.getAuction().getID().equals(id)) {
				return a;
			}
		}
		
		return null;
	}
	
	public Auction createAuction(Item item) throws NoSuchAlgorithmException, ParseException {
		Auction newAuction = new Auction(this.nodeID, 15, item);
		this.auctions.add(newAuction);
		
		String json = this.toJSON("New Auction", newAuction);
		
		// Advertise network
		
		return newAuction;
	}
	
	// Change exception to SubscriptionException
	public Auction subscribeAuction(String jsonAuction, Host host) throws SubscriptionException, NoSuchAlgorithmException, ParseException {
		Auction auction = this.fromJSON(jsonAuction);
		for (SubscribedAuction subbed : this.subscribed) {
			if (subbed.auction.getID().equals(auction.getID())) {
				throw new SubscriptionException();
			}
		}
		
		this.subscribed.add(new SubscribedAuction(auction, host));
		
		return auction;
	}
	
	// Change Exception to InvalidBidException
	public void placeBid(double amount, SubscribedAuction auction) throws InvalidBidException, ParseException {
		String json = this.toJSON("Place bid", auction.auction);
		// place bid via RPC
		// receive RPC answer
		// if (answer == error) throw new InvalidBidException(code)
	}
	
	public void receiveBid(ID bidder, double bid, String auction) throws ParseException {
    	for (Auction a : this.auctions) {
    		if (a.getID().equals(auction)) {
    			if (!a.isActive()) {
    				String json = this.toJSON("Error: The auction is already over.", null);
    				// send error message to bidder informing the auction is over
    			} else {
    				boolean status = a.placeBid(bid, bidder);
        			if (status) {
        				String json = this.toJSON("Bid placed successfully!", null);
        				// send success message to bidder
        				
        				json = this.toJSON("Update Auction", a);
        				// advertise network
        			} else {
        				String json = this.toJSON("Error: The bid amount provided is insufficient or invalid.", null);
        				// send error message to bidder informing the bid is invalid or insufficient
        			}
    			}
    			
    			break;
    		}
    	}
    	String json = this.toJSON("Error: Auction not found.", null);
    	// send error message to bidder informing no auction was found
    }
	
	// This is supposed to be a private method, but it's public just for testing purposes
	@SuppressWarnings("unchecked")
	public String toJSON(String message, Object obj) throws ParseException {
		JSONObject root = new JSONObject();
		
		root.put("message", message);
		
		if (obj != null) {
			if (obj instanceof Auction) {
				JSONParser parser = new JSONParser();
				Auction a = (Auction) obj;
				JSONObject auction = new JSONObject();
				auction.put("id", a.getID());
				auction.put("initial_bid", a.getInitialBid());
				auction.put("initial_time", a.getInitialTime());
				auction.put("current_bid", a.getCurrentBid());
				if (a.getCurrentBidder() != null) {
					auction.put("current_bidder", a.getCurrentBidder().toString());
				} else {
					auction.put("current_bidder", "");
				}
				auction.put("Item", parser.parse(a.getItem().toJSON()));
				if (a.getTimer() != null) {
					auction.put("timer", a.getTimer().getSeconds());
				} else {
					auction.put("timer", a.getTime());
				}
				
				auction.put("active", a.isActive());
				
				root.put("auction", auction);
			}
		}
		
		return root.toJSONString();
	}
	
	private Auction fromJSON(String obj) throws ParseException, NoSuchAlgorithmException {
		Auction auction;
		
		JSONParser parser = new JSONParser();
		Object json_object = parser.parse(obj);
		JSONObject json = (JSONObject) json_object;
		json = (JSONObject) json.get("auction");
		
		String id = (String) json.get("id");
		double initial_bid = (double) json.get("initial_bid");
		long initial_time = (long) json.get("initial_time");
		double current_bid = (double) json.get("current_bid");
		String bidder = (String) json.get("current_bidder");
		long time = (long) json.get("timer");
		ID current_bidder;
		if (!bidder.equals("")) {
			current_bidder = new ID(Integer.valueOf(bidder));
		} else {
			current_bidder = null;
		}
		boolean active = (boolean) json.get("active");
		JSONObject item_object = (JSONObject) json.get("Item");
		Item item = new SubscribedItem((long) item_object.get("id"), (String) item_object.get("name"));
			
		auction = new Auction(id, initial_bid, item, initial_time, time, current_bidder, current_bid, active);
		
		
		return auction;
	}
}
