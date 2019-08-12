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

public class McEliecePointchevalCipher implements MessageEncryptor {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2.2";

    /* renamed from: k */
    private int f7119k;
    McElieceCCA2KeyParameters key;
    private Digest messDigest;

    /* renamed from: n */
    private int f7120n;

    /* renamed from: sr */
    private SecureRandom f7121sr;

    /* renamed from: t */
    private int f7122t;

    public void init(boolean forSigning, CipherParameters param) {
        if (!forSigning) {
            this.key = (McElieceCCA2PrivateKeyParameters) param;
            initCipherDecrypt((McElieceCCA2PrivateKeyParameters) this.key);
        } else if (param instanceof ParametersWithRandom) {
            ParametersWithRandom rParam = (ParametersWithRandom) param;
            this.f7121sr = rParam.getRandom();
            this.key = (McElieceCCA2PublicKeyParameters) rParam.getParameters();
            initCipherEncrypt((McElieceCCA2PublicKeyParameters) this.key);
        } else {
            this.f7121sr = new SecureRandom();
            this.key = (McElieceCCA2PublicKeyParameters) param;
            initCipherEncrypt((McElieceCCA2PublicKeyParameters) this.key);
        }
    }

    public int getKeySize(McElieceCCA2KeyParameters key2) throws IllegalArgumentException {
        if (key2 instanceof McElieceCCA2PublicKeyParameters) {
            return ((McElieceCCA2PublicKeyParameters) key2).getN();
        }
        if (key2 instanceof McElieceCCA2PrivateKeyParameters) {
            return ((McElieceCCA2PrivateKeyParameters) key2).getN();
        }
        throw new IllegalArgumentException("unsupported type");
    }

    /* access modifiers changed from: protected */
    public int decryptOutputSize(int inLen) {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int encryptOutputSize(int inLen) {
        return 0;
    }

    public void initCipherEncrypt(McElieceCCA2PublicKeyParameters pubKey) {
        this.f7121sr = this.f7121sr != null ? this.f7121sr : new SecureRandom();
        this.messDigest = pubKey.getParameters().getDigest();
        this.f7120n = pubKey.getN();
        this.f7119k = pubKey.getK();
        this.f7122t = pubKey.getT();
    }

    public void initCipherDecrypt(McElieceCCA2PrivateKeyParameters privKey) {
        this.messDigest = privKey.getParameters().getDigest();
        this.f7120n = privKey.getN();
        this.f7119k = privKey.getK();
        this.f7122t = privKey.getT();
    }

    public byte[] messageEncrypt(byte[] input) throws Exception {
        int kDiv8 = this.f7119k >> 3;
        byte[] r = new byte[kDiv8];
        this.f7121sr.nextBytes(r);
        GF2Vector rPrime = new GF2Vector(this.f7119k, this.f7121sr);
        byte[] rPrimeBytes = rPrime.getEncoded();
        byte[] mr = ByteUtils.concatenate(input, r);
        this.messDigest.update(mr, 0, mr.length);
        byte[] hmr = new byte[this.messDigest.getDigestSize()];
        this.messDigest.doFinal(hmr, 0);
        byte[] c1 = McElieceCCA2Primitives.encryptionPrimitive((McElieceCCA2PublicKeyParameters) this.key, rPrime, Conversions.encode(this.f7120n, this.f7122t, hmr)).getEncoded();
        DigestRandomGenerator sr0 = new DigestRandomGenerator(new SHA1Digest());
        sr0.addSeedMaterial(rPrimeBytes);
        byte[] c2 = new byte[(input.length + kDiv8)];
        sr0.nextBytes(c2);
        for (int i = 0; i < input.length; i++) {
            c2[i] = (byte) (c2[i] ^ input[i]);
        }
        for (int i2 = 0; i2 < kDiv8; i2++) {
            int length = input.length + i2;
            c2[length] = (byte) (c2[length] ^ r[i2]);
        }
        return ByteUtils.concatenate(c1, c2);
    }

    public byte[] messageDecrypt(byte[] input) throws Exception {
        int c1Len = (this.f7120n + 7) >> 3;
        int c2Len = input.length - c1Len;
        byte[][] c1c2 = ByteUtils.split(input, c1Len);
        byte[] c1 = c1c2[0];
        byte[] c2 = c1c2[1];
        GF2Vector[] c1Dec = McElieceCCA2Primitives.decryptionPrimitive((McElieceCCA2PrivateKeyParameters) this.key, GF2Vector.OS2VP(this.f7120n, c1));
        byte[] rPrimeBytes = c1Dec[0].getEncoded();
        GF2Vector z = c1Dec[1];
        DigestRandomGenerator digestRandomGenerator = new DigestRandomGenerator(new SHA1Digest());
        digestRandomGenerator.addSeedMaterial(rPrimeBytes);
        byte[] mrBytes = new byte[c2Len];
        digestRandomGenerator.nextBytes(mrBytes);
        for (int i = 0; i < c2Len; i++) {
            mrBytes[i] = (byte) (mrBytes[i] ^ c2[i]);
        }
        this.messDigest.update(mrBytes, 0, mrBytes.length);
        byte[] hmr = new byte[this.messDigest.getDigestSize()];
        this.messDigest.doFinal(hmr, 0);
        if (Conversions.encode(this.f7120n, this.f7122t, hmr).equals(z)) {
            return ByteUtils.split(mrBytes, c2Len - (this.f7119k >> 3))[0];
        }
        throw new Exception("Bad Padding: Invalid ciphertext.");
    }
}
