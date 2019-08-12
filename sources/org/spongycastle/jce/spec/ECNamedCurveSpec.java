package org.spongycastle.jce.spec;

import java.math.BigInteger;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import org.spongycastle.math.field.FiniteField;
import org.spongycastle.math.field.Polynomial;
import org.spongycastle.math.field.PolynomialExtensionField;
import org.spongycastle.math.p332ec.ECAlgorithms;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.util.Arrays;

public class ECNamedCurveSpec extends ECParameterSpec {
    private String name;

    private static EllipticCurve convertCurve(ECCurve curve, byte[] seed) {
        return new EllipticCurve(convertField(curve.getField()), curve.getA().toBigInteger(), curve.getB().toBigInteger(), seed);
    }

    private static ECField convertField(FiniteField field) {
        if (ECAlgorithms.isFpField(field)) {
            return new ECFieldFp(field.getCharacteristic());
        }
        Polynomial poly = ((PolynomialExtensionField) field).getMinimalPolynomial();
        int[] exponents = poly.getExponentsPresent();
        return new ECFieldF2m(poly.getDegree(), Arrays.reverse(Arrays.copyOfRange(exponents, 1, exponents.length - 1)));
    }

    private static ECPoint convertPoint(org.spongycastle.math.p332ec.ECPoint g) {
        org.spongycastle.math.p332ec.ECPoint g2 = g.normalize();
        return new ECPoint(g2.getAffineXCoord().toBigInteger(), g2.getAffineYCoord().toBigInteger());
    }

    public ECNamedCurveSpec(String name2, ECCurve curve, org.spongycastle.math.p332ec.ECPoint g, BigInteger n) {
        super(convertCurve(curve, null), convertPoint(g), n, 1);
        this.name = name2;
    }

    public ECNamedCurveSpec(String name2, EllipticCurve curve, ECPoint g, BigInteger n) {
        super(curve, g, n, 1);
        this.name = name2;
    }

    public ECNamedCurveSpec(String name2, ECCurve curve, org.spongycastle.math.p332ec.ECPoint g, BigInteger n, BigInteger h) {
        super(convertCurve(curve, null), convertPoint(g), n, h.intValue());
        this.name = name2;
    }

    public ECNamedCurveSpec(String name2, EllipticCurve curve, ECPoint g, BigInteger n, BigInteger h) {
        super(curve, g, n, h.intValue());
        this.name = name2;
    }

    public ECNamedCurveSpec(String name2, ECCurve curve, org.spongycastle.math.p332ec.ECPoint g, BigInteger n, BigInteger h, byte[] seed) {
        super(convertCurve(curve, seed), convertPoint(g), n, h.intValue());
        this.name = name2;
    }

    public String getName() {
        return this.name;
    }
}
