package org.spongycastle.asn1.dvcs;

import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;

public class TargetEtcChain extends ASN1Object {
    private ASN1Sequence chain;
    private PathProcInput pathProcInput;
    private CertEtcToken target;

    public TargetEtcChain(CertEtcToken target2) {
        this(target2, null, null);
    }

    public TargetEtcChain(CertEtcToken target2, CertEtcToken[] chain2) {
        this(target2, chain2, null);
    }

    public TargetEtcChain(CertEtcToken target2, PathProcInput pathProcInput2) {
        this(target2, null, pathProcInput2);
    }

    public TargetEtcChain(CertEtcToken target2, CertEtcToken[] chain2, PathProcInput pathProcInput2) {
        this.target = target2;
        if (chain2 != null) {
            this.chain = new DERSequence((ASN1Encodable[]) chain2);
        }
        this.pathProcInput = pathProcInput2;
    }

    private TargetEtcChain(ASN1Sequence seq) {
        int i = 0 + 1;
        this.target = CertEtcToken.getInstance(seq.getObjectAt(0));
        int i2 = i + 1;
        try {
            this.chain = ASN1Sequence.getInstance(seq.getObjectAt(i));
        } catch (IllegalArgumentException e) {
        } catch (IndexOutOfBoundsException e2) {
            return;
        }
        i = i2 + 1;
        try {
            ASN1TaggedObject tagged = ASN1TaggedObject.getInstance(seq.getObjectAt(i2));
            switch (tagged.getTagNo()) {
                case 0:
                    this.pathProcInput = PathProcInput.getInstance(tagged, false);
                    break;
            }
        } catch (IllegalArgumentException | IndexOutOfBoundsException e3) {
        }
        int i3 = i;
    }

    public static TargetEtcChain getInstance(Object obj) {
        if (obj instanceof TargetEtcChain) {
            return (TargetEtcChain) obj;
        }
        if (obj != null) {
            return new TargetEtcChain(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static TargetEtcChain getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(this.target);
        if (this.chain != null) {
            v.add(this.chain);
        }
        if (this.pathProcInput != null) {
            v.add(new DERTaggedObject(false, 0, this.pathProcInput));
        }
        return new DERSequence(v);
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("TargetEtcChain {\n");
        s.append("target: " + this.target + "\n");
        if (this.chain != null) {
            s.append("chain: " + this.chain + "\n");
        }
        if (this.pathProcInput != null) {
            s.append("pathProcInput: " + this.pathProcInput + "\n");
        }
        s.append("}\n");
        return s.toString();
    }

    public CertEtcToken getTarget() {
        return this.target;
    }

    public CertEtcToken[] getChain() {
        if (this.chain != null) {
            return CertEtcToken.arrayFromSequence(this.chain);
        }
        return null;
    }

    private void setChain(ASN1Sequence chain2) {
        this.chain = chain2;
    }

    public PathProcInput getPathProcInput() {
        return this.pathProcInput;
    }

    private void setPathProcInput(PathProcInput pathProcInput2) {
        this.pathProcInput = pathProcInput2;
    }

    public static TargetEtcChain[] arrayFromSequence(ASN1Sequence seq) {
        TargetEtcChain[] tmp = new TargetEtcChain[seq.size()];
        for (int i = 0; i != tmp.length; i++) {
            tmp[i] = getInstance(seq.getObjectAt(i));
        }
        return tmp;
    }
}
