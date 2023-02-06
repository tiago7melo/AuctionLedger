package test;

import java.security.NoSuchAlgorithmException;

import auction.Auction;
import kad.exceptions.InvalidPortException;
import kad.kademlia.network.BootstrapNode;
import kad.kademlia.network.NodeInstance;

public class Demo2 {
	public static void main(String[] args) {
		try {
			BootstrapNode node1 = new BootstrapNode(9001, "127.0.0.1");
			node1.start();
			NodeInstance node2 = new NodeInstance(9002, "127.0.0.1", node1);
			node2.start();
			Auction a = node2.createAuction(new Car(1, "Audi R8", "Audi"));
			System.out.println("\nAUCTION\n-------------------\n" + node2.getOwnAuction(a.getID()).toString());
			Auction added = node1.getAuctionHandler().subscribeAuction(node2.getAuctionHandler().toJSON("Simulation of a received auction from a broadcast", a), node2.getHost());
			System.out.println("Sleeping for 5 seconds...");
			Thread.sleep(5000);
			System.out.println("\nAUCTION\n-------------------\n" + node2.getOwnAuction(a.getID()).toString());
			node1.getAuctionHandler().placeBid(20.581, node1.getAuctionHandler().getSubscribedAuction(a.getID()));
			node2.getAuctionHandler().receiveBid(node1.getID(), 20.581, a.getID());
			System.out.println("Sleeping for 3 seconds...");
			Thread.sleep(3000);
			System.out.println("\nAUCTION\n-------------------\n" + node2.getOwnAuction(a.getID()).toString());
		} catch (InvalidPortException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
