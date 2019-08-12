package org.jmrtd;

import java.security.Provider;
import java.security.Security;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import org.spongycastle.jce.provider.BouncyCastleProvider;

public class JMRTDSecurityProvider extends Provider {
    private static final Provider BC_PROVIDER = new BouncyCastleProvider();
    private static final String BC_PROVIDER_CLASS_NAME = "org.spongycastle.jce.provider.BouncyCastleProvider";
    private static final Provider JMRTD_PROVIDER = new JMRTDSecurityProvider();
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final Provider SC_PROVIDER = new BouncyCastleProvider();
    private static final String SC_PROVIDER_CLASS_NAME = "org.spongycastle.jce.provider.BouncyCastleProvider";
    private static final String SUN_PROVIDER_CLASS_NAME = "sun.security.provider.Sun";
    private static final long serialVersionUID = -2881416441551680704L;

    static {
        if (BC_PROVIDER != null) {
            Security.insertProviderAt(BC_PROVIDER, 1);
        }
        if (SC_PROVIDER != null) {
            Security.insertProviderAt(SC_PROVIDER, 2);
        }
        if (JMRTD_PROVIDER != null) {
            Security.insertProviderAt(JMRTD_PROVIDER, 3);
        }
    }

    private JMRTDSecurityProvider() {
        super("JMRTD", 0.1d, "JMRTD Security Provider");
        put("CertificateFactory.CVC", "org.jmrtd.cert.CVCertificateFactorySpi");
        if (BC_PROVIDER != null) {
            replicateFromProvider("CertificateFactory", "X.509", getBouncyCastleProvider());
            replicateFromProvider("CertStore", "Collection", getBouncyCastleProvider());
            replicateFromProvider("MessageDigest", "SHA1", getBouncyCastleProvider());
            replicateFromProvider("Signature", "SHA1withRSA/ISO9796-2", getBouncyCastleProvider());
            replicateFromProvider("Signature", "MD2withRSA", getBouncyCastleProvider());
            replicateFromProvider("Signature", "MD4withRSA", getBouncyCastleProvider());
            replicateFromProvider("Signature", "MD5withRSA", getBouncyCastleProvider());
            replicateFromProvider("Signature", "SHA1withRSA", getBouncyCastleProvider());
            replicateFromProvider("Signature", "SHA1withRSA/ISO9796-2", getBouncyCastleProvider());
            replicateFromProvider("Signature", "SHA256withRSA", getBouncyCastleProvider());
            replicateFromProvider("Signature", "SHA256withRSA/ISO9796-2", getBouncyCastleProvider());
            replicateFromProvider("Signature", "SHA384withRSA", getBouncyCastleProvider());
            replicateFromProvider("Signature", "SHA384withRSA/ISO9796-2", getBouncyCastleProvider());
            replicateFromProvider("Signature", "SHA512withRSA", getBouncyCastleProvider());
            replicateFromProvider("Signature", "SHA512withRSA/ISO9796-2", getBouncyCastleProvider());
            replicateFromProvider("Signature", "SHA224withRSA", getBouncyCastleProvider());
            replicateFromProvider("Signature", "SHA224withRSA/ISO9796-2", getBouncyCastleProvider());
            put("Alg.Alias.Mac.ISO9797Alg3Mac", "ISO9797ALG3MAC");
            put("Alg.Alias.CertificateFactory.X509", "X.509");
        }
    }

    private void replicateFromProvider(String str, String str2, Provider provider) {
        String str3 = str + "." + str2;
        Object obj = provider.get(str3);
        if (obj != null) {
            put(str3, obj);
        }
    }

    public static Provider getInstance() {
        return JMRTD_PROVIDER;
    }

    public static int beginPreferBouncyCastleProvider() {
        Provider bouncyCastleProvider = getBouncyCastleProvider();
        if (bouncyCastleProvider == null) {
            return -1;
        }
        Provider[] providers = Security.getProviders();
        for (int i = 0; i < providers.length; i++) {
            Provider provider = providers[i];
            if (bouncyCastleProvider.getClass().getCanonicalName().equals(provider.getClass().getCanonicalName())) {
                Security.removeProvider(provider.getName());
                Security.insertProviderAt(bouncyCastleProvider, 1);
                return i + 1;
            }
        }
        return -1;
    }

    public static void endPreferBouncyCastleProvider(int i) {
        Provider bouncyCastleProvider = getBouncyCastleProvider();
        Security.removeProvider(bouncyCastleProvider.getName());
        if (i > 0) {
            Security.insertProviderAt(bouncyCastleProvider, i);
        }
    }

    public static Provider getBouncyCastleProvider() {
        if (BC_PROVIDER != null) {
            return BC_PROVIDER;
        }
        if (SC_PROVIDER != null) {
            return SC_PROVIDER;
        }
        LOGGER.severe("No Bouncy or Spongy provider");
        return null;
    }

    public static Provider getSpongyCastleProvider() {
        if (SC_PROVIDER != null) {
            return SC_PROVIDER;
        }
        if (BC_PROVIDER != null) {
            return BC_PROVIDER;
        }
        LOGGER.severe("No Bouncy or Spongy provider");
        return null;
    }

    private static Provider getProvider(String str, String str2) {
        List providers = getProviders(str, str2);
        if (providers == null || providers.size() <= 0) {
            return null;
        }
        return (Provider) providers.get(0);
    }

    private static List<Provider> getProviders(String str, String str2) {
        if (Security.getAlgorithms(str).contains(str2)) {
            return new ArrayList(Arrays.asList(Security.getProviders(str + "." + str2)));
        }
        if (BC_PROVIDER != null && BC_PROVIDER.getService(str, str2) != null) {
            return new ArrayList(Collections.singletonList(BC_PROVIDER));
        }
        if (SC_PROVIDER != null && SC_PROVIDER.getService(str, str2) != null) {
            return new ArrayList(Collections.singletonList(SC_PROVIDER));
        }
        if (JMRTD_PROVIDER == null || JMRTD_PROVIDER.getService(str, str2) == null) {
            return null;
        }
        return new ArrayList(Collections.singletonList(JMRTD_PROVIDER));
    }
}
