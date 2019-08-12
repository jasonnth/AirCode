package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.OutputStream;

public class ByteQueueOutputStream extends OutputStream {
    private ByteQueue buffer = new ByteQueue();

    public ByteQueue getBuffer() {
        return this.buffer;
    }

    public void write(int b) throws IOException {
        this.buffer.addData(new byte[]{(byte) b}, 0, 1);
    }

    public void write(byte[] b, int off, int len) throws IOException {
        this.buffer.addData(b, off, len);
    }
}
