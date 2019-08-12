package org.spongycastle.crypto.macs;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.Mac;
import org.spongycastle.crypto.generators.Poly1305KeyGenerator;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.util.Pack;

public class Poly1305 implements Mac {
    private static final int BLOCK_SIZE = 16;
    private final BlockCipher cipher;
    private final byte[] currentBlock;
    private int currentBlockOffset;

    /* renamed from: h0 */
    private int f6743h0;

    /* renamed from: h1 */
    private int f6744h1;

    /* renamed from: h2 */
    private int f6745h2;

    /* renamed from: h3 */
    private int f6746h3;

    /* renamed from: h4 */
    private int f6747h4;

    /* renamed from: k0 */
    private int f6748k0;

    /* renamed from: k1 */
    private int f6749k1;

    /* renamed from: k2 */
    private int f6750k2;

    /* renamed from: k3 */
    private int f6751k3;

    /* renamed from: r0 */
    private int f6752r0;

    /* renamed from: r1 */
    private int f6753r1;

    /* renamed from: r2 */
    private int f6754r2;

    /* renamed from: r3 */
    private int f6755r3;

    /* renamed from: r4 */
    private int f6756r4;

    /* renamed from: s1 */
    private int f6757s1;

    /* renamed from: s2 */
    private int f6758s2;

    /* renamed from: s3 */
    private int f6759s3;

    /* renamed from: s4 */
    private int f6760s4;
    private final byte[] singleByte;

    public Poly1305() {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        this.cipher = null;
    }

    public Poly1305(BlockCipher cipher2) {
        this.singleByte = new byte[1];
        this.currentBlock = new byte[16];
        this.currentBlockOffset = 0;
        if (cipher2.getBlockSize() != 16) {
            throw new IllegalArgumentException("Poly1305 requires a 128 bit block cipher.");
        }
        this.cipher = cipher2;
    }

    public void init(CipherParameters params) throws IllegalArgumentException {
        byte[] nonce = null;
        if (this.cipher != null) {
            if (!(params instanceof ParametersWithIV)) {
                throw new IllegalArgumentException("Poly1305 requires an IV when used with a block cipher.");
            }
            ParametersWithIV ivParams = (ParametersWithIV) params;
            nonce = ivParams.getIV();
            params = ivParams.getParameters();
        }
        if (!(params instanceof KeyParameter)) {
            throw new IllegalArgumentException("Poly1305 requires a key.");
        }
        setKey(((KeyParameter) params).getKey(), nonce);
        reset();
    }

    private void setKey(byte[] key, byte[] nonce) {
        byte[] kBytes;
        if (this.cipher == null || (nonce != null && nonce.length == 16)) {
            Poly1305KeyGenerator.checkKey(key);
            int t0 = Pack.littleEndianToInt(key, 16);
            int t1 = Pack.littleEndianToInt(key, 20);
            int t2 = Pack.littleEndianToInt(key, 24);
            int t3 = Pack.littleEndianToInt(key, 28);
            this.f6752r0 = 67108863 & t0;
            this.f6753r1 = 67108611 & ((t0 >>> 26) | (t1 << 6));
            this.f6754r2 = 67092735 & ((t1 >>> 20) | (t2 << 12));
            this.f6755r3 = 66076671 & ((t2 >>> 14) | (t3 << 18));
            this.f6756r4 = 1048575 & (t3 >>> 8);
            this.f6757s1 = this.f6753r1 * 5;
            this.f6758s2 = this.f6754r2 * 5;
            this.f6759s3 = this.f6755r3 * 5;
            this.f6760s4 = this.f6756r4 * 5;
            if (this.cipher == null) {
                kBytes = key;
            } else {
                kBytes = new byte[16];
                this.cipher.init(true, new KeyParameter(key, 0, 16));
                this.cipher.processBlock(nonce, 0, kBytes, 0);
            }
            this.f6748k0 = Pack.littleEndianToInt(kBytes, 0);
            this.f6749k1 = Pack.littleEndianToInt(kBytes, 4);
            this.f6750k2 = Pack.littleEndianToInt(kBytes, 8);
            this.f6751k3 = Pack.littleEndianToInt(kBytes, 12);
            return;
        }
        throw new IllegalArgumentException("Poly1305 requires a 128 bit IV.");
    }

