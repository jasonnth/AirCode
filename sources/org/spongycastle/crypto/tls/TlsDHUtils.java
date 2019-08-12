package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Hashtable;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.agreement.DHBasicAgreement;
import org.spongycastle.crypto.generators.DHBasicKeyPairGenerator;
import org.spongycastle.crypto.params.DHKeyGenerationParameters;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.Integers;
import org.spongycastle.util.encoders.Hex;

public class TlsDHUtils {
    public static final Integer EXT_negotiated_ff_dhe_groups = Integers.valueOf(101);
    static final BigInteger TWO = BigInteger.valueOf(2);
    static final DHParameters draft_ffdhe2432 = fromSafeP(draft_ffdhe2432_p);
    private static final String draft_ffdhe2432_p = "FFFFFFFFFFFFFFFFADF85458A2BB4A9AAFDC5620273D3CF1D8B9C583CE2D3695A9E13641146433FBCC939DCE249B3EF97D2FE363630C75D8F681B202AEC4617AD3DF1ED5D5FD65612433F51F5F066ED0856365553DED1AF3B557135E7F57C935984F0C70E0E68B77E2A689DAF3EFE8721DF158A136ADE73530ACCA4F483A797ABC0AB182B324FB61D108A94BB2C8E3FBB96ADAB760D7F4681D4F42A3DE394DF4AE56EDE76372BB190B07A7C8EE0A6D709E02FCE1CDF7E2ECC03404CD28342F619172FE9CE98583FF8E4F1232EEF28183C3FE3B1B4C6FAD733BB5FCBC2EC22005C58EF1837D1683B2C6F34A26C1B2EFFA886B4238611FCFDCDE355B3B6519035BBC34F4DEF99C023861B46FC9D6E6C9077AD91D2691F7F7EE598CB0FAC186D91CAEFE13098533C8B3FFFFFFFFFFFFFFFF";
    static final DHParameters draft_ffdhe3072 = fromSafeP(draft_ffdhe3072_p);
    private static final String draft_ffdhe3072_p = "FFFFFFFFFFFFFFFFADF85458A2BB4A9AAFDC5620273D3CF1D8B9C583CE2D3695A9E13641146433FBCC939DCE249B3EF97D2FE363630C75D8F681B202AEC4617AD3DF1ED5D5FD65612433F51F5F066ED0856365553DED1AF3B557135E7F57C935984F0C70E0E68B77E2A689DAF3EFE8721DF158A136ADE73530ACCA4F483A797ABC0AB182B324FB61D108A94BB2C8E3FBB96ADAB760D7F4681D4F42A3DE394DF4AE56EDE76372BB190B07A7C8EE0A6D709E02FCE1CDF7E2ECC03404CD28342F619172FE9CE98583FF8E4F1232EEF28183C3FE3B1B4C6FAD733BB5FCBC2EC22005C58EF1837D1683B2C6F34A26C1B2EFFA886B4238611FCFDCDE355B3B6519035BBC34F4DEF99C023861B46FC9D6E6C9077AD91D2691F7F7EE598CB0FAC186D91CAEFE130985139270B4130C93BC437944F4FD4452E2D74DD364F2E21E71F54BFF5CAE82AB9C9DF69EE86D2BC522363A0DABC521979B0DEADA1DBF9A42D5C4484E0ABCD06BFA53DDEF3C1B20EE3FD59D7C25E41D2B66C62E37FFFFFFFFFFFFFFFF";
    static final DHParameters draft_ffdhe4096 = fromSafeP(draft_ffdhe4096_p);
    private static final String draft_ffdhe4096_p = "FFFFFFFFFFFFFFFFADF85458A2BB4A9AAFDC5620273D3CF1D8B9C583CE2D3695A9E13641146433FBCC939DCE249B3EF97D2FE363630C75D8F681B202AEC4617AD3DF1ED5D5FD65612433F51F5F066ED0856365553DED1AF3B557135E7F57C935984F0C70E0E68B77E2A689DAF3EFE8721DF158A136ADE73530ACCA4F483A797ABC0AB182B324FB61D108A94BB2C8E3FBB96ADAB760D7F4681D4F42A3DE394DF4AE56EDE76372BB190B07A7C8EE0A6D709E02FCE1CDF7E2ECC03404CD28342F619172FE9CE98583FF8E4F1232EEF28183C3FE3B1B4C6FAD733BB5FCBC2EC22005C58EF1837D1683B2C6F34A26C1B2EFFA886B4238611FCFDCDE355B3B6519035BBC34F4DEF99C023861B46FC9D6E6C9077AD91D2691F7F7EE598CB0FAC186D91CAEFE130985139270B4130C93BC437944F4FD4452E2D74DD364F2E21E71F54BFF5CAE82AB9C9DF69EE86D2BC522363A0DABC521979B0DEADA1DBF9A42D5C4484E0ABCD06BFA53DDEF3C1B20EE3FD59D7C25E41D2B669E1EF16E6F52C3164DF4FB7930E9E4E58857B6AC7D5F42D69F6D187763CF1D5503400487F55BA57E31CC7A7135C886EFB4318AED6A1E012D9E6832A907600A918130C46DC778F971AD0038092999A333CB8B7A1A1DB93D7140003C2A4ECEA9F98D0ACC0A8291CDCEC97DCF8EC9B55A7F88A46B4DB5A851F44182E1C68A007E5E655F6AFFFFFFFFFFFFFFFF";
    static final DHParameters draft_ffdhe6144 = fromSafeP(draft_ffdhe6144_p);
    private static final String draft_ffdhe6144_p = "FFFFFFFFFFFFFFFFADF85458A2BB4A9AAFDC5620273D3CF1D8B9C583CE2D3695A9E13641146433FBCC939DCE249B3EF97D2FE363630C75D8F681B202AEC4617AD3DF1ED5D5FD65612433F51F5F066ED0856365553DED1AF3B557135E7F57C935984F0C70E0E68B77E2A689DAF3EFE8721DF158A136ADE73530ACCA4F483A797ABC0AB182B324FB61D108A94BB2C8E3FBB96ADAB760D7F4681D4F42A3DE394DF4AE56EDE76372BB190B07A7C8EE0A6D709E02FCE1CDF7E2ECC03404CD28342F619172FE9CE98583FF8E4F1232EEF28183C3FE3B1B4C6FAD733BB5FCBC2EC22005C58EF1837D1683B2C6F34A26C1B2EFFA886B4238611FCFDCDE355B3B6519035BBC34F4DEF99C023861B46FC9D6E6C9077AD91D2691F7F7EE598CB0FAC186D91CAEFE130985139270B4130C93BC437944F4FD4452E2D74DD364F2E21E71F54BFF5CAE82AB9C9DF69EE86D2BC522363A0DABC521979B0DEADA1DBF9A42D5C4484E0ABCD06BFA53DDEF3C1B20EE3FD59D7C25E41D2B669E1EF16E6F52C3164DF4FB7930E9E4E58857B6AC7D5F42D69F6D187763CF1D5503400487F55BA57E31CC7A7135C886EFB4318AED6A1E012D9E6832A907600A918130C46DC778F971AD0038092999A333CB8B7A1A1DB93D7140003C2A4ECEA9F98D0ACC0A8291CDCEC97DCF8EC9B55A7F88A46B4DB5A851F44182E1C68A007E5E0DD9020BFD64B645036C7A4E677D2C38532A3A23BA4442CAF53EA63BB454329B7624C8917BDD64B1C0FD4CB38E8C334C701C3ACDAD0657FCCFEC719B1F5C3E4E46041F388147FB4CFDB477A52471F7A9A96910B855322EDB6340D8A00EF092350511E30ABEC1FFF9E3A26E7FB29F8C183023C3587E38DA0077D9B4763E4E4B94B2BBC194C6651E77CAF992EEAAC0232A281BF6B3A739C1226116820AE8DB5847A67CBEF9C9091B462D538CD72B03746AE77F5E62292C311562A846505DC82DB854338AE49F5235C95B91178CCF2DD5CACEF403EC9D1810C6272B045B3B71F9DC6B80D63FDD4A8E9ADB1E6962A69526D43161C1A41D570D7938DAD4A40E329CD0E40E65FFFFFFFFFFFFFFFF";
    static final DHParameters draft_ffdhe8192 = fromSafeP(draft_ffdhe8192_p);
    private static final String draft_ffdhe8192_p = "FFFFFFFFFFFFFFFFADF85458A2BB4A9AAFDC5620273D3CF1D8B9C583CE2D3695A9E13641146433FBCC939DCE249B3EF97D2FE363630C75D8F681B202AEC4617AD3DF1ED5D5FD65612433F51F5F066ED0856365553DED1AF3B557135E7F57C935984F0C70E0E68B77E2A689DAF3EFE8721DF158A136ADE73530ACCA4F483A797ABC0AB182B324FB61D108A94BB2C8E3FBB96ADAB760D7F4681D4F42A3DE394DF4AE56EDE76372BB190B07A7C8EE0A6D709E02FCE1CDF7E2ECC03404CD28342F619172FE9CE98583FF8E4F1232EEF28183C3FE3B1B4C6FAD733BB5FCBC2EC22005C58EF1837D1683B2C6F34A26C1B2EFFA886B4238611FCFDCDE355B3B6519035BBC34F4DEF99C023861B46FC9D6E6C9077AD91D2691F7F7EE598CB0FAC186D91CAEFE130985139270B4130C93BC437944F4FD4452E2D74DD364F2E21E71F54BFF5CAE82AB9C9DF69EE86D2BC522363A0DABC521979B0DEADA1DBF9A42D5C4484E0ABCD06BFA53DDEF3C1B20EE3FD59D7C25E41D2B669E1EF16E6F52C3164DF4FB7930E9E4E58857B6AC7D5F42D69F6D187763CF1D5503400487F55BA57E31CC7A7135C886EFB4318AED6A1E012D9E6832A907600A918130C46DC778F971AD0038092999A333CB8B7A1A1DB93D7140003C2A4ECEA9F98D0ACC0A8291CDCEC97DCF8EC9B55A7F88A46B4DB5A851F44182E1C68A007E5E0DD9020BFD64B645036C7A4E677D2C38532A3A23BA4442CAF53EA63BB454329B7624C8917BDD64B1C0FD4CB38E8C334C701C3ACDAD0657FCCFEC719B1F5C3E4E46041F388147FB4CFDB477A52471F7A9A96910B855322EDB6340D8A00EF092350511E30ABEC1FFF9E3A26E7FB29F8C183023C3587E38DA0077D9B4763E4E4B94B2BBC194C6651E77CAF992EEAAC0232A281BF6B3A739C1226116820AE8DB5847A67CBEF9C9091B462D538CD72B03746AE77F5E62292C311562A846505DC82DB854338AE49F5235C95B91178CCF2DD5CACEF403EC9D1810C6272B045B3B71F9DC6B80D63FDD4A8E9ADB1E6962A69526D43161C1A41D570D7938DAD4A40E329CCFF46AAA36AD004CF600C8381E425A31D951AE64FDB23FCEC9509D43687FEB69EDD1CC5E0B8CC3BDF64B10EF86B63142A3AB8829555B2F747C932665CB2C0F1CC01BD70229388839D2AF05E454504AC78B7582822846C0BA35C35F5C59160CC046FD8251541FC68C9C86B022BB7099876A460E7451A8A93109703FEE1C217E6C3826E52C51AA691E0E423CFC99E9E31650C1217B624816CDAD9A95F9D5B8019488D9C0A0A1FE3075A577E23183F81D4A3F2FA4571EFC8CE0BA8A4FE8B6855DFE72B0A66EDED2FBABFBE58A30FAFABE1C5D71A87E2F741EF8C1FE86FEA6BBFDE530677F0D97D11D49F7A8443D0822E506A9F4614E011E2A94838FF88CD68C8BB7C5C6424CFFFFFFFFFFFFFFFF";

