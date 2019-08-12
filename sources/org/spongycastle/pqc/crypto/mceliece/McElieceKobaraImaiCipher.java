package org.spongycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.prng.DigestRandomGenerator;
import org.spongycastle.pqc.crypto.MessageEncryptor;
import org.spongycastle.pqc.math.linearalgebra.ByteUtils;
import org.spongycastle.pqc.math.linearalgebra.GF2Vector;
import org.spongycastle.pqc.math.linearalgebra.IntegerFunctions;

public class McElieceKobaraImaiCipher implements MessageEncryptor {
    private static final String DEFAULT_PRNG_NAME = "SHA1PRNG";
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2.3";
    public static final byte[] PUBLIC_CONSTANT = "a predetermined public constant".getBytes();

    /* renamed from: k */
    private int f7108k;
    McElieceCCA2KeyParameters key;
    private Digest messDigest;

    /* renamed from: n */
    private int f7109n;

    /* renamed from: sr */
    private SecureRandom f7110sr;

    /* renamed from: t */
    private int f7111t;

    public void init(boolean forSigning, CipherParameters param) {
        if (!forSigning) {
            this.key = (McElieceCCA2PrivateKeyParameters) param;
            initCipherDecrypt((McElieceCCA2PrivateKeyParameters) this.key);
        } else if (param instanceof ParametersWithRandom) {
            ParametersWithRandom rParam = (ParametersWithRandom) param;
            this.f7110sr = rParam.getRandom();
            this.key = (McElieceCCA2PublicKeyParameters) rParam.getParameters();
            initCipherEncrypt((McElieceCCA2PublicKeyParameters) this.key);
        } else {
            this.f7110sr = new SecureRandom();
            this.key = (McElieceCCA2PublicKeyParameters) param;
            initCipherEncrypt((McElieceCCA2PublicKeyParameters) this.key);
        }
    }

    public int getKeySize(McElieceCCA2KeyParameters key2) {
        if (key2 instanceof McElieceCCA2PublicKeyParameters) {
            return ((McElieceCCA2PublicKeyParameters) key2).getN();
        }
        if (key2 instanceof McElieceCCA2PrivateKeyParameters) {
            return ((McElieceCCA2PrivateKeyParameters) key2).getN();
        }
        throw new IllegalArgumentException("unsupported type");
    }

    private void initCipherEncrypt(McElieceCCA2PublicKeyParameters pubKey) {
        this.messDigest = pubKey.getParameters().getDigest();
        this.f7109n = pubKey.getN();
        this.f7108k = pubKey.getK();
        this.f7111t = pubKey.getT();
    }

    public void initCipherDecrypt(McElieceCCA2PrivateKeyParameters privKey) {
        this.messDigest = privKey.getParameters().getDigest();
        this.f7109n = privKey.getN();
        this.f7108k = privKey.getK();
        this.f7111t = privKey.getT();
    }

