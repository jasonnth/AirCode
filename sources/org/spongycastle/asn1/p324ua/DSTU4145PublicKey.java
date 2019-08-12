package org.spongycastle.asn1.p324ua;

import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.math.p332ec.ECPoint;

/* renamed from: org.spongycastle.asn1.ua.DSTU4145PublicKey */
public class DSTU4145PublicKey extends ASN1Object {
    private ASN1OctetString pubKey;

    public DSTU4145PublicKey(ECPoint pubKey2) {
        this.pubKey = new DEROctetString(DSTU4145PointEncoder.encodePoint(pubKey2));
    }

    private DSTU4145PublicKey(ASN1OctetString ocStr) {
        this.pubKey = ocStr;
    }

    public static DSTU4145PublicKey getInstance(Object obj) {
        if (obj instanceof DSTU4145PublicKey) {
            return (DSTU4145PublicKey) obj;
        }
        if (obj != null) {
            return new DSTU4145PublicKey(ASN1OctetString.getInstance(obj));
        }
        return null;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.pubKey;
    }
}
