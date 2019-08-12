package org.spongycastle.jcajce.provider.asymmetric.x509;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.security.NoSuchProviderException;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.pkcs.ContentInfo;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.SignedData;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.util.p333io.pem.PemObject;
import org.spongycastle.util.p333io.pem.PemWriter;

public class PKIXCertPath extends CertPath {
    static final List certPathEncodings;
    private List certificates;
    private final JcaJceHelper helper = new BCJcaJceHelper();

    static {
        List encodings = new ArrayList();
        encodings.add("PkiPath");
        encodings.add("PEM");
        encodings.add("PKCS7");
        certPathEncodings = Collections.unmodifiableList(encodings);
    }

    private List sortCerts(List certs) {
        if (certs.size() < 2) {
            return certs;
        }
        X500Principal issuer = ((X509Certificate) certs.get(0)).getIssuerX500Principal();
        boolean okay = true;
        int i = 1;
        while (true) {
            if (i != certs.size()) {
                if (!issuer.equals(((X509Certificate) certs.get(i)).getSubjectX500Principal())) {
                    okay = false;
                    break;
                }
                issuer = ((X509Certificate) certs.get(i)).getIssuerX500Principal();
                i++;
            } else {
                break;
            }
        }
        if (okay) {
            return certs;
        }
        List arrayList = new ArrayList(certs.size());
        List arrayList2 = new ArrayList(certs);
        for (int i2 = 0; i2 < certs.size(); i2++) {
            X509Certificate cert = (X509Certificate) certs.get(i2);
            boolean found = false;
            X500Principal subject = cert.getSubjectX500Principal();
            int j = 0;
            while (true) {
                if (j == certs.size()) {
                    break;
                } else if (((X509Certificate) certs.get(j)).getIssuerX500Principal().equals(subject)) {
                    found = true;
                    break;
                } else {
                    j++;
                }
            }
            if (!found) {
                arrayList.add(cert);
                certs.remove(i2);
            }
        }
        if (arrayList.size() > 1) {
            return arrayList2;
        }
        for (int i3 = 0; i3 != arrayList.size(); i3++) {
            X500Principal issuer2 = ((X509Certificate) arrayList.get(i3)).getIssuerX500Principal();
            int j2 = 0;
            while (true) {
                if (j2 >= certs.size()) {
                    break;
                }
                X509Certificate c = (X509Certificate) certs.get(j2);
                if (issuer2.equals(c.getSubjectX500Principal())) {
                    arrayList.add(c);
                    certs.remove(j2);
                    break;
                }
                j2++;
            }
        }
        if (certs.size() > 0) {
            return arrayList2;
        }
        return arrayList;
    }

    PKIXCertPath(List certificates2) {
        super("X.509");
        this.certificates = sortCerts(new ArrayList(certificates2));
    }

    PKIXCertPath(InputStream inStream, String encoding) throws CertificateException {
        super("X.509");
        try {
            if (encoding.equalsIgnoreCase("PkiPath")) {
                ASN1Primitive derObject = new ASN1InputStream(inStream).readObject();
                if (!(derObject instanceof ASN1Sequence)) {
                    throw new CertificateException("input stream does not contain a ASN1 SEQUENCE while reading PkiPath encoded data to load CertPath");
                }
                Enumeration e = ((ASN1Sequence) derObject).getObjects();
                this.certificates = new ArrayList();
                CertificateFactory certFactory = this.helper.createCertificateFactory("X.509");
                while (e.hasMoreElements()) {
                    this.certificates.add(0, certFactory.generateCertificate(new ByteArrayInputStream(((ASN1Encodable) e.nextElement()).toASN1Primitive().getEncoded(ASN1Encoding.DER))));
                }
            } else if (encoding.equalsIgnoreCase("PKCS7") || encoding.equalsIgnoreCase("PEM")) {
                InputStream bufferedInputStream = new BufferedInputStream(inStream);
                try {
                    this.certificates = new ArrayList();
                    CertificateFactory certFactory2 = this.helper.createCertificateFactory("X.509");
                    while (true) {
                        Certificate cert = certFactory2.generateCertificate(bufferedInputStream);
                        if (cert == null) {
                            break;
                        }
                        this.certificates.add(cert);
                    }
                    InputStream inStream2 = bufferedInputStream;
                } catch (IOException e2) {
                    ex = e2;
                    BufferedInputStream bufferedInputStream2 = bufferedInputStream;
                    throw new CertificateException("IOException throw while decoding CertPath:\n" + ex.toString());
                } catch (NoSuchProviderException e3) {
                    ex = e3;
                    BufferedInputStream bufferedInputStream3 = bufferedInputStream;
                    throw new CertificateException("BouncyCastle provider not found while trying to get a CertificateFactory:\n" + ex.toString());
                }
            } else {
                throw new CertificateException("unsupported encoding: " + encoding);
            }
            this.certificates = sortCerts(this.certificates);
        } catch (IOException e4) {
            ex = e4;
            throw new CertificateException("IOException throw while decoding CertPath:\n" + ex.toString());
        } catch (NoSuchProviderException e5) {
            ex = e5;
            throw new CertificateException("BouncyCastle provider not found while trying to get a CertificateFactory:\n" + ex.toString());
        }
    }

