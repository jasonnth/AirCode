package com.paypal.android.sdk.onetouch.core.encryption;

import com.paypal.android.sdk.onetouch.core.exception.InvalidEncryptionDataException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class OtcCrypto {
    private byte[] dataDigest(byte[] data, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac sha256HMAC = Mac.getInstance("HmacSHA256");
        sha256HMAC.init(new SecretKeySpec(key, "HmacSHA256"));
        return sha256HMAC.doFinal(data);
    }

    public byte[] generateRandom256BitKey() {
        return EncryptionUtils.generateRandomData(32);
    }

    public byte[] encryptRSAData(byte[] plainData, Certificate certificate) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidEncryptionDataException {
        if (plainData.length > 214) {
            throw new InvalidEncryptionDataException("Data is too large for public key encryption: " + plainData.length + " > " + 214);
        }
        PublicKey publicKey = certificate.getPublicKey();
        Cipher rsaCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        rsaCipher.init(1, publicKey);
        return rsaCipher.doFinal(plainData);
    }

    public byte[] decryptAESCTRData(byte[] cipherData, byte[] key) throws IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, IllegalArgumentException, InvalidAlgorithmParameterException, NoSuchPaddingException, BadPaddingException, InvalidEncryptionDataException {
        if (cipherData.length < 48) {
            throw new InvalidEncryptionDataException("data is too small");
        }
        byte[] encryptionKey = new byte[16];
        System.arraycopy(key, 0, encryptionKey, 0, 16);
        byte[] digestKey = new byte[16];
        System.arraycopy(key, 16, digestKey, 0, 16);
        byte[] signature = new byte[32];
        System.arraycopy(cipherData, 0, signature, 0, 32);
        byte[] signedData = new byte[(cipherData.length - 32)];
        System.arraycopy(cipherData, 32, signedData, 0, cipherData.length - 32);
        if (!EncryptionUtils.isEqual(dataDigest(signedData, digestKey), signature)) {
            throw new IllegalArgumentException("Signature mismatch");
        }
        byte[] nonceData = new byte[16];
        System.arraycopy(signedData, 0, nonceData, 0, 16);
        IvParameterSpec nonceSpec = new IvParameterSpec(nonceData);
        SecretKeySpec keySpec = new SecretKeySpec(encryptionKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(2, keySpec, nonceSpec);
        return cipher.doFinal(signedData, 16, signedData.length - 16);
    }
}
