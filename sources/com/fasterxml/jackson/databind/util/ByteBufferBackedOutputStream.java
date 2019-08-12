package com.fasterxml.jackson.databind.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class ByteBufferBackedOutputStream extends OutputStream {

    /* renamed from: _b */
    protected final ByteBuffer f3146_b;

    public ByteBufferBackedOutputStream(ByteBuffer buf) {
        this.f3146_b = buf;
    }

    public void write(int b) throws IOException {
        this.f3146_b.put((byte) b);
    }

    public void write(byte[] bytes, int off, int len) throws IOException {
        this.f3146_b.put(bytes, off, len);
    }
}
