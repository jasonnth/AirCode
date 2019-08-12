package org.spongycastle.crypto.signers;

import java.io.IOException;
import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;

public class DSADigestSigner implements Signer {
    private final Digest digest;
    private final DSA dsaSigner;
    private boolean forSigning;

    public DSADigestSigner(DSA signer, Digest digest2) {
        this.digest = digest2;
        this.dsaSigner = signer;
    }

    public void init(boolean forSigning2, CipherParameters parameters) {
        AsymmetricKeyParameter k;
        this.forSigning = forSigning2;
        if (parameters instanceof ParametersWithRandom) {
            k = (AsymmetricKeyParameter) ((ParametersWithRandom) parameters).getParameters();
        } else {
            k = (AsymmetricKeyParameter) parameters;
        }
        if (forSigning2 && !k.isPrivate()) {
            throw new IllegalArgumentException("Signing Requires Private Key.");
        } else if (forSigning2 || !k.isPrivate()) {
            reset();
            this.dsaSigner.init(forSigning2, parameters);
        } else {
            throw new IllegalArgumentException("Verification Requires Public Key.");
        }
    }

    public void update(byte input) {
        this.digest.update(input);
    }

    public void update(byte[] input, int inOff, int length) {
        this.digest.update(input, inOff, length);
    }

    public byte[] generateSignature() {
        if (!this.forSigning) {
            throw new IllegalStateException("DSADigestSigner not initialised for signature generation.");
        }
        byte[] hash = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(hash, 0);
        BigInteger[] sig = this.dsaSigner.generateSignature(hash);
        try {
            return derEncode(sig[0], sig[1]);
        } catch (IOException e) {
            throw new IllegalStateException("unable to encode signature");
        }
    }

    public boolean verifySignature(byte[] signature) {
        int i = 0;
        if (this.forSigning) {
            throw new IllegalStateException("DSADigestSigner not initialised for verification");
        }
        byte[] hash = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(hash, i);
        try {
            BigInteger[] sig = derDecode(signature);
            return this.dsaSigner.verifySignature(hash, sig[0], sig[1]);
        } catch (IOException e) {
            return i;
        }
    }

    public void reset() {
        this.digest.reset();
    }

    private byte[] derEncode(BigInteger r, BigInteger s) throws IOException {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(r));
        v.add(new ASN1Integer(s));
        return new DERSequence(v).getEncoded(ASN1Encoding.DER);
    }

    private BigInteger[] derDecode(byte[] encoding) throws IOException {
        ASN1Sequence s = (ASN1Sequence) ASN1Primitive.fromByteArray(encoding);
        return new BigInteger[]{((ASN1Integer) s.getObjectAt(0)).getValue(), ((ASN1Integer) s.getObjectAt(1)).getValue()};
    }
}
