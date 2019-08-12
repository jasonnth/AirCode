package org.spongycastle.jcajce.provider.asymmetric.util;

import java.math.BigInteger;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.p325x9.ECNamedCurveTable;
import org.spongycastle.asn1.p325x9.X962Parameters;
import org.spongycastle.asn1.p325x9.X9ECParameters;
import org.spongycastle.crypto.p326ec.CustomNamedCurves;
import org.spongycastle.jcajce.provider.config.ProviderConfiguration;
import org.spongycastle.jce.spec.ECNamedCurveParameterSpec;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.math.field.FiniteField;
import org.spongycastle.math.field.Polynomial;
import org.spongycastle.math.field.PolynomialExtensionField;
import org.spongycastle.math.p332ec.ECAlgorithms;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECCurve.C5397Fp;
import org.spongycastle.math.p332ec.ECCurve.F2m;
import org.spongycastle.util.Arrays;

public class EC5Util {
    private static Map customCurves = new HashMap();

    static {
        Enumeration e = CustomNamedCurves.getNames();
        while (e.hasMoreElements()) {
            String name = (String) e.nextElement();
            X9ECParameters curveParams = ECNamedCurveTable.getByName(name);
            if (curveParams != null) {
                customCurves.put(curveParams.getCurve(), CustomNamedCurves.getByName(name).getCurve());
            }
        }
    }

    public static ECCurve getCurve(ProviderConfiguration configuration, X962Parameters params) {
        if (params.isNamedCurve()) {
            return ECUtil.getNamedCurveByOid(ASN1ObjectIdentifier.getInstance(params.getParameters())).getCurve();
        }
        if (params.isImplicitlyCA()) {
            return configuration.getEcImplicitlyCa().getCurve();
        }
        return X9ECParameters.getInstance(params.getParameters()).getCurve();
    }

    public static ECParameterSpec convertToSpec(X962Parameters params, ECCurve curve) {
        if (params.isNamedCurve()) {
            ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier) params.getParameters();
            X9ECParameters ecP = ECUtil.getNamedCurveByOid(oid);
            return new ECNamedCurveSpec(ECUtil.getCurveName(oid), convertCurve(curve, ecP.getSeed()), new ECPoint(ecP.getG().getAffineXCoord().toBigInteger(), ecP.getG().getAffineYCoord().toBigInteger()), ecP.getN(), ecP.getH());
        } else if (params.isImplicitlyCA()) {
            return null;
        } else {
            X9ECParameters ecP2 = X9ECParameters.getInstance(params.getParameters());
            EllipticCurve ellipticCurve = convertCurve(curve, ecP2.getSeed());
            if (ecP2.getH() != null) {
                return new ECParameterSpec(ellipticCurve, new ECPoint(ecP2.getG().getAffineXCoord().toBigInteger(), ecP2.getG().getAffineYCoord().toBigInteger()), ecP2.getN(), ecP2.getH().intValue());
            }
            return new ECParameterSpec(ellipticCurve, new ECPoint(ecP2.getG().getAffineXCoord().toBigInteger(), ecP2.getG().getAffineYCoord().toBigInteger()), ecP2.getN(), 1);
        }
    }

    public static ECParameterSpec convertToSpec(X9ECParameters domainParameters) {
        return new ECParameterSpec(convertCurve(domainParameters.getCurve(), null), new ECPoint(domainParameters.getG().getAffineXCoord().toBigInteger(), domainParameters.getG().getAffineYCoord().toBigInteger()), domainParameters.getN(), domainParameters.getH().intValue());
    }

    public static EllipticCurve convertCurve(ECCurve curve, byte[] seed) {
        return new EllipticCurve(convertField(curve.getField()), curve.getA().toBigInteger(), curve.getB().toBigInteger(), null);
    }

    public static ECCurve convertCurve(EllipticCurve ec) {
        ECField field = ec.getField();
        BigInteger a = ec.getA();
        BigInteger b = ec.getB();
        if (field instanceof ECFieldFp) {
            C5397Fp curve = new C5397Fp(((ECFieldFp) field).getP(), a, b);
            if (customCurves.containsKey(curve)) {
                return (ECCurve) customCurves.get(curve);
            }
            return curve;
        }
        ECFieldF2m fieldF2m = (ECFieldF2m) field;
        int m = fieldF2m.getM();
        int[] ks = ECUtil.convertMidTerms(fieldF2m.getMidTermsOfReductionPolynomial());
        return new F2m(m, ks[0], ks[1], ks[2], a, b);
    }

    public static ECField convertField(FiniteField field) {
        if (ECAlgorithms.isFpField(field)) {
            return new ECFieldFp(field.getCharacteristic());
        }
        Polynomial poly = ((PolynomialExtensionField) field).getMinimalPolynomial();
        int[] exponents = poly.getExponentsPresent();
        return new ECFieldF2m(poly.getDegree(), Arrays.reverse(Arrays.copyOfRange(exponents, 1, exponents.length - 1)));
    }

    public static ECParameterSpec convertSpec(EllipticCurve ellipticCurve, org.spongycastle.jce.spec.ECParameterSpec spec) {
        if (!(spec instanceof ECNamedCurveParameterSpec)) {
            return new ECParameterSpec(ellipticCurve, new ECPoint(spec.getG().getAffineXCoord().toBigInteger(), spec.getG().getAffineYCoord().toBigInteger()), spec.getN(), spec.getH().intValue());
        }
        return new ECNamedCurveSpec(((ECNamedCurveParameterSpec) spec).getName(), ellipticCurve, new ECPoint(spec.getG().getAffineXCoord().toBigInteger(), spec.getG().getAffineYCoord().toBigInteger()), spec.getN(), spec.getH());
    }

    public static org.spongycastle.jce.spec.ECParameterSpec convertSpec(ECParameterSpec ecSpec, boolean withCompression) {
        ECCurve curve = convertCurve(ecSpec.getCurve());
        return new org.spongycastle.jce.spec.ECParameterSpec(curve, convertPoint(curve, ecSpec.getGenerator(), withCompression), ecSpec.getOrder(), BigInteger.valueOf((long) ecSpec.getCofactor()), ecSpec.getCurve().getSeed());
    }

    public static org.spongycastle.math.p332ec.ECPoint convertPoint(ECParameterSpec ecSpec, ECPoint point, boolean withCompression) {
        return convertPoint(convertCurve(ecSpec.getCurve()), point, withCompression);
    }

    public static org.spongycastle.math.p332ec.ECPoint convertPoint(ECCurve curve, ECPoint point, boolean withCompression) {
        return curve.createPoint(point.getAffineX(), point.getAffineY());
    }
}
