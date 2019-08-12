package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import java.util.Vector;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class SubjectDirectoryAttributes extends ASN1Object {
    private Vector attributes = new Vector();

    public static SubjectDirectoryAttributes getInstance(Object obj) {
        if (obj instanceof SubjectDirectoryAttributes) {
            return (SubjectDirectoryAttributes) obj;
        }
        if (obj != null) {
            return new SubjectDirectoryAttributes(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private SubjectDirectoryAttributes(ASN1Sequence seq) {
        Enumeration e = seq.getObjects();
        while (e.hasMoreElements()) {
            this.attributes.addElement(Attribute.getInstance(ASN1Sequence.getInstance(e.nextElement())));
        }
    }

    public SubjectDirectoryAttributes(Vector attributes2) {
        Enumeration e = attributes2.elements();
        while (e.hasMoreElements()) {
            this.attributes.addElement(e.nextElement());
        }
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector vec = new ASN1EncodableVector();
        Enumeration e = this.attributes.elements();
        while (e.hasMoreElements()) {
            vec.add((Attribute) e.nextElement());
        }
        return new DERSequence(vec);
    }

    public Vector getAttributes() {
        return this.attributes;
    }
}
