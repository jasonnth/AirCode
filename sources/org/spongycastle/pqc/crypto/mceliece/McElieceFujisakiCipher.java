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

public class McElieceFujisakiCipher implements MessageEncryptor {
    private static final String DEFAULT_PRNG_NAME = "SHA1PRNG";
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2.1";

    /* renamed from: k */
    private int f7101k;
    McElieceCCA2KeyParameters key;
    private Digest messDigest;

    /* renamed from: n */
    private int f7102n;

    /* renamed from: sr */
    private SecureRandom f7103sr;

    /* renamed from: t */
    private int f7104t;

    public void init(boolean forSigning, CipherParameters param) {
        if (!forSigning) {
            this.key = (McElieceCCA2PrivateKeyParameters) param;
            initCipherDecrypt((McElieceCCA2PrivateKeyParameters) this.key);
        } else if (param instanceof ParametersWithRandom) {
            ParametersWithRandom rParam = (ParametersWithRandom) param;
            this.f7103sr = rParam.getRandom();
            this.key = (McElieceCCA2PublicKeyParameters) rParam.getParameters();
            initCipherEncrypt((McElieceCCA2PublicKeyParameters) this.key);
        } else {
            this.f7103sr = new SecureRandom();
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

    private void initCipherEncrypt(McElieceCCA2PublicKeyParameters pubKey) {
        this.f7103sr = this.f7103sr != null ? this.f7103sr : new SecureRandom();
        this.messDigest = pubKey.getParameters().getDigest();
        this.f7102n = pubKey.getN();
        this.f7101k = pubKey.getK();
        this.f7104t = pubKey.getT();
    }

    public void initCipherDecrypt(McElieceCCA2PrivateKeyParameters privKey) {
        this.messDigest = privKey.getParameters().getDigest();
        this.f7102n = privKey.getN();
        this.f7104t = privKey.getT();
    }

    public byte[] messageEncrypt(byte[] input) throws Exception {
        GF2Vector r = new GF2Vector(this.f7101k, this.f7103sr);
        byte[] rBytes = r.getEncoded();
        byte[] rm = ByteUtils.concatenate(rBytes, input);
        this.messDigest.update(rm, 0, rm.length);
        byte[] hrm = new byte[this.messDigest.getDigestSize()];
        this.messDigest.doFinal(hrm, 0);
        byte[] c1 = McElieceCCA2Primitives.encryptionPrimitive((McElieceCCA2PublicKeyParameters) this.key, r, Conversions.encode(this.f7102n, this.f7104t, hrm)).getEncoded();
        DigestRandomGenerator sr0 = new DigestRandomGenerator(new SHA1Digest());
        sr0.addSeedMaterial(rBytes);
        byte[] c2 = new byte[input.length];
        sr0.nextBytes(c2);
        for (int i = 0; i < input.length; i++) {
            c2[i] = (byte) (c2[i] ^ input[i]);
        }
        return ByteUtils.concatenate(c1, c2);
    }

    public byte[] messageDecrypt(byte[] input) throws Exception {
        int c1Len = (this.f7102n + 7) >> 3;
        int c2Len = input.length - c1Len;
        byte[][] c1c2 = ByteUtils.split(input, c1Len);
        byte[] c1 = c1c2[0];
        byte[] c2 = c1c2[1];
        GF2Vector[] decC1 = McElieceCCA2Primitives.decryptionPrimitive((McElieceCCA2PrivateKeyParameters) this.key, GF2Vector.OS2VP(this.f7102n, c1));
        byte[] rBytes = decC1[0].getEncoded();
        GF2Vector z = decC1[1];
        DigestRandomGenerator sr0 = new DigestRandomGenerator(new SHA1Digest());
        sr0.addSeedMaterial(rBytes);
        byte[] mBytes = new byte[c2Len];
        sr0.nextBytes(mBytes);
        for (int i = 0; i < c2Len; i++) {
            mBytes[i] = (byte) (mBytes[i] ^ c2[i]);
        }
        byte[] rmBytes = ByteUtils.concatenate(rBytes, mBytes);
        byte[] hrm = new byte[this.messDigest.getDigestSize()];
        this.messDigest.update(rmBytes, 0, rmBytes.length);
        this.messDigest.doFinal(hrm, 0);
        if (Conversions.encode(this.f7102n, this.f7104t, hrm).equals(z)) {
            return mBytes;
        }
        throw new Exception("Bad Padding: invalid ciphertext");
    }
}
