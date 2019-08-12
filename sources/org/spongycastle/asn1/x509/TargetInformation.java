package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class TargetInformation extends ASN1Object {
    private ASN1Sequence targets;

    public static TargetInformation getInstance(Object obj) {
        if (obj instanceof TargetInformation) {
            return (TargetInformation) obj;
        }
        if (obj != null) {
            return new TargetInformation(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private TargetInformation(ASN1Sequence seq) {
        this.targets = seq;
    }

    public Targets[] getTargetsObjects() {
        Targets[] copy = new Targets[this.targets.size()];
        int count = 0;
        Enumeration e = this.targets.getObjects();
        while (e.hasMoreElements()) {
            int count2 = count + 1;
            copy[count] = Targets.getInstance(e.nextElement());
            count = count2;
        }
        return copy;
    }

    public TargetInformation(Targets targets2) {
        this.targets = new DERSequence((ASN1Encodable) targets2);
    }

    public TargetInformation(Target[] targets2) {
        this(new Targets(targets2));
    }

    public ASN1Primitive toASN1Primitive() {
        return this.targets;
    }
}
