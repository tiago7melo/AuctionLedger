package kad.exceptions;

public class IDException extends Exception {
    public String msg;

    public String message(String msg) {
        return msg;
    }

    public String message() {
        return "ID length doesn't match expected byte size!";
    }
}
