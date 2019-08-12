package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.RSAKeyParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.p333io.Streams;

public class TlsPSKKeyExchange extends AbstractTlsKeyExchange {
    protected short[] clientECPointFormats;
    protected DHPrivateKeyParameters dhAgreePrivateKey = null;
    protected DHPublicKeyParameters dhAgreePublicKey = null;
    protected DHParameters dhParameters;
    protected ECPrivateKeyParameters ecAgreePrivateKey = null;
    protected ECPublicKeyParameters ecAgreePublicKey = null;
    protected int[] namedCurves;
    protected byte[] premasterSecret;
    protected byte[] psk = null;
    protected TlsPSKIdentity pskIdentity;
    protected TlsPSKIdentityManager pskIdentityManager;
    protected byte[] psk_identity_hint = null;
    protected RSAKeyParameters rsaServerPublicKey = null;
    protected TlsEncryptionCredentials serverCredentials = null;
    protected short[] serverECPointFormats;
    protected AsymmetricKeyParameter serverPublicKey = null;

    public TlsPSKKeyExchange(int keyExchange, Vector supportedSignatureAlgorithms, TlsPSKIdentity pskIdentity2, TlsPSKIdentityManager pskIdentityManager2, DHParameters dhParameters2, int[] namedCurves2, short[] clientECPointFormats2, short[] serverECPointFormats2) {
        super(keyExchange, supportedSignatureAlgorithms);
        switch (keyExchange) {
            case 13:
            case 14:
            case 15:
            case 24:
                this.pskIdentity = pskIdentity2;
                this.pskIdentityManager = pskIdentityManager2;
                this.dhParameters = dhParameters2;
                this.namedCurves = namedCurves2;
                this.clientECPointFormats = clientECPointFormats2;
                this.serverECPointFormats = serverECPointFormats2;
                return;
            default:
                throw new IllegalArgumentException("unsupported key exchange algorithm");
        }
    }

    public void skipServerCredentials() throws IOException {
        if (this.keyExchange == 15) {
            throw new TlsFatalAlert(10);
        }
    }

    public void processServerCredentials(TlsCredentials serverCredentials2) throws IOException {
        if (!(serverCredentials2 instanceof TlsEncryptionCredentials)) {
            throw new TlsFatalAlert(80);
        }
        processServerCertificate(serverCredentials2.getCertificate());
        this.serverCredentials = (TlsEncryptionCredentials) serverCredentials2;
    }

    public byte[] generateServerKeyExchange() throws IOException {
        this.psk_identity_hint = this.pskIdentityManager.getHint();
        if (this.psk_identity_hint == null && !requiresServerKeyExchange()) {
            return null;
        }
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        if (this.psk_identity_hint == null) {
            TlsUtils.writeOpaque16(TlsUtils.EMPTY_BYTES, buf);
        } else {
            TlsUtils.writeOpaque16(this.psk_identity_hint, buf);
        }
        if (this.keyExchange == 14) {
            if (this.dhParameters == null) {
                throw new TlsFatalAlert(80);
            }
            this.dhAgreePrivateKey = TlsDHUtils.generateEphemeralServerKeyExchange(this.context.getSecureRandom(), this.dhParameters, buf);
        } else if (this.keyExchange == 24) {
            this.ecAgreePrivateKey = TlsECCUtils.generateEphemeralServerKeyExchange(this.context.getSecureRandom(), this.namedCurves, this.clientECPointFormats, buf);
        }
        return buf.toByteArray();
    }

