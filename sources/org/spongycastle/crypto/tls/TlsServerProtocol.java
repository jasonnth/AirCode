package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Vector;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.Arrays;

public class TlsServerProtocol extends TlsProtocol {
    protected CertificateRequest certificateRequest = null;
    protected short clientCertificateType = -1;
    protected TlsKeyExchange keyExchange = null;
    protected TlsHandshakeHash prepareFinishHash = null;
    protected TlsCredentials serverCredentials = null;
    protected TlsServer tlsServer = null;
    TlsServerContextImpl tlsServerContext = null;

    public TlsServerProtocol(InputStream input, OutputStream output, SecureRandom secureRandom) {
        super(input, output, secureRandom);
    }

    public TlsServerProtocol(SecureRandom secureRandom) {
        super(secureRandom);
    }

    public void accept(TlsServer tlsServer2) throws IOException {
        if (tlsServer2 == null) {
            throw new IllegalArgumentException("'tlsServer' cannot be null");
        } else if (this.tlsServer != null) {
            throw new IllegalStateException("'accept' can only be called once");
        } else {
            this.tlsServer = tlsServer2;
            this.securityParameters = new SecurityParameters();
            this.securityParameters.entity = 0;
            this.tlsServerContext = new TlsServerContextImpl(this.secureRandom, this.securityParameters);
            this.securityParameters.serverRandom = createRandomBlock(tlsServer2.shouldUseGMTUnixTime(), this.tlsServerContext.getNonceRandomGenerator());
            this.tlsServer.init(this.tlsServerContext);
            this.recordStream.init(this.tlsServerContext);
            this.recordStream.setRestrictReadVersion(false);
            blockForHandshake();
        }
    }

    /* access modifiers changed from: protected */
    public void cleanupHandshake() {
        super.cleanupHandshake();
        this.keyExchange = null;
        this.serverCredentials = null;
        this.certificateRequest = null;
        this.prepareFinishHash = null;
    }

    /* access modifiers changed from: protected */
    public TlsContext getContext() {
        return this.tlsServerContext;
    }

    /* access modifiers changed from: 0000 */
    public AbstractTlsContext getContextAdmin() {
        return this.tlsServerContext;
    }

    /* access modifiers changed from: protected */
    public TlsPeer getPeer() {
        return this.tlsServer;
    }

