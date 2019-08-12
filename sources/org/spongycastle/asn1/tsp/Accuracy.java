package org.spongycastle.asn1.tsp;

import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class Accuracy extends ASN1Object {
    protected static final int MAX_MICROS = 999;
    protected static final int MAX_MILLIS = 999;
    protected static final int MIN_MICROS = 1;
    protected static final int MIN_MILLIS = 1;
    ASN1Integer micros;
    ASN1Integer millis;
    ASN1Integer seconds;

    protected Accuracy() {
    }

    public Accuracy(ASN1Integer seconds2, ASN1Integer millis2, ASN1Integer micros2) {
        this.seconds = seconds2;
        if (millis2 == null || (millis2.getValue().intValue() >= 1 && millis2.getValue().intValue() <= 999)) {
            this.millis = millis2;
            if (micros2 == null || (micros2.getValue().intValue() >= 1 && micros2.getValue().intValue() <= 999)) {
                this.micros = micros2;
                return;
            }
            throw new IllegalArgumentException("Invalid micros field : not in (1..999)");
        }
        throw new IllegalArgumentException("Invalid millis field : not in (1..999)");
    }

    private Accuracy(ASN1Sequence seq) {
        this.seconds = null;
        this.millis = null;
        this.micros = null;
        for (int i = 0; i < seq.size(); i++) {
            if (seq.getObjectAt(i) instanceof ASN1Integer) {
                this.seconds = (ASN1Integer) seq.getObjectAt(i);
            } else if (seq.getObjectAt(i) instanceof DERTaggedObject) {
                DERTaggedObject extra = (DERTaggedObject) seq.getObjectAt(i);
                switch (extra.getTagNo()) {
                    case 0:
                        this.millis = ASN1Integer.getInstance(extra, false);
                        if (this.millis.getValue().intValue() >= 1 && this.millis.getValue().intValue() <= 999) {
                            break;
                        } else {
                            throw new IllegalArgumentException("Invalid millis field : not in (1..999).");
                        }
                    case 1:
                        this.micros = ASN1Integer.getInstance(extra, false);
                        if (this.micros.getValue().intValue() >= 1 && this.micros.getValue().intValue() <= 999) {
                            break;
                        } else {
                            throw new IllegalArgumentException("Invalid micros field : not in (1..999).");
                        }
                    default:
                        throw new IllegalArgumentException("Invalig tag number");
                }
            } else {
                continue;
            }
        }
    }

    public static Accuracy getInstance(Object o) {
        if (o instanceof Accuracy) {
            return (Accuracy) o;
        }
        if (o != null) {
            return new Accuracy(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public ASN1Integer getSeconds() {
        return this.seconds;
    }

    public ASN1Integer getMillis() {
        return this.millis;
    }

    public ASN1Integer getMicros() {
        return this.micros;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.seconds != null) {
            v.add(this.seconds);
        }
        if (this.millis != null) {
            v.add(new DERTaggedObject(false, 0, this.millis));
        }
        if (this.micros != null) {
            v.add(new DERTaggedObject(false, 1, this.micros));
        }
        return new DERSequence(v);
    }
}
