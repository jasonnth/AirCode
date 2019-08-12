package org.spongycastle.jce;

import java.io.IOException;
import java.security.cert.CRLException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.x509.TBSCertList;
import org.spongycastle.asn1.x509.TBSCertificateStructure;
import org.spongycastle.asn1.x509.X509Name;

public class PrincipalUtil {
    public static X509Principal getIssuerX509Principal(X509Certificate cert) throws CertificateEncodingException {
        try {
            return new X509Principal(X509Name.getInstance(TBSCertificateStructure.getInstance(ASN1Primitive.fromByteArray(cert.getTBSCertificate())).getIssuer()));
        } catch (IOException e) {
            throw new CertificateEncodingException(e.toString());
        }
    }

    public static X509Principal getSubjectX509Principal(X509Certificate cert) throws CertificateEncodingException {
        try {
            return new X509Principal(X509Name.getInstance(TBSCertificateStructure.getInstance(ASN1Primitive.fromByteArray(cert.getTBSCertificate())).getSubject()));
        } catch (IOException e) {
            throw new CertificateEncodingException(e.toString());
        }
    }

    public static X509Principal getIssuerX509Principal(X509CRL crl) throws CRLException {
        try {
            return new X509Principal(X509Name.getInstance(TBSCertList.getInstance(ASN1Primitive.fromByteArray(crl.getTBSCertList())).getIssuer()));
        } catch (IOException e) {
            throw new CRLException(e.toString());
        }
    }
}
