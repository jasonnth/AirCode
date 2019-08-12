package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Pack;

public class SipHash implements Mac {

    /* renamed from: c */
    protected final int f6761c;

    /* renamed from: d */
    protected final int f6762d;

    /* renamed from: k0 */
    protected long f6763k0;

    /* renamed from: k1 */
    protected long f6764k1;

    /* renamed from: m */
    protected long f6765m;

    /* renamed from: v0 */
    protected long f6766v0;

    /* renamed from: v1 */
    protected long f6767v1;

    /* renamed from: v2 */
    protected long f6768v2;

    /* renamed from: v3 */
    protected long f6769v3;
    protected int wordCount;
    protected int wordPos;

    public SipHash() {
        this.f6765m = 0;
        this.wordPos = 0;
        this.wordCount = 0;
        this.f6761c = 2;
        this.f6762d = 4;
    }

    public SipHash(int c, int d) {
        this.f6765m = 0;
        this.wordPos = 0;
        this.wordCount = 0;
        this.f6761c = c;
        this.f6762d = d;
    }

    public String getAlgorithmName() {
        return "SipHash-" + this.f6761c + "-" + this.f6762d;
    }

    public int getMacSize() {
        return 8;
    }

    public void init(CipherParameters params) throws IllegalArgumentException {
        if (!(params instanceof KeyParameter)) {
            throw new IllegalArgumentException("'params' must be an instance of KeyParameter");
        }
        byte[] key = ((KeyParameter) params).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("'params' must be a 128-bit key");
        }
        this.f6763k0 = Pack.littleEndianToLong(key, 0);
        this.f6764k1 = Pack.littleEndianToLong(key, 8);
        reset();
    }

    public void update(byte input) throws IllegalStateException {
        this.f6765m >>>= 8;
        this.f6765m |= (((long) input) & 255) << 56;
        int i = this.wordPos + 1;
        this.wordPos = i;
        if (i == 8) {
            processMessageWord();
            this.wordPos = 0;
        }
    }

    public void update(byte[] input, int offset, int length) throws DataLengthException, IllegalStateException {
        int i = 0;
        int fullWords = length & -8;
        if (this.wordPos == 0) {
            while (i < fullWords) {
                this.f6765m = Pack.littleEndianToLong(input, offset + i);
                processMessageWord();
                i += 8;
            }
            while (i < length) {
                this.f6765m >>>= 8;
                this.f6765m |= (((long) input[offset + i]) & 255) << 56;
                i++;
            }
            this.wordPos = length - fullWords;
            return;
        }
        int bits = this.wordPos << 3;
        while (i < fullWords) {
            long n = Pack.littleEndianToLong(input, offset + i);
            this.f6765m = (n << bits) | (this.f6765m >>> (-bits));
            processMessageWord();
            this.f6765m = n;
            i += 8;
        }
        while (i < length) {
            this.f6765m >>>= 8;
            this.f6765m |= (((long) input[offset + i]) & 255) << 56;
            int i2 = this.wordPos + 1;
            this.wordPos = i2;
            if (i2 == 8) {
                processMessageWord();
                this.wordPos = 0;
            }
            i++;
        }
    }

    public long doFinal() throws DataLengthException, IllegalStateException {
        this.f6765m >>>= (7 - this.wordPos) << 3;
        this.f6765m >>>= 8;
        this.f6765m |= (((long) ((this.wordCount << 3) + this.wordPos)) & 255) << 56;
        processMessageWord();
        this.f6768v2 ^= 255;
        applySipRounds(this.f6762d);
        long result = ((this.f6766v0 ^ this.f6767v1) ^ this.f6768v2) ^ this.f6769v3;
        reset();
        return result;
    }

    public int doFinal(byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        Pack.longToLittleEndian(doFinal(), out, outOff);
        return 8;
    }

    public void reset() {
        this.f6766v0 = this.f6763k0 ^ 8317987319222330741L;
        this.f6767v1 = this.f6764k1 ^ 7237128888997146477L;
        this.f6768v2 = this.f6763k0 ^ 7816392313619706465L;
        this.f6769v3 = this.f6764k1 ^ 8387220255154660723L;
        this.f6765m = 0;
        this.wordPos = 0;
        this.wordCount = 0;
    }

    /* access modifiers changed from: protected */
    public void processMessageWord() {
        this.wordCount++;
        this.f6769v3 ^= this.f6765m;
        applySipRounds(this.f6761c);
        this.f6766v0 ^= this.f6765m;
    }

    /* access modifiers changed from: protected */
    public void applySipRounds(int n) {
        long r0 = this.f6766v0;
        long r1 = this.f6767v1;
        long r2 = this.f6768v2;
        long r3 = this.f6769v3;
        for (int r = 0; r < n; r++) {
            long r02 = r0 + r1;
            long r22 = r2 + r3;
            long r12 = rotateLeft(r1, 13) ^ r02;
            long r32 = rotateLeft(r3, 16) ^ r22;
            long r23 = r22 + r12;
            r0 = rotateLeft(r02, 32) + r32;
            r1 = rotateLeft(r12, 17) ^ r23;
            r3 = rotateLeft(r32, 21) ^ r0;
            r2 = rotateLeft(r23, 32);
        }
        this.f6766v0 = r0;
        this.f6767v1 = r1;
        this.f6768v2 = r2;
        this.f6769v3 = r3;
    }

    protected static long rotateLeft(long x, int n) {
        return (x << n) | (x >>> (-n));
    }
}
