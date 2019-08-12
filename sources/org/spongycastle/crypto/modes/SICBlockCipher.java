package org.spongycastle.crypto.modes;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.SkippingStreamCipher;
import org.spongycastle.crypto.StreamBlockCipher;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Pack;

public class SICBlockCipher extends StreamBlockCipher implements SkippingStreamCipher {

    /* renamed from: IV */
    private byte[] f6796IV = new byte[this.blockSize];
    private final int blockSize = this.cipher.getBlockSize();
    private int byteCount = 0;
    private final BlockCipher cipher;
    private byte[] counter = new byte[this.blockSize];
    private byte[] counterOut = new byte[this.blockSize];

    public SICBlockCipher(BlockCipher c) {
        super(c);
        this.cipher = c;
    }

    public void init(boolean forEncryption, CipherParameters params) throws IllegalArgumentException {
        int maxCounterSize = 8;
        if (params instanceof ParametersWithIV) {
            ParametersWithIV ivParam = (ParametersWithIV) params;
            this.f6796IV = Arrays.clone(ivParam.getIV());
            if (this.blockSize < this.f6796IV.length) {
                throw new IllegalArgumentException("CTR/SIC mode requires IV no greater than: " + this.blockSize + " bytes.");
            }
            if (8 > this.blockSize / 2) {
                maxCounterSize = this.blockSize / 2;
            }
            if (this.blockSize - this.f6796IV.length > maxCounterSize) {
                throw new IllegalArgumentException("CTR/SIC mode requires IV of at least: " + (this.blockSize - maxCounterSize) + " bytes.");
            }
            if (ivParam.getParameters() != null) {
                this.cipher.init(true, ivParam.getParameters());
            }
            reset();
            return;
        }
        throw new IllegalArgumentException("CTR/SIC mode requires ParametersWithIV");
    }

    public String getAlgorithmName() {
        return this.cipher.getAlgorithmName() + "/SIC";
    }

    public int getBlockSize() {
        return this.cipher.getBlockSize();
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        processBytes(in, inOff, this.blockSize, out, outOff);
        return this.blockSize;
    }

    /* access modifiers changed from: protected */
    public byte calculateByte(byte in) throws DataLengthException, IllegalStateException {
        if (this.byteCount == 0) {
            this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
            byte[] bArr = this.counterOut;
            int i = this.byteCount;
            this.byteCount = i + 1;
            return (byte) (bArr[i] ^ in);
        }
        byte[] bArr2 = this.counterOut;
        int i2 = this.byteCount;
        this.byteCount = i2 + 1;
        byte b = (byte) (bArr2[i2] ^ in);
        if (this.byteCount != this.counter.length) {
            return b;
        }
        this.byteCount = 0;
        incrementCounterAt(0);
        checkCounter();
        return b;
    }

    private void checkCounter() {
        if (this.f6796IV.length < this.blockSize) {
            for (int i = 0; i != this.f6796IV.length; i++) {
                if (this.counter[i] != this.f6796IV[i]) {
                    throw new IllegalStateException("Counter in CTR/SIC mode out of range.");
                }
            }
        }
    }

    private void incrementCounterAt(int pos) {
        byte b;
        int i = this.counter.length - pos;
        do {
            i--;
            if (i >= 0) {
                byte[] bArr = this.counter;
                b = (byte) (bArr[i] + 1);
                bArr[i] = b;
            } else {
                return;
            }
        } while (b == 0);
    }

    private void incrementCounter(int offSet) {
        byte old = this.counter[this.counter.length - 1];
        byte[] bArr = this.counter;
        int length = this.counter.length - 1;
        bArr[length] = (byte) (bArr[length] + offSet);
        if (old != 0 && this.counter[this.counter.length - 1] < old) {
            incrementCounterAt(1);
        }
    }

    private void decrementCounterAt(int pos) {
        byte b;
        int i = this.counter.length - pos;
        do {
            i--;
            if (i >= 0) {
                byte[] bArr = this.counter;
                b = (byte) (bArr[i] - 1);
                bArr[i] = b;
            } else {
                return;
            }
        } while (b == -1);
    }

    private void adjustCounter(long n) {
        if (n >= 0) {
            long numBlocks = (((long) this.byteCount) + n) / ((long) this.blockSize);
            long rem = numBlocks;
            if (rem > 255) {
                for (int i = 5; i >= 1; i--) {
                    long diff = 1 << (i * 8);
                    while (rem >= diff) {
                        incrementCounterAt(i);
                        rem -= diff;
                    }
                }
            }
            incrementCounter((int) rem);
            this.byteCount = (int) ((((long) this.byteCount) + n) - (((long) this.blockSize) * numBlocks));
            return;
        }
        long numBlocks2 = ((-n) - ((long) this.byteCount)) / ((long) this.blockSize);
        long rem2 = numBlocks2;
        if (rem2 > 255) {
            for (int i2 = 5; i2 >= 1; i2--) {
                long diff2 = 1 << (i2 * 8);
                while (rem2 > diff2) {
                    decrementCounterAt(i2);
                    rem2 -= diff2;
                }
            }
        }
        for (long i3 = 0; i3 != rem2; i3++) {
            decrementCounterAt(0);
        }
        int gap = (int) (((long) this.byteCount) + n + (((long) this.blockSize) * numBlocks2));
        if (gap >= 0) {
            this.byteCount = 0;
            return;
        }
        decrementCounterAt(0);
        this.byteCount = this.blockSize + gap;
    }

    public void reset() {
        Arrays.fill(this.counter, 0);
        System.arraycopy(this.f6796IV, 0, this.counter, 0, this.f6796IV.length);
        this.cipher.reset();
        this.byteCount = 0;
    }

    public long skip(long numberOfBytes) {
        adjustCounter(numberOfBytes);
        checkCounter();
        this.cipher.processBlock(this.counter, 0, this.counterOut, 0);
        return numberOfBytes;
    }

    public long seekTo(long position) {
        reset();
        return skip(position);
    }

    public long getPosition() {
        int v;
        byte[] res = new byte[this.counter.length];
        System.arraycopy(this.counter, 0, res, 0, res.length);
        for (int i = res.length - 1; i >= 1; i--) {
            if (i < this.f6796IV.length) {
                v = (res[i] & 255) - (this.f6796IV[i] & 255);
            } else {
                v = res[i] & 255;
            }
            if (v < 0) {
                int i2 = i - 1;
                res[i2] = (byte) (res[i2] - 1);
                v += 256;
            }
            res[i] = (byte) v;
        }
        return (Pack.bigEndianToLong(res, res.length - 8) * ((long) this.blockSize)) + ((long) this.byteCount);
    }
}
