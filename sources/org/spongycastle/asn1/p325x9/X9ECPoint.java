package org.spongycastle.asn1.p325x9;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.util.Arrays;

/* renamed from: org.spongycastle.asn1.x9.X9ECPoint */
public class X9ECPoint extends ASN1Object {

    /* renamed from: c */
    private ECCurve f6449c;
    private final ASN1OctetString encoding;

    /* renamed from: p */
    private ECPoint f6450p;

    public X9ECPoint(ECPoint p) {
        this(p, false);
    }

    public X9ECPoint(ECPoint p, boolean compressed) {
        this.f6450p = p.normalize();
        this.encoding = new DEROctetString(p.getEncoded(compressed));
    }

    public X9ECPoint(ECCurve c, byte[] encoding2) {
        this.f6449c = c;
        this.encoding = new DEROctetString(Arrays.clone(encoding2));
    }

    public X9ECPoint(ECCurve c, ASN1OctetString s) {
        this(c, s.getOctets());
    }

    public byte[] getPointEncoding() {
        return Arrays.clone(this.encoding.getOctets());
    }

    public ECPoint getPoint() {
        if (this.f6450p == null) {
            this.f6450p = this.f6449c.decodePoint(this.encoding.getOctets()).normalize();
        }
        return this.f6450p;
    }

    public boolean isPointCompressed() {
        byte[] octets = this.encoding.getOctets();
        if (octets == null || octets.length <= 0) {
            return false;
        }
        if (octets[0] == 2 || octets[0] == 3) {
            return true;
        }
        return false;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.encoding;
    }
}
