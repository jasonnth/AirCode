package org.spongycastle.crypto.tls;

import java.math.BigInteger;
import org.spongycastle.crypto.BasicAgreement;
import org.spongycastle.crypto.agreement.DHBasicAgreement;
import org.spongycastle.crypto.agreement.ECDHBasicAgreement;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.util.BigIntegers;

public class DefaultTlsAgreementCredentials extends AbstractTlsAgreementCredentials {
    protected BasicAgreement basicAgreement;
    protected Certificate certificate;
    protected AsymmetricKeyParameter privateKey;
    protected boolean truncateAgreement;

    public DefaultTlsAgreementCredentials(Certificate certificate2, AsymmetricKeyParameter privateKey2) {
        if (certificate2 == null) {
            throw new IllegalArgumentException("'certificate' cannot be null");
        } else if (certificate2.isEmpty()) {
            throw new IllegalArgumentException("'certificate' cannot be empty");
        } else if (privateKey2 == null) {
            throw new IllegalArgumentException("'privateKey' cannot be null");
        } else if (!privateKey2.isPrivate()) {
            throw new IllegalArgumentException("'privateKey' must be private");
        } else {
            if (privateKey2 instanceof DHPrivateKeyParameters) {
                this.basicAgreement = new DHBasicAgreement();
                this.truncateAgreement = true;
            } else if (privateKey2 instanceof ECPrivateKeyParameters) {
                this.basicAgreement = new ECDHBasicAgreement();
                this.truncateAgreement = false;
            } else {
                throw new IllegalArgumentException("'privateKey' type not supported: " + privateKey2.getClass().getName());
            }
            this.certificate = certificate2;
            this.privateKey = privateKey2;
        }
    }

    public Certificate getCertificate() {
        return this.certificate;
    }

    public byte[] generateAgreement(AsymmetricKeyParameter peerPublicKey) {
        this.basicAgreement.init(this.privateKey);
        BigInteger agreementValue = this.basicAgreement.calculateAgreement(peerPublicKey);
        if (this.truncateAgreement) {
            return BigIntegers.asUnsignedByteArray(agreementValue);
        }
        return BigIntegers.asUnsignedByteArray(this.basicAgreement.getFieldSize(), agreementValue);
    }
}
