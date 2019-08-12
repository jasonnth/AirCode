package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Hashtable;
import org.spongycastle.asn1.p325x9.ECNamedCurveTable;
import org.spongycastle.asn1.p325x9.X9ECParameters;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.agreement.ECDHBasicAgreement;
import org.spongycastle.crypto.generators.ECKeyPairGenerator;
import org.spongycastle.crypto.p326ec.CustomNamedCurves;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyGenerationParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.math.field.PolynomialExtensionField;
import org.spongycastle.math.p332ec.ECAlgorithms;
import org.spongycastle.math.p332ec.ECCurve;
import org.spongycastle.math.p332ec.ECCurve.C5397Fp;
import org.spongycastle.math.p332ec.ECCurve.F2m;
import org.spongycastle.math.p332ec.ECFieldElement;
import org.spongycastle.math.p332ec.ECPoint;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.Integers;

public class TlsECCUtils {
    private static final String[] CURVE_NAMES = {"sect163k1", "sect163r1", "sect163r2", "sect193r1", "sect193r2", "sect233k1", "sect233r1", "sect239k1", "sect283k1", "sect283r1", "sect409k1", "sect409r1", "sect571k1", "sect571r1", "secp160k1", "secp160r1", "secp160r2", "secp192k1", "secp192r1", "secp224k1", "secp224r1", "secp256k1", "secp256r1", "secp384r1", "secp521r1", "brainpoolP256r1", "brainpoolP384r1", "brainpoolP512r1"};
    public static final Integer EXT_ec_point_formats = Integers.valueOf(11);
    public static final Integer EXT_elliptic_curves = Integers.valueOf(10);

    public static void addSupportedEllipticCurvesExtension(Hashtable extensions, int[] namedCurves) throws IOException {
        extensions.put(EXT_elliptic_curves, createSupportedEllipticCurvesExtension(namedCurves));
    }

    public static void addSupportedPointFormatsExtension(Hashtable extensions, short[] ecPointFormats) throws IOException {
        extensions.put(EXT_ec_point_formats, createSupportedPointFormatsExtension(ecPointFormats));
    }

    public static int[] getSupportedEllipticCurvesExtension(Hashtable extensions) throws IOException {
        byte[] extensionData = TlsUtils.getExtensionData(extensions, EXT_elliptic_curves);
        if (extensionData == null) {
            return null;
        }
        return readSupportedEllipticCurvesExtension(extensionData);
    }

    public static short[] getSupportedPointFormatsExtension(Hashtable extensions) throws IOException {
        byte[] extensionData = TlsUtils.getExtensionData(extensions, EXT_ec_point_formats);
        if (extensionData == null) {
            return null;
        }
        return readSupportedPointFormatsExtension(extensionData);
    }

    public static byte[] createSupportedEllipticCurvesExtension(int[] namedCurves) throws IOException {
        if (namedCurves != null && namedCurves.length >= 1) {
            return TlsUtils.encodeUint16ArrayWithUint16Length(namedCurves);
        }
        throw new TlsFatalAlert(80);
    }

    public static byte[] createSupportedPointFormatsExtension(short[] ecPointFormats) throws IOException {
        if (ecPointFormats == null || !Arrays.contains(ecPointFormats, 0)) {
            ecPointFormats = Arrays.append(ecPointFormats, 0);
        }
        return TlsUtils.encodeUint8ArrayWithUint8Length(ecPointFormats);
    }

