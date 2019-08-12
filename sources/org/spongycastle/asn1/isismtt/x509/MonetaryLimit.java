package org.spongycastle.asn1.isismtt.x509;

import java.math.BigInteger;
import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.DERSequence;

public class MonetaryLimit extends ASN1Object {
    ASN1Integer amount;
    DERPrintableString currency;
    ASN1Integer exponent;

    public static MonetaryLimit getInstance(Object obj) {
        if (obj == null || (obj instanceof MonetaryLimit)) {
            return (MonetaryLimit) obj;
        }
        if (obj instanceof ASN1Sequence) {
            return new MonetaryLimit(ASN1Sequence.getInstance(obj));
        }
        throw new IllegalArgumentException("unknown object in getInstance");
    }

    private MonetaryLimit(ASN1Sequence seq) {
        if (seq.size() != 3) {
            throw new IllegalArgumentException("Bad sequence size: " + seq.size());
        }
        Enumeration e = seq.getObjects();
        this.currency = DERPrintableString.getInstance(e.nextElement());
        this.amount = ASN1Integer.getInstance(e.nextElement());
        this.exponent = ASN1Integer.getInstance(e.nextElement());
    }

    public MonetaryLimit(String currency2, int amount2, int exponent2) {
        this.currency = new DERPrintableString(currency2, true);
        this.amount = new ASN1Integer((long) amount2);
        this.exponent = new ASN1Integer((long) exponent2);
    }

    public String getCurrency() {
        return this.currency.getString();
    }

    public BigInteger getAmount() {
        return this.amount.getValue();
    }

    public BigInteger getExponent() {
        return this.exponent.getValue();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector seq = new ASN1EncodableVector();
        seq.add(this.currency);
        seq.add(this.amount);
        seq.add(this.exponent);
        return new DERSequence(seq);
    }
}