    /* access modifiers changed from: protected */
    public void handleHandshakeMessage(short type, byte[] data) throws IOException {
        boolean z = true;
        ByteArrayInputStream buf = new ByteArrayInputStream(data);
        switch (type) {
            case 1:
                switch (this.connection_state) {
                    case 0:
                        receiveClientHelloMessage(buf);
                        this.connection_state = 1;
                        sendServerHelloMessage();
                        this.connection_state = 2;
                        this.recordStream.notifyHelloComplete();
                        Vector serverSupplementalData = this.tlsServer.getServerSupplementalData();
                        if (serverSupplementalData != null) {
                            sendSupplementalDataMessage(serverSupplementalData);
                        }
                        this.connection_state = 3;
                        this.keyExchange = this.tlsServer.getKeyExchange();
                        this.keyExchange.init(getContext());
                        this.serverCredentials = this.tlsServer.getCredentials();
                        Certificate serverCertificate = null;
                        if (this.serverCredentials == null) {
                            this.keyExchange.skipServerCredentials();
                        } else {
                            this.keyExchange.processServerCredentials(this.serverCredentials);
                            serverCertificate = this.serverCredentials.getCertificate();
                            sendCertificateMessage(serverCertificate);
                        }
                        this.connection_state = 4;
                        if (serverCertificate == null || serverCertificate.isEmpty()) {
                            this.allowCertificateStatus = false;
                        }
                        if (this.allowCertificateStatus) {
                            CertificateStatus certificateStatus = this.tlsServer.getCertificateStatus();
                            if (certificateStatus != null) {
                                sendCertificateStatusMessage(certificateStatus);
                            }
                        }
                        this.connection_state = 5;
                        byte[] serverKeyExchange = this.keyExchange.generateServerKeyExchange();
                        if (serverKeyExchange != null) {
                            sendServerKeyExchangeMessage(serverKeyExchange);
                        }
                        this.connection_state = 6;
                        if (this.serverCredentials != null) {
                            this.certificateRequest = this.tlsServer.getCertificateRequest();
                            if (this.certificateRequest != null) {
                                boolean isTLSv12 = TlsUtils.isTLSv12(getContext());
                                if (this.certificateRequest.getSupportedSignatureAlgorithms() == null) {
                                    z = false;
                                }
                                if (isTLSv12 != z) {
                                    throw new TlsFatalAlert(80);
                                }
                                this.keyExchange.validateCertificateRequest(this.certificateRequest);
                                sendCertificateRequestMessage(this.certificateRequest);
                                TlsUtils.trackHashAlgorithms(this.recordStream.getHandshakeHash(), this.certificateRequest.getSupportedSignatureAlgorithms());
                            }
                        }
                        this.connection_state = 7;
                        sendServerHelloDoneMessage();
                        this.connection_state = 8;
                        this.recordStream.getHandshakeHash().sealHashAlgorithms();
                        return;
                    case 16:
                        refuseRenegotiation();
                        return;
                    default:
                        throw new TlsFatalAlert(10);
                }
            case 11:
                switch (this.connection_state) {
                    case 8:
                        this.tlsServer.processClientSupplementalData(null);
                        break;
                    case 9:
                        break;
                    default:
                        throw new TlsFatalAlert(10);
                }
                if (this.certificateRequest == null) {
                    throw new TlsFatalAlert(10);
                }
                receiveCertificateMessage(buf);
                this.connection_state = 10;
                return;
            case 15:
                switch (this.connection_state) {
                    case 11:
                        if (!expectCertificateVerifyMessage()) {
                            throw new TlsFatalAlert(10);
                        }
                        receiveCertificateVerifyMessage(buf);
                        this.connection_state = 12;
                        return;
                    default:
                        throw new TlsFatalAlert(10);
                }
            case 16:
                switch (this.connection_state) {
                    case 8:
                        this.tlsServer.processClientSupplementalData(null);
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    default:
                        throw new TlsFatalAlert(10);
                }
                if (this.certificateRequest == null) {
                    this.keyExchange.skipClientCredentials();
                } else if (TlsUtils.isTLSv12(getContext())) {
                    throw new TlsFatalAlert(10);
                } else if (!TlsUtils.isSSL(getContext())) {
                    notifyClientCertificate(Certificate.EMPTY_CHAIN);
                } else if (this.peerCertificate == null) {
                    throw new TlsFatalAlert(10);
                }
                receiveClientKeyExchangeMessage(buf);
                this.connection_state = 11;
                return;
            case 20:
                switch (this.connection_state) {
                    case 11:
                        if (expectCertificateVerifyMessage()) {
                            throw new TlsFatalAlert(10);
                        }
                        break;
                    case 12:
                        break;
                    default:
                        throw new TlsFatalAlert(10);
                }
                processFinishedMessage(buf);
                this.connection_state = 13;
                if (this.expectSessionTicket) {
                    sendNewSessionTicketMessage(this.tlsServer.getNewSessionTicket());
                    sendChangeCipherSpecMessage();
                }
                this.connection_state = 14;
                sendFinishedMessage();
                this.connection_state = 15;
                this.connection_state = 16;
                completeHandshake();
                return;
            case 23:
                switch (this.connection_state) {
                    case 8:
                        this.tlsServer.processClientSupplementalData(readSupplementalDataMessage(buf));
                        this.connection_state = 9;
                        return;
                    default:
                        throw new TlsFatalAlert(10);
                }
            default:
                throw new TlsFatalAlert(10);
        }
    }

