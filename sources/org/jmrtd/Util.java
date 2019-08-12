package org.jmrtd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.Provider;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.ECPublicKeySpec;
import java.security.spec.EllipticCurve;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.DHPublicKeySpec;
import javax.crypto.spec.SecretKeySpec;
import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.tlv.TLVOutputStream;
import net.p318sf.scuba.util.Hex;
import org.jmrtd.cbeff.ISO781611;
import org.jmrtd.lds.MRZInfo;
import org.jmrtd.lds.PACEInfo;
import org.jmrtd.lds.SecurityInfo;
import org.spongycastle.asn1.ASN1Encodable;
import org.spongycastle.asn1.ASN1InputStream;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Primitive;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.eac.EACObjectIdentifiers;
import org.spongycastle.asn1.p325x9.X962NamedCurves;
import org.spongycastle.asn1.p325x9.X9ECParameters;
import org.spongycastle.asn1.pkcs.DHParameter;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.signers.PSSSigner;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.jce.ECNamedCurveTable;
import org.spongycastle.jce.spec.ECNamedCurveParameterSpec;
import org.spongycastle.jce.spec.ECNamedCurveSpec;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECCurve.C5397Fp;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.pqc.jcajce.spec.McElieceCCA2ParameterSpec;
import p005cn.jpush.android.JPushConstants;

public class Util {
    private static final Provider BC_PROVIDER = JMRTDSecurityProvider.getBouncyCastleProvider();
    public static final int ENC_MODE = 1;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    public static final int MAC_MODE = 2;
    public static final int PACE_MODE = 3;

    private Util() {
    }

    public static SecretKey deriveKey(byte[] bArr, int i) throws GeneralSecurityException {
        return deriveKey(bArr, "DESede", 128, i);
    }

    public static SecretKey deriveKey(byte[] bArr, String str, int i, int i2) throws GeneralSecurityException {
        return deriveKey(bArr, str, i, null, i2);
    }

    public static SecretKey deriveKey(byte[] bArr, String str, int i, byte[] bArr2, int i2) throws GeneralSecurityException {
        String inferDigestAlgorithmFromCipherAlgorithmForKeyDerivation = inferDigestAlgorithmFromCipherAlgorithmForKeyDerivation(str, i);
        LOGGER.info("DEBUG: key derivation uses digestAlg = " + inferDigestAlgorithmFromCipherAlgorithmForKeyDerivation);
        MessageDigest instance = MessageDigest.getInstance(inferDigestAlgorithmFromCipherAlgorithmForKeyDerivation);
        instance.reset();
        instance.update(bArr);
        if (bArr2 != null) {
            instance.update(bArr2);
        }
        instance.update(new byte[]{0, 0, 0, (byte) i2});
        byte[] digest = instance.digest();
        byte[] bArr3 = null;
        if ("DESede".equalsIgnoreCase(str) || "3DES".equalsIgnoreCase(str)) {
            switch (i) {
                case 112:
                case 128:
                    bArr3 = new byte[24];
                    System.arraycopy(digest, 0, bArr3, 0, 8);
                    System.arraycopy(digest, 8, bArr3, 8, 8);
                    System.arraycopy(digest, 0, bArr3, 16, 8);
                    break;
                default:
                    throw new IllegalArgumentException("KDF can only use DESede with 128-bit key length");
            }
        } else if ("AES".equalsIgnoreCase(str) || str.startsWith("AES")) {
            switch (i) {
                case 128:
                    bArr3 = new byte[16];
                    System.arraycopy(digest, 0, bArr3, 0, 16);
                    break;
                case 192:
                    bArr3 = new byte[24];
                    System.arraycopy(digest, 0, bArr3, 0, 24);
                    break;
                case 256:
                    bArr3 = new byte[32];
                    System.arraycopy(digest, 0, bArr3, 0, 32);
                    break;
                default:
                    throw new IllegalArgumentException("KDF can only use AES with 128-bit, 192-bit key or 256-bit length, found: " + i + "-bit key length");
            }
        }
        return new SecretKeySpec(bArr3, str);
    }

    public static byte[] computeKeySeedForBAC(String str, String str2, String str3) throws GeneralSecurityException {
        return computeKeySeed(str, str2, str3, "SHA-1", true);
    }

    public static byte[] computeKeySeedForPACE(String str, String str2, String str3) throws GeneralSecurityException {
        return computeKeySeed(str, str2, str3, "SHA-1", false);
    }

    public static byte[] computeKeySeed(String str, String str2, String str3, String str4, boolean z) throws GeneralSecurityException {
        byte[] bArr = {(byte) MRZInfo.checkDigit(str)};
        byte[] bArr2 = {(byte) MRZInfo.checkDigit(str2)};
        byte[] bArr3 = {(byte) MRZInfo.checkDigit(str3)};
        MessageDigest instance = MessageDigest.getInstance(str4);
        instance.update(getBytes(str));
        instance.update(bArr);
        instance.update(getBytes(str2));
        instance.update(bArr2);
        instance.update(getBytes(str3));
        instance.update(bArr3);
        byte[] digest = instance.digest();
        if (!z) {
            return digest;
        }
        byte[] bArr4 = new byte[16];
        System.arraycopy(digest, 0, bArr4, 0, 16);
        return bArr4;
    }

