package org.spongycastle.x509.extension;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.PublicKey;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.x509.AuthorityKeyIdentifier;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x509.X509Extension;
import org.spongycastle.asn1.x509.X509Name;
import org.spongycastle.jce.PrincipalUtil;

public class AuthorityKeyIdentifierStructure extends AuthorityKeyIdentifier {
    public AuthorityKeyIdentifierStructure(byte[] encodedValue) throws IOException {
        super((ASN1Sequence) X509ExtensionUtil.fromExtensionValue(encodedValue));
    }

    public AuthorityKeyIdentifierStructure(X509Extension extension) {
        super((ASN1Sequence) extension.getParsedValue());
    }

    public AuthorityKeyIdentifierStructure(Extension extension) {
        super((ASN1Sequence) extension.getParsedValue());
    }

    private static ASN1Sequence fromCertificate(X509Certificate certificate) throws CertificateParsingException {
        try {
            if (certificate.getVersion() != 3) {
                return (ASN1Sequence) new AuthorityKeyIdentifier(SubjectPublicKeyInfo.getInstance(certificate.getPublicKey().getEncoded()), new GeneralNames(new GeneralName((X509Name) PrincipalUtil.getIssuerX509Principal(certificate))), certificate.getSerialNumber()).toASN1Primitive();
            }
            GeneralName genName = new GeneralName((X509Name) PrincipalUtil.getIssuerX509Principal(certificate));
            byte[] ext = certificate.getExtensionValue(Extension.subjectKeyIdentifier.getId());
            if (ext != null) {
                return (ASN1Sequence) new AuthorityKeyIdentifier(((ASN1OctetString) X509ExtensionUtil.fromExtensionValue(ext)).getOctets(), new GeneralNames(genName), certificate.getSerialNumber()).toASN1Primitive();
            }
            return (ASN1Sequence) new AuthorityKeyIdentifier(SubjectPublicKeyInfo.getInstance(certificate.getPublicKey().getEncoded()), new GeneralNames(genName), certificate.getSerialNumber()).toASN1Primitive();
        } catch (Exception e) {
            throw new CertificateParsingException("Exception extracting certificate details: " + e.toString());
        }
    }

    private static ASN1Sequence fromKey(PublicKey pubKey) throws InvalidKeyException {
        try {
            return (ASN1Sequence) new AuthorityKeyIdentifier(SubjectPublicKeyInfo.getInstance(pubKey.getEncoded())).toASN1Primitive();
        } catch (Exception e) {
            throw new InvalidKeyException("can't process key: " + e);
        }
    }

    public AuthorityKeyIdentifierStructure(X509Certificate certificate) throws CertificateParsingException {
        super(fromCertificate(certificate));
    }

    public AuthorityKeyIdentifierStructure(PublicKey pubKey) throws InvalidKeyException {
        super(fromKey(pubKey));
    }
}
