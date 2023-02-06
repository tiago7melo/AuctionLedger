package auction;

import kad.kademlia.ID;
import java.security.NoSuchAlgorithmException;

public class Auction {
    private final String id;
    private final double initialBid;
    private final long initialTime;
    private double currentBid;
    private ID currentBidder;
    private final Item item;
    private Timer timer;
    private boolean active;
    private long time;
    
    public class Timer implements Runnable {
    	private int seconds = 300;
    	private Thread t;
    	private boolean reset;
    	
    	Timer() {
    		this.t = new Thread(this);
    		this.reset = false;
    		this.t.start();
    	}
    	
    	public int getSeconds() {
    		return this.seconds;
    	}

		public void run() {
			while (this.seconds > 0) {
				if (this.reset) {
					this.seconds = 300;
					this.reset = false;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.seconds--;
			}
			
			Auction.this.active = false;
		}
		
		protected void reset() {
			this.reset = true;
		}
    }

    // Used when creating an auction
    public Auction(ID nodeID, double initialBid, Item item) throws NoSuchAlgorithmException {
    	this.item = item;
        this.initialBid = initialBid;
        this.initialTime = System.currentTimeMillis();
        this.id = this.generateId(nodeID, this.initialTime);
        this.timer = new Timer();
        this.active = true;
        this.currentBid = 0;
    }
    
    // Used when received an auction from a broadcast
    public Auction(String auctionID, double initialBid, Item item, long initialTime, long time, ID currentBidder, double currentBid, boolean active) throws NoSuchAlgorithmException {
    	this.item = item;
        this.initialBid = initialBid;
        this.initialTime = initialTime;
        this.id = auctionID;
        this.time = time;
        this.currentBidder = currentBidder;
        this.active = active;
        this.currentBid = currentBid;
    }

	public long getInitialTime() {
		return initialTime;
	}

	public Timer getTimer() {
		return timer;
	}
	
	public long getTime() {
		return this.time;
	}

	public boolean isActive() {
    	return this.active;
    }
    
    public Item getItem() {
    	return this.item;
    }

    public String getID() {
        return this.id;
    }

    public double getInitialBid() {
        return this.initialBid;
    }
    
    public boolean placeBid(double bid, ID bidder) {
    	if ((this.currentBid == 0 && bid > this.initialBid) || bid > this.currentBid) {
    		this.currentBid = bid;
    		this.currentBidder = bidder;
    		this.timer.reset();
    		
    		return true;
    	}
    	
    	return false;
    }
    
    public ID getCurrentBidder() {
    	return this.currentBidder;
    }
    
    public double getCurrentBid() {
    	return this.currentBid;
    }

    private String generateId(ID id, long initialTime) throws NoSuchAlgorithmException {
        String s = String.valueOf(initialTime);
        s.concat(id.toString());

        return Utils.hash(s);
    }
    
    @Override
    public String toString() {
    	String res = "";
    	
    	res += "ID: " + this.id + "\n";
    	res += "Active: " + this.active + "\n";
    	res += "Item: \n" + this.item.toString();
    	res += "Initial Bid: " + String.format("%.02f", this.initialBid) + "$\n";
    	if (this.currentBidder == null) {
    		res += "Current Bid: No bid placed yet!\n";
        	res += "Current Bidder: No bid placed yet!\n";
    	} else {
    		res += "Current Bid: " + this.currentBid + "$\n";
        	res += "Current Bidder ID: " + this.currentBidder.toString() + "\n";
    	}
    	if (this.timer != null) {
    		res += "Timer: " + this.timer.seconds + "\n";
    	} else {
    		res += "Timer: " + this.time + "\n";
    	}
    	
    	
    	return res;
    }
}