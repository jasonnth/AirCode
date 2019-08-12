package com.microsoft.thrifty.transport;

import java.io.IOException;
import okio.Buffer;

public class BufferTransport extends Transport {

    /* renamed from: b */
    public final Buffer f3561b;

    public BufferTransport() {
        this(new Buffer());
    }

    public BufferTransport(Buffer buffer) {
        this.f3561b = buffer;
    }

    public int read(byte[] buffer, int offset, int count) throws IOException {
        return this.f3561b.read(buffer, offset, count);
    }

    public void write(byte[] buffer, int offset, int count) throws IOException {
        this.f3561b.write(buffer, offset, count);
    }

    public void flush() throws IOException {
        this.f3561b.flush();
    }

    public void close() throws IOException {
        this.f3561b.close();
    }
}
