package org.spongycastle.asn1;

import java.io.IOException;
import java.math.BigInteger;
import org.spongycastle.util.Arrays;

public class ASN1Enumerated extends ASN1Primitive {
    private static ASN1Enumerated[] cache = new ASN1Enumerated[12];
    private final byte[] bytes;

    public static ASN1Enumerated getInstance(Object obj) {
        if (obj == null || (obj instanceof ASN1Enumerated)) {
            return (ASN1Enumerated) obj;
        }
        if (obj instanceof byte[]) {
            try {
                return (ASN1Enumerated) fromByteArray((byte[]) obj);
            } catch (Exception e) {
                throw new IllegalArgumentException("encoding error in getInstance: " + e.toString());
            }
        } else {
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
    }

    public static ASN1Enumerated getInstance(ASN1TaggedObject obj, boolean explicit) {
        ASN1Primitive o = obj.getObject();
        if (explicit || (o instanceof ASN1Enumerated)) {
            return getInstance(o);
        }
        return fromOctetString(((ASN1OctetString) o).getOctets());
    }

    public ASN1Enumerated(int value) {
        this.bytes = BigInteger.valueOf((long) value).toByteArray();
    }

    public ASN1Enumerated(BigInteger value) {
        this.bytes = value.toByteArray();
    }

    public ASN1Enumerated(byte[] bytes2) {
        this.bytes = bytes2;
    }

    public BigInteger getValue() {
        return new BigInteger(this.bytes);
    }

    /* access modifiers changed from: 0000 */
    public boolean isConstructed() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public int encodedLength() {
        return StreamUtil.calculateBodyLength(this.bytes.length) + 1 + this.bytes.length;
    }

    /* access modifiers changed from: 0000 */
    public void encode(ASN1OutputStream out) throws IOException {
        out.writeEncoded(10, this.bytes);
    }

    /* access modifiers changed from: 0000 */
    public boolean asn1Equals(ASN1Primitive o) {
        if (!(o instanceof ASN1Enumerated)) {
            return false;
        }
        return Arrays.areEqual(this.bytes, ((ASN1Enumerated) o).bytes);
    }

    public int hashCode() {
        return Arrays.hashCode(this.bytes);
    }

    static ASN1Enumerated fromOctetString(byte[] enc) {
        if (enc.length > 1) {
            return new ASN1Enumerated(Arrays.clone(enc));
        }
        if (enc.length == 0) {
            throw new IllegalArgumentException("ENUMERATED has zero length");
        }
        int value = enc[0] & 255;
        if (value >= cache.length) {
            return new ASN1Enumerated(Arrays.clone(enc));
        }
        ASN1Enumerated possibleMatch = cache[value];
        if (possibleMatch != null) {
            return possibleMatch;
        }
        ASN1Enumerated[] aSN1EnumeratedArr = cache;
        ASN1Enumerated aSN1Enumerated = new ASN1Enumerated(Arrays.clone(enc));
        aSN1EnumeratedArr[value] = aSN1Enumerated;
        return aSN1Enumerated;
    }
}
