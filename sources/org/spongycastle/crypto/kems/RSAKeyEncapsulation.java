package org.spongycastle.crypto.kems;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.KeyEncapsulation;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.util.BigIntegers;

public class RSAKeyEncapsulation implements KeyEncapsulation {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static final BigInteger ZERO = BigInteger.valueOf(0);
    private DerivationFunction kdf;
    private RSAKeyParameters key;
    private SecureRandom rnd;

    public RSAKeyEncapsulation(DerivationFunction kdf2, SecureRandom rnd2) {
        this.kdf = kdf2;
        this.rnd = rnd2;
    }

    public void init(CipherParameters key2) throws IllegalArgumentException {
        if (!(key2 instanceof RSAKeyParameters)) {
            throw new IllegalArgumentException("RSA key required");
        }
        this.key = (RSAKeyParameters) key2;
    }

    public CipherParameters encrypt(byte[] out, int outOff, int keyLen) throws IllegalArgumentException {
        if (this.key.isPrivate()) {
            throw new IllegalArgumentException("Public key required for encryption");
        }
        BigInteger n = this.key.getModulus();
        BigInteger e = this.key.getExponent();
        BigInteger r = BigIntegers.createRandomInRange(ZERO, n.subtract(ONE), this.rnd);
        byte[] C = BigIntegers.asUnsignedByteArray((n.bitLength() + 7) / 8, r.modPow(e, n));
        System.arraycopy(C, 0, out, outOff, C.length);
        return generateKey(n, r, keyLen);
    }

    public CipherParameters encrypt(byte[] out, int keyLen) {
        return encrypt(out, 0, keyLen);
    }

    public CipherParameters decrypt(byte[] in, int inOff, int inLen, int keyLen) throws IllegalArgumentException {
        if (!this.key.isPrivate()) {
            throw new IllegalArgumentException("Private key required for decryption");
        }
        BigInteger n = this.key.getModulus();
        BigInteger d = this.key.getExponent();
        byte[] C = new byte[inLen];
        System.arraycopy(in, inOff, C, 0, C.length);
        return generateKey(n, new BigInteger(1, C).modPow(d, n), keyLen);
    }

    public CipherParameters decrypt(byte[] in, int keyLen) {
        return decrypt(in, 0, in.length, keyLen);
    }

    /* access modifiers changed from: protected */
    public KeyParameter generateKey(BigInteger n, BigInteger r, int keyLen) {
        this.kdf.init(new KDFParameters(BigIntegers.asUnsignedByteArray((n.bitLength() + 7) / 8, r), null));
        byte[] K = new byte[keyLen];
        this.kdf.generateBytes(K, 0, K.length);
        return new KeyParameter(K);
    }
}
