package org.spongycastle.pqc.crypto.mceliece;

import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2Vector;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.GoppaCode;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.spongycastle.pqc.math.linearalgebra.Vector;

public final class McElieceCCA2Primitives {
    private McElieceCCA2Primitives() {
    }

    public static GF2Vector encryptionPrimitive(McElieceCCA2PublicKeyParameters pubKey, GF2Vector m, GF2Vector z) {
        return (GF2Vector) pubKey.getMatrixG().leftMultiplyLeftCompactForm(m).add(z);
    }

    public static GF2Vector[] decryptionPrimitive(McElieceCCA2PrivateKeyParameters privKey, GF2Vector c) {
        int k = privKey.getK();
        Permutation p = privKey.getP();
        GF2mField field = privKey.getField();
        PolynomialGF2mSmallM gp = privKey.getGoppaPoly();
        GF2Matrix h = privKey.getH();
        GF2Vector cPInv = (GF2Vector) c.multiply(p.computeInverse());
        GF2Vector errors = GoppaCode.syndromeDecode((GF2Vector) h.rightMultiply((Vector) cPInv), field, gp, privKey.getQInv());
        GF2Vector mG = (GF2Vector) ((GF2Vector) cPInv.add(errors)).multiply(p);
        return new GF2Vector[]{mG.extractRightVector(k), (GF2Vector) errors.multiply(p)};
    }
}
