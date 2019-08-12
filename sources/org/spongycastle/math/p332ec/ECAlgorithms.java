package org.spongycastle.math.p332ec;

import java.math.BigInteger;
import org.jmrtd.PassportService;
import org.spongycastle.math.field.FiniteField;
import org.spongycastle.math.field.PolynomialExtensionField;
import org.spongycastle.math.p332ec.ECCurve.AbstractF2m;
import org.spongycastle.math.p332ec.endo.ECEndomorphism;
import org.spongycastle.math.p332ec.endo.GLVEndomorphism;

/* renamed from: org.spongycastle.math.ec.ECAlgorithms */
public class ECAlgorithms {
    public static boolean isF2mCurve(ECCurve c) {
        return isF2mField(c.getField());
    }

    public static boolean isF2mField(FiniteField field) {
        return field.getDimension() > 1 && field.getCharacteristic().equals(ECConstants.TWO) && (field instanceof PolynomialExtensionField);
    }

    public static boolean isFpCurve(ECCurve c) {
        return isFpField(c.getField());
    }

    public static boolean isFpField(FiniteField field) {
        return field.getDimension() == 1;
    }

    public static ECPoint sumOfMultiplies(ECPoint[] ps, BigInteger[] ks) {
        if (ps == null || ks == null || ps.length != ks.length || ps.length < 1) {
            throw new IllegalArgumentException("point and scalar arrays should be non-null, and of equal, non-zero, length");
        }
        int count = ps.length;
        switch (count) {
            case 1:
                return ps[0].multiply(ks[0]);
            case 2:
                return sumOfTwoMultiplies(ps[0], ks[0], ps[1], ks[1]);
            default:
                ECPoint p = ps[0];
                ECCurve c = p.getCurve();
                ECPoint[] imported = new ECPoint[count];
                imported[0] = p;
                for (int i = 1; i < count; i++) {
                    imported[i] = importPoint(c, ps[i]);
                }
                ECEndomorphism endomorphism = c.getEndomorphism();
                if (endomorphism instanceof GLVEndomorphism) {
                    return validatePoint(implSumOfMultipliesGLV(imported, ks, (GLVEndomorphism) endomorphism));
                }
                return validatePoint(implSumOfMultiplies(imported, ks));
        }
    }

    public static ECPoint sumOfTwoMultiplies(ECPoint P, BigInteger a, ECPoint Q, BigInteger b) {
        ECCurve cp = P.getCurve();
        ECPoint Q2 = importPoint(cp, Q);
        if ((cp instanceof AbstractF2m) && ((AbstractF2m) cp).isKoblitz()) {
            return validatePoint(P.multiply(a).add(Q2.multiply(b)));
        }
        ECEndomorphism endomorphism = cp.getEndomorphism();
        if (!(endomorphism instanceof GLVEndomorphism)) {
            return validatePoint(implShamirsTrickWNaf(P, a, Q2, b));
        }
        return validatePoint(implSumOfMultipliesGLV(new ECPoint[]{P, Q2}, new BigInteger[]{a, b}, (GLVEndomorphism) endomorphism));
    }

    public static ECPoint shamirsTrick(ECPoint P, BigInteger k, ECPoint Q, BigInteger l) {
        return validatePoint(implShamirsTrickJsf(P, k, importPoint(P.getCurve(), Q), l));
    }

    public static ECPoint importPoint(ECCurve c, ECPoint p) {
        if (c.equals(p.getCurve())) {
            return c.importPoint(p);
        }
        throw new IllegalArgumentException("Point must be on the same curve");
    }

    public static void montgomeryTrick(ECFieldElement[] zs, int off, int len) {
        montgomeryTrick(zs, off, len, null);
    }

    public static void montgomeryTrick(ECFieldElement[] zs, int off, int len, ECFieldElement scale) {
        ECFieldElement[] c = new ECFieldElement[len];
        c[0] = zs[off];
        int i = 0;
        while (true) {
            i++;
            if (i >= len) {
                break;
            }
            c[i] = c[i - 1].multiply(zs[off + i]);
        }
        int i2 = i - 1;
        if (scale != null) {
            c[i2] = c[i2].multiply(scale);
        }
        ECFieldElement u = c[i2].invert();
        int i3 = i2;
        while (i3 > 0) {
            int i4 = i3 - 1;
            int j = off + i3;
            ECFieldElement tmp = zs[j];
            zs[j] = c[i4].multiply(u);
            u = u.multiply(tmp);
            i3 = i4;
        }
        zs[off] = u;
    }

