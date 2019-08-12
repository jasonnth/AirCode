package org.spongycastle.math.p332ec;

import java.math.BigInteger;
import org.spongycastle.asn1.eac.EACTags;

/* renamed from: org.spongycastle.math.ec.WNafUtil */
public abstract class WNafUtil {
    private static final int[] DEFAULT_WINDOW_SIZE_CUTOFFS = {13, 41, EACTags.COEXISTANT_TAG_ALLOCATION_AUTHORITY, 337, 897, 2305};
    private static final byte[] EMPTY_BYTES = new byte[0];
    private static final int[] EMPTY_INTS = new int[0];
    private static final ECPoint[] EMPTY_POINTS = new ECPoint[0];
    public static final String PRECOMP_NAME = "bc_wnaf";

    public static int[] generateCompactNaf(BigInteger k) {
        int digit;
        int length;
        if ((k.bitLength() >>> 16) != 0) {
            throw new IllegalArgumentException("'k' must have bitlength < 2^16");
        } else if (k.signum() == 0) {
            return EMPTY_INTS;
        } else {
            BigInteger _3k = k.shiftLeft(1).add(k);
            int bits = _3k.bitLength();
            int[] naf = new int[(bits >> 1)];
            BigInteger diff = _3k.xor(k);
            int highBit = bits - 1;
            int zeroes = 0;
            int i = 1;
            int length2 = 0;
            while (i < highBit) {
                if (!diff.testBit(i)) {
                    zeroes++;
                    length = length2;
                } else {
                    if (k.testBit(i)) {
                        digit = -1;
                    } else {
                        digit = 1;
                    }
                    length = length2 + 1;
                    naf[length2] = (digit << 16) | zeroes;
                    zeroes = 1;
                    i++;
                }
                i++;
                length2 = length;
            }
            int length3 = length2 + 1;
            naf[length2] = 65536 | zeroes;
            if (naf.length > length3) {
                return trim(naf, length3);
            }
            return naf;
        }
    }

    public static int[] generateCompactWindowNaf(int width, BigInteger k) {
        int zeroes;
        if (width == 2) {
            return generateCompactNaf(k);
        }
        if (width < 2 || width > 16) {
            throw new IllegalArgumentException("'width' must be in the range [2, 16]");
        } else if ((k.bitLength() >>> 16) != 0) {
            throw new IllegalArgumentException("'k' must have bitlength < 2^16");
        } else if (k.signum() == 0) {
            return EMPTY_INTS;
        } else {
            int[] wnaf = new int[((k.bitLength() / width) + 1)];
            int pow2 = 1 << width;
            int mask = pow2 - 1;
            int sign = pow2 >>> 1;
            boolean carry = false;
            int length = 0;
            int pos = 0;
            while (pos <= k.bitLength()) {
                if (k.testBit(pos) == carry) {
                    pos++;
                } else {
                    k = k.shiftRight(pos);
                    int digit = k.intValue() & mask;
                    if (carry) {
                        digit++;
                    }
                    carry = (digit & sign) != 0;
                    if (carry) {
                        digit -= pow2;
                    }
                    if (length > 0) {
                        zeroes = pos - 1;
                    } else {
                        zeroes = pos;
                    }
                    int length2 = length + 1;
                    wnaf[length] = (digit << 16) | zeroes;
                    pos = width;
                    length = length2;
                }
            }
            if (wnaf.length > length) {
                return trim(wnaf, length);
            }
            return wnaf;
        }
    }

