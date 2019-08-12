package org.spongycastle.asn1.x509;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;

public class Targets extends ASN1Object {
    private ASN1Sequence targets;

    public static Targets getInstance(Object obj) {
        if (obj instanceof Targets) {
            return (Targets) obj;
        }
        if (obj != null) {
            return new Targets(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private Targets(ASN1Sequence targets2) {
        this.targets = targets2;
    }

    public Targets(Target[] targets2) {
        this.targets = new DERSequence((ASN1Encodable[]) targets2);
    }

    public Target[] getTargets() {
        Target[] targs = new Target[this.targets.size()];
        int count = 0;
        Enumeration e = this.targets.getObjects();
        while (e.hasMoreElements()) {
            int count2 = count + 1;
            targs[count] = Target.getInstance(e.nextElement());
            count = count2;
        }
        return targs;
    }

    public ASN1Primitive toASN1Primitive() {
        return this.targets;
    }
}
