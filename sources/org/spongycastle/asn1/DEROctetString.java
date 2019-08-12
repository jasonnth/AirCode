package org.spongycastle.asn1;

import java.io.IOException;

public class DEROctetString extends ASN1OctetString {
    public DEROctetString(byte[] string) {
        super(string);
    }

    public DEROctetString(ASN1Encodable obj) throws IOException {
        super(obj.toASN1Primitive().getEncoded(ASN1Encoding.DER));
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
        out.writeEncoded(4, this.string);
    }

    static void encode(DEROutputStream derOut, byte[] bytes) throws IOException {
        derOut.writeEncoded(4, bytes);
    }
}
