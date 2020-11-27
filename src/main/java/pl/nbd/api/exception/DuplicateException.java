package pl.nbd.api.exception;

public class DuplicateException extends RuntimeException {

    public DuplicateException(String message) {
        super(message);
    }
}
