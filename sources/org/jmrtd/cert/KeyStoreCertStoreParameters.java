package org.jmrtd.cert;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.CertStoreParameters;
import java.util.logging.Logger;
import org.jmrtd.JMRTDSecurityProvider;

public class KeyStoreCertStoreParameters implements Cloneable, CertStoreParameters {
    private static final String DEFAULT_ALGORITHM = "JKS";
    private static final char[] DEFAULT_PASSWORD = "".toCharArray();
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private KeyStore keyStore;

    public KeyStoreCertStoreParameters(URI uri) throws KeyStoreException {
        this(uri, DEFAULT_ALGORITHM, DEFAULT_PASSWORD);
    }

    public KeyStoreCertStoreParameters(URI uri, char[] cArr) throws KeyStoreException {
        this(uri, DEFAULT_ALGORITHM, cArr);
    }

    public KeyStoreCertStoreParameters(URI uri, String str) throws KeyStoreException {
        this(uri, str, DEFAULT_PASSWORD);
    }

    public KeyStoreCertStoreParameters(URI uri, String str, char[] cArr) throws KeyStoreException {
        this(readKeyStore(uri, str, cArr));
    }

    public KeyStoreCertStoreParameters(KeyStore keyStore2) {
        this.keyStore = keyStore2;
    }

    public KeyStore getKeyStore() {
        return this.keyStore;
    }

    public Object clone() {
        return new KeyStoreCertStoreParameters(this.keyStore);
    }

    private static KeyStore readKeyStore(URI uri, String str, char[] cArr) throws KeyStoreException {
        try {
            int beginPreferBouncyCastleProvider = JMRTDSecurityProvider.beginPreferBouncyCastleProvider();
            InputStream inputStream = uri.toURL().openConnection().getInputStream();
            KeyStore instance = KeyStore.getInstance(str);
            try {
                LOGGER.info("KeystoreCertStore will use provider for KeyStore: " + instance.getProvider().getClass().getCanonicalName());
                instance.load(inputStream, cArr);
            } catch (IOException e) {
                LOGGER.warning("Cannot read this file \"" + uri + "\" as keystore");
            }
            inputStream.close();
            JMRTDSecurityProvider.endPreferBouncyCastleProvider(beginPreferBouncyCastleProvider);
            return instance;
        } catch (Exception e2) {
            throw new KeyStoreException("Error getting keystore: " + e2.getMessage());
        }
    }
}
