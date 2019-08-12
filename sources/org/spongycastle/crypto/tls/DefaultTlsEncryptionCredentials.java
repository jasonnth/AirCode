package org.spongycastle.crypto.tls;

import java.io.IOException;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.RSAKeyParameters;

public class DefaultTlsEncryptionCredentials extends AbstractTlsEncryptionCredentials {
    protected Certificate certificate;
    protected TlsContext context;
    protected AsymmetricKeyParameter privateKey;

    public DefaultTlsEncryptionCredentials(TlsContext context2, Certificate certificate2, AsymmetricKeyParameter privateKey2) {
        if (certificate2 == null) {
            throw new IllegalArgumentException("'certificate' cannot be null");
        } else if (certificate2.isEmpty()) {
            throw new IllegalArgumentException("'certificate' cannot be empty");
        } else if (privateKey2 == null) {
            throw new IllegalArgumentException("'privateKey' cannot be null");
        } else if (!privateKey2.isPrivate()) {
            throw new IllegalArgumentException("'privateKey' must be private");
        } else if (privateKey2 instanceof RSAKeyParameters) {
            this.context = context2;
            this.certificate = certificate2;
            this.privateKey = privateKey2;
        } else {
            throw new IllegalArgumentException("'privateKey' type not supported: " + privateKey2.getClass().getName());
        }
    }

    public Certificate getCertificate() {
        return this.certificate;
    }

    public byte[] decryptPreMasterSecret(byte[] encryptedPreMasterSecret) throws IOException {
        return TlsRSAUtils.safeDecryptPreMasterSecret(this.context, (RSAKeyParameters) this.privateKey, encryptedPreMasterSecret);
    }
}
