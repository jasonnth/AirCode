package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.crypto.CipherParameters;

public class GOST3410Parameters implements CipherParameters {

    /* renamed from: a */
    private BigInteger f6842a;

    /* renamed from: p */
    private BigInteger f6843p;

    /* renamed from: q */
    private BigInteger f6844q;
    private GOST3410ValidationParameters validation;

    public GOST3410Parameters(BigInteger p, BigInteger q, BigInteger a) {
        this.f6843p = p;
        this.f6844q = q;
        this.f6842a = a;
    }

    public GOST3410Parameters(BigInteger p, BigInteger q, BigInteger a, GOST3410ValidationParameters params) {
        this.f6842a = a;
        this.f6843p = p;
        this.f6844q = q;
        this.validation = params;
    }

    public BigInteger getP() {
        return this.f6843p;
    }

    public BigInteger getQ() {
        return this.f6844q;
    }

    public BigInteger getA() {
        return this.f6842a;
    }

    public GOST3410ValidationParameters getValidationParameters() {
        return this.validation;
    }

    public int hashCode() {
        return (this.f6843p.hashCode() ^ this.f6844q.hashCode()) ^ this.f6842a.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GOST3410Parameters)) {
            return false;
        }
        GOST3410Parameters pm = (GOST3410Parameters) obj;
        if (!pm.getP().equals(this.f6843p) || !pm.getQ().equals(this.f6844q) || !pm.getA().equals(this.f6842a)) {
            return false;
        }
        return true;
    }
}
