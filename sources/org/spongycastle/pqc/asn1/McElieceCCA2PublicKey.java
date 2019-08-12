package org.spongycastle.pqc.asn1;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.pqc.math.linearalgebra.GF2Matrix;

public class McElieceCCA2PublicKey extends ASN1Object {
    private byte[] matrixG;

    /* renamed from: n */
    private int f7066n;
    private ASN1ObjectIdentifier oid;

    /* renamed from: t */
    private int f7067t;

    public McElieceCCA2PublicKey(ASN1ObjectIdentifier oid2, int n, int t, GF2Matrix g) {
        this.oid = oid2;
        this.f7066n = n;
        this.f7067t = t;
        this.matrixG = g.getEncoded();
    }

    private McElieceCCA2PublicKey(ASN1Sequence seq) {
        this.oid = (ASN1ObjectIdentifier) seq.getObjectAt(0);
        this.f7066n = ((ASN1Integer) seq.getObjectAt(1)).getValue().intValue();
        this.f7067t = ((ASN1Integer) seq.getObjectAt(2)).getValue().intValue();
        this.matrixG = ((ASN1OctetString) seq.getObjectAt(3)).getOctets();
    }

    public ASN1ObjectIdentifier getOID() {
        return this.oid;
    }

    public int getN() {
        return this.f7066n;
    }

    public int getT() {
        return this.f7067t;
    }

    public GF2Matrix getG() {
        return new GF2Matrix(this.matrixG);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.oid);
        v.add(new ASN1Integer((long) this.f7066n));
        v.add(new ASN1Integer((long) this.f7067t));
        v.add(new DEROctetString(this.matrixG));
        return new DERSequence(v);
    }

    public static McElieceCCA2PublicKey getInstance(Object o) {
        if (o instanceof McElieceCCA2PublicKey) {
            return (McElieceCCA2PublicKey) o;
        }
        if (o != null) {
            return new McElieceCCA2PublicKey(ASN1Sequence.getInstance(o));
        }
        return null;
    }
}
