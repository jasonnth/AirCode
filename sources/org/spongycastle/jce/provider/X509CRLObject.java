package org.spongycastle.jce.provider;

import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CRLException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.util.ASN1Dump;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.CRLDistPoint;
import org.spongycastle.asn1.x509.CRLNumber;
import org.spongycastle.asn1.x509.CertificateList;
import org.spongycastle.asn1.x509.Extension;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.GeneralNames;
import org.spongycastle.asn1.x509.IssuingDistributionPoint;
import org.spongycastle.asn1.x509.TBSCertList.CRLEntry;
import org.spongycastle.jce.X509Principal;
import org.spongycastle.util.Strings;
import org.spongycastle.util.encoders.Hex;

public class X509CRLObject extends X509CRL {

    /* renamed from: c */
    private CertificateList f6936c;
    private int hashCodeValue;
    private boolean isHashCodeSet = false;
    private boolean isIndirect;
    private String sigAlgName;
    private byte[] sigAlgParams;

    public static boolean isIndirectCRL(X509CRL crl) throws CRLException {
        try {
            byte[] idp = crl.getExtensionValue(Extension.issuingDistributionPoint.getId());
            return idp != null && IssuingDistributionPoint.getInstance(ASN1OctetString.getInstance(idp).getOctets()).isIndirectCRL();
        } catch (Exception e) {
            throw new ExtCRLException("Exception reading IssuingDistributionPoint", e);
        }
    }

    public X509CRLObject(CertificateList c) throws CRLException {
        this.f6936c = c;
        try {
            this.sigAlgName = X509SignatureUtil.getSignatureName(c.getSignatureAlgorithm());
            if (c.getSignatureAlgorithm().getParameters() != null) {
                this.sigAlgParams = c.getSignatureAlgorithm().getParameters().toASN1Primitive().getEncoded(ASN1Encoding.DER);
            } else {
                this.sigAlgParams = null;
            }
            this.isIndirect = isIndirectCRL(this);
        } catch (Exception e) {
            throw new CRLException("CRL contents invalid: " + e);
        }
    }

    public boolean hasUnsupportedCriticalExtension() {
        Set extns = getCriticalExtensionOIDs();
        if (extns == null) {
            return false;
        }
        extns.remove(RFC3280CertPathUtilities.ISSUING_DISTRIBUTION_POINT);
        extns.remove(RFC3280CertPathUtilities.DELTA_CRL_INDICATOR);
        if (!extns.isEmpty()) {
            return true;
        }
        return false;
    }

