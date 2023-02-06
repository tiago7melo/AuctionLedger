package auction;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Utils {
    public static final String ALGORITHM = "SHA-256";
    
    public static String hash(String input) throws NoSuchAlgorithmException {
    	MessageDigest md = MessageDigest.getInstance(ALGORITHM);
        byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64)
            hexString.insert(0, '0');
        
        return hexString.toString();
    }
}