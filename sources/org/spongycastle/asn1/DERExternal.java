package org.spongycastle.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DERExternal extends ASN1Primitive {
    private ASN1Primitive dataValueDescriptor;
    private ASN1ObjectIdentifier directReference;
    private int encoding;
    private ASN1Primitive externalContent;
    private ASN1Integer indirectReference;

    public DERExternal(ASN1EncodableVector vector) {
        int offset = 0;
        ASN1Primitive enc = getObjFromVector(vector, 0);
        if (enc instanceof ASN1ObjectIdentifier) {
            this.directReference = (ASN1ObjectIdentifier) enc;
            offset = 0 + 1;
            enc = getObjFromVector(vector, offset);
        }
        if (enc instanceof ASN1Integer) {
            this.indirectReference = (ASN1Integer) enc;
            offset++;
            enc = getObjFromVector(vector, offset);
        }
        if (!(enc instanceof ASN1TaggedObject)) {
            this.dataValueDescriptor = enc;
            offset++;
            enc = getObjFromVector(vector, offset);
        }
        if (vector.size() != offset + 1) {
            throw new IllegalArgumentException("input vector too large");
        } else if (!(enc instanceof ASN1TaggedObject)) {
            throw new IllegalArgumentException("No tagged object found in vector. Structure doesn't seem to be of type External");
        } else {
            ASN1TaggedObject obj = (ASN1TaggedObject) enc;
            setEncoding(obj.getTagNo());
            this.externalContent = obj.getObject();
        }
    }

    private ASN1Primitive getObjFromVector(ASN1EncodableVector v, int index) {
        if (v.size() > index) {
            return v.get(index).toASN1Primitive();
        }
        throw new IllegalArgumentException("too few objects in input vector");
    }

    public DERExternal(ASN1ObjectIdentifier directReference2, ASN1Integer indirectReference2, ASN1Primitive dataValueDescriptor2, DERTaggedObject externalData) {
        this(directReference2, indirectReference2, dataValueDescriptor2, externalData.getTagNo(), externalData.toASN1Primitive());
    }

    public DERExternal(ASN1ObjectIdentifier directReference2, ASN1Integer indirectReference2, ASN1Primitive dataValueDescriptor2, int encoding2, ASN1Primitive externalData) {
        setDirectReference(directReference2);
        setIndirectReference(indirectReference2);
        setDataValueDescriptor(dataValueDescriptor2);
        setEncoding(encoding2);
        setExternalContent(externalData.toASN1Primitive());
    }

    public int hashCode() {
        int ret = 0;
        if (this.directReference != null) {
            ret = this.directReference.hashCode();
        }
        if (this.indirectReference != null) {
            ret ^= this.indirectReference.hashCode();
        }
        if (this.dataValueDescriptor != null) {
            ret ^= this.dataValueDescriptor.hashCode();
        }
        return ret ^ this.externalContent.hashCode();
    }

    /* access modifiers changed from: 0000 */
    public boolean isConstructed() {
        return true;
    }

    /* access modifiers changed from: 0000 */
    public int encodedLength() throws IOException {
        return getEncoded().length;
    }

    /* access modifiers changed from: 0000 */
    public void encode(ASN1OutputStream out) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (this.directReference != null) {
            baos.write(this.directReference.getEncoded(ASN1Encoding.DER));
        }
        if (this.indirectReference != null) {
            baos.write(this.indirectReference.getEncoded(ASN1Encoding.DER));
        }
        if (this.dataValueDescriptor != null) {
            baos.write(this.dataValueDescriptor.getEncoded(ASN1Encoding.DER));
        }
        baos.write(new DERTaggedObject(true, this.encoding, this.externalContent).getEncoded(ASN1Encoding.DER));
        out.writeEncoded(32, 8, baos.toByteArray());
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(ASN1Primitive o) {
        if (!(o instanceof DERExternal)) {
            return false;
        }
        if (this == o) {
            return true;
        }
        DERExternal other = (DERExternal) o;
        if (this.directReference != null && (other.directReference == null || !other.directReference.equals(this.directReference))) {
            return false;
        }
        if (this.indirectReference != null && (other.indirectReference == null || !other.indirectReference.equals(this.indirectReference))) {
            return false;
        }
        if (this.dataValueDescriptor == null || (other.dataValueDescriptor != null && other.dataValueDescriptor.equals(this.dataValueDescriptor))) {
            return this.externalContent.equals(other.externalContent);
        }
        return false;
    }

    public ASN1Primitive getDataValueDescriptor() {
        return this.dataValueDescriptor;
    }

    public ASN1ObjectIdentifier getDirectReference() {
        return this.directReference;
    }

    public int getEncoding() {
        return this.encoding;
    }

    public ASN1Primitive getExternalContent() {
        return this.externalContent;
    }

    public ASN1Integer getIndirectReference() {
        return this.indirectReference;
    }

    private void setDataValueDescriptor(ASN1Primitive dataValueDescriptor2) {
        this.dataValueDescriptor = dataValueDescriptor2;
    }

    private void setDirectReference(ASN1ObjectIdentifier directReferemce) {
        this.directReference = directReferemce;
    }

    private void setEncoding(int encoding2) {
        if (encoding2 < 0 || encoding2 > 2) {
            throw new IllegalArgumentException("invalid encoding value: " + encoding2);
        }
        this.encoding = encoding2;
    }

    private void setExternalContent(ASN1Primitive externalContent2) {
        this.externalContent = externalContent2;
    }

    private void setIndirectReference(ASN1Integer indirectReference2) {
        this.indirectReference = indirectReference2;
    }
}
