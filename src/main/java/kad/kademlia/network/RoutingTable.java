package kad.kademlia.network;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import kad.kademlia.ID;
import kad.kademlia.KeyComparator;
import ledger.Settings;

public class RoutingTable {
    private ID id;
    private List<Bucket> buckets;

    public RoutingTable(ID id) {
        this.id = id;
        buckets = new ArrayList<Bucket>();
        for(int i = 0; i < Settings.ID_LENGTH+1; i++) {
            //ID kadId = new ID(i);
            //buckets.add(createBucket(kadId));
            buckets.add(createBucket(i));
        }
    }   

    /**
     * finds the bucket id in the table corresponding to given id
     * @param id given id
     * @return corresponding bucket
     */
    
    public int findBucket(ID id) {
        int bucketID = this.id.getDistance(id);
    	/*System.out.println("[table] for id " + this.id.getInt().intValue() 
    			+ " for id check " + id.getInt().intValue() +
				  " found bucket " + bucketID);*/
        return bucketID < 0 ? 0 : bucketID;
    }

    public Bucket createBucket(int id) {
        return new Bucket(id);
    }

    
    public boolean contains(ID nodeID) {
        int bucket = findBucket(nodeID);
        return buckets.get(bucket).contains(nodeID);
    }

    
    public void delete(Node node) {
        ID nodeID = node.getID();
        int bucket = findBucket(nodeID);
        buckets.get(bucket).remove(nodeID);
    }

   
    public List<Node> allNodes() {
        List<Node> nodes = new ArrayList<>();
        for (Bucket bucket : this.buckets) {
            for (Node node : bucket.listNodes()) {
                nodes.add(node);
            }
        }

        return nodes;
    }

  
    public List<Node> closestNodes(ID target, int closestAmount) {
        TreeSet<Node> sortedNodes = new TreeSet<>(new KeyComparator(target)); 
        sortedNodes.addAll(this.allNodes());
        List<Node> result = new ArrayList<>(closestAmount);

        int count = 0;
        for (Node n : sortedNodes) {
            count++;
            result.add(n);
            if(count == closestAmount) break;
        }

        return result;
    }
   

    /* adds node if it wasn't there */
    /* updates lastSeen if it was */
    
    public void update(Node node) {
    	node.setSeen(new Date());
    	
    	/*System.out.println("[table] for id " + this.id.getInt().intValue() 
    			+ ", updating ID " + node.getID());*/
    	
        int bucket_id = this.findBucket(node.getID());
        Bucket bucket = buckets.get(bucket_id);
        
        if(bucket.contains(node.getID())) {
        	//System.out.println("[table] updating existing node");
            bucket.toFront(node.getID()); 
        }
        else {
        	//System.out.println("[table] found new contact node, adding");
            bucket.add(node);
        }
     
    }
}
