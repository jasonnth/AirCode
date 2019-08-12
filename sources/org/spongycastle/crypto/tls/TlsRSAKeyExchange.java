package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.p333io.Streams;

public class TlsRSAKeyExchange extends AbstractTlsKeyExchange {
    protected byte[] premasterSecret;
    protected RSAKeyParameters rsaServerPublicKey = null;
    protected TlsEncryptionCredentials serverCredentials = null;
    protected AsymmetricKeyParameter serverPublicKey = null;

    public TlsRSAKeyExchange(Vector supportedSignatureAlgorithms) {
        super(1, supportedSignatureAlgorithms);
    }

    public void skipServerCredentials() throws IOException {
        throw new TlsFatalAlert(10);
    }

    public void processServerCredentials(TlsCredentials serverCredentials2) throws IOException {
        if (!(serverCredentials2 instanceof TlsEncryptionCredentials)) {
            throw new TlsFatalAlert(80);
        }
        processServerCertificate(serverCredentials2.getCertificate());
        this.serverCredentials = (TlsEncryptionCredentials) serverCredentials2;
    }

    public void processServerCertificate(Certificate serverCertificate) throws IOException {
        if (serverCertificate.isEmpty()) {
            throw new TlsFatalAlert(42);
        }
        Certificate x509Cert = serverCertificate.getCertificateAt(0);
        try {
            this.serverPublicKey = PublicKeyFactory.createKey(x509Cert.getSubjectPublicKeyInfo());
            if (this.serverPublicKey.isPrivate()) {
                throw new TlsFatalAlert(80);
            }
            this.rsaServerPublicKey = validateRSAPublicKey((RSAKeyParameters) this.serverPublicKey);
            TlsUtils.validateKeyUsage(x509Cert, 32);
            super.processServerCertificate(serverCertificate);
        } catch (RuntimeException e) {
            throw new TlsFatalAlert(43, e);
        }
    }

    public void validateCertificateRequest(CertificateRequest certificateRequest) throws IOException {
        short[] types = certificateRequest.getCertificateTypes();
        int i = 0;
        while (i < types.length) {
            switch (types[i]) {
                case 1:
                case 2:
                case 64:
                    i++;
                default:
                    throw new TlsFatalAlert(47);
            }
        }
    }

    public void processClientCredentials(TlsCredentials clientCredentials) throws IOException {
        if (!(clientCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert(80);
        }
    }

    public void generateClientKeyExchange(OutputStream output) throws IOException {
        this.premasterSecret = TlsRSAUtils.generateEncryptedPreMasterSecret(this.context, this.rsaServerPublicKey, output);
    }

    public void processClientKeyExchange(InputStream input) throws IOException {
        byte[] encryptedPreMasterSecret;
        if (TlsUtils.isSSL(this.context)) {
            encryptedPreMasterSecret = Streams.readAll(input);
        } else {
            encryptedPreMasterSecret = TlsUtils.readOpaque16(input);
        }
        this.premasterSecret = this.serverCredentials.decryptPreMasterSecret(encryptedPreMasterSecret);
    }

    public byte[] generatePremasterSecret() throws IOException {
        if (this.premasterSecret == null) {
            throw new TlsFatalAlert(80);
        }
        byte[] tmp = this.premasterSecret;
        this.premasterSecret = null;
        return tmp;
    }

    /* access modifiers changed from: protected */
    public RSAKeyParameters validateRSAPublicKey(RSAKeyParameters key) throws IOException {
        if (key.getExponent().isProbablePrime(2)) {
            return key;
        }
        throw new TlsFatalAlert(47);
    }
}
