package com.jumio.persistence;

public interface PersistenceTarget {
    boolean delete() throws PersistenceException;

    byte[] readBlob() throws PersistenceException;

    long storeBlob(byte[] bArr) throws PersistenceException;
}
