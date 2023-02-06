package kad.exceptions;

public class SubscriptionException extends Exception {
	public String message() {
        return "The auction was already subscribed.";
    }
}
