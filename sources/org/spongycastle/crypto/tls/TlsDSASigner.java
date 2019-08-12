package org.spongycastle.crypto.tls;

import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.digests.NullDigest;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.signers.DSADigestSigner;

public abstract class TlsDSASigner extends AbstractTlsSigner {
    /* access modifiers changed from: protected */
    public abstract DSA createDSAImpl(short s);

    /* access modifiers changed from: protected */
    public abstract short getSignatureAlgorithm();

    public byte[] generateRawSignature(SignatureAndHashAlgorithm algorithm, AsymmetricKeyParameter privateKey, byte[] hash) throws CryptoException {
        Signer signer = makeSigner(algorithm, true, true, new ParametersWithRandom(privateKey, this.context.getSecureRandom()));
        if (algorithm == null) {
            signer.update(hash, 16, 20);
        } else {
            signer.update(hash, 0, hash.length);
        }
        return signer.generateSignature();
    }

    public boolean verifyRawSignature(SignatureAndHashAlgorithm algorithm, byte[] sigBytes, AsymmetricKeyParameter publicKey, byte[] hash) throws CryptoException {
        Signer signer = makeSigner(algorithm, true, false, publicKey);
        if (algorithm == null) {
            signer.update(hash, 16, 20);
        } else {
            signer.update(hash, 0, hash.length);
        }
        return signer.verifySignature(sigBytes);
    }

    public Signer createSigner(SignatureAndHashAlgorithm algorithm, AsymmetricKeyParameter privateKey) {
        return makeSigner(algorithm, false, true, privateKey);
    }

    public Signer createVerifyer(SignatureAndHashAlgorithm algorithm, AsymmetricKeyParameter publicKey) {
        return makeSigner(algorithm, false, false, publicKey);
    }

    /* access modifiers changed from: protected */
    public CipherParameters makeInitParameters(boolean forSigning, CipherParameters cp) {
        return cp;
    }

    /* access modifiers changed from: protected */
    public Signer makeSigner(SignatureAndHashAlgorithm algorithm, boolean raw, boolean forSigning, CipherParameters cp) {
        if ((algorithm != null) != TlsUtils.isTLSv12(this.context)) {
            throw new IllegalStateException();
        } else if (algorithm == null || algorithm.getSignature() == getSignatureAlgorithm()) {
            short hashAlgorithm = algorithm == null ? 2 : algorithm.getHash();
            Signer s = new DSADigestSigner(createDSAImpl(hashAlgorithm), raw ? new NullDigest() : TlsUtils.createHash(hashAlgorithm));
            s.init(forSigning, makeInitParameters(forSigning, cp));
            return s;
        } else {
            throw new IllegalStateException();
        }
    }
}
