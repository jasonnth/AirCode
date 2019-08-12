package org.spongycastle.jcajce.provider.asymmetric.x509;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactorySpi;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.SignedData;
import org.spongycastle.asn1.x509.CertificateList;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;

public class CertificateFactory extends CertificateFactorySpi {
    private static final PEMUtil PEM_CERT_PARSER = new PEMUtil("CERTIFICATE");
    private static final PEMUtil PEM_CRL_PARSER = new PEMUtil("CRL");
    private final JcaJceHelper bcHelper = new BCJcaJceHelper();
    private InputStream currentCrlStream = null;
    private InputStream currentStream = null;
    private ASN1Set sCrlData = null;
    private int sCrlDataObjectCount = 0;
    private ASN1Set sData = null;
    private int sDataObjectCount = 0;

    private class ExCertificateException extends CertificateException {
        private Throwable cause;

        public ExCertificateException(Throwable cause2) {
            this.cause = cause2;
        }

        public ExCertificateException(String msg, Throwable cause2) {
            super(msg);
            this.cause = cause2;
        }

        public Throwable getCause() {
            return this.cause;
        }
    }

    private Certificate readDERCertificate(ASN1InputStream dIn) throws IOException, CertificateParsingException {
        ASN1Sequence seq = (ASN1Sequence) dIn.readObject();
        if (seq.size() <= 1 || !(seq.getObjectAt(0) instanceof ASN1ObjectIdentifier) || !seq.getObjectAt(0).equals(PKCSObjectIdentifiers.signedData)) {
            return new X509CertificateObject(this.bcHelper, org.spongycastle.asn1.x509.Certificate.getInstance(seq));
        }
        this.sData = SignedData.getInstance(ASN1Sequence.getInstance((ASN1TaggedObject) seq.getObjectAt(1), true)).getCertificates();
        return getCertificate();
    }

    private Certificate getCertificate() throws CertificateParsingException {
        if (this.sData != null) {
            while (this.sDataObjectCount < this.sData.size()) {
                ASN1Set aSN1Set = this.sData;
                int i = this.sDataObjectCount;
                this.sDataObjectCount = i + 1;
                ASN1Encodable obj = aSN1Set.getObjectAt(i);
                if (obj instanceof ASN1Sequence) {
                    return new X509CertificateObject(this.bcHelper, org.spongycastle.asn1.x509.Certificate.getInstance(obj));
                }
            }
        }
        return null;
    }

    private Certificate readPEMCertificate(InputStream in) throws IOException, CertificateParsingException {
        ASN1Sequence seq = PEM_CERT_PARSER.readPEMObject(in);
        if (seq != null) {
            return new X509CertificateObject(this.bcHelper, org.spongycastle.asn1.x509.Certificate.getInstance(seq));
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public CRL createCRL(CertificateList c) throws CRLException {
        return new X509CRLObject(this.bcHelper, c);
    }

    private CRL readPEMCRL(InputStream in) throws IOException, CRLException {
        ASN1Sequence seq = PEM_CRL_PARSER.readPEMObject(in);
        if (seq != null) {
            return createCRL(CertificateList.getInstance(seq));
        }
        return null;
    }

    private CRL readDERCRL(ASN1InputStream aIn) throws IOException, CRLException {
        ASN1Sequence seq = (ASN1Sequence) aIn.readObject();
        if (seq.size() <= 1 || !(seq.getObjectAt(0) instanceof ASN1ObjectIdentifier) || !seq.getObjectAt(0).equals(PKCSObjectIdentifiers.signedData)) {
            return createCRL(CertificateList.getInstance(seq));
        }
        this.sCrlData = SignedData.getInstance(ASN1Sequence.getInstance((ASN1TaggedObject) seq.getObjectAt(1), true)).getCRLs();
        return getCRL();
    }

    private CRL getCRL() throws CRLException {
        if (this.sCrlData == null || this.sCrlDataObjectCount >= this.sCrlData.size()) {
            return null;
        }
        ASN1Set aSN1Set = this.sCrlData;
        int i = this.sCrlDataObjectCount;
        this.sCrlDataObjectCount = i + 1;
        return createCRL(CertificateList.getInstance(aSN1Set.getObjectAt(i)));
    }

    public Certificate engineGenerateCertificate(InputStream in) throws CertificateException {
        if (this.currentStream == null) {
            this.currentStream = in;
            this.sData = null;
            this.sDataObjectCount = 0;
        } else if (this.currentStream != in) {
            this.currentStream = in;
            this.sData = null;
            this.sDataObjectCount = 0;
        }
        try {
            if (this.sData == null) {
                PushbackInputStream pis = new PushbackInputStream(in);
                int tag = pis.read();
                if (tag == -1) {
                    return null;
                }
                pis.unread(tag);
                if (tag != 48) {
                    return readPEMCertificate(pis);
                }
                return readDERCertificate(new ASN1InputStream((InputStream) pis));
            } else if (this.sDataObjectCount != this.sData.size()) {
                return getCertificate();
            } else {
                this.sData = null;
                this.sDataObjectCount = 0;
                return null;
            }
        } catch (Exception e) {
            throw new ExCertificateException(e);
        }
    }

    public Collection engineGenerateCertificates(InputStream inStream) throws CertificateException {
        List certs = new ArrayList();
        while (true) {
            Certificate cert = engineGenerateCertificate(inStream);
            if (cert == null) {
                return certs;
            }
            certs.add(cert);
        }
    }

    public CRL engineGenerateCRL(InputStream inStream) throws CRLException {
        if (this.currentCrlStream == null) {
            this.currentCrlStream = inStream;
            this.sCrlData = null;
            this.sCrlDataObjectCount = 0;
        } else if (this.currentCrlStream != inStream) {
            this.currentCrlStream = inStream;
            this.sCrlData = null;
            this.sCrlDataObjectCount = 0;
        }
        try {
            if (this.sCrlData == null) {
                PushbackInputStream pis = new PushbackInputStream(inStream);
                int tag = pis.read();
                if (tag == -1) {
                    return null;
                }
                pis.unread(tag);
                if (tag != 48) {
                    return readPEMCRL(pis);
                }
                return readDERCRL(new ASN1InputStream((InputStream) pis, true));
            } else if (this.sCrlDataObjectCount != this.sCrlData.size()) {
                return getCRL();
            } else {
                this.sCrlData = null;
                this.sCrlDataObjectCount = 0;
                return null;
            }
        } catch (CRLException e) {
            throw e;
        } catch (Exception e2) {
            throw new CRLException(e2.toString());
        }
    }

    public Collection engineGenerateCRLs(InputStream inStream) throws CRLException {
        List crls = new ArrayList();
        while (true) {
            CRL crl = engineGenerateCRL(inStream);
            if (crl == null) {
                return crls;
            }
            crls.add(crl);
        }
    }

    public Iterator engineGetCertPathEncodings() {
        return PKIXCertPath.certPathEncodings.iterator();
    }

    public CertPath engineGenerateCertPath(InputStream inStream) throws CertificateException {
        return engineGenerateCertPath(inStream, "PkiPath");
    }

    public CertPath engineGenerateCertPath(InputStream inStream, String encoding) throws CertificateException {
        return new PKIXCertPath(inStream, encoding);
    }

    public CertPath engineGenerateCertPath(List certificates) throws CertificateException {
        for (Object obj : certificates) {
            if (obj != null && !(obj instanceof X509Certificate)) {
                throw new CertificateException("list contains non X509Certificate object while creating CertPath\n" + obj.toString());
            }
        }
        return new PKIXCertPath(certificates);
    }
}