    public Iterator getEncodings() {
        return certPathEncodings.iterator();
    }

    public byte[] getEncoded() throws CertificateEncodingException {
        Iterator iter = getEncodings();
        if (iter.hasNext()) {
            Object enc = iter.next();
            if (enc instanceof String) {
                return getEncoded((String) enc);
            }
        }
        return null;
    }

    public byte[] getEncoded(String encoding) throws CertificateEncodingException {
        if (encoding.equalsIgnoreCase("PkiPath")) {
            ASN1EncodableVector v = new ASN1EncodableVector();
            ListIterator iter = this.certificates.listIterator(this.certificates.size());
            while (iter.hasPrevious()) {
                v.add(toASN1Object((X509Certificate) iter.previous()));
            }
            return toDEREncoded(new DERSequence(v));
        } else if (encoding.equalsIgnoreCase("PKCS7")) {
            ContentInfo encInfo = new ContentInfo(PKCSObjectIdentifiers.data, null);
            ASN1EncodableVector v2 = new ASN1EncodableVector();
            for (int i = 0; i != this.certificates.size(); i++) {
                v2.add(toASN1Object((X509Certificate) this.certificates.get(i)));
            }
            return toDEREncoded(new ContentInfo(PKCSObjectIdentifiers.signedData, new SignedData(new ASN1Integer(1), new DERSet(), encInfo, new DERSet(v2), null, new DERSet())));
        } else if (encoding.equalsIgnoreCase("PEM")) {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            PemWriter pWrt = new PemWriter(new OutputStreamWriter(bOut));
            int i2 = 0;
            while (i2 != this.certificates.size()) {
                try {
                    pWrt.writeObject(new PemObject("CERTIFICATE", ((X509Certificate) this.certificates.get(i2)).getEncoded()));
                    i2++;
                } catch (Exception e) {
                    throw new CertificateEncodingException("can't encode certificate for PEM encoded path");
                }
            }
            pWrt.close();
            return bOut.toByteArray();
        } else {
            throw new CertificateEncodingException("unsupported encoding: " + encoding);
        }
    }

    public List getCertificates() {
        return Collections.unmodifiableList(new ArrayList(this.certificates));
    }

    private ASN1Primitive toASN1Object(X509Certificate cert) throws CertificateEncodingException {
        try {
            return new ASN1InputStream(cert.getEncoded()).readObject();
        } catch (Exception e) {
            throw new CertificateEncodingException("Exception while encoding certificate: " + e.toString());
        }
    }

    private byte[] toDEREncoded(ASN1Encodable obj) throws CertificateEncodingException {
        try {
            return obj.toASN1Primitive().getEncoded(ASN1Encoding.DER);
        } catch (IOException e) {
            throw new CertificateEncodingException("Exception thrown: " + e);
        }
    }
}
