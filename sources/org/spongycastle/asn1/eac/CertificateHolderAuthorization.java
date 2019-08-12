package org.spongycastle.asn1.eac;

import java.io.IOException;
import java.util.Hashtable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.DERApplicationSpecific;
import org.spongycastle.util.Integers;

public class CertificateHolderAuthorization extends ASN1Object {
    static BidirectionalMap AuthorizationRole = new BidirectionalMap();
    public static final int CVCA = 192;
    public static final int DV_DOMESTIC = 128;
    public static final int DV_FOREIGN = 64;

    /* renamed from: IS */
    public static final int f6373IS = 0;
    public static final int RADG3 = 1;
    public static final int RADG4 = 2;
    static Hashtable ReverseMap = new Hashtable();
    static Hashtable RightsDecodeMap = new Hashtable();
    public static final ASN1ObjectIdentifier id_role_EAC = EACObjectIdentifiers.bsi_de.branch("3.1.2.1");
    DERApplicationSpecific accessRights;
    ASN1ObjectIdentifier oid;

    static {
        RightsDecodeMap.put(Integers.valueOf(2), "RADG4");
        RightsDecodeMap.put(Integers.valueOf(1), "RADG3");
        AuthorizationRole.put(Integers.valueOf(192), "CVCA");
        AuthorizationRole.put(Integers.valueOf(128), "DV_DOMESTIC");
        AuthorizationRole.put(Integers.valueOf(64), "DV_FOREIGN");
        AuthorizationRole.put(Integers.valueOf(0), "IS");
    }

    public static String getRoleDescription(int i) {
        return (String) AuthorizationRole.get(Integers.valueOf(i));
    }

    public static int getFlag(String description) {
        Integer i = (Integer) AuthorizationRole.getReverse(description);
        if (i != null) {
            return i.intValue();
        }
        throw new IllegalArgumentException("Unknown value " + description);
    }

    private void setPrivateData(ASN1InputStream cha) throws IOException {
        ASN1Primitive obj = cha.readObject();
        if (obj instanceof ASN1ObjectIdentifier) {
            this.oid = (ASN1ObjectIdentifier) obj;
            ASN1Primitive obj2 = cha.readObject();
            if (obj2 instanceof DERApplicationSpecific) {
                this.accessRights = (DERApplicationSpecific) obj2;
                return;
            }
            throw new IllegalArgumentException("No access rights in CerticateHolderAuthorization");
        }
        throw new IllegalArgumentException("no Oid in CerticateHolderAuthorization");
    }

    public CertificateHolderAuthorization(ASN1ObjectIdentifier oid2, int rights) throws IOException {
        setOid(oid2);
        setAccessRights((byte) rights);
    }

    public CertificateHolderAuthorization(DERApplicationSpecific aSpe) throws IOException {
        if (aSpe.getApplicationTag() == 76) {
            setPrivateData(new ASN1InputStream(aSpe.getContents()));
        }
    }

    public int getAccessRights() {
        return this.accessRights.getContents()[0] & 255;
    }

    private void setAccessRights(byte rights) {
        this.accessRights = new DERApplicationSpecific(EACTags.getTag(83), new byte[]{rights});
    }

    public ASN1ObjectIdentifier getOid() {
        return this.oid;
    }

    private void setOid(ASN1ObjectIdentifier oid2) {
        this.oid = oid2;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.oid);
        v.add(this.accessRights);
        return new DERApplicationSpecific(76, v);
    }
}
