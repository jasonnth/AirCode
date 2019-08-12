package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Vector;
import org.spongycastle.asn1.x509.Certificate;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.Signer;
import org.spongycastle.crypto.agreement.srp.SRP6Client;
import org.spongycastle.crypto.agreement.srp.SRP6Server;
import org.spongycastle.crypto.agreement.srp.SRP6Util;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.SRP6GroupParameters;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.p333io.TeeInputStream;

public class TlsSRPKeyExchange extends AbstractTlsKeyExchange {
    protected TlsSRPGroupVerifier groupVerifier;
    protected byte[] identity;
    protected byte[] password;
    protected TlsSignerCredentials serverCredentials;
    protected AsymmetricKeyParameter serverPublicKey;
    protected SRP6Client srpClient;
    protected SRP6GroupParameters srpGroup;
    protected BigInteger srpPeerCredentials;
    protected byte[] srpSalt;
    protected SRP6Server srpServer;
    protected BigInteger srpVerifier;
    protected TlsSigner tlsSigner;

    protected static TlsSigner createSigner(int keyExchange) {
        switch (keyExchange) {
            case 21:
                return null;
            case 22:
                return new TlsDSSSigner();
            case 23:
                return new TlsRSASigner();
            default:
                throw new IllegalArgumentException("unsupported key exchange algorithm");
        }
    }

    public TlsSRPKeyExchange(int keyExchange, Vector supportedSignatureAlgorithms, byte[] identity2, byte[] password2) {
        this(keyExchange, supportedSignatureAlgorithms, new DefaultTlsSRPGroupVerifier(), identity2, password2);
    }

    public TlsSRPKeyExchange(int keyExchange, Vector supportedSignatureAlgorithms, TlsSRPGroupVerifier groupVerifier2, byte[] identity2, byte[] password2) {
        super(keyExchange, supportedSignatureAlgorithms);
        this.serverPublicKey = null;
        this.srpGroup = null;
        this.srpClient = null;
        this.srpServer = null;
        this.srpPeerCredentials = null;
        this.srpVerifier = null;
        this.srpSalt = null;
        this.serverCredentials = null;
        this.tlsSigner = createSigner(keyExchange);
        this.groupVerifier = groupVerifier2;
        this.identity = identity2;
        this.password = password2;
        this.srpClient = new SRP6Client();
    }

    public TlsSRPKeyExchange(int keyExchange, Vector supportedSignatureAlgorithms, byte[] identity2, TlsSRPLoginParameters loginParameters) {
        super(keyExchange, supportedSignatureAlgorithms);
        this.serverPublicKey = null;
        this.srpGroup = null;
        this.srpClient = null;
        this.srpServer = null;
        this.srpPeerCredentials = null;
        this.srpVerifier = null;
        this.srpSalt = null;
        this.serverCredentials = null;
        this.tlsSigner = createSigner(keyExchange);
        this.identity = identity2;
        this.srpServer = new SRP6Server();
        this.srpGroup = loginParameters.getGroup();
        this.srpVerifier = loginParameters.getVerifier();
        this.srpSalt = loginParameters.getSalt();
    }

    public void init(TlsContext context) {
        super.init(context);
        if (this.tlsSigner != null) {
            this.tlsSigner.init(context);
        }
    }

    public void skipServerCredentials() throws IOException {
        if (this.tlsSigner != null) {
            throw new TlsFatalAlert(10);
        }
    }

    public void processServerCertificate(Certificate serverCertificate) throws IOException {
        if (this.tlsSigner == null) {
            throw new TlsFatalAlert(10);
        } else if (serverCertificate.isEmpty()) {
            throw new TlsFatalAlert(42);
        } else {
            Certificate x509Cert = serverCertificate.getCertificateAt(0);
            try {
                this.serverPublicKey = PublicKeyFactory.createKey(x509Cert.getSubjectPublicKeyInfo());
                if (!this.tlsSigner.isValidPublicKey(this.serverPublicKey)) {
                    throw new TlsFatalAlert(46);
                }
                TlsUtils.validateKeyUsage(x509Cert, 128);
                super.processServerCertificate(serverCertificate);
            } catch (RuntimeException e) {
                throw new TlsFatalAlert(43, e);
            }
        }
    }

    public void processServerCredentials(TlsCredentials serverCredentials2) throws IOException {
        if (this.keyExchange == 21 || !(serverCredentials2 instanceof TlsSignerCredentials)) {
            throw new TlsFatalAlert(80);
        }
        processServerCertificate(serverCredentials2.getCertificate());
        this.serverCredentials = (TlsSignerCredentials) serverCredentials2;
    }

