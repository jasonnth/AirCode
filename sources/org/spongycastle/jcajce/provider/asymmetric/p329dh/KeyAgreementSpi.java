package org.spongycastle.jcajce.provider.asymmetric.p329dh;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.agreement.kdf.DHKEKGenerator;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.jcajce.provider.asymmetric.util.BaseAgreementSpi;
import org.spongycastle.jcajce.spec.UserKeyingMaterialSpec;

/* renamed from: org.spongycastle.jcajce.provider.asymmetric.dh.KeyAgreementSpi */
public class KeyAgreementSpi extends BaseAgreementSpi {

    /* renamed from: g */
    private BigInteger f6897g;

    /* renamed from: p */
    private BigInteger f6898p;

    /* renamed from: x */
    private BigInteger f6899x;

    /* renamed from: org.spongycastle.jcajce.provider.asymmetric.dh.KeyAgreementSpi$DHwithRFC2631KDF */
    public static class DHwithRFC2631KDF extends KeyAgreementSpi {
        public DHwithRFC2631KDF() {
            super("DHwithRFC2631KDF", new DHKEKGenerator(new SHA1Digest()));
        }
    }

    public KeyAgreementSpi() {
        super("Diffie-Hellman", null);
    }

    public KeyAgreementSpi(String kaAlgorithm, DerivationFunction kdf) {
        super(kaAlgorithm, kdf);
    }

    /* access modifiers changed from: protected */
    public byte[] bigIntToBytes(BigInteger r) {
        int expectedLength = (this.f6898p.bitLength() + 7) / 8;
        byte[] tmp = r.toByteArray();
        if (tmp.length == expectedLength) {
            return tmp;
        }
        if (tmp[0] == 0 && tmp.length == expectedLength + 1) {
            byte[] rv = new byte[(tmp.length - 1)];
            System.arraycopy(tmp, 1, rv, 0, rv.length);
            return rv;
        }
        byte[] rv2 = new byte[expectedLength];
        System.arraycopy(tmp, 0, rv2, rv2.length - tmp.length, tmp.length);
        return rv2;
    }

    /* access modifiers changed from: protected */
    public Key engineDoPhase(Key key, boolean lastPhase) throws InvalidKeyException, IllegalStateException {
        if (this.f6899x == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        } else if (!(key instanceof DHPublicKey)) {
            throw new InvalidKeyException("DHKeyAgreement doPhase requires DHPublicKey");
        } else {
            DHPublicKey pubKey = (DHPublicKey) key;
            if (!pubKey.getParams().getG().equals(this.f6897g) || !pubKey.getParams().getP().equals(this.f6898p)) {
                throw new InvalidKeyException("DHPublicKey not for this KeyAgreement!");
            } else if (lastPhase) {
                this.result = ((DHPublicKey) key).getY().modPow(this.f6899x, this.f6898p);
                return null;
            } else {
                this.result = ((DHPublicKey) key).getY().modPow(this.f6899x, this.f6898p);
                return new BCDHPublicKey(this.result, pubKey.getParams());
            }
        }
    }

    /* access modifiers changed from: protected */
    public byte[] engineGenerateSecret() throws IllegalStateException {
        if (this.f6899x != null) {
            return super.engineGenerateSecret();
        }
        throw new IllegalStateException("Diffie-Hellman not initialised.");
    }

    /* access modifiers changed from: protected */
    public int engineGenerateSecret(byte[] sharedSecret, int offset) throws IllegalStateException, ShortBufferException {
        if (this.f6899x != null) {
            return super.engineGenerateSecret(sharedSecret, offset);
        }
        throw new IllegalStateException("Diffie-Hellman not initialised.");
    }

    /* access modifiers changed from: protected */
    public SecretKey engineGenerateSecret(String algorithm) throws NoSuchAlgorithmException {
        if (this.f6899x == null) {
            throw new IllegalStateException("Diffie-Hellman not initialised.");
        }
        byte[] res = bigIntToBytes(this.result);
        if (algorithm.equals("TlsPremasterSecret")) {
            return new SecretKeySpec(trimZeroes(res), algorithm);
        }
        return super.engineGenerateSecret(algorithm);
    }

    /* access modifiers changed from: protected */
    public void engineInit(Key key, AlgorithmParameterSpec params, SecureRandom random) throws InvalidKeyException, InvalidAlgorithmParameterException {
        if (!(key instanceof DHPrivateKey)) {
            throw new InvalidKeyException("DHKeyAgreement requires DHPrivateKey for initialisation");
        }
        DHPrivateKey privKey = (DHPrivateKey) key;
        if (params == null) {
            this.f6898p = privKey.getParams().getP();
            this.f6897g = privKey.getParams().getG();
        } else if (params instanceof DHParameterSpec) {
            DHParameterSpec p = (DHParameterSpec) params;
            this.f6898p = p.getP();
            this.f6897g = p.getG();
        } else if (params instanceof UserKeyingMaterialSpec) {
            this.f6898p = privKey.getParams().getP();
            this.f6897g = privKey.getParams().getG();
            this.ukmParameters = ((UserKeyingMaterialSpec) params).getUserKeyingMaterial();
        } else {
            throw new InvalidAlgorithmParameterException("DHKeyAgreement only accepts DHParameterSpec");
        }
        BigInteger x = privKey.getX();
        this.result = x;
        this.f6899x = x;
    }

    /* access modifiers changed from: protected */
    public void engineInit(Key key, SecureRandom random) throws InvalidKeyException {
        if (!(key instanceof DHPrivateKey)) {
            throw new InvalidKeyException("DHKeyAgreement requires DHPrivateKey");
        }
        DHPrivateKey privKey = (DHPrivateKey) key;
        this.f6898p = privKey.getParams().getP();
        this.f6897g = privKey.getParams().getG();
        BigInteger x = privKey.getX();
        this.result = x;
        this.f6899x = x;
    }
}
