package kad.kademlia.adt;

import java.util.List;

import kad.kademlia.ID;
import kad.kademlia.network.Node;


public interface BucketADT {
    boolean contains(ID id);
    boolean contains(Node node);
    List<ID> listIds();
    List<Node> listNodes();
    void add(Node node);
    void remove(Node node);
    void remove(ID id);
    void toFront(ID id);
    int size();
}

