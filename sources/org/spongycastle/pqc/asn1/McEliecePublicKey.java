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

public class McEliecePublicKey extends ASN1Object {
    private byte[] matrixG;

    /* renamed from: n */
    private int f7070n;
    private ASN1ObjectIdentifier oid;

    /* renamed from: t */
    private int f7071t;

    public McEliecePublicKey(ASN1ObjectIdentifier oid2, int n, int t, GF2Matrix g) {
        this.oid = oid2;
        this.f7070n = n;
        this.f7071t = t;
        this.matrixG = g.getEncoded();
    }

    private McEliecePublicKey(ASN1Sequence seq) {
        this.oid = (ASN1ObjectIdentifier) seq.getObjectAt(0);
        this.f7070n = ((ASN1Integer) seq.getObjectAt(1)).getValue().intValue();
        this.f7071t = ((ASN1Integer) seq.getObjectAt(2)).getValue().intValue();
        this.matrixG = ((ASN1OctetString) seq.getObjectAt(3)).getOctets();
    }

    public ASN1ObjectIdentifier getOID() {
        return this.oid;
    }

    public int getN() {
        return this.f7070n;
    }

    public int getT() {
        return this.f7071t;
    }

    public GF2Matrix getG() {
        return new GF2Matrix(this.matrixG);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.oid);
        v.add(new ASN1Integer((long) this.f7070n));
        v.add(new ASN1Integer((long) this.f7071t));
        v.add(new DEROctetString(this.matrixG));
        return new DERSequence(v);
    }

    public static McEliecePublicKey getInstance(Object o) {
        if (o instanceof McEliecePublicKey) {
            return (McEliecePublicKey) o;
        }
        if (o != null) {
            return new McEliecePublicKey(ASN1Sequence.getInstance(o));
        }
        return null;
    }
}
