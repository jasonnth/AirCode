package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class NameConstraints extends ASN1Object {
    private GeneralSubtree[] excluded;
    private GeneralSubtree[] permitted;

    public static NameConstraints getInstance(Object obj) {
        if (obj instanceof NameConstraints) {
            return (NameConstraints) obj;
        }
        if (obj != null) {
            return new NameConstraints(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private NameConstraints(ASN1Sequence seq) {
        Enumeration e = seq.getObjects();
        while (e.hasMoreElements()) {
            ASN1TaggedObject o = ASN1TaggedObject.getInstance(e.nextElement());
            switch (o.getTagNo()) {
                case 0:
                    this.permitted = createArray(ASN1Sequence.getInstance(o, false));
                    break;
                case 1:
                    this.excluded = createArray(ASN1Sequence.getInstance(o, false));
                    break;
            }
        }
    }

    public NameConstraints(GeneralSubtree[] permitted2, GeneralSubtree[] excluded2) {
        if (permitted2 != null) {
            this.permitted = permitted2;
        }
        if (excluded2 != null) {
            this.excluded = excluded2;
        }
    }

    private GeneralSubtree[] createArray(ASN1Sequence subtree) {
        GeneralSubtree[] ar = new GeneralSubtree[subtree.size()];
        for (int i = 0; i != ar.length; i++) {
            ar[i] = GeneralSubtree.getInstance(subtree.getObjectAt(i));
        }
        return ar;
    }

    public GeneralSubtree[] getPermittedSubtrees() {
        return this.permitted;
    }

    public GeneralSubtree[] getExcludedSubtrees() {
        return this.excluded;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.permitted != null) {
            v.add(new DERTaggedObject(false, 0, new DERSequence((ASN1Encodable[]) this.permitted)));
        }
        if (this.excluded != null) {
            v.add(new DERTaggedObject(false, 1, new DERSequence((ASN1Encodable[]) this.excluded)));
        }
        return new DERSequence(v);
    }
}
