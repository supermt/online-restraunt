package edu.uestc.msstudio.panorama.common.exception;

public class StorageFileNotFoundException extends StorageException {
    private static final long serialVersionUID = 1155024247854487327L;

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}