package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Choice;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.util.Strings;

public class DistributionPointName extends ASN1Object implements ASN1Choice {
    public static final int FULL_NAME = 0;
    public static final int NAME_RELATIVE_TO_CRL_ISSUER = 1;
    ASN1Encodable name;
    int type;

    public static DistributionPointName getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1TaggedObject.getInstance(obj, true));
    }

    public static DistributionPointName getInstance(Object obj) {
        if (obj == null || (obj instanceof DistributionPointName)) {
            return (DistributionPointName) obj;
        }
        if (obj instanceof ASN1TaggedObject) {
            return new DistributionPointName((ASN1TaggedObject) obj);
        }
        throw new IllegalArgumentException("unknown object in factory: " + obj.getClass().getName());
    }

    public DistributionPointName(int type2, ASN1Encodable name2) {
        this.type = type2;
        this.name = name2;
    }

    public DistributionPointName(GeneralNames name2) {
        this(0, name2);
    }

    public int getType() {
        return this.type;
    }

    public ASN1Encodable getName() {
        return this.name;
    }

    public DistributionPointName(ASN1TaggedObject obj) {
        this.type = obj.getTagNo();
        if (this.type == 0) {
            this.name = GeneralNames.getInstance(obj, false);
        } else {
            this.name = ASN1Set.getInstance(obj, false);
        }
    }

    public ASN1Primitive toASN1Primitive() {
        return new DERTaggedObject(false, this.type, this.name);
    }

    public String toString() {
        String sep = Strings.lineSeparator();
        StringBuffer buf = new StringBuffer();
        buf.append("DistributionPointName: [");
        buf.append(sep);
        if (this.type == 0) {
            appendObject(buf, sep, "fullName", this.name.toString());
        } else {
            appendObject(buf, sep, "nameRelativeToCRLIssuer", this.name.toString());
        }
        buf.append("]");
        buf.append(sep);
        return buf.toString();
    }

    private void appendObject(StringBuffer buf, String sep, String name2, String value) {
        String indent = "    ";
        buf.append(indent);
        buf.append(name2);
        buf.append(":");
        buf.append(sep);
        buf.append(indent);
        buf.append(indent);
        buf.append(value);
        buf.append(sep);
    }
}
