package org.spongycastle.asn1.p324ua;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

/* renamed from: org.spongycastle.asn1.ua.DSTU4145BinaryField */
public class DSTU4145BinaryField extends ASN1Object {

    /* renamed from: j */
    private int f6393j;

    /* renamed from: k */
    private int f6394k;

    /* renamed from: l */
    private int f6395l;

    /* renamed from: m */
    private int f6396m;

    private DSTU4145BinaryField(ASN1Sequence seq) {
        this.f6396m = ASN1Integer.getInstance(seq.getObjectAt(0)).getPositiveValue().intValue();
        if (seq.getObjectAt(1) instanceof ASN1Integer) {
            this.f6394k = ((ASN1Integer) seq.getObjectAt(1)).getPositiveValue().intValue();
        } else if (seq.getObjectAt(1) instanceof ASN1Sequence) {
            ASN1Sequence coefs = ASN1Sequence.getInstance(seq.getObjectAt(1));
            this.f6394k = ASN1Integer.getInstance(coefs.getObjectAt(0)).getPositiveValue().intValue();
            this.f6393j = ASN1Integer.getInstance(coefs.getObjectAt(1)).getPositiveValue().intValue();
            this.f6395l = ASN1Integer.getInstance(coefs.getObjectAt(2)).getPositiveValue().intValue();
        } else {
            throw new IllegalArgumentException("object parse error");
        }
    }

    public static DSTU4145BinaryField getInstance(Object obj) {
        if (obj instanceof DSTU4145BinaryField) {
            return (DSTU4145BinaryField) obj;
        }
        if (obj != null) {
            return new DSTU4145BinaryField(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public DSTU4145BinaryField(int m, int k1, int k2, int k3) {
        this.f6396m = m;
        this.f6394k = k1;
        this.f6393j = k2;
        this.f6395l = k3;
    }

    public int getM() {
        return this.f6396m;
    }

    public int getK1() {
        return this.f6394k;
    }

    public int getK2() {
        return this.f6393j;
    }

    public int getK3() {
        return this.f6395l;
    }

    public DSTU4145BinaryField(int m, int k) {
        this(m, k, 0, 0);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer((long) this.f6396m));
        if (this.f6393j == 0) {
            v.add(new ASN1Integer((long) this.f6394k));
        } else {
            ASN1EncodableVector coefs = new ASN1EncodableVector();
            coefs.add(new ASN1Integer((long) this.f6394k));
            coefs.add(new ASN1Integer((long) this.f6393j));
            coefs.add(new ASN1Integer((long) this.f6395l));
            v.add(new DERSequence(coefs));
        }
        return new DERSequence(v);
    }
}