    public String getAlgorithmName() {
        return this.cipher == null ? "Poly1305" : "Poly1305-" + this.cipher.getAlgorithmName();
    }

    public int getMacSize() {
        return 16;
    }

    public void update(byte in) throws IllegalStateException {
        this.singleByte[0] = in;
        update(this.singleByte, 0, 1);
    }

    public void update(byte[] in, int inOff, int len) throws DataLengthException, IllegalStateException {
        int copied = 0;
        while (len > copied) {
            if (this.currentBlockOffset == 16) {
                processBlock();
                this.currentBlockOffset = 0;
            }
            int toCopy = Math.min(len - copied, 16 - this.currentBlockOffset);
            System.arraycopy(in, copied + inOff, this.currentBlock, this.currentBlockOffset, toCopy);
            copied += toCopy;
            this.currentBlockOffset += toCopy;
        }
    }

    private void processBlock() {
        if (this.currentBlockOffset < 16) {
            this.currentBlock[this.currentBlockOffset] = 1;
            for (int i = this.currentBlockOffset + 1; i < 16; i++) {
                this.currentBlock[i] = 0;
            }
        }
        long t0 = 4294967295L & ((long) Pack.littleEndianToInt(this.currentBlock, 0));
        long t1 = 4294967295L & ((long) Pack.littleEndianToInt(this.currentBlock, 4));
        long t2 = 4294967295L & ((long) Pack.littleEndianToInt(this.currentBlock, 8));
        long t3 = 4294967295L & ((long) Pack.littleEndianToInt(this.currentBlock, 12));
        this.f6743h0 = (int) (((long) this.f6743h0) + (67108863 & t0));
        this.f6744h1 = (int) (((long) this.f6744h1) + ((((t1 << 32) | t0) >>> 26) & 67108863));
        this.f6745h2 = (int) (((long) this.f6745h2) + ((((t2 << 32) | t1) >>> 20) & 67108863));
        this.f6746h3 = (int) (((long) this.f6746h3) + ((((t3 << 32) | t2) >>> 14) & 67108863));
        this.f6747h4 = (int) (((long) this.f6747h4) + (t3 >>> 8));
        if (this.currentBlockOffset == 16) {
            this.f6747h4 += 16777216;
        }
        long tp0 = mul32x32_64(this.f6743h0, this.f6752r0) + mul32x32_64(this.f6744h1, this.f6760s4) + mul32x32_64(this.f6745h2, this.f6759s3) + mul32x32_64(this.f6746h3, this.f6758s2) + mul32x32_64(this.f6747h4, this.f6757s1);
        long tp1 = mul32x32_64(this.f6743h0, this.f6753r1) + mul32x32_64(this.f6744h1, this.f6752r0) + mul32x32_64(this.f6745h2, this.f6760s4) + mul32x32_64(this.f6746h3, this.f6759s3) + mul32x32_64(this.f6747h4, this.f6758s2);
        long tp2 = mul32x32_64(this.f6743h0, this.f6754r2) + mul32x32_64(this.f6744h1, this.f6753r1) + mul32x32_64(this.f6745h2, this.f6752r0) + mul32x32_64(this.f6746h3, this.f6760s4) + mul32x32_64(this.f6747h4, this.f6759s3);
        long tp3 = mul32x32_64(this.f6743h0, this.f6755r3) + mul32x32_64(this.f6744h1, this.f6754r2) + mul32x32_64(this.f6745h2, this.f6753r1) + mul32x32_64(this.f6746h3, this.f6752r0) + mul32x32_64(this.f6747h4, this.f6760s4);
        long tp4 = mul32x32_64(this.f6743h0, this.f6756r4) + mul32x32_64(this.f6744h1, this.f6755r3) + mul32x32_64(this.f6745h2, this.f6754r2) + mul32x32_64(this.f6746h3, this.f6753r1) + mul32x32_64(this.f6747h4, this.f6752r0);
        this.f6743h0 = ((int) tp0) & 67108863;
        long tp12 = tp1 + (tp0 >>> 26);
        this.f6744h1 = ((int) tp12) & 67108863;
        long tp22 = tp2 + ((tp12 >>> 26) & -1);
        this.f6745h2 = ((int) tp22) & 67108863;
        long tp32 = tp3 + ((tp22 >>> 26) & -1);
        this.f6746h3 = ((int) tp32) & 67108863;
        long tp42 = tp4 + (tp32 >>> 26);
        this.f6747h4 = ((int) tp42) & 67108863;
        this.f6743h0 = (int) (((long) this.f6743h0) + (5 * (tp42 >>> 26)));
    }

