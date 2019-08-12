package org.ejbca.cvc;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import org.ejbca.cvc.exception.ConstructionException;
import org.ejbca.cvc.exception.ParseException;

public final class CertificateParser {
    private CertificateParser() {
    }

    public static CVCObject parseCVCObject(byte[] bArr) throws ParseException, ConstructionException {
        return decode(bArr, (CVCTagEnum) null);
    }

    public static CVCertificate parseCertificate(byte[] bArr) throws ParseException, ConstructionException {
        return (CVCertificate) decode(bArr, CVCTagEnum.CV_CERTIFICATE);
    }

    private static CVCObject decode(byte[] bArr, CVCTagEnum cVCTagEnum) throws ParseException, ConstructionException {
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                CVCObject decode = decode(new DataInputStream(byteArrayInputStream), cVCTagEnum);
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (IOException e) {
                        throw new ParseException((Throwable) e);
                    }
                }
                return decode;
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayInputStream = null;
            if (byteArrayInputStream != null) {
                byteArrayInputStream.close();
            }
            throw th;
        }
    }

    private static CVCObject decode(DataInputStream dataInputStream, CVCTagEnum cVCTagEnum) throws IOException, ConstructionException, ParseException {
        CVCTagEnum findTagFromValue = findTagFromValue(decodeTag(dataInputStream));
        if (cVCTagEnum == null || findTagFromValue == cVCTagEnum) {
            int decodeLength = CVCObject.decodeLength(dataInputStream);
            if (findTagFromValue.isSequence()) {
                int available = dataInputStream.available() - decodeLength;
                AbstractSequence createSequence = SequenceFactory.createSequence(findTagFromValue);
                while (dataInputStream.available() > available) {
                    createSequence.addSubfield(decode(dataInputStream, (CVCTagEnum) null));
                }
                if (createSequence instanceof GenericPublicKeyField) {
                    return KeyFactory.createInstance((GenericPublicKeyField) createSequence);
                }
                return createSequence;
            }
            byte[] bArr = new byte[decodeLength];
            dataInputStream.read(bArr, 0, decodeLength);
            return FieldFactory.decodeField(findTagFromValue, bArr);
        }
        throw new ParseException("Expected first tag " + cVCTagEnum + " but found " + findTagFromValue);
    }

    private static CVCTagEnum findTagFromValue(int i) throws ParseException {
        CVCTagEnum cVCTagEnum;
        CVCTagEnum[] values = CVCTagEnum.values();
        int length = values.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                cVCTagEnum = null;
                break;
            }
            cVCTagEnum = values[i2];
            if (cVCTagEnum.getValue() == i) {
                break;
            }
            i2++;
        }
        if (cVCTagEnum != null) {
            return cVCTagEnum;
        }
        throw new ParseException("Unknown CVC tag value " + Integer.toHexString(i));
    }

    private static int decodeTag(DataInputStream dataInputStream) throws IOException {
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        if ((readUnsignedByte & 31) != 31) {
            return readUnsignedByte;
        }
        return (readUnsignedByte << 8) + dataInputStream.readByte();
    }
}