    private static BigInteger fromHex(String hex) {
        return new BigInteger(1, Hex.decode(hex));
    }

    private static DHParameters fromSafeP(String hexP) {
        BigInteger p = fromHex(hexP);
        return new DHParameters(p, TWO, p.shiftRight(1));
    }

    public static void addNegotiatedDHEGroupsClientExtension(Hashtable extensions, short[] dheGroups) throws IOException {
        extensions.put(EXT_negotiated_ff_dhe_groups, createNegotiatedDHEGroupsClientExtension(dheGroups));
    }

    public static void addNegotiatedDHEGroupsServerExtension(Hashtable extensions, short dheGroup) throws IOException {
        extensions.put(EXT_negotiated_ff_dhe_groups, createNegotiatedDHEGroupsServerExtension(dheGroup));
    }

    public static short[] getNegotiatedDHEGroupsClientExtension(Hashtable extensions) throws IOException {
        byte[] extensionData = TlsUtils.getExtensionData(extensions, EXT_negotiated_ff_dhe_groups);
        if (extensionData == null) {
            return null;
        }
        return readNegotiatedDHEGroupsClientExtension(extensionData);
    }

    public static short getNegotiatedDHEGroupsServerExtension(Hashtable extensions) throws IOException {
        byte[] extensionData = TlsUtils.getExtensionData(extensions, EXT_negotiated_ff_dhe_groups);
        if (extensionData == null) {
            return -1;
        }
        return readNegotiatedDHEGroupsServerExtension(extensionData);
    }

