package kad.kademlia.network;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import ledger.Settings;

public class Challenge {
	private int challenge_id;
	private long challenge;
	private int solution;
	
	private String address;
	private int port;
	
	// generate a challenge to send to another node
	public Challenge(int id, String ip, int port) {
		this.challenge_id = id;
		this.address = ip;
		this.port = port;
		
		this.solution = -1;
		
		Random gen = new Random();
		this.challenge = gen.nextLong();
	}
	
	// instantiate a challenge that was sent to this node
	public Challenge(int id, String ip, int port, long challenge) {
		this.challenge_id = id;
		this.address = ip;
		this.port = port;
		
		this.solution = -1;
		
		Random gen = new Random();
		this.challenge = challenge;
	}
	
	   public boolean leadingZerosCheck(String hash) {
	    	String prefixString = new String(new char[Settings.JOIN_SECURITY_CHALLENGE_DIFFICULTY]).replace('\0', '0');
	        if (!hash.substring(0, Settings.JOIN_SECURITY_CHALLENGE_DIFFICULTY).equals(prefixString)) {
	            return false;
	        }
	        
	        return true;
	   }
	   
		public String verify(int solution, long challenge) { 	
	    	
	    	String dataToHash = solution + ":" + challenge;
	          
	        MessageDigest digest = null;
	        byte[] bytes = null;
	        try {
	            digest = MessageDigest.getInstance("SHA-256");
	            bytes = digest.digest(dataToHash.getBytes("UTF-8"));
	        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
	            ex.printStackTrace();
	        }
	        
	        StringBuffer buffer = new StringBuffer();
	        for (byte b : bytes) {
	            buffer.append(String.format("%02x", b));
	        }
	        return buffer.toString();
	    }
		
		public int solve(long challenge) {
			int solution = 0;
			String hash = "";
			System.out.println("[bootstrap] solving security challenge " + challenge);
			
			do {
				solution++;
				hash = this.verify(solution, challenge);
			} while(!leadingZerosCheck(hash));
			
			System.out.println("[bootstrap] solved:" + hash);
			return solution;
		}
		
		public int getChallengeID() {
			return challenge_id;
		}

		public void setChallengeID(int challenge_id) {
			this.challenge_id = challenge_id;
		}

		public long getChallenge() {
			return challenge;
		}

		public void setChallenge(long challenge) {
			this.challenge = challenge;
		}

		public int getSolution() {
			return solution;
		}

		public void setSolution(int solution) {
			this.solution = solution;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}

}
