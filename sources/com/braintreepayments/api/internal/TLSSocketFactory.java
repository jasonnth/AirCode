package com.braintreepayments.api.internal;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

public class TLSSocketFactory extends SSLSocketFactory {
    private SSLSocketFactory mInternalSSLSocketFactory;

    public TLSSocketFactory() throws SSLException {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, null, null);
            this.mInternalSSLSocketFactory = sslContext.getSocketFactory();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new SSLException(e.getMessage());
        }
    }

    public TLSSocketFactory(InputStream certificateStream) throws SSLException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            for (Certificate cert : CertificateFactory.getInstance("X.509").generateCertificates(certificateStream)) {
                if (cert instanceof X509Certificate) {
                    keyStore.setCertificateEntry(((X509Certificate) cert).getSubjectDN().getName(), cert);
                }
            }
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(keyStore);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            this.mInternalSSLSocketFactory = sslContext.getSocketFactory();
            try {
                certificateStream.close();
            } catch (IOException | NullPointerException e) {
            }
        } catch (Exception e2) {
            throw new SSLException(e2.getMessage());
        } catch (Throwable th) {
            try {
                certificateStream.close();
            } catch (IOException | NullPointerException e3) {
            }
            throw th;
        }
    }

    public String[] getDefaultCipherSuites() {
        return this.mInternalSSLSocketFactory.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.mInternalSSLSocketFactory.getSupportedCipherSuites();
    }

    public Socket createSocket(Socket s, String host, int port, boolean autoClose) throws IOException {
        return enableTLSOnSocket(this.mInternalSSLSocketFactory.createSocket(s, host, port, autoClose));
    }

    public Socket createSocket(String host, int port) throws IOException {
        return enableTLSOnSocket(this.mInternalSSLSocketFactory.createSocket(host, port));
    }

    public Socket createSocket(String host, int port, InetAddress localHost, int localPort) throws IOException {
        return enableTLSOnSocket(this.mInternalSSLSocketFactory.createSocket(host, port, localHost, localPort));
    }

    public Socket createSocket(InetAddress host, int port) throws IOException {
        return enableTLSOnSocket(this.mInternalSSLSocketFactory.createSocket(host, port));
    }

    public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort) throws IOException {
        return enableTLSOnSocket(this.mInternalSSLSocketFactory.createSocket(address, port, localAddress, localPort));
    }

    private Socket enableTLSOnSocket(Socket socket) {
        if (socket instanceof SSLSocket) {
            ArrayList<String> supportedProtocols = new ArrayList<>(Arrays.asList(((SSLSocket) socket).getSupportedProtocols()));
            supportedProtocols.retainAll(Collections.singletonList("TLSv1.2"));
            ((SSLSocket) socket).setEnabledProtocols((String[]) supportedProtocols.toArray(new String[supportedProtocols.size()]));
        }
        return socket;
    }
}