    public static ECPoint referenceMultiply(ECPoint p, BigInteger k) {
        BigInteger x = k.abs();
        ECPoint q = p.getCurve().getInfinity();
        int t = x.bitLength();
        if (t > 0) {
            if (x.testBit(0)) {
                q = p;
            }
            for (int i = 1; i < t; i++) {
                p = p.twice();
                if (x.testBit(i)) {
                    q = q.add(p);
                }
            }
        }
        return k.signum() < 0 ? q.negate() : q;
    }

    public static ECPoint validatePoint(ECPoint p) {
        if (p.isValid()) {
            return p;
        }
        throw new IllegalArgumentException("Invalid point");
    }

    static ECPoint implShamirsTrickJsf(ECPoint P, BigInteger k, ECPoint Q, BigInteger l) {
        ECCurve curve = P.getCurve();
        ECPoint infinity = curve.getInfinity();
        ECPoint PaddQ = P.add(Q);
        ECPoint[] points = {Q, P.subtract(Q), P, PaddQ};
        curve.normalizeAll(points);
        ECPoint[] table = {points[3].negate(), points[2].negate(), points[1].negate(), points[0].negate(), infinity, points[0], points[1], points[2], points[3]};
        byte[] jsf = WNafUtil.generateJSF(k, l);
        ECPoint R = infinity;
        int i = jsf.length;
        while (true) {
            i--;
            if (i < 0) {
                return R;
            }
            byte jsfi = jsf[i];
            int lDigit = (jsfi << PassportService.SF_CVCA) >> 28;
            R = R.twicePlus(table[(((jsfi << 24) >> 28) * 3) + 4 + lDigit]);
        }
    }

    static ECPoint implShamirsTrickWNaf(ECPoint P, BigInteger k, ECPoint Q, BigInteger l) {
        boolean negK = k.signum() < 0;
        boolean negL = l.signum() < 0;
        BigInteger k2 = k.abs();
        BigInteger l2 = l.abs();
        int widthP = Math.max(2, Math.min(16, WNafUtil.getWindowSize(k2.bitLength())));
        int widthQ = Math.max(2, Math.min(16, WNafUtil.getWindowSize(l2.bitLength())));
        WNafPreCompInfo infoP = WNafUtil.precompute(P, widthP, true);
        WNafPreCompInfo infoQ = WNafUtil.precompute(Q, widthQ, true);
        return implShamirsTrickWNaf(negK ? infoP.getPreCompNeg() : infoP.getPreComp(), negK ? infoP.getPreComp() : infoP.getPreCompNeg(), WNafUtil.generateWindowNaf(widthP, k2), negL ? infoQ.getPreCompNeg() : infoQ.getPreComp(), negL ? infoQ.getPreComp() : infoQ.getPreCompNeg(), WNafUtil.generateWindowNaf(widthQ, l2));
    }

