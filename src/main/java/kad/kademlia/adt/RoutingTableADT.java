package kad.kademlia.adt;

import java.util.List;

import kad.kademlia.ID;
import kad.kademlia.network.Bucket;
import kad.kademlia.network.Node;

public interface RoutingTableADT {

    public int findBucket(ID id);
    public Bucket createBucket(ID id);
    public boolean update(Node node);  
    public void delete(Node node);
    public boolean contains(ID id);
    public List<Node> allNodes();
    public List<Node> closestNodes(ID target, int closestAmount);

}
