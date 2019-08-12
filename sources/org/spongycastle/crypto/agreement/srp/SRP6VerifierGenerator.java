package org.spongycastle.crypto.agreement.srp;

import java.math.BigInteger;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.SRP6GroupParameters;

public class SRP6VerifierGenerator {

    /* renamed from: N */
    protected BigInteger f6486N;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f6487g;

    public void init(BigInteger N, BigInteger g, Digest digest2) {
        this.f6486N = N;
        this.f6487g = g;
        this.digest = digest2;
    }

    public void init(SRP6GroupParameters group, Digest digest2) {
        this.f6486N = group.getN();
        this.f6487g = group.getG();
        this.digest = digest2;
    }

    public BigInteger generateVerifier(byte[] salt, byte[] identity, byte[] password) {
        return this.f6487g.modPow(SRP6Util.calculateX(this.digest, this.f6486N, salt, identity, password), this.f6486N);
    }
}
