package org.spongycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.SRP6GroupParameters;

public class SRP6Server {

    /* renamed from: A */
    protected BigInteger f6476A;

    /* renamed from: B */
    protected BigInteger f6477B;
    protected BigInteger Key;

    /* renamed from: M1 */
    protected BigInteger f6478M1;

    /* renamed from: M2 */
    protected BigInteger f6479M2;

    /* renamed from: N */
    protected BigInteger f6480N;

    /* renamed from: S */
    protected BigInteger f6481S;

    /* renamed from: b */
    protected BigInteger f6482b;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f6483g;
    protected SecureRandom random;

    /* renamed from: u */
    protected BigInteger f6484u;

    /* renamed from: v */
    protected BigInteger f6485v;

    public void init(BigInteger N, BigInteger g, BigInteger v, Digest digest2, SecureRandom random2) {
        this.f6480N = N;
        this.f6483g = g;
        this.f6485v = v;
        this.random = random2;
        this.digest = digest2;
    }

    public void init(SRP6GroupParameters group, BigInteger v, Digest digest2, SecureRandom random2) {
        init(group.getN(), group.getG(), v, digest2, random2);
    }

    public BigInteger generateServerCredentials() {
        BigInteger k = SRP6Util.calculateK(this.digest, this.f6480N, this.f6483g);
        this.f6482b = selectPrivateValue();
        this.f6477B = k.multiply(this.f6485v).mod(this.f6480N).add(this.f6483g.modPow(this.f6482b, this.f6480N)).mod(this.f6480N);
        return this.f6477B;
    }

    public BigInteger calculateSecret(BigInteger clientA) throws CryptoException {
        this.f6476A = SRP6Util.validatePublicValue(this.f6480N, clientA);
        this.f6484u = SRP6Util.calculateU(this.digest, this.f6480N, this.f6476A, this.f6477B);
        this.f6481S = calculateS();
        return this.f6481S;
    }

    /* access modifiers changed from: protected */
    public BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.digest, this.f6480N, this.f6483g, this.random);
    }

    private BigInteger calculateS() {
        return this.f6485v.modPow(this.f6484u, this.f6480N).multiply(this.f6476A).mod(this.f6480N).modPow(this.f6482b, this.f6480N);
    }

    public boolean verifyClientEvidenceMessage(BigInteger clientM1) throws CryptoException {
        if (this.f6476A == null || this.f6477B == null || this.f6481S == null) {
            throw new CryptoException("Impossible to compute and verify M1: some data are missing from the previous operations (A,B,S)");
        } else if (!SRP6Util.calculateM1(this.digest, this.f6480N, this.f6476A, this.f6477B, this.f6481S).equals(clientM1)) {
            return false;
        } else {
            this.f6478M1 = clientM1;
            return true;
        }
    }

    public BigInteger calculateServerEvidenceMessage() throws CryptoException {
        if (this.f6476A == null || this.f6478M1 == null || this.f6481S == null) {
            throw new CryptoException("Impossible to compute M2: some data are missing from the previous operations (A,M1,S)");
        }
        this.f6479M2 = SRP6Util.calculateM2(this.digest, this.f6480N, this.f6476A, this.f6478M1, this.f6481S);
        return this.f6479M2;
    }

    public BigInteger calculateSessionKey() throws CryptoException {
        if (this.f6481S == null || this.f6478M1 == null || this.f6479M2 == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        this.Key = SRP6Util.calculateKey(this.digest, this.f6480N, this.f6481S);
        return this.Key;
    }
}
