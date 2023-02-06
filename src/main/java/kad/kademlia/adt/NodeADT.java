package kad.kademlia.adt;

import java.math.BigInteger;
import java.util.Date;

import kad.kademlia.ID;
import kad.kademlia.network.Host;
import kad.kademlia.network.Node;
import kad.kademlia.network.RoutingTable;

public interface NodeADT {
    public Host getHost();
    public ID getID();
    public Date lastSeen();
    public void setSeen(Date date);
    public void setSeenNow();
    public void setID(ID id);
    public void incrementStaleCount();
    public void resetStaleCount();
    public int getStaleCount();
    public boolean isFresher(Node other);
    public RoutingTable getRoutingTable();
	BigInteger getIdAsInt();
}
