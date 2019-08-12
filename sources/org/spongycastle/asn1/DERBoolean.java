package org.spongycastle.asn1;

public class DERBoolean extends ASN1Boolean {
    public DERBoolean(boolean value) {
        super(value);
    }

    DERBoolean(byte[] value) {
        super(value);
    }
}