    public int doFinal(byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        if (outOff + 16 > out.length) {
            throw new DataLengthException("Output buffer is too short.");
        }
        if (this.currentBlockOffset > 0) {
            processBlock();
        }
        int b = this.f6743h0 >>> 26;
        this.f6743h0 &= 67108863;
        this.f6744h1 += b;
        int b2 = this.f6744h1 >>> 26;
        this.f6744h1 &= 67108863;
        this.f6745h2 += b2;
        int b3 = this.f6745h2 >>> 26;
        this.f6745h2 &= 67108863;
        this.f6746h3 += b3;
        int b4 = this.f6746h3 >>> 26;
        this.f6746h3 &= 67108863;
        this.f6747h4 += b4;
        int b5 = this.f6747h4 >>> 26;
        this.f6747h4 &= 67108863;
        this.f6743h0 += b5 * 5;
        int g0 = this.f6743h0 + 5;
        int b6 = g0 >>> 26;
        int g1 = this.f6744h1 + b6;
        int b7 = g1 >>> 26;
        int g12 = g1 & 67108863;
        int g2 = this.f6745h2 + b7;
        int b8 = g2 >>> 26;
        int g22 = g2 & 67108863;
        int g3 = this.f6746h3 + b8;
        int b9 = g3 >>> 26;
        int g32 = g3 & 67108863;
        int g4 = (this.f6747h4 + b9) - 67108864;
        int b10 = (g4 >>> 31) - 1;
        int nb = b10 ^ -1;
        this.f6743h0 = (this.f6743h0 & nb) | (g0 & 67108863 & b10);
        this.f6744h1 = (this.f6744h1 & nb) | (g12 & b10);
        this.f6745h2 = (this.f6745h2 & nb) | (g22 & b10);
        this.f6746h3 = (this.f6746h3 & nb) | (g32 & b10);
        this.f6747h4 = (this.f6747h4 & nb) | (g4 & b10);
        long f0 = (((long) (this.f6743h0 | (this.f6744h1 << 26))) & 4294967295L) + (4294967295L & ((long) this.f6748k0));
        long f1 = (((long) ((this.f6744h1 >>> 6) | (this.f6745h2 << 20))) & 4294967295L) + (4294967295L & ((long) this.f6749k1));
        long f2 = (((long) ((this.f6745h2 >>> 12) | (this.f6746h3 << 14))) & 4294967295L) + (4294967295L & ((long) this.f6750k2));
        long f3 = (((long) ((this.f6746h3 >>> 18) | (this.f6747h4 << 8))) & 4294967295L) + (4294967295L & ((long) this.f6751k3));
        Pack.intToLittleEndian((int) f0, out, outOff);
        long f12 = f1 + (f0 >>> 32);
        Pack.intToLittleEndian((int) f12, out, outOff + 4);
        long f22 = f2 + (f12 >>> 32);
        Pack.intToLittleEndian((int) f22, out, outOff + 8);
        Pack.intToLittleEndian((int) (f3 + (f22 >>> 32)), out, outOff + 12);
        reset();
        return 16;
    }

    public void reset() {
        this.currentBlockOffset = 0;
        this.f6747h4 = 0;
        this.f6746h3 = 0;
        this.f6745h2 = 0;
        this.f6744h1 = 0;
        this.f6743h0 = 0;
    }

    private static final long mul32x32_64(int i1, int i2) {
        return ((long) i1) * ((long) i2);
    }
}
