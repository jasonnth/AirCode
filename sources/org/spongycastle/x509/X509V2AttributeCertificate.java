package org.spongycastle.x509;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.x509.AttributeCertificate;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.util.Arrays;

public class X509V2AttributeCertificate implements X509AttributeCertificate {
    private AttributeCertificate cert;
    private Date notAfter;
    private Date notBefore;

    private static AttributeCertificate getObject(InputStream in) throws IOException {
        try {
            return AttributeCertificate.getInstance(new ASN1InputStream(in).readObject());
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            throw new IOException("exception decoding certificate structure: " + e2.toString());
        }
    }

    public X509V2AttributeCertificate(InputStream encIn) throws IOException {
        this(getObject(encIn));
    }

    public X509V2AttributeCertificate(byte[] encoded) throws IOException {
        this((InputStream) new ByteArrayInputStream(encoded));
    }

    X509V2AttributeCertificate(AttributeCertificate cert2) throws IOException {
        this.cert = cert2;
        try {
            this.notAfter = cert2.getAcinfo().getAttrCertValidityPeriod().getNotAfterTime().getDate();
            this.notBefore = cert2.getAcinfo().getAttrCertValidityPeriod().getNotBeforeTime().getDate();
        } catch (ParseException e) {
            throw new IOException("invalid data structure in certificate!");
        }
    }

    public int getVersion() {
        return this.cert.getAcinfo().getVersion().getValue().intValue() + 1;
    }

    public BigInteger getSerialNumber() {
        return this.cert.getAcinfo().getSerialNumber().getValue();
    }

    public AttributeCertificateHolder getHolder() {
        return new AttributeCertificateHolder((ASN1Sequence) this.cert.getAcinfo().getHolder().toASN1Primitive());
    }

    public AttributeCertificateIssuer getIssuer() {
        return new AttributeCertificateIssuer(this.cert.getAcinfo().getIssuer());
    }

    public Date getNotBefore() {
        return this.notBefore;
    }

    public Date getNotAfter() {
        return this.notAfter;
    }

    public boolean[] getIssuerUniqueID() {
        DERBitString id = this.cert.getAcinfo().getIssuerUniqueID();
        if (id == null) {
            return null;
        }
        byte[] bytes = id.getBytes();
        boolean[] boolId = new boolean[((bytes.length * 8) - id.getPadBits())];
        for (int i = 0; i != boolId.length; i++) {
            boolId[i] = (bytes[i / 8] & (128 >>> (i % 8))) != 0;
        }
        return boolId;
    }

    public void checkValidity() throws CertificateExpiredException, CertificateNotYetValidException {
        checkValidity(new Date());
    }

    public void checkValidity(Date date) throws CertificateExpiredException, CertificateNotYetValidException {
        if (date.after(getNotAfter())) {
            throw new CertificateExpiredException("certificate expired on " + getNotAfter());
        } else if (date.before(getNotBefore())) {
            throw new CertificateNotYetValidException("certificate not valid till " + getNotBefore());
        }
    }

    public byte[] getSignature() {
        return this.cert.getSignatureValue().getOctets();
    }

    public final void verify(PublicKey key, String provider) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        if (!this.cert.getSignatureAlgorithm().equals(this.cert.getAcinfo().getSignature())) {
            throw new CertificateException("Signature algorithm in certificate info not same as outer certificate");
        }
        Signature signature = Signature.getInstance(this.cert.getSignatureAlgorithm().getAlgorithm().getId(), provider);
        signature.initVerify(key);
        try {
            signature.update(this.cert.getAcinfo().getEncoded());
            if (!signature.verify(getSignature())) {
                throw new InvalidKeyException("Public key presented not for certificate signature");
            }
        } catch (IOException e) {
            throw new SignatureException("Exception encoding certificate info object");
        }
    }

    public byte[] getEncoded() throws IOException {
        return this.cert.getEncoded();
    }

    public byte[] getExtensionValue(String oid) {
        Extensions extensions = this.cert.getAcinfo().getExtensions();
        if (extensions != null) {
            Extension ext = extensions.getExtension(new ASN1ObjectIdentifier(oid));
            if (ext != null) {
                try {
                    return ext.getExtnValue().getEncoded(ASN1Encoding.DER);
                } catch (Exception e) {
                    throw new RuntimeException("error encoding " + e.toString());
                }
            }
        }
        return null;
    }

    private Set getExtensionOIDs(boolean critical) {
        Extensions extensions = this.cert.getAcinfo().getExtensions();
        if (extensions == null) {
            return null;
        }
        Set set = new HashSet();
        Enumeration e = extensions.oids();
        while (e.hasMoreElements()) {
            ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier) e.nextElement();
            if (extensions.getExtension(oid).isCritical() == critical) {
                set.add(oid.getId());
            }
        }
        return set;
    }

    public Set getNonCriticalExtensionOIDs() {
        return getExtensionOIDs(false);
    }

    public Set getCriticalExtensionOIDs() {
        return getExtensionOIDs(true);
    }

    public boolean hasUnsupportedCriticalExtension() {
        Set extensions = getCriticalExtensionOIDs();
        return extensions != null && !extensions.isEmpty();
    }

    public X509Attribute[] getAttributes() {
        ASN1Sequence seq = this.cert.getAcinfo().getAttributes();
        X509Attribute[] attrs = new X509Attribute[seq.size()];
        for (int i = 0; i != seq.size(); i++) {
            attrs[i] = new X509Attribute(seq.getObjectAt(i));
        }
        return attrs;
    }

    public X509Attribute[] getAttributes(String oid) {
        ASN1Sequence seq = this.cert.getAcinfo().getAttributes();
        List list = new ArrayList();
        for (int i = 0; i != seq.size(); i++) {
            X509Attribute attr = new X509Attribute(seq.getObjectAt(i));
            if (attr.getOID().equals(oid)) {
                list.add(attr);
            }
        }
        if (list.size() == 0) {
            return null;
        }
        return (X509Attribute[]) list.toArray(new X509Attribute[list.size()]);
    }

    public boolean equals(Object o) {
        boolean z = false;
        if (o == this) {
            return true;
        }
        if (!(o instanceof X509AttributeCertificate)) {
            return z;
        }
        try {
            return Arrays.areEqual(getEncoded(), ((X509AttributeCertificate) o).getEncoded());
        } catch (IOException e) {
            return z;
        }
    }

    public int hashCode() {
        try {
            return Arrays.hashCode(getEncoded());
        } catch (IOException e) {
            return 0;
        }
    }
}
