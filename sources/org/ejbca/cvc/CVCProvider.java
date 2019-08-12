package org.ejbca.cvc;

import java.security.Provider;

public class CVCProvider extends Provider {
    static String INFO = ("CVC Security Provider " + version + " (supports Card Verifiable Certificates for ePassports)");
    public static String PROVIDER_NAME = "CVC";
    static double version = 1.0d;

    public CVCProvider() {
        super(PROVIDER_NAME, version, INFO);
        put("CertificateFactory.CVC", JDKCVCertificateFactory.class.getName());
        put("Alg.Alias.CertificateFactory.CVC", "CVC");
    }
}
