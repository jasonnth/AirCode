package org.spongycastle.jcajce.provider.keystore.pkcs12;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore.LoadStoreParameter;
import java.security.KeyStore.PasswordProtection;
import java.security.KeyStore.ProtectionParameter;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.DERBMPString;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.cryptopro.GOST28147Parameters;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.ntt.NTTObjectIdentifiers;
import org.spongycastle.asn1.pkcs.AuthenticatedSafe;
import org.spongycastle.asn1.pkcs.CertBag;
import org.spongycastle.asn1.pkcs.ContentInfo;
import org.spongycastle.asn1.pkcs.EncryptedData;
import org.spongycastle.asn1.pkcs.EncryptedPrivateKeyInfo;
import org.spongycastle.asn1.pkcs.MacData;
import org.spongycastle.asn1.pkcs.PBES2Parameters;
import org.spongycastle.asn1.pkcs.PBKDF2Params;
import org.spongycastle.asn1.pkcs.PKCS12PBEParams;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.asn1.pkcs.Pfx;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.asn1.pkcs.SafeBag;
import org.spongycastle.asn1.util.ASN1Dump;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.DigestInfo;
import org.spongycastle.asn1.x509.SubjectKeyIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.asn1.x509.X509ObjectIdentifiers;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.digests.SHA1Digest;
import org.spongycastle.jcajce.PKCS12Key;
import org.spongycastle.jcajce.PKCS12StoreParameter;
import org.spongycastle.jcajce.spec.GOST28147ParameterSpec;
import org.spongycastle.jcajce.spec.PBKDF2KeySpec;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.interfaces.BCKeyStore;
import org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.provider.JDKPKCS12StoreParameter;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;
import org.spongycastle.util.Strings;
import org.spongycastle.util.encoders.Hex;

public class PKCS12KeyStoreSpi extends KeyStoreSpi implements PKCSObjectIdentifiers, X509ObjectIdentifiers, BCKeyStore {
    static final int CERTIFICATE = 1;
    static final int KEY = 2;
    static final int KEY_PRIVATE = 0;
    static final int KEY_PUBLIC = 1;
    static final int KEY_SECRET = 2;
    private static final int MIN_ITERATIONS = 1024;
    static final int NULL = 0;
    private static final int SALT_SIZE = 20;
    static final int SEALED = 4;
    static final int SECRET = 3;
    private static final DefaultSecretKeyProvider keySizeProvider = new DefaultSecretKeyProvider();
    private ASN1ObjectIdentifier certAlgorithm;
    private CertificateFactory certFact;
    private IgnoresCaseHashtable certs = new IgnoresCaseHashtable();
    private Hashtable chainCerts = new Hashtable();
    private final JcaJceHelper helper = new BCJcaJceHelper();
    private ASN1ObjectIdentifier keyAlgorithm;
    private Hashtable keyCerts = new Hashtable();
    private IgnoresCaseHashtable keys = new IgnoresCaseHashtable();
    private Hashtable localIds = new Hashtable();
    protected SecureRandom random = new SecureRandom();

    public static class BCPKCS12KeyStore extends PKCS12KeyStoreSpi {
        public BCPKCS12KeyStore() {
            super(new BouncyCastleProvider(), pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd40BitRC2_CBC);
        }
    }

    public static class BCPKCS12KeyStore3DES extends PKCS12KeyStoreSpi {
        public BCPKCS12KeyStore3DES() {
            super(new BouncyCastleProvider(), pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd3_KeyTripleDES_CBC);
        }
    }

    private class CertId {

        /* renamed from: id */
        byte[] f6917id;

        CertId(PublicKey key) {
            this.f6917id = PKCS12KeyStoreSpi.this.createSubjectKeyId(key).getKeyIdentifier();
        }

        CertId(byte[] id) {
            this.f6917id = id;
        }

