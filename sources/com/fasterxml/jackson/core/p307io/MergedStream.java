package com.fasterxml.jackson.core.p307io;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.fasterxml.jackson.core.io.MergedStream */
public final class MergedStream extends InputStream {

    /* renamed from: _b */
    private byte[] f3142_b;
    private final IOContext _ctxt;
    private final int _end;
    private final InputStream _in;
    private int _ptr;

    public MergedStream(IOContext ctxt, InputStream in, byte[] buf, int start, int end) {
        this._ctxt = ctxt;
        this._in = in;
        this.f3142_b = buf;
        this._ptr = start;
        this._end = end;
    }

    public int available() throws IOException {
        if (this.f3142_b != null) {
            return this._end - this._ptr;
        }
        return this._in.available();
    }

    public void close() throws IOException {
        _free();
        this._in.close();
    }

    public void mark(int readlimit) {
        if (this.f3142_b == null) {
            this._in.mark(readlimit);
        }
    }

    public boolean markSupported() {
        return this.f3142_b == null && this._in.markSupported();
    }

    public int read() throws IOException {
        if (this.f3142_b == null) {
            return this._in.read();
        }
        byte[] bArr = this.f3142_b;
        int i = this._ptr;
        this._ptr = i + 1;
        int c = bArr[i] & 255;
        if (this._ptr < this._end) {
            return c;
        }
        _free();
        return c;
    }

    public int read(byte[] b) throws IOException {
        return read(b, 0, b.length);
    }

    public int read(byte[] b, int off, int len) throws IOException {
        if (this.f3142_b == null) {
            return this._in.read(b, off, len);
        }
        int avail = this._end - this._ptr;
        if (len > avail) {
            len = avail;
        }
        System.arraycopy(this.f3142_b, this._ptr, b, off, len);
        this._ptr += len;
        if (this._ptr >= this._end) {
            _free();
        }
        return len;
    }

    public void reset() throws IOException {
        if (this.f3142_b == null) {
            this._in.reset();
        }
    }

    public long skip(long n) throws IOException {
        long count = 0;
        if (this.f3142_b != null) {
            int amount = this._end - this._ptr;
            if (((long) amount) > n) {
                this._ptr += (int) n;
                return n;
            }
            _free();
            count = 0 + ((long) amount);
            n -= (long) amount;
        }
        if (n > 0) {
            count += this._in.skip(n);
        }
        return count;
    }

    private void _free() {
        byte[] buf = this.f3142_b;
        if (buf != null) {
            this.f3142_b = null;
            if (this._ctxt != null) {
                this._ctxt.releaseReadIOBuffer(buf);
            }
        }
    }
}
