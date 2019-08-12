package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

public abstract class AbstractTlsKeyExchange implements TlsKeyExchange {
    protected TlsContext context;
    protected int keyExchange;
    protected Vector supportedSignatureAlgorithms;

    protected AbstractTlsKeyExchange(int keyExchange2, Vector supportedSignatureAlgorithms2) {
        this.keyExchange = keyExchange2;
        this.supportedSignatureAlgorithms = supportedSignatureAlgorithms2;
    }

    /* access modifiers changed from: protected */
    public DigitallySigned parseSignature(InputStream input) throws IOException {
        DigitallySigned signature = DigitallySigned.parse(this.context, input);
        SignatureAndHashAlgorithm signatureAlgorithm = signature.getAlgorithm();
        if (signatureAlgorithm != null) {
            TlsUtils.verifySupportedSignatureAlgorithm(this.supportedSignatureAlgorithms, signatureAlgorithm);
        }
        return signature;
    }

    public void init(TlsContext context2) {
        this.context = context2;
        ProtocolVersion clientVersion = context2.getClientVersion();
        if (TlsUtils.isSignatureAlgorithmsExtensionAllowed(clientVersion)) {
            if (this.supportedSignatureAlgorithms == null) {
                switch (this.keyExchange) {
                    case 1:
                    case 5:
                    case 9:
                    case 15:
                    case 18:
                    case 19:
                    case 23:
                        this.supportedSignatureAlgorithms = TlsUtils.getDefaultRSASignatureAlgorithms();
                        return;
                    case 3:
                    case 7:
                    case 22:
                        this.supportedSignatureAlgorithms = TlsUtils.getDefaultDSSSignatureAlgorithms();
                        return;
                    case 13:
                    case 14:
                    case 21:
                    case 24:
                        return;
                    case 16:
                    case 17:
                        this.supportedSignatureAlgorithms = TlsUtils.getDefaultECDSASignatureAlgorithms();
                        return;
                    default:
                        throw new IllegalStateException("unsupported key exchange algorithm");
                }
            }
        } else if (this.supportedSignatureAlgorithms != null) {
            throw new IllegalStateException("supported_signature_algorithms not allowed for " + clientVersion);
        }
    }

    public void processServerCertificate(Certificate serverCertificate) throws IOException {
        if (this.supportedSignatureAlgorithms == null) {
        }
    }

    public void processServerCredentials(TlsCredentials serverCredentials) throws IOException {
        processServerCertificate(serverCredentials.getCertificate());
    }

    public boolean requiresServerKeyExchange() {
        return false;
    }

    public byte[] generateServerKeyExchange() throws IOException {
        if (!requiresServerKeyExchange()) {
            return null;
        }
        throw new TlsFatalAlert(80);
    }

    public void skipServerKeyExchange() throws IOException {
        if (requiresServerKeyExchange()) {
            throw new TlsFatalAlert(10);
        }
    }

    public void processServerKeyExchange(InputStream input) throws IOException {
        if (!requiresServerKeyExchange()) {
            throw new TlsFatalAlert(10);
        }
    }

    public void skipClientCredentials() throws IOException {
    }

    public void processClientCertificate(Certificate clientCertificate) throws IOException {
    }

    public void processClientKeyExchange(InputStream input) throws IOException {
        throw new TlsFatalAlert(80);
    }
}
