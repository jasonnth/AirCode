package org.spongycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.pqc.crypto.MessageEncryptor;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2Vector;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.GoppaCode;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.spongycastle.pqc.math.linearalgebra.Vector;

public class McEliecePKCSCipher implements MessageEncryptor {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.1";
    public int cipherTextSize;

    /* renamed from: k */
    private int f7112k;
    McElieceKeyParameters key;
    public int maxPlainTextSize;

    /* renamed from: n */
    private int f7113n;

    /* renamed from: sr */
    private SecureRandom f7114sr;

    /* renamed from: t */
    private int f7115t;

    public void init(boolean forSigning, CipherParameters param) {
        if (!forSigning) {
            this.key = (McEliecePrivateKeyParameters) param;
            initCipherDecrypt((McEliecePrivateKeyParameters) this.key);
        } else if (param instanceof ParametersWithRandom) {
            ParametersWithRandom rParam = (ParametersWithRandom) param;
            this.f7114sr = rParam.getRandom();
            this.key = (McEliecePublicKeyParameters) rParam.getParameters();
            initCipherEncrypt((McEliecePublicKeyParameters) this.key);
        } else {
            this.f7114sr = new SecureRandom();
            this.key = (McEliecePublicKeyParameters) param;
            initCipherEncrypt((McEliecePublicKeyParameters) this.key);
        }
    }

    public int getKeySize(McElieceKeyParameters key2) {
        if (key2 instanceof McEliecePublicKeyParameters) {
            return ((McEliecePublicKeyParameters) key2).getN();
        }
        if (key2 instanceof McEliecePrivateKeyParameters) {
            return ((McEliecePrivateKeyParameters) key2).getN();
        }
        throw new IllegalArgumentException("unsupported type");
    }

    public void initCipherEncrypt(McEliecePublicKeyParameters pubKey) {
        this.f7114sr = this.f7114sr != null ? this.f7114sr : new SecureRandom();
        this.f7113n = pubKey.getN();
        this.f7112k = pubKey.getK();
        this.f7115t = pubKey.getT();
        this.cipherTextSize = this.f7113n >> 3;
        this.maxPlainTextSize = this.f7112k >> 3;
    }

    public void initCipherDecrypt(McEliecePrivateKeyParameters privKey) {
        this.f7113n = privKey.getN();
        this.f7112k = privKey.getK();
        this.maxPlainTextSize = this.f7112k >> 3;
        this.cipherTextSize = this.f7113n >> 3;
    }

    public byte[] messageEncrypt(byte[] input) {
        GF2Vector m = computeMessageRepresentative(input);
        return ((GF2Vector) ((McEliecePublicKeyParameters) this.key).getG().leftMultiply((Vector) m).add(new GF2Vector(this.f7113n, this.f7115t, this.f7114sr))).getEncoded();
    }

    private GF2Vector computeMessageRepresentative(byte[] input) {
        int i;
        int i2 = this.maxPlainTextSize;
        if ((this.f7112k & 7) != 0) {
            i = 1;
        } else {
            i = 0;
        }
        byte[] data = new byte[(i + i2)];
        System.arraycopy(input, 0, data, 0, input.length);
        data[input.length] = 1;
        return GF2Vector.OS2VP(this.f7112k, data);
    }

    public byte[] messageDecrypt(byte[] input) throws Exception {
        GF2Vector vec = GF2Vector.OS2VP(this.f7113n, input);
        McEliecePrivateKeyParameters privKey = (McEliecePrivateKeyParameters) this.key;
        GF2mField field = privKey.getField();
        PolynomialGF2mSmallM gp = privKey.getGoppaPoly();
        GF2Matrix sInv = privKey.getSInv();
        Permutation p1 = privKey.getP1();
        Permutation p2 = privKey.getP2();
        GF2Matrix h = privKey.getH();
        PolynomialGF2mSmallM[] qInv = privKey.getQInv();
        Permutation p = p1.rightMultiply(p2);
        GF2Vector cPInv = (GF2Vector) vec.multiply(p.computeInverse());
        GF2Vector z = GoppaCode.syndromeDecode((GF2Vector) h.rightMultiply((Vector) cPInv), field, gp, qInv);
        GF2Vector mSG = (GF2Vector) ((GF2Vector) cPInv.add(z)).multiply(p1);
        GF2Vector z2 = (GF2Vector) z.multiply(p);
        return computeMessage((GF2Vector) sInv.leftMultiply((Vector) mSG.extractRightVector(this.f7112k)));
    }

    private byte[] computeMessage(GF2Vector mr) throws Exception {
        byte[] mrBytes = mr.getEncoded();
        int index = mrBytes.length - 1;
        while (index >= 0 && mrBytes[index] == 0) {
            index--;
        }
        if (index < 0 || mrBytes[index] != 1) {
            throw new Exception("Bad Padding: invalid ciphertext");
        }
        byte[] mBytes = new byte[index];
        System.arraycopy(mrBytes, 0, mBytes, 0, index);
        return mBytes;
    }
}
