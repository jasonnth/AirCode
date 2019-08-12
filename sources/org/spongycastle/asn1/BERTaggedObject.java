package org.spongycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

public class BERTaggedObject extends ASN1TaggedObject {
    public BERTaggedObject(int tagNo, ASN1Encodable obj) {
        super(true, tagNo, obj);
    }

    public BERTaggedObject(boolean explicit, int tagNo, ASN1Encodable obj) {
        super(explicit, tagNo, obj);
    }

    public BERTaggedObject(int tagNo) {
        super(false, tagNo, new BERSequence());
    }

    /* access modifiers changed from: 0000 */
    public boolean isConstructed() {
        if (this.empty || this.explicit) {
            return true;
        }
        return this.obj.toASN1Primitive().toDERObject().isConstructed();
    }

    /* access modifiers changed from: 0000 */
    public int encodedLength() throws IOException {
        if (this.empty) {
            return StreamUtil.calculateTagLength(this.tagNo) + 1;
        }
        int length = this.obj.toASN1Primitive().encodedLength();
        if (this.explicit) {
            return StreamUtil.calculateTagLength(this.tagNo) + StreamUtil.calculateBodyLength(length) + length;
        }
        return StreamUtil.calculateTagLength(this.tagNo) + (length - 1);
    }

    /* access modifiers changed from: 0000 */
    public void encode(ASN1OutputStream out) throws IOException {
        Enumeration e;
        out.writeTag(160, this.tagNo);
        out.write(128);
        if (!this.empty) {
            if (!this.explicit) {
                if (this.obj instanceof ASN1OctetString) {
                    if (this.obj instanceof BEROctetString) {
                        e = ((BEROctetString) this.obj).getObjects();
                    } else {
                        e = new BEROctetString(((ASN1OctetString) this.obj).getOctets()).getObjects();
                    }
                } else if (this.obj instanceof ASN1Sequence) {
                    e = ((ASN1Sequence) this.obj).getObjects();
                } else if (this.obj instanceof ASN1Set) {
                    e = ((ASN1Set) this.obj).getObjects();
                } else {
                    throw new RuntimeException("not implemented: " + this.obj.getClass().getName());
                }
                while (e.hasMoreElements()) {
                    out.writeObject((ASN1Encodable) e.nextElement());
                }
            } else {
                out.writeObject(this.obj);
            }
        }
        out.write(0);
        out.write(0);
    }
}
