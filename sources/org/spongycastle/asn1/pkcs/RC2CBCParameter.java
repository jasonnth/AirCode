package org.spongycastle.asn1.pkcs;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequence;

public class RC2CBCParameter extends ASN1Object {

    /* renamed from: iv */
    ASN1OctetString f6392iv;
    ASN1Integer version;

    public static RC2CBCParameter getInstance(Object o) {
        if (o instanceof RC2CBCParameter) {
            return (RC2CBCParameter) o;
        }
        if (o != null) {
            return new RC2CBCParameter(ASN1Sequence.getInstance(o));
        }
        return null;
    }

    public RC2CBCParameter(byte[] iv) {
        this.version = null;
        this.f6392iv = new DEROctetString(iv);
    }

    public RC2CBCParameter(int parameterVersion, byte[] iv) {
        this.version = new ASN1Integer((long) parameterVersion);
        this.f6392iv = new DEROctetString(iv);
    }

    private RC2CBCParameter(ASN1Sequence seq) {
        if (seq.size() == 1) {
            this.version = null;
            this.f6392iv = (ASN1OctetString) seq.getObjectAt(0);
            return;
        }
        this.version = (ASN1Integer) seq.getObjectAt(0);
        this.f6392iv = (ASN1OctetString) seq.getObjectAt(1);
    }

    public BigInteger getRC2ParameterVersion() {
        if (this.version == null) {
            return null;
        }
        return this.version.getValue();
    }

    public byte[] getIV() {
        return this.f6392iv.getOctets();
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.version != null) {
            v.add(this.version);
        }
        v.add(this.f6392iv);
        return new DERSequence(v);
    }
}
