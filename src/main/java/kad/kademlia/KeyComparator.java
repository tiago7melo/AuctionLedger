package kad.kademlia;

import java.math.BigInteger;
import java.util.Comparator;

import kad.kademlia.network.Node;

public class KeyComparator implements Comparator<Node> {

    private final BigInteger id;

    public KeyComparator(ID id) {
        this.id = id.getInt();
    }

    @Override
    public int compare(Node n1, Node n2)
    {
        BigInteger b1 = n1.getID().getInt();
        BigInteger b2 = n2.getID().getInt();

        b1 = b1.xor(id);
        b2 = b2.xor(id);

        return b1.abs().compareTo(b2.abs());
    }
    
}
