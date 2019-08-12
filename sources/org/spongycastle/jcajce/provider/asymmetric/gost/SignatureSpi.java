package org.spongycastle.jcajce.provider.asymmetric.gost;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.spec.AlgorithmParameterSpec;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DSA;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.GOST3411Digest;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.signers.GOST3410Signer;
import org.spongycastle.jcajce.provider.asymmetric.util.ECUtil;
import org.spongycastle.jcajce.provider.asymmetric.util.GOST3410Util;
import org.spongycastle.jce.interfaces.ECKey;
import org.spongycastle.jce.interfaces.ECPublicKey;
import org.spongycastle.jce.interfaces.GOST3410Key;
import org.spongycastle.jce.provider.BouncyCastleProvider;

public class SignatureSpi extends java.security.SignatureSpi implements PKCSObjectIdentifiers, X509ObjectIdentifiers {
    private Digest digest = new GOST3411Digest();
    private SecureRandom random;
    private DSA signer = new GOST3410Signer();

    /* access modifiers changed from: protected */
    public void engineInitVerify(PublicKey publicKey) throws InvalidKeyException {
        CipherParameters param;
        if (publicKey instanceof ECPublicKey) {
            param = ECUtil.generatePublicKeyParameter(publicKey);
        } else if (publicKey instanceof GOST3410Key) {
            param = GOST3410Util.generatePublicKeyParameter(publicKey);
        } else {
            try {
                PublicKey publicKey2 = BouncyCastleProvider.getPublicKey(SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()));
                if (publicKey2 instanceof ECPublicKey) {
                    param = ECUtil.generatePublicKeyParameter(publicKey2);
                } else {
                    throw new InvalidKeyException("can't recognise key type in DSA based signer");
                }
            } catch (Exception e) {
                throw new InvalidKeyException("can't recognise key type in DSA based signer");
            }
        }
        this.digest.reset();
        this.signer.init(false, param);
    }

    /* access modifiers changed from: protected */
    public void engineInitSign(PrivateKey privateKey, SecureRandom random2) throws InvalidKeyException {
        this.random = random2;
        engineInitSign(privateKey);
    }

    /* access modifiers changed from: protected */
    public void engineInitSign(PrivateKey privateKey) throws InvalidKeyException {
        CipherParameters param;
        if (privateKey instanceof ECKey) {
            param = ECUtil.generatePrivateKeyParameter(privateKey);
        } else {
            param = GOST3410Util.generatePrivateKeyParameter(privateKey);
        }
        this.digest.reset();
        if (this.random != null) {
            this.signer.init(true, new ParametersWithRandom(param, this.random));
        } else {
            this.signer.init(true, param);
        }
    }

    /* access modifiers changed from: protected */
    public void engineUpdate(byte b) throws SignatureException {
        this.digest.update(b);
    }

    /* access modifiers changed from: protected */
    public void engineUpdate(byte[] b, int off, int len) throws SignatureException {
        this.digest.update(b, off, len);
    }

    /* access modifiers changed from: protected */
    public byte[] engineSign() throws SignatureException {
        byte[] hash = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(hash, 0);
        try {
            byte[] sigBytes = new byte[64];
            BigInteger[] sig = this.signer.generateSignature(hash);
            byte[] r = sig[0].toByteArray();
            byte[] s = sig[1].toByteArray();
            if (s[0] != 0) {
                System.arraycopy(s, 0, sigBytes, 32 - s.length, s.length);
            } else {
                System.arraycopy(s, 1, sigBytes, 32 - (s.length - 1), s.length - 1);
            }
            if (r[0] != 0) {
                System.arraycopy(r, 0, sigBytes, 64 - r.length, r.length);
            } else {
                System.arraycopy(r, 1, sigBytes, 64 - (r.length - 1), r.length - 1);
            }
            return sigBytes;
        } catch (Exception e) {
            throw new SignatureException(e.toString());
        }
    }

    /* access modifiers changed from: protected */
    public boolean engineVerify(byte[] sigBytes) throws SignatureException {
        byte[] hash = new byte[this.digest.getDigestSize()];
        this.digest.doFinal(hash, 0);
        try {
            byte[] r = new byte[32];
            byte[] s = new byte[32];
            System.arraycopy(sigBytes, 0, s, 0, 32);
            System.arraycopy(sigBytes, 32, r, 0, 32);
            BigInteger[] sig = {new BigInteger(1, r), new BigInteger(1, s)};
            return this.signer.verifySignature(hash, sig[0], sig[1]);
        } catch (Exception e) {
            throw new SignatureException("error decoding signature bytes.");
        }
    }

    /* access modifiers changed from: protected */
    public void engineSetParameter(AlgorithmParameterSpec params) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    /* access modifiers changed from: protected */
    public void engineSetParameter(String param, Object value) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }

    /* access modifiers changed from: protected */
    public Object engineGetParameter(String param) {
        throw new UnsupportedOperationException("engineSetParameter unsupported");
    }
}
