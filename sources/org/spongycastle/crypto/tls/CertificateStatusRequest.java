package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CertificateStatusRequest {
    protected Object request;
    protected short statusType;

    public CertificateStatusRequest(short statusType2, Object request2) {
        if (!isCorrectType(statusType2, request2)) {
            throw new IllegalArgumentException("'request' is not an instance of the correct type");
        }
        this.statusType = statusType2;
        this.request = request2;
    }

    public short getStatusType() {
        return this.statusType;
    }

    public Object getRequest() {
        return this.request;
    }

    public OCSPStatusRequest getOCSPStatusRequest() {
        if (isCorrectType(1, this.request)) {
            return (OCSPStatusRequest) this.request;
        }
        throw new IllegalStateException("'request' is not an OCSPStatusRequest");
    }

    public void encode(OutputStream output) throws IOException {
        TlsUtils.writeUint8(this.statusType, output);
        switch (this.statusType) {
            case 1:
                ((OCSPStatusRequest) this.request).encode(output);
                return;
            default:
                throw new TlsFatalAlert(80);
        }
    }

    public static CertificateStatusRequest parse(InputStream input) throws IOException {
        short status_type = TlsUtils.readUint8(input);
        switch (status_type) {
            case 1:
                return new CertificateStatusRequest(status_type, OCSPStatusRequest.parse(input));
            default:
                throw new TlsFatalAlert(50);
        }
    }

    protected static boolean isCorrectType(short statusType2, Object request2) {
        switch (statusType2) {
            case 1:
                return request2 instanceof OCSPStatusRequest;
            default:
                throw new IllegalArgumentException("'statusType' is an unsupported value");
        }
    }
}
