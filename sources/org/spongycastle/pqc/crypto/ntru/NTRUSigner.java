package org.spongycastle.pqc.crypto.ntru;

import java.nio.ByteBuffer;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.pqc.math.ntru.polynomial.IntegerPolynomial;
import org.spongycastle.pqc.math.ntru.polynomial.Polynomial;

public class NTRUSigner {
    private Digest hashAlg;
    private NTRUSigningParameters params;
    private NTRUSigningPrivateKeyParameters signingKeyPair;
    private NTRUSigningPublicKeyParameters verificationKey;

    public NTRUSigner(NTRUSigningParameters params2) {
        this.params = params2;
    }

    public void init(boolean forSigning, CipherParameters params2) {
        if (forSigning) {
            this.signingKeyPair = (NTRUSigningPrivateKeyParameters) params2;
        } else {
            this.verificationKey = (NTRUSigningPublicKeyParameters) params2;
        }
        this.hashAlg = this.params.hashAlg;
        this.hashAlg.reset();
    }

    public void update(byte b) {
        if (this.hashAlg == null) {
            throw new IllegalStateException("Call initSign or initVerify first!");
        }
        this.hashAlg.update(b);
    }

    public void update(byte[] m, int off, int length) {
        if (this.hashAlg == null) {
            throw new IllegalStateException("Call initSign or initVerify first!");
        }
        this.hashAlg.update(m, off, length);
    }

    public byte[] generateSignature() {
        if (this.hashAlg == null || this.signingKeyPair == null) {
            throw new IllegalStateException("Call initSign first!");
        }
        byte[] msgHash = new byte[this.hashAlg.getDigestSize()];
        this.hashAlg.doFinal(msgHash, 0);
        return signHash(msgHash, this.signingKeyPair);
    }

    private byte[] signHash(byte[] msgHash, NTRUSigningPrivateKeyParameters kp) {
        IntegerPolynomial i;
        IntegerPolynomial s;
        int r = 0;
        NTRUSigningPublicKeyParameters kPub = kp.getPublicKey();
        do {
            r++;
            if (r > this.params.signFailTolerance) {
                throw new IllegalStateException("Signing failed: too many retries (max=" + this.params.signFailTolerance + ")");
            }
            i = createMsgRep(msgHash, r);
            s = sign(i, kp);
        } while (!verify(i, s, kPub.f7169h));
        byte[] rawSig = s.toBinary(this.params.f7166q);
        ByteBuffer sbuf = ByteBuffer.allocate(rawSig.length + 4);
        sbuf.put(rawSig);
        sbuf.putInt(r);
        return sbuf.array();
    }

    private IntegerPolynomial sign(IntegerPolynomial i, NTRUSigningPrivateKeyParameters kp) {
        int N = this.params.f7161N;
        int q = this.params.f7166q;
        int perturbationBases = this.params.f7160B;
        NTRUSigningPrivateKeyParameters kPriv = kp;
        NTRUSigningPublicKeyParameters kPub = kp.getPublicKey();
        IntegerPolynomial s = new IntegerPolynomial(N);
        for (int iLoop = perturbationBases; iLoop >= 1; iLoop--) {
            Polynomial f = kPriv.getBasis(iLoop).f7167f;
            Polynomial fPrime = kPriv.getBasis(iLoop).fPrime;
            IntegerPolynomial y = f.mult(i);
            y.div(q);
            IntegerPolynomial y2 = fPrime.mult(y);
            IntegerPolynomial x = fPrime.mult(i);
            x.div(q);
            IntegerPolynomial si = y2;
            si.sub(f.mult(x));
            s.add(si);
            IntegerPolynomial hi = (IntegerPolynomial) kPriv.getBasis(iLoop).f7168h.clone();
            if (iLoop > 1) {
                hi.sub(kPriv.getBasis(iLoop - 1).f7168h);
            } else {
                hi.sub(kPub.f7169h);
            }
            i = si.mult(hi, q);
        }
        Polynomial f2 = kPriv.getBasis(0).f7167f;
        Polynomial fPrime2 = kPriv.getBasis(0).fPrime;
        IntegerPolynomial y3 = f2.mult(i);
        y3.div(q);
        IntegerPolynomial y4 = fPrime2.mult(y3);
        IntegerPolynomial x2 = fPrime2.mult(i);
        x2.div(q);
        y4.sub(f2.mult(x2));
        s.add(y4);
        s.modPositive(q);
        return s;
    }

    public boolean verifySignature(byte[] sig) {
        if (this.hashAlg == null || this.verificationKey == null) {
            throw new IllegalStateException("Call initVerify first!");
        }
        byte[] msgHash = new byte[this.hashAlg.getDigestSize()];
        this.hashAlg.doFinal(msgHash, 0);
        return verifyHash(msgHash, sig, this.verificationKey);
    }

    private boolean verifyHash(byte[] msgHash, byte[] sig, NTRUSigningPublicKeyParameters pub) {
        ByteBuffer sbuf = ByteBuffer.wrap(sig);
        byte[] rawSig = new byte[(sig.length - 4)];
        sbuf.get(rawSig);
        return verify(createMsgRep(msgHash, sbuf.getInt()), IntegerPolynomial.fromBinary(rawSig, this.params.f7161N, this.params.f7166q), pub.f7169h);
    }

    private boolean verify(IntegerPolynomial i, IntegerPolynomial s, IntegerPolynomial h) {
        int q = this.params.f7166q;
        double normBoundSq = this.params.normBoundSq;
        double betaSq = this.params.betaSq;
        IntegerPolynomial t = h.mult(s, q);
        t.sub(i);
        return ((double) ((long) (((double) s.centeredNormSq(q)) + (((double) t.centeredNormSq(q)) * betaSq)))) <= normBoundSq;
    }

    /* access modifiers changed from: protected */
    public IntegerPolynomial createMsgRep(byte[] msgHash, int r) {
        int N = this.params.f7161N;
        int c = 31 - Integer.numberOfLeadingZeros(this.params.f7166q);
        int B = (c + 7) / 8;
        IntegerPolynomial i = new IntegerPolynomial(N);
        ByteBuffer cbuf = ByteBuffer.allocate(msgHash.length + 4);
        cbuf.put(msgHash);
        cbuf.putInt(r);
        NTRUSignerPrng prng = new NTRUSignerPrng(cbuf.array(), this.params.hashAlg);
        for (int t = 0; t < N; t++) {
            byte[] o = prng.nextBytes(B);
            o[o.length - 1] = (byte) ((o[o.length - 1] >> ((B * 8) - c)) << ((B * 8) - c));
            ByteBuffer obuf = ByteBuffer.allocate(4);
            obuf.put(o);
            obuf.rewind();
            i.coeffs[t] = Integer.reverseBytes(obuf.getInt());
        }
        return i;
    }
}
