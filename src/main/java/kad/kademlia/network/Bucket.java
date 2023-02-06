package kad.kademlia.network;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kad.kademlia.ID;

public class Bucket {
    private final int id;
    /* needed to know what's at the top and bottom
    so we know what node to get rid of when bucket is full */
    private List<Integer> ids;
    private Map<Integer,Node> nodes;

    public final static int BUCKET_SIZE = 20;

    public Bucket(int id) {
        this.id = id;
        this.ids = new ArrayList<Integer>();
        this.nodes = new ConcurrentHashMap<>();
    }

    
    public void add(Node node) {
    	
        if(this.ids.size() == BUCKET_SIZE) {
        	int idtoremove = ids.get(BUCKET_SIZE-1);
        	ids.remove(BUCKET_SIZE-1);
        	nodes.remove(idtoremove);
        }
        
        ids.add(0,node.getID().getInt().intValue());
        nodes.put(node.getID().getInt().intValue(),node);
        
    }
   
    public void remove(ID id) {
        ids.remove(id.getInt().intValue());
        nodes.remove(id.getInt().intValue());
    }

   
    public List<Integer> listIds() {
        return this.ids;
    }

    
    public List<Node> listNodes() {
        return new ArrayList<Node>(nodes.values());
    }

    
    public boolean contains(ID id) {
        for(int idtmp : ids) {
        	if(idtmp == id.getInt().intValue()) {
        		return true;
        	}
        }
        
        return false;
    }

    
    public void toFront(ID id) {
    	nodes.get(id.getInt().intValue()).setSeenNow();
    	nodes.get(id.getInt().intValue()).resetStaleCount();
        ids.remove(id);
        ids.add(0,id.getInt().intValue());
    }

    
    public int size() {
        return this.ids.size();
    }
    
}

