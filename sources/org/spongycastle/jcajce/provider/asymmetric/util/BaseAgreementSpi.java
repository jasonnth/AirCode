package org.spongycastle.jcajce.provider.asymmetric.util;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import javax.crypto.KeyAgreementSpi;
import javax.crypto.SecretKey;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.spongycastle.asn1.gnu.GNUObjectIdentifiers;
import org.spongycastle.asn1.kisa.KISAObjectIdentifiers;
import org.spongycastle.asn1.misc.MiscObjectIdentifiers;
import org.spongycastle.asn1.nist.NISTObjectIdentifiers;
import org.spongycastle.asn1.ntt.NTTObjectIdentifiers;
import org.spongycastle.asn1.oiw.OIWObjectIdentifiers;
import org.spongycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.spongycastle.crypto.DerivationFunction;
import org.spongycastle.crypto.agreement.kdf.DHKDFParameters;
import org.spongycastle.crypto.agreement.kdf.DHKEKGenerator;
import org.spongycastle.crypto.params.DESParameters;
import org.spongycastle.crypto.params.KDFParameters;
import org.spongycastle.util.Integers;
import org.spongycastle.util.Strings;

public abstract class BaseAgreementSpi extends KeyAgreementSpi {
    private static final Map<String, ASN1ObjectIdentifier> defaultOids = new HashMap();
    private static final Hashtable des = new Hashtable();
    private static final Map<String, Integer> keySizes = new HashMap();
    private static final Map<String, String> nameTable = new HashMap();
    private static final Hashtable oids = new Hashtable();
    private final String kaAlgorithm;
    private final DerivationFunction kdf;
    protected BigInteger result;
    protected byte[] ukmParameters;

    /* access modifiers changed from: protected */
    public abstract byte[] bigIntToBytes(BigInteger bigInteger);