    public static byte[] createNegotiatedDHEGroupsClientExtension(short[] dheGroups) throws IOException {
        if (dheGroups != null && dheGroups.length >= 1 && dheGroups.length <= 255) {
            return TlsUtils.encodeUint8ArrayWithUint8Length(dheGroups);
        }
        throw new TlsFatalAlert(80);
    }

    public static byte[] createNegotiatedDHEGroupsServerExtension(short dheGroup) throws IOException {
        TlsUtils.checkUint8(dheGroup);
        byte[] extensionData = new byte[1];
        TlsUtils.writeUint8(dheGroup, extensionData, 0);
        return extensionData;
    }

    public static short[] readNegotiatedDHEGroupsClientExtension(byte[] extensionData) throws IOException {
        if (extensionData == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        }
        ByteArrayInputStream buf = new ByteArrayInputStream(extensionData);
        short length = TlsUtils.readUint8(buf);
        if (length < 1) {
            throw new TlsFatalAlert(50);
        }
        short[] dheGroups = TlsUtils.readUint8Array(length, buf);
        TlsProtocol.assertEmpty(buf);
        return dheGroups;
    }

    public static short readNegotiatedDHEGroupsServerExtension(byte[] extensionData) throws IOException {
        if (extensionData == null) {
            throw new IllegalArgumentException("'extensionData' cannot be null");
        } else if (extensionData.length == 1) {
            return TlsUtils.readUint8(extensionData, 0);
        } else {
            throw new TlsFatalAlert(50);
        }
    }

