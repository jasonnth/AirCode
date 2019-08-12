package org.spongycastle.asn1;

import java.io.IOException;
import java.io.InputStream;

public class DEROctetStringParser implements ASN1OctetStringParser {
    private DefiniteLengthInputStream stream;

    DEROctetStringParser(DefiniteLengthInputStream stream2) {
        this.stream = stream2;
    }

    public InputStream getOctetStream() {
        return this.stream;
    }

    public ASN1Primitive getLoadedObject() throws IOException {
        return new DEROctetString(this.stream.toByteArray());
    }

    public ASN1Primitive toASN1Primitive() {
        try {
            return getLoadedObject();
        } catch (IOException e) {
            throw new ASN1ParsingException("IOException converting stream to byte array: " + e.getMessage(), e);
        }
    }
}