    public boolean requiresServerKeyExchange() {
        return true;
    }

    public byte[] generateServerKeyExchange() throws IOException {
        this.srpServer.init(this.srpGroup, this.srpVerifier, TlsUtils.createHash(2), this.context.getSecureRandom());
        ServerSRPParams srpParams = new ServerSRPParams(this.srpGroup.getN(), this.srpGroup.getG(), this.srpSalt, this.srpServer.generateServerCredentials());
        DigestInputBuffer buf = new DigestInputBuffer();
        srpParams.encode(buf);
        if (this.serverCredentials != null) {
            SignatureAndHashAlgorithm signatureAndHashAlgorithm = TlsUtils.getSignatureAndHashAlgorithm(this.context, this.serverCredentials);
            Digest d = TlsUtils.createHash(signatureAndHashAlgorithm);
            SecurityParameters securityParameters = this.context.getSecurityParameters();
            d.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
            d.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
            buf.updateDigest(d);
            byte[] hash = new byte[d.getDigestSize()];
            d.doFinal(hash, 0);
            new DigitallySigned(signatureAndHashAlgorithm, this.serverCredentials.generateCertificateSignature(hash)).encode(buf);
        }
        return buf.toByteArray();
    }

    public void processServerKeyExchange(InputStream input) throws IOException {
        SecurityParameters securityParameters = this.context.getSecurityParameters();
        SignerInputBuffer buf = null;
        InputStream teeIn = input;
        if (this.tlsSigner != null) {
            buf = new SignerInputBuffer();
            teeIn = new TeeInputStream(input, buf);
        }
        ServerSRPParams srpParams = ServerSRPParams.parse(teeIn);
        if (buf != null) {
            DigitallySigned signed_params = parseSignature(input);
            Signer signer = initVerifyer(this.tlsSigner, signed_params.getAlgorithm(), securityParameters);
            buf.updateSigner(signer);
            if (!signer.verifySignature(signed_params.getSignature())) {
                throw new TlsFatalAlert(51);
            }
        }
        this.srpGroup = new SRP6GroupParameters(srpParams.getN(), srpParams.getG());
        if (!this.groupVerifier.accept(this.srpGroup)) {
            throw new TlsFatalAlert(71);
        }
        this.srpSalt = srpParams.getS();
        try {
            this.srpPeerCredentials = SRP6Util.validatePublicValue(this.srpGroup.getN(), srpParams.getB());
            this.srpClient.init(this.srpGroup, TlsUtils.createHash(2), this.context.getSecureRandom());
        } catch (CryptoException e) {
            throw new TlsFatalAlert(47, e);
        }
    }

    public void validateCertificateRequest(CertificateRequest certificateRequest) throws IOException {
        throw new TlsFatalAlert(10);
    }

    public void processClientCredentials(TlsCredentials clientCredentials) throws IOException {
        throw new TlsFatalAlert(80);
    }

    public void generateClientKeyExchange(OutputStream output) throws IOException {
        TlsSRPUtils.writeSRPParameter(this.srpClient.generateClientCredentials(this.srpSalt, this.identity, this.password), output);
        this.context.getSecurityParameters().srpIdentity = Arrays.clone(this.identity);
    }

    public void processClientKeyExchange(InputStream input) throws IOException {
        try {
            this.srpPeerCredentials = SRP6Util.validatePublicValue(this.srpGroup.getN(), TlsSRPUtils.readSRPParameter(input));
            this.context.getSecurityParameters().srpIdentity = Arrays.clone(this.identity);
        } catch (CryptoException e) {
            throw new TlsFatalAlert(47, e);
        }
    }

    public byte[] generatePremasterSecret() throws IOException {
        BigInteger S;
        try {
            if (this.srpServer != null) {
                S = this.srpServer.calculateSecret(this.srpPeerCredentials);
            } else {
                S = this.srpClient.calculateSecret(this.srpPeerCredentials);
            }
            return BigIntegers.asUnsignedByteArray(S);
        } catch (CryptoException e) {
            throw new TlsFatalAlert(47, e);
        }
    }

    /* access modifiers changed from: protected */
    public Signer initVerifyer(TlsSigner tlsSigner2, SignatureAndHashAlgorithm algorithm, SecurityParameters securityParameters) {
        Signer signer = tlsSigner2.createVerifyer(algorithm, this.serverPublicKey);
        signer.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        signer.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        return signer;
    }
}
