package org.spongycastle.crypto.digests;

import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.util.Memoable;
import org.spongycastle.util.Pack;

public abstract class GeneralDigest implements ExtendedDigest, Memoable {
    private static final int BYTE_LENGTH = 64;
    private long byteCount;
    private final byte[] xBuf;
    private int xBufOff;

    /* access modifiers changed from: protected */
    public abstract void processBlock();

    /* access modifiers changed from: protected */
    public abstract void processLength(long j);

    /* access modifiers changed from: protected */
    public abstract void processWord(byte[] bArr, int i);

    protected GeneralDigest() {
        this.xBuf = new byte[4];
        this.xBufOff = 0;
    }

    protected GeneralDigest(GeneralDigest t) {
        this.xBuf = new byte[4];
        copyIn(t);
    }

    protected GeneralDigest(byte[] encodedState) {
        this.xBuf = new byte[4];
        System.arraycopy(encodedState, 0, this.xBuf, 0, this.xBuf.length);
        this.xBufOff = Pack.bigEndianToInt(encodedState, 4);
        this.byteCount = Pack.bigEndianToLong(encodedState, 8);
    }

    /* access modifiers changed from: protected */
    public void copyIn(GeneralDigest t) {
        System.arraycopy(t.xBuf, 0, this.xBuf, 0, t.xBuf.length);
        this.xBufOff = t.xBufOff;
        this.byteCount = t.byteCount;
    }

    public void update(byte in) {
        byte[] bArr = this.xBuf;
        int i = this.xBufOff;
        this.xBufOff = i + 1;
        bArr[i] = in;
        if (this.xBufOff == this.xBuf.length) {
            processWord(this.xBuf, 0);
            this.xBufOff = 0;
        }
        this.byteCount++;
    }

    public void update(byte[] in, int inOff, int len) {
        int len2 = Math.max(0, len);
        int i = 0;
        if (this.xBufOff != 0) {
            while (true) {
                int i2 = i;
                if (i2 >= len2) {
                    i = i2;
                    break;
                }
                byte[] bArr = this.xBuf;
                int i3 = this.xBufOff;
                this.xBufOff = i3 + 1;
                i = i2 + 1;
                bArr[i3] = in[inOff + i2];
                if (this.xBufOff == 4) {
                    processWord(this.xBuf, 0);
                    this.xBufOff = 0;
                    break;
                }
            }
        }
        int limit = ((len2 - i) & -4) + i;
        while (i < limit) {
            processWord(in, inOff + i);
            i += 4;
        }
        int i4 = i;
        while (i4 < len2) {
            byte[] bArr2 = this.xBuf;
            int i5 = this.xBufOff;
            this.xBufOff = i5 + 1;
            int i6 = i4 + 1;
            bArr2[i5] = in[inOff + i4];
            i4 = i6;
        }
        this.byteCount += (long) len2;
    }

    public void finish() {
        long bitLength = this.byteCount << 3;
        update(ISOFileInfo.DATA_BYTES1);
        while (this.xBufOff != 0) {
            update(0);
        }
        processLength(bitLength);
        processBlock();
    }

    public void reset() {
        this.byteCount = 0;
        this.xBufOff = 0;
        for (int i = 0; i < this.xBuf.length; i++) {
            this.xBuf[i] = 0;
        }
    }

    /* access modifiers changed from: protected */
    public void populateState(byte[] state) {
        System.arraycopy(this.xBuf, 0, state, 0, this.xBufOff);
        Pack.intToBigEndian(this.xBufOff, state, 4);
        Pack.longToBigEndian(this.byteCount, state, 8);
    }

    public int getByteLength() {
        return 64;
    }
}
