package org.spongycastle.asn1.dvcs;

import java.math.BigInteger;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.PolicyInformation;

public class DVCSRequestInformation extends ASN1Object {
    private static final int DEFAULT_VERSION = 1;
    private static final int TAG_DATA_LOCATIONS = 3;
    private static final int TAG_DVCS = 2;
    private static final int TAG_EXTENSIONS = 4;
    private static final int TAG_REQUESTER = 0;
    private static final int TAG_REQUEST_POLICY = 1;
    private GeneralNames dataLocations;
    private GeneralNames dvcs;
    private Extensions extensions;
    private BigInteger nonce;
    private PolicyInformation requestPolicy;
    private DVCSTime requestTime;
    private GeneralNames requester;
    private ServiceType service;
    private int version = 1;

    private DVCSRequestInformation(ASN1Sequence seq) {
        int i = 0;
        if (seq.getObjectAt(0) instanceof ASN1Integer) {
            int i2 = 0 + 1;
            this.version = ASN1Integer.getInstance(seq.getObjectAt(0)).getValue().intValue();
            i = i2;
        } else {
            this.version = 1;
        }
        int i3 = i + 1;
        this.service = ServiceType.getInstance(seq.getObjectAt(i));
        for (int i4 = i3; i4 < seq.size(); i4++) {
            ASN1Encodable x = seq.getObjectAt(i4);
            if (x instanceof ASN1Integer) {
                this.nonce = ASN1Integer.getInstance(x).getValue();
            } else if (x instanceof ASN1GeneralizedTime) {
                this.requestTime = DVCSTime.getInstance(x);
            } else if (x instanceof ASN1TaggedObject) {
                ASN1TaggedObject t = ASN1TaggedObject.getInstance(x);
                switch (t.getTagNo()) {
                    case 0:
                        this.requester = GeneralNames.getInstance(t, false);
                        break;
                    case 1:
                        this.requestPolicy = PolicyInformation.getInstance(ASN1Sequence.getInstance(t, false));
                        break;
                    case 2:
                        this.dvcs = GeneralNames.getInstance(t, false);
                        break;
                    case 3:
                        this.dataLocations = GeneralNames.getInstance(t, false);
                        break;
                    case 4:
                        this.extensions = Extensions.getInstance(t, false);
                        break;
                }
            } else {
                this.requestTime = DVCSTime.getInstance(x);
            }
        }
    }

    public static DVCSRequestInformation getInstance(Object obj) {
        if (obj instanceof DVCSRequestInformation) {
            return (DVCSRequestInformation) obj;
        }
        if (obj != null) {
            return new DVCSRequestInformation(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static DVCSRequestInformation getInstance(ASN1TaggedObject obj, boolean explicit) {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if (this.version != 1) {
            v.add(new ASN1Integer((long) this.version));
        }
        v.add(this.service);
        if (this.nonce != null) {
            v.add(new ASN1Integer(this.nonce));
        }
        if (this.requestTime != null) {
            v.add(this.requestTime);
        }
        int[] tags = {0, 1, 2, 3, 4};
        ASN1Encodable[] taggedObjects = {this.requester, this.requestPolicy, this.dvcs, this.dataLocations, this.extensions};
        for (int i = 0; i < tags.length; i++) {
            int tag = tags[i];
            ASN1Encodable taggedObject = taggedObjects[i];
            if (taggedObject != null) {
                v.add(new DERTaggedObject(false, tag, taggedObject));
            }
        }
        return new DERSequence(v);
    }

    public String toString() {
        StringBuffer s = new StringBuffer();
        s.append("DVCSRequestInformation {\n");
        if (this.version != 1) {
            s.append("version: " + this.version + "\n");
        }
        s.append("service: " + this.service + "\n");
        if (this.nonce != null) {
            s.append("nonce: " + this.nonce + "\n");
        }
        if (this.requestTime != null) {
            s.append("requestTime: " + this.requestTime + "\n");
        }
        if (this.requester != null) {
            s.append("requester: " + this.requester + "\n");
        }
        if (this.requestPolicy != null) {
            s.append("requestPolicy: " + this.requestPolicy + "\n");
        }
        if (this.dvcs != null) {
            s.append("dvcs: " + this.dvcs + "\n");
        }
        if (this.dataLocations != null) {
            s.append("dataLocations: " + this.dataLocations + "\n");
        }
        if (this.extensions != null) {
            s.append("extensions: " + this.extensions + "\n");
        }
        s.append("}\n");
        return s.toString();
    }

    public int getVersion() {
        return this.version;
    }

    public ServiceType getService() {
        return this.service;
    }

    public BigInteger getNonce() {
        return this.nonce;
    }

    public DVCSTime getRequestTime() {
        return this.requestTime;
    }

    public GeneralNames getRequester() {
        return this.requester;
    }

    public PolicyInformation getRequestPolicy() {
        return this.requestPolicy;
    }

    public GeneralNames getDVCS() {
        return this.dvcs;
    }

    public GeneralNames getDataLocations() {
        return this.dataLocations;
    }

    public Extensions getExtensions() {
        return this.extensions;
    }
}
