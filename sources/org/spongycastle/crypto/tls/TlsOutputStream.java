package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.OutputStream;

class TlsOutputStream extends OutputStream {
    private byte[] buf = new byte[1];
    private TlsProtocol handler;

    TlsOutputStream(TlsProtocol handler2) {
        this.handler = handler2;
    }

    public void write(byte[] buf2, int offset, int len) throws IOException {
        this.handler.writeData(buf2, offset, len);
    }

    public void write(int arg0) throws IOException {
        this.buf[0] = (byte) arg0;
        write(this.buf, 0, 1);
    }

    public void close() throws IOException {
        this.handler.close();
    }

    public void flush() throws IOException {
        this.handler.flush();
    }
}
