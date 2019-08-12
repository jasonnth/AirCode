package org.spongycastle.jce;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.p325x9.X9ECParameters;
import org.spongycastle.crypto.p326ec.CustomNamedCurves;
import org.spongycastle.jce.spec.ECNamedCurveParameterSpec;

public class ECNamedCurveTable {
    public static ECNamedCurveParameterSpec getParameterSpec(String name) {
        X9ECParameters ecP = CustomNamedCurves.getByName(name);
        if (ecP == null) {
            try {
                ecP = CustomNamedCurves.getByOID(new ASN1ObjectIdentifier(name));
            } catch (IllegalArgumentException e) {
            }
            if (ecP == null) {
                ecP = org.spongycastle.asn1.p325x9.ECNamedCurveTable.getByName(name);
                if (ecP == null) {
                    try {
                        ecP = org.spongycastle.asn1.p325x9.ECNamedCurveTable.getByOID(new ASN1ObjectIdentifier(name));
                    } catch (IllegalArgumentException e2) {
                    }
                }
            }
        }
        if (ecP == null) {
            return null;
        }
        return new ECNamedCurveParameterSpec(name, ecP.getCurve(), ecP.getG(), ecP.getN(), ecP.getH(), ecP.getSeed());
    }

    public static Enumeration getNames() {
        return org.spongycastle.asn1.p325x9.ECNamedCurveTable.getNames();
    }
}
