package org.spongycastle.crypto.tls;

public class ByteQueue {
    private static final int DEFAULT_CAPACITY = 1024;
    private int available;
    private byte[] databuf;
    private int skipped;

    public static int nextTwoPow(int i) {
        int i2 = i | (i >> 1);
        int i3 = i2 | (i2 >> 2);
        int i4 = i3 | (i3 >> 4);
        int i5 = i4 | (i4 >> 8);
        return (i5 | (i5 >> 16)) + 1;
    }

    public ByteQueue() {
        this(1024);
    }

    public ByteQueue(int capacity) {
        this.skipped = 0;
        this.available = 0;
        this.databuf = new byte[capacity];
    }

    public void read(byte[] buf, int offset, int len, int skip) {
        if (buf.length - offset < len) {
            throw new IllegalArgumentException("Buffer size of " + buf.length + " is too small for a read of " + len + " bytes");
        } else if (this.available - skip < len) {
            throw new IllegalStateException("Not enough data to read");
        } else {
            System.arraycopy(this.databuf, this.skipped + skip, buf, offset, len);
        }
    }

    public void addData(byte[] buf, int off, int len) {
        if (this.skipped + this.available + len > this.databuf.length) {
            int desiredSize = nextTwoPow(this.available + len);
            if (desiredSize > this.databuf.length) {
                byte[] tmp = new byte[desiredSize];
                System.arraycopy(this.databuf, this.skipped, tmp, 0, this.available);
                this.databuf = tmp;
            } else {
                System.arraycopy(this.databuf, this.skipped, this.databuf, 0, this.available);
            }
            this.skipped = 0;
        }
        System.arraycopy(buf, off, this.databuf, this.skipped + this.available, len);
        this.available += len;
    }

    public void removeData(int i) {
        if (i > this.available) {
            throw new IllegalStateException("Cannot remove " + i + " bytes, only got " + this.available);
        }
        this.available -= i;
        this.skipped += i;
    }

    public void removeData(byte[] buf, int off, int len, int skip) {
        read(buf, off, len, skip);
        removeData(skip + len);
    }

    public byte[] removeData(int len, int skip) {
        byte[] buf = new byte[len];
        removeData(buf, 0, len, skip);
        return buf;
    }

    public int size() {
        return this.available;
    }

    public int available() {
        return this.available;
    }
}
