package org.spongycastle.pqc.crypto.mceliece;

import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;
import org.spongycastle.pqc.math.linearalgebra.GF2mField;
import org.spongycastle.pqc.math.linearalgebra.GoppaCode;
import org.spongycastle.pqc.math.linearalgebra.GoppaCode.MaMaPe;
import org.spongycastle.pqc.math.linearalgebra.Matrix;
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.spongycastle.pqc.math.linearalgebra.PolynomialRingGF2m;

public class McElieceKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.1";
    private int fieldPoly;
    private boolean initialized = false;

    /* renamed from: m */
    private int f7105m;
    private McElieceKeyGenerationParameters mcElieceParams;

    /* renamed from: n */
    private int f7106n;
    private SecureRandom random;

    /* renamed from: t */
    private int f7107t;

    private void initializeDefault() {
        initialize(new McElieceKeyGenerationParameters(new SecureRandom(), new McElieceParameters()));
    }

    private void initialize(KeyGenerationParameters param) {
        this.mcElieceParams = (McElieceKeyGenerationParameters) param;
        this.random = new SecureRandom();
        this.f7105m = this.mcElieceParams.getParameters().getM();
        this.f7106n = this.mcElieceParams.getParameters().getN();
        this.f7107t = this.mcElieceParams.getParameters().getT();
        this.fieldPoly = this.mcElieceParams.getParameters().getFieldPoly();
        this.initialized = true;
    }

    private AsymmetricCipherKeyPair genKeyPair() {
        if (!this.initialized) {
            initializeDefault();
        }
        GF2mField field = new GF2mField(this.f7105m, this.fieldPoly);
        PolynomialGF2mSmallM gp = new PolynomialGF2mSmallM(field, this.f7107t, 'I', this.random);
        PolynomialRingGF2m polynomialRingGF2m = new PolynomialRingGF2m(field, gp);
        PolynomialGF2mSmallM[] sqRootMatrix = polynomialRingGF2m.getSquareRootMatrix();
        GF2Matrix h = GoppaCode.createCanonicalCheckMatrix(field, gp);
        MaMaPe mmp = GoppaCode.computeSystematicForm(h, this.random);
        GF2Matrix shortH = mmp.getSecondMatrix();
        Permutation p1 = mmp.getPermutation();
        GF2Matrix shortG = (GF2Matrix) shortH.computeTranspose();
        GF2Matrix gPrime = shortG.extendLeftCompactForm();
        int k = shortG.getNumRows();
        GF2Matrix[] matrixSandInverse = GF2Matrix.createRandomRegularMatrixAndItsInverse(k, this.random);
        Permutation p2 = new Permutation(this.f7106n, this.random);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new McEliecePublicKeyParameters("1.3.6.1.4.1.8301.3.1.3.4.1", this.f7106n, this.f7107t, (GF2Matrix) ((GF2Matrix) matrixSandInverse[0].rightMultiply((Matrix) gPrime)).rightMultiply(p2), this.mcElieceParams.getParameters()), (AsymmetricKeyParameter) new McEliecePrivateKeyParameters("1.3.6.1.4.1.8301.3.1.3.4.1", this.f7106n, k, field, gp, matrixSandInverse[1], p1, p2, h, sqRootMatrix, this.mcElieceParams.getParameters()));
    }

    public void init(KeyGenerationParameters param) {
        initialize(param);
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }
}