    public static byte[] generateJSF(BigInteger g, BigInteger h) {
        int j;
        byte[] jsf = new byte[(Math.max(g.bitLength(), h.bitLength()) + 1)];
        BigInteger k0 = g;
        BigInteger k1 = h;
        int j2 = 0;
        int d0 = 0;
        int d1 = 0;
        int offset = 0;
        while (true) {
            j = j2;
            if ((d0 | d1) == 0 && k0.bitLength() <= offset && k1.bitLength() <= offset) {
                break;
            }
            int n0 = ((k0.intValue() >>> offset) + d0) & 7;
            int n1 = ((k1.intValue() >>> offset) + d1) & 7;
            int u0 = n0 & 1;
            if (u0 != 0) {
                u0 -= n0 & 2;
                if (n0 + u0 == 4 && (n1 & 3) == 2) {
                    u0 = -u0;
                }
            }
            int u1 = n1 & 1;
            if (u1 != 0) {
                u1 -= n1 & 2;
                if (n1 + u1 == 4 && (n0 & 3) == 2) {
                    u1 = -u1;
                }
            }
            if ((d0 << 1) == u0 + 1) {
                d0 ^= 1;
            }
            if ((d1 << 1) == u1 + 1) {
                d1 ^= 1;
            }
            offset++;
            if (offset == 30) {
                offset = 0;
                k0 = k0.shiftRight(30);
                k1 = k1.shiftRight(30);
            }
            j2 = j + 1;
            jsf[j] = (byte) ((u0 << 4) | (u1 & 15));
        }
        if (jsf.length > j) {
            return trim(jsf, j);
        }
        return jsf;
    }

    public static byte[] generateNaf(BigInteger k) {
        int i;
        if (k.signum() == 0) {
            return EMPTY_BYTES;
        }
        BigInteger _3k = k.shiftLeft(1).add(k);
        int digits = _3k.bitLength() - 1;
        byte[] naf = new byte[digits];
        BigInteger diff = _3k.xor(k);
        int i2 = 1;
        while (i2 < digits) {
            if (diff.testBit(i2)) {
                int i3 = i2 - 1;
                if (k.testBit(i2)) {
                    i = -1;
                } else {
                    i = 1;
                }
                naf[i3] = (byte) i;
                i2++;
            }
            i2++;
        }
        naf[digits - 1] = 1;
        return naf;
    }

    public static byte[] generateWindowNaf(int width, BigInteger k) {
        if (width == 2) {
            return generateNaf(k);
        }
        if (width < 2 || width > 8) {
            throw new IllegalArgumentException("'width' must be in the range [2, 8]");
        } else if (k.signum() == 0) {
            return EMPTY_BYTES;
        } else {
            byte[] wnaf = new byte[(k.bitLength() + 1)];
            int pow2 = 1 << width;
            int mask = pow2 - 1;
            int sign = pow2 >>> 1;
            boolean carry = false;
            int length = 0;
            int pos = 0;
            while (pos <= k.bitLength()) {
                if (k.testBit(pos) == carry) {
                    pos++;
                } else {
                    k = k.shiftRight(pos);
                    int digit = k.intValue() & mask;
                    if (carry) {
                        digit++;
                    }
                    carry = (digit & sign) != 0;
                    if (carry) {
                        digit -= pow2;
                    }
                    if (length > 0) {
                        pos--;
                    }
                    int length2 = length + pos;
                    int length3 = length2 + 1;
                    wnaf[length2] = (byte) digit;
                    pos = width;
                    length = length3;
                }
            }
            if (wnaf.length > length) {
                return trim(wnaf, length);
            }
            return wnaf;
        }
    }

    public static int getNafWeight(BigInteger k) {
        if (k.signum() == 0) {
            return 0;
        }
        return k.shiftLeft(1).add(k).xor(k).bitCount();
    }

    public static WNafPreCompInfo getWNafPreCompInfo(ECPoint p) {
        return getWNafPreCompInfo(p.getCurve().getPreCompInfo(p, PRECOMP_NAME));
    }

    public static WNafPreCompInfo getWNafPreCompInfo(PreCompInfo preCompInfo) {
        if (preCompInfo == null || !(preCompInfo instanceof WNafPreCompInfo)) {
            return new WNafPreCompInfo();
        }
        return (WNafPreCompInfo) preCompInfo;
    }

