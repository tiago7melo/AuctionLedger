package kad.exceptions;

public class RoutingException extends Exception {
    public String msg;

    public String message(String msg) {
        return msg;
    }

    public String message() {
        return "RoutingError: Bootstrap node did not respond.";
    }
}
