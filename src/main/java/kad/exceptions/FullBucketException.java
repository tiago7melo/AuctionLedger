package kad.exceptions;

public class FullBucketException extends Exception {
    public String msg;

    public String message(String msg) {
        return msg;
    }

    public String message() {
        return "Bucket is full.";
    }
}
