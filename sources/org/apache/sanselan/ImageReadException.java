package org.apache.sanselan;

public class ImageReadException extends SanselanException {
    public ImageReadException(String s) {
        super(s);
    }

    public ImageReadException(String s, Exception e) {
        super(s, e);
    }
}
