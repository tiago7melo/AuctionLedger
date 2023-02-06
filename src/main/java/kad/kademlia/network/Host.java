package kad.kademlia.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

import kad.kademlia.adt.HostADT;
import ledger.Settings;
import kad.exceptions.InvalidPortException;

public class Host implements HostADT {
    private int port;
    private InetAddress IP;

    public Host(int port, String ip) throws InvalidPortException {
		try {
			if (ip.equals("127.0.0.1") || ip.equals("localhost")) {
				this.IP = InetAddress.getLoopbackAddress();
			} else {
				if (InetAddress.getLocalHost().getHostAddress().equals(ip))
					this.IP = InetAddress.getLocalHost();
				else this.IP = InetAddress.getByName(ip);
			}
		} catch (UnknownHostException e) {
			System.err.println("Invalid host provided. Setting node address to loopback.");
			this.IP = InetAddress.getLoopbackAddress();
		}
		
        if (port <= 0 || port > 65535) {
            throw new InvalidPortException();
        }
        this.port = port;
    }
    
    public Host(String ip) {
		try {
			if (ip.equals("127.0.0.1") || ip.equals("localhost")) {
				this.IP = InetAddress.getLoopbackAddress();
			} else {
				if (InetAddress.getLocalHost().getHostAddress().equals(ip))
					this.IP = InetAddress.getLocalHost();
				else
					this.IP = InetAddress.getByName(ip);
			}
		} catch (UnknownHostException e) {
			System.err.println("Invalid host provided. Setting node address to loopback.");
			this.IP = InetAddress.getLoopbackAddress();
		}
		
        this.port = Settings.SERVER_PORT;
    }
    
    public Host() {
    	this.IP = InetAddress.getLoopbackAddress();
    	this.port = Settings.SERVER_PORT;
    }

    
    public InetAddress getIP() {
        return this.IP;
    }

    
    public int getPort() {
        return this.port;
    }
}