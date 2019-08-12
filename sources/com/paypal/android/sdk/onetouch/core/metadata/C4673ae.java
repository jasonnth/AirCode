package com.paypal.android.sdk.onetouch.core.metadata;

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
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/* renamed from: com.paypal.android.sdk.onetouch.core.metadata.ae */
public final class C4673ae extends SSLSocketFactory {

    /* renamed from: a */
    private SSLSocketFactory f3826a;

    public C4673ae() {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, null, null);
            this.f3826a = instance.getSocketFactory();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            throw new SSLException(e.getMessage());
        }
    }

    public C4673ae(InputStream inputStream) {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            for (Certificate certificate : CertificateFactory.getInstance("X.509").generateCertificates(inputStream)) {
                if (certificate instanceof X509Certificate) {
                    instance.setCertificateEntry(((X509Certificate) certificate).getSubjectDN().getName(), certificate);
                }
            }
            TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance2.init(instance);
            SSLContext instance3 = SSLContext.getInstance("TLS");
            instance3.init(null, instance2.getTrustManagers(), null);
            this.f3826a = instance3.getSocketFactory();
            try {
                inputStream.close();
            } catch (IOException | NullPointerException e) {
            }
        } catch (Exception e2) {
            throw new SSLException(e2.getMessage());
        } catch (Throwable th) {
            try {
                inputStream.close();
            } catch (IOException | NullPointerException e3) {
            }
            throw th;
        }
    }

    /* renamed from: a */
    private static Socket m2375a(Socket socket) {
        if (socket instanceof SSLSocket) {
            ArrayList arrayList = new ArrayList(Arrays.asList(((SSLSocket) socket).getSupportedProtocols()));
            arrayList.retainAll(Arrays.asList(new String[]{"TLSv1.2", "TLSv1.1", "TLSv1"}));
            ((SSLSocket) socket).setEnabledProtocols((String[]) arrayList.toArray(new String[arrayList.size()]));
        }
        return socket;
    }

    public final Socket createSocket(String str, int i) {
        return m2375a(this.f3826a.createSocket(str, i));
    }

    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return m2375a(this.f3826a.createSocket(str, i, inetAddress, i2));
    }

    public final Socket createSocket(InetAddress inetAddress, int i) {
        return m2375a(this.f3826a.createSocket(inetAddress, i));
    }

    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return m2375a(this.f3826a.createSocket(inetAddress, i, inetAddress2, i2));
    }

    public final Socket createSocket(Socket socket, String str, int i, boolean z) {
        return m2375a(this.f3826a.createSocket(socket, str, i, z));
    }

    public final String[] getDefaultCipherSuites() {
        return this.f3826a.getDefaultCipherSuites();
    }

    public final String[] getSupportedCipherSuites() {
        return this.f3826a.getSupportedCipherSuites();
    }
}
