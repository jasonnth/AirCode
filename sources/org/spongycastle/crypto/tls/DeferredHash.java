package org.spongycastle.crypto.tls;

import java.util.Enumeration;
import java.util.Hashtable;
import org.spongycastle.crypto.Digest;
import org.spongycastle.util.Shorts;

class DeferredHash implements TlsHandshakeHash {
    protected static final int BUFFERING_HASH_LIMIT = 4;
    private DigestInputBuffer buf;
    protected TlsContext context;
    private Hashtable hashes;
    private Short prfHashAlgorithm;

    DeferredHash() {
        this.buf = new DigestInputBuffer();
        this.hashes = new Hashtable();
        this.prfHashAlgorithm = null;
    }

    private DeferredHash(Short prfHashAlgorithm2, Digest prfHash) {
        this.buf = null;
        this.hashes = new Hashtable();
        this.prfHashAlgorithm = prfHashAlgorithm2;
        this.hashes.put(prfHashAlgorithm2, prfHash);
    }

    public void init(TlsContext context2) {
        this.context = context2;
    }

    /* Debug info: failed to restart local var, previous not found, register: 3 */
    public TlsHandshakeHash notifyPRFDetermined() {
        int prfAlgorithm = this.context.getSecurityParameters().getPrfAlgorithm();
        if (prfAlgorithm == 0) {
            CombinedHash legacyHash = new CombinedHash();
            legacyHash.init(this.context);
            this.buf.updateDigest(legacyHash);
            return legacyHash.notifyPRFDetermined();
        }
        this.prfHashAlgorithm = Shorts.valueOf(TlsUtils.getHashAlgorithmForPRFAlgorithm(prfAlgorithm));
        checkTrackingHash(this.prfHashAlgorithm);
        return this;
    }

    public void trackHashAlgorithm(short hashAlgorithm) {
        if (this.buf == null) {
            throw new IllegalStateException("Too late to track more hash algorithms");
        }
        checkTrackingHash(Shorts.valueOf(hashAlgorithm));
    }

    public void sealHashAlgorithms() {
        checkStopBuffering();
    }

    public TlsHandshakeHash stopTracking() {
        Digest prfHash = TlsUtils.cloneHash(this.prfHashAlgorithm.shortValue(), (Digest) this.hashes.get(this.prfHashAlgorithm));
        if (this.buf != null) {
            this.buf.updateDigest(prfHash);
        }
        DeferredHash result = new DeferredHash(this.prfHashAlgorithm, prfHash);
        result.init(this.context);
        return result;
    }

    public Digest forkPRFHash() {
        checkStopBuffering();
        if (this.buf == null) {
            return TlsUtils.cloneHash(this.prfHashAlgorithm.shortValue(), (Digest) this.hashes.get(this.prfHashAlgorithm));
        }
        Digest prfHash = TlsUtils.createHash(this.prfHashAlgorithm.shortValue());
        this.buf.updateDigest(prfHash);
        return prfHash;
    }

    public byte[] getFinalHash(short hashAlgorithm) {
        Digest d = (Digest) this.hashes.get(Shorts.valueOf(hashAlgorithm));
        if (d == null) {
            throw new IllegalStateException("HashAlgorithm." + HashAlgorithm.getText(hashAlgorithm) + " is not being tracked");
        }
        Digest d2 = TlsUtils.cloneHash(hashAlgorithm, d);
        if (this.buf != null) {
            this.buf.updateDigest(d2);
        }
        byte[] bs = new byte[d2.getDigestSize()];
        d2.doFinal(bs, 0);
        return bs;
    }

    public String getAlgorithmName() {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    public int getDigestSize() {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    public void update(byte input) {
        if (this.buf != null) {
            this.buf.write(input);
            return;
        }
        Enumeration e = this.hashes.elements();
        while (e.hasMoreElements()) {
            ((Digest) e.nextElement()).update(input);
        }
    }

    public void update(byte[] input, int inOff, int len) {
        if (this.buf != null) {
            this.buf.write(input, inOff, len);
            return;
        }
        Enumeration e = this.hashes.elements();
        while (e.hasMoreElements()) {
            ((Digest) e.nextElement()).update(input, inOff, len);
        }
    }

    public int doFinal(byte[] output, int outOff) {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    public void reset() {
        if (this.buf != null) {
            this.buf.reset();
            return;
        }
        Enumeration e = this.hashes.elements();
        while (e.hasMoreElements()) {
            ((Digest) e.nextElement()).reset();
        }
    }

    /* access modifiers changed from: protected */
    public void checkStopBuffering() {
        if (this.buf != null && this.hashes.size() <= 4) {
            Enumeration e = this.hashes.elements();
            while (e.hasMoreElements()) {
                this.buf.updateDigest((Digest) e.nextElement());
            }
            this.buf = null;
        }
    }

    /* access modifiers changed from: protected */
    public void checkTrackingHash(Short hashAlgorithm) {
        if (!this.hashes.containsKey(hashAlgorithm)) {
            this.hashes.put(hashAlgorithm, TlsUtils.createHash(hashAlgorithm.shortValue()));
        }
    }
}
