package kad.kademlia;

import java.math.BigInteger;
import ledger.Settings;

public class ID {

    private byte id[];
    
    public ID(byte[] id) {
        this.id = id;
    }

    public ID(Integer i) {
    	// this.id = ByteBuffer.allocate(Settings.ID_BYTES).putInt(i).array();
        BigInteger bigInt = BigInteger.valueOf(i);
        this.id = bigInt.toByteArray();
    }
    
    public byte[] getBytes() {
        return this.id;
    }
    
    
    // TODO: maybe deprecate, use BigInteger.xor
    public ID XOR(ID other) {
        byte[] result = new byte[Settings.ID_BYTES];
        byte[] tid = this.id;
        byte[] oid = other.id;
        
        int size;
        if(tid.length > oid.length)
        	size = tid.length;
        else
        	size = oid.length;
      
        for (int i = 0; i < size; i++) {

            result[i] =  (byte) (tid[i] ^ oid[i]);
        }
       
        return new ID(result);
    }


    public int firstSetBit() {
        int prefixLength = 0;
        for (byte b : this.id) {
            if (b == 0) 
                prefixLength += 8; 
            else {
                int count = 0;
                for (int i = 7; i >= 0; i--) {
                    if ((b & (1 << i)) == 0)
                        count++; 
                    else  
                        break;
                }
                prefixLength += count;
                break;
            }
        }
        return prefixLength;
    }

    public int getDistance(ID other) {
    	// Settings.ID_LENGTH - this.XOR(other).firstSetBit()
    	//System.out.println(this.XOR(other).firstSetBit());
        //return this.XOR(other).firstSetBit();
    	
    	BigInteger b1 = new BigInteger(1,this.id);
    	BigInteger b2 = new BigInteger(1,other.id);
    	return b1.xor(b2).intValue();
    }
    
    public BigInteger getInt() {
    	return new BigInteger(1, this.getBytes());
    }
    
    @Override
    public String toString() {
    	return this.getInt().toString();
    }
}
