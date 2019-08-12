package org.spongycastle.asn1.x509;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1String;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class RoleSyntax extends ASN1Object {
    private GeneralNames roleAuthority;
    private GeneralName roleName;

    public static RoleSyntax getInstance(Object obj) {
        if (obj instanceof RoleSyntax) {
            return (RoleSyntax) obj;
        }
        if (obj != null) {
            return new RoleSyntax(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public RoleSyntax(GeneralNames roleAuthority2, GeneralName roleName2) {
        if (roleName2 == null || roleName2.getTagNo() != 6 || ((ASN1String) roleName2.getName()).getString().equals("")) {
            throw new IllegalArgumentException("the role name MUST be non empty and MUST use the URI option of GeneralName");
        }
        this.roleAuthority = roleAuthority2;
        this.roleName = roleName2;
    }

    public RoleSyntax(GeneralName roleName2) {
        this(null, roleName2);
    }

    public RoleSyntax(String roleName2) {
        if (roleName2 == null) {
            roleName2 = "";
        }
        this(new GeneralName(6, roleName2));
    }

    private RoleSyntax(ASN1Sequence seq) {
        if (seq.size() < 1 || seq.size() > 2) {
            throw new IllegalArgumentException("Bad sequence size: " + seq.size());
        }
        for (int i = 0; i != seq.size(); i++) {
            ASN1TaggedObject taggedObject = ASN1TaggedObject.getInstance(seq.getObjectAt(i));
            switch (taggedObject.getTagNo()) {
                case 0:
                    this.roleAuthority = GeneralNames.getInstance(taggedObject, false);
                    break;
                case 1:
                    this.roleName = GeneralName.getInstance(taggedObject, true);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown tag in RoleSyntax");
            }
        }
    }

    public GeneralNames getRoleAuthority() {
        return this.roleAuthority;
    }

    public GeneralName getRoleName() {
        return this.roleName;
    }

    public String getRoleNameAsString() {
        return ((ASN1String) this.roleName.getName()).getString();
    }

    public String[] getRoleAuthorityAsString() {
        if (this.roleAuthority == null) {
            return new String[0];
        }
        GeneralName[] names = this.roleAuthority.getNames();
        String[] namesString = new String[names.length];
        for (int i = 0; i < names.length; i++) {
            ASN1Encodable value = names[i].getName();
            if (value instanceof ASN1String) {
                namesString[i] = ((ASN1String) value).getString();
            } else {
                namesString[i] = value.toString();
            }
        }
        return namesString;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.roleAuthority != null) {
            v.add(new DERTaggedObject(false, 0, this.roleAuthority));
        }
        v.add(new DERTaggedObject(true, 1, this.roleName));
        return new DERSequence(v);
    }

    public String toString() {
        StringBuffer buff = new StringBuffer("Name: " + getRoleNameAsString() + " - Auth: ");
        if (this.roleAuthority == null || this.roleAuthority.getNames().length == 0) {
            buff.append("N/A");
        } else {
            String[] names = getRoleAuthorityAsString();
            buff.append('[').append(names[0]);
            for (int i = 1; i < names.length; i++) {
                buff.append(", ").append(names[i]);
            }
            buff.append(']');
        }
        return buff.toString();
    }
}
