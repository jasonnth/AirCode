package org.ejbca.cvc.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SignatureException;
import java.util.Locale;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERInteger;
import org.bouncycastle.asn1.DEROutputStream;
import org.bouncycastle.asn1.DERSequence;

public final class BCECUtil {
    private BCECUtil() {
    }

    public static byte[] convertX962SigToCVC(String str, byte[] bArr) throws IOException {
        byte[] bArr2;
        if (!str.toUpperCase(Locale.getDefault()).contains("ECDSA")) {
            return bArr;
        }
        ASN1Sequence readObject = new ASN1InputStream(bArr).readObject();
        BigInteger value = readObject.getObjectAt(0).getValue();
        BigInteger value2 = readObject.getObjectAt(1).getValue();
        byte[] makeUnsigned = makeUnsigned(value);
        byte[] makeUnsigned2 = makeUnsigned(value2);
        if (makeUnsigned.length > makeUnsigned2.length) {
            bArr2 = new byte[(makeUnsigned.length * 2)];
        } else {
            bArr2 = new byte[(makeUnsigned2.length * 2)];
        }
        System.arraycopy(makeUnsigned, 0, bArr2, (bArr2.length / 2) - makeUnsigned.length, makeUnsigned.length);
        System.arraycopy(makeUnsigned2, 0, bArr2, bArr2.length - makeUnsigned2.length, makeUnsigned2.length);
        return bArr2;
    }

    public static byte[] convertCVCSigToX962(String str, byte[] bArr) throws SignatureException {
        if (!str.toUpperCase(Locale.getDefault()).contains("ECDSA")) {
            return bArr;
        }
        byte[] bArr2 = new byte[(bArr.length / 2)];
        byte[] bArr3 = new byte[(bArr.length / 2)];
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        System.arraycopy(bArr, bArr2.length, bArr3, 0, bArr3.length);
        BigInteger bigInteger = new BigInteger(1, bArr2);
        BigInteger bigInteger2 = new BigInteger(1, bArr3);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DEROutputStream dEROutputStream = new DEROutputStream(byteArrayOutputStream);
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new DERInteger(bigInteger));
        aSN1EncodableVector.add(new DERInteger(bigInteger2));
        try {
            dEROutputStream.writeObject(new DERSequence(aSN1EncodableVector));
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new SignatureException(e);
        }
    }

    private static byte[] makeUnsigned(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] != 0) {
            return byteArray;
        }
        byte[] bArr = new byte[(byteArray.length - 1)];
        System.arraycopy(byteArray, 1, bArr, 0, bArr.length);
        return bArr;
    }
}
