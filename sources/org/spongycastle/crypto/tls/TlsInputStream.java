package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;

class TlsInputStream extends InputStream {
    private byte[] buf = new byte[1];
    private TlsProtocol handler = null;

    TlsInputStream(TlsProtocol handler2) {
        this.handler = handler2;
    }

    public int available() throws IOException {
        return this.handler.applicationDataAvailable();
    }

    public int read(byte[] buf2, int offset, int len) throws IOException {
        return this.handler.readApplicationData(buf2, offset, len);
    }

    public int read() throws IOException {
        if (read(this.buf) < 0) {
            return -1;
        }
        return this.buf[0] & 255;
    }

    public void close() throws IOException {
        this.handler.close();
    }
}
