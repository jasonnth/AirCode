package org.spongycastle.math.p332ec.tools;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.asn1.p325x9.ECNamedCurveTable;
import org.spongycastle.asn1.p325x9.X9ECParameters;
import org.spongycastle.math.p332ec.ECAlgorithms;
import org.spongycastle.math.p332ec.ECConstants;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECFieldElement.C5398Fp;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.util.BigIntegers;

/* renamed from: org.spongycastle.math.ec.tools.DiscoverEndomorphisms */
public class DiscoverEndomorphisms {
    private static final int radix = 16;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Expected a list of curve names as arguments");
            return;
        }
        for (String discoverEndomorphisms : args) {
            discoverEndomorphisms(discoverEndomorphisms);
        }
    }

    public static void discoverEndomorphisms(X9ECParameters x9) {
        if (x9 == null) {
            throw new NullPointerException("x9");
        }
        ECCurve c = x9.getCurve();
        if (ECAlgorithms.isFpCurve(c)) {
            BigInteger characteristic = c.getField().getCharacteristic();
            if (c.getA().isZero() && characteristic.mod(ECConstants.THREE).equals(ECConstants.ONE)) {
                System.out.println("Curve has a 'GLV Type B' endomorphism with these parameters:");
                printGLVTypeBParameters(x9);
            }
        }
    }

    private static void discoverEndomorphisms(String curveName) {
        X9ECParameters x9 = ECNamedCurveTable.getByName(curveName);
        if (x9 == null) {
            System.err.println("Unknown curve: " + curveName);
            return;
        }
        ECCurve c = x9.getCurve();
        if (ECAlgorithms.isFpCurve(c)) {
            BigInteger characteristic = c.getField().getCharacteristic();
            if (c.getA().isZero() && characteristic.mod(ECConstants.THREE).equals(ECConstants.ONE)) {
                System.out.println("Curve '" + curveName + "' has a 'GLV Type B' endomorphism with these parameters:");
                printGLVTypeBParameters(x9);
            }
        }
    }

    private static void printGLVTypeBParameters(X9ECParameters x9) {
        BigInteger[] lambdas = solveQuadraticEquation(x9.getN(), ECConstants.ONE, ECConstants.ONE);
        ECFieldElement[] betas = findBetaValues(x9.getCurve());
        printGLVTypeBParameters(x9, lambdas[0], betas);
        System.out.println("OR");
        printGLVTypeBParameters(x9, lambdas[1], betas);
    }

    private static void printGLVTypeBParameters(X9ECParameters x9, BigInteger lambda, ECFieldElement[] betas) {
        ECPoint G = x9.getG().normalize();
        ECPoint mapG = G.multiply(lambda).normalize();
        if (!G.getYCoord().equals(mapG.getYCoord())) {
            throw new IllegalStateException("Derivation of GLV Type B parameters failed unexpectedly");
        }
        ECFieldElement beta = betas[0];
        if (!G.getXCoord().multiply(beta).equals(mapG.getXCoord())) {
            beta = betas[1];
            if (!G.getXCoord().multiply(beta).equals(mapG.getXCoord())) {
                throw new IllegalStateException("Derivation of GLV Type B parameters failed unexpectedly");
            }
        }
        BigInteger n = x9.getN();
        BigInteger[] rt = extEuclidGLV(n, lambda);
        BigInteger[] v1 = {rt[2], rt[3].negate()};
        BigInteger[] v2 = chooseShortest(new BigInteger[]{rt[0], rt[1].negate()}, new BigInteger[]{rt[4], rt[5].negate()});
        if (!isVectorBoundedBySqrt(v2, n) && areRelativelyPrime(v1[0], v1[1])) {
            BigInteger r = v1[0];
            BigInteger t = v1[1];
            BigInteger s = r.add(t.multiply(lambda)).divide(n);
            BigInteger[] vw = extEuclidBezout(new BigInteger[]{s.abs(), t.abs()});
            if (vw != null) {
                BigInteger v = vw[0];
                BigInteger w = vw[1];
                if (s.signum() < 0) {
                    v = v.negate();
                }
                if (t.signum() > 0) {
                    w = w.negate();
                }
                if (!s.multiply(v).subtract(t.multiply(w)).equals(ECConstants.ONE)) {
                    throw new IllegalStateException();
                }
                BigInteger x = w.multiply(n).subtract(v.multiply(lambda));
                BigInteger base1 = v.negate();
                BigInteger base2 = x.negate();
                BigInteger sqrtN = isqrt(n.subtract(ECConstants.ONE)).add(ECConstants.ONE);
                BigInteger[] range = intersect(calculateRange(base1, sqrtN, t), calculateRange(base2, sqrtN, r));
                if (range != null) {
                    for (BigInteger alpha = range[0]; alpha.compareTo(range[1]) <= 0; alpha = alpha.add(ECConstants.ONE)) {
                        BigInteger[] candidate = {x.add(alpha.multiply(r)), v.add(alpha.multiply(t))};
                        if (isShorter(candidate, v2)) {
                            v2 = candidate;
                        }
                    }
                }
            }
        }
        BigInteger d = v1[0].multiply(v2[1]).subtract(v1[1].multiply(v2[0]));
        int bits = (n.bitLength() + 16) - (n.bitLength() & 7);
        BigInteger g1 = roundQuotient(v2[1].shiftLeft(bits), d);
        BigInteger g2 = roundQuotient(v1[1].shiftLeft(bits), d).negate();
        printProperty("Beta", beta.toBigInteger().toString(16));
        printProperty("Lambda", lambda.toString(16));
        printProperty("v1", "{ " + v1[0].toString(16) + ", " + v1[1].toString(16) + " }");
        printProperty("v2", "{ " + v2[0].toString(16) + ", " + v2[1].toString(16) + " }");
        printProperty("d", d.toString(16));
        printProperty("(OPT) g1", g1.toString(16));
        printProperty("(OPT) g2", g2.toString(16));
        printProperty("(OPT) bits", Integer.toString(bits));
    }

    private static void printProperty(String name, Object value) {
        StringBuffer sb = new StringBuffer("  ");
        sb.append(name);
        while (sb.length() < 20) {
            sb.append(' ');
        }
        sb.append("= ");
        sb.append(value.toString());
        System.out.println(sb.toString());
    }

    private static boolean areRelativelyPrime(BigInteger a, BigInteger b) {
        return a.gcd(b).equals(ECConstants.ONE);
    }

    private static BigInteger[] calculateRange(BigInteger mid, BigInteger off, BigInteger div) {
        return order(mid.subtract(off).divide(div), mid.add(off).divide(div));
    }

    private static BigInteger[] extEuclidBezout(BigInteger[] ab) {
        boolean swap = ab[0].compareTo(ab[1]) < 0;
        if (swap) {
            swap(ab);
        }
        BigInteger r0 = ab[0];
        BigInteger r1 = ab[1];
        BigInteger s0 = ECConstants.ONE;
        BigInteger s1 = ECConstants.ZERO;
        BigInteger t0 = ECConstants.ZERO;
        BigInteger t1 = ECConstants.ONE;
        while (r1.compareTo(ECConstants.ONE) > 0) {
            BigInteger[] qr = r0.divideAndRemainder(r1);
            BigInteger q = qr[0];
            BigInteger r2 = qr[1];
            BigInteger s2 = s0.subtract(q.multiply(s1));
            BigInteger t2 = t0.subtract(q.multiply(t1));
            r0 = r1;
            r1 = r2;
            s0 = s1;
            s1 = s2;
            t0 = t1;
            t1 = t2;
        }
        if (r1.signum() <= 0) {
            return null;
        }
        BigInteger[] st = {s1, t1};
        if (!swap) {
            return st;
        }
        swap(st);
        return st;
    }

    private static BigInteger[] extEuclidGLV(BigInteger n, BigInteger lambda) {
        BigInteger r0 = n;
        BigInteger r1 = lambda;
        BigInteger t0 = ECConstants.ZERO;
        BigInteger t1 = ECConstants.ONE;
        while (true) {
            BigInteger[] qr = r0.divideAndRemainder(r1);
            BigInteger q = qr[0];
            BigInteger r2 = qr[1];
            BigInteger t2 = t0.subtract(q.multiply(t1));
            if (isLessThanSqrt(r1, n)) {
                return new BigInteger[]{r0, t0, r1, t1, r2, t2};
            }
            r0 = r1;
            r1 = r2;
            t0 = t1;
            t1 = t2;
        }
    }

    private static BigInteger[] chooseShortest(BigInteger[] u, BigInteger[] v) {
        return isShorter(u, v) ? u : v;
    }

    private static BigInteger[] intersect(BigInteger[] ab, BigInteger[] cd) {
        BigInteger min = ab[0].max(cd[0]);
        BigInteger max = ab[1].min(cd[1]);
        if (min.compareTo(max) > 0) {
            return null;
        }
        return new BigInteger[]{min, max};
    }

    private static boolean isLessThanSqrt(BigInteger a, BigInteger b) {
        BigInteger a2 = a.abs();
        BigInteger b2 = b.abs();
        int target = b2.bitLength();
        int maxBits = a2.bitLength() * 2;
        return maxBits + -1 <= target && (maxBits < target || a2.multiply(a2).compareTo(b2) < 0);
    }

    private static boolean isShorter(BigInteger[] u, BigInteger[] v) {
        boolean c1;
        boolean z = true;
        BigInteger u1 = u[0].abs();
        BigInteger u2 = u[1].abs();
        BigInteger v1 = v[0].abs();
        BigInteger v2 = v[1].abs();
        if (u1.compareTo(v1) < 0) {
            c1 = true;
        } else {
            c1 = false;
        }
        if (c1 == (u2.compareTo(v2) < 0)) {
            return c1;
        }
        if (u1.multiply(u1).add(u2.multiply(u2)).compareTo(v1.multiply(v1).add(v2.multiply(v2))) >= 0) {
            z = false;
        }
        return z;
    }

    private static boolean isVectorBoundedBySqrt(BigInteger[] v, BigInteger n) {
        return isLessThanSqrt(v[0].abs().max(v[1].abs()), n);
    }

    private static BigInteger[] order(BigInteger a, BigInteger b) {
        if (a.compareTo(b) <= 0) {
            return new BigInteger[]{a, b};
        }
        return new BigInteger[]{b, a};
    }

    private static BigInteger roundQuotient(BigInteger x, BigInteger y) {
        boolean negative = x.signum() != y.signum();
        BigInteger x2 = x.abs();
        BigInteger y2 = y.abs();
        BigInteger result = x2.add(y2.shiftRight(1)).divide(y2);
        return negative ? result.negate() : result;
    }

    private static BigInteger[] solveQuadraticEquation(BigInteger n, BigInteger r, BigInteger s) {
        BigInteger root1 = new C5398Fp(n, r.multiply(r).subtract(s.shiftLeft(2)).mod(n)).sqrt().toBigInteger();
        BigInteger root2 = n.subtract(root1);
        if (root1.testBit(0)) {
            root2 = root2.add(n);
        } else {
            root1 = root1.add(n);
        }
        return new BigInteger[]{root1.shiftRight(1), root2.shiftRight(1)};
    }

    private static ECFieldElement[] findBetaValues(ECCurve c) {
        BigInteger b;
        BigInteger q = c.getField().getCharacteristic();
        BigInteger e = q.divide(ECConstants.THREE);
        SecureRandom random = new SecureRandom();
        do {
            b = BigIntegers.createRandomInRange(ECConstants.TWO, q.subtract(ECConstants.TWO), random).modPow(e, q);
        } while (b.equals(ECConstants.ONE));
        ECFieldElement beta = c.fromBigInteger(b);
        return new ECFieldElement[]{beta, beta.square()};
    }

    private static BigInteger isqrt(BigInteger x) {
        BigInteger g0 = x.shiftRight(x.bitLength() / 2);
        while (true) {
            BigInteger g1 = g0.add(x.divide(g0)).shiftRight(1);
            if (g1.equals(g0)) {
                return g1;
            }
            g0 = g1;
        }
    }

    private static void swap(BigInteger[] ab) {
        BigInteger tmp = ab[0];
        ab[0] = ab[1];
        ab[1] = tmp;
    }
}
