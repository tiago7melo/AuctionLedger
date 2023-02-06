package kad.exceptions;

public class KadServerDownException extends Exception {
    public String msg;

    public String message(String msg) {
        return msg;
    }

    public String message() {
        return "Kad Server is not running.";
    }
}