    public static byte[] padWithMRZ(byte[] bArr) {
        return padWithMRZ(bArr, 0, bArr.length);
    }

    public static byte[] padWithCAN(byte[] bArr, int i) {
        return padWithCAN(bArr, 0, bArr.length, i);
    }

    public static byte[] padWithMRZ(byte[] bArr, int i, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(bArr, i, i2);
        byteArrayOutputStream.write(-128);
        while (byteArrayOutputStream.size() % 8 != 0) {
            byteArrayOutputStream.write(0);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] padWithCAN(byte[] bArr, int i, int i2, int i3) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(bArr, i, i2);
        byteArrayOutputStream.write(-128);
        while (byteArrayOutputStream.size() % i3 != 0) {
            byteArrayOutputStream.write(0);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static long computeSendSequenceCounter(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length != 8 || bArr2 == null || bArr2.length != 8) {
            throw new IllegalStateException("Wrong length input");
        }
        long j = 0;
        for (int i = 4; i < 8; i++) {
            j = (j << 8) + ((long) (bArr[i] & 255));
        }
        for (int i2 = 4; i2 < 8; i2++) {
            j = (j << 8) + ((long) (bArr2[i2] & 255));
        }
        return j;
    }

    public static byte[] unpad(byte[] bArr) throws BadPaddingException {
        int length = bArr.length - 1;
        while (length >= 0 && bArr[length] == 0) {
            length--;
        }
        if ((bArr[length] & 255) != 128) {
            throw new BadPaddingException("Expected constant 0x80, found 0x" + Integer.toHexString(bArr[length] & 255) + "\nDEBUG: in = " + Hex.bytesToHexString(bArr) + ", index = " + length);
        }
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    public static byte[] recoverMessage(int i, byte[] bArr) {
        if (bArr == null || bArr.length < 1) {
            throw new IllegalArgumentException("Plaintext too short to recover message");
        } else if (((bArr[0] & ISO7816.INS_GET_RESPONSE) ^ 64) != 0) {
            throw new NumberFormatException("Could not get M1");
        } else if (((bArr[bArr.length - 1] & 15) ^ PassportService.SF_DG12) != 0) {
            throw new NumberFormatException("Could not get M1");
        } else if (((bArr[bArr.length - 1] & 255) ^ PSSSigner.TRAILER_IMPLICIT) == 0) {
            int i2 = 0;
            while (i2 < bArr.length && ((bArr[i2] & 15) ^ 10) != 0) {
                i2++;
            }
            int i3 = i2 + 1;
            int length = ((bArr.length - 1) - i) - i3;
            if (length <= 0) {
                throw new NumberFormatException("Could not get M1");
            } else if ((bArr[0] & ISO7816.INS_VERIFY) == 0) {
                throw new NumberFormatException("Could not get M1");
            } else {
                byte[] bArr2 = new byte[length];
                System.arraycopy(bArr, i3, bArr2, 0, length);
                return bArr2;
            }
        } else {
            throw new NumberFormatException("Could not get M1");
        }
    }

    public static byte[] getRawECDSASignature(byte[] bArr, int i) throws IOException {
        ASN1InputStream aSN1InputStream = new ASN1InputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            Enumeration objects = ((ASN1Sequence) aSN1InputStream.readObject()).getObjects();
            while (objects.hasMoreElements()) {
                byteArrayOutputStream.write(alignKeyDataToSize(((ASN1Integer) objects.nextElement()).getValue().toByteArray(), i));
            }
            byteArrayOutputStream.flush();
            return byteArrayOutputStream.toByteArray();
        } finally {
            aSN1InputStream.close();
            byteArrayOutputStream.close();
        }
    }

    public static byte[] alignKeyDataToSize(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        if (bArr.length < i) {
            i = bArr.length;
        }
        System.arraycopy(bArr, bArr.length - i, bArr2, bArr2.length - i, i);
        return bArr2;
    }

    public static byte[] i2os(BigInteger bigInteger, int i) {
        BigInteger valueOf = BigInteger.valueOf(256);
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            BigInteger mod = bigInteger.mod(valueOf);
            bigInteger = bigInteger.divide(valueOf);
            bArr[(i - 1) - i2] = (byte) mod.intValue();
        }
        return bArr;
    }

    public static byte[] i2os(BigInteger bigInteger) {
        int length = bigInteger.toString(16).length();
        if (length % 2 != 0) {
            length++;
        }
        return i2os(bigInteger, length / 2);
    }

