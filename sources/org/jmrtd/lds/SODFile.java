package org.jmrtd.lds;

import com.airbnb.android.utils.AirbnbConstants;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.Signature;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;
import javax.security.auth.x500.X500Principal;
import org.jmrtd.JMRTDSecurityProvider;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Encoding;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Object;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.ASN1TaggedObject;
import org.spongycastle.asn1.BERTaggedObject;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.DLSequence;
import org.spongycastle.asn1.DLSet;
import org.spongycastle.asn1.DLTaggedObject;
import org.spongycastle.asn1.cms.Attribute;
import org.spongycastle.asn1.cms.ContentInfo;
import org.spongycastle.asn1.cms.IssuerAndSerialNumber;
import org.spongycastle.asn1.cms.SignedData;
import org.spongycastle.asn1.cms.SignerIdentifier;
import org.spongycastle.asn1.cms.SignerInfo;
import org.spongycastle.asn1.icao.DataGroupHash;
import org.spongycastle.asn1.icao.LDSSecurityObject;
import org.spongycastle.asn1.icao.LDSVersionInfo;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.jce.provider.X509CertificateObject;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2ParameterSpec;

public class SODFile extends DataGroup {
    private static final Provider BC_PROVIDER = JMRTDSecurityProvider.getBouncyCastleProvider();
    private static final String ICAO_LDS_SOD_ALT_OID = "1.3.27.1.1.1";
    private static final String ICAO_LDS_SOD_OID = "2.23.136.1.1.1";
    private static final String IEEE_P1363_SHA1_OID = "1.3.14.3.2.26";
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final String PKCS1_MD2_WITH_RSA_OID = "1.2.840.113549.1.1.2";
    private static final String PKCS1_MD4_WITH_RSA_OID = "1.2.840.113549.1.1.3";
    private static final String PKCS1_MD5_WITH_RSA_OID = "1.2.840.113549.1.1.4";
    private static final String PKCS1_RSASSA_PSS_OID = "1.2.840.113549.1.1.10";
    private static final String PKCS1_RSA_OID = "1.2.840.113549.1.1.1";
    private static final String PKCS1_SHA1_WITH_RSA_OID = "1.2.840.113549.1.1.5";
    private static final String PKCS1_SHA224_WITH_RSA_OID = "1.2.840.113549.1.1.14";
    private static final String PKCS1_SHA256_WITH_RSA_AND_MGF1 = "1.2.840.113549.1.1.8";
    private static final String PKCS1_SHA256_WITH_RSA_OID = "1.2.840.113549.1.1.11";
    private static final String PKCS1_SHA384_WITH_RSA_OID = "1.2.840.113549.1.1.12";
    private static final String PKCS1_SHA512_WITH_RSA_OID = "1.2.840.113549.1.1.13";
    private static final String RFC_3369_CONTENT_TYPE_OID = "1.2.840.113549.1.9.3";
    private static final String RFC_3369_MESSAGE_DIGEST_OID = "1.2.840.113549.1.9.4";
    private static final String RFC_3369_SIGNED_DATA_OID = "1.2.840.113549.1.7.2";
    private static final String SDU_LDS_SOD_OID = "1.2.528.1.1006.1.20.1";
    private static final String X9_SHA1_WITH_ECDSA_OID = "1.2.840.10045.4.1";
    private static final String X9_SHA224_WITH_ECDSA_OID = "1.2.840.10045.4.3.1";
    private static final String X9_SHA256_WITH_ECDSA_OID = "1.2.840.10045.4.3.2";
    private static final long serialVersionUID = -1081347374739311111L;
    private SignedData signedData;

