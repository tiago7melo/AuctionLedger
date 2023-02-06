package kad.kademlia.adt;

import java.net.InetAddress;

public interface HostADT {
    public InetAddress getIP();
    public int getPort();
}
