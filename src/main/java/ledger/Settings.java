package ledger;

public class Settings {
	public static int POW_DIFFICULTY = 5;
	public static int BLOCK_SIZE = 3;
	public static String genesisHash = "0000000000000000000000000000000000000000000000000000000000000000";
	
    public static final int ID_LENGTH = 128;
    public final static int ID_BYTES = ID_LENGTH / 8;
    
    public final static int SERVER_PORT = 5236;
	
    
    public final static int STALE_THRESHOLD = 1;
    public final static int CHECK_ALIVE_PERIOD_MILLIS = 300000;
    public final static int REPUBLISH_PERIOD_MILLIS = 900000;
    
    
    public final static int JOIN_CLOSEST_NODES_ALPHA = 2;
    public final static int JOIN_SECURITY_CHALLENGE_DIFFICULTY = 2;
}
