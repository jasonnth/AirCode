package org.spongycastle.math.p332ec.tools;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.SortedSet;
import java.util.TreeSet;
import org.spongycastle.asn1.p325x9.ECNamedCurveTable;
import org.spongycastle.asn1.p325x9.X9ECParameters;
import org.spongycastle.crypto.p326ec.CustomNamedCurves;
import org.spongycastle.math.p332ec.ECAlgorithms;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.util.Integers;

/* renamed from: org.spongycastle.math.ec.tools.TraceOptimizer */
public class TraceOptimizer {
    private static final BigInteger ONE = BigInteger.valueOf(1);

    /* renamed from: R */
    private static final SecureRandom f7056R = new SecureRandom();

    public static void main(String[] args) {
        SortedSet<String> names = new TreeSet<>(enumToList(ECNamedCurveTable.getNames()));
        names.addAll(enumToList(CustomNamedCurves.getNames()));
        for (String name : names) {
            X9ECParameters x9 = CustomNamedCurves.getByName(name);
            if (x9 == null) {
                x9 = ECNamedCurveTable.getByName(name);
            }
            if (x9 != null && ECAlgorithms.isF2mCurve(x9.getCurve())) {
                System.out.print(name + ":");
                implPrintNonZeroTraceBits(x9);
            }
        }
    }

    public static void printNonZeroTraceBits(X9ECParameters x9) {
        if (!ECAlgorithms.isF2mCurve(x9.getCurve())) {
            throw new IllegalArgumentException("Trace only defined over characteristic-2 fields");
        }
        implPrintNonZeroTraceBits(x9);
    }

    public static void implPrintNonZeroTraceBits(X9ECParameters x9) {
        ECCurve c = x9.getCurve();
        int m = c.getFieldSize();
        ArrayList nonZeroTraceBits = new ArrayList();
        for (int i = 0; i < m; i++) {
            if (calculateTrace(c.fromBigInteger(ONE.shiftLeft(i))) != 0) {
                nonZeroTraceBits.add(Integers.valueOf(i));
                System.out.print(" " + i);
            }
        }
        System.out.println();
        for (int i2 = 0; i2 < 1000; i2++) {
            BigInteger x = new BigInteger(m, f7056R);
            int check = calculateTrace(c.fromBigInteger(x));
            int tr = 0;
            for (int j = 0; j < nonZeroTraceBits.size(); j++) {
                if (x.testBit(((Integer) nonZeroTraceBits.get(j)).intValue())) {
                    tr ^= 1;
                }
            }
            if (check != tr) {
                throw new IllegalStateException("Optimized-trace sanity check failed");
            }
        }
    }

    private static int calculateTrace(ECFieldElement fe) {
        int m = fe.getFieldSize();
        ECFieldElement tr = fe;
        for (int i = 1; i < m; i++) {
            fe = fe.square();
            tr = tr.add(fe);
        }
        BigInteger b = tr.toBigInteger();
        if (b.bitLength() <= 1) {
            return b.intValue();
        }
        throw new IllegalStateException();
    }

    private static ArrayList enumToList(Enumeration en) {
        ArrayList rv = new ArrayList();
        while (en.hasMoreElements()) {
            rv.add(en.nextElement());
        }
        return rv;
    }
}
