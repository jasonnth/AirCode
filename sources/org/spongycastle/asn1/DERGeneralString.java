package org.spongycastle.asn1;

import java.io.IOException;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Strings;

public class DERGeneralString extends ASN1Primitive implements ASN1String {
    private final byte[] string;

    public static DERGeneralString getInstance(Object obj) {
        if (obj == null || (obj instanceof DERGeneralString)) {
            return (DERGeneralString) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (DERGeneralString) fromByteArray((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static DERGeneralString getInstance(ASN1TaggedObject obj, boolean explicit) {
        ASN1Primitive o = obj.getObject();
        if (explicit || (o instanceof DERGeneralString)) {
            return getInstance(o);
        }
        return new DERGeneralString(((ASN1OctetString) o).getOctets());
    }

    DERGeneralString(byte[] string2) {
        this.string = string2;
    }

    public DERGeneralString(String string2) {
        this.string = Strings.toByteArray(string2);
    }

    public String getString() {
        return Strings.fromByteArray(this.string);
    }

    public String toString() {
        return getString();
    }

    public byte[] getOctets() {
        return Arrays.clone(this.string);
    }

    /* access modifiers changed from: 0000 */
    public boolean isConstructed() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public int encodedLength() {
        return StreamUtil.calculateBodyLength(this.string.length) + 1 + this.string.length;
    }

    /* access modifiers changed from: 0000 */
    public void encode(ASN1OutputStream out) throws IOException {
        out.writeEncoded(27, this.string);
    }

    public int hashCode() {
        return Arrays.hashCode(this.string);
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(ASN1Primitive o) {
        if (!(o instanceof DERGeneralString)) {
            return false;
        }
        return Arrays.areEqual(this.string, ((DERGeneralString) o).string);
    }
}
