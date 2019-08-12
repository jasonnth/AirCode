package com.jumio.core.network;

import com.jumio.commons.log.Log;
import java.security.cert.CertificateException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

public class JumioTrustManagerV3 implements X509TrustManager {
    private static Boolean certificateCheck = Boolean.valueOf(true);
    private X509TrustManager defaultTrustManager = null;

    public JumioTrustManagerV3() {
        try {
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(null);
            this.defaultTrustManager = (X509TrustManager) tmf.getTrustManagers()[0];
        } catch (Exception e) {
            this.defaultTrustManager = null;
        }
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if (chain.length < 2) {
            throw new CertificateException("SSL Certificate Chain is not sufficient");
        }
        if (certificateCheck.booleanValue()) {
            this.defaultTrustManager.checkServerTrusted(chain, authType);
        }
        checkJumioCertificate(chain[0]);
    }

    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    private void checkJumioCertificate(X509Certificate cert) throws CertificateException {
        try {
            cert.checkValidity();
        } catch (CertificateNotYetValidException e) {
            try {
                Log.m1929w("JumioTrustManager", "SSL Certificate is not yet valid");
            } catch (Exception e2) {
                throw new CertificateException("SSL Certificate match error", e2);
            }
        }
    }
}
