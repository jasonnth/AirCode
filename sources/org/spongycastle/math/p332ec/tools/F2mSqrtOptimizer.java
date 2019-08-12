package org.spongycastle.math.p332ec.tools;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.SortedSet;
import java.util.TreeSet;
import org.spongycastle.asn1.p325x9.ECNamedCurveTable;
import org.spongycastle.asn1.p325x9.X9ECParameters;
import org.spongycastle.crypto.p326ec.CustomNamedCurves;
import org.spongycastle.math.p332ec.ECAlgorithms;
import org.spongycastle.math.p332ec.ECFieldElement;

/* renamed from: org.spongycastle.math.ec.tools.F2mSqrtOptimizer */
public class F2mSqrtOptimizer {
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
                implPrintRootZ(x9);
            }
        }
    }

    public static void printRootZ(X9ECParameters x9) {
        if (!ECAlgorithms.isF2mCurve(x9.getCurve())) {
            throw new IllegalArgumentException("Sqrt optimization only defined over characteristic-2 fields");
        }
        implPrintRootZ(x9);
    }

    private static void implPrintRootZ(X9ECParameters x9) {
        ECFieldElement z = x9.getCurve().fromBigInteger(BigInteger.valueOf(2));
        ECFieldElement rootZ = z.sqrt();
        System.out.println(rootZ.toBigInteger().toString(16).toUpperCase());
        if (!rootZ.square().equals(z)) {
            throw new IllegalStateException("Optimized-sqrt sanity check failed");
        }
    }

    private static ArrayList enumToList(Enumeration en) {
        ArrayList rv = new ArrayList();
        while (en.hasMoreElements()) {
            rv.add(en.nextElement());
        }
        return rv;
    }
}
