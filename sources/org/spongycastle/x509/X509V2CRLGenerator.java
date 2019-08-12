package org.spongycastle.x509;

import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.security.cert.CRLException;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import javax.security.auth.x500.X500Principal;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1GeneralizedTime;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DERSequence;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.CertificateList;
import org.spongycastle.asn1.x509.Extensions;
import org.spongycastle.asn1.x509.TBSCertList;
import org.spongycastle.asn1.x509.Time;
import org.spongycastle.asn1.x509.V2TBSCertListGenerator;
import org.spongycastle.asn1.x509.X509Extensions;
import org.spongycastle.asn1.x509.X509ExtensionsGenerator;
import org.spongycastle.asn1.x509.X509Name;
import org.spongycastle.jce.X509Principal;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.provider.X509CRLObject;

public class X509V2CRLGenerator {
    private X509ExtensionsGenerator extGenerator = new X509ExtensionsGenerator();
    private AlgorithmIdentifier sigAlgId;
    private ASN1ObjectIdentifier sigOID;
    private String signatureAlgorithm;
    private V2TBSCertListGenerator tbsGen = new V2TBSCertListGenerator();

    private static class ExtCRLException extends CRLException {
        Throwable cause;

        ExtCRLException(String message, Throwable cause2) {
            super(message);
            this.cause = cause2;
        }

        public Throwable getCause() {
            return this.cause;
        }
    }

    public void reset() {
        this.tbsGen = new V2TBSCertListGenerator();
        this.extGenerator.reset();
    }

    public void setIssuerDN(X500Principal issuer) {
        try {
            this.tbsGen.setIssuer((X509Name) new X509Principal(issuer.getEncoded()));
        } catch (IOException e) {
            throw new IllegalArgumentException("can't process principal: " + e);
        }
    }

    public void setIssuerDN(X509Name issuer) {
        this.tbsGen.setIssuer(issuer);
    }

    public void setThisUpdate(Date date) {
        this.tbsGen.setThisUpdate(new Time(date));
    }

    public void setNextUpdate(Date date) {
        this.tbsGen.setNextUpdate(new Time(date));
    }

    public void addCRLEntry(BigInteger userCertificate, Date revocationDate, int reason) {
        this.tbsGen.addCRLEntry(new ASN1Integer(userCertificate), new Time(revocationDate), reason);
    }

    public void addCRLEntry(BigInteger userCertificate, Date revocationDate, int reason, Date invalidityDate) {
        this.tbsGen.addCRLEntry(new ASN1Integer(userCertificate), new Time(revocationDate), reason, new ASN1GeneralizedTime(invalidityDate));
    }

    public void addCRLEntry(BigInteger userCertificate, Date revocationDate, X509Extensions extensions) {
        this.tbsGen.addCRLEntry(new ASN1Integer(userCertificate), new Time(revocationDate), Extensions.getInstance(extensions));
    }

    public void addCRL(X509CRL other) throws CRLException {
        Set<X509CRLEntry> revocations = other.getRevokedCertificates();
        if (revocations != null) {
            for (X509CRLEntry entry : revocations) {
                try {
                    this.tbsGen.addCRLEntry(ASN1Sequence.getInstance(new ASN1InputStream(entry.getEncoded()).readObject()));
                } catch (IOException e) {
                    throw new CRLException("exception processing encoding of CRL: " + e.toString());
                }
            }
        }
    }

    public void setSignatureAlgorithm(String signatureAlgorithm2) {
        this.signatureAlgorithm = signatureAlgorithm2;
        try {
            this.sigOID = X509Util.getAlgorithmOID(signatureAlgorithm2);
            this.sigAlgId = X509Util.getSigAlgID(this.sigOID, signatureAlgorithm2);
            this.tbsGen.setSignature(this.sigAlgId);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unknown signature type requested");
        }
    }

    public void addExtension(String oid, boolean critical, ASN1Encodable value) {
        addExtension(new ASN1ObjectIdentifier(oid), critical, value);
    }

    public void addExtension(ASN1ObjectIdentifier oid, boolean critical, ASN1Encodable value) {
        this.extGenerator.addExtension(new ASN1ObjectIdentifier(oid.getId()), critical, value);
    }

    public void addExtension(String oid, boolean critical, byte[] value) {
        addExtension(new ASN1ObjectIdentifier(oid), critical, value);
    }

    public void addExtension(ASN1ObjectIdentifier oid, boolean critical, byte[] value) {
        this.extGenerator.addExtension(new ASN1ObjectIdentifier(oid.getId()), critical, value);
    }

    public X509CRL generateX509CRL(PrivateKey key) throws SecurityException, SignatureException, InvalidKeyException {
        try {
            return generateX509CRL(key, BouncyCastleProvider.PROVIDER_NAME, null);
        } catch (NoSuchProviderException e) {
            throw new SecurityException("BC provider not installed!");
        }
    }

    public X509CRL generateX509CRL(PrivateKey key, SecureRandom random) throws SecurityException, SignatureException, InvalidKeyException {
        try {
            return generateX509CRL(key, BouncyCastleProvider.PROVIDER_NAME, random);
        } catch (NoSuchProviderException e) {
            throw new SecurityException("BC provider not installed!");
        }
    }

    public X509CRL generateX509CRL(PrivateKey key, String provider) throws NoSuchProviderException, SecurityException, SignatureException, InvalidKeyException {
        return generateX509CRL(key, provider, null);
    }

    public X509CRL generateX509CRL(PrivateKey key, String provider, SecureRandom random) throws NoSuchProviderException, SecurityException, SignatureException, InvalidKeyException {
        try {
            return generate(key, provider, random);
        } catch (NoSuchProviderException e) {
            throw e;
        } catch (SignatureException e2) {
            throw e2;
        } catch (InvalidKeyException e3) {
            throw e3;
        } catch (GeneralSecurityException e4) {
            throw new SecurityException("exception: " + e4);
        }
    }

    public X509CRL generate(PrivateKey key) throws CRLException, IllegalStateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return generate(key, (SecureRandom) null);
    }

    public X509CRL generate(PrivateKey key, SecureRandom random) throws CRLException, IllegalStateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        TBSCertList tbsCrl = generateCertList();
        try {
            return generateJcaObject(tbsCrl, X509Util.calculateSignature(this.sigOID, this.signatureAlgorithm, key, random, tbsCrl));
        } catch (IOException e) {
            throw new ExtCRLException("cannot generate CRL encoding", e);
        }
    }

    public X509CRL generate(PrivateKey key, String provider) throws CRLException, IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        return generate(key, provider, null);
    }

    public X509CRL generate(PrivateKey key, String provider, SecureRandom random) throws CRLException, IllegalStateException, NoSuchProviderException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        TBSCertList tbsCrl = generateCertList();
        try {
            return generateJcaObject(tbsCrl, X509Util.calculateSignature(this.sigOID, this.signatureAlgorithm, provider, key, random, tbsCrl));
        } catch (IOException e) {
            throw new ExtCRLException("cannot generate CRL encoding", e);
        }
    }

    private TBSCertList generateCertList() {
        if (!this.extGenerator.isEmpty()) {
            this.tbsGen.setExtensions(this.extGenerator.generate());
        }
        return this.tbsGen.generateTBSCertList();
    }

    private X509CRL generateJcaObject(TBSCertList tbsCrl, byte[] signature) throws CRLException {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(tbsCrl);
        v.add(this.sigAlgId);
        v.add(new DERBitString(signature));
        return new X509CRLObject(new CertificateList(new DERSequence(v)));
    }

    public Iterator getSignatureAlgNames() {
        return X509Util.getAlgNames();
    }
}