    public SODFile(String str, String str2, Map<Integer, byte[]> map, byte[] bArr, X509Certificate x509Certificate) throws NoSuchAlgorithmException, CertificateException {
        super(LDSFile.EF_SOD_TAG);
        try {
            this.signedData = createSignedData(str, str2, map, bArr, x509Certificate);
        } catch (IOException e) {
            LOGGER.severe("Error creating signedData: " + e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public SODFile(String str, String str2, Map<Integer, byte[]> map, PrivateKey privateKey, X509Certificate x509Certificate, String str3) throws NoSuchAlgorithmException, CertificateException {
        super(LDSFile.EF_SOD_TAG);
        try {
            this.signedData = createSignedData(str, str2, map, privateKey, x509Certificate, str3);
        } catch (IOException e) {
            LOGGER.severe("Error creating signedData: " + e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public SODFile(String str, String str2, Map<Integer, byte[]> map, PrivateKey privateKey, X509Certificate x509Certificate, String str3, String str4, String str5) throws NoSuchAlgorithmException, CertificateException {
        super(LDSFile.EF_SOD_TAG);
        try {
            this.signedData = createSignedData(str, str2, map, privateKey, x509Certificate, str3, str4, str5);
        } catch (IOException e) {
            LOGGER.severe("Error creating signedData: " + e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public SODFile(String str, String str2, Map<Integer, byte[]> map, PrivateKey privateKey, X509Certificate x509Certificate) throws NoSuchAlgorithmException, CertificateException {
        super(LDSFile.EF_SOD_TAG);
        try {
            this.signedData = createSignedData(str, str2, map, privateKey, x509Certificate, null);
        } catch (IOException e) {
            LOGGER.severe("Error creating signedData: " + e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public SODFile(InputStream inputStream) throws IOException {
        super(LDSFile.EF_SOD_TAG, inputStream);
    }

    private static SignerInfo getSignerInfo(SignedData signedData2) {
        ASN1Set signerInfos = signedData2.getSignerInfos();
        if (signerInfos.size() > 1) {
            LOGGER.warning("Found " + signerInfos.size() + " signerInfos");
        }
        if (0 < signerInfos.size()) {
            return new SignerInfo((ASN1Sequence) signerInfos.getObjectAt(0));
        }
        return null;
    }

    private static LDSSecurityObject getLDSSecurityObject(SignedData signedData2) {
        try {
            ContentInfo encapContentInfo = signedData2.getEncapContentInfo();
            String id = encapContentInfo.getContentType().getId();
            DEROctetString dEROctetString = (DEROctetString) encapContentInfo.getContent();
            if (!ICAO_LDS_SOD_OID.equals(id) && !SDU_LDS_SOD_OID.equals(id) && !ICAO_LDS_SOD_ALT_OID.equals(id)) {
                LOGGER.warning("SignedData does not appear to contain an LDS SOd. (content type is " + id + ", was expecting " + ICAO_LDS_SOD_OID + ")");
            }
            ASN1InputStream aSN1InputStream = new ASN1InputStream((InputStream) new ByteArrayInputStream(dEROctetString.getOctets()));
            ASN1Primitive readObject = aSN1InputStream.readObject();
            if (!(readObject instanceof ASN1Sequence)) {
                throw new IllegalStateException("Expected ASN1Sequence, found " + readObject.getClass().getSimpleName());
            }
            LDSSecurityObject instance = LDSSecurityObject.getInstance(readObject);
            if (aSN1InputStream.readObject() != null) {
                LOGGER.warning("Ignoring extra object found after LDSSecurityObject...");
            }
            return instance;
        } catch (IOException e) {
            throw new IllegalStateException("Could not read security object in signedData");
        }
    }

    private static byte[] getEContent(SignedData signedData2) {
        IOException iOException;
        byte[] bArr;
        NoSuchAlgorithmException noSuchAlgorithmException;
        byte[] bArr2;
        byte[] bArr3;
        byte[] bArr4 = null;
        SignerInfo signerInfo = getSignerInfo(signedData2);
        ASN1Set authenticatedAttributes = signerInfo.getAuthenticatedAttributes();
        byte[] octets = ((DEROctetString) signedData2.getEncapContentInfo().getContent()).getOctets();
        if (authenticatedAttributes.size() == 0) {
            return octets;
        }
        String id = signerInfo.getDigestAlgorithm().getAlgorithm().getId();
        try {
            byte[] encoded = authenticatedAttributes.getEncoded(ASN1Encoding.DER);
            try {
                Enumeration objects = authenticatedAttributes.getObjects();
                while (objects.hasMoreElements()) {
                    Attribute instance = Attribute.getInstance((ASN1Sequence) objects.nextElement());
                    if (RFC_3369_MESSAGE_DIGEST_OID.equals(instance.getAttrType().getId())) {
                        ASN1Set attrValues = instance.getAttrValues();
                        if (attrValues.size() != 1) {
                            LOGGER.warning("Expected only one attribute value in signedAttribute message digest in eContent!");
                        }
                        bArr3 = ((DEROctetString) attrValues.getObjectAt(0)).getOctets();
                    } else {
                        bArr3 = bArr4;
                    }
                    bArr4 = bArr3;
                }
                if (bArr4 == null) {
                    LOGGER.warning("Error extracting signedAttribute message digest in eContent!");
                }
                if (!Arrays.equals(bArr4, MessageDigest.getInstance(id).digest(octets))) {
                    LOGGER.warning("Error checking signedAttribute message digest in eContent!");
                }
                return encoded;
            } catch (NoSuchAlgorithmException e) {
                noSuchAlgorithmException = e;
                bArr2 = encoded;
                LOGGER.warning("Error checking signedAttributes in eContent! No such algorithm: \"" + id + "\": " + noSuchAlgorithmException.getMessage());
                return bArr2;
            } catch (IOException e2) {
                iOException = e2;
                bArr = encoded;
                LOGGER.severe("Error getting signedAttributes: " + iOException.getMessage());
                return bArr;
            }
        } catch (NoSuchAlgorithmException e3) {
            NoSuchAlgorithmException noSuchAlgorithmException2 = e3;
            bArr2 = null;
            noSuchAlgorithmException = noSuchAlgorithmException2;
        } catch (IOException e4) {
            IOException iOException2 = e4;
            bArr = null;
            iOException = iOException2;
            LOGGER.severe("Error getting signedAttributes: " + iOException.getMessage());
            return bArr;
        }
    }

    private static byte[] getEncryptedDigest(SignedData signedData2) {
        return getSignerInfo(signedData2).getEncryptedDigest().getOctets();
    }

    private static SignedData createSignedData(String str, String str2, Map<Integer, byte[]> map, byte[] bArr, X509Certificate x509Certificate) throws NoSuchAlgorithmException, CertificateException, IOException {
        ASN1Set createSingletonSet = createSingletonSet(createDigestAlgorithms(str));
        ContentInfo createContentInfo = createContentInfo(str, map);
        return new SignedData(createSingletonSet, createContentInfo, createSingletonSet(createCertificate(x509Certificate)), null, createSingletonSet(createSignerInfo(str, str2, ((DEROctetString) createContentInfo.getContent()).getOctets(), bArr, x509Certificate).toASN1Object()));
    }

    private static SignedData createSignedData(String str, String str2, Map<Integer, byte[]> map, PrivateKey privateKey, X509Certificate x509Certificate, String str3) throws NoSuchAlgorithmException, CertificateException, IOException {
        return createSignedData(str, str2, map, privateKey, x509Certificate, str3, null, null);
    }

    private static SignedData createSignedData(String str, String str2, Map<Integer, byte[]> map, PrivateKey privateKey, X509Certificate x509Certificate, String str3, String str4, String str5) throws NoSuchAlgorithmException, CertificateException, IOException {
        Signature instance;
        ASN1Set createSingletonSet = createSingletonSet(createDigestAlgorithms(str));
        ContentInfo createContentInfo = createContentInfo(str, map, str4, str5);
        byte[] octets = ((DEROctetString) createContentInfo.getContent()).getOctets();
        try {
            byte[] encoded = createAuthenticatedAttributes(str, octets).getEncoded(ASN1Encoding.DER);
            if (str3 != null) {
                instance = Signature.getInstance(str2, str3);
            } else {
                instance = Signature.getInstance(str2);
            }
            instance.initSign(privateKey);
            instance.update(encoded);
            return new SignedData(createSingletonSet, createContentInfo, createSingletonSet(createCertificate(x509Certificate)), null, createSingletonSet(createSignerInfo(str, str2, octets, instance.sign(), x509Certificate).toASN1Object()));
        } catch (Exception e) {
            LOGGER.severe("Exception: " + e.getMessage());
            return null;
        }
    }

    private static ASN1Sequence createDigestAlgorithms(String str) throws NoSuchAlgorithmException {
        ASN1ObjectIdentifier aSN1ObjectIdentifier = new ASN1ObjectIdentifier(lookupOIDByMnemonic(str).getId());
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(aSN1ObjectIdentifier);
        return new DLSequence(aSN1EncodableVector);
    }

    private static ASN1Sequence createCertificate(X509Certificate x509Certificate) throws CertificateException {
        ASN1InputStream aSN1InputStream;
        try {
            aSN1InputStream = new ASN1InputStream(x509Certificate.getEncoded());
            ASN1Sequence aSN1Sequence = (ASN1Sequence) aSN1InputStream.readObject();
            aSN1InputStream.close();
            return aSN1Sequence;
        } catch (IOException e) {
            throw new CertificateException("Could not construct certificate byte stream");
        } catch (Throwable th) {
            aSN1InputStream.close();
            throw th;
        }
    }

    private static ContentInfo createContentInfo(String str, Map<Integer, byte[]> map) throws NoSuchAlgorithmException, IOException {
        return createContentInfo(str, map, null, null);
    }

    private static ContentInfo createContentInfo(String str, Map<Integer, byte[]> map, String str2, String str3) throws NoSuchAlgorithmException, IOException {
        LDSSecurityObject lDSSecurityObject;
        DataGroupHash[] dataGroupHashArr = new DataGroupHash[map.size()];
        int i = 0;
        Iterator it = map.keySet().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                break;
            }
            int intValue = ((Integer) it.next()).intValue();
            i = i2 + 1;
            dataGroupHashArr[i2] = new DataGroupHash(intValue, new DEROctetString((byte[]) map.get(Integer.valueOf(intValue))));
        }
        AlgorithmIdentifier instance = AlgorithmIdentifier.getInstance(lookupOIDByMnemonic(str));
        if (str2 == null) {
            lDSSecurityObject = new LDSSecurityObject(instance, dataGroupHashArr);
        } else {
            lDSSecurityObject = new LDSSecurityObject(instance, dataGroupHashArr, new LDSVersionInfo(str2, str3));
        }
        return new ContentInfo(new ASN1ObjectIdentifier(ICAO_LDS_SOD_OID), new DEROctetString((ASN1Encodable) lDSSecurityObject));
    }

    private static SignerInfo createSignerInfo(String str, String str2, byte[] bArr, byte[] bArr2, X509Certificate x509Certificate) throws NoSuchAlgorithmException {
        return new SignerInfo(new SignerIdentifier(new IssuerAndSerialNumber(new X500Name(x509Certificate.getIssuerX500Principal().getName("RFC2253")), x509Certificate.getSerialNumber())), new AlgorithmIdentifier(lookupOIDByMnemonic(str)), createAuthenticatedAttributes(str, bArr), new AlgorithmIdentifier(lookupOIDByMnemonic(str2)), (ASN1OctetString) new DEROctetString(bArr2), (ASN1Set) null);
    }

    private static ASN1Set createAuthenticatedAttributes(String str, byte[] bArr) throws NoSuchAlgorithmException {
        if (McElieceCCA2ParameterSpec.DEFAULT_MD.equals(str)) {
            str = "SHA-256";
        }
        DEROctetString dEROctetString = new DEROctetString(MessageDigest.getInstance(str).digest(bArr));
        return new DLSet((ASN1Encodable[]) new ASN1Object[]{new Attribute(new ASN1ObjectIdentifier(RFC_3369_CONTENT_TYPE_OID), createSingletonSet(new ASN1ObjectIdentifier(ICAO_LDS_SOD_OID))).toASN1Primitive(), new Attribute(new ASN1ObjectIdentifier(RFC_3369_MESSAGE_DIGEST_OID), createSingletonSet(dEROctetString)).toASN1Primitive()});
    }

    private static ASN1Set createSingletonSet(ASN1Object aSN1Object) {
        return new DLSet(new ASN1Encodable[]{aSN1Object});
    }

    private static String lookupMnemonicByOID(String str) throws NoSuchAlgorithmException {
        if (str == null) {
            return null;
        }
        if (str.equals(X509ObjectIdentifiers.organization.getId())) {
            return "O";
        }
        if (str.equals(X509ObjectIdentifiers.organizationalUnitName.getId())) {
            return "OU";
        }
        if (str.equals(X509ObjectIdentifiers.commonName.getId())) {
            return AirbnbConstants.COUNTRY_CODE_CHINA;
        }
        if (str.equals(X509ObjectIdentifiers.countryName.getId())) {
            return "C";
        }
        if (str.equals(X509ObjectIdentifiers.stateOrProvinceName.getId())) {
            return "ST";
        }
        if (str.equals(X509ObjectIdentifiers.localityName.getId())) {
            return "L";
        }
        if (str.equals(X509ObjectIdentifiers.id_SHA1.getId())) {
            return "SHA-1";
        }
        if (str.equals(NISTObjectIdentifiers.id_sha224.getId())) {
            return "SHA-224";
        }
        if (str.equals(NISTObjectIdentifiers.id_sha256.getId())) {
            return "SHA-256";
        }
        if (str.equals(NISTObjectIdentifiers.id_sha384.getId())) {
            return "SHA-384";
        }
        if (str.equals(NISTObjectIdentifiers.id_sha512.getId())) {
            return "SHA-512";
        }
        if (str.equals(X9_SHA1_WITH_ECDSA_OID)) {
            return "SHA1withECDSA";
        }
        if (str.equals(X9_SHA224_WITH_ECDSA_OID)) {
            return "SHA224withECDSA";
        }
        if (str.equals(X9_SHA256_WITH_ECDSA_OID)) {
            return "SHA256withECDSA";
        }
        if (str.equals(PKCS1_RSA_OID)) {
            return "RSA";
        }
        if (str.equals(PKCS1_MD2_WITH_RSA_OID)) {
            return "MD2withRSA";
        }
        if (str.equals(PKCS1_MD4_WITH_RSA_OID)) {
            return "MD4withRSA";
        }
        if (str.equals(PKCS1_MD5_WITH_RSA_OID)) {
            return "MD5withRSA";
        }
        if (str.equals(PKCS1_SHA1_WITH_RSA_OID)) {
            return "SHA1withRSA";
        }
        if (str.equals(PKCS1_SHA256_WITH_RSA_OID)) {
            return "SHA256withRSA";
        }
        if (str.equals(PKCS1_SHA384_WITH_RSA_OID)) {
            return "SHA384withRSA";
        }
        if (str.equals(PKCS1_SHA512_WITH_RSA_OID)) {
            return "SHA512withRSA";
        }
        if (str.equals(PKCS1_SHA224_WITH_RSA_OID)) {
            return "SHA224withRSA";
        }
        if (str.equals(IEEE_P1363_SHA1_OID)) {
            return "SHA-1";
        }
        if (str.equals(PKCS1_RSASSA_PSS_OID)) {
            return "SSAwithRSA/PSS";
        }
        if (str.equals(PKCS1_SHA256_WITH_RSA_AND_MGF1)) {
            return "SHA256withRSAandMGF1";
        }
        throw new NoSuchAlgorithmException("Unknown OID " + str);
    }

    private static ASN1ObjectIdentifier lookupOIDByMnemonic(String str) throws NoSuchAlgorithmException {
        if (str.equals("O")) {
            return X509ObjectIdentifiers.organization;
        }
        if (str.equals("OU")) {
            return X509ObjectIdentifiers.organizationalUnitName;
        }
        if (str.equals(AirbnbConstants.COUNTRY_CODE_CHINA)) {
            return X509ObjectIdentifiers.commonName;
        }
        if (str.equals("C")) {
            return X509ObjectIdentifiers.countryName;
        }
        if (str.equals("ST")) {
            return X509ObjectIdentifiers.stateOrProvinceName;
        }
        if (str.equals("L")) {
            return X509ObjectIdentifiers.localityName;
        }
        if (str.equalsIgnoreCase("SHA-1") || str.equalsIgnoreCase("SHA1")) {
            return X509ObjectIdentifiers.id_SHA1;
        }
        if (str.equalsIgnoreCase("SHA-224") || str.equalsIgnoreCase("SHA224")) {
            return NISTObjectIdentifiers.id_sha224;
        }
        if (str.equalsIgnoreCase("SHA-256") || str.equalsIgnoreCase(McElieceCCA2ParameterSpec.DEFAULT_MD)) {
            return NISTObjectIdentifiers.id_sha256;
        }
        if (str.equalsIgnoreCase("SHA-384") || str.equalsIgnoreCase("SHA384")) {
            return NISTObjectIdentifiers.id_sha384;
        }
        if (str.equalsIgnoreCase("SHA-512") || str.equalsIgnoreCase("SHA512")) {
            return NISTObjectIdentifiers.id_sha512;
        }
        if (str.equalsIgnoreCase("RSA")) {
            return new ASN1ObjectIdentifier(PKCS1_RSA_OID);
        }
        if (str.equalsIgnoreCase("MD2withRSA")) {
            return new ASN1ObjectIdentifier(PKCS1_MD2_WITH_RSA_OID);
        }
        if (str.equalsIgnoreCase("MD4withRSA")) {
            return new ASN1ObjectIdentifier(PKCS1_MD4_WITH_RSA_OID);
        }
        if (str.equalsIgnoreCase("MD5withRSA")) {
            return new ASN1ObjectIdentifier(PKCS1_MD5_WITH_RSA_OID);
        }
        if (str.equalsIgnoreCase("SHA1withRSA")) {
            return new ASN1ObjectIdentifier(PKCS1_SHA1_WITH_RSA_OID);
        }
        if (str.equalsIgnoreCase("SHA256withRSA")) {
            return new ASN1ObjectIdentifier(PKCS1_SHA256_WITH_RSA_OID);
        }
        if (str.equalsIgnoreCase("SHA384withRSA")) {
            return new ASN1ObjectIdentifier(PKCS1_SHA384_WITH_RSA_OID);
        }
        if (str.equalsIgnoreCase("SHA512withRSA")) {
            return new ASN1ObjectIdentifier(PKCS1_SHA512_WITH_RSA_OID);
        }
        if (str.equalsIgnoreCase("SHA224withRSA")) {
            return new ASN1ObjectIdentifier(PKCS1_SHA224_WITH_RSA_OID);
        }
        if (str.equalsIgnoreCase("SHA1withECDSA")) {
            return new ASN1ObjectIdentifier(X9_SHA1_WITH_ECDSA_OID);
        }
        if (str.equalsIgnoreCase("SHA224withECDSA")) {
            return new ASN1ObjectIdentifier(X9_SHA224_WITH_ECDSA_OID);
        }
        if (str.equalsIgnoreCase("SHA256withECDSA")) {
            return new ASN1ObjectIdentifier(X9_SHA256_WITH_ECDSA_OID);
        }
        if (str.equalsIgnoreCase("SAwithRSA/PSS")) {
            return new ASN1ObjectIdentifier(PKCS1_RSASSA_PSS_OID);
        }
        if (str.equalsIgnoreCase("SSAwithRSA/PSS")) {
            return new ASN1ObjectIdentifier(PKCS1_RSASSA_PSS_OID);
        }
        if (str.equalsIgnoreCase("RSASSA-PSS")) {
            return new ASN1ObjectIdentifier(PKCS1_RSASSA_PSS_OID);
        }
        if (str.equalsIgnoreCase("SHA256withRSAandMGF1")) {
            return new ASN1ObjectIdentifier(PKCS1_SHA256_WITH_RSA_AND_MGF1);
        }
        throw new NoSuchAlgorithmException("Unknown name " + str);
    }

    /* access modifiers changed from: protected */
    public void readContent(InputStream inputStream) throws IOException {
        int tagNo;
        ASN1Primitive object;
        ASN1Sequence aSN1Sequence = (ASN1Sequence) new ASN1InputStream(inputStream).readObject();
        if (aSN1Sequence.size() != 2) {
            throw new IOException("Was expecting a DER sequence of length 2, found a DER sequence of length " + aSN1Sequence.size());
        }
        String id = ((ASN1ObjectIdentifier) aSN1Sequence.getObjectAt(0)).getId();
        if (!RFC_3369_SIGNED_DATA_OID.equals(id)) {
            throw new IOException("Was expecting signed-data content type OID (1.2.840.113549.1.7.2), found " + id);
        }
        ASN1Encodable objectAt = aSN1Sequence.getObjectAt(1);
        if (objectAt instanceof DERTaggedObject) {
            DERTaggedObject dERTaggedObject = (DERTaggedObject) objectAt;
            tagNo = dERTaggedObject.getTagNo();
            object = dERTaggedObject.getObject();
        } else if (objectAt instanceof BERTaggedObject) {
            BERTaggedObject bERTaggedObject = (BERTaggedObject) objectAt;
            tagNo = bERTaggedObject.getTagNo();
            object = bERTaggedObject.getObject();
        } else if (objectAt instanceof ASN1TaggedObject) {
            DLTaggedObject dLTaggedObject = (DLTaggedObject) objectAt;
            tagNo = dLTaggedObject.getTagNo();
            object = dLTaggedObject.getObject();
        } else if (objectAt instanceof ASN1TaggedObject) {
            ASN1TaggedObject aSN1TaggedObject = (ASN1TaggedObject) objectAt;
            tagNo = aSN1TaggedObject.getTagNo();
            object = aSN1TaggedObject.getObject();
        } else {
            throw new IOException("Was expecting an ASN1TaggedObject, found " + objectAt.getClass().getCanonicalName());
        }
        if (tagNo != 0) {
            throw new IOException("Was expecting tag 0, found " + Integer.toHexString(tagNo));
        } else if (!(object instanceof ASN1Sequence)) {
            throw new IOException("Was expecting an ASN.1 sequence as content");
        } else {
            this.signedData = SignedData.getInstance((ASN1Sequence) object);
        }
    }

    /* access modifiers changed from: protected */
    public void writeContent(OutputStream outputStream) throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        aSN1EncodableVector.add(new ASN1ObjectIdentifier(RFC_3369_SIGNED_DATA_OID));
        aSN1EncodableVector.add(new DERTaggedObject(0, this.signedData));
        outputStream.write(new DLSequence(aSN1EncodableVector).getEncoded(ASN1Encoding.DER));
    }

    public Map<Integer, byte[]> getDataGroupHashes() {
        DataGroupHash[] datagroupHash = getLDSSecurityObject(this.signedData).getDatagroupHash();
        TreeMap treeMap = new TreeMap();
        for (DataGroupHash dataGroupHash : datagroupHash) {
            treeMap.put(Integer.valueOf(dataGroupHash.getDataGroupNumber()), dataGroupHash.getDataGroupHashValue().getOctets());
        }
        return treeMap;
    }

    public byte[] getEncryptedDigest() {
        return getEncryptedDigest(this.signedData);
    }

    public byte[] getEContent() {
        return getEContent(this.signedData);
    }

    public String getDigestAlgorithm() {
        try {
            return lookupMnemonicByOID(getLDSSecurityObject(this.signedData).getDigestAlgorithmIdentifier().getAlgorithm().getId());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            return null;
        }
    }

    public String getSignerInfoDigestAlgorithm() {
        try {
            return lookupMnemonicByOID(getSignerInfo(this.signedData).getDigestAlgorithm().getAlgorithm().getId());
        } catch (NoSuchAlgorithmException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            return null;
        }
    }

    public String getDigestEncryptionAlgorithm() {
        try {
            String id = getSignerInfo(this.signedData).getDigestEncryptionAlgorithm().getAlgorithm().getId();
            if (id == null) {
                return null;
            }
            return lookupMnemonicByOID(id);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            return null;
        }
    }

    public String getLDSVersion() {
        LDSVersionInfo versionInfo = getLDSSecurityObject(this.signedData).getVersionInfo();
        if (versionInfo == null) {
            return null;
        }
        return versionInfo.getLdsVersion();
    }

    public String getUnicodeVersion() {
        LDSVersionInfo versionInfo = getLDSSecurityObject(this.signedData).getVersionInfo();
        if (versionInfo == null) {
            return null;
        }
        return versionInfo.getUnicodeVersion();
    }

    public X509Certificate getDocSigningCertificate() throws CertificateException {
        ASN1Set certificates = this.signedData.getCertificates();
        if (certificates == null || certificates.size() <= 0) {
            return null;
        }
        if (certificates.size() != 1) {
            LOGGER.warning("Found " + certificates.size() + " certificates");
        }
        X509CertificateObject x509CertificateObject = null;
        byte[] bArr = null;
        int i = 0;
        while (i < certificates.size()) {
            x509CertificateObject = new X509CertificateObject(Certificate.getInstance((ASN1Sequence) certificates.getObjectAt(i)));
            i++;
            bArr = x509CertificateObject.getEncoded();
        }
        try {
            return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
        } catch (Exception e) {
            return x509CertificateObject;
        }
    }

    public boolean checkDocSignature(java.security.cert.Certificate certificate) throws GeneralSecurityException {
        String str;
        Signature instance;
        MessageDigest instance2;
        byte[] eContent = getEContent();
        byte[] encryptedDigest = getEncryptedDigest();
        try {
            str = getDigestEncryptionAlgorithm();
        } catch (Exception e) {
            str = null;
        }
        if (str == null) {
            String signerInfoDigestAlgorithm = getSignerInfoDigestAlgorithm();
            try {
                instance2 = MessageDigest.getInstance(signerInfoDigestAlgorithm);
            } catch (Exception e2) {
                instance2 = MessageDigest.getInstance(signerInfoDigestAlgorithm, BC_PROVIDER);
            }
            instance2.update(eContent);
            return Arrays.equals(instance2.digest(), encryptedDigest);
        }
        if ("SSAwithRSA/PSS".equals(str)) {
            str = getSignerInfoDigestAlgorithm().replace("-", "") + "withRSA/PSS";
        }
        if ("RSA".equals(str)) {
            str = getSignerInfoDigestAlgorithm().replace("-", "") + "withRSA";
        }
        LOGGER.info("digestEncryptionAlgorithm = " + str);
        try {
            instance = Signature.getInstance(str);
        } catch (Exception e3) {
            instance = Signature.getInstance(str, BC_PROVIDER);
        }
        instance.initVerify(certificate);
        instance.update(eContent);
        return instance.verify(encryptedDigest);
    }

    public X500Principal getIssuerX500Principal() {
        try {
            return new X500Principal(getIssuerAndSerialNumber().getName().getEncoded(ASN1Encoding.DER));
        } catch (IOException e) {
            LOGGER.severe("Could not get issuer: " + e.getMessage());
            return null;
        }
    }

    public BigInteger getSerialNumber() {
        return getIssuerAndSerialNumber().getSerialNumber().getValue();
    }

    public String toString() {
        try {
            return "SODFile " + getDocSigningCertificate().getIssuerX500Principal();
        } catch (Exception e) {
            return "SODFile";
        }
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!obj.getClass().equals(getClass())) {
            return false;
        }
        return Arrays.equals(getEncoded(), ((SODFile) obj).getEncoded());
    }

    public int hashCode() {
        return (Arrays.hashCode(getEncoded()) * 11) + 111;
    }

    private IssuerAndSerialNumber getIssuerAndSerialNumber() {
        IssuerAndSerialNumber instance = IssuerAndSerialNumber.getInstance(getSignerInfo(this.signedData).getSID().getId());
        return new IssuerAndSerialNumber(instance.getName(), instance.getSerialNumber().getValue());
    }
}