        public int hashCode() {
            return Arrays.hashCode(this.f6917id);
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof CertId)) {
                return false;
            }
            return Arrays.areEqual(this.f6917id, ((CertId) o).f6917id);
        }
    }

    public static class DefPKCS12KeyStore extends PKCS12KeyStoreSpi {
        public DefPKCS12KeyStore() {
            super(null, pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd40BitRC2_CBC);
        }
    }

    public static class DefPKCS12KeyStore3DES extends PKCS12KeyStoreSpi {
        public DefPKCS12KeyStore3DES() {
            super(null, pbeWithSHAAnd3_KeyTripleDES_CBC, pbeWithSHAAnd3_KeyTripleDES_CBC);
        }
    }

    private static class DefaultSecretKeyProvider {
        private final Map KEY_SIZES;

        DefaultSecretKeyProvider() {
            Map keySizes = new HashMap();
            keySizes.put(new ASN1ObjectIdentifier("1.2.840.113533.7.66.10"), Integers.valueOf(128));
            keySizes.put(PKCSObjectIdentifiers.des_EDE3_CBC, Integers.valueOf(192));
            keySizes.put(NISTObjectIdentifiers.id_aes128_CBC, Integers.valueOf(128));
            keySizes.put(NISTObjectIdentifiers.id_aes192_CBC, Integers.valueOf(192));
            keySizes.put(NISTObjectIdentifiers.id_aes256_CBC, Integers.valueOf(256));
            keySizes.put(NTTObjectIdentifiers.id_camellia128_cbc, Integers.valueOf(128));
            keySizes.put(NTTObjectIdentifiers.id_camellia192_cbc, Integers.valueOf(192));
            keySizes.put(NTTObjectIdentifiers.id_camellia256_cbc, Integers.valueOf(256));
            keySizes.put(CryptoProObjectIdentifiers.gostR28147_gcfb, Integers.valueOf(256));
            this.KEY_SIZES = Collections.unmodifiableMap(keySizes);
        }

        public int getKeySize(AlgorithmIdentifier algorithmIdentifier) {
            Integer keySize = (Integer) this.KEY_SIZES.get(algorithmIdentifier.getAlgorithm());
            if (keySize != null) {
                return keySize.intValue();
            }
            return -1;
        }
    }

    private static class IgnoresCaseHashtable {
        private Hashtable keys;
        private Hashtable orig;

        private IgnoresCaseHashtable() {
            this.orig = new Hashtable();
            this.keys = new Hashtable();
        }

        public void put(String key, Object value) {
            String lower = key == null ? null : Strings.toLowerCase(key);
            String k = (String) this.keys.get(lower);
            if (k != null) {
                this.orig.remove(k);
            }
            this.keys.put(lower, key);
            this.orig.put(key, value);
        }

        public Enumeration keys() {
            return this.orig.keys();
        }

        public Object remove(String alias) {
            String k = (String) this.keys.remove(alias == null ? null : Strings.toLowerCase(alias));
            if (k == null) {
                return null;
            }
            return this.orig.remove(k);
        }

        public Object get(String alias) {
            String k = (String) this.keys.get(alias == null ? null : Strings.toLowerCase(alias));
            if (k == null) {
                return null;
            }
            return this.orig.get(k);
        }

        public Enumeration elements() {
            return this.orig.elements();
        }
    }

    public PKCS12KeyStoreSpi(Provider provider, ASN1ObjectIdentifier keyAlgorithm2, ASN1ObjectIdentifier certAlgorithm2) {
        this.keyAlgorithm = keyAlgorithm2;
        this.certAlgorithm = certAlgorithm2;
        if (provider != null) {
            try {
                this.certFact = CertificateFactory.getInstance("X.509", provider);
            } catch (Exception e) {
                throw new IllegalArgumentException("can't create cert factory - " + e.toString());
            }
        } else {
            this.certFact = CertificateFactory.getInstance("X.509");
        }
    }

    /* access modifiers changed from: private */
    public SubjectKeyIdentifier createSubjectKeyId(PublicKey pubKey) {
        try {
            return new SubjectKeyIdentifier(getDigest(SubjectPublicKeyInfo.getInstance(pubKey.getEncoded())));
        } catch (Exception e) {
            throw new RuntimeException("error creating key");
        }
    }

    private static byte[] getDigest(SubjectPublicKeyInfo spki) {
        Digest digest = new SHA1Digest();
        byte[] resBuf = new byte[digest.getDigestSize()];
        byte[] bytes = spki.getPublicKeyData().getBytes();
        digest.update(bytes, 0, bytes.length);
        digest.doFinal(resBuf, 0);
        return resBuf;
    }

    public void setRandom(SecureRandom rand) {
        this.random = rand;
    }

    public Enumeration engineAliases() {
        Hashtable tab = new Hashtable();
        Enumeration e = this.certs.keys();
        while (e.hasMoreElements()) {
            tab.put(e.nextElement(), "cert");
        }
        Enumeration e2 = this.keys.keys();
        while (e2.hasMoreElements()) {
            String a = (String) e2.nextElement();
            if (tab.get(a) == null) {
                tab.put(a, "key");
            }
        }
        return tab.keys();
    }

    public boolean engineContainsAlias(String alias) {
        return (this.certs.get(alias) == null && this.keys.get(alias) == null) ? false : true;
    }

    public void engineDeleteEntry(String alias) throws KeyStoreException {
        Key k = (Key) this.keys.remove(alias);
        Certificate c = (Certificate) this.certs.remove(alias);
        if (c != null) {
            this.chainCerts.remove(new CertId(c.getPublicKey()));
        }
        if (k != null) {
            String id = (String) this.localIds.remove(alias);
            if (id != null) {
                c = (Certificate) this.keyCerts.remove(id);
            }
            if (c != null) {
                this.chainCerts.remove(new CertId(c.getPublicKey()));
            }
        }
    }

    public Certificate engineGetCertificate(String alias) {
        if (alias == null) {
            throw new IllegalArgumentException("null alias passed to getCertificate.");
        }
        Certificate c = (Certificate) this.certs.get(alias);
        if (c != null) {
            return c;
        }
        String id = (String) this.localIds.get(alias);
        if (id != null) {
            return (Certificate) this.keyCerts.get(id);
        }
        return (Certificate) this.keyCerts.get(alias);
    }

    public String engineGetCertificateAlias(Certificate cert) {
        Enumeration c = this.certs.elements();
        Enumeration k = this.certs.keys();
        while (c.hasMoreElements()) {
            String ta = (String) k.nextElement();
            if (((Certificate) c.nextElement()).equals(cert)) {
                return ta;
            }
        }
        Enumeration c2 = this.keyCerts.elements();
        Enumeration k2 = this.keyCerts.keys();
        while (c2.hasMoreElements()) {
            String ta2 = (String) k2.nextElement();
            if (((Certificate) c2.nextElement()).equals(cert)) {
                return ta2;
            }
        }
        return null;
    }

    /* JADX WARNING: type inference failed for: r13v3 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.security.cert.Certificate[] engineGetCertificateChain(java.lang.String r21) {
        /*
            r20 = this;
            if (r21 != 0) goto L_0x000b
            java.lang.IllegalArgumentException r17 = new java.lang.IllegalArgumentException
            java.lang.String r18 = "null alias passed to getCertificateChain."
            r17.<init>(r18)
            throw r17
        L_0x000b:
            boolean r17 = r20.engineIsKeyEntry(r21)
            if (r17 != 0) goto L_0x0013
            r7 = 0
        L_0x0012:
            return r7
        L_0x0013:
            java.security.cert.Certificate r6 = r20.engineGetCertificate(r21)
            if (r6 == 0) goto L_0x00ea
            java.util.Vector r9 = new java.util.Vector
            r9.<init>()
        L_0x001e:
            if (r6 == 0) goto L_0x00cf
            r16 = r6
            java.security.cert.X509Certificate r16 = (java.security.cert.X509Certificate) r16
            r13 = 0
            org.spongycastle.asn1.ASN1ObjectIdentifier r17 = org.spongycastle.asn1.x509.Extension.authorityKeyIdentifier
            java.lang.String r17 = r17.getId()
            byte[] r5 = r16.getExtensionValue(r17)
            if (r5 == 0) goto L_0x006e
            org.spongycastle.asn1.ASN1InputStream r3 = new org.spongycastle.asn1.ASN1InputStream     // Catch:{ IOException -> 0x00b9 }
            r3.<init>(r5)     // Catch:{ IOException -> 0x00b9 }
            org.spongycastle.asn1.ASN1Primitive r17 = r3.readObject()     // Catch:{ IOException -> 0x00b9 }
            org.spongycastle.asn1.ASN1OctetString r17 = (org.spongycastle.asn1.ASN1OctetString) r17     // Catch:{ IOException -> 0x00b9 }
            byte[] r4 = r17.getOctets()     // Catch:{ IOException -> 0x00b9 }
            org.spongycastle.asn1.ASN1InputStream r3 = new org.spongycastle.asn1.ASN1InputStream     // Catch:{ IOException -> 0x00b9 }
            r3.<init>(r4)     // Catch:{ IOException -> 0x00b9 }
            org.spongycastle.asn1.ASN1Primitive r17 = r3.readObject()     // Catch:{ IOException -> 0x00b9 }
            org.spongycastle.asn1.x509.AuthorityKeyIdentifier r12 = org.spongycastle.asn1.x509.AuthorityKeyIdentifier.getInstance(r17)     // Catch:{ IOException -> 0x00b9 }
            byte[] r17 = r12.getKeyIdentifier()     // Catch:{ IOException -> 0x00b9 }
            if (r17 == 0) goto L_0x006e
            r0 = r20
            java.util.Hashtable r0 = r0.chainCerts     // Catch:{ IOException -> 0x00b9 }
            r17 = r0
            org.spongycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId r18 = new org.spongycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId     // Catch:{ IOException -> 0x00b9 }
            byte[] r19 = r12.getKeyIdentifier()     // Catch:{ IOException -> 0x00b9 }
            r0 = r18
            r1 = r20
            r2 = r19
            r0.<init>(r2)     // Catch:{ IOException -> 0x00b9 }
            java.lang.Object r13 = r17.get(r18)     // Catch:{ IOException -> 0x00b9 }
            java.security.cert.Certificate r13 = (java.security.cert.Certificate) r13     // Catch:{ IOException -> 0x00b9 }
        L_0x006e:
            if (r13 != 0) goto L_0x00b0
            java.security.Principal r11 = r16.getIssuerDN()
            java.security.Principal r14 = r16.getSubjectDN()
            boolean r17 = r11.equals(r14)
            if (r17 != 0) goto L_0x00b0
            r0 = r20
            java.util.Hashtable r0 = r0.chainCerts
            r17 = r0
            java.util.Enumeration r10 = r17.keys()
        L_0x0088:
            boolean r17 = r10.hasMoreElements()
            if (r17 == 0) goto L_0x00b0
            r0 = r20
            java.util.Hashtable r0 = r0.chainCerts
            r17 = r0
            java.lang.Object r18 = r10.nextElement()
            java.lang.Object r8 = r17.get(r18)
            java.security.cert.X509Certificate r8 = (java.security.cert.X509Certificate) r8
            java.security.Principal r15 = r8.getSubjectDN()
            boolean r17 = r15.equals(r11)
            if (r17 == 0) goto L_0x0088
            java.security.PublicKey r17 = r8.getPublicKey()     // Catch:{ Exception -> 0x00ed }
            r16.verify(r17)     // Catch:{ Exception -> 0x00ed }
            r13 = r8
        L_0x00b0:
            boolean r17 = r9.contains(r6)
            if (r17 == 0) goto L_0x00c4
            r6 = 0
            goto L_0x001e
        L_0x00b9:
            r10 = move-exception
            java.lang.RuntimeException r17 = new java.lang.RuntimeException
            java.lang.String r18 = r10.toString()
            r17.<init>(r18)
            throw r17
        L_0x00c4:
            r9.addElement(r6)
            if (r13 == r6) goto L_0x00cc
            r6 = r13
            goto L_0x001e
        L_0x00cc:
            r6 = 0
            goto L_0x001e
        L_0x00cf:
            int r17 = r9.size()
            r0 = r17
            java.security.cert.Certificate[] r7 = new java.security.cert.Certificate[r0]
            r11 = 0
        L_0x00d8:
            int r0 = r7.length
            r17 = r0
            r0 = r17
            if (r11 == r0) goto L_0x0012
            java.lang.Object r17 = r9.elementAt(r11)
            java.security.cert.Certificate r17 = (java.security.cert.Certificate) r17
            r7[r11] = r17
            int r11 = r11 + 1
            goto L_0x00d8
        L_0x00ea:
            r7 = 0
            goto L_0x0012
        L_0x00ed:
            r17 = move-exception
            goto L_0x0088
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.engineGetCertificateChain(java.lang.String):java.security.cert.Certificate[]");
    }

    public Date engineGetCreationDate(String alias) {
        if (alias == null) {
            throw new NullPointerException("alias == null");
        } else if (this.keys.get(alias) == null && this.certs.get(alias) == null) {
            return null;
        } else {
            return new Date();
        }
    }

    public Key engineGetKey(String alias, char[] password) throws NoSuchAlgorithmException, UnrecoverableKeyException {
        if (alias != null) {
            return (Key) this.keys.get(alias);
        }
        throw new IllegalArgumentException("null alias passed to getKey.");
    }

    public boolean engineIsCertificateEntry(String alias) {
        return this.certs.get(alias) != null && this.keys.get(alias) == null;
    }

    public boolean engineIsKeyEntry(String alias) {
        return this.keys.get(alias) != null;
    }

    public void engineSetCertificateEntry(String alias, Certificate cert) throws KeyStoreException {
        if (this.keys.get(alias) != null) {
            throw new KeyStoreException("There is a key entry with the name " + alias + ".");
        }
        this.certs.put(alias, cert);
        this.chainCerts.put(new CertId(cert.getPublicKey()), cert);
    }

    public void engineSetKeyEntry(String alias, byte[] key, Certificate[] chain) throws KeyStoreException {
        throw new RuntimeException("operation not supported");
    }

    public void engineSetKeyEntry(String alias, Key key, char[] password, Certificate[] chain) throws KeyStoreException {
        if (!(key instanceof PrivateKey)) {
            throw new KeyStoreException("PKCS12 does not support non-PrivateKeys");
        } else if (!(key instanceof PrivateKey) || chain != null) {
            if (this.keys.get(alias) != null) {
                engineDeleteEntry(alias);
            }
            this.keys.put(alias, key);
            if (chain != null) {
                this.certs.put(alias, chain[0]);
                for (int i = 0; i != chain.length; i++) {
                    this.chainCerts.put(new CertId(chain[i].getPublicKey()), chain[i]);
                }
            }
        } else {
            throw new KeyStoreException("no certificate chain for private key");
        }
    }

    public int engineSize() {
        Hashtable tab = new Hashtable();
        Enumeration e = this.certs.keys();
        while (e.hasMoreElements()) {
            tab.put(e.nextElement(), "cert");
        }
        Enumeration e2 = this.keys.keys();
        while (e2.hasMoreElements()) {
            String a = (String) e2.nextElement();
            if (tab.get(a) == null) {
                tab.put(a, "key");
            }
        }
        return tab.size();
    }

    /* access modifiers changed from: protected */
    public PrivateKey unwrapKey(AlgorithmIdentifier algId, byte[] data, char[] password, boolean wrongPKCS12Zero) throws IOException {
        ASN1ObjectIdentifier algorithm = algId.getAlgorithm();
        try {
            if (algorithm.mo49525on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
                PKCS12PBEParams pbeParams = PKCS12PBEParams.getInstance(algId.getParameters());
                PBEParameterSpec defParams = new PBEParameterSpec(pbeParams.getIV(), pbeParams.getIterations().intValue());
                Cipher cipher = this.helper.createCipher(algorithm.getId());
                cipher.init(4, new PKCS12Key(password, wrongPKCS12Zero), defParams);
                return (PrivateKey) cipher.unwrap(data, "", 2);
            } else if (algorithm.equals(PKCSObjectIdentifiers.id_PBES2)) {
                return (PrivateKey) createCipher(4, password, algId).unwrap(data, "", 2);
            } else {
                throw new IOException("exception unwrapping private key - cannot recognise: " + algorithm);
            }
        } catch (Exception e) {
            throw new IOException("exception unwrapping private key - " + e.toString());
        }
    }

    /* access modifiers changed from: protected */
    public byte[] wrapKey(String algorithm, Key key, PKCS12PBEParams pbeParams, char[] password) throws IOException {
        PBEKeySpec pbeSpec = new PBEKeySpec(password);
        try {
            SecretKeyFactory keyFact = this.helper.createSecretKeyFactory(algorithm);
            PBEParameterSpec defParams = new PBEParameterSpec(pbeParams.getIV(), pbeParams.getIterations().intValue());
            Cipher cipher = this.helper.createCipher(algorithm);
            cipher.init(3, keyFact.generateSecret(pbeSpec), defParams);
            return cipher.wrap(key);
        } catch (Exception e) {
            throw new IOException("exception encrypting data - " + e.toString());
        }
    }

    /* access modifiers changed from: protected */
    public byte[] cryptData(boolean forEncryption, AlgorithmIdentifier algId, char[] password, boolean wrongPKCS12Zero, byte[] data) throws IOException {
        ASN1ObjectIdentifier algorithm = algId.getAlgorithm();
        int mode = forEncryption ? 1 : 2;
        if (algorithm.mo49525on(PKCSObjectIdentifiers.pkcs_12PbeIds)) {
            PKCS12PBEParams pbeParams = PKCS12PBEParams.getInstance(algId.getParameters());
            new PBEKeySpec(password);
            try {
                PBEParameterSpec defParams = new PBEParameterSpec(pbeParams.getIV(), pbeParams.getIterations().intValue());
                PKCS12Key key = new PKCS12Key(password, wrongPKCS12Zero);
                Cipher cipher = this.helper.createCipher(algorithm.getId());
                cipher.init(mode, key, defParams);
                return cipher.doFinal(data);
            } catch (Exception e) {
                throw new IOException("exception decrypting data - " + e.toString());
            }
        } else if (algorithm.equals(PKCSObjectIdentifiers.id_PBES2)) {
            try {
                return createCipher(mode, password, algId).doFinal(data);
            } catch (Exception e2) {
                throw new IOException("exception decrypting data - " + e2.toString());
            }
        } else {
            throw new IOException("unknown PBE algorithm: " + algorithm);
        }
    }

    private Cipher createCipher(int mode, char[] password, AlgorithmIdentifier algId) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchProviderException {
        SecretKey key;
        PBES2Parameters alg = PBES2Parameters.getInstance(algId.getParameters());
        PBKDF2Params func = PBKDF2Params.getInstance(alg.getKeyDerivationFunc().getParameters());
        AlgorithmIdentifier encScheme = AlgorithmIdentifier.getInstance(alg.getEncryptionScheme());
        SecretKeyFactory keyFact = this.helper.createSecretKeyFactory(alg.getKeyDerivationFunc().getAlgorithm().getId());
        if (func.isDefaultPrf()) {
            key = keyFact.generateSecret(new PBEKeySpec(password, func.getSalt(), func.getIterationCount().intValue(), keySizeProvider.getKeySize(encScheme)));
        } else {
            key = keyFact.generateSecret(new PBKDF2KeySpec(password, func.getSalt(), func.getIterationCount().intValue(), keySizeProvider.getKeySize(encScheme), func.getPrf()));
        }
        Cipher cipher = Cipher.getInstance(alg.getEncryptionScheme().getAlgorithm().getId());
        AlgorithmIdentifier instance = AlgorithmIdentifier.getInstance(alg.getEncryptionScheme());
        ASN1Encodable encParams = alg.getEncryptionScheme().getParameters();
        if (encParams instanceof ASN1OctetString) {
            cipher.init(mode, key, new IvParameterSpec(ASN1OctetString.getInstance(encParams).getOctets()));
        } else {
            GOST28147Parameters gParams = GOST28147Parameters.getInstance(encParams);
            cipher.init(mode, key, new GOST28147ParameterSpec(gParams.getEncryptionParamSet(), gParams.getIV()));
        }
        return cipher;
    }

    public void engineLoad(InputStream stream, char[] password) throws IOException {
        if (stream != null) {
            if (password == null) {
                throw new NullPointerException("No password supplied for PKCS#12 KeyStore.");
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(stream);
            bufferedInputStream.mark(10);
            if (bufferedInputStream.read() != 48) {
                throw new IOException("stream does not represent a PKCS12 key store");
            }
            bufferedInputStream.reset();
            ASN1InputStream aSN1InputStream = new ASN1InputStream((InputStream) bufferedInputStream);
            Pfx bag = Pfx.getInstance((ASN1Sequence) aSN1InputStream.readObject());
            ContentInfo info = bag.getAuthSafe();
            Vector chain = new Vector();
            boolean unmarkedKey = false;
            boolean wrongPKCS12Zero = false;
            if (bag.getMacData() != null) {
                MacData mData = bag.getMacData();
                DigestInfo dInfo = mData.getMac();
                AlgorithmIdentifier algId = dInfo.getAlgorithmId();
                byte[] salt = mData.getSalt();
                int itCount = mData.getIterationCount().intValue();
                byte[] data = ((ASN1OctetString) info.getContent()).getOctets();
                try {
                    byte[] res = calculatePbeMac(algId.getAlgorithm(), salt, itCount, password, false, data);
                    byte[] dig = dInfo.getDigest();
                    if (!Arrays.constantTimeAreEqual(res, dig)) {
                        if (password.length > 0) {
                            throw new IOException("PKCS12 key store mac invalid - wrong password or corrupted file.");
                        }
                        if (!Arrays.constantTimeAreEqual(calculatePbeMac(algId.getAlgorithm(), salt, itCount, password, true, data), dig)) {
                            throw new IOException("PKCS12 key store mac invalid - wrong password or corrupted file.");
                        }
                        wrongPKCS12Zero = true;
                    }
                } catch (IOException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new IOException("error constructing MAC: " + e2.toString());
                }
            }
            this.keys = new IgnoresCaseHashtable();
            this.localIds = new Hashtable();
            if (info.getContentType().equals(data)) {
                ASN1InputStream aSN1InputStream2 = new ASN1InputStream(((ASN1OctetString) info.getContent()).getOctets());
                ContentInfo[] c = AuthenticatedSafe.getInstance(aSN1InputStream2.readObject()).getContentInfo();
                for (int i = 0; i != c.length; i++) {
                    if (c[i].getContentType().equals(data)) {
                        ASN1InputStream aSN1InputStream3 = new ASN1InputStream(((ASN1OctetString) c[i].getContent()).getOctets());
                        ASN1Sequence seq = (ASN1Sequence) aSN1InputStream3.readObject();
                        for (int j = 0; j != seq.size(); j++) {
                            SafeBag b = SafeBag.getInstance(seq.getObjectAt(j));
                            if (b.getBagId().equals(pkcs8ShroudedKeyBag)) {
                                EncryptedPrivateKeyInfo eIn = EncryptedPrivateKeyInfo.getInstance(b.getBagValue());
                                PrivateKey privKey = unwrapKey(eIn.getEncryptionAlgorithm(), eIn.getEncryptedData(), password, wrongPKCS12Zero);
                                PKCS12BagAttributeCarrier bagAttr = (PKCS12BagAttributeCarrier) privKey;
                                String alias = null;
                                ASN1OctetString localId = null;
                                if (b.getBagAttributes() != null) {
                                    Enumeration e3 = b.getBagAttributes().getObjects();
                                    while (e3.hasMoreElements()) {
                                        ASN1Sequence sq = (ASN1Sequence) e3.nextElement();
                                        ASN1ObjectIdentifier aOid = (ASN1ObjectIdentifier) sq.getObjectAt(0);
                                        ASN1Set attrSet = (ASN1Set) sq.getObjectAt(1);
                                        ASN1Primitive attr = null;
                                        if (attrSet.size() > 0) {
                                            attr = (ASN1Primitive) attrSet.getObjectAt(0);
                                            ASN1Encodable existing = bagAttr.getBagAttribute(aOid);
                                            if (existing == null) {
                                                bagAttr.setBagAttribute(aOid, attr);
                                            } else if (!existing.toASN1Primitive().equals(attr)) {
                                                throw new IOException("attempt to add existing attribute with different value");
                                            }
                                        }
                                        if (aOid.equals(pkcs_9_at_friendlyName)) {
                                            alias = ((DERBMPString) attr).getString();
                                            this.keys.put(alias, privKey);
                                        } else {
                                            if (aOid.equals(pkcs_9_at_localKeyId)) {
                                                localId = (ASN1OctetString) attr;
                                            }
                                        }
                                    }
                                }
                                if (localId != null) {
                                    String str = new String(Hex.encode(localId.getOctets()));
                                    if (alias == null) {
                                        this.keys.put(str, privKey);
                                    } else {
                                        this.localIds.put(alias, str);
                                    }
                                } else {
                                    unmarkedKey = true;
                                    this.keys.put("unmarked", privKey);
                                }
                            } else if (b.getBagId().equals(certBag)) {
                                chain.addElement(b);
                            } else {
                                System.out.println("extra in data " + b.getBagId());
                                System.out.println(ASN1Dump.dumpAsString(b));
                            }
                        }
                        continue;
                    } else if (c[i].getContentType().equals(encryptedData)) {
                        EncryptedData d = EncryptedData.getInstance(c[i].getContent());
                        ASN1Sequence seq2 = (ASN1Sequence) ASN1Primitive.fromByteArray(cryptData(false, d.getEncryptionAlgorithm(), password, wrongPKCS12Zero, d.getContent().getOctets()));
                        for (int j2 = 0; j2 != seq2.size(); j2++) {
                            SafeBag b2 = SafeBag.getInstance(seq2.getObjectAt(j2));
                            if (b2.getBagId().equals(certBag)) {
                                chain.addElement(b2);
                            } else if (b2.getBagId().equals(pkcs8ShroudedKeyBag)) {
                                EncryptedPrivateKeyInfo eIn2 = EncryptedPrivateKeyInfo.getInstance(b2.getBagValue());
                                PrivateKey privKey2 = unwrapKey(eIn2.getEncryptionAlgorithm(), eIn2.getEncryptedData(), password, wrongPKCS12Zero);
                                PKCS12BagAttributeCarrier bagAttr2 = (PKCS12BagAttributeCarrier) privKey2;
                                String alias2 = null;
                                ASN1OctetString localId2 = null;
                                Enumeration e4 = b2.getBagAttributes().getObjects();
                                while (e4.hasMoreElements()) {
                                    ASN1Sequence sq2 = (ASN1Sequence) e4.nextElement();
                                    ASN1ObjectIdentifier aOid2 = (ASN1ObjectIdentifier) sq2.getObjectAt(0);
                                    ASN1Set attrSet2 = (ASN1Set) sq2.getObjectAt(1);
                                    ASN1Primitive attr2 = null;
                                    if (attrSet2.size() > 0) {
                                        attr2 = (ASN1Primitive) attrSet2.getObjectAt(0);
                                        ASN1Encodable existing2 = bagAttr2.getBagAttribute(aOid2);
                                        if (existing2 == null) {
                                            bagAttr2.setBagAttribute(aOid2, attr2);
                                        } else if (!existing2.toASN1Primitive().equals(attr2)) {
                                            throw new IOException("attempt to add existing attribute with different value");
                                        }
                                    }
                                    if (aOid2.equals(pkcs_9_at_friendlyName)) {
                                        alias2 = ((DERBMPString) attr2).getString();
                                        this.keys.put(alias2, privKey2);
                                    } else {
                                        if (aOid2.equals(pkcs_9_at_localKeyId)) {
                                            localId2 = (ASN1OctetString) attr2;
                                        }
                                    }
                                }
                                String str2 = new String(Hex.encode(localId2.getOctets()));
                                if (alias2 == null) {
                                    this.keys.put(str2, privKey2);
                                } else {
                                    this.localIds.put(alias2, str2);
                                }
                            } else if (b2.getBagId().equals(keyBag)) {
                                PrivateKey privKey3 = BouncyCastleProvider.getPrivateKey(PrivateKeyInfo.getInstance(b2.getBagValue()));
                                PKCS12BagAttributeCarrier bagAttr3 = (PKCS12BagAttributeCarrier) privKey3;
                                String alias3 = null;
                                ASN1OctetString localId3 = null;
                                Enumeration e5 = b2.getBagAttributes().getObjects();
                                while (e5.hasMoreElements()) {
                                    ASN1Sequence sq3 = ASN1Sequence.getInstance(e5.nextElement());
                                    ASN1ObjectIdentifier aOid3 = ASN1ObjectIdentifier.getInstance(sq3.getObjectAt(0));
                                    ASN1Set attrSet3 = ASN1Set.getInstance(sq3.getObjectAt(1));
                                    if (attrSet3.size() > 0) {
                                        ASN1Primitive attr3 = (ASN1Primitive) attrSet3.getObjectAt(0);
                                        ASN1Encodable existing3 = bagAttr3.getBagAttribute(aOid3);
                                        if (existing3 == null) {
                                            bagAttr3.setBagAttribute(aOid3, attr3);
                                        } else if (!existing3.toASN1Primitive().equals(attr3)) {
                                            throw new IOException("attempt to add existing attribute with different value");
                                        }
                                        if (aOid3.equals(pkcs_9_at_friendlyName)) {
                                            alias3 = ((DERBMPString) attr3).getString();
                                            this.keys.put(alias3, privKey3);
                                        } else {
                                            if (aOid3.equals(pkcs_9_at_localKeyId)) {
                                                localId3 = (ASN1OctetString) attr3;
                                            }
                                        }
                                    }
                                }
                                String str3 = new String(Hex.encode(localId3.getOctets()));
                                if (alias3 == null) {
                                    this.keys.put(str3, privKey3);
                                } else {
                                    this.localIds.put(alias3, str3);
                                }
                            } else {
                                System.out.println("extra in encryptedData " + b2.getBagId());
                                System.out.println(ASN1Dump.dumpAsString(b2));
                            }
                        }
                        continue;
                    } else {
                        System.out.println("extra " + c[i].getContentType().getId());
                        System.out.println("extra " + ASN1Dump.dumpAsString(c[i].getContent()));
                    }
                }
            }
            this.certs = new IgnoresCaseHashtable();
            this.chainCerts = new Hashtable();
            this.keyCerts = new Hashtable();
            int i2 = 0;
            while (i2 != chain.size()) {
                SafeBag b3 = (SafeBag) chain.elementAt(i2);
                CertBag cb = CertBag.getInstance(b3.getBagValue());
                if (!cb.getCertId().equals(x509Certificate)) {
                    throw new RuntimeException("Unsupported certificate type: " + cb.getCertId());
                }
                try {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(((ASN1OctetString) cb.getCertValue()).getOctets());
                    Certificate cert = this.certFact.generateCertificate(byteArrayInputStream);
                    ASN1OctetString localId4 = null;
                    String alias4 = null;
                    if (b3.getBagAttributes() != null) {
                        Enumeration e6 = b3.getBagAttributes().getObjects();
                        while (e6.hasMoreElements()) {
                            ASN1Sequence sq4 = ASN1Sequence.getInstance(e6.nextElement());
                            ASN1ObjectIdentifier oid = ASN1ObjectIdentifier.getInstance(sq4.getObjectAt(0));
                            ASN1Set attrSet4 = ASN1Set.getInstance(sq4.getObjectAt(1));
                            if (attrSet4.size() > 0) {
                                ASN1Primitive attr4 = (ASN1Primitive) attrSet4.getObjectAt(0);
                                if (cert instanceof PKCS12BagAttributeCarrier) {
                                    PKCS12BagAttributeCarrier bagAttr4 = (PKCS12BagAttributeCarrier) cert;
                                    ASN1Encodable existing4 = bagAttr4.getBagAttribute(oid);
                                    if (existing4 == null) {
                                        bagAttr4.setBagAttribute(oid, attr4);
                                    } else if (!existing4.toASN1Primitive().equals(attr4)) {
                                        throw new IOException("attempt to add existing attribute with different value");
                                    }
                                }
                                if (oid.equals(pkcs_9_at_friendlyName)) {
                                    alias4 = ((DERBMPString) attr4).getString();
                                } else {
                                    if (oid.equals(pkcs_9_at_localKeyId)) {
                                        localId4 = (ASN1OctetString) attr4;
                                    }
                                }
                            }
                        }
                    }
                    this.chainCerts.put(new CertId(cert.getPublicKey()), cert);
                    if (!unmarkedKey) {
                        if (localId4 != null) {
                            String str4 = new String(Hex.encode(localId4.getOctets()));
                            this.keyCerts.put(str4, cert);
                        }
                        if (alias4 != null) {
                            this.certs.put(alias4, cert);
                        }
                    } else if (this.keyCerts.isEmpty()) {
                        String str5 = new String(Hex.encode(createSubjectKeyId(cert.getPublicKey()).getKeyIdentifier()));
                        this.keyCerts.put(str5, cert);
                        this.keys.put(str5, this.keys.remove("unmarked"));
                    }
                    i2++;
                } catch (Exception e7) {
                    throw new RuntimeException(e7.toString());
                }
            }
        }
    }

    public void engineStore(LoadStoreParameter param) throws IOException, NoSuchAlgorithmException, CertificateException {
        PKCS12StoreParameter bcParam;
        char[] password;
        if (param == null) {
            throw new IllegalArgumentException("'param' arg cannot be null");
        } else if ((param instanceof PKCS12StoreParameter) || (param instanceof JDKPKCS12StoreParameter)) {
            if (param instanceof PKCS12StoreParameter) {
                bcParam = (PKCS12StoreParameter) param;
            } else {
                bcParam = new PKCS12StoreParameter(((JDKPKCS12StoreParameter) param).getOutputStream(), param.getProtectionParameter(), ((JDKPKCS12StoreParameter) param).isUseDEREncoding());
            }
            ProtectionParameter protParam = param.getProtectionParameter();
            if (protParam == null) {
                password = null;
            } else if (protParam instanceof PasswordProtection) {
                password = ((PasswordProtection) protParam).getPassword();
            } else {
                throw new IllegalArgumentException("No support for protection parameter of type " + protParam.getClass().getName());
            }
            doStore(bcParam.getOutputStream(), password, bcParam.isForDEREncoding());
        } else {
            throw new IllegalArgumentException("No support for 'param' of type " + param.getClass().getName());
        }
    }

    public void engineStore(OutputStream stream, char[] password) throws IOException {
        doStore(stream, password, false);
    }

    /* JADX WARNING: type inference failed for: r0v17, types: [org.spongycastle.asn1.BEROutputStream] */
    /* JADX WARNING: type inference failed for: r18v1 */
    /* JADX WARNING: type inference failed for: r0v18, types: [org.spongycastle.asn1.DEROutputStream] */
    /* JADX WARNING: type inference failed for: r0v26, types: [org.spongycastle.asn1.BEROutputStream] */
    /* JADX WARNING: type inference failed for: r18v3 */
    /* JADX WARNING: type inference failed for: r0v27, types: [org.spongycastle.asn1.DEROutputStream] */
    /* JADX WARNING: type inference failed for: r0v28, types: [org.spongycastle.asn1.DEROutputStream] */
    /* JADX WARNING: type inference failed for: r0v29, types: [org.spongycastle.asn1.DEROutputStream] */
    /* JADX WARNING: type inference failed for: r0v144, types: [org.spongycastle.asn1.BEROutputStream] */
    /* JADX WARNING: type inference failed for: r0v145, types: [org.spongycastle.asn1.BEROutputStream] */
    /* JADX WARNING: type inference failed for: r0v146, types: [org.spongycastle.asn1.DEROutputStream] */
    /* JADX WARNING: type inference failed for: r0v147, types: [org.spongycastle.asn1.DEROutputStream] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v144, types: [org.spongycastle.asn1.BEROutputStream]
      assigns: [org.spongycastle.asn1.BEROutputStream, org.spongycastle.asn1.DEROutputStream]
      uses: [org.spongycastle.asn1.BEROutputStream, ?[OBJECT, ARRAY], org.spongycastle.asn1.DEROutputStream]
      mth insns count: 534
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 8 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doStore(java.io.OutputStream r64, char[] r65, boolean r66) throws java.io.IOException {
        /*
            r63 = this;
            if (r65 != 0) goto L_0x000b
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            java.lang.String r5 = "No password supplied for PKCS#12 KeyStore."
            r4.<init>(r5)
            throw r4
        L_0x000b:
            org.spongycastle.asn1.ASN1EncodableVector r48 = new org.spongycastle.asn1.ASN1EncodableVector
            r48.<init>()
            r0 = r63
            org.spongycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r4 = r0.keys
            java.util.Enumeration r51 = r4.keys()
        L_0x0018:
            boolean r4 = r51.hasMoreElements()
            if (r4 == 0) goto L_0x018b
            r4 = 20
            byte[] r0 = new byte[r4]
            r46 = r0
            r0 = r63
            java.security.SecureRandom r4 = r0.random
            r0 = r46
            r4.nextBytes(r0)
            java.lang.Object r54 = r51.nextElement()
            java.lang.String r54 = (java.lang.String) r54
            r0 = r63
            org.spongycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r4 = r0.keys
            r0 = r54
            java.lang.Object r59 = r4.get(r0)
            java.security.PrivateKey r59 = (java.security.PrivateKey) r59
            org.spongycastle.asn1.pkcs.PKCS12PBEParams r45 = new org.spongycastle.asn1.pkcs.PKCS12PBEParams
            r4 = 1024(0x400, float:1.435E-42)
            r0 = r45
            r1 = r46
            r0.<init>(r1, r4)
            r0 = r63
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = r0.keyAlgorithm
            java.lang.String r4 = r4.getId()
            r0 = r63
            r1 = r59
            r2 = r45
            r3 = r65
            byte[] r42 = r0.wrapKey(r4, r1, r2, r3)
            org.spongycastle.asn1.x509.AlgorithmIdentifier r40 = new org.spongycastle.asn1.x509.AlgorithmIdentifier
            r0 = r63
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = r0.keyAlgorithm
            org.spongycastle.asn1.ASN1Primitive r5 = r45.toASN1Primitive()
            r0 = r40
            r0.<init>(r4, r5)
            org.spongycastle.asn1.pkcs.EncryptedPrivateKeyInfo r43 = new org.spongycastle.asn1.pkcs.EncryptedPrivateKeyInfo
            r0 = r43
            r1 = r40
            r2 = r42
            r0.<init>(r1, r2)
            r19 = 0
            org.spongycastle.asn1.ASN1EncodableVector r44 = new org.spongycastle.asn1.ASN1EncodableVector
            r44.<init>()
            r0 = r59
            boolean r4 = r0 instanceof org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier
            if (r4 == 0) goto L_0x010f
            r22 = r59
            org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier r22 = (org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier) r22
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_friendlyName
            r0 = r22
            org.spongycastle.asn1.ASN1Encodable r55 = r0.getBagAttribute(r4)
            org.spongycastle.asn1.DERBMPString r55 = (org.spongycastle.asn1.DERBMPString) r55
            if (r55 == 0) goto L_0x00a1
            java.lang.String r4 = r55.getString()
            r0 = r54
            boolean r4 = r4.equals(r0)
            if (r4 != 0) goto L_0x00af
        L_0x00a1:
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_friendlyName
            org.spongycastle.asn1.DERBMPString r5 = new org.spongycastle.asn1.DERBMPString
            r0 = r54
            r5.<init>(r0)
            r0 = r22
            r0.setBagAttribute(r4, r5)
        L_0x00af:
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_localKeyId
            r0 = r22
            org.spongycastle.asn1.ASN1Encodable r4 = r0.getBagAttribute(r4)
            if (r4 != 0) goto L_0x00d2
            r0 = r63
            r1 = r54
            java.security.cert.Certificate r33 = r0.engineGetCertificate(r1)
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_localKeyId
            java.security.PublicKey r5 = r33.getPublicKey()
            r0 = r63
            org.spongycastle.asn1.x509.SubjectKeyIdentifier r5 = r0.createSubjectKeyId(r5)
            r0 = r22
            r0.setBagAttribute(r4, r5)
        L_0x00d2:
            java.util.Enumeration r36 = r22.getBagAttributeKeys()
        L_0x00d6:
            boolean r4 = r36.hasMoreElements()
            if (r4 == 0) goto L_0x010f
            java.lang.Object r56 = r36.nextElement()
            org.spongycastle.asn1.ASN1ObjectIdentifier r56 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r56
            org.spongycastle.asn1.ASN1EncodableVector r47 = new org.spongycastle.asn1.ASN1EncodableVector
            r47.<init>()
            r0 = r47
            r1 = r56
            r0.add(r1)
            org.spongycastle.asn1.DERSet r4 = new org.spongycastle.asn1.DERSet
            r0 = r22
            r1 = r56
            org.spongycastle.asn1.ASN1Encodable r5 = r0.getBagAttribute(r1)
            r4.<init>(r5)
            r0 = r47
            r0.add(r4)
            r19 = 1
            org.spongycastle.asn1.DERSequence r4 = new org.spongycastle.asn1.DERSequence
            r0 = r47
            r4.<init>(r0)
            r0 = r44
            r0.add(r4)
            goto L_0x00d6
        L_0x010f:
            if (r19 != 0) goto L_0x016e
            org.spongycastle.asn1.ASN1EncodableVector r47 = new org.spongycastle.asn1.ASN1EncodableVector
            r47.<init>()
            r0 = r63
            r1 = r54
            java.security.cert.Certificate r33 = r0.engineGetCertificate(r1)
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_localKeyId
            r0 = r47
            r0.add(r4)
            org.spongycastle.asn1.DERSet r4 = new org.spongycastle.asn1.DERSet
            java.security.PublicKey r5 = r33.getPublicKey()
            r0 = r63
            org.spongycastle.asn1.x509.SubjectKeyIdentifier r5 = r0.createSubjectKeyId(r5)
            r4.<init>(r5)
            r0 = r47
            r0.add(r4)
            org.spongycastle.asn1.DERSequence r4 = new org.spongycastle.asn1.DERSequence
            r0 = r47
            r4.<init>(r0)
            r0 = r44
            r0.add(r4)
            org.spongycastle.asn1.ASN1EncodableVector r47 = new org.spongycastle.asn1.ASN1EncodableVector
            r47.<init>()
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_friendlyName
            r0 = r47
            r0.add(r4)
            org.spongycastle.asn1.DERSet r4 = new org.spongycastle.asn1.DERSet
            org.spongycastle.asn1.DERBMPString r5 = new org.spongycastle.asn1.DERBMPString
            r0 = r54
            r5.<init>(r0)
            r4.<init>(r5)
            r0 = r47
            r0.add(r4)
            org.spongycastle.asn1.DERSequence r4 = new org.spongycastle.asn1.DERSequence
            r0 = r47
            r4.<init>(r0)
            r0 = r44
            r0.add(r4)
        L_0x016e:
            org.spongycastle.asn1.pkcs.SafeBag r41 = new org.spongycastle.asn1.pkcs.SafeBag
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = pkcs8ShroudedKeyBag
            org.spongycastle.asn1.ASN1Primitive r5 = r43.toASN1Primitive()
            org.spongycastle.asn1.DERSet r7 = new org.spongycastle.asn1.DERSet
            r0 = r44
            r7.<init>(r0)
            r0 = r41
            r0.<init>(r4, r5, r7)
            r0 = r48
            r1 = r41
            r0.add(r1)
            goto L_0x0018
        L_0x018b:
            org.spongycastle.asn1.DERSequence r4 = new org.spongycastle.asn1.DERSequence
            r0 = r48
            r4.<init>(r0)
            java.lang.String r5 = "DER"
            byte[] r49 = r4.getEncoded(r5)
            org.spongycastle.asn1.BEROctetString r50 = new org.spongycastle.asn1.BEROctetString
            r0 = r50
            r1 = r49
            r0.<init>(r1)
            r4 = 20
            byte[] r0 = new byte[r4]
            r27 = r0
            r0 = r63
            java.security.SecureRandom r4 = r0.random
            r0 = r27
            r4.nextBytes(r0)
            org.spongycastle.asn1.ASN1EncodableVector r31 = new org.spongycastle.asn1.ASN1EncodableVector
            r31.<init>()
            org.spongycastle.asn1.pkcs.PKCS12PBEParams r26 = new org.spongycastle.asn1.pkcs.PKCS12PBEParams
            r4 = 1024(0x400, float:1.435E-42)
            r0 = r26
            r1 = r27
            r0.<init>(r1, r4)
            org.spongycastle.asn1.x509.AlgorithmIdentifier r6 = new org.spongycastle.asn1.x509.AlgorithmIdentifier
            r0 = r63
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = r0.certAlgorithm
            org.spongycastle.asn1.ASN1Primitive r5 = r26.toASN1Primitive()
            r6.<init>(r4, r5)
            java.util.Hashtable r35 = new java.util.Hashtable
            r35.<init>()
            r0 = r63
            org.spongycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r4 = r0.keys
            java.util.Enumeration r32 = r4.keys()
        L_0x01db:
            boolean r4 = r32.hasMoreElements()
            if (r4 == 0) goto L_0x032e
            java.lang.Object r54 = r32.nextElement()     // Catch:{ CertificateEncodingException -> 0x030f }
            java.lang.String r54 = (java.lang.String) r54     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r63
            r1 = r54
            java.security.cert.Certificate r28 = r0.engineGetCertificate(r1)     // Catch:{ CertificateEncodingException -> 0x030f }
            r23 = 0
            org.spongycastle.asn1.pkcs.CertBag r24 = new org.spongycastle.asn1.pkcs.CertBag     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = x509Certificate     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.DEROctetString r5 = new org.spongycastle.asn1.DEROctetString     // Catch:{ CertificateEncodingException -> 0x030f }
            byte[] r7 = r28.getEncoded()     // Catch:{ CertificateEncodingException -> 0x030f }
            r5.<init>(r7)     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r24
            r0.<init>(r4, r5)     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.ASN1EncodableVector r37 = new org.spongycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x030f }
            r37.<init>()     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r28
            boolean r4 = r0 instanceof org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier     // Catch:{ CertificateEncodingException -> 0x030f }
            if (r4 == 0) goto L_0x0292
            r0 = r28
            org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier r0 = (org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier) r0     // Catch:{ CertificateEncodingException -> 0x030f }
            r22 = r0
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_friendlyName     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r22
            org.spongycastle.asn1.ASN1Encodable r55 = r0.getBagAttribute(r4)     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.DERBMPString r55 = (org.spongycastle.asn1.DERBMPString) r55     // Catch:{ CertificateEncodingException -> 0x030f }
            if (r55 == 0) goto L_0x022c
            java.lang.String r4 = r55.getString()     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r54
            boolean r4 = r4.equals(r0)     // Catch:{ CertificateEncodingException -> 0x030f }
            if (r4 != 0) goto L_0x023a
        L_0x022c:
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_friendlyName     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.DERBMPString r5 = new org.spongycastle.asn1.DERBMPString     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r54
            r5.<init>(r0)     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r22
            r0.setBagAttribute(r4, r5)     // Catch:{ CertificateEncodingException -> 0x030f }
        L_0x023a:
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_localKeyId     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r22
            org.spongycastle.asn1.ASN1Encodable r4 = r0.getBagAttribute(r4)     // Catch:{ CertificateEncodingException -> 0x030f }
            if (r4 != 0) goto L_0x0255
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_localKeyId     // Catch:{ CertificateEncodingException -> 0x030f }
            java.security.PublicKey r5 = r28.getPublicKey()     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r63
            org.spongycastle.asn1.x509.SubjectKeyIdentifier r5 = r0.createSubjectKeyId(r5)     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r22
            r0.setBagAttribute(r4, r5)     // Catch:{ CertificateEncodingException -> 0x030f }
        L_0x0255:
            java.util.Enumeration r36 = r22.getBagAttributeKeys()     // Catch:{ CertificateEncodingException -> 0x030f }
        L_0x0259:
            boolean r4 = r36.hasMoreElements()     // Catch:{ CertificateEncodingException -> 0x030f }
            if (r4 == 0) goto L_0x0292
            java.lang.Object r56 = r36.nextElement()     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.ASN1ObjectIdentifier r56 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r56     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.ASN1EncodableVector r38 = new org.spongycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x030f }
            r38.<init>()     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r38
            r1 = r56
            r0.add(r1)     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.DERSet r4 = new org.spongycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r22
            r1 = r56
            org.spongycastle.asn1.ASN1Encodable r5 = r0.getBagAttribute(r1)     // Catch:{ CertificateEncodingException -> 0x030f }
            r4.<init>(r5)     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r38
            r0.add(r4)     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.DERSequence r4 = new org.spongycastle.asn1.DERSequence     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r38
            r4.<init>(r0)     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r37
            r0.add(r4)     // Catch:{ CertificateEncodingException -> 0x030f }
            r23 = 1
            goto L_0x0259
        L_0x0292:
            if (r23 != 0) goto L_0x02e9
            org.spongycastle.asn1.ASN1EncodableVector r38 = new org.spongycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x030f }
            r38.<init>()     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_localKeyId     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r38
            r0.add(r4)     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.DERSet r4 = new org.spongycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x030f }
            java.security.PublicKey r5 = r28.getPublicKey()     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r63
            org.spongycastle.asn1.x509.SubjectKeyIdentifier r5 = r0.createSubjectKeyId(r5)     // Catch:{ CertificateEncodingException -> 0x030f }
            r4.<init>(r5)     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r38
            r0.add(r4)     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.DERSequence r4 = new org.spongycastle.asn1.DERSequence     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r38
            r4.<init>(r0)     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r37
            r0.add(r4)     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.ASN1EncodableVector r38 = new org.spongycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x030f }
            r38.<init>()     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_friendlyName     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r38
            r0.add(r4)     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.DERSet r4 = new org.spongycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.DERBMPString r5 = new org.spongycastle.asn1.DERBMPString     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r54
            r5.<init>(r0)     // Catch:{ CertificateEncodingException -> 0x030f }
            r4.<init>(r5)     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r38
            r0.add(r4)     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.DERSequence r4 = new org.spongycastle.asn1.DERSequence     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r38
            r4.<init>(r0)     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r37
            r0.add(r4)     // Catch:{ CertificateEncodingException -> 0x030f }
        L_0x02e9:
            org.spongycastle.asn1.pkcs.SafeBag r61 = new org.spongycastle.asn1.pkcs.SafeBag     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = certBag     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.ASN1Primitive r5 = r24.toASN1Primitive()     // Catch:{ CertificateEncodingException -> 0x030f }
            org.spongycastle.asn1.DERSet r7 = new org.spongycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r37
            r7.<init>(r0)     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r61
            r0.<init>(r4, r5, r7)     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r31
            r1 = r61
            r0.add(r1)     // Catch:{ CertificateEncodingException -> 0x030f }
            r0 = r35
            r1 = r28
            r2 = r28
            r0.put(r1, r2)     // Catch:{ CertificateEncodingException -> 0x030f }
            goto L_0x01db
        L_0x030f:
            r36 = move-exception
            java.io.IOException r4 = new java.io.IOException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "Error encoding certificate: "
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r7 = r36.toString()
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x032e:
            r0 = r63
            org.spongycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r4 = r0.certs
            java.util.Enumeration r32 = r4.keys()
        L_0x0336:
            boolean r4 = r32.hasMoreElements()
            if (r4 == 0) goto L_0x045c
            java.lang.Object r30 = r32.nextElement()     // Catch:{ CertificateEncodingException -> 0x043d }
            java.lang.String r30 = (java.lang.String) r30     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r63
            org.spongycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r4 = r0.certs     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r30
            java.lang.Object r28 = r4.get(r0)     // Catch:{ CertificateEncodingException -> 0x043d }
            java.security.cert.Certificate r28 = (java.security.cert.Certificate) r28     // Catch:{ CertificateEncodingException -> 0x043d }
            r23 = 0
            r0 = r63
            org.spongycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$IgnoresCaseHashtable r4 = r0.keys     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r30
            java.lang.Object r4 = r4.get(r0)     // Catch:{ CertificateEncodingException -> 0x043d }
            if (r4 != 0) goto L_0x0336
            org.spongycastle.asn1.pkcs.CertBag r24 = new org.spongycastle.asn1.pkcs.CertBag     // Catch:{ CertificateEncodingException -> 0x043d }
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = x509Certificate     // Catch:{ CertificateEncodingException -> 0x043d }
            org.spongycastle.asn1.DEROctetString r5 = new org.spongycastle.asn1.DEROctetString     // Catch:{ CertificateEncodingException -> 0x043d }
            byte[] r7 = r28.getEncoded()     // Catch:{ CertificateEncodingException -> 0x043d }
            r5.<init>(r7)     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r24
            r0.<init>(r4, r5)     // Catch:{ CertificateEncodingException -> 0x043d }
            org.spongycastle.asn1.ASN1EncodableVector r37 = new org.spongycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x043d }
            r37.<init>()     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r28
            boolean r4 = r0 instanceof org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier     // Catch:{ CertificateEncodingException -> 0x043d }
            if (r4 == 0) goto L_0x03ec
            r0 = r28
            org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier r0 = (org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier) r0     // Catch:{ CertificateEncodingException -> 0x043d }
            r22 = r0
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_friendlyName     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r22
            org.spongycastle.asn1.ASN1Encodable r55 = r0.getBagAttribute(r4)     // Catch:{ CertificateEncodingException -> 0x043d }
            org.spongycastle.asn1.DERBMPString r55 = (org.spongycastle.asn1.DERBMPString) r55     // Catch:{ CertificateEncodingException -> 0x043d }
            if (r55 == 0) goto L_0x0397
            java.lang.String r4 = r55.getString()     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r30
            boolean r4 = r4.equals(r0)     // Catch:{ CertificateEncodingException -> 0x043d }
            if (r4 != 0) goto L_0x03a5
        L_0x0397:
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_friendlyName     // Catch:{ CertificateEncodingException -> 0x043d }
            org.spongycastle.asn1.DERBMPString r5 = new org.spongycastle.asn1.DERBMPString     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r30
            r5.<init>(r0)     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r22
            r0.setBagAttribute(r4, r5)     // Catch:{ CertificateEncodingException -> 0x043d }
        L_0x03a5:
            java.util.Enumeration r36 = r22.getBagAttributeKeys()     // Catch:{ CertificateEncodingException -> 0x043d }
        L_0x03a9:
            boolean r4 = r36.hasMoreElements()     // Catch:{ CertificateEncodingException -> 0x043d }
            if (r4 == 0) goto L_0x03ec
            java.lang.Object r56 = r36.nextElement()     // Catch:{ CertificateEncodingException -> 0x043d }
            org.spongycastle.asn1.ASN1ObjectIdentifier r56 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r56     // Catch:{ CertificateEncodingException -> 0x043d }
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers.pkcs_9_at_localKeyId     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r56
            boolean r4 = r0.equals(r4)     // Catch:{ CertificateEncodingException -> 0x043d }
            if (r4 != 0) goto L_0x03a9
            org.spongycastle.asn1.ASN1EncodableVector r38 = new org.spongycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x043d }
            r38.<init>()     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r38
            r1 = r56
            r0.add(r1)     // Catch:{ CertificateEncodingException -> 0x043d }
            org.spongycastle.asn1.DERSet r4 = new org.spongycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r22
            r1 = r56
            org.spongycastle.asn1.ASN1Encodable r5 = r0.getBagAttribute(r1)     // Catch:{ CertificateEncodingException -> 0x043d }
            r4.<init>(r5)     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r38
            r0.add(r4)     // Catch:{ CertificateEncodingException -> 0x043d }
            org.spongycastle.asn1.DERSequence r4 = new org.spongycastle.asn1.DERSequence     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r38
            r4.<init>(r0)     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r37
            r0.add(r4)     // Catch:{ CertificateEncodingException -> 0x043d }
            r23 = 1
            goto L_0x03a9
        L_0x03ec:
            if (r23 != 0) goto L_0x0417
            org.spongycastle.asn1.ASN1EncodableVector r38 = new org.spongycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x043d }
            r38.<init>()     // Catch:{ CertificateEncodingException -> 0x043d }
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = pkcs_9_at_friendlyName     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r38
            r0.add(r4)     // Catch:{ CertificateEncodingException -> 0x043d }
            org.spongycastle.asn1.DERSet r4 = new org.spongycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x043d }
            org.spongycastle.asn1.DERBMPString r5 = new org.spongycastle.asn1.DERBMPString     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r30
            r5.<init>(r0)     // Catch:{ CertificateEncodingException -> 0x043d }
            r4.<init>(r5)     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r38
            r0.add(r4)     // Catch:{ CertificateEncodingException -> 0x043d }
            org.spongycastle.asn1.DERSequence r4 = new org.spongycastle.asn1.DERSequence     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r38
            r4.<init>(r0)     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r37
            r0.add(r4)     // Catch:{ CertificateEncodingException -> 0x043d }
        L_0x0417:
            org.spongycastle.asn1.pkcs.SafeBag r61 = new org.spongycastle.asn1.pkcs.SafeBag     // Catch:{ CertificateEncodingException -> 0x043d }
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = certBag     // Catch:{ CertificateEncodingException -> 0x043d }
            org.spongycastle.asn1.ASN1Primitive r5 = r24.toASN1Primitive()     // Catch:{ CertificateEncodingException -> 0x043d }
            org.spongycastle.asn1.DERSet r7 = new org.spongycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r37
            r7.<init>(r0)     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r61
            r0.<init>(r4, r5, r7)     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r31
            r1 = r61
            r0.add(r1)     // Catch:{ CertificateEncodingException -> 0x043d }
            r0 = r35
            r1 = r28
            r2 = r28
            r0.put(r1, r2)     // Catch:{ CertificateEncodingException -> 0x043d }
            goto L_0x0336
        L_0x043d:
            r36 = move-exception
            java.io.IOException r4 = new java.io.IOException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "Error encoding certificate: "
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r7 = r36.toString()
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x045c:
            java.util.Set r62 = r63.getUsedCertificateSet()
            r0 = r63
            java.util.Hashtable r4 = r0.chainCerts
            java.util.Enumeration r32 = r4.keys()
        L_0x0468:
            boolean r4 = r32.hasMoreElements()
            if (r4 == 0) goto L_0x0538
            java.lang.Object r30 = r32.nextElement()     // Catch:{ CertificateEncodingException -> 0x04fc }
            org.spongycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi$CertId r30 = (org.spongycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.CertId) r30     // Catch:{ CertificateEncodingException -> 0x04fc }
            r0 = r63
            java.util.Hashtable r4 = r0.chainCerts     // Catch:{ CertificateEncodingException -> 0x04fc }
            r0 = r30
            java.lang.Object r28 = r4.get(r0)     // Catch:{ CertificateEncodingException -> 0x04fc }
            java.security.cert.Certificate r28 = (java.security.cert.Certificate) r28     // Catch:{ CertificateEncodingException -> 0x04fc }
            r0 = r62
            r1 = r28
            boolean r4 = r0.contains(r1)     // Catch:{ CertificateEncodingException -> 0x04fc }
            if (r4 == 0) goto L_0x0468
            r0 = r35
            r1 = r28
            java.lang.Object r4 = r0.get(r1)     // Catch:{ CertificateEncodingException -> 0x04fc }
            if (r4 != 0) goto L_0x0468
            org.spongycastle.asn1.pkcs.CertBag r24 = new org.spongycastle.asn1.pkcs.CertBag     // Catch:{ CertificateEncodingException -> 0x04fc }
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = x509Certificate     // Catch:{ CertificateEncodingException -> 0x04fc }
            org.spongycastle.asn1.DEROctetString r5 = new org.spongycastle.asn1.DEROctetString     // Catch:{ CertificateEncodingException -> 0x04fc }
            byte[] r7 = r28.getEncoded()     // Catch:{ CertificateEncodingException -> 0x04fc }
            r5.<init>(r7)     // Catch:{ CertificateEncodingException -> 0x04fc }
            r0 = r24
            r0.<init>(r4, r5)     // Catch:{ CertificateEncodingException -> 0x04fc }
            org.spongycastle.asn1.ASN1EncodableVector r37 = new org.spongycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x04fc }
            r37.<init>()     // Catch:{ CertificateEncodingException -> 0x04fc }
            r0 = r28
            boolean r4 = r0 instanceof org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier     // Catch:{ CertificateEncodingException -> 0x04fc }
            if (r4 == 0) goto L_0x051b
            r0 = r28
            org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier r0 = (org.spongycastle.jce.interfaces.PKCS12BagAttributeCarrier) r0     // Catch:{ CertificateEncodingException -> 0x04fc }
            r22 = r0
            java.util.Enumeration r36 = r22.getBagAttributeKeys()     // Catch:{ CertificateEncodingException -> 0x04fc }
        L_0x04bb:
            boolean r4 = r36.hasMoreElements()     // Catch:{ CertificateEncodingException -> 0x04fc }
            if (r4 == 0) goto L_0x051b
            java.lang.Object r56 = r36.nextElement()     // Catch:{ CertificateEncodingException -> 0x04fc }
            org.spongycastle.asn1.ASN1ObjectIdentifier r56 = (org.spongycastle.asn1.ASN1ObjectIdentifier) r56     // Catch:{ CertificateEncodingException -> 0x04fc }
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers.pkcs_9_at_localKeyId     // Catch:{ CertificateEncodingException -> 0x04fc }
            r0 = r56
            boolean r4 = r0.equals(r4)     // Catch:{ CertificateEncodingException -> 0x04fc }
            if (r4 != 0) goto L_0x04bb
            org.spongycastle.asn1.ASN1EncodableVector r38 = new org.spongycastle.asn1.ASN1EncodableVector     // Catch:{ CertificateEncodingException -> 0x04fc }
            r38.<init>()     // Catch:{ CertificateEncodingException -> 0x04fc }
            r0 = r38
            r1 = r56
            r0.add(r1)     // Catch:{ CertificateEncodingException -> 0x04fc }
            org.spongycastle.asn1.DERSet r4 = new org.spongycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x04fc }
            r0 = r22
            r1 = r56
            org.spongycastle.asn1.ASN1Encodable r5 = r0.getBagAttribute(r1)     // Catch:{ CertificateEncodingException -> 0x04fc }
            r4.<init>(r5)     // Catch:{ CertificateEncodingException -> 0x04fc }
            r0 = r38
            r0.add(r4)     // Catch:{ CertificateEncodingException -> 0x04fc }
            org.spongycastle.asn1.DERSequence r4 = new org.spongycastle.asn1.DERSequence     // Catch:{ CertificateEncodingException -> 0x04fc }
            r0 = r38
            r4.<init>(r0)     // Catch:{ CertificateEncodingException -> 0x04fc }
            r0 = r37
            r0.add(r4)     // Catch:{ CertificateEncodingException -> 0x04fc }
            goto L_0x04bb
        L_0x04fc:
            r36 = move-exception
            java.io.IOException r4 = new java.io.IOException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "Error encoding certificate: "
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r7 = r36.toString()
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x051b:
            org.spongycastle.asn1.pkcs.SafeBag r61 = new org.spongycastle.asn1.pkcs.SafeBag     // Catch:{ CertificateEncodingException -> 0x04fc }
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = certBag     // Catch:{ CertificateEncodingException -> 0x04fc }
            org.spongycastle.asn1.ASN1Primitive r5 = r24.toASN1Primitive()     // Catch:{ CertificateEncodingException -> 0x04fc }
            org.spongycastle.asn1.DERSet r7 = new org.spongycastle.asn1.DERSet     // Catch:{ CertificateEncodingException -> 0x04fc }
            r0 = r37
            r7.<init>(r0)     // Catch:{ CertificateEncodingException -> 0x04fc }
            r0 = r61
            r0.<init>(r4, r5, r7)     // Catch:{ CertificateEncodingException -> 0x04fc }
            r0 = r31
            r1 = r61
            r0.add(r1)     // Catch:{ CertificateEncodingException -> 0x04fc }
            goto L_0x0468
        L_0x0538:
            org.spongycastle.asn1.DERSequence r4 = new org.spongycastle.asn1.DERSequence
            r0 = r31
            r4.<init>(r0)
            java.lang.String r5 = "DER"
            byte[] r9 = r4.getEncoded(r5)
            r5 = 1
            r8 = 0
            r4 = r63
            r7 = r65
            byte[] r29 = r4.cryptData(r5, r6, r7, r8, r9)
            org.spongycastle.asn1.pkcs.EncryptedData r25 = new org.spongycastle.asn1.pkcs.EncryptedData
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = data
            org.spongycastle.asn1.BEROctetString r5 = new org.spongycastle.asn1.BEROctetString
            r0 = r29
            r5.<init>(r0)
            r0 = r25
            r0.<init>(r4, r6, r5)
            r4 = 2
            org.spongycastle.asn1.pkcs.ContentInfo[] r0 = new org.spongycastle.asn1.pkcs.ContentInfo[r4]
            r39 = r0
            r4 = 0
            org.spongycastle.asn1.pkcs.ContentInfo r5 = new org.spongycastle.asn1.pkcs.ContentInfo
            org.spongycastle.asn1.ASN1ObjectIdentifier r7 = data
            r0 = r50
            r5.<init>(r7, r0)
            r39[r4] = r5
            r4 = 1
            org.spongycastle.asn1.pkcs.ContentInfo r5 = new org.spongycastle.asn1.pkcs.ContentInfo
            org.spongycastle.asn1.ASN1ObjectIdentifier r7 = encryptedData
            org.spongycastle.asn1.ASN1Primitive r8 = r25.toASN1Primitive()
            r5.<init>(r7, r8)
            r39[r4] = r5
            org.spongycastle.asn1.pkcs.AuthenticatedSafe r20 = new org.spongycastle.asn1.pkcs.AuthenticatedSafe
            r0 = r20
            r1 = r39
            r0.<init>(r1)
            java.io.ByteArrayOutputStream r21 = new java.io.ByteArrayOutputStream
            r21.<init>()
            if (r66 == 0) goto L_0x0612
            org.spongycastle.asn1.DEROutputStream r18 = new org.spongycastle.asn1.DEROutputStream
            r0 = r18
            r1 = r21
            r0.<init>(r1)
        L_0x0598:
            r0 = r18
            r1 = r20
            r0.writeObject(r1)
            byte[] r58 = r21.toByteArray()
            org.spongycastle.asn1.pkcs.ContentInfo r53 = new org.spongycastle.asn1.pkcs.ContentInfo
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = data
            org.spongycastle.asn1.BEROctetString r5 = new org.spongycastle.asn1.BEROctetString
            r0 = r58
            r5.<init>(r0)
            r0 = r53
            r0.<init>(r4, r5)
            r4 = 20
            byte[] r12 = new byte[r4]
            r13 = 1024(0x400, float:1.435E-42)
            r0 = r63
            java.security.SecureRandom r4 = r0.random
            r4.nextBytes(r12)
            org.spongycastle.asn1.ASN1Encodable r4 = r53.getContent()
            org.spongycastle.asn1.ASN1OctetString r4 = (org.spongycastle.asn1.ASN1OctetString) r4
            byte[] r16 = r4.getOctets()
            org.spongycastle.asn1.ASN1ObjectIdentifier r11 = id_SHA1     // Catch:{ Exception -> 0x061d }
            r15 = 0
            r10 = r63
            r14 = r65
            byte[] r60 = r10.calculatePbeMac(r11, r12, r13, r14, r15, r16)     // Catch:{ Exception -> 0x061d }
            org.spongycastle.asn1.x509.AlgorithmIdentifier r17 = new org.spongycastle.asn1.x509.AlgorithmIdentifier     // Catch:{ Exception -> 0x061d }
            org.spongycastle.asn1.ASN1ObjectIdentifier r4 = id_SHA1     // Catch:{ Exception -> 0x061d }
            org.spongycastle.asn1.DERNull r5 = org.spongycastle.asn1.DERNull.INSTANCE     // Catch:{ Exception -> 0x061d }
            r0 = r17
            r0.<init>(r4, r5)     // Catch:{ Exception -> 0x061d }
            org.spongycastle.asn1.x509.DigestInfo r34 = new org.spongycastle.asn1.x509.DigestInfo     // Catch:{ Exception -> 0x061d }
            r0 = r34
            r1 = r17
            r2 = r60
            r0.<init>(r1, r2)     // Catch:{ Exception -> 0x061d }
            org.spongycastle.asn1.pkcs.MacData r52 = new org.spongycastle.asn1.pkcs.MacData     // Catch:{ Exception -> 0x061d }
            r0 = r52
            r1 = r34
            r0.<init>(r1, r12, r13)     // Catch:{ Exception -> 0x061d }
            org.spongycastle.asn1.pkcs.Pfx r57 = new org.spongycastle.asn1.pkcs.Pfx
            r0 = r57
            r1 = r53
            r2 = r52
            r0.<init>(r1, r2)
            if (r66 == 0) goto L_0x063c
            org.spongycastle.asn1.DEROutputStream r18 = new org.spongycastle.asn1.DEROutputStream
            r0 = r18
            r1 = r64
            r0.<init>(r1)
        L_0x060a:
            r0 = r18
            r1 = r57
            r0.writeObject(r1)
            return
        L_0x0612:
            org.spongycastle.asn1.BEROutputStream r18 = new org.spongycastle.asn1.BEROutputStream
            r0 = r18
            r1 = r21
            r0.<init>(r1)
            goto L_0x0598
        L_0x061d:
            r36 = move-exception
            java.io.IOException r4 = new java.io.IOException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "error constructing MAC: "
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r7 = r36.toString()
            java.lang.StringBuilder r5 = r5.append(r7)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x063c:
            org.spongycastle.asn1.BEROutputStream r18 = new org.spongycastle.asn1.BEROutputStream
            r0 = r18
            r1 = r64
            r0.<init>(r1)
            goto L_0x060a
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.jcajce.provider.keystore.pkcs12.PKCS12KeyStoreSpi.doStore(java.io.OutputStream, char[], boolean):void");
    }

    private Set getUsedCertificateSet() {
        Set usedSet = new HashSet();
        Enumeration en = this.keys.keys();
        while (en.hasMoreElements()) {
            Certificate[] certs2 = engineGetCertificateChain((String) en.nextElement());
            for (int i = 0; i != certs2.length; i++) {
                usedSet.add(certs2[i]);
            }
        }
        Enumeration en2 = this.certs.keys();
        while (en2.hasMoreElements()) {
            usedSet.add(engineGetCertificate((String) en2.nextElement()));
        }
        return usedSet;
    }

    private byte[] calculatePbeMac(ASN1ObjectIdentifier oid, byte[] salt, int itCount, char[] password, boolean wrongPkcs12Zero, byte[] data) throws Exception {
        PBEParameterSpec defParams = new PBEParameterSpec(salt, itCount);
        Mac mac = this.helper.createMac(oid.getId());
        mac.init(new PKCS12Key(password, wrongPkcs12Zero), defParams);
        mac.update(data);
        return mac.doFinal();
    }
}
