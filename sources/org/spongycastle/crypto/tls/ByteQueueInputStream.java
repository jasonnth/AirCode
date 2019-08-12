package org.spongycastle.crypto.tls;

import java.io.InputStream;

public class ByteQueueInputStream extends InputStream {
    private ByteQueue buffer = new ByteQueue();

    public void addBytes(byte[] bytes) {
        this.buffer.addData(bytes, 0, bytes.length);
    }

    public int peek(byte[] buf) {
        int bytesToRead = Math.min(this.buffer.available(), buf.length);
        this.buffer.read(buf, 0, bytesToRead, 0);
        return bytesToRead;
    }

    public int read() {
        if (this.buffer.available() == 0) {
            return -1;
        }
        return this.buffer.removeData(1, 0)[0] & 255;
    }

    public int read(byte[] b) {
        return read(b, 0, b.length);
    }

    public int read(byte[] b, int off, int len) {
        int bytesToRead = Math.min(this.buffer.available(), len);
        this.buffer.removeData(b, off, bytesToRead, 0);
        return bytesToRead;
    }

    public long skip(long n) {
        int bytesToRemove = Math.min((int) n, this.buffer.available());
        this.buffer.removeData(bytesToRemove);
        return (long) bytesToRemove;
    }

    public int available() {
        return this.buffer.available();
    }

    public void close() {
    }
}
