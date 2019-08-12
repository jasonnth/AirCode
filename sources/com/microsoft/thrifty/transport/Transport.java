package com.microsoft.thrifty.transport;

import java.io.Closeable;
import java.io.IOException;

public abstract class Transport implements Closeable {
    public abstract void flush() throws IOException;

    public abstract int read(byte[] bArr, int i, int i2) throws IOException;

    public abstract void write(byte[] bArr, int i, int i2) throws IOException;

    public void write(byte[] data) throws IOException {
        write(data, 0, data.length);
    }
}
