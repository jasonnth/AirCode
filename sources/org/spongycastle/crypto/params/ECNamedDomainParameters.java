package org.spongycastle.crypto.params;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECPoint;

public class ECNamedDomainParameters extends ECDomainParameters {
    private ASN1ObjectIdentifier name;

    public ECNamedDomainParameters(ASN1ObjectIdentifier name2, ECCurve curve, ECPoint G, BigInteger n) {
        this(name2, curve, G, n, null, null);
    }

    public ECNamedDomainParameters(ASN1ObjectIdentifier name2, ECCurve curve, ECPoint G, BigInteger n, BigInteger h) {
        this(name2, curve, G, n, h, null);
    }

    public ECNamedDomainParameters(ASN1ObjectIdentifier name2, ECCurve curve, ECPoint G, BigInteger n, BigInteger h, byte[] seed) {
        super(curve, G, n, h, seed);
        this.name = name2;
    }

    public ASN1ObjectIdentifier getName() {
        return this.name;
    }
}
