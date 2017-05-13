package edu.uestc.msstudio.panorama.common.exception;

public class StorageException extends RuntimeException {

    private static final long serialVersionUID = -2121317019015662178L;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
