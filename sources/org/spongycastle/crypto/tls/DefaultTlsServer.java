package org.spongycastle.crypto.tls;

import java.io.IOException;
import org.spongycastle.crypto.agreement.DHStandardGroups;
import org.spongycastle.crypto.params.DHParameters;

public abstract class DefaultTlsServer extends AbstractTlsServer {
    public DefaultTlsServer() {
    }

    public DefaultTlsServer(TlsCipherFactory cipherFactory) {
        super(cipherFactory);
    }

    /* access modifiers changed from: protected */
    public TlsSignerCredentials getDSASignerCredentials() throws IOException {
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public TlsSignerCredentials getECDSASignerCredentials() throws IOException {
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public TlsEncryptionCredentials getRSAEncryptionCredentials() throws IOException {
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public TlsSignerCredentials getRSASignerCredentials() throws IOException {
        throw new TlsFatalAlert(80);
    }

    /* access modifiers changed from: protected */
    public DHParameters getDHParameters() {
        return DHStandardGroups.rfc5114_2048_256;
    }

    /* access modifiers changed from: protected */
    public int[] getCipherSuites() {
        return new int[]{CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA, CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, 158, 107, 103, 57, 51, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, 61, 60, 53, 47};
    }

    public TlsCredentials getCredentials() throws IOException {
        switch (TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite)) {
            case 1:
                return getRSAEncryptionCredentials();
            case 3:
            case 7:
                return getDSASignerCredentials();
            case 5:
            case 19:
                return getRSASignerCredentials();
            case 16:
            case 17:
                return getECDSASignerCredentials();
            default:
                throw new TlsFatalAlert(80);
        }
    }

    public TlsKeyExchange getKeyExchange() throws IOException {
        int keyExchangeAlgorithm = TlsUtils.getKeyExchangeAlgorithm(this.selectedCipherSuite);
        switch (keyExchangeAlgorithm) {
            case 1:
                return createRSAKeyExchange();
            case 3:
            case 5:
                return createDHEKeyExchange(keyExchangeAlgorithm);
            case 7:
            case 9:
                return createDHKeyExchange(keyExchangeAlgorithm);
            case 16:
            case 18:
                return createECDHKeyExchange(keyExchangeAlgorithm);
            case 17:
            case 19:
                return createECDHEKeyExchange(keyExchangeAlgorithm);
            default:
                throw new TlsFatalAlert(80);
        }
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createDHKeyExchange(int keyExchange) {
        return new TlsDHKeyExchange(keyExchange, this.supportedSignatureAlgorithms, getDHParameters());
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createDHEKeyExchange(int keyExchange) {
        return new TlsDHEKeyExchange(keyExchange, this.supportedSignatureAlgorithms, getDHParameters());
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createECDHKeyExchange(int keyExchange) {
        return new TlsECDHKeyExchange(keyExchange, this.supportedSignatureAlgorithms, this.namedCurves, this.clientECPointFormats, this.serverECPointFormats);
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createECDHEKeyExchange(int keyExchange) {
        return new TlsECDHEKeyExchange(keyExchange, this.supportedSignatureAlgorithms, this.namedCurves, this.clientECPointFormats, this.serverECPointFormats);
    }

    /* access modifiers changed from: protected */
    public TlsKeyExchange createRSAKeyExchange() {
        return new TlsRSAKeyExchange(this.supportedSignatureAlgorithms);
    }
}
