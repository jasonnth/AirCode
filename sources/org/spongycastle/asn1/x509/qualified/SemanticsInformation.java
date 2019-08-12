package org.spongycastle.asn1.x509.qualified;

import java.util.Enumeration;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.GeneralName;

public class SemanticsInformation extends ASN1Object {
    private GeneralName[] nameRegistrationAuthorities;
    private ASN1ObjectIdentifier semanticsIdentifier;

    public static SemanticsInformation getInstance(Object obj) {
        if (obj instanceof SemanticsInformation) {
            return (SemanticsInformation) obj;
        }
        if (obj != null) {
            return new SemanticsInformation(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    private SemanticsInformation(ASN1Sequence seq) {
        Enumeration e = seq.getObjects();
        if (seq.size() < 1) {
            throw new IllegalArgumentException("no objects in SemanticsInformation");
        }
        Object object = e.nextElement();
        if (object instanceof ASN1ObjectIdentifier) {
            this.semanticsIdentifier = ASN1ObjectIdentifier.getInstance(object);
            if (e.hasMoreElements()) {
                object = e.nextElement();
            } else {
                object = null;
            }
        }
        if (object != null) {
            ASN1Sequence generalNameSeq = ASN1Sequence.getInstance(object);
            this.nameRegistrationAuthorities = new GeneralName[generalNameSeq.size()];
            for (int i = 0; i < generalNameSeq.size(); i++) {
                this.nameRegistrationAuthorities[i] = GeneralName.getInstance(generalNameSeq.getObjectAt(i));
            }
        }
    }

    public SemanticsInformation(ASN1ObjectIdentifier semanticsIdentifier2, GeneralName[] generalNames) {
        this.semanticsIdentifier = semanticsIdentifier2;
        this.nameRegistrationAuthorities = generalNames;
    }

    public SemanticsInformation(ASN1ObjectIdentifier semanticsIdentifier2) {
        this.semanticsIdentifier = semanticsIdentifier2;
        this.nameRegistrationAuthorities = null;
    }

    public SemanticsInformation(GeneralName[] generalNames) {
        this.semanticsIdentifier = null;
        this.nameRegistrationAuthorities = generalNames;
    }

    public ASN1ObjectIdentifier getSemanticsIdentifier() {
        return this.semanticsIdentifier;
    }

    public GeneralName[] getNameRegistrationAuthorities() {
        return this.nameRegistrationAuthorities;
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector seq = new ASN1EncodableVector();
        if (this.semanticsIdentifier != null) {
            seq.add(this.semanticsIdentifier);
        }
        if (this.nameRegistrationAuthorities != null) {
            ASN1EncodableVector seqname = new ASN1EncodableVector();
            for (GeneralName add : this.nameRegistrationAuthorities) {
                seqname.add(add);
            }
            seq.add(new DERSequence(seqname));
        }
        return new DERSequence(seq);
    }
}