    public static int[] readSupportedEllipticCurvesExtension(byte[] extensionData) throws IOException {
        if (extensionData == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        ByteArrayInputStream buf = new ByteArrayInputStream(extensionData);
        int length = TlsUtils.readUint16(buf);
        if (length < 2 || (length & 1) != 0) {
            throw new TlsFatalAlert(50);
        }
        int[] namedCurves = TlsUtils.readUint16Array(length / 2, buf);
        TlsProtocol.assertEmpty(buf);
        return namedCurves;
    }

    public static short[] readSupportedPointFormatsExtension(byte[] extensionData) throws IOException {
        if (extensionData == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        ByteArrayInputStream buf = new ByteArrayInputStream(extensionData);
        short length = TlsUtils.readUint8(buf);
        if (length < 1) {
            throw new TlsFatalAlert(50);
        }
        short[] ecPointFormats = TlsUtils.readUint8Array(length, buf);
        TlsProtocol.assertEmpty(buf);
        if (Arrays.contains(ecPointFormats, 0)) {
            return ecPointFormats;
        }
        throw new TlsFatalAlert(47);
    }

    public static String getNameOfNamedCurve(int namedCurve) {
        if (isSupportedNamedCurve(namedCurve)) {
            return CURVE_NAMES[namedCurve - 1];
        }
        return null;
    }

    public static ECDomainParameters getParametersForNamedCurve(int namedCurve) {
        String curveName = getNameOfNamedCurve(namedCurve);
        if (curveName == null) {
            return null;
        }
        X9ECParameters ecP = CustomNamedCurves.getByName(curveName);
        if (ecP == null) {
            ecP = ECNamedCurveTable.getByName(curveName);
            if (ecP == null) {
                return null;
            }
        }
        return new ECDomainParameters(ecP.getCurve(), ecP.getG(), ecP.getN(), ecP.getH(), ecP.getSeed());
    }

    public static boolean hasAnySupportedNamedCurves() {
        return CURVE_NAMES.length > 0;
    }

    public static boolean containsECCCipherSuites(int[] cipherSuites) {
        for (int isECCCipherSuite : cipherSuites) {
            if (isECCCipherSuite(isECCCipherSuite)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isECCCipherSuite(int cipherSuite) {
        switch (cipherSuite) {
            case CipherSuite.TLS_ECDH_ECDSA_WITH_NULL_SHA /*49153*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA /*49154*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA /*49155*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA /*49156*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA /*49157*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA /*49158*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA /*49159*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA /*49160*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA /*49161*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA /*49162*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_NULL_SHA /*49163*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA /*49164*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA /*49165*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA /*49166*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA /*49167*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA /*49168*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA /*49169*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA /*49170*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA /*49171*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA /*49172*/:
            case CipherSuite.TLS_ECDH_anon_WITH_NULL_SHA /*49173*/:
            case CipherSuite.TLS_ECDH_anon_WITH_RC4_128_SHA /*49174*/:
            case CipherSuite.TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA /*49175*/:
            case CipherSuite.TLS_ECDH_anon_WITH_AES_128_CBC_SHA /*49176*/:
            case CipherSuite.TLS_ECDH_anon_WITH_AES_256_CBC_SHA /*49177*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 /*49187*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 /*49188*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 /*49189*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 /*49190*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 /*49191*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 /*49192*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 /*49193*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 /*49194*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 /*49195*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 /*49196*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 /*49197*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 /*49198*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 /*49199*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 /*49200*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 /*49201*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 /*49202*/:
            case CipherSuite.TLS_ECDHE_PSK_WITH_RC4_128_SHA /*49203*/:
            case CipherSuite.TLS_ECDHE_PSK_WITH_3DES_EDE_CBC_SHA /*49204*/:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA /*49205*/:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA /*49206*/:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256 /*49207*/:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384 /*49208*/:
            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA /*49209*/:
            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256 /*49210*/:
            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA384 /*49211*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /*49266*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /*49267*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /*49268*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /*49269*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /*49270*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384 /*49271*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256 /*49272*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384 /*49273*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /*49286*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /*49287*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /*49288*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /*49289*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /*49290*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /*49291*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /*49292*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /*49293*/:
            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /*49306*/:
            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /*49307*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM /*49324*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM /*49325*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 /*49326*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 /*49327*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /*52243*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /*52244*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_ESTREAM_SALSA20_SHA1 /*58386*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_SALSA20_SHA1 /*58387*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ESTREAM_SALSA20_SHA1 /*58388*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_SALSA20_SHA1 /*58389*/:
            case CipherSuite.TLS_ECDHE_PSK_WITH_ESTREAM_SALSA20_SHA1 /*58392*/:
            case CipherSuite.TLS_ECDHE_PSK_WITH_SALSA20_SHA1 /*58393*/:
                return true;
            default:
                return false;
        }
    }

    public static boolean areOnSameCurve(ECDomainParameters a, ECDomainParameters b) {
        return a.getCurve().equals(b.getCurve()) && a.getG().equals(b.getG()) && a.getN().equals(b.getN()) && a.getH().equals(b.getH());
    }

    public static boolean isSupportedNamedCurve(int namedCurve) {
        return namedCurve > 0 && namedCurve <= CURVE_NAMES.length;
    }

    public static boolean isCompressionPreferred(short[] ecPointFormats, short compressionFormat) {
        if (ecPointFormats == null) {
            return false;
        }
        for (short ecPointFormat : ecPointFormats) {
            if (ecPointFormat == 0) {
                return false;
            }
            if (ecPointFormat == compressionFormat) {
                return true;
            }
        }
        return false;
    }

    public static byte[] serializeECFieldElement(int fieldSize, BigInteger x) throws IOException {
        return BigIntegers.asUnsignedByteArray((fieldSize + 7) / 8, x);
    }

    public static byte[] serializeECPoint(short[] ecPointFormats, ECPoint point) throws IOException {
        ECCurve curve = point.getCurve();
        boolean compressed = false;
        if (ECAlgorithms.isFpCurve(curve)) {
            compressed = isCompressionPreferred(ecPointFormats, 1);
        } else if (ECAlgorithms.isF2mCurve(curve)) {
            compressed = isCompressionPreferred(ecPointFormats, 2);
        }
        return point.getEncoded(compressed);
    }

    public static byte[] serializeECPublicKey(short[] ecPointFormats, ECPublicKeyParameters keyParameters) throws IOException {
        return serializeECPoint(ecPointFormats, keyParameters.getQ());
    }

    public static BigInteger deserializeECFieldElement(int fieldSize, byte[] encoding) throws IOException {
        if (encoding.length == (fieldSize + 7) / 8) {
            return new BigInteger(1, encoding);
        }
        throw new TlsFatalAlert(50);
    }

    public static ECPoint deserializeECPoint(short[] ecPointFormats, ECCurve curve, byte[] encoding) throws IOException {
        short actualFormat;
        if (encoding == null || encoding.length < 1) {
            throw new TlsFatalAlert(47);
        }
        switch (encoding[0]) {
            case 2:
            case 3:
                if (ECAlgorithms.isF2mCurve(curve)) {
                    actualFormat = 2;
                    break;
                } else if (ECAlgorithms.isFpCurve(curve)) {
                    actualFormat = 1;
                    break;
                } else {
                    throw new TlsFatalAlert(47);
                }
            case 4:
                actualFormat = 0;
                break;
            default:
                throw new TlsFatalAlert(47);
        }
        if (actualFormat == 0 || (ecPointFormats != null && Arrays.contains(ecPointFormats, actualFormat))) {
            return curve.decodePoint(encoding);
        }
        throw new TlsFatalAlert(47);
    }

    public static ECPublicKeyParameters deserializeECPublicKey(short[] ecPointFormats, ECDomainParameters curve_params, byte[] encoding) throws IOException {
        try {
            return new ECPublicKeyParameters(deserializeECPoint(ecPointFormats, curve_params.getCurve(), encoding), curve_params);
        } catch (RuntimeException e) {
            throw new TlsFatalAlert(47, e);
        }
    }

    public static byte[] calculateECDHBasicAgreement(ECPublicKeyParameters publicKey, ECPrivateKeyParameters privateKey) {
        ECDHBasicAgreement basicAgreement = new ECDHBasicAgreement();
        basicAgreement.init(privateKey);
        return BigIntegers.asUnsignedByteArray(basicAgreement.getFieldSize(), basicAgreement.calculateAgreement(publicKey));
    }

    public static AsymmetricCipherKeyPair generateECKeyPair(SecureRandom random, ECDomainParameters ecParams) {
        ECKeyPairGenerator keyPairGenerator = new ECKeyPairGenerator();
        keyPairGenerator.init(new ECKeyGenerationParameters(ecParams, random));
        return keyPairGenerator.generateKeyPair();
    }

    public static ECPrivateKeyParameters generateEphemeralClientKeyExchange(SecureRandom random, short[] ecPointFormats, ECDomainParameters ecParams, OutputStream output) throws IOException {
        AsymmetricCipherKeyPair kp = generateECKeyPair(random, ecParams);
        writeECPoint(ecPointFormats, ((ECPublicKeyParameters) kp.getPublic()).getQ(), output);
        return (ECPrivateKeyParameters) kp.getPrivate();
    }

    static ECPrivateKeyParameters generateEphemeralServerKeyExchange(SecureRandom random, int[] namedCurves, short[] ecPointFormats, OutputStream output) throws IOException {
        int namedCurve = -1;
        if (namedCurves != null) {
            int i = 0;
            while (true) {
                if (i >= namedCurves.length) {
                    break;
                }
                int entry = namedCurves[i];
                if (NamedCurve.isValid(entry) && isSupportedNamedCurve(entry)) {
                    namedCurve = entry;
                    break;
                }
                i++;
            }
        } else {
            namedCurve = 23;
        }
        ECDomainParameters ecParams = null;
        if (namedCurve >= 0) {
            ecParams = getParametersForNamedCurve(namedCurve);
        } else if (Arrays.contains(namedCurves, 65281)) {
            ecParams = getParametersForNamedCurve(23);
        } else if (Arrays.contains(namedCurves, (int) NamedCurve.arbitrary_explicit_char2_curves)) {
            ecParams = getParametersForNamedCurve(10);
        }
        if (ecParams == null) {
            throw new TlsFatalAlert(80);
        }
        if (namedCurve < 0) {
            writeExplicitECParameters(ecPointFormats, ecParams, output);
        } else {
            writeNamedECParameters(namedCurve, output);
        }
        return generateEphemeralClientKeyExchange(random, ecPointFormats, ecParams, output);
    }

    public static ECPublicKeyParameters validateECPublicKey(ECPublicKeyParameters key) throws IOException {
        return key;
    }

    public static int readECExponent(int fieldSize, InputStream input) throws IOException {
        BigInteger K = readECParameter(input);
        if (K.bitLength() < 32) {
            int k = K.intValue();
            if (k > 0 && k < fieldSize) {
                return k;
            }
        }
        throw new TlsFatalAlert(47);
    }

    public static BigInteger readECFieldElement(int fieldSize, InputStream input) throws IOException {
        return deserializeECFieldElement(fieldSize, TlsUtils.readOpaque8(input));
    }

    public static BigInteger readECParameter(InputStream input) throws IOException {
        return new BigInteger(1, TlsUtils.readOpaque8(input));
    }

    public static ECDomainParameters readECParameters(int[] namedCurves, short[] ecPointFormats, InputStream input) throws IOException {
        try {
            switch (TlsUtils.readUint8(input)) {
                case 1:
                    checkNamedCurve(namedCurves, 65281);
                    BigInteger prime_p = readECParameter(input);
                    BigInteger a = readECFieldElement(prime_p.bitLength(), input);
                    BigInteger b = readECFieldElement(prime_p.bitLength(), input);
                    byte[] baseEncoding = TlsUtils.readOpaque8(input);
                    BigInteger order = readECParameter(input);
                    BigInteger cofactor = readECParameter(input);
                    ECCurve curve = new C5397Fp(prime_p, a, b, order, cofactor);
                    return new ECDomainParameters(curve, deserializeECPoint(ecPointFormats, curve, baseEncoding), order, cofactor);
                case 2:
                    checkNamedCurve(namedCurves, NamedCurve.arbitrary_explicit_char2_curves);
                    int m = TlsUtils.readUint16(input);
                    short basis = TlsUtils.readUint8(input);
                    if (!ECBasisType.isValid(basis)) {
                        throw new TlsFatalAlert(47);
                    }
                    int k1 = readECExponent(m, input);
                    int k2 = -1;
                    int k3 = -1;
                    if (basis == 2) {
                        k2 = readECExponent(m, input);
                        k3 = readECExponent(m, input);
                    }
                    BigInteger a2 = readECFieldElement(m, input);
                    BigInteger b2 = readECFieldElement(m, input);
                    byte[] baseEncoding2 = TlsUtils.readOpaque8(input);
                    BigInteger order2 = readECParameter(input);
                    BigInteger cofactor2 = readECParameter(input);
                    ECCurve curve2 = basis == 2 ? new F2m(m, k1, k2, k3, a2, b2, order2, cofactor2) : new F2m(m, k1, a2, b2, order2, cofactor2);
                    return new ECDomainParameters(curve2, deserializeECPoint(ecPointFormats, curve2, baseEncoding2), order2, cofactor2);
                case 3:
                    int namedCurve = TlsUtils.readUint16(input);
                    if (!NamedCurve.refersToASpecificNamedCurve(namedCurve)) {
                        throw new TlsFatalAlert(47);
                    }
                    checkNamedCurve(namedCurves, namedCurve);
                    return getParametersForNamedCurve(namedCurve);
                default:
                    throw new TlsFatalAlert(47);
            }
        } catch (RuntimeException e) {
            throw new TlsFatalAlert(47, e);
        }
        throw new TlsFatalAlert(47, e);
    }

    private static void checkNamedCurve(int[] namedCurves, int namedCurve) throws IOException {
        if (namedCurves != null && !Arrays.contains(namedCurves, namedCurve)) {
            throw new TlsFatalAlert(47);
        }
    }

    public static void writeECExponent(int k, OutputStream output) throws IOException {
        writeECParameter(BigInteger.valueOf((long) k), output);
    }

    public static void writeECFieldElement(ECFieldElement x, OutputStream output) throws IOException {
        TlsUtils.writeOpaque8(x.getEncoded(), output);
    }

    public static void writeECFieldElement(int fieldSize, BigInteger x, OutputStream output) throws IOException {
        TlsUtils.writeOpaque8(serializeECFieldElement(fieldSize, x), output);
    }

    public static void writeECParameter(BigInteger x, OutputStream output) throws IOException {
        TlsUtils.writeOpaque8(BigIntegers.asUnsignedByteArray(x), output);
    }

    public static void writeExplicitECParameters(short[] ecPointFormats, ECDomainParameters ecParameters, OutputStream output) throws IOException {
        ECCurve curve = ecParameters.getCurve();
        if (ECAlgorithms.isFpCurve(curve)) {
            TlsUtils.writeUint8(1, output);
            writeECParameter(curve.getField().getCharacteristic(), output);
        } else if (ECAlgorithms.isF2mCurve(curve)) {
            int[] exponents = ((PolynomialExtensionField) curve.getField()).getMinimalPolynomial().getExponentsPresent();
            TlsUtils.writeUint8(2, output);
            int m = exponents[exponents.length - 1];
            TlsUtils.checkUint16(m);
            TlsUtils.writeUint16(m, output);
            if (exponents.length == 3) {
                TlsUtils.writeUint8(1, output);
                writeECExponent(exponents[1], output);
            } else if (exponents.length == 5) {
                TlsUtils.writeUint8(2, output);
                writeECExponent(exponents[1], output);
                writeECExponent(exponents[2], output);
                writeECExponent(exponents[3], output);
            } else {
                throw new IllegalArgumentException("Only trinomial and pentomial curves are supported");
            }
        } else {
            throw new IllegalArgumentException("'ecParameters' not a known curve type");
        }
        writeECFieldElement(curve.getA(), output);
        writeECFieldElement(curve.getB(), output);
        TlsUtils.writeOpaque8(serializeECPoint(ecPointFormats, ecParameters.getG()), output);
        writeECParameter(ecParameters.getN(), output);
        writeECParameter(ecParameters.getH(), output);
    }

    public static void writeECPoint(short[] ecPointFormats, ECPoint point, OutputStream output) throws IOException {
        TlsUtils.writeOpaque8(serializeECPoint(ecPointFormats, point), output);
    }

    public static void writeNamedECParameters(int namedCurve, OutputStream output) throws IOException {
        if (!NamedCurve.refersToASpecificNamedCurve(namedCurve)) {
            throw new TlsFatalAlert(80);
        }
        TlsUtils.writeUint8(3, output);
        TlsUtils.checkUint16(namedCurve);
        TlsUtils.writeUint16(namedCurve, output);
    }
}
