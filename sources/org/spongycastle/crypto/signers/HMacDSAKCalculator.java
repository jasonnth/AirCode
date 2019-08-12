package org.spongycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;

public class HMacDSAKCalculator implements DSAKCalculator {
    private static final BigInteger ZERO = BigInteger.valueOf(0);

    /* renamed from: K */
    private final byte[] f6885K = new byte[this.hMac.getMacSize()];

    /* renamed from: V */
    private final byte[] f6886V = new byte[this.hMac.getMacSize()];
    private final HMac hMac;

    /* renamed from: n */
    private BigInteger f6887n;

    public HMacDSAKCalculator(Digest digest) {
        this.hMac = new HMac(digest);
    }

    public boolean isDeterministic() {
        return true;
    }

    public void init(BigInteger n, SecureRandom random) {
        throw new IllegalStateException("Operation not supported");
    }

    public void init(BigInteger n, BigInteger d, byte[] message) {
        this.f6887n = n;
        Arrays.fill(this.f6886V, 1);
        Arrays.fill(this.f6885K, 0);
        byte[] x = new byte[((n.bitLength() + 7) / 8)];
        byte[] dVal = BigIntegers.asUnsignedByteArray(d);
        System.arraycopy(dVal, 0, x, x.length - dVal.length, dVal.length);
        byte[] m = new byte[((n.bitLength() + 7) / 8)];
        BigInteger mInt = bitsToInt(message);
        if (mInt.compareTo(n) >= 0) {
            mInt = mInt.subtract(n);
        }
        byte[] mVal = BigIntegers.asUnsignedByteArray(mInt);
        System.arraycopy(mVal, 0, m, m.length - mVal.length, mVal.length);
        this.hMac.init(new KeyParameter(this.f6885K));
        this.hMac.update(this.f6886V, 0, this.f6886V.length);
        this.hMac.update(0);
        this.hMac.update(x, 0, x.length);
        this.hMac.update(m, 0, m.length);
        this.hMac.doFinal(this.f6885K, 0);
        this.hMac.init(new KeyParameter(this.f6885K));
        this.hMac.update(this.f6886V, 0, this.f6886V.length);
        this.hMac.doFinal(this.f6886V, 0);
        this.hMac.update(this.f6886V, 0, this.f6886V.length);
        this.hMac.update(1);
        this.hMac.update(x, 0, x.length);
        this.hMac.update(m, 0, m.length);
        this.hMac.doFinal(this.f6885K, 0);
        this.hMac.init(new KeyParameter(this.f6885K));
        this.hMac.update(this.f6886V, 0, this.f6886V.length);
        this.hMac.doFinal(this.f6886V, 0);
    }

    public BigInteger nextK() {
        byte[] t = new byte[((this.f6887n.bitLength() + 7) / 8)];
        while (true) {
            int tOff = 0;
            while (tOff < t.length) {
                this.hMac.update(this.f6886V, 0, this.f6886V.length);
                this.hMac.doFinal(this.f6886V, 0);
                int len = Math.min(t.length - tOff, this.f6886V.length);
                System.arraycopy(this.f6886V, 0, t, tOff, len);
                tOff += len;
            }
            BigInteger k = bitsToInt(t);
            if (k.compareTo(ZERO) > 0 && k.compareTo(this.f6887n) < 0) {
                return k;
            }
            this.hMac.update(this.f6886V, 0, this.f6886V.length);
            this.hMac.update(0);
            this.hMac.doFinal(this.f6885K, 0);
            this.hMac.init(new KeyParameter(this.f6885K));
            this.hMac.update(this.f6886V, 0, this.f6886V.length);
            this.hMac.doFinal(this.f6886V, 0);
        }
    }

    private BigInteger bitsToInt(byte[] t) {
        BigInteger v = new BigInteger(1, t);
        if (t.length * 8 > this.f6887n.bitLength()) {
            return v.shiftRight((t.length * 8) - this.f6887n.bitLength());
        }
        return v;
    }
}
