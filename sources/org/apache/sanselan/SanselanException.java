package org.apache.sanselan;

public class SanselanException extends Exception {
    public SanselanException(String s) {
        super(s);
    }

    public SanselanException(String s, Exception e) {
        super(s, e);
    }
}
