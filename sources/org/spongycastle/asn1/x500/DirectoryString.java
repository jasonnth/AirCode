package org.spongycastle.asn1.x500;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1String;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERBMPString;
import org.spongycastle.asn1.DERPrintableString;
import org.spongycastle.asn1.DERT61String;
import org.spongycastle.asn1.DERUTF8String;
import org.spongycastle.asn1.DERUniversalString;

public class DirectoryString extends ASN1Object implements ASN1Choice, ASN1String {
    private ASN1String string;

    public static DirectoryString getInstance(Object o) {
        if (o == null || (o instanceof DirectoryString)) {
            return (DirectoryString) o;
        }
        if (o instanceof DERT61String) {
            return new DirectoryString((DERT61String) o);
        }
        if (o instanceof DERPrintableString) {
            return new DirectoryString((DERPrintableString) o);
        }
        if (o instanceof DERUniversalString) {
            return new DirectoryString((DERUniversalString) o);
        }
        if (o instanceof DERUTF8String) {
            return new DirectoryString((DERUTF8String) o);
        }
        if (o instanceof DERBMPString) {
            return new DirectoryString((DERBMPString) o);
        }
        throw new IllegalArgumentException("illegal object in getInstance: " + o.getClass().getName());
    }

    public static DirectoryString getInstance(ASN1TaggedObject o, boolean explicit) {
        if (explicit) {
            return getInstance(o.getObject());
        }
        throw new IllegalArgumentException("choice item must be explicitly tagged");
    }

    private DirectoryString(DERT61String string2) {
        this.string = string2;
    }

    private DirectoryString(DERPrintableString string2) {
        this.string = string2;
    }

    private DirectoryString(DERUniversalString string2) {
        this.string = string2;
    }

    private DirectoryString(DERUTF8String string2) {
        this.string = string2;
    }

    private DirectoryString(DERBMPString string2) {
        this.string = string2;
    }

    public DirectoryString(String string2) {
        this.string = new DERUTF8String(string2);
    }

    public String getString() {
        return this.string.getString();
    }

    public String toString() {
        return this.string.getString();
    }

    public ASN1Primitive toASN1Primitive() {
        return ((ASN1Encodable) this.string).toASN1Primitive();
    }
}
