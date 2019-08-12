package org.spongycastle.crypto.tls;

import java.io.IOException;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DSAPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;

public class DefaultTlsSignerCredentials extends AbstractTlsSignerCredentials {
    protected Certificate certificate;
    protected TlsContext context;
    protected AsymmetricKeyParameter privateKey;
    protected SignatureAndHashAlgorithm signatureAndHashAlgorithm;
    protected TlsSigner signer;

    public DefaultTlsSignerCredentials(TlsContext context2, Certificate certificate2, AsymmetricKeyParameter privateKey2) {
        this(context2, certificate2, privateKey2, null);
    }

    public DefaultTlsSignerCredentials(TlsContext context2, Certificate certificate2, AsymmetricKeyParameter privateKey2, SignatureAndHashAlgorithm signatureAndHashAlgorithm2) {
        if (certificate2 == null) {
            throw new IllegalArgumentException("'certificate' cannot be null");
        } else if (certificate2.isEmpty()) {
            throw new IllegalArgumentException("'certificate' cannot be empty");
        } else if (privateKey2 == null) {
            throw new IllegalArgumentException("'privateKey' cannot be null");
        } else if (!privateKey2.isPrivate()) {
            throw new IllegalArgumentException("'privateKey' must be private");
        } else if (!TlsUtils.isTLSv12(context2) || signatureAndHashAlgorithm2 != null) {
            if (privateKey2 instanceof RSAKeyParameters) {
                this.signer = new TlsRSASigner();
            } else if (privateKey2 instanceof DSAPrivateKeyParameters) {
                this.signer = new TlsDSSSigner();
            } else if (privateKey2 instanceof ECPrivateKeyParameters) {
                this.signer = new TlsECDSASigner();
            } else {
                throw new IllegalArgumentException("'privateKey' type not supported: " + privateKey2.getClass().getName());
            }
            this.signer.init(context2);
            this.context = context2;
            this.certificate = certificate2;
            this.privateKey = privateKey2;
            this.signatureAndHashAlgorithm = signatureAndHashAlgorithm2;
        } else {
            throw new IllegalArgumentException("'signatureAndHashAlgorithm' cannot be null for (D)TLS 1.2+");
        }
    }

    public Certificate getCertificate() {
        return this.certificate;
    }

    public byte[] generateCertificateSignature(byte[] hash) throws IOException {
        try {
            if (TlsUtils.isTLSv12(this.context)) {
                return this.signer.generateRawSignature(this.signatureAndHashAlgorithm, this.privateKey, hash);
            }
            return this.signer.generateRawSignature(this.privateKey, hash);
        } catch (CryptoException e) {
            throw new TlsFatalAlert(80, e);
        }
    }

    public SignatureAndHashAlgorithm getSignatureAndHashAlgorithm() {
        return this.signatureAndHashAlgorithm;
    }
}