    public void processServerCertificate(Certificate serverCertificate) throws IOException {
        if (this.keyExchange != 15) {
            throw new TlsFatalAlert(10);
        } else if (serverCertificate.isEmpty()) {
            throw new TlsFatalAlert(42);
        } else {
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
    }

    public boolean requiresServerKeyExchange() {
        switch (this.keyExchange) {
            case 14:
            case 24:
                return true;
            default:
                return false;
        }
    }

    public void processServerKeyExchange(InputStream input) throws IOException {
        this.psk_identity_hint = TlsUtils.readOpaque16(input);
        if (this.keyExchange == 14) {
            this.dhAgreePublicKey = TlsDHUtils.validateDHPublicKey(ServerDHParams.parse(input).getPublicKey());
            this.dhParameters = this.dhAgreePublicKey.getParameters();
        } else if (this.keyExchange == 24) {
            this.ecAgreePublicKey = TlsECCUtils.validateECPublicKey(TlsECCUtils.deserializeECPublicKey(this.clientECPointFormats, TlsECCUtils.readECParameters(this.namedCurves, this.clientECPointFormats, input), TlsUtils.readOpaque8(input)));
        }
    }

    public void validateCertificateRequest(CertificateRequest certificateRequest) throws IOException {
        throw new TlsFatalAlert(10);
    }

    public void processClientCredentials(TlsCredentials clientCredentials) throws IOException {
        throw new TlsFatalAlert(80);
    }

    public void generateClientKeyExchange(OutputStream output) throws IOException {
        if (this.psk_identity_hint == null) {
            this.pskIdentity.skipIdentityHint();
        } else {
            this.pskIdentity.notifyIdentityHint(this.psk_identity_hint);
        }
        byte[] psk_identity = this.pskIdentity.getPSKIdentity();
        if (psk_identity == null) {
            throw new TlsFatalAlert(80);
        }
        this.psk = this.pskIdentity.getPSK();
        if (this.psk == null) {
            throw new TlsFatalAlert(80);
        }
        TlsUtils.writeOpaque16(psk_identity, output);
        this.context.getSecurityParameters().pskIdentity = Arrays.clone(psk_identity);
        if (this.keyExchange == 14) {
            this.dhAgreePrivateKey = TlsDHUtils.generateEphemeralClientKeyExchange(this.context.getSecureRandom(), this.dhParameters, output);
        } else if (this.keyExchange == 24) {
            this.ecAgreePrivateKey = TlsECCUtils.generateEphemeralClientKeyExchange(this.context.getSecureRandom(), this.serverECPointFormats, this.ecAgreePublicKey.getParameters(), output);
        } else if (this.keyExchange == 15) {
            this.premasterSecret = TlsRSAUtils.generateEncryptedPreMasterSecret(this.context, this.rsaServerPublicKey, output);
        }
    }

    public void processClientKeyExchange(InputStream input) throws IOException {
        byte[] encryptedPreMasterSecret;
        byte[] psk_identity = TlsUtils.readOpaque16(input);
        this.psk = this.pskIdentityManager.getPSK(psk_identity);
        if (this.psk == null) {
            throw new TlsFatalAlert(AlertDescription.unknown_psk_identity);
        }
        this.context.getSecurityParameters().pskIdentity = psk_identity;
        if (this.keyExchange == 14) {
            this.dhAgreePublicKey = TlsDHUtils.validateDHPublicKey(new DHPublicKeyParameters(TlsDHUtils.readDHParameter(input), this.dhParameters));
        } else if (this.keyExchange == 24) {
            byte[] point = TlsUtils.readOpaque8(input);
            this.ecAgreePublicKey = TlsECCUtils.validateECPublicKey(TlsECCUtils.deserializeECPublicKey(this.serverECPointFormats, this.ecAgreePrivateKey.getParameters(), point));
        } else if (this.keyExchange == 15) {
            if (TlsUtils.isSSL(this.context)) {
                encryptedPreMasterSecret = Streams.readAll(input);
            } else {
                encryptedPreMasterSecret = TlsUtils.readOpaque16(input);
            }
            this.premasterSecret = this.serverCredentials.decryptPreMasterSecret(encryptedPreMasterSecret);
        }
    }

    public byte[] generatePremasterSecret() throws IOException {
        byte[] other_secret = generateOtherSecret(this.psk.length);
        ByteArrayOutputStream buf = new ByteArrayOutputStream(other_secret.length + 4 + this.psk.length);
        TlsUtils.writeOpaque16(other_secret, buf);
        TlsUtils.writeOpaque16(this.psk, buf);
        Arrays.fill(this.psk, 0);
        this.psk = null;
        return buf.toByteArray();
    }

    /* access modifiers changed from: protected */
    public byte[] generateOtherSecret(int pskLength) throws IOException {
        if (this.keyExchange == 14) {
            if (this.dhAgreePrivateKey != null) {
                return TlsDHUtils.calculateDHBasicAgreement(this.dhAgreePublicKey, this.dhAgreePrivateKey);
            }
            throw new TlsFatalAlert(80);
        } else if (this.keyExchange == 24) {
            if (this.ecAgreePrivateKey != null) {
                return TlsECCUtils.calculateECDHBasicAgreement(this.ecAgreePublicKey, this.ecAgreePrivateKey);
            }
            throw new TlsFatalAlert(80);
        } else if (this.keyExchange == 15) {
            return this.premasterSecret;
        } else {
            return new byte[pskLength];
        }
    }

    /* access modifiers changed from: protected */
    public RSAKeyParameters validateRSAPublicKey(RSAKeyParameters key) throws IOException {
        if (key.getExponent().isProbablePrime(2)) {
            return key;
        }
        throw new TlsFatalAlert(47);
    }
}
