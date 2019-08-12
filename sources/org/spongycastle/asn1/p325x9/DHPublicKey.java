package org.spongycastle.asn1.p325x9;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1TaggedObject;

/* renamed from: org.spongycastle.asn1.x9.DHPublicKey */
public class DHPublicKey extends ASN1Object {

    /* renamed from: y */
    private ASN1Integer f6441y;

    public static DHPublicKey getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Integer.getInstance(obj, explicit));
    }

    public static DHPublicKey getInstance(Object obj) {
        if (obj == null || (obj instanceof DHPublicKey)) {
            return (DHPublicKey) obj;
        }
        if (obj instanceof ASN1Integer) {
            return new DHPublicKey((ASN1Integer) obj);
        }
        throw new IllegalArgumentException("Invalid DHPublicKey: " + obj.getClass().getName());
    }

    private DHPublicKey(ASN1Integer y) {
        if (y == null) {
            throw new IllegalArgumentException("'y' cannot be null");
        }
        this.f6441y = y;
    }

    public DHPublicKey(BigInteger y) {
        if (y == null) {
            throw new IllegalArgumentException("'y' cannot be null");
        }
        this.f6441y = new ASN1Integer(y);
    }

    public BigInteger getY() {
        return this.f6441y.getPositiveValue();
    }

    public ASN1Primitive toASN1Primitive() {
        return this.f6441y;
    }
}