    /* access modifiers changed from: protected */
    public void handleWarningMessage(short description) throws IOException {
        switch (description) {
            case 41:
                if (TlsUtils.isSSL(getContext()) && this.certificateRequest != null) {
                    notifyClientCertificate(Certificate.EMPTY_CHAIN);
                    return;
                }
                return;
            default:
                super.handleWarningMessage(description);
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void notifyClientCertificate(Certificate clientCertificate) throws IOException {
        if (this.certificateRequest == null) {
            throw new IllegalStateException();
        } else if (this.peerCertificate != null) {
            throw new TlsFatalAlert(10);
        } else {
            this.peerCertificate = clientCertificate;
            if (clientCertificate.isEmpty()) {
                this.keyExchange.skipClientCredentials();
            } else {
                this.clientCertificateType = TlsUtils.getClientCertificateType(clientCertificate, this.serverCredentials.getCertificate());
                this.keyExchange.processClientCertificate(clientCertificate);
            }
            this.tlsServer.notifyClientCertificate(clientCertificate);
        }
    }

    /* access modifiers changed from: protected */
    public void receiveCertificateMessage(ByteArrayInputStream buf) throws IOException {
        Certificate clientCertificate = Certificate.parse(buf);
        assertEmpty(buf);
        notifyClientCertificate(clientCertificate);
    }

    /* access modifiers changed from: protected */
    public void receiveCertificateVerifyMessage(ByteArrayInputStream buf) throws IOException {
        byte[] hash;
        if (this.certificateRequest == null) {
            throw new IllegalStateException();
        }
        DigitallySigned clientCertificateVerify = DigitallySigned.parse(getContext(), buf);
        assertEmpty(buf);
        try {
            SignatureAndHashAlgorithm signatureAlgorithm = clientCertificateVerify.getAlgorithm();
            if (TlsUtils.isTLSv12(getContext())) {
                TlsUtils.verifySupportedSignatureAlgorithm(this.certificateRequest.getSupportedSignatureAlgorithms(), signatureAlgorithm);
                hash = this.prepareFinishHash.getFinalHash(signatureAlgorithm.getHash());
            } else {
                hash = this.securityParameters.getSessionHash();
            }
            AsymmetricKeyParameter publicKey = PublicKeyFactory.createKey(this.peerCertificate.getCertificateAt(0).getSubjectPublicKeyInfo());
            TlsSigner tlsSigner = TlsUtils.createTlsSigner(this.clientCertificateType);
            tlsSigner.init(getContext());
            if (!tlsSigner.verifyRawSignature(signatureAlgorithm, clientCertificateVerify.getSignature(), publicKey, hash)) {
                throw new TlsFatalAlert(51);
            }
        } catch (TlsFatalAlert e) {
            throw e;
        } catch (Exception e2) {
            throw new TlsFatalAlert(51, e2);
        }
    }

    /* access modifiers changed from: protected */
    public void receiveClientHelloMessage(ByteArrayInputStream buf) throws IOException {
        ProtocolVersion client_version = TlsUtils.readVersion(buf);
        this.recordStream.setWriteVersion(client_version);
        if (client_version.isDTLS()) {
            throw new TlsFatalAlert(47);
        }
        byte[] client_random = TlsUtils.readFully(32, (InputStream) buf);
        if (TlsUtils.readOpaque8(buf).length > 32) {
            throw new TlsFatalAlert(47);
        }
        int cipher_suites_length = TlsUtils.readUint16(buf);
        if (cipher_suites_length < 2 || (cipher_suites_length & 1) != 0) {
            throw new TlsFatalAlert(50);
        }
        this.offeredCipherSuites = TlsUtils.readUint16Array(cipher_suites_length / 2, buf);
        int compression_methods_length = TlsUtils.readUint8(buf);
        if (compression_methods_length < 1) {
            throw new TlsFatalAlert(47);
        }
        this.offeredCompressionMethods = TlsUtils.readUint8Array(compression_methods_length, buf);
        this.clientExtensions = readExtensions(buf);
        this.securityParameters.extendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension(this.clientExtensions);
        getContextAdmin().setClientVersion(client_version);
        this.tlsServer.notifyClientVersion(client_version);
        this.tlsServer.notifyFallback(Arrays.contains(this.offeredCipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV));
        this.securityParameters.clientRandom = client_random;
        this.tlsServer.notifyOfferedCipherSuites(this.offeredCipherSuites);
        this.tlsServer.notifyOfferedCompressionMethods(this.offeredCompressionMethods);
        if (Arrays.contains(this.offeredCipherSuites, 255)) {
            this.secure_renegotiation = true;
        }
        byte[] renegExtData = TlsUtils.getExtensionData(this.clientExtensions, EXT_RenegotiationInfo);
        if (renegExtData != null) {
            this.secure_renegotiation = true;
            if (!Arrays.constantTimeAreEqual(renegExtData, createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert(40);
            }
        }
        this.tlsServer.notifySecureRenegotiation(this.secure_renegotiation);
        if (this.clientExtensions != null) {
            this.tlsServer.processClientExtensions(this.clientExtensions);
        }
    }

    /* access modifiers changed from: protected */
    public void receiveClientKeyExchangeMessage(ByteArrayInputStream buf) throws IOException {
        this.keyExchange.processClientKeyExchange(buf);
        assertEmpty(buf);
        this.prepareFinishHash = this.recordStream.prepareToFinish();
        this.securityParameters.sessionHash = getCurrentPRFHash(getContext(), this.prepareFinishHash, null);
        establishMasterSecret(getContext(), this.keyExchange);
        this.recordStream.setPendingConnectionState(getPeer().getCompression(), getPeer().getCipher());
        if (!this.expectSessionTicket) {
            sendChangeCipherSpecMessage();
        }
    }

    /* access modifiers changed from: protected */
    public void sendCertificateRequestMessage(CertificateRequest certificateRequest2) throws IOException {
        HandshakeMessage message = new HandshakeMessage(this, 13);
        certificateRequest2.encode(message);
        message.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendCertificateStatusMessage(CertificateStatus certificateStatus) throws IOException {
        HandshakeMessage message = new HandshakeMessage(this, 22);
        certificateStatus.encode(message);
        message.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendNewSessionTicketMessage(NewSessionTicket newSessionTicket) throws IOException {
        if (newSessionTicket == null) {
            throw new TlsFatalAlert(80);
        }
        HandshakeMessage message = new HandshakeMessage(this, 4);
        newSessionTicket.encode(message);
        message.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendServerHelloMessage() throws IOException {
        boolean z;
        boolean noRenegExt;
        boolean z2 = true;
        HandshakeMessage message = new HandshakeMessage(this, 2);
        ProtocolVersion server_version = this.tlsServer.getServerVersion();
        if (!server_version.isEqualOrEarlierVersionOf(getContext().getClientVersion())) {
            throw new TlsFatalAlert(80);
        }
        this.recordStream.setReadVersion(server_version);
        this.recordStream.setWriteVersion(server_version);
        this.recordStream.setRestrictReadVersion(true);
        getContextAdmin().setServerVersion(server_version);
        TlsUtils.writeVersion(server_version, message);
        message.write(this.securityParameters.serverRandom);
        TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, message);
        int selectedCipherSuite = this.tlsServer.getSelectedCipherSuite();
        if (!Arrays.contains(this.offeredCipherSuites, selectedCipherSuite) || selectedCipherSuite == 0 || CipherSuite.isSCSV(selectedCipherSuite) || !TlsUtils.isValidCipherSuiteForVersion(selectedCipherSuite, getContext().getServerVersion())) {
            throw new TlsFatalAlert(80);
        }
        this.securityParameters.cipherSuite = selectedCipherSuite;
        short selectedCompressionMethod = this.tlsServer.getSelectedCompressionMethod();
        if (!Arrays.contains(this.offeredCompressionMethods, selectedCompressionMethod)) {
            throw new TlsFatalAlert(80);
        }
        this.securityParameters.compressionAlgorithm = selectedCompressionMethod;
        TlsUtils.writeUint16(selectedCipherSuite, message);
        TlsUtils.writeUint8(selectedCompressionMethod, (OutputStream) message);
        this.serverExtensions = this.tlsServer.getServerExtensions();
        if (this.secure_renegotiation) {
            if (TlsUtils.getExtensionData(this.serverExtensions, EXT_RenegotiationInfo) == null) {
                noRenegExt = true;
            } else {
                noRenegExt = false;
            }
            if (noRenegExt) {
                this.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(this.serverExtensions);
                this.serverExtensions.put(EXT_RenegotiationInfo, createRenegotiationInfo(TlsUtils.EMPTY_BYTES));
            }
        }
        if (this.securityParameters.extendedMasterSecret) {
            this.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(this.serverExtensions);
            TlsExtensionsUtils.addExtendedMasterSecretExtension(this.serverExtensions);
        }
        if (this.serverExtensions != null) {
            this.securityParameters.encryptThenMAC = TlsExtensionsUtils.hasEncryptThenMACExtension(this.serverExtensions);
            this.securityParameters.maxFragmentLength = processMaxFragmentLengthExtension(this.clientExtensions, this.serverExtensions, 80);
            this.securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(this.serverExtensions);
            if (this.resumedSession || !TlsUtils.hasExpectedEmptyExtensionData(this.serverExtensions, TlsExtensionsUtils.EXT_status_request, 80)) {
                z = false;
            } else {
                z = true;
            }
            this.allowCertificateStatus = z;
            if (this.resumedSession || !TlsUtils.hasExpectedEmptyExtensionData(this.serverExtensions, TlsProtocol.EXT_SessionTicket, 80)) {
                z2 = false;
            }
            this.expectSessionTicket = z2;
            writeExtensions(message, this.serverExtensions);
        }
        this.securityParameters.prfAlgorithm = getPRFAlgorithm(getContext(), this.securityParameters.getCipherSuite());
        this.securityParameters.verifyDataLength = 12;
        applyMaxFragmentLengthExtension();
        message.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendServerHelloDoneMessage() throws IOException {
        byte[] message = new byte[4];
        TlsUtils.writeUint8(14, message, 0);
        TlsUtils.writeUint24(0, message, 1);
        writeHandshakeMessage(message, 0, message.length);
    }

    /* access modifiers changed from: protected */
    public void sendServerKeyExchangeMessage(byte[] serverKeyExchange) throws IOException {
        HandshakeMessage message = new HandshakeMessage(12, serverKeyExchange.length);
        message.write(serverKeyExchange);
        message.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public boolean expectCertificateVerifyMessage() {
        return this.clientCertificateType >= 0 && TlsUtils.hasSigningCapability(this.clientCertificateType);
    }
}
