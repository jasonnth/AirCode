package com.microsoft.thrifty.util;

import java.io.ByteArrayOutputStream;

public class UnsafeByteArrayOutputStream extends ByteArrayOutputStream {
    public UnsafeByteArrayOutputStream(int count) {
        super(count);
    }

    public byte[] getBuffer() {
        return this.buf;
    }
}