    private Set getExtensionOIDs(boolean critical) {
        if (getVersion() == 2) {
            Extensions extensions = this.f6936c.getTBSCertList().getExtensions();
            if (extensions != null) {
                Set set = new HashSet();
                Enumeration e = extensions.oids();
                while (e.hasMoreElements()) {
                    ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier) e.nextElement();
                    if (critical == extensions.getExtension(oid).isCritical()) {
                        set.add(oid.getId());
                    }
                }
                return set;
            }
        }
        return null;
    }

    public Set getCriticalExtensionOIDs() {
        return getExtensionOIDs(true);
    }

    public Set getNonCriticalExtensionOIDs() {
        return getExtensionOIDs(false);
    }

    public byte[] getExtensionValue(String oid) {
        Extensions exts = this.f6936c.getTBSCertList().getExtensions();
        if (exts != null) {
            Extension ext = exts.getExtension(new ASN1ObjectIdentifier(oid));
            if (ext != null) {
                try {
                    return ext.getExtnValue().getEncoded();
                } catch (Exception e) {
                    throw new IllegalStateException("error parsing " + e.toString());
                }
            }
        }
        return null;
    }

    public byte[] getEncoded() throws CRLException {
        try {
            return this.f6936c.getEncoded(ASN1Encoding.DER);
        } catch (IOException e) {
            throw new CRLException(e.toString());
        }
    }

    public void verify(PublicKey key) throws CRLException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        Signature sig;
        try {
            sig = Signature.getInstance(getSigAlgName(), BouncyCastleProvider.PROVIDER_NAME);
        } catch (Exception e) {
            sig = Signature.getInstance(getSigAlgName());
        }
        doVerify(key, sig);
    }

    public void verify(PublicKey key, String sigProvider) throws CRLException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        Signature sig;
        if (sigProvider != null) {
            sig = Signature.getInstance(getSigAlgName(), sigProvider);
        } else {
            sig = Signature.getInstance(getSigAlgName());
        }
        doVerify(key, sig);
    }

    public void verify(PublicKey key, Provider sigProvider) throws CRLException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sig;
        if (sigProvider != null) {
            sig = Signature.getInstance(getSigAlgName(), sigProvider);
        } else {
            sig = Signature.getInstance(getSigAlgName());
        }
        doVerify(key, sig);
    }

    private void doVerify(PublicKey key, Signature sig) throws CRLException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        if (!this.f6936c.getSignatureAlgorithm().equals(this.f6936c.getTBSCertList().getSignature())) {
            throw new CRLException("Signature algorithm on CertificateList does not match TBSCertList.");
        }
        sig.initVerify(key);
        sig.update(getTBSCertList());
        if (!sig.verify(getSignature())) {
            throw new SignatureException("CRL does not verify with supplied public key.");
        }
    }

    public int getVersion() {
        return this.f6936c.getVersionNumber();
    }

    public Principal getIssuerDN() {
        return new X509Principal(X500Name.getInstance(this.f6936c.getIssuer().toASN1Primitive()));
    }

    public X500Principal getIssuerX500Principal() {
        try {
            return new X500Principal(this.f6936c.getIssuer().getEncoded());
        } catch (IOException e) {
            throw new IllegalStateException("can't encode issuer DN");
        }
    }

    public Date getThisUpdate() {
        return this.f6936c.getThisUpdate().getDate();
    }

    public Date getNextUpdate() {
        if (this.f6936c.getNextUpdate() != null) {
            return this.f6936c.getNextUpdate().getDate();
        }
        return null;
    }

    private Set loadCRLEntries() {
        Set entrySet = new HashSet();
        Enumeration certs = this.f6936c.getRevokedCertificateEnumeration();
        X500Name previousCertificateIssuer = null;
        while (certs.hasMoreElements()) {
            CRLEntry entry = (CRLEntry) certs.nextElement();
            entrySet.add(new X509CRLEntryObject(entry, this.isIndirect, previousCertificateIssuer));
            if (this.isIndirect && entry.hasExtensions()) {
                Extension currentCaName = entry.getExtensions().getExtension(Extension.certificateIssuer);
                if (currentCaName != null) {
                    previousCertificateIssuer = X500Name.getInstance(GeneralNames.getInstance(currentCaName.getParsedValue()).getNames()[0].getName());
                }
            }
        }
        return entrySet;
    }

    public X509CRLEntry getRevokedCertificate(BigInteger serialNumber) {
        Enumeration certs = this.f6936c.getRevokedCertificateEnumeration();
        X500Name previousCertificateIssuer = null;
        while (certs.hasMoreElements()) {
            CRLEntry entry = (CRLEntry) certs.nextElement();
            if (serialNumber.equals(entry.getUserCertificate().getValue())) {
                return new X509CRLEntryObject(entry, this.isIndirect, previousCertificateIssuer);
            }
            if (this.isIndirect && entry.hasExtensions()) {
                Extension currentCaName = entry.getExtensions().getExtension(Extension.certificateIssuer);
                if (currentCaName != null) {
                    previousCertificateIssuer = X500Name.getInstance(GeneralNames.getInstance(currentCaName.getParsedValue()).getNames()[0].getName());
                }
            }
        }
        return null;
    }

    public Set getRevokedCertificates() {
        Set entrySet = loadCRLEntries();
        if (!entrySet.isEmpty()) {
            return Collections.unmodifiableSet(entrySet);
        }
        return null;
    }

    public byte[] getTBSCertList() throws CRLException {
        try {
            return this.f6936c.getTBSCertList().getEncoded(ASN1Encoding.DER);
        } catch (IOException e) {
            throw new CRLException(e.toString());
        }
    }

    public byte[] getSignature() {
        return this.f6936c.getSignature().getOctets();
    }

    public String getSigAlgName() {
        return this.sigAlgName;
    }

    public String getSigAlgOID() {
        return this.f6936c.getSignatureAlgorithm().getAlgorithm().getId();
    }

    public byte[] getSigAlgParams() {
        if (this.sigAlgParams == null) {
            return null;
        }
        byte[] tmp = new byte[this.sigAlgParams.length];
        System.arraycopy(this.sigAlgParams, 0, tmp, 0, tmp.length);
        return tmp;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer();
        String nl = Strings.lineSeparator();
        buf.append("              Version: ").append(getVersion()).append(nl);
        buf.append("             IssuerDN: ").append(getIssuerDN()).append(nl);
        buf.append("          This update: ").append(getThisUpdate()).append(nl);
        buf.append("          Next update: ").append(getNextUpdate()).append(nl);
        buf.append("  Signature Algorithm: ").append(getSigAlgName()).append(nl);
        byte[] sig = getSignature();
        buf.append("            Signature: ").append(new String(Hex.encode(sig, 0, 20))).append(nl);
        for (int i = 20; i < sig.length; i += 20) {
            if (i < sig.length - 20) {
                buf.append("                       ").append(new String(Hex.encode(sig, i, 20))).append(nl);
            } else {
                buf.append("                       ").append(new String(Hex.encode(sig, i, sig.length - i))).append(nl);
            }
        }
        Extensions extensions = this.f6936c.getTBSCertList().getExtensions();
        if (extensions != null) {
            Enumeration e = extensions.oids();
            if (e.hasMoreElements()) {
                buf.append("           Extensions: ").append(nl);
            }
            while (e.hasMoreElements()) {
                ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier) e.nextElement();
                Extension ext = extensions.getExtension(oid);
                if (ext.getExtnValue() != null) {
                    ASN1InputStream dIn = new ASN1InputStream(ext.getExtnValue().getOctets());
                    buf.append("                       critical(").append(ext.isCritical()).append(") ");
                    try {
                        if (oid.equals(Extension.cRLNumber)) {
                            buf.append(new CRLNumber(ASN1Integer.getInstance(dIn.readObject()).getPositiveValue())).append(nl);
                        } else if (oid.equals(Extension.deltaCRLIndicator)) {
                            buf.append("Base CRL: " + new CRLNumber(ASN1Integer.getInstance(dIn.readObject()).getPositiveValue())).append(nl);
                        } else if (oid.equals(Extension.issuingDistributionPoint)) {
                            buf.append(IssuingDistributionPoint.getInstance(dIn.readObject())).append(nl);
                        } else if (oid.equals(Extension.cRLDistributionPoints)) {
                            buf.append(CRLDistPoint.getInstance(dIn.readObject())).append(nl);
                        } else if (oid.equals(Extension.freshestCRL)) {
                            buf.append(CRLDistPoint.getInstance(dIn.readObject())).append(nl);
                        } else {
                            buf.append(oid.getId());
                            buf.append(" value = ").append(ASN1Dump.dumpAsString(dIn.readObject())).append(nl);
                        }
                    } catch (Exception e2) {
                        buf.append(oid.getId());
                        buf.append(" value = ").append("*****").append(nl);
                    }
                } else {
                    buf.append(nl);
                }
            }
        }
        Set<Object> set = getRevokedCertificates();
        if (set != null) {
            for (Object append : set) {
                buf.append(append);
                buf.append(nl);
            }
        }
        return buf.toString();
    }

    public boolean isRevoked(Certificate cert) {
        X500Name issuer;
        if (!cert.getType().equals("X.509")) {
            throw new RuntimeException("X.509 CRL used with non X.509 Cert");
        }
        Enumeration certs = this.f6936c.getRevokedCertificateEnumeration();
        X500Name caName = this.f6936c.getIssuer();
        if (certs != null) {
            BigInteger serial = ((X509Certificate) cert).getSerialNumber();
            while (certs.hasMoreElements()) {
                CRLEntry entry = CRLEntry.getInstance(certs.nextElement());
                if (this.isIndirect && entry.hasExtensions()) {
                    Extension currentCaName = entry.getExtensions().getExtension(Extension.certificateIssuer);
                    if (currentCaName != null) {
                        caName = X500Name.getInstance(GeneralNames.getInstance(currentCaName.getParsedValue()).getNames()[0].getName());
                    }
                }
                if (entry.getUserCertificate().getValue().equals(serial)) {
                    if (cert instanceof X509Certificate) {
                        issuer = X500Name.getInstance(((X509Certificate) cert).getIssuerX500Principal().getEncoded());
                    } else {
                        try {
                            issuer = org.spongycastle.asn1.x509.Certificate.getInstance(cert.getEncoded()).getIssuer();
                        } catch (CertificateEncodingException e) {
                            throw new RuntimeException("Cannot process certificate");
                        }
                    }
                    if (!caName.equals(issuer)) {
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof X509CRL)) {
            return false;
        }
        if (!(other instanceof X509CRLObject)) {
            return super.equals(other);
        }
        X509CRLObject crlObject = (X509CRLObject) other;
        if (!this.isHashCodeSet || !crlObject.isHashCodeSet || crlObject.hashCodeValue == this.hashCodeValue) {
            return this.f6936c.equals(crlObject.f6936c);
        }
        return false;
    }

    public int hashCode() {
        if (!this.isHashCodeSet) {
            this.isHashCodeSet = true;
            this.hashCodeValue = super.hashCode();
        }
        return this.hashCodeValue;
    }
}
