package kad.exceptions;

public class UnknownMessageException extends Exception {
    public String msg;

    public String message(String msg) {
        return msg;
    }

    public String message() {
        return "Unknown Error.";
    }
}
