package org.apache.sanselan;

public class ImageWriteException extends SanselanException {
    public ImageWriteException(String s) {
        super(s);
    }

    public ImageWriteException(String s, Exception e) {
        super(s, e);
    }
}
