package kad.exceptions;

public class ContentNotFoundException extends Exception {
    public String msg;

    public String message(String msg) {
        return msg;
    }

    public String message() {
        return "No Value was found.";
    }
}
