package org.spongycastle.jcajce.provider.asymmetric.util;

import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.CipherSpi;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.RC2ParameterSpec;
import javax.crypto.spec.RC5ParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.spongycastle.asn1.pkcs.PrivateKeyInfo;
import org.spongycastle.crypto.InvalidCipherTextException;
import org.spongycastle.crypto.Wrapper;
import org.spongycastle.jcajce.util.BCJcaJceHelper;
import org.spongycastle.jcajce.util.JcaJceHelper;
import org.spongycastle.jce.provider.BouncyCastleProvider;

public abstract class BaseCipherSpi extends CipherSpi {
    private Class[] availableSpecs = {IvParameterSpec.class, PBEParameterSpec.class, RC2ParameterSpec.class, RC5ParameterSpec.class};
    protected AlgorithmParameters engineParams = null;
    private final JcaJceHelper helper = new BCJcaJceHelper();

    /* renamed from: iv */
    private byte[] f6913iv;
    private int ivSize;
    protected Wrapper wrapEngine = null;

    protected BaseCipherSpi() {
    }

    /* access modifiers changed from: protected */
    public int engineGetBlockSize() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public byte[] engineGetIV() {
        return null;
    }

    /* access modifiers changed from: protected */
    public int engineGetKeySize(Key key) {
        return key.getEncoded().length;
    }

    /* access modifiers changed from: protected */
    public int engineGetOutputSize(int inputLen) {
        return -1;
    }

    /* access modifiers changed from: protected */
    public AlgorithmParameters engineGetParameters() {
        return null;
    }

    /* access modifiers changed from: protected */
    public final AlgorithmParameters createParametersInstance(String algorithm) throws NoSuchAlgorithmException, NoSuchProviderException {
        return this.helper.createAlgorithmParameters(algorithm);
    }

    /* access modifiers changed from: protected */
    public void engineSetMode(String mode) throws NoSuchAlgorithmException {
        throw new NoSuchAlgorithmException("can't support mode " + mode);
    }

    /* access modifiers changed from: protected */
    public void engineSetPadding(String padding) throws NoSuchPaddingException {
        throw new NoSuchPaddingException("Padding " + padding + " unknown.");
    }

    /* access modifiers changed from: protected */
    public byte[] engineWrap(Key key) throws IllegalBlockSizeException, InvalidKeyException {
        byte[] encoded = key.getEncoded();
        if (encoded == null) {
            throw new InvalidKeyException("Cannot wrap key, null encoding.");
        }
        try {
            if (this.wrapEngine == null) {
                return engineDoFinal(encoded, 0, encoded.length);
            }
            return this.wrapEngine.wrap(encoded, 0, encoded.length);
        } catch (BadPaddingException e) {
            throw new IllegalBlockSizeException(e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public Key engineUnwrap(byte[] wrappedKey, String wrappedKeyAlgorithm, int wrappedKeyType) throws InvalidKeyException {
        byte[] encoded;
        try {
            if (this.wrapEngine == null) {
                encoded = engineDoFinal(wrappedKey, 0, wrappedKey.length);
            } else {
                encoded = this.wrapEngine.unwrap(wrappedKey, 0, wrappedKey.length);
            }
            if (wrappedKeyType == 3) {
                return new SecretKeySpec(encoded, wrappedKeyAlgorithm);
            }
            if (!wrappedKeyAlgorithm.equals("") || wrappedKeyType != 2) {
                try {
                    KeyFactory kf = this.helper.createKeyFactory(wrappedKeyAlgorithm);
                    if (wrappedKeyType == 1) {
                        return kf.generatePublic(new X509EncodedKeySpec(encoded));
                    }
                    if (wrappedKeyType == 2) {
                        return kf.generatePrivate(new PKCS8EncodedKeySpec(encoded));
                    }
                    throw new InvalidKeyException("Unknown key type " + wrappedKeyType);
                } catch (NoSuchAlgorithmException e) {
                    throw new InvalidKeyException("Unknown key type " + e.getMessage());
                } catch (InvalidKeySpecException e2) {
                    throw new InvalidKeyException("Unknown key type " + e2.getMessage());
                } catch (NoSuchProviderException e3) {
                    throw new InvalidKeyException("Unknown key type " + e3.getMessage());
                }
            } else {
                try {
                    PrivateKeyInfo in = PrivateKeyInfo.getInstance(encoded);
                    PrivateKey privKey = BouncyCastleProvider.getPrivateKey(in);
                    if (privKey != null) {
                        return privKey;
                    }
                    throw new InvalidKeyException("algorithm " + in.getPrivateKeyAlgorithm().getAlgorithm() + " not supported");
                } catch (Exception e4) {
                    throw new InvalidKeyException("Invalid key encoding.");
                }
            }
        } catch (InvalidCipherTextException e5) {
            throw new InvalidKeyException(e5.getMessage());
        } catch (BadPaddingException e6) {
            throw new InvalidKeyException(e6.getMessage());
        } catch (IllegalBlockSizeException e22) {
            throw new InvalidKeyException(e22.getMessage());
        }
    }
}
