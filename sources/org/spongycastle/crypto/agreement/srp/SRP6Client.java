package org.spongycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.params.SRP6GroupParameters;

public class SRP6Client {

    /* renamed from: A */
    protected BigInteger f6466A;

    /* renamed from: B */
    protected BigInteger f6467B;
    protected BigInteger Key;

    /* renamed from: M1 */
    protected BigInteger f6468M1;

    /* renamed from: M2 */
    protected BigInteger f6469M2;

    /* renamed from: N */
    protected BigInteger f6470N;

    /* renamed from: S */
    protected BigInteger f6471S;

    /* renamed from: a */
    protected BigInteger f6472a;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f6473g;
    protected SecureRandom random;

    /* renamed from: u */
    protected BigInteger f6474u;

    /* renamed from: x */
    protected BigInteger f6475x;

    public void init(BigInteger N, BigInteger g, Digest digest2, SecureRandom random2) {
        this.f6470N = N;
        this.f6473g = g;
        this.digest = digest2;
        this.random = random2;
    }

    public void init(SRP6GroupParameters group, Digest digest2, SecureRandom random2) {
        init(group.getN(), group.getG(), digest2, random2);
    }

    public BigInteger generateClientCredentials(byte[] salt, byte[] identity, byte[] password) {
        this.f6475x = SRP6Util.calculateX(this.digest, this.f6470N, salt, identity, password);
        this.f6472a = selectPrivateValue();
        this.f6466A = this.f6473g.modPow(this.f6472a, this.f6470N);
        return this.f6466A;
    }

    public BigInteger calculateSecret(BigInteger serverB) throws CryptoException {
        this.f6467B = SRP6Util.validatePublicValue(this.f6470N, serverB);
        this.f6474u = SRP6Util.calculateU(this.digest, this.f6470N, this.f6466A, this.f6467B);
        this.f6471S = calculateS();
        return this.f6471S;
    }

    /* access modifiers changed from: protected */
    public BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.digest, this.f6470N, this.f6473g, this.random);
    }

    private BigInteger calculateS() {
        BigInteger k = SRP6Util.calculateK(this.digest, this.f6470N, this.f6473g);
        return this.f6467B.subtract(this.f6473g.modPow(this.f6475x, this.f6470N).multiply(k).mod(this.f6470N)).mod(this.f6470N).modPow(this.f6474u.multiply(this.f6475x).add(this.f6472a), this.f6470N);
    }

    public BigInteger calculateClientEvidenceMessage() throws CryptoException {
        if (this.f6466A == null || this.f6467B == null || this.f6471S == null) {
            throw new CryptoException("Impossible to compute M1: some data are missing from the previous operations (A,B,S)");
        }
        this.f6468M1 = SRP6Util.calculateM1(this.digest, this.f6470N, this.f6466A, this.f6467B, this.f6471S);
        return this.f6468M1;
    }

    public boolean verifyServerEvidenceMessage(BigInteger serverM2) throws CryptoException {
        if (this.f6466A == null || this.f6468M1 == null || this.f6471S == null) {
            throw new CryptoException("Impossible to compute and verify M2: some data are missing from the previous operations (A,M1,S)");
        } else if (!SRP6Util.calculateM2(this.digest, this.f6470N, this.f6466A, this.f6468M1, this.f6471S).equals(serverM2)) {
            return false;
        } else {
            this.f6469M2 = serverM2;
            return true;
        }
    }

    public BigInteger calculateSessionKey() throws CryptoException {
        if (this.f6471S == null || this.f6468M1 == null || this.f6469M2 == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        this.Key = SRP6Util.calculateKey(this.digest, this.f6470N, this.f6471S);
        return this.Key;
    }
}
