package kad.kademlia.network;

import java.math.BigInteger;
import java.util.Date;
import kad.kademlia.ID;
import kad.kademlia.adt.NodeADT;

public class Node implements NodeADT {
    private ID id;
    private Host conn;
    private Date lastSeen;
    private RoutingTable table;
    private int staleCount;

    public Node(ID id, Host conn) {
        this.id = id;
        this.conn = conn;
        this.staleCount = 0;
        this.table = new RoutingTable(this.id);
    }
    
    public Node(Host conn) {
    	this.id = null;
        this.conn = conn;
        this.staleCount = 0;
        
        //defaults to 0 on bootstrap node, changed when ID is gotten for bootstrap on others
        this.table = new RoutingTable(new ID(0));
    }
    
    
    public void startTable(RoutingTable table) {
    	this.table = table;
    }
    @Override
    public Host getHost() {
        return this.conn;
    }

    public ID getID() {
    	return this.id;
    }
    
    @Override
    public void setID(ID id) {
        this.id = id;
    }
    
    @Override
    public BigInteger getIdAsInt() {
        return this.id.getInt();
    }
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    /* functions for Node class to work as an entry in routing table */
    //TODO: if needed, extract this to a higher level Contact class

    @Override
    public Date lastSeen() {
        return this.lastSeen;
    }

    @Override
    public void setSeen(Date date) {
        this.lastSeen = date;
    }

    @Override
    public void setSeenNow() {
        this.lastSeen = new Date();
    }

    @Override
    public void incrementStaleCount() {
        this.staleCount++;
    }

    @Override
    public void resetStaleCount() {
        this.staleCount = 0;
    }
    
    @Override
    public int getStaleCount() {
    	return this.staleCount;
    }

    @Override
    public boolean isFresher(Node other) {
        return this.lastSeen.after(other.lastSeen);
    }
    
    @Override
    public RoutingTable getRoutingTable() {
    	return this.table;
    }
}