    public static int getWindowSize(int bits) {
        return getWindowSize(bits, DEFAULT_WINDOW_SIZE_CUTOFFS);
    }

    public static int getWindowSize(int bits, int[] windowSizeCutoffs) {
        int w = 0;
        while (w < windowSizeCutoffs.length && bits >= windowSizeCutoffs[w]) {
            w++;
        }
        return w + 2;
    }

    public static ECPoint mapPointWithPrecomp(ECPoint p, int width, boolean includeNegated, ECPointMap pointMap) {
        ECCurve c = p.getCurve();
        WNafPreCompInfo wnafPreCompP = precompute(p, width, includeNegated);
        ECPoint q = pointMap.map(p);
        WNafPreCompInfo wnafPreCompQ = getWNafPreCompInfo(c.getPreCompInfo(q, PRECOMP_NAME));
        ECPoint twiceP = wnafPreCompP.getTwice();
        if (twiceP != null) {
            wnafPreCompQ.setTwice(pointMap.map(twiceP));
        }
        ECPoint[] preCompP = wnafPreCompP.getPreComp();
        ECPoint[] preCompQ = new ECPoint[preCompP.length];
        for (int i = 0; i < preCompP.length; i++) {
            preCompQ[i] = pointMap.map(preCompP[i]);
        }
        wnafPreCompQ.setPreComp(preCompQ);
        if (includeNegated) {
            ECPoint[] preCompNegQ = new ECPoint[preCompQ.length];
            for (int i2 = 0; i2 < preCompNegQ.length; i2++) {
                preCompNegQ[i2] = preCompQ[i2].negate();
            }
            wnafPreCompQ.setPreCompNeg(preCompNegQ);
        }
        c.setPreCompInfo(q, PRECOMP_NAME, wnafPreCompQ);
        return q;
    }

    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.spongycastle.math.p332ec.WNafPreCompInfo precompute(org.spongycastle.math.p332ec.ECPoint r18, int r19, boolean r20) {
        /*
            org.spongycastle.math.ec.ECCurve r1 = r18.getCurve()
            java.lang.String r15 = "bc_wnaf"
            r0 = r18
            org.spongycastle.math.ec.PreCompInfo r15 = r1.getPreCompInfo(r0, r15)
            org.spongycastle.math.ec.WNafPreCompInfo r14 = getWNafPreCompInfo(r15)
            r4 = 0
            r15 = 1
            r16 = 0
            int r17 = r19 + -2
            int r16 = java.lang.Math.max(r16, r17)
            int r12 = r15 << r16
            org.spongycastle.math.ec.ECPoint[] r10 = r14.getPreComp()
            if (r10 != 0) goto L_0x0050
            org.spongycastle.math.ec.ECPoint[] r10 = EMPTY_POINTS
        L_0x0025:
            if (r4 >= r12) goto L_0x0035
            org.spongycastle.math.ec.ECPoint[] r10 = resizeTable(r10, r12)
            r15 = 1
            if (r12 != r15) goto L_0x0052
            r15 = 0
            org.spongycastle.math.ec.ECPoint r16 = r18.normalize()
            r10[r15] = r16
        L_0x0035:
            r14.setPreComp(r10)
            if (r20 == 0) goto L_0x00e0
            org.spongycastle.math.ec.ECPoint[] r11 = r14.getPreCompNeg()
            if (r11 != 0) goto L_0x00d4
            r9 = 0
            org.spongycastle.math.ec.ECPoint[] r11 = new org.spongycastle.math.p332ec.ECPoint[r12]
        L_0x0043:
            if (r9 >= r12) goto L_0x00dd
            r15 = r10[r9]
            org.spongycastle.math.ec.ECPoint r15 = r15.negate()
            r11[r9] = r15
            int r9 = r9 + 1
            goto L_0x0043
        L_0x0050:
            int r4 = r10.length
            goto L_0x0025
        L_0x0052:
            r2 = r4
            if (r2 != 0) goto L_0x0059
            r15 = 0
            r10[r15] = r18
            r2 = 1
        L_0x0059:
            r5 = 0
            r15 = 2
            if (r12 != r15) goto L_0x006a
            r15 = 1
            org.spongycastle.math.ec.ECPoint r16 = r18.threeTimes()
            r10[r15] = r16
        L_0x0064:
            int r15 = r12 - r4
            r1.normalizeAll(r10, r4, r15, r5)
            goto L_0x0035
        L_0x006a:
            org.spongycastle.math.ec.ECPoint r13 = r14.getTwice()
            int r15 = r2 + -1
            r8 = r10[r15]
            if (r13 != 0) goto L_0x0095
            r15 = 0
            r15 = r10[r15]
            org.spongycastle.math.ec.ECPoint r13 = r15.twice()
            r14.setTwice(r13)
            boolean r15 = org.spongycastle.math.p332ec.ECAlgorithms.isFpCurve(r1)
            if (r15 == 0) goto L_0x0095
            int r15 = r1.getFieldSize()
            r16 = 64
            r0 = r16
            if (r15 < r0) goto L_0x0095
            int r15 = r1.getCoordinateSystem()
            switch(r15) {
                case 2: goto L_0x00a2;
                case 3: goto L_0x00a2;
                case 4: goto L_0x00a2;
                default: goto L_0x0095;
            }
        L_0x0095:
            r3 = r2
        L_0x0096:
            if (r3 >= r12) goto L_0x00e9
            int r2 = r3 + 1
            org.spongycastle.math.ec.ECPoint r8 = r8.add(r13)
            r10[r3] = r8
            r3 = r2
            goto L_0x0096
        L_0x00a2:
            r15 = 0
            org.spongycastle.math.ec.ECFieldElement r5 = r13.getZCoord(r15)
            org.spongycastle.math.ec.ECFieldElement r15 = r13.getXCoord()
            java.math.BigInteger r15 = r15.toBigInteger()
            org.spongycastle.math.ec.ECFieldElement r16 = r13.getYCoord()
            java.math.BigInteger r16 = r16.toBigInteger()
            r0 = r16
            org.spongycastle.math.ec.ECPoint r13 = r1.createPoint(r15, r0)
            org.spongycastle.math.ec.ECFieldElement r6 = r5.square()
            org.spongycastle.math.ec.ECFieldElement r7 = r6.multiply(r5)
            org.spongycastle.math.ec.ECPoint r15 = r8.scaleX(r6)
            org.spongycastle.math.ec.ECPoint r8 = r15.scaleY(r7)
            if (r4 != 0) goto L_0x0095
            r15 = 0
            r10[r15] = r8
            r3 = r2
            goto L_0x0096
        L_0x00d4:
            int r9 = r11.length
            if (r9 >= r12) goto L_0x0043
            org.spongycastle.math.ec.ECPoint[] r11 = resizeTable(r11, r12)
            goto L_0x0043
        L_0x00dd:
            r14.setPreCompNeg(r11)
        L_0x00e0:
            java.lang.String r15 = "bc_wnaf"
            r0 = r18
            r1.setPreCompInfo(r0, r15, r14)
            return r14
        L_0x00e9:
            r2 = r3
            goto L_0x0064
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.math.p332ec.WNafUtil.precompute(org.spongycastle.math.ec.ECPoint, int, boolean):org.spongycastle.math.ec.WNafPreCompInfo");
    }

    private static byte[] trim(byte[] a, int length) {
        byte[] result = new byte[length];
        System.arraycopy(a, 0, result, 0, result.length);
        return result;
    }

    private static int[] trim(int[] a, int length) {
        int[] result = new int[length];
        System.arraycopy(a, 0, result, 0, result.length);
        return result;
    }

    private static ECPoint[] resizeTable(ECPoint[] a, int length) {
        ECPoint[] result = new ECPoint[length];
        System.arraycopy(a, 0, result, 0, a.length);
        return result;
    }
}
