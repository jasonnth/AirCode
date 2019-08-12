package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.Digest;

class CombinedHash implements TlsHandshakeHash {
    protected TlsContext context;
    protected Digest md5;
    protected Digest sha1;

    CombinedHash() {
        this.md5 = TlsUtils.createHash(1);
        this.sha1 = TlsUtils.createHash(2);
    }

    CombinedHash(CombinedHash t) {
        this.context = t.context;
        this.md5 = TlsUtils.cloneHash(1, t.md5);
        this.sha1 = TlsUtils.cloneHash(2, t.sha1);
    }

    public void init(TlsContext context2) {
        this.context = context2;
    }

    public TlsHandshakeHash notifyPRFDetermined() {
        return this;
    }

    public void trackHashAlgorithm(short hashAlgorithm) {
        throw new IllegalStateException("CombinedHash only supports calculating the legacy PRF for handshake hash");
    }

    public void sealHashAlgorithms() {
    }

    public TlsHandshakeHash stopTracking() {
        return new CombinedHash(this);
    }

    public Digest forkPRFHash() {
        return new CombinedHash(this);
    }

    public byte[] getFinalHash(short hashAlgorithm) {
        throw new IllegalStateException("CombinedHash doesn't support multiple hashes");
    }

    public String getAlgorithmName() {
        return this.md5.getAlgorithmName() + " and " + this.sha1.getAlgorithmName();
    }

    public int getDigestSize() {
        return this.md5.getDigestSize() + this.sha1.getDigestSize();
    }

    public void update(byte input) {
        this.md5.update(input);
        this.sha1.update(input);
    }

    public void update(byte[] input, int inOff, int len) {
        this.md5.update(input, inOff, len);
        this.sha1.update(input, inOff, len);
    }

    public int doFinal(byte[] output, int outOff) {
        if (this.context != null && TlsUtils.isSSL(this.context)) {
            ssl3Complete(this.md5, SSL3Mac.IPAD, SSL3Mac.OPAD, 48);
            ssl3Complete(this.sha1, SSL3Mac.IPAD, SSL3Mac.OPAD, 40);
        }
        int i1 = this.md5.doFinal(output, outOff);
        return i1 + this.sha1.doFinal(output, outOff + i1);
    }

    public void reset() {
        this.md5.reset();
        this.sha1.reset();
    }

    /* access modifiers changed from: protected */
    public void ssl3Complete(Digest d, byte[] ipad, byte[] opad, int padLength) {
        byte[] master_secret = this.context.getSecurityParameters().masterSecret;
        d.update(master_secret, 0, master_secret.length);
        d.update(ipad, 0, padLength);
        byte[] tmp = new byte[d.getDigestSize()];
        d.doFinal(tmp, 0);
        d.update(master_secret, 0, master_secret.length);
        d.update(opad, 0, padLength);
        d.update(tmp, 0, tmp.length);
    }
}
