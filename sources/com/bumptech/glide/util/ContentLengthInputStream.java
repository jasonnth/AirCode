package com.bumptech.glide.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ContentLengthInputStream extends FilterInputStream {
    private final long contentLength;
    private int readSoFar;

    public static InputStream obtain(InputStream other, long contentLength2) {
        return new ContentLengthInputStream(other, contentLength2);
    }

    ContentLengthInputStream(InputStream in, long contentLength2) {
        super(in);
        this.contentLength = contentLength2;
    }

    public synchronized int available() throws IOException {
        return (int) Math.max(this.contentLength - ((long) this.readSoFar), (long) this.in.available());
    }

    public synchronized int read() throws IOException {
        return checkReadSoFarOrThrow(super.read());
    }

    public int read(byte[] buffer) throws IOException {
        return read(buffer, 0, buffer.length);
    }

    public synchronized int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        return checkReadSoFarOrThrow(super.read(buffer, byteOffset, byteCount));
    }

    private int checkReadSoFarOrThrow(int read) throws IOException {
        if (read >= 0) {
            this.readSoFar += read;
        } else if (this.contentLength - ((long) this.readSoFar) > 0) {
            throw new IOException("Failed to read all expected data, expected: " + this.contentLength + ", but read: " + this.readSoFar);
        }
        return read;
    }
}