    public static BigInteger os2i(byte[] bArr) {
        if (bArr != null) {
            return os2i(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException();
    }

    public static BigInteger os2i(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException();
        }
        BigInteger bigInteger = BigInteger.ZERO;
        BigInteger valueOf = BigInteger.valueOf(256);
        BigInteger bigInteger2 = bigInteger;
        for (int i3 = i; i3 < i + i2; i3++) {
            bigInteger2 = bigInteger2.multiply(valueOf).add(BigInteger.valueOf((long) (bArr[i3] & 255)));
        }
        return bigInteger2;
    }

    public static BigInteger os2fe(byte[] bArr, BigInteger bigInteger) {
        return os2i(bArr).mod(bigInteger);
    }

    public static byte[] publicKeyECPointToOS(ECPoint eCPoint) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BigInteger affineX = eCPoint.getAffineX();
        BigInteger affineY = eCPoint.getAffineY();
        try {
            byteArrayOutputStream.write(4);
            byteArrayOutputStream.write(i2os(affineX));
            byteArrayOutputStream.write(i2os(affineY));
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public static String inferDigestAlgorithmFromSignatureAlgorithm(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        String str2 = null;
        String upperCase = str.toUpperCase();
        if (upperCase.contains("WITH")) {
            str2 = upperCase.split("WITH")[0];
        }
        if ("SHA1".equalsIgnoreCase(str2)) {
            str2 = "SHA-1";
        }
        if ("SHA224".equalsIgnoreCase(str2)) {
            str2 = "SHA-224";
        }
        if (McElieceCCA2ParameterSpec.DEFAULT_MD.equalsIgnoreCase(str2)) {
            str2 = "SHA-256";
        }
        if ("SHA384".equalsIgnoreCase(str2)) {
            str2 = "SHA-384";
        }
        if ("SHA512".equalsIgnoreCase(str2)) {
            return "SHA-512";
        }
        return str2;
    }

    public static String inferDigestAlgorithmFromCipherAlgorithmForKeyDerivation(String str, int i) {
        if (str == null) {
            throw new IllegalArgumentException();
        } else if ("DESede".equals(str) || "AES-128".equals(str)) {
            return "SHA-1";
        } else {
            if ("AES".equals(str) && i == 128) {
                return "SHA-1";
            }
            if ("AES-256".equals(str) || "AES-192".equals(str)) {
                return "SHA-256";
            }
            if ("AES".equals(str) && (i == 192 || i == 256)) {
                return "SHA-256";
            }
            throw new IllegalArgumentException("Unsupported cipher algorithm or key length \"" + str + "\", " + i);
        }
    }

    public static DHParameterSpec toExplicitDHParameterSpec(DHParameters dHParameters) {
        return new DHParameterSpec(dHParameters.getP(), dHParameters.getG(), dHParameters.getL());
    }

    public static String getDetailedPublicKeyAlgorithm(PublicKey publicKey) {
        String algorithm = publicKey.getAlgorithm();
        if (publicKey instanceof RSAPublicKey) {
            return algorithm + " [" + ((RSAPublicKey) publicKey).getModulus().bitLength() + " bit]";
        } else if (!(publicKey instanceof ECPublicKey)) {
            return algorithm;
        } else {
            String curveName = getCurveName(((ECPublicKey) publicKey).getParams());
            if (curveName != null) {
                return algorithm + " [" + curveName + "]";
            }
            return algorithm;
        }
    }

    public static String getCurveName(ECParameterSpec eCParameterSpec) {
        ECNamedCurveSpec namedCurveSpec = toNamedCurveSpec(eCParameterSpec);
        if (namedCurveSpec == null) {
            return null;
        }
        return namedCurveSpec.getName();
    }

    public static ECParameterSpec toExplicitECParameterSpec(ECNamedCurveParameterSpec eCNamedCurveParameterSpec) {
        return toExplicitECParameterSpec((ECParameterSpec) toECNamedCurveSpec(eCNamedCurveParameterSpec));
    }

    public static ECParameterSpec toExplicitECParameterSpec(ECParameterSpec eCParameterSpec) {
        try {
            ECPoint generator = eCParameterSpec.getGenerator();
            BigInteger order = eCParameterSpec.getOrder();
            int cofactor = eCParameterSpec.getCofactor();
            EllipticCurve curve = eCParameterSpec.getCurve();
            BigInteger a = curve.getA();
            BigInteger b = curve.getB();
            ECField field = curve.getField();
            if (field instanceof ECFieldFp) {
                return new ECParameterSpec(new EllipticCurve(new ECFieldFp(((ECFieldFp) field).getP()), a, b), generator, order, cofactor);
            }
            if (field instanceof ECFieldF2m) {
                return new ECParameterSpec(new EllipticCurve(new ECFieldF2m(((ECFieldF2m) field).getM()), a, b), generator, order, cofactor);
            }
            LOGGER.warning("Could not make named EC param spec explicit");
            return eCParameterSpec;
        } catch (Exception e) {
            LOGGER.warning("Could not make named EC param spec explicit");
            return eCParameterSpec;
        }
    }

    private static ECNamedCurveSpec toNamedCurveSpec(ECParameterSpec eCParameterSpec) {
        if (eCParameterSpec == null) {
            return null;
        }
        if (eCParameterSpec instanceof ECNamedCurveSpec) {
            return (ECNamedCurveSpec) eCParameterSpec;
        }
        ArrayList<String> list = Collections.list(ECNamedCurveTable.getNames());
        ArrayList arrayList = new ArrayList();
        for (String parameterSpec : list) {
            ECNamedCurveSpec eCNamedCurveSpec = toECNamedCurveSpec(ECNamedCurveTable.getParameterSpec(parameterSpec));
            if (eCNamedCurveSpec.getCurve().equals(eCParameterSpec.getCurve()) && eCNamedCurveSpec.getGenerator().equals(eCParameterSpec.getGenerator()) && eCNamedCurveSpec.getOrder().equals(eCParameterSpec.getOrder()) && eCNamedCurveSpec.getCofactor() == eCParameterSpec.getCofactor()) {
                arrayList.add(eCNamedCurveSpec);
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        if (arrayList.size() == 1) {
            return (ECNamedCurveSpec) arrayList.get(0);
        }
        return (ECNamedCurveSpec) arrayList.get(0);
    }

    public static ECNamedCurveSpec toECNamedCurveSpec(ECNamedCurveParameterSpec eCNamedCurveParameterSpec) {
        return new ECNamedCurveSpec(eCNamedCurveParameterSpec.getName(), eCNamedCurveParameterSpec.getCurve(), eCNamedCurveParameterSpec.getG(), eCNamedCurveParameterSpec.getN(), eCNamedCurveParameterSpec.getH(), eCNamedCurveParameterSpec.getSeed());
    }

    public static SubjectPublicKeyInfo toSubjectPublicKeyInfo(PublicKey publicKey) {
        try {
            String algorithm = publicKey.getAlgorithm();
            if ("EC".equals(algorithm) || "ECDH".equals(algorithm) || (publicKey instanceof ECPublicKey)) {
                ASN1InputStream aSN1InputStream = new ASN1InputStream(publicKey.getEncoded());
                SubjectPublicKeyInfo subjectPublicKeyInfo = new SubjectPublicKeyInfo((ASN1Sequence) aSN1InputStream.readObject());
                aSN1InputStream.close();
                AlgorithmIdentifier algorithm2 = subjectPublicKeyInfo.getAlgorithm();
                String id = algorithm2.getAlgorithm().getId();
                if (!SecurityInfo.ID_EC_PUBLIC_KEY.equals(id)) {
                    throw new IllegalStateException("Was expecting id-ecPublicKey (" + SecurityInfo.ID_EC_PUBLIC_KEY_TYPE + "), found " + id);
                }
                ASN1Primitive aSN1Primitive = algorithm2.getParameters().toASN1Primitive();
                if (!(aSN1Primitive instanceof ASN1ObjectIdentifier)) {
                    return subjectPublicKeyInfo;
                }
                ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) aSN1Primitive;
                X9ECParameters byOID = X962NamedCurves.getByOID(aSN1ObjectIdentifier);
                if (byOID == null) {
                    throw new IllegalStateException("Could not find X9.62 named curve for OID " + aSN1ObjectIdentifier.getId());
                }
                org.spongycastle.math.p332ec.ECPoint g = byOID.getG();
                X9ECParameters x9ECParameters = new X9ECParameters(byOID.getCurve(), g.getCurve().createPoint(g.getX().toBigInteger(), g.getY().toBigInteger(), false), byOID.getN(), byOID.getH(), byOID.getSeed());
                if (!(publicKey instanceof org.spongycastle.jce.interfaces.ECPublicKey)) {
                    return subjectPublicKeyInfo;
                }
                return new SubjectPublicKeyInfo(new AlgorithmIdentifier(subjectPublicKeyInfo.getAlgorithm().getAlgorithm(), x9ECParameters.toASN1Primitive()), ((org.spongycastle.jce.interfaces.ECPublicKey) publicKey).getQ().getEncoded());
            } else if ("DH".equals(algorithm) || (publicKey instanceof DHPublicKey)) {
                DHPublicKey dHPublicKey = (DHPublicKey) publicKey;
                DHParameterSpec params = dHPublicKey.getParams();
                return new SubjectPublicKeyInfo(new AlgorithmIdentifier(EACObjectIdentifiers.id_PK_DH, new DHParameter(params.getP(), params.getG(), params.getL()).toASN1Primitive()), (ASN1Encodable) new ASN1Integer(dHPublicKey.getY()));
            } else {
                throw new IllegalArgumentException("Unrecognized key type, found " + publicKey.getAlgorithm() + ", should be DH or ECDH");
            }
        } catch (Exception e) {
            LOGGER.severe("Exception: " + e.getMessage());
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0029, code lost:
        LOGGER.severe("Exception: " + r1.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0047, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0048, code lost:
        LOGGER.severe("Exception: " + r1.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0047 A[ExcHandler: Exception (r1v1 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:1:0x0004] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.security.PublicKey toPublicKey(org.spongycastle.asn1.x509.SubjectPublicKeyInfo r5) {
        /*
            r0 = 0
            java.lang.String r1 = "DER"
            byte[] r1 = r5.getEncoded(r1)     // Catch:{ GeneralSecurityException -> 0x0028, Exception -> 0x0047 }
            java.security.spec.X509EncodedKeySpec r2 = new java.security.spec.X509EncodedKeySpec     // Catch:{ GeneralSecurityException -> 0x0028, Exception -> 0x0047 }
            r2.<init>(r1)     // Catch:{ GeneralSecurityException -> 0x0028, Exception -> 0x0047 }
            java.lang.String r1 = "DH"
            java.security.KeyFactory r1 = java.security.KeyFactory.getInstance(r1)     // Catch:{ GeneralSecurityException -> 0x0019, Exception -> 0x0047 }
            java.security.PublicKey r0 = r1.generatePublic(r2)     // Catch:{ GeneralSecurityException -> 0x0019, Exception -> 0x0047 }
        L_0x0018:
            return r0
        L_0x0019:
            r1 = move-exception
            java.lang.String r1 = "EC"
            java.security.Provider r3 = BC_PROVIDER     // Catch:{ GeneralSecurityException -> 0x0028, Exception -> 0x0047 }
            java.security.KeyFactory r1 = java.security.KeyFactory.getInstance(r1, r3)     // Catch:{ GeneralSecurityException -> 0x0028, Exception -> 0x0047 }
            java.security.PublicKey r0 = r1.generatePublic(r2)     // Catch:{ GeneralSecurityException -> 0x0028, Exception -> 0x0047 }
            goto L_0x0018
        L_0x0028:
            r1 = move-exception
            java.util.logging.Logger r2 = LOGGER
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Exception: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r1 = r1.getMessage()
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            r2.severe(r1)
            goto L_0x0018
        L_0x0047:
            r1 = move-exception
            java.util.logging.Logger r2 = LOGGER
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Exception: "
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r1 = r1.getMessage()
            java.lang.StringBuilder r1 = r3.append(r1)
            java.lang.String r1 = r1.toString()
            r2.severe(r1)
            goto L_0x0018
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jmrtd.Util.toPublicKey(org.spongycastle.asn1.x509.SubjectPublicKeyInfo):java.security.PublicKey");
    }

    public static PublicKey reconstructPublicKey(PublicKey publicKey) {
        if (!(publicKey instanceof ECPublicKey)) {
            return publicKey;
        }
        try {
            ECPublicKey eCPublicKey = (ECPublicKey) publicKey;
            ECPoint w = eCPublicKey.getW();
            ECParameterSpec explicitECParameterSpec = toExplicitECParameterSpec(eCPublicKey.getParams());
            if (explicitECParameterSpec == null) {
                return publicKey;
            }
            return KeyFactory.getInstance("EC", BC_PROVIDER).generatePublic(new ECPublicKeySpec(w, explicitECParameterSpec));
        } catch (Exception e) {
            LOGGER.warning("Could not make public key param spec explicit");
            return publicKey;
        }
    }

    public static byte[] encodePublicKeyDataObject(String str, PublicKey publicKey) throws InvalidKeyException {
        return encodePublicKeyDataObject(str, publicKey, true);
    }

    public static byte[] encodePublicKeyDataObject(String str, PublicKey publicKey, boolean z) throws InvalidKeyException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        TLVOutputStream tLVOutputStream = new TLVOutputStream(byteArrayOutputStream);
        try {
            tLVOutputStream.writeTag(32585);
            if (publicKey instanceof DHPublicKey) {
                DHPublicKey dHPublicKey = (DHPublicKey) publicKey;
                DHParameterSpec params = dHPublicKey.getParams();
                BigInteger p = params.getP();
                int l = params.getL();
                BigInteger g = params.getG();
                BigInteger y = dHPublicKey.getY();
                tLVOutputStream.write(new ASN1ObjectIdentifier(str).getEncoded());
                if (!z) {
                    tLVOutputStream.writeTag(129);
                    tLVOutputStream.writeValue(i2os(p));
                    tLVOutputStream.writeTag(ISO781611.BIOMETRIC_SUBTYPE_TAG);
                    tLVOutputStream.writeValue(i2os(BigInteger.valueOf((long) l)));
                    tLVOutputStream.writeTag(ISO781611.CREATION_DATE_AND_TIME_TAG);
                    tLVOutputStream.writeValue(i2os(g));
                }
                tLVOutputStream.writeTag(CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA);
                tLVOutputStream.writeValue(i2os(y));
            } else if (publicKey instanceof ECPublicKey) {
                ECPublicKey eCPublicKey = (ECPublicKey) publicKey;
                ECParameterSpec params2 = eCPublicKey.getParams();
                BigInteger prime = getPrime(params2);
                EllipticCurve curve = params2.getCurve();
                BigInteger a = curve.getA();
                BigInteger b = curve.getB();
                ECPoint generator = params2.getGenerator();
                BigInteger order = params2.getOrder();
                int cofactor = params2.getCofactor();
                ECPoint w = eCPublicKey.getW();
                tLVOutputStream.write(new ASN1ObjectIdentifier(str).getEncoded());
                if (!z) {
                    tLVOutputStream.writeTag(129);
                    tLVOutputStream.writeValue(i2os(prime));
                    tLVOutputStream.writeTag(ISO781611.BIOMETRIC_SUBTYPE_TAG);
                    tLVOutputStream.writeValue(i2os(a));
                    tLVOutputStream.writeTag(ISO781611.CREATION_DATE_AND_TIME_TAG);
                    tLVOutputStream.writeValue(i2os(b));
                    tLVOutputStream.writeTag(CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA);
                    tLVOutputStream.write(i2os(generator.getAffineX()));
                    tLVOutputStream.write(i2os(generator.getAffineY()));
                    tLVOutputStream.writeValueEnd();
                    tLVOutputStream.writeTag(133);
                    tLVOutputStream.writeValue(i2os(order));
                }
                tLVOutputStream.writeTag(134);
                tLVOutputStream.writeValue(publicKeyECPointToOS(w));
                if (!z) {
                    tLVOutputStream.writeTag(135);
                    tLVOutputStream.writeValue(i2os(BigInteger.valueOf((long) cofactor)));
                }
            } else {
                throw new InvalidKeyException("Unsupported public key: " + publicKey.getClass().getCanonicalName());
            }
            tLVOutputStream.writeValueEnd();
            tLVOutputStream.flush();
            tLVOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            throw new IllegalStateException("Error in encoding public key");
        }
    }

    public static byte[] encodePublicKeyForSmartCard(PublicKey publicKey) throws InvalidKeyException {
        if (publicKey == null) {
            throw new IllegalArgumentException("Cannot encode null public key");
        } else if (publicKey instanceof ECPublicKey) {
            ECPublicKey eCPublicKey = (ECPublicKey) publicKey;
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byteArrayOutputStream.write(publicKeyECPointToOS(eCPublicKey.getW()));
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            } catch (IOException e) {
                throw new IllegalStateException("Internal error writing to memory: " + e.getMessage());
            }
        } else if (publicKey instanceof DHPublicKey) {
            return i2os(((DHPublicKey) publicKey).getY());
        } else {
            throw new InvalidKeyException("Unsupported public key: " + publicKey.getClass().getCanonicalName());
        }
    }

    public static PublicKey decodePublicKeyFromSmartCard(byte[] bArr, AlgorithmParameterSpec algorithmParameterSpec) {
        if (algorithmParameterSpec == null) {
            throw new IllegalArgumentException("Params cannot be null");
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
            if (algorithmParameterSpec instanceof ECParameterSpec) {
                if (dataInputStream.read() != 4) {
                    throw new IllegalArgumentException("Expected encoded public key to start with 0x04");
                }
                int length = (bArr.length - 1) / 2;
                byte[] bArr2 = new byte[length];
                byte[] bArr3 = new byte[length];
                dataInputStream.readFully(bArr2);
                dataInputStream.readFully(bArr3);
                dataInputStream.close();
                return KeyFactory.getInstance("EC").generatePublic(new ECPublicKeySpec(new ECPoint(os2i(bArr2), os2i(bArr3)), (ECParameterSpec) algorithmParameterSpec));
            } else if (!(algorithmParameterSpec instanceof DHParameterSpec)) {
                throw new IllegalArgumentException("Expected ECParameterSpec or DHParameterSpec, found " + algorithmParameterSpec.getClass().getCanonicalName());
            } else if (dataInputStream.read() != 4) {
                throw new IllegalArgumentException("Expected encoded public key to start with 0x04");
            } else {
                byte[] bArr4 = new byte[(bArr.length - 1)];
                dataInputStream.readFully(bArr4);
                dataInputStream.close();
                DHParameterSpec dHParameterSpec = (DHParameterSpec) algorithmParameterSpec;
                return KeyFactory.getInstance("DH").generatePublic(new DHPublicKeySpec(os2i(bArr4), dHParameterSpec.getP(), dHParameterSpec.getG()));
            }
        } catch (IOException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        } catch (GeneralSecurityException e2) {
            LOGGER.severe("Exception: " + e2.getMessage());
            throw new IllegalArgumentException(e2.getMessage());
        }
    }

    public static String inferMacAlgorithmFromCipherAlgorithm(String str) throws InvalidAlgorithmParameterException {
        if (str == null) {
            throw new IllegalArgumentException("Cannot infer MAC algorithm from cipher algoruthm null");
        } else if (str.startsWith("DESede")) {
            return "ISO9797Alg3Mac";
        } else {
            if (str.startsWith("AES")) {
                return "AESCMAC";
            }
            throw new InvalidAlgorithmParameterException("Cannot infer MAC algorithm from cipher algorithm \"" + str + "\"");
        }
    }

    public static byte[] generateAuthenticationToken(String str, SecretKey secretKey, PublicKey publicKey) throws GeneralSecurityException {
        Mac instance = Mac.getInstance(inferMacAlgorithmFromCipherAlgorithm(PACEInfo.toCipherAlgorithm(str)), BC_PROVIDER);
        byte[] encodePublicKeyDataObject = encodePublicKeyDataObject(str, publicKey);
        instance.init(secretKey);
        byte[] doFinal = instance.doFinal(encodePublicKeyDataObject);
        byte[] bArr = new byte[8];
        System.arraycopy(doFinal, 0, bArr, 0, bArr.length);
        return bArr;
    }

    public static String inferProtocolIdentifier(PublicKey publicKey) {
        String algorithm = publicKey.getAlgorithm();
        if ("EC".equals(algorithm) || "ECDH".equals(algorithm)) {
            return SecurityInfo.ID_PK_ECDH_OID;
        }
        if ("DH".equals(algorithm)) {
            return SecurityInfo.ID_PK_DH_OID;
        }
        throw new IllegalArgumentException("Wrong key type. Was expecting ECDH or DH public key.");
    }

    public static AlgorithmParameterSpec mapNonceGM(byte[] bArr, byte[] bArr2, AlgorithmParameterSpec algorithmParameterSpec) {
        if (algorithmParameterSpec == null) {
            throw new IllegalArgumentException("Unsupported parameters for mapping nonce");
        } else if (algorithmParameterSpec instanceof ECParameterSpec) {
            ECParameterSpec eCParameterSpec = (ECParameterSpec) algorithmParameterSpec;
            BigInteger os2i = os2i(bArr2);
            return mapNonceGMWithECDH(os2i(bArr), new ECPoint(os2i, computeAffineY(os2i, eCParameterSpec)), eCParameterSpec);
        } else if (algorithmParameterSpec instanceof DHParameterSpec) {
            return mapNonceGMWithDH(os2i(bArr), os2i(bArr2), (DHParameterSpec) algorithmParameterSpec);
        } else {
            throw new IllegalArgumentException("Unsupported parameters for mapping nonce, expected ECParameterSpec or DHParameterSpec, found " + algorithmParameterSpec.getClass().getCanonicalName());
        }
    }

    public static AlgorithmParameterSpec mapNonceIM(byte[] bArr, byte[] bArr2, byte[] bArr3, AlgorithmParameterSpec algorithmParameterSpec) {
        return null;
    }

    private static ECParameterSpec mapNonceGMWithECDH(BigInteger bigInteger, ECPoint eCPoint, ECParameterSpec eCParameterSpec) {
        ECPoint generator = eCParameterSpec.getGenerator();
        EllipticCurve curve = eCParameterSpec.getCurve();
        BigInteger a = curve.getA();
        BigInteger b = curve.getB();
        BigInteger p = ((ECFieldFp) curve.getField()).getP();
        BigInteger order = eCParameterSpec.getOrder();
        int cofactor = eCParameterSpec.getCofactor();
        ECPoint add = add(multiply(bigInteger, generator, eCParameterSpec), eCPoint, eCParameterSpec);
        if (!toBouncyCastleECPoint(add, eCParameterSpec).isValid()) {
            LOGGER.info("ephemeralGenerator is not a valid point");
        }
        return new ECParameterSpec(new EllipticCurve(new ECFieldFp(p), a, b), add, order, cofactor);
    }

    private static DHParameterSpec mapNonceGMWithDH(BigInteger bigInteger, BigInteger bigInteger2, DHParameterSpec dHParameterSpec) {
        BigInteger p = dHParameterSpec.getP();
        return new DHParameterSpec(p, dHParameterSpec.getG().modPow(bigInteger, p).multiply(bigInteger2).mod(p), dHParameterSpec.getL());
    }

    private static ECPoint add(ECPoint eCPoint, ECPoint eCPoint2, ECParameterSpec eCParameterSpec) {
        return fromBouncyCastleECPoint(toBouncyCastleECPoint(eCPoint, eCParameterSpec).add(toBouncyCastleECPoint(eCPoint2, eCParameterSpec)));
    }

    public static ECPoint multiply(BigInteger bigInteger, ECPoint eCPoint, ECParameterSpec eCParameterSpec) {
        return fromBouncyCastleECPoint(toBouncyCastleECPoint(eCPoint, eCParameterSpec).multiply(bigInteger));
    }

    private static byte[] getBytes(String str) {
        byte[] bytes = str.getBytes();
        try {
            return str.getBytes(JPushConstants.ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            return bytes;
        }
    }

    public static BigInteger getPrime(AlgorithmParameterSpec algorithmParameterSpec) {
        if (algorithmParameterSpec == null) {
            throw new IllegalArgumentException("Parameters null");
        } else if (algorithmParameterSpec instanceof DHParameterSpec) {
            return ((DHParameterSpec) algorithmParameterSpec).getP();
        } else {
            if (algorithmParameterSpec instanceof ECParameterSpec) {
                ECField field = ((ECParameterSpec) algorithmParameterSpec).getCurve().getField();
                if (field instanceof ECFieldFp) {
                    return ((ECFieldFp) field).getP();
                }
                throw new IllegalStateException("Was expecting prime field of type ECFieldFp, found " + field.getClass().getCanonicalName());
            }
            throw new IllegalArgumentException("Unsupported agreement algorithm, was expecting DHParameterSpec or ECParameterSpec, found " + algorithmParameterSpec.getClass().getCanonicalName());
        }
    }

    public static byte[] wrapDO(byte b, byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("Data to wrap is null");
        }
        byte[] bArr2 = new byte[(bArr.length + 2)];
        bArr2[0] = b;
        bArr2[1] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr2, 2, bArr.length);
        return bArr2;
    }

    public static byte[] unwrapDO(byte b, byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            throw new IllegalArgumentException("Wrapped data is null or length < 2");
        }
        byte b2 = bArr[0];
        if (b2 != b) {
            throw new IllegalArgumentException("Expected tag " + Integer.toHexString(b) + ", found tag " + Integer.toHexString(b2));
        }
        byte[] bArr2 = new byte[(bArr.length - 2)];
        System.arraycopy(bArr, 2, bArr2, 0, bArr2.length);
        return bArr2;
    }

    public static String inferKeyAgreementAlgorithm(PublicKey publicKey) {
        if (publicKey instanceof ECPublicKey) {
            return "ECDH";
        }
        if (publicKey instanceof DHPublicKey) {
            return "DH";
        }
        throw new IllegalArgumentException("Unsupported public key: " + publicKey);
    }

    public static BigInteger computeAffineY(BigInteger bigInteger, ECParameterSpec eCParameterSpec) {
        ECCurve bouncyCastleECCurve = toBouncyCastleECCurve(eCParameterSpec);
        ECFieldElement a = bouncyCastleECCurve.getA();
        ECFieldElement b = bouncyCastleECCurve.getB();
        ECFieldElement fromBigInteger = bouncyCastleECCurve.fromBigInteger(bigInteger);
        return fromBigInteger.multiply(fromBigInteger).add(a).multiply(fromBigInteger).add(b).sqrt().toBigInteger();
    }

    private static org.spongycastle.math.p332ec.ECPoint toBouncyCastleECPoint(ECPoint eCPoint, ECParameterSpec eCParameterSpec) {
        return toBouncyCastleECCurve(eCParameterSpec).createPoint(eCPoint.getAffineX(), eCPoint.getAffineY(), false);
    }

    private static ECPoint fromBouncyCastleECPoint(org.spongycastle.math.p332ec.ECPoint eCPoint) {
        org.spongycastle.math.p332ec.ECPoint normalize = eCPoint.normalize();
        if (!normalize.isValid()) {
            LOGGER.warning("point not valid");
        }
        return new ECPoint(normalize.getAffineXCoord().toBigInteger(), normalize.getAffineYCoord().toBigInteger());
    }

    public static boolean isValid(ECPoint eCPoint, ECParameterSpec eCParameterSpec) {
        return toBouncyCastleECPoint(eCPoint, eCParameterSpec).isValid();
    }

    public static ECPoint normalize(ECPoint eCPoint, ECParameterSpec eCParameterSpec) {
        return fromBouncyCastleECPoint(toBouncyCastleECPoint(eCPoint, eCParameterSpec).normalize());
    }

    private static ECCurve toBouncyCastleECCurve(ECParameterSpec eCParameterSpec) {
        EllipticCurve curve = eCParameterSpec.getCurve();
        ECField field = curve.getField();
        if (!(field instanceof ECFieldFp)) {
            throw new IllegalArgumentException("Only prime field supported (for now), found " + field.getClass().getCanonicalName());
        }
        int cofactor = eCParameterSpec.getCofactor();
        BigInteger order = eCParameterSpec.getOrder();
        return new C5397Fp(getPrime(eCParameterSpec), curve.getA(), curve.getB(), order, BigInteger.valueOf((long) cofactor));
    }
}