    static ECPoint implShamirsTrickWNaf(ECPoint P, BigInteger k, ECPointMap pointMapQ, BigInteger l) {
        boolean negK = k.signum() < 0;
        boolean negL = l.signum() < 0;
        BigInteger k2 = k.abs();
        BigInteger l2 = l.abs();
        int width = Math.max(2, Math.min(16, WNafUtil.getWindowSize(Math.max(k2.bitLength(), l2.bitLength()))));
        ECPoint Q = WNafUtil.mapPointWithPrecomp(P, width, true, pointMapQ);
        WNafPreCompInfo infoP = WNafUtil.getWNafPreCompInfo(P);
        WNafPreCompInfo infoQ = WNafUtil.getWNafPreCompInfo(Q);
        return implShamirsTrickWNaf(negK ? infoP.getPreCompNeg() : infoP.getPreComp(), negK ? infoP.getPreComp() : infoP.getPreCompNeg(), WNafUtil.generateWindowNaf(width, k2), negL ? infoQ.getPreCompNeg() : infoQ.getPreComp(), negL ? infoQ.getPreComp() : infoQ.getPreCompNeg(), WNafUtil.generateWindowNaf(width, l2));
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r11v2, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r12v2, types: [byte] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.spongycastle.math.p332ec.ECPoint implShamirsTrickWNaf(org.spongycastle.math.p332ec.ECPoint[] r16, org.spongycastle.math.p332ec.ECPoint[] r17, byte[] r18, org.spongycastle.math.p332ec.ECPoint[] r19, org.spongycastle.math.p332ec.ECPoint[] r20, byte[] r21) {
        /*
            r0 = r18
            int r14 = r0.length
            r0 = r21
            int r15 = r0.length
            int r5 = java.lang.Math.max(r14, r15)
            r14 = 0
            r14 = r16[r14]
            org.spongycastle.math.ec.ECCurve r2 = r14.getCurve()
            org.spongycastle.math.ec.ECPoint r4 = r2.getInfinity()
            r1 = r4
            r13 = 0
            int r3 = r5 + -1
        L_0x0019:
            if (r3 < 0) goto L_0x006d
            r0 = r18
            int r14 = r0.length
            if (r3 >= r14) goto L_0x0032
            byte r11 = r18[r3]
        L_0x0022:
            r0 = r21
            int r14 = r0.length
            if (r3 >= r14) goto L_0x0034
            byte r12 = r21[r3]
        L_0x0029:
            r14 = r11 | r12
            if (r14 != 0) goto L_0x0036
            int r13 = r13 + 1
        L_0x002f:
            int r3 = r3 + -1
            goto L_0x0019
        L_0x0032:
            r11 = 0
            goto L_0x0022
        L_0x0034:
            r12 = 0
            goto L_0x0029
        L_0x0036:
            r8 = r4
            if (r11 == 0) goto L_0x0049
            int r6 = java.lang.Math.abs(r11)
            if (r11 >= 0) goto L_0x0067
            r9 = r17
        L_0x0041:
            int r14 = r6 >>> 1
            r14 = r9[r14]
            org.spongycastle.math.ec.ECPoint r8 = r8.add(r14)
        L_0x0049:
            if (r12 == 0) goto L_0x005b
            int r7 = java.lang.Math.abs(r12)
            if (r12 >= 0) goto L_0x006a
            r10 = r20
        L_0x0053:
            int r14 = r7 >>> 1
            r14 = r10[r14]
            org.spongycastle.math.ec.ECPoint r8 = r8.add(r14)
        L_0x005b:
            if (r13 <= 0) goto L_0x0062
            org.spongycastle.math.ec.ECPoint r1 = r1.timesPow2(r13)
            r13 = 0
        L_0x0062:
            org.spongycastle.math.ec.ECPoint r1 = r1.twicePlus(r8)
            goto L_0x002f
        L_0x0067:
            r9 = r16
            goto L_0x0041
        L_0x006a:
            r10 = r19
            goto L_0x0053
        L_0x006d:
            if (r13 <= 0) goto L_0x0073
            org.spongycastle.math.ec.ECPoint r1 = r1.timesPow2(r13)
        L_0x0073:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.math.p332ec.ECAlgorithms.implShamirsTrickWNaf(org.spongycastle.math.ec.ECPoint[], org.spongycastle.math.ec.ECPoint[], byte[], org.spongycastle.math.ec.ECPoint[], org.spongycastle.math.ec.ECPoint[], byte[]):org.spongycastle.math.ec.ECPoint");
    }

    static ECPoint implSumOfMultiplies(ECPoint[] ps, BigInteger[] ks) {
        int count = ps.length;
        boolean[] negs = new boolean[count];
        WNafPreCompInfo[] infos = new WNafPreCompInfo[count];
        byte[][] wnafs = new byte[count][];
        for (int i = 0; i < count; i++) {
            BigInteger ki = ks[i];
            negs[i] = ki.signum() < 0;
            BigInteger ki2 = ki.abs();
            int width = Math.max(2, Math.min(16, WNafUtil.getWindowSize(ki2.bitLength())));
            infos[i] = WNafUtil.precompute(ps[i], width, true);
            wnafs[i] = WNafUtil.generateWindowNaf(width, ki2);
        }
        return implSumOfMultiplies(negs, infos, wnafs);
    }

    static ECPoint implSumOfMultipliesGLV(ECPoint[] ps, BigInteger[] ks, GLVEndomorphism glvEndomorphism) {
        BigInteger n = ps[0].getCurve().getOrder();
        BigInteger[] abs = new BigInteger[(len << 1)];
        int j = 0;
        for (int i = 0; i < len; i++) {
            BigInteger[] ab = glvEndomorphism.decomposeScalar(ks[i].mod(n));
            int j2 = j + 1;
            abs[j] = ab[0];
            j = j2 + 1;
            abs[j2] = ab[1];
        }
        ECPointMap pointMap = glvEndomorphism.getPointMap();
        if (glvEndomorphism.hasEfficientPointMap()) {
            return implSumOfMultiplies(ps, pointMap, abs);
        }
        ECPoint[] pqs = new ECPoint[(len << 1)];
        int j3 = 0;
        for (ECPoint p : ps) {
            ECPoint q = pointMap.map(p);
            int j4 = j3 + 1;
            pqs[j3] = p;
            j3 = j4 + 1;
            pqs[j4] = q;
        }
        return implSumOfMultiplies(pqs, abs);
    }

    static ECPoint implSumOfMultiplies(ECPoint[] ps, ECPointMap pointMap, BigInteger[] ks) {
        int halfCount = ps.length;
        int fullCount = halfCount << 1;
        boolean[] negs = new boolean[fullCount];
        WNafPreCompInfo[] infos = new WNafPreCompInfo[fullCount];
        byte[][] wnafs = new byte[fullCount][];
        for (int i = 0; i < halfCount; i++) {
            int j0 = i << 1;
            int j1 = j0 + 1;
            BigInteger kj0 = ks[j0];
            negs[j0] = kj0.signum() < 0;
            BigInteger kj02 = kj0.abs();
            BigInteger kj1 = ks[j1];
            negs[j1] = kj1.signum() < 0;
            BigInteger kj12 = kj1.abs();
            int width = Math.max(2, Math.min(16, WNafUtil.getWindowSize(Math.max(kj02.bitLength(), kj12.bitLength()))));
            ECPoint P = ps[i];
            ECPoint Q = WNafUtil.mapPointWithPrecomp(P, width, true, pointMap);
            infos[j0] = WNafUtil.getWNafPreCompInfo(P);
            infos[j1] = WNafUtil.getWNafPreCompInfo(Q);
            wnafs[j0] = WNafUtil.generateWindowNaf(width, kj02);
            wnafs[j1] = WNafUtil.generateWindowNaf(width, kj12);
        }
        return implSumOfMultiplies(negs, infos, wnafs);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r12v2, types: [byte] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.spongycastle.math.p332ec.ECPoint implSumOfMultiplies(boolean[] r17, org.spongycastle.math.p332ec.WNafPreCompInfo[] r18, byte[][] r19) {
        /*
            r8 = 0
            r0 = r19
            int r2 = r0.length
            r4 = 0
        L_0x0005:
            if (r4 >= r2) goto L_0x0011
            r15 = r19[r4]
            int r15 = r15.length
            int r8 = java.lang.Math.max(r8, r15)
            int r4 = r4 + 1
            goto L_0x0005
        L_0x0011:
            r15 = 0
            r15 = r18[r15]
            org.spongycastle.math.ec.ECPoint[] r15 = r15.getPreComp()
            r16 = 0
            r15 = r15[r16]
            org.spongycastle.math.ec.ECCurve r3 = r15.getCurve()
            org.spongycastle.math.ec.ECPoint r5 = r3.getInfinity()
            r1 = r5
            r14 = 0
            int r4 = r8 + -1
        L_0x0028:
            if (r4 < 0) goto L_0x0071
            r10 = r5
            r7 = 0
        L_0x002c:
            if (r7 >= r2) goto L_0x005e
            r13 = r19[r7]
            int r15 = r13.length
            if (r4 >= r15) goto L_0x0055
            byte r12 = r13[r4]
        L_0x0035:
            if (r12 == 0) goto L_0x0052
            int r9 = java.lang.Math.abs(r12)
            r6 = r18[r7]
            if (r12 >= 0) goto L_0x0057
            r15 = 1
        L_0x0040:
            boolean r16 = r17[r7]
            r0 = r16
            if (r15 != r0) goto L_0x0059
            org.spongycastle.math.ec.ECPoint[] r11 = r6.getPreComp()
        L_0x004a:
            int r15 = r9 >>> 1
            r15 = r11[r15]
            org.spongycastle.math.ec.ECPoint r10 = r10.add(r15)
        L_0x0052:
            int r7 = r7 + 1
            goto L_0x002c
        L_0x0055:
            r12 = 0
            goto L_0x0035
        L_0x0057:
            r15 = 0
            goto L_0x0040
        L_0x0059:
            org.spongycastle.math.ec.ECPoint[] r11 = r6.getPreCompNeg()
            goto L_0x004a
        L_0x005e:
            if (r10 != r5) goto L_0x0065
            int r14 = r14 + 1
        L_0x0062:
            int r4 = r4 + -1
            goto L_0x0028
        L_0x0065:
            if (r14 <= 0) goto L_0x006c
            org.spongycastle.math.ec.ECPoint r1 = r1.timesPow2(r14)
            r14 = 0
        L_0x006c:
            org.spongycastle.math.ec.ECPoint r1 = r1.twicePlus(r10)
            goto L_0x0062
        L_0x0071:
            if (r14 <= 0) goto L_0x0077
            org.spongycastle.math.ec.ECPoint r1 = r1.timesPow2(r14)
        L_0x0077:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.math.p332ec.ECAlgorithms.implSumOfMultiplies(boolean[], org.spongycastle.math.ec.WNafPreCompInfo[], byte[][]):org.spongycastle.math.ec.ECPoint");
    }
}
