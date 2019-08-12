package com.jumio.ale.swig;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ALEInputStream extends FilterInputStream {
    private ALERequest aleRequest = null;
    private boolean done = false;
    private byte[] ibuffer = new byte[512];
    private InputStream input;
    private byte[] obuffer;
    private int ofinish = 0;
    private int ostart = 0;

    private int getMoreData() throws IOException {
        int read;
        if (this.done) {
            return -1;
        }
        int readin = this.input.read(this.ibuffer);
        if (readin == -1) {
            this.done = true;
            try {
                this.aleRequest.finishResponse();
                return readin;
            } catch (Exception e) {
                throw new IOException(e);
            }
        } else {
            try {
                if (readin != this.ibuffer.length) {
                    byte[] temp = new byte[readin];
                    System.arraycopy(this.ibuffer, 0, temp, 0, readin);
                    this.obuffer = new byte[(temp.length + 32)];
                    read = this.aleRequest.updateResponse(temp, this.obuffer);
                } else {
                    this.obuffer = new byte[(this.ibuffer.length + 32)];
                    read = this.aleRequest.updateResponse(this.ibuffer, this.obuffer);
                }
                this.ostart = 0;
                if (this.obuffer == null) {
                    this.ofinish = 0;
                } else {
                    this.ofinish = read;
                }
                return this.ofinish;
            } catch (Exception e2) {
                this.obuffer = null;
                throw new IOException(e2);
            }
        }
    }

    public ALEInputStream(InputStream is, ALERequest aleRequest2) {
        super(is);
        this.input = is;
        this.aleRequest = aleRequest2;
        aleRequest2.initResponse();
    }

    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        if (this.ostart >= this.ofinish) {
            int i = 0;
            while (i == 0) {
                i = getMoreData();
            }
            if (i == -1) {
                return -1;
            }
        }
        if (len <= 0) {
            return 0;
        }
        int available = this.ofinish - this.ostart;
        if (len < available) {
            available = len;
        }
        if (b != null) {
            System.arraycopy(this.obuffer, this.ostart, b, off, available);
        }
        this.ostart += available;
        return available;
    }

    public long skip(long n) throws IOException {
        int available = this.ofinish - this.ostart;
        if (n > ((long) available)) {
            n = (long) available;
        }
        if (n < 0) {
            return 0;
        }
        this.ostart = (int) (((long) this.ostart) + n);
        return n;
    }

    public int available() throws IOException {
        return this.ofinish - this.ostart;
    }

    public void close() throws IOException {
        this.input.close();
        try {
            if (!this.done) {
                this.aleRequest.finishResponse();
            }
            this.ostart = 0;
            this.ofinish = 0;
        } catch (Exception ex) {
            throw new IOException(ex);
        }
    }

    public boolean markSupported() {
        return false;
    }
}
