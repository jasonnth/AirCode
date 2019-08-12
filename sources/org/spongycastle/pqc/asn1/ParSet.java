package org.spongycastle.pqc.asn1;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.util.Arrays;

public class ParSet extends ASN1Object {
    private static final BigInteger ZERO = BigInteger.valueOf(0);

    /* renamed from: h */
    private int[] f7072h;

    /* renamed from: k */
    private int[] f7073k;

    /* renamed from: t */
    private int f7074t;

    /* renamed from: w */
    private int[] f7075w;

    private static int checkBigIntegerInIntRangeAndPositive(BigInteger b) {
        if (b.compareTo(BigInteger.valueOf(2147483647L)) <= 0 && b.compareTo(ZERO) > 0) {
            return b.intValue();
        }
        throw new IllegalArgumentException("BigInteger not in Range: " + b.toString());
    }

    private ParSet(ASN1Sequence seq) {
        if (seq.size() != 4) {
            throw new IllegalArgumentException("sie of seqOfParams = " + seq.size());
        }
        this.f7074t = checkBigIntegerInIntRangeAndPositive(((ASN1Integer) seq.getObjectAt(0)).getValue());
        ASN1Sequence seqOfPSh = (ASN1Sequence) seq.getObjectAt(1);
        ASN1Sequence seqOfPSw = (ASN1Sequence) seq.getObjectAt(2);
        ASN1Sequence seqOfPSK = (ASN1Sequence) seq.getObjectAt(3);
        if (seqOfPSh.size() == this.f7074t && seqOfPSw.size() == this.f7074t && seqOfPSK.size() == this.f7074t) {
            this.f7072h = new int[seqOfPSh.size()];
            this.f7075w = new int[seqOfPSw.size()];
            this.f7073k = new int[seqOfPSK.size()];
            for (int i = 0; i < this.f7074t; i++) {
                this.f7072h[i] = checkBigIntegerInIntRangeAndPositive(((ASN1Integer) seqOfPSh.getObjectAt(i)).getValue());
                this.f7075w[i] = checkBigIntegerInIntRangeAndPositive(((ASN1Integer) seqOfPSw.getObjectAt(i)).getValue());
                this.f7073k[i] = checkBigIntegerInIntRangeAndPositive(((ASN1Integer) seqOfPSK.getObjectAt(i)).getValue());
            }
            return;
        }
        throw new IllegalArgumentException("invalid size of sequences");
    }

    public ParSet(int t, int[] h, int[] w, int[] k) {
        this.f7074t = t;
        this.f7072h = h;
        this.f7075w = w;
        this.f7073k = k;
    }

    public static ParSet getInstance(Object o) {
        if (o instanceof ParSet) {
            return (ParSet) o;
        }
        if (o != null) {
            return new ParSet(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public int getT() {
        return this.f7074t;
    }

    public int[] getH() {
        return Arrays.clone(this.f7072h);
    }

    public int[] getW() {
        return Arrays.clone(this.f7075w);
    }

    public int[] getK() {
        return Arrays.clone(this.f7073k);
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector seqOfPSh = new ASN1EncodableVector();
        ASN1EncodableVector seqOfPSw = new ASN1EncodableVector();
        ASN1EncodableVector seqOfPSK = new ASN1EncodableVector();
        for (int i = 0; i < this.f7072h.length; i++) {
            seqOfPSh.add(new ASN1Integer((long) this.f7072h[i]));
            seqOfPSw.add(new ASN1Integer((long) this.f7075w[i]));
            seqOfPSK.add(new ASN1Integer((long) this.f7073k[i]));
        }
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer((long) this.f7074t));
        v.add(new DERSequence(seqOfPSh));
        v.add(new DERSequence(seqOfPSw));
        v.add(new DERSequence(seqOfPSK));
        return new DERSequence(v);
    }
}