    public static DHParameters getParametersForDHEGroup(short dheGroup) {
        switch (dheGroup) {
            case 0:
                return draft_ffdhe2432;
            case 1:
                return draft_ffdhe3072;
            case 2:
                return draft_ffdhe4096;
            case 3:
                return draft_ffdhe6144;
            case 4:
                return draft_ffdhe8192;
            default:
                return null;
        }
    }

    public static boolean containsDHECipherSuites(int[] cipherSuites) {
        for (int isDHECipherSuite : cipherSuites) {
            if (isDHECipherSuite(isDHECipherSuite)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isDHECipherSuite(int cipherSuite) {
        switch (cipherSuite) {
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 45:
            case 50:
            case 51:
            case 56:
            case 57:
            case 64:
            case 68:
            case 69:
            case 103:
            case 106:
            case 107:
            case 135:
            case 136:
            case 142:
            case CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA /*143*/:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA /*144*/:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA /*145*/:
            case CipherSuite.TLS_DHE_DSS_WITH_SEED_CBC_SHA /*153*/:
            case CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA /*154*/:
            case 158:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 /*159*/:
            case CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 /*162*/:
            case CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 /*163*/:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256 /*170*/:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384 /*171*/:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256 /*178*/:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384 /*179*/:
            case 180:
            case CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384 /*181*/:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256 /*189*/:
            case 190:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256 /*195*/:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256 /*196*/:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /*49276*/:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /*49277*/:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256 /*49280*/:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384 /*49281*/:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256 /*49296*/:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384 /*49297*/:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /*49302*/:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /*49303*/:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM /*49310*/:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM /*49311*/:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8 /*49314*/:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8 /*49315*/:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM /*49318*/:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM /*49319*/:
            case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8 /*49322*/:
            case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8 /*49323*/:
            case CipherSuite.TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /*52245*/:
            case CipherSuite.TLS_DHE_PSK_WITH_ESTREAM_SALSA20_SHA1 /*58396*/:
            case CipherSuite.TLS_DHE_PSK_WITH_SALSA20_SHA1 /*58397*/:
            case CipherSuite.TLS_DHE_RSA_WITH_ESTREAM_SALSA20_SHA1 /*58398*/:
            case CipherSuite.TLS_DHE_RSA_WITH_SALSA20_SHA1 /*58399*/:
                return true;
            default:
                return false;
        }
    }

    public static boolean areCompatibleParameters(DHParameters a, DHParameters b) {
        return a.getP().equals(b.getP()) && a.getG().equals(b.getG());
    }

    public static byte[] calculateDHBasicAgreement(DHPublicKeyParameters publicKey, DHPrivateKeyParameters privateKey) {
        DHBasicAgreement basicAgreement = new DHBasicAgreement();
        basicAgreement.init(privateKey);
        return BigIntegers.asUnsignedByteArray(basicAgreement.calculateAgreement(publicKey));
    }

    public static AsymmetricCipherKeyPair generateDHKeyPair(SecureRandom random, DHParameters dhParams) {
        DHBasicKeyPairGenerator dhGen = new DHBasicKeyPairGenerator();
        dhGen.init(new DHKeyGenerationParameters(random, dhParams));
        return dhGen.generateKeyPair();
    }

    public static DHPrivateKeyParameters generateEphemeralClientKeyExchange(SecureRandom random, DHParameters dhParams, OutputStream output) throws IOException {
        AsymmetricCipherKeyPair kp = generateDHKeyPair(random, dhParams);
        writeDHParameter(((DHPublicKeyParameters) kp.getPublic()).getY(), output);
        return (DHPrivateKeyParameters) kp.getPrivate();
    }

    public static DHPrivateKeyParameters generateEphemeralServerKeyExchange(SecureRandom random, DHParameters dhParams, OutputStream output) throws IOException {
        AsymmetricCipherKeyPair kp = generateDHKeyPair(random, dhParams);
        new ServerDHParams((DHPublicKeyParameters) kp.getPublic()).encode(output);
        return (DHPrivateKeyParameters) kp.getPrivate();
    }

    public static DHParameters validateDHParameters(DHParameters params) throws IOException {
        BigInteger p = params.getP();
        BigInteger g = params.getG();
        if (!p.isProbablePrime(2)) {
            throw new TlsFatalAlert(47);
        } else if (g.compareTo(TWO) >= 0 && g.compareTo(p.subtract(TWO)) <= 0) {
            return params;
        } else {
            throw new TlsFatalAlert(47);
        }
    }

    public static DHPublicKeyParameters validateDHPublicKey(DHPublicKeyParameters key) throws IOException {
        DHParameters params = validateDHParameters(key.getParameters());
        BigInteger Y = key.getY();
        if (Y.compareTo(TWO) >= 0 && Y.compareTo(params.getP().subtract(TWO)) <= 0) {
            return key;
        }
        throw new TlsFatalAlert(47);
    }

    public static BigInteger readDHParameter(InputStream input) throws IOException {
        return new BigInteger(1, TlsUtils.readOpaque16(input));
    }

    public static void writeDHParameter(BigInteger x, OutputStream output) throws IOException {
        TlsUtils.writeOpaque16(BigIntegers.asUnsignedByteArray(x), output);
    }
}
