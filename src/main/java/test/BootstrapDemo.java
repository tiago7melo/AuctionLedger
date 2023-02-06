package test;

import java.util.Scanner;
import kad.exceptions.InvalidPortException;
import kad.kademlia.ID;
import kad.kademlia.network.Host;
import kad.kademlia.network.Node;
import kad.kademlia.network.NodeInstance;

public class BootstrapDemo {
	public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			//System.out.println("Is this node a bootstrap? 1-yes 2-no");
			int isBootstrap = 1;
			
			//System.out.println("Choose a port to start the service.");
			int port = 1001;

			NodeInstance node;
				try {
					if(isBootstrap == 1) {
						System.out.println("Bootstrap node started");
						//node = new BootstrapNode(port, "localhost");
						node = new NodeInstance(port,"localhost",true);
					}
					else {
						System.out.println("Normal node started");
						Node bootstrapNodeInfo = new Node(new ID(0), new Host(1001,"localhost"));
						node = new NodeInstance(port,"localhost",bootstrapNodeInfo);
					}
					
				  	
				  	node.start();
				  	System.out.println("ID: " + node.getID().getInt());
				  	
	 	
				} catch (InvalidPortException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}

	}
}