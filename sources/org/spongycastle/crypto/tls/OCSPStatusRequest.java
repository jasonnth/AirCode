package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ocsp.ResponderID;
import org.spongycastle.asn1.x509.Extensions;

public class OCSPStatusRequest {
    protected Extensions requestExtensions;
    protected Vector responderIDList;

    public OCSPStatusRequest(Vector responderIDList2, Extensions requestExtensions2) {
        this.responderIDList = responderIDList2;
        this.requestExtensions = requestExtensions2;
    }

    public Vector getResponderIDList() {
        return this.responderIDList;
    }

    public Extensions getRequestExtensions() {
        return this.requestExtensions;
    }

    public void encode(OutputStream output) throws IOException {
        if (this.responderIDList == null || this.responderIDList.isEmpty()) {
            TlsUtils.writeUint16(0, output);
        } else {
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            for (int i = 0; i < this.responderIDList.size(); i++) {
                TlsUtils.writeOpaque16(((ResponderID) this.responderIDList.elementAt(i)).getEncoded(ASN1Encoding.DER), buf);
            }
            TlsUtils.checkUint16(buf.size());
            TlsUtils.writeUint16(buf.size(), output);
            buf.writeTo(output);
        }
        if (this.requestExtensions == null) {
            TlsUtils.writeUint16(0, output);
            return;
        }
        byte[] derEncoding = this.requestExtensions.getEncoded(ASN1Encoding.DER);
        TlsUtils.checkUint16(derEncoding.length);
        TlsUtils.writeUint16(derEncoding.length, output);
        output.write(derEncoding);
    }

    public static OCSPStatusRequest parse(InputStream input) throws IOException {
        Vector responderIDList2 = new Vector();
        int length = TlsUtils.readUint16(input);
        if (length > 0) {
            ByteArrayInputStream buf = new ByteArrayInputStream(TlsUtils.readFully(length, input));
            do {
                responderIDList2.addElement(ResponderID.getInstance(TlsUtils.readDERObject(TlsUtils.readOpaque16(buf))));
            } while (buf.available() > 0);
        }
        Extensions requestExtensions2 = null;
        int length2 = TlsUtils.readUint16(input);
        if (length2 > 0) {
            requestExtensions2 = Extensions.getInstance(TlsUtils.readDERObject(TlsUtils.readFully(length2, input)));
        }
        return new OCSPStatusRequest(responderIDList2, requestExtensions2);
    }
}
