package com.jumio.persistence;

public class PersistenceException extends Exception {
    public PersistenceException(Exception ex) {
        super(ex);
    }

    public PersistenceException(String s) {
        super(s);
    }
}
