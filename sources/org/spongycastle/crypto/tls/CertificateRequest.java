package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.x500.X500Name;

public class CertificateRequest {
    protected Vector certificateAuthorities;
    protected short[] certificateTypes;
    protected Vector supportedSignatureAlgorithms;

    public CertificateRequest(short[] certificateTypes2, Vector supportedSignatureAlgorithms2, Vector certificateAuthorities2) {
        this.certificateTypes = certificateTypes2;
        this.supportedSignatureAlgorithms = supportedSignatureAlgorithms2;
        this.certificateAuthorities = certificateAuthorities2;
    }

    public short[] getCertificateTypes() {
        return this.certificateTypes;
    }

    public Vector getSupportedSignatureAlgorithms() {
        return this.supportedSignatureAlgorithms;
    }

    public Vector getCertificateAuthorities() {
        return this.certificateAuthorities;
    }

    public void encode(OutputStream output) throws IOException {
        if (this.certificateTypes == null || this.certificateTypes.length == 0) {
            TlsUtils.writeUint8(0, output);
        } else {
            TlsUtils.writeUint8ArrayWithUint8Length(this.certificateTypes, output);
        }
        if (this.supportedSignatureAlgorithms != null) {
            TlsUtils.encodeSupportedSignatureAlgorithms(this.supportedSignatureAlgorithms, false, output);
        }
        if (this.certificateAuthorities == null || this.certificateAuthorities.isEmpty()) {
            TlsUtils.writeUint16(0, output);
            return;
        }
        Vector derEncodings = new Vector(this.certificateAuthorities.size());
        int totalLength = 0;
        for (int i = 0; i < this.certificateAuthorities.size(); i++) {
            byte[] derEncoding = ((X500Name) this.certificateAuthorities.elementAt(i)).getEncoded(ASN1Encoding.DER);
            derEncodings.addElement(derEncoding);
            totalLength += derEncoding.length + 2;
        }
        TlsUtils.checkUint16(totalLength);
        TlsUtils.writeUint16(totalLength, output);
        for (int i2 = 0; i2 < derEncodings.size(); i2++) {
            TlsUtils.writeOpaque16((byte[]) derEncodings.elementAt(i2), output);
        }
    }

    public static CertificateRequest parse(TlsContext context, InputStream input) throws IOException {
        short numTypes = TlsUtils.readUint8(input);
        short[] certificateTypes2 = new short[numTypes];
        for (int i = 0; i < numTypes; i++) {
            certificateTypes2[i] = TlsUtils.readUint8(input);
        }
        Vector supportedSignatureAlgorithms2 = null;
        if (TlsUtils.isTLSv12(context)) {
            supportedSignatureAlgorithms2 = TlsUtils.parseSupportedSignatureAlgorithms(false, input);
        }
        Vector certificateAuthorities2 = new Vector();
        ByteArrayInputStream bis = new ByteArrayInputStream(TlsUtils.readOpaque16(input));
        while (bis.available() > 0) {
            certificateAuthorities2.addElement(X500Name.getInstance(TlsUtils.readDERObject(TlsUtils.readOpaque16(bis))));
        }
        return new CertificateRequest(certificateTypes2, supportedSignatureAlgorithms2, certificateAuthorities2);
    }
}