    static {
        Integer i64 = Integers.valueOf(64);
        Integer i128 = Integers.valueOf(128);
        Integer i192 = Integers.valueOf(192);
        Integer i256 = Integers.valueOf(256);
        keySizes.put("DES", i64);
        keySizes.put("DESEDE", i192);
        keySizes.put("BLOWFISH", i128);
        keySizes.put("AES", i256);
        keySizes.put(NISTObjectIdentifiers.id_aes128_ECB.getId(), i128);
        keySizes.put(NISTObjectIdentifiers.id_aes192_ECB.getId(), i192);
        keySizes.put(NISTObjectIdentifiers.id_aes256_ECB.getId(), i256);
        keySizes.put(NISTObjectIdentifiers.id_aes128_CBC.getId(), i128);
        keySizes.put(NISTObjectIdentifiers.id_aes192_CBC.getId(), i192);
        keySizes.put(NISTObjectIdentifiers.id_aes256_CBC.getId(), i256);
        keySizes.put(NISTObjectIdentifiers.id_aes128_CFB.getId(), i128);
        keySizes.put(NISTObjectIdentifiers.id_aes192_CFB.getId(), i192);
        keySizes.put(NISTObjectIdentifiers.id_aes256_CFB.getId(), i256);
        keySizes.put(NISTObjectIdentifiers.id_aes128_OFB.getId(), i128);
        keySizes.put(NISTObjectIdentifiers.id_aes192_OFB.getId(), i192);
        keySizes.put(NISTObjectIdentifiers.id_aes256_OFB.getId(), i256);
        keySizes.put(NISTObjectIdentifiers.id_aes128_wrap.getId(), i128);
        keySizes.put(NISTObjectIdentifiers.id_aes192_wrap.getId(), i192);
        keySizes.put(NISTObjectIdentifiers.id_aes256_wrap.getId(), i256);
        keySizes.put(NISTObjectIdentifiers.id_aes128_CCM.getId(), i128);
        keySizes.put(NISTObjectIdentifiers.id_aes192_CCM.getId(), i192);
        keySizes.put(NISTObjectIdentifiers.id_aes256_CCM.getId(), i256);
        keySizes.put(NISTObjectIdentifiers.id_aes128_GCM.getId(), i128);
        keySizes.put(NISTObjectIdentifiers.id_aes192_GCM.getId(), i192);
        keySizes.put(NISTObjectIdentifiers.id_aes256_GCM.getId(), i256);
        keySizes.put(NTTObjectIdentifiers.id_camellia128_wrap.getId(), i128);
        keySizes.put(NTTObjectIdentifiers.id_camellia192_wrap.getId(), i192);
        keySizes.put(NTTObjectIdentifiers.id_camellia256_wrap.getId(), i256);
        keySizes.put(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap.getId(), i128);
        keySizes.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId(), i192);
        keySizes.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), i192);
        keySizes.put(OIWObjectIdentifiers.desCBC.getId(), i64);
        keySizes.put(PKCSObjectIdentifiers.id_hmacWithSHA1.getId(), Integers.valueOf(160));
        keySizes.put(PKCSObjectIdentifiers.id_hmacWithSHA256.getId(), i256);
        keySizes.put(PKCSObjectIdentifiers.id_hmacWithSHA384.getId(), Integers.valueOf(384));
        keySizes.put(PKCSObjectIdentifiers.id_hmacWithSHA512.getId(), Integers.valueOf(512));
        defaultOids.put("DESEDE", PKCSObjectIdentifiers.des_EDE3_CBC);
        defaultOids.put("AES", NISTObjectIdentifiers.id_aes256_CBC);
        defaultOids.put("CAMELLIA", NTTObjectIdentifiers.id_camellia256_cbc);
        defaultOids.put("SEED", KISAObjectIdentifiers.id_seedCBC);
        defaultOids.put("DES", OIWObjectIdentifiers.desCBC);
        nameTable.put(MiscObjectIdentifiers.cast5CBC.getId(), "CAST5");
        nameTable.put(MiscObjectIdentifiers.as_sys_sec_alg_ideaCBC.getId(), "IDEA");
        nameTable.put(MiscObjectIdentifiers.cryptlib_algorithm_blowfish_ECB.getId(), "Blowfish");
        nameTable.put(MiscObjectIdentifiers.cryptlib_algorithm_blowfish_CBC.getId(), "Blowfish");
        nameTable.put(MiscObjectIdentifiers.cryptlib_algorithm_blowfish_CFB.getId(), "Blowfish");
        nameTable.put(MiscObjectIdentifiers.cryptlib_algorithm_blowfish_OFB.getId(), "Blowfish");
        nameTable.put(OIWObjectIdentifiers.desECB.getId(), "DES");
        nameTable.put(OIWObjectIdentifiers.desCBC.getId(), "DES");
        nameTable.put(OIWObjectIdentifiers.desCFB.getId(), "DES");
        nameTable.put(OIWObjectIdentifiers.desOFB.getId(), "DES");
        nameTable.put(OIWObjectIdentifiers.desEDE.getId(), "DESede");
        nameTable.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), "DESede");
        nameTable.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId(), "DESede");
        nameTable.put(PKCSObjectIdentifiers.id_alg_CMSRC2wrap.getId(), "RC2");
        nameTable.put(PKCSObjectIdentifiers.id_hmacWithSHA1.getId(), "HmacSHA1");
        nameTable.put(PKCSObjectIdentifiers.id_hmacWithSHA224.getId(), "HmacSHA224");
        nameTable.put(PKCSObjectIdentifiers.id_hmacWithSHA256.getId(), "HmacSHA256");
        nameTable.put(PKCSObjectIdentifiers.id_hmacWithSHA384.getId(), "HmacSHA384");
        nameTable.put(PKCSObjectIdentifiers.id_hmacWithSHA512.getId(), "HmacSHA512");
        nameTable.put(NTTObjectIdentifiers.id_camellia128_cbc.getId(), "Camellia");
        nameTable.put(NTTObjectIdentifiers.id_camellia192_cbc.getId(), "Camellia");
        nameTable.put(NTTObjectIdentifiers.id_camellia256_cbc.getId(), "Camellia");
        nameTable.put(NTTObjectIdentifiers.id_camellia128_wrap.getId(), "Camellia");
        nameTable.put(NTTObjectIdentifiers.id_camellia192_wrap.getId(), "Camellia");
        nameTable.put(NTTObjectIdentifiers.id_camellia256_wrap.getId(), "Camellia");
        nameTable.put(KISAObjectIdentifiers.id_npki_app_cmsSeed_wrap.getId(), "SEED");
        nameTable.put(KISAObjectIdentifiers.id_seedCBC.getId(), "SEED");
        nameTable.put(KISAObjectIdentifiers.id_seedMAC.getId(), "SEED");
        nameTable.put(CryptoProObjectIdentifiers.gostR28147_gcfb.getId(), "GOST28147");
        nameTable.put(NISTObjectIdentifiers.id_aes128_wrap.getId(), "AES");
        nameTable.put(NISTObjectIdentifiers.id_aes128_CCM.getId(), "AES");
        nameTable.put(NISTObjectIdentifiers.id_aes128_CCM.getId(), "AES");
        oids.put("DESEDE", PKCSObjectIdentifiers.des_EDE3_CBC);
        oids.put("AES", NISTObjectIdentifiers.id_aes256_CBC);
        oids.put("DES", OIWObjectIdentifiers.desCBC);
        des.put("DES", "DES");
        des.put("DESEDE", "DES");
        des.put(OIWObjectIdentifiers.desCBC.getId(), "DES");
        des.put(PKCSObjectIdentifiers.des_EDE3_CBC.getId(), "DES");
        des.put(PKCSObjectIdentifiers.id_alg_CMS3DESwrap.getId(), "DES");
    }

    public BaseAgreementSpi(String kaAlgorithm2, DerivationFunction kdf2) {
        this.kaAlgorithm = kaAlgorithm2;
        this.kdf = kdf2;
    }

    protected static String getAlgorithm(String algDetails) {
        if (algDetails.indexOf(91) > 0) {
            return algDetails.substring(0, algDetails.indexOf(91));
        }
        if (algDetails.startsWith(NISTObjectIdentifiers.aes.getId())) {
            return "AES";
        }
        if (algDetails.startsWith(GNUObjectIdentifiers.Serpent.getId())) {
            return "Serpent";
        }
        String name = (String) nameTable.get(Strings.toUpperCase(algDetails));
        if (name == null) {
            return algDetails;
        }
        return name;
    }

    protected static int getKeySize(String algDetails) {
        if (algDetails.indexOf(91) > 0) {
            return (Integer.parseInt(algDetails.substring(algDetails.indexOf(91) + 1, algDetails.indexOf(93))) + 7) / 8;
        }
        String algKey = Strings.toUpperCase(algDetails);
        if (!keySizes.containsKey(algKey)) {
            return -1;
        }
        return ((Integer) keySizes.get(algKey)).intValue();
    }

    protected static byte[] trimZeroes(byte[] secret) {
        if (secret[0] != 0) {
            return secret;
        }
        int ind = 0;
        while (ind < secret.length && secret[ind] == 0) {
            ind++;
        }
        byte[] rv = new byte[(secret.length - ind)];
        System.arraycopy(secret, ind, rv, 0, rv.length);
        return rv;
    }

    /* access modifiers changed from: protected */
    public byte[] engineGenerateSecret() throws IllegalStateException {
        if (this.kdf == null) {
            return bigIntToBytes(this.result);
        }
        throw new UnsupportedOperationException("KDF can only be used when algorithm is known");
    }

    /* access modifiers changed from: protected */
    public int engineGenerateSecret(byte[] sharedSecret, int offset) throws IllegalStateException, ShortBufferException {
        byte[] secret = engineGenerateSecret();
        if (sharedSecret.length - offset < secret.length) {
            throw new ShortBufferException(this.kaAlgorithm + " key agreement: need " + secret.length + " bytes");
        }
        System.arraycopy(secret, 0, sharedSecret, offset, secret.length);
        return secret.length;
    }

    /* access modifiers changed from: protected */
    public SecretKey engineGenerateSecret(String algorithm) throws NoSuchAlgorithmException {
        byte[] secret = bigIntToBytes(this.result);
        String algKey = Strings.toUpperCase(algorithm);
        String oidAlgorithm = algorithm;
        if (oids.containsKey(algKey)) {
            oidAlgorithm = ((ASN1ObjectIdentifier) oids.get(algKey)).getId();
        }
        int keySize = getKeySize(oidAlgorithm);
        if (this.kdf != null) {
            if (keySize < 0) {
                throw new NoSuchAlgorithmException("unknown algorithm encountered: " + oidAlgorithm);
            }
            byte[] keyBytes = new byte[(keySize / 8)];
            if (this.kdf instanceof DHKEKGenerator) {
                try {
                    this.kdf.init(new DHKDFParameters(new ASN1ObjectIdentifier(oidAlgorithm), keySize, secret, this.ukmParameters));
                } catch (IllegalArgumentException e) {
                    throw new NoSuchAlgorithmException("no OID for algorithm: " + oidAlgorithm);
                }
            } else {
                this.kdf.init(new KDFParameters(secret, this.ukmParameters));
            }
            this.kdf.generateBytes(keyBytes, 0, keyBytes.length);
            secret = keyBytes;
        } else if (keySize > 0) {
            byte[] keyBytes2 = new byte[(keySize / 8)];
            System.arraycopy(secret, 0, keyBytes2, 0, keyBytes2.length);
            secret = keyBytes2;
        }
        if (des.containsKey(oidAlgorithm)) {
            DESParameters.setOddParity(secret);
        }
        return new SecretKeySpec(secret, getAlgorithm(algorithm));
    }
}