    public byte[] messageEncrypt(byte[] input) throws Exception {
        int c2Len = this.messDigest.getDigestSize();
        int c4Len = this.f7108k >> 3;
        int c5Len = (IntegerFunctions.binomial(this.f7109n, this.f7111t).bitLength() - 1) >> 3;
        int mLen = ((c4Len + c5Len) - c2Len) - PUBLIC_CONSTANT.length;
        if (input.length > mLen) {
            mLen = input.length;
        }
        int c1Len = mLen + PUBLIC_CONSTANT.length;
        int c6Len = ((c1Len + c2Len) - c4Len) - c5Len;
        byte[] mConst = new byte[c1Len];
        System.arraycopy(input, 0, mConst, 0, input.length);
        System.arraycopy(PUBLIC_CONSTANT, 0, mConst, mLen, PUBLIC_CONSTANT.length);
        byte[] r = new byte[c2Len];
        this.f7110sr.nextBytes(r);
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.addSeedMaterial(r);
        byte[] c1 = new byte[c1Len];
        digestRandomGenerator.nextBytes(c1);
        for (int i = c1Len - 1; i >= 0; i--) {
            c1[i] = (byte) (c1[i] ^ mConst[i]);
        }
        byte[] c2 = new byte[this.messDigest.getDigestSize()];
        this.messDigest.update(c1, 0, c1.length);
        this.messDigest.doFinal(c2, 0);
        for (int i2 = c2Len - 1; i2 >= 0; i2--) {
            c2[i2] = (byte) (c2[i2] ^ r[i2]);
        }
        byte[] c2c1 = ByteUtils.concatenate(c2, c1);
        byte[] c6 = new byte[0];
        if (c6Len > 0) {
            c6 = new byte[c6Len];
            System.arraycopy(c2c1, 0, c6, 0, c6Len);
        }
        byte[] c5 = new byte[c5Len];
        System.arraycopy(c2c1, c6Len, c5, 0, c5Len);
        byte[] c4 = new byte[c4Len];
        System.arraycopy(c2c1, c6Len + c5Len, c4, 0, c4Len);
        byte[] encC4 = McElieceCCA2Primitives.encryptionPrimitive((McElieceCCA2PublicKeyParameters) this.key, GF2Vector.OS2VP(this.f7108k, c4), Conversions.encode(this.f7109n, this.f7111t, c5)).getEncoded();
        if (c6Len > 0) {
            return ByteUtils.concatenate(c6, encC4);
        }
        return encC4;
    }

    public byte[] messageDecrypt(byte[] input) throws Exception {
        byte[] c6;
        byte[] encC4;
        int nDiv8 = this.f7109n >> 3;
        if (input.length < nDiv8) {
            throw new Exception("Bad Padding: Ciphertext too short.");
        }
        int c2Len = this.messDigest.getDigestSize();
        int c4Len = this.f7108k >> 3;
        int c6Len = input.length - nDiv8;
        if (c6Len > 0) {
            byte[][] c6EncC4 = ByteUtils.split(input, c6Len);
            c6 = c6EncC4[0];
            encC4 = c6EncC4[1];
        } else {
            c6 = new byte[0];
            encC4 = input;
        }
        GF2Vector[] c4z = McElieceCCA2Primitives.decryptionPrimitive((McElieceCCA2PrivateKeyParameters) this.key, GF2Vector.OS2VP(this.f7109n, encC4));
        byte[] c4 = c4z[0].getEncoded();
        GF2Vector z = c4z[1];
        if (c4.length > c4Len) {
            c4 = ByteUtils.subArray(c4, 0, c4Len);
        }
        byte[] c6c5c4 = ByteUtils.concatenate(ByteUtils.concatenate(c6, Conversions.decode(this.f7109n, this.f7111t, z)), c4);
        int c1Len = c6c5c4.length - c2Len;
        byte[][] c2c1 = ByteUtils.split(c6c5c4, c2Len);
        byte[] c2 = c2c1[0];
        byte[] c1 = c2c1[1];
        byte[] rPrime = new byte[this.messDigest.getDigestSize()];
        this.messDigest.update(c1, 0, c1.length);
        this.messDigest.doFinal(rPrime, 0);
        for (int i = c2Len - 1; i >= 0; i--) {
            rPrime[i] = (byte) (rPrime[i] ^ c2[i]);
        }
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.addSeedMaterial(rPrime);
        byte[] mConstPrime = new byte[c1Len];
        digestRandomGenerator.nextBytes(mConstPrime);
        for (int i2 = c1Len - 1; i2 >= 0; i2--) {
            mConstPrime[i2] = (byte) (mConstPrime[i2] ^ c1[i2]);
        }
        if (mConstPrime.length < c1Len) {
            throw new Exception("Bad Padding: invalid ciphertext");
        }
        byte[][] temp = ByteUtils.split(mConstPrime, c1Len - PUBLIC_CONSTANT.length);
        byte[] mr = temp[0];
        if (ByteUtils.equals(temp[1], PUBLIC_CONSTANT)) {
            return mr;
        }
        throw new Exception("Bad Padding: invalid ciphertext");
    }
}
