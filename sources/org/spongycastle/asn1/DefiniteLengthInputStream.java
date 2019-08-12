package org.spongycastle.asn1;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.util.p333io.Streams;

class DefiniteLengthInputStream extends LimitedInputStream {
    private static final byte[] EMPTY_BYTES = new byte[0];
    private final int _originalLength;
    private int _remaining;

    DefiniteLengthInputStream(InputStream in, int length) {
        super(in, length);
        if (length < 0) {
            throw new IllegalArgumentException("negative lengths not allowed");
        }
        this._originalLength = length;
        this._remaining = length;
        if (length == 0) {
            setParentEofDetect(true);
        }
    }

    /* access modifiers changed from: 0000 */
    public int getRemaining() {
        return this._remaining;
    }

    public int read() throws IOException {
        if (this._remaining == 0) {
            return -1;
        }
        int b = this._in.read();
        if (b < 0) {
            throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
        }
        int i = this._remaining - 1;
        this._remaining = i;
        if (i != 0) {
            return b;
        }
        setParentEofDetect(true);
        return b;
    }

    public int read(byte[] buf, int off, int len) throws IOException {
        if (this._remaining == 0) {
            return -1;
        }
        int numRead = this._in.read(buf, off, Math.min(len, this._remaining));
        if (numRead < 0) {
            throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
        }
        int i = this._remaining - numRead;
        this._remaining = i;
        if (i != 0) {
            return numRead;
        }
        setParentEofDetect(true);
        return numRead;
    }

    /* access modifiers changed from: 0000 */
    public byte[] toByteArray() throws IOException {
        if (this._remaining == 0) {
            return EMPTY_BYTES;
        }
        byte[] bytes = new byte[this._remaining];
        int readFully = this._remaining - Streams.readFully(this._in, bytes);
        this._remaining = readFully;
        if (readFully != 0) {
            throw new EOFException("DEF length " + this._originalLength + " object truncated by " + this._remaining);
        }
        setParentEofDetect(true);
        return bytes;
    }
}
