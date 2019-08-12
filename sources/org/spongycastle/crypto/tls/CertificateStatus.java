package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ocsp.OCSPResponse;

public class CertificateStatus {
    protected Object response;
    protected short statusType;

    public CertificateStatus(short statusType2, Object response2) {
        if (!isCorrectType(statusType2, response2)) {
            throw new IllegalArgumentException("'response' is not an instance of the correct type");
        }
        this.statusType = statusType2;
        this.response = response2;
    }

    public short getStatusType() {
        return this.statusType;
    }

    public Object getResponse() {
        return this.response;
    }

    public OCSPResponse getOCSPResponse() {
        if (isCorrectType(1, this.response)) {
            return (OCSPResponse) this.response;
        }
        throw new IllegalStateException("'response' is not an OCSPResponse");
    }

    public void encode(OutputStream output) throws IOException {
        TlsUtils.writeUint8(this.statusType, output);
        switch (this.statusType) {
            case 1:
                TlsUtils.writeOpaque24(((OCSPResponse) this.response).getEncoded(ASN1Encoding.DER), output);
                return;
            default:
                throw new TlsFatalAlert(80);
        }
    }

    public static CertificateStatus parse(InputStream input) throws IOException {
        short status_type = TlsUtils.readUint8(input);
        switch (status_type) {
            case 1:
                return new CertificateStatus(status_type, OCSPResponse.getInstance(TlsUtils.readDERObject(TlsUtils.readOpaque24(input))));
            default:
                throw new TlsFatalAlert(50);
        }
    }

    protected static boolean isCorrectType(short statusType2, Object response2) {
        switch (statusType2) {
            case 1:
                return response2 instanceof OCSPResponse;
            default:
                throw new IllegalArgumentException("'statusType' is an unsupported value");
        }
    }
}
