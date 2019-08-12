package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;

public class TlsECDHKeyExchange extends AbstractTlsKeyExchange {
    protected TlsAgreementCredentials agreementCredentials;
    protected short[] clientECPointFormats;
    protected ECPrivateKeyParameters ecAgreePrivateKey;
    protected ECPublicKeyParameters ecAgreePublicKey;
    protected int[] namedCurves;
    protected short[] serverECPointFormats;
    protected AsymmetricKeyParameter serverPublicKey;
    protected TlsSigner tlsSigner;

    public TlsECDHKeyExchange(int keyExchange, Vector supportedSignatureAlgorithms, int[] namedCurves2, short[] clientECPointFormats2, short[] serverECPointFormats2) {
        super(keyExchange, supportedSignatureAlgorithms);
        switch (keyExchange) {
            case 16:
            case 18:
                this.tlsSigner = null;
                break;
            case 17:
                this.tlsSigner = new TlsECDSASigner();
                break;
            case 19:
                this.tlsSigner = new TlsRSASigner();
                break;
            default:
                throw new IllegalArgumentException("unsupported key exchange algorithm");
        }
        this.namedCurves = namedCurves2;
        this.clientECPointFormats = clientECPointFormats2;
        this.serverECPointFormats = serverECPointFormats2;
    }

    public void init(TlsContext context) {
        super.init(context);
        if (this.tlsSigner != null) {
            this.tlsSigner.init(context);
        }
    }

    public void skipServerCredentials() throws IOException {
        throw new TlsFatalAlert(10);
    }

    public void processServerCertificate(Certificate serverCertificate) throws IOException {
        if (serverCertificate.isEmpty()) {
            throw new TlsFatalAlert(42);
        }
        Certificate x509Cert = serverCertificate.getCertificateAt(0);
        try {
            this.serverPublicKey = PublicKeyFactory.createKey(x509Cert.getSubjectPublicKeyInfo());
            if (this.tlsSigner == null) {
                try {
                    this.ecAgreePublicKey = TlsECCUtils.validateECPublicKey((ECPublicKeyParameters) this.serverPublicKey);
                    TlsUtils.validateKeyUsage(x509Cert, 8);
                } catch (ClassCastException e) {
                    throw new TlsFatalAlert(46, e);
                }
            } else if (!this.tlsSigner.isValidPublicKey(this.serverPublicKey)) {
                throw new TlsFatalAlert(46);
            } else {
                TlsUtils.validateKeyUsage(x509Cert, 128);
            }
            super.processServerCertificate(serverCertificate);
        } catch (RuntimeException e2) {
            throw new TlsFatalAlert(43, e2);
        }
    }

    public boolean requiresServerKeyExchange() {
        switch (this.keyExchange) {
            case 17:
            case 19:
            case 20:
                return true;
            default:
                return false;
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
                case 65:
                case 66:
                    i++;
                default:
                    throw new TlsFatalAlert(47);
            }
        }
    }

    public void processClientCredentials(TlsCredentials clientCredentials) throws IOException {
        if (clientCredentials instanceof TlsAgreementCredentials) {
            this.agreementCredentials = (TlsAgreementCredentials) clientCredentials;
        } else if (!(clientCredentials instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert(80);
        }
    }

    public void generateClientKeyExchange(OutputStream output) throws IOException {
        if (this.agreementCredentials == null) {
            this.ecAgreePrivateKey = TlsECCUtils.generateEphemeralClientKeyExchange(this.context.getSecureRandom(), this.serverECPointFormats, this.ecAgreePublicKey.getParameters(), output);
        }
    }

    public void processClientCertificate(Certificate clientCertificate) throws IOException {
    }

    public void processClientKeyExchange(InputStream input) throws IOException {
        if (this.ecAgreePublicKey == null) {
            byte[] point = TlsUtils.readOpaque8(input);
            this.ecAgreePublicKey = TlsECCUtils.validateECPublicKey(TlsECCUtils.deserializeECPublicKey(this.serverECPointFormats, this.ecAgreePrivateKey.getParameters(), point));
        }
    }

    public byte[] generatePremasterSecret() throws IOException {
        if (this.agreementCredentials != null) {
            return this.agreementCredentials.generateAgreement(this.ecAgreePublicKey);
        }
        if (this.ecAgreePrivateKey != null) {
            return TlsECCUtils.calculateECDHBasicAgreement(this.ecAgreePublicKey, this.ecAgreePrivateKey);
        }
        throw new TlsFatalAlert(80);
    }
}
