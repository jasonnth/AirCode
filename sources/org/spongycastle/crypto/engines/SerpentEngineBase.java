package org.spongycastle.crypto.engines;

import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;

public abstract class SerpentEngineBase implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;
    static final int PHI = -1640531527;
    static final int ROUNDS = 32;

    /* renamed from: X0 */
    protected int f6705X0;

    /* renamed from: X1 */
    protected int f6706X1;

    /* renamed from: X2 */
    protected int f6707X2;

    /* renamed from: X3 */
    protected int f6708X3;
    protected boolean encrypting;
    protected int[] wKey;

    /* access modifiers changed from: protected */
    public abstract void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2);

    /* access modifiers changed from: protected */
    public abstract void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2);

    /* access modifiers changed from: protected */
    public abstract int[] makeWorkingKey(byte[] bArr);

    SerpentEngineBase() {
    }

    public void init(boolean encrypting2, CipherParameters params) {
        if (params instanceof KeyParameter) {
            this.encrypting = encrypting2;
            this.wKey = makeWorkingKey(((KeyParameter) params).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to " + getAlgorithmName() + " init - " + params.getClass().getName());
    }

    public String getAlgorithmName() {
        return "Serpent";
    }

    public int getBlockSize() {
        return 16;
    }

    public final int processBlock(byte[] in, int inOff, byte[] out, int outOff) {
        if (this.wKey == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        } else if (inOff + 16 > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if (outOff + 16 > out.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            if (this.encrypting) {
                encryptBlock(in, inOff, out, outOff);
            } else {
                decryptBlock(in, inOff, out, outOff);
            }
            return 16;
        }
    }

    public void reset() {
    }

    protected static int rotateLeft(int x, int bits) {
        return (x << bits) | (x >>> (-bits));
    }

    protected static int rotateRight(int x, int bits) {
        return (x >>> bits) | (x << (-bits));
    }

    /* access modifiers changed from: protected */
    public final void sb0(int a, int b, int c, int d) {
        int t1 = a ^ d;
        int t3 = c ^ t1;
        int t4 = b ^ t3;
        this.f6708X3 = (a & d) ^ t4;
        int t7 = a ^ (b & t1);
        this.f6707X2 = (c | t7) ^ t4;
        int t12 = this.f6708X3 & (t3 ^ t7);
        this.f6706X1 = (t3 ^ -1) ^ t12;
        this.f6705X0 = (t7 ^ -1) ^ t12;
    }

    /* access modifiers changed from: protected */
    public final void ib0(int a, int b, int c, int d) {
        int t1 = a ^ -1;
        int t2 = a ^ b;
        int t4 = d ^ (t1 | t2);
        int t5 = c ^ t4;
        this.f6707X2 = t2 ^ t5;
        int t8 = t1 ^ (d & t2);
        this.f6706X1 = (this.f6707X2 & t8) ^ t4;
        this.f6708X3 = (a & t4) ^ (this.f6706X1 | t5);
        this.f6705X0 = this.f6708X3 ^ (t5 ^ t8);
    }

    /* access modifiers changed from: protected */
    public final void sb1(int a, int b, int c, int d) {
        int t2 = b ^ (a ^ -1);
        int t5 = c ^ (a | t2);
        this.f6707X2 = d ^ t5;
        int t7 = b ^ (d | t2);
        int t8 = t2 ^ this.f6707X2;
        this.f6708X3 = (t5 & t7) ^ t8;
        int t11 = t5 ^ t7;
        this.f6706X1 = this.f6708X3 ^ t11;
        this.f6705X0 = (t8 & t11) ^ t5;
    }

    /* access modifiers changed from: protected */
    public final void ib1(int a, int b, int c, int d) {
        int t1 = b ^ d;
        int t3 = a ^ (b & t1);
        int t4 = t1 ^ t3;
        this.f6708X3 = c ^ t4;
        int t7 = b ^ (t1 & t3);
        this.f6706X1 = t3 ^ (this.f6708X3 | t7);
        int t10 = this.f6706X1 ^ -1;
        int t11 = this.f6708X3 ^ t7;
        this.f6705X0 = t10 ^ t11;
        this.f6707X2 = (t10 | t11) ^ t4;
    }

    /* access modifiers changed from: protected */
    public final void sb2(int a, int b, int c, int d) {
        int t1 = a ^ -1;
        int t2 = b ^ d;
        this.f6705X0 = t2 ^ (c & t1);
        int t5 = c ^ t1;
        int t7 = b & (c ^ this.f6705X0);
        this.f6708X3 = t5 ^ t7;
        this.f6707X2 = ((d | t7) & (this.f6705X0 | t5)) ^ a;
        this.f6706X1 = (this.f6708X3 ^ t2) ^ (this.f6707X2 ^ (d | t1));
    }

    /* access modifiers changed from: protected */
    public final void ib2(int a, int b, int c, int d) {
        int t1 = b ^ d;
        int t2 = t1 ^ -1;
        int t3 = a ^ c;
        int t4 = c ^ t1;
        this.f6705X0 = t3 ^ (b & t4);
        this.f6708X3 = t1 ^ (t3 | (d ^ (a | t2)));
        int t11 = t4 ^ -1;
        int t12 = this.f6705X0 | this.f6708X3;
        this.f6706X1 = t11 ^ t12;
        this.f6707X2 = (d & t11) ^ (t3 ^ t12);
    }

    /* access modifiers changed from: protected */
    public final void sb3(int a, int b, int c, int d) {
        int t1 = a ^ b;
        int t3 = a | d;
        int t4 = c ^ d;
        int t6 = (a & c) | (t1 & t3);
        this.f6707X2 = t4 ^ t6;
        int t9 = t6 ^ (b ^ t3);
        this.f6705X0 = t1 ^ (t4 & t9);
        int t12 = this.f6707X2 & this.f6705X0;
        this.f6706X1 = t9 ^ t12;
        this.f6708X3 = (b | d) ^ (t4 ^ t12);
    }

    /* access modifiers changed from: protected */
    public final void ib3(int a, int b, int c, int d) {
        int t1 = a | b;
        int t2 = b ^ c;
        int t4 = a ^ (b & t2);
        int t5 = c ^ t4;
        int t6 = d | t4;
        this.f6705X0 = t2 ^ t6;
        int t9 = d ^ (t2 | t6);
        this.f6707X2 = t5 ^ t9;
        int t11 = t1 ^ t9;
        this.f6708X3 = t4 ^ (this.f6705X0 & t11);
        this.f6706X1 = this.f6708X3 ^ (this.f6705X0 ^ t11);
    }

    /* access modifiers changed from: protected */
    public final void sb4(int a, int b, int c, int d) {
        int t1 = a ^ d;
        int t3 = c ^ (d & t1);
        int t4 = b | t3;
        this.f6708X3 = t1 ^ t4;
        int t6 = b ^ -1;
        this.f6705X0 = t3 ^ (t1 | t6);
        int t10 = t1 ^ t6;
        this.f6707X2 = (a & this.f6705X0) ^ (t4 & t10);
        this.f6706X1 = (a ^ t3) ^ (this.f6707X2 & t10);
    }

    /* access modifiers changed from: protected */
    public final void ib4(int a, int b, int c, int d) {
        int t3 = b ^ (a & (c | d));
        int t5 = c ^ (a & t3);
        this.f6706X1 = d ^ t5;
        int t7 = a ^ -1;
        this.f6708X3 = t3 ^ (t5 & this.f6706X1);
        int t11 = d ^ (this.f6706X1 | t7);
        this.f6705X0 = this.f6708X3 ^ t11;
        this.f6707X2 = (t3 & t11) ^ (this.f6706X1 ^ t7);
    }

    /* access modifiers changed from: protected */
    public final void sb5(int a, int b, int c, int d) {
        int t1 = a ^ -1;
        int t2 = a ^ b;
        int t3 = a ^ d;
        this.f6705X0 = (c ^ t1) ^ (t2 | t3);
        int t7 = d & this.f6705X0;
        this.f6706X1 = t7 ^ (t2 ^ this.f6705X0);
        int t12 = t3 ^ (t1 | this.f6705X0);
        this.f6707X2 = (t2 | t7) ^ t12;
        this.f6708X3 = (b ^ t7) ^ (this.f6706X1 & t12);
    }

    /* access modifiers changed from: protected */
    public final void ib5(int a, int b, int c, int d) {
        int t1 = c ^ -1;
        int t3 = d ^ (b & t1);
        int t4 = a & t3;
        this.f6708X3 = t4 ^ (b ^ t1);
        int t7 = b | this.f6708X3;
        this.f6706X1 = t3 ^ (a & t7);
        int t10 = a | d;
        this.f6705X0 = t10 ^ (t1 ^ t7);
        this.f6707X2 = (b & t10) ^ ((a ^ c) | t4);
    }

    /* access modifiers changed from: protected */
    public final void sb6(int a, int b, int c, int d) {
        int t2 = a ^ d;
        int t3 = b ^ t2;
        int t5 = c ^ ((a ^ -1) | t2);
        this.f6706X1 = b ^ t5;
        int t8 = d ^ (t2 | this.f6706X1);
        this.f6707X2 = t3 ^ (t5 & t8);
        int t11 = t5 ^ t8;
        this.f6705X0 = this.f6707X2 ^ t11;
        this.f6708X3 = (t5 ^ -1) ^ (t3 & t11);
    }

    /* access modifiers changed from: protected */
    public final void ib6(int a, int b, int c, int d) {
        int t1 = a ^ -1;
        int t2 = a ^ b;
        int t3 = c ^ t2;
        int t5 = d ^ (c | t1);
        this.f6706X1 = t3 ^ t5;
        int t8 = t2 ^ (t3 & t5);
        this.f6708X3 = t5 ^ (b | t8);
        int t11 = b | this.f6708X3;
        this.f6705X0 = t8 ^ t11;
        this.f6707X2 = (d & t1) ^ (t3 ^ t11);
    }

    /* access modifiers changed from: protected */
    public final void sb7(int a, int b, int c, int d) {
        int t1 = b ^ c;
        int t3 = d ^ (c & t1);
        int t4 = a ^ t3;
        this.f6706X1 = b ^ (t4 & (d | t1));
        int t8 = t3 | this.f6706X1;
        this.f6708X3 = t1 ^ (a & t4);
        int t11 = t4 ^ t8;
        this.f6707X2 = t3 ^ (this.f6708X3 & t11);
        this.f6705X0 = (t11 ^ -1) ^ (this.f6708X3 & this.f6707X2);
    }

    /* access modifiers changed from: protected */
    public final void ib7(int a, int b, int c, int d) {
        int t3 = c | (a & b);
        int t4 = d & (a | b);
        this.f6708X3 = t3 ^ t4;
        int t7 = b ^ t4;
        this.f6706X1 = a ^ (t7 | (this.f6708X3 ^ (d ^ -1)));
        this.f6705X0 = (c ^ t7) ^ (this.f6706X1 | d);
        this.f6707X2 = (this.f6706X1 ^ t3) ^ (this.f6705X0 ^ (this.f6708X3 & a));
    }

    /* access modifiers changed from: protected */
    /* renamed from: LT */
    public final void mo51161LT() {
        int x0 = rotateLeft(this.f6705X0, 13);
        int x2 = rotateLeft(this.f6707X2, 3);
        int x3 = (this.f6708X3 ^ x2) ^ (x0 << 3);
        this.f6706X1 = rotateLeft((this.f6706X1 ^ x0) ^ x2, 1);
        this.f6708X3 = rotateLeft(x3, 7);
        this.f6705X0 = rotateLeft((this.f6706X1 ^ x0) ^ this.f6708X3, 5);
        this.f6707X2 = rotateLeft((this.f6708X3 ^ x2) ^ (this.f6706X1 << 7), 22);
    }

    /* access modifiers changed from: protected */
    public final void inverseLT() {
        int x2 = (rotateRight(this.f6707X2, 22) ^ this.f6708X3) ^ (this.f6706X1 << 7);
        int x0 = (rotateRight(this.f6705X0, 5) ^ this.f6706X1) ^ this.f6708X3;
        int x3 = rotateRight(this.f6708X3, 7);
        int x1 = rotateRight(this.f6706X1, 1);
        this.f6708X3 = (x3 ^ x2) ^ (x0 << 3);
        this.f6706X1 = (x1 ^ x0) ^ x2;
        this.f6707X2 = rotateRight(x2, 3);
        this.f6705X0 = rotateRight(x0, 13);
    }
}
