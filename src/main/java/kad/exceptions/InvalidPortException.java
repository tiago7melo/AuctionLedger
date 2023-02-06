package kad.exceptions;

public class InvalidPortException extends Exception {
    public String msg;

    public String message(String msg) {
        return msg;
    }

    public String message() {
        return "Invalid port number!";
    }
}
