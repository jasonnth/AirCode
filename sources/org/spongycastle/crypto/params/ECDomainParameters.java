package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.math.p332ec.ECConstants;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.util.Arrays;

public class ECDomainParameters implements ECConstants {

    /* renamed from: G */
    private ECPoint f6832G;
    private ECCurve curve;

    /* renamed from: h */
    private BigInteger f6833h;

    /* renamed from: n */
    private BigInteger f6834n;
    private byte[] seed;

    public ECDomainParameters(ECCurve curve2, ECPoint G, BigInteger n) {
        this(curve2, G, n, ONE, null);
    }

    public ECDomainParameters(ECCurve curve2, ECPoint G, BigInteger n, BigInteger h) {
        this(curve2, G, n, h, null);
    }

    public ECDomainParameters(ECCurve curve2, ECPoint G, BigInteger n, BigInteger h, byte[] seed2) {
        this.curve = curve2;
        this.f6832G = G.normalize();
        this.f6834n = n;
        this.f6833h = h;
        this.seed = seed2;
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public ECPoint getG() {
        return this.f6832G;
    }

    public BigInteger getN() {
        return this.f6834n;
    }

    public BigInteger getH() {
        return this.f6833h;
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }
}
