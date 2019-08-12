package com.jumio.ale.swig;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ALEOutputStream extends FilterOutputStream {
    private ALERequest aleRequest;
    private boolean closed;
    private byte[] obuffer;
    private int obufferLen;
    private OutputStream outputStream;

    public ALEOutputStream(OutputStream outputStream2, ALERequest aleRequest2, int clearLength) throws Exception {
        this(outputStream2, aleRequest2, null, clearLength);
    }

    public ALEOutputStream(OutputStream outputStream2, ALERequest aleRequest2, ALEHeader aleHeader, int clearLength) throws Exception {
        super(outputStream2);
        this.aleRequest = null;
        this.obufferLen = 0;
        this.closed = false;
        this.outputStream = outputStream2;
        this.aleRequest = aleRequest2;
        this.obuffer = new byte[aleRequest2.calculateRequestInit(aleHeader, clearLength)];
        this.obufferLen = aleRequest2.initRequest(aleHeader, clearLength, this.obuffer);
        outputStream2.write(this.obuffer, 0, this.obufferLen);
    }

    public void write(byte[] b, int offset, int count) throws IOException {
        if (offset == 0 && count == b.length) {
            write(b);
            return;
        }
        byte[] sub = new byte[count];
        System.arraycopy(b, offset, sub, 0, count);
        write(sub);
    }

    public void write(byte[] b) throws IOException {
        this.obuffer = new byte[this.aleRequest.calculateRequestUpdate(b.length)];
        try {
            this.obufferLen = this.aleRequest.updateRequest(b, this.obuffer);
            if (this.obuffer != null) {
                this.outputStream.write(this.obuffer, 0, this.obufferLen);
                this.obuffer = null;
                this.obufferLen = 0;
            }
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public void flush() throws IOException {
        if (this.obuffer != null) {
            this.outputStream.write(this.obuffer, 0, this.obufferLen);
            this.obuffer = null;
        }
        this.outputStream.flush();
    }

    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            try {
                this.obuffer = new byte[this.aleRequest.calculateRequestFinish()];
                this.obufferLen = this.aleRequest.finishRequest(this.obuffer);
            } catch (Exception e) {
                this.obuffer = null;
                this.obufferLen = 0;
            }
            try {
                flush();
            } catch (IOException e2) {
            }
            this.outputStream.close();
        }
    }
}
