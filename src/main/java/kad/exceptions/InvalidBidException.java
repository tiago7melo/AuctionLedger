package kad.exceptions;

public class InvalidBidException extends Exception {
    public String message(int code) {
    	String msg;
    	switch (code) {
    	case 1: msg = "Can't place bid on own auction!";
    		break;
    	case 2: msg = "Invalid bid amount!";
    		break;
    	default: msg = "";
    		break;
    	}

        return msg;
    }
}

