package org.spongycastle.jcajce.provider.asymmetric.dstu;

import java.io.IOException;
import java.security.SignatureException;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.DEROctetString;

public class SignatureSpiLe extends SignatureSpi {
    /* access modifiers changed from: 0000 */
    public void reverseBytes(byte[] bytes) {
        for (int i = 0; i < bytes.length / 2; i++) {
            byte tmp = bytes[i];
            bytes[i] = bytes[(bytes.length - 1) - i];
            bytes[(bytes.length - 1) - i] = tmp;
        }
    }

    /* access modifiers changed from: protected */
    public byte[] engineSign() throws SignatureException {
        byte[] signature = ASN1OctetString.getInstance(super.engineSign()).getOctets();
        reverseBytes(signature);
        try {
            return new DEROctetString(signature).getEncoded();
        } catch (Exception e) {
            throw new SignatureException(e.toString());
        }
    }

    /* access modifiers changed from: protected */
    public boolean engineVerify(byte[] sigBytes) throws SignatureException {
        try {
            byte[] bytes = ((ASN1OctetString) ASN1OctetString.fromByteArray(sigBytes)).getOctets();
            reverseBytes(bytes);
            try {
                return super.engineVerify(new DEROctetString(bytes).getEncoded());
            } catch (SignatureException e) {
                throw e;
            } catch (Exception e2) {
                throw new SignatureException(e2.toString());
            }
        } catch (IOException e3) {
            throw new SignatureException("error decoding signature bytes.");
        }
    }
}
