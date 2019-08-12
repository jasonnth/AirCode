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
import org.spongycastle.pqc.math.linearalgebra.Permutation;
import org.spongycastle.pqc.math.linearalgebra.PolynomialGF2mSmallM;
import org.spongycastle.pqc.math.linearalgebra.PolynomialRingGF2m;

public class McElieceCCA2KeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2";
    private int fieldPoly;
    private boolean initialized = false;

    /* renamed from: m */
    private int f7092m;
    private McElieceCCA2KeyGenerationParameters mcElieceCCA2Params;

    /* renamed from: n */
    private int f7093n;
    private SecureRandom random;

    /* renamed from: t */
    private int f7094t;

    private void initializeDefault() {
        init(new McElieceCCA2KeyGenerationParameters(new SecureRandom(), new McElieceCCA2Parameters()));
    }

    public void init(KeyGenerationParameters param) {
        this.mcElieceCCA2Params = (McElieceCCA2KeyGenerationParameters) param;
        this.random = new SecureRandom();
        this.f7092m = this.mcElieceCCA2Params.getParameters().getM();
        this.f7093n = this.mcElieceCCA2Params.getParameters().getN();
        this.f7094t = this.mcElieceCCA2Params.getParameters().getT();
        this.fieldPoly = this.mcElieceCCA2Params.getParameters().getFieldPoly();
        this.initialized = true;
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        if (!this.initialized) {
            initializeDefault();
        }
        GF2mField field = new GF2mField(this.f7092m, this.fieldPoly);
        PolynomialGF2mSmallM gp = new PolynomialGF2mSmallM(field, this.f7094t, 'I', this.random);
        PolynomialRingGF2m polynomialRingGF2m = new PolynomialRingGF2m(field, gp);
        PolynomialGF2mSmallM[] qInv = polynomialRingGF2m.getSquareRootMatrix();
        GF2Matrix h = GoppaCode.createCanonicalCheckMatrix(field, gp);
        MaMaPe mmp = GoppaCode.computeSystematicForm(h, this.random);
        GF2Matrix shortH = mmp.getSecondMatrix();
        Permutation p = mmp.getPermutation();
        GF2Matrix shortG = (GF2Matrix) shortH.computeTranspose();
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new McElieceCCA2PublicKeyParameters("1.3.6.1.4.1.8301.3.1.3.4.2", this.f7093n, this.f7094t, shortG, this.mcElieceCCA2Params.getParameters()), (AsymmetricKeyParameter) new McElieceCCA2PrivateKeyParameters("1.3.6.1.4.1.8301.3.1.3.4.2", this.f7093n, shortG.getNumRows(), field, gp, p, h, qInv, this.mcElieceCCA2Params.getParameters()));
    }
}
