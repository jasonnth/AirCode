package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.util.PublicKeyFactory;
import org.spongycastle.util.Arrays;

public class DTLSServerProtocol extends DTLSProtocol {
    protected boolean verifyRequests = true;

    protected static class ServerHandshakeState {
        boolean allowCertificateStatus = false;
        CertificateRequest certificateRequest = null;
        Certificate clientCertificate = null;
        short clientCertificateType = -1;
        Hashtable clientExtensions = null;
        boolean expectSessionTicket = false;
        TlsKeyExchange keyExchange = null;
        int[] offeredCipherSuites = null;
        short[] offeredCompressionMethods = null;
        boolean resumedSession = false;
        boolean secure_renegotiation = false;
        TlsServer server = null;
        TlsServerContextImpl serverContext = null;
        TlsCredentials serverCredentials = null;
        Hashtable serverExtensions = null;

        protected ServerHandshakeState() {
        }
    }

    public DTLSServerProtocol(SecureRandom secureRandom) {
        super(secureRandom);
    }

    public boolean getVerifyRequests() {
        return this.verifyRequests;
    }

    public void setVerifyRequests(boolean verifyRequests2) {
        this.verifyRequests = verifyRequests2;
    }

    public DTLSTransport accept(TlsServer server, DatagramTransport transport) throws IOException {
        if (server == null) {
            throw new IllegalArgumentException("'server' cannot be null");
        } else if (transport == null) {
            throw new IllegalArgumentException("'transport' cannot be null");
        } else {
            SecurityParameters securityParameters = new SecurityParameters();
            securityParameters.entity = 0;
            ServerHandshakeState state = new ServerHandshakeState();
            state.server = server;
            state.serverContext = new TlsServerContextImpl(this.secureRandom, securityParameters);
            securityParameters.serverRandom = TlsProtocol.createRandomBlock(server.shouldUseGMTUnixTime(), state.serverContext.getNonceRandomGenerator());
            server.init(state.serverContext);
            DTLSRecordLayer recordLayer = new DTLSRecordLayer(transport, state.serverContext, server, 22);
            try {
                return serverHandshake(state, recordLayer);
            } catch (TlsFatalAlert fatalAlert) {
                recordLayer.fail(fatalAlert.getAlertDescription());
                throw fatalAlert;
            } catch (IOException e) {
                recordLayer.fail(80);
                throw e;
            } catch (RuntimeException e2) {
                recordLayer.fail(80);
                throw new TlsFatalAlert(80, e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public DTLSTransport serverHandshake(ServerHandshakeState state, DTLSRecordLayer recordLayer) throws IOException {
        SecurityParameters securityParameters = state.serverContext.getSecurityParameters();
        DTLSReliableHandshake handshake = new DTLSReliableHandshake(state.serverContext, recordLayer);
        C5360Message clientMessage = handshake.receiveMessage();
        if (clientMessage.getType() == 1) {
            processClientHello(state, clientMessage.getBody());
            byte[] serverHelloBody = generateServerHello(state);
            applyMaxFragmentLengthExtension(recordLayer, securityParameters.maxFragmentLength);
            ProtocolVersion recordLayerVersion = state.serverContext.getServerVersion();
            recordLayer.setReadVersion(recordLayerVersion);
            recordLayer.setWriteVersion(recordLayerVersion);
            handshake.sendMessage(2, serverHelloBody);
            handshake.notifyHelloComplete();
            Vector serverSupplementalData = state.server.getServerSupplementalData();
            if (serverSupplementalData != null) {
                handshake.sendMessage(23, generateSupplementalData(serverSupplementalData));
            }
            state.keyExchange = state.server.getKeyExchange();
            state.keyExchange.init(state.serverContext);
            state.serverCredentials = state.server.getCredentials();
            Certificate serverCertificate = null;
            if (state.serverCredentials == null) {
                state.keyExchange.skipServerCredentials();
            } else {
                state.keyExchange.processServerCredentials(state.serverCredentials);
                serverCertificate = state.serverCredentials.getCertificate();
                handshake.sendMessage(11, generateCertificate(serverCertificate));
            }
            if (serverCertificate == null || serverCertificate.isEmpty()) {
                state.allowCertificateStatus = false;
            }
            if (state.allowCertificateStatus) {
                CertificateStatus certificateStatus = state.server.getCertificateStatus();
                if (certificateStatus != null) {
                    handshake.sendMessage(22, generateCertificateStatus(state, certificateStatus));
                }
            }
            byte[] serverKeyExchange = state.keyExchange.generateServerKeyExchange();
            if (serverKeyExchange != null) {
                handshake.sendMessage(12, serverKeyExchange);
            }
            if (state.serverCredentials != null) {
                state.certificateRequest = state.server.getCertificateRequest();
                if (state.certificateRequest != null) {
                    if (TlsUtils.isTLSv12((TlsContext) state.serverContext) != (state.certificateRequest.getSupportedSignatureAlgorithms() != null)) {
                        throw new TlsFatalAlert(80);
                    }
                    state.keyExchange.validateCertificateRequest(state.certificateRequest);
                    handshake.sendMessage(13, generateCertificateRequest(state, state.certificateRequest));
                    TlsUtils.trackHashAlgorithms(handshake.getHandshakeHash(), state.certificateRequest.getSupportedSignatureAlgorithms());
                }
            }
            handshake.sendMessage(14, TlsUtils.EMPTY_BYTES);
            handshake.getHandshakeHash().sealHashAlgorithms();
            C5360Message clientMessage2 = handshake.receiveMessage();
            if (clientMessage2.getType() == 23) {
                processClientSupplementalData(state, clientMessage2.getBody());
                clientMessage2 = handshake.receiveMessage();
            } else {
                state.server.processClientSupplementalData(null);
            }
            if (state.certificateRequest == null) {
                state.keyExchange.skipClientCredentials();
            } else if (clientMessage2.getType() == 11) {
                processClientCertificate(state, clientMessage2.getBody());
                clientMessage2 = handshake.receiveMessage();
            } else if (TlsUtils.isTLSv12((TlsContext) state.serverContext)) {
                throw new TlsFatalAlert(10);
            } else {
                notifyClientCertificate(state, Certificate.EMPTY_CHAIN);
            }
            if (clientMessage2.getType() == 16) {
                processClientKeyExchange(state, clientMessage2.getBody());
                TlsHandshakeHash prepareFinishHash = handshake.prepareToFinish();
                securityParameters.sessionHash = TlsProtocol.getCurrentPRFHash(state.serverContext, prepareFinishHash, null);
                TlsProtocol.establishMasterSecret(state.serverContext, state.keyExchange);
                recordLayer.initPendingEpoch(state.server.getCipher());
                if (expectCertificateVerifyMessage(state)) {
                    processCertificateVerify(state, handshake.receiveMessageBody(15), prepareFinishHash);
                }
                processFinished(handshake.receiveMessageBody(20), TlsUtils.calculateVerifyData(state.serverContext, ExporterLabel.client_finished, TlsProtocol.getCurrentPRFHash(state.serverContext, handshake.getHandshakeHash(), null)));
                if (state.expectSessionTicket) {
                    handshake.sendMessage(4, generateNewSessionTicket(state, state.server.getNewSessionTicket()));
                }
                handshake.sendMessage(20, TlsUtils.calculateVerifyData(state.serverContext, ExporterLabel.server_finished, TlsProtocol.getCurrentPRFHash(state.serverContext, handshake.getHandshakeHash(), null)));
                handshake.finish();
                state.server.notifyHandshakeComplete();
                DTLSTransport dTLSTransport = new DTLSTransport(recordLayer);
                return dTLSTransport;
            }
            throw new TlsFatalAlert(10);
        }
        throw new TlsFatalAlert(10);
    }

    /* access modifiers changed from: protected */
    public byte[] generateCertificateRequest(ServerHandshakeState state, CertificateRequest certificateRequest) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        certificateRequest.encode(buf);
        return buf.toByteArray();
    }

    /* access modifiers changed from: protected */
    public byte[] generateCertificateStatus(ServerHandshakeState state, CertificateStatus certificateStatus) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        certificateStatus.encode(buf);
        return buf.toByteArray();
    }

    /* access modifiers changed from: protected */
    public byte[] generateNewSessionTicket(ServerHandshakeState state, NewSessionTicket newSessionTicket) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        newSessionTicket.encode(buf);
        return buf.toByteArray();
    }

    /* access modifiers changed from: protected */
    public byte[] generateServerHello(ServerHandshakeState state) throws IOException {
        boolean z;
        boolean noRenegExt;
        boolean z2 = true;
        SecurityParameters securityParameters = state.serverContext.getSecurityParameters();
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ProtocolVersion server_version = state.server.getServerVersion();
        if (!server_version.isEqualOrEarlierVersionOf(state.serverContext.getClientVersion())) {
            throw new TlsFatalAlert(80);
        }
        state.serverContext.setServerVersion(server_version);
        TlsUtils.writeVersion(state.serverContext.getServerVersion(), buf);
        buf.write(securityParameters.getServerRandom());
        TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, buf);
        int selectedCipherSuite = state.server.getSelectedCipherSuite();
        if (!Arrays.contains(state.offeredCipherSuites, selectedCipherSuite) || selectedCipherSuite == 0 || CipherSuite.isSCSV(selectedCipherSuite) || !TlsUtils.isValidCipherSuiteForVersion(selectedCipherSuite, state.serverContext.getServerVersion())) {
            throw new TlsFatalAlert(80);
        }
        validateSelectedCipherSuite(selectedCipherSuite, 80);
        securityParameters.cipherSuite = selectedCipherSuite;
        short selectedCompressionMethod = state.server.getSelectedCompressionMethod();
        if (!Arrays.contains(state.offeredCompressionMethods, selectedCompressionMethod)) {
            throw new TlsFatalAlert(80);
        }
        securityParameters.compressionAlgorithm = selectedCompressionMethod;
        TlsUtils.writeUint16(selectedCipherSuite, buf);
        TlsUtils.writeUint8(selectedCompressionMethod, (OutputStream) buf);
        state.serverExtensions = state.server.getServerExtensions();
        if (state.secure_renegotiation) {
            if (TlsUtils.getExtensionData(state.serverExtensions, TlsProtocol.EXT_RenegotiationInfo) == null) {
                noRenegExt = true;
            } else {
                noRenegExt = false;
            }
            if (noRenegExt) {
                state.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(state.serverExtensions);
                state.serverExtensions.put(TlsProtocol.EXT_RenegotiationInfo, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES));
            }
        }
        if (securityParameters.extendedMasterSecret) {
            state.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(state.serverExtensions);
            TlsExtensionsUtils.addExtendedMasterSecretExtension(state.serverExtensions);
        }
        if (state.serverExtensions != null) {
            securityParameters.encryptThenMAC = TlsExtensionsUtils.hasEncryptThenMACExtension(state.serverExtensions);
            securityParameters.maxFragmentLength = evaluateMaxFragmentLengthExtension(state.resumedSession, state.clientExtensions, state.serverExtensions, 80);
            securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(state.serverExtensions);
            if (state.resumedSession || !TlsUtils.hasExpectedEmptyExtensionData(state.serverExtensions, TlsExtensionsUtils.EXT_status_request, 80)) {
                z = false;
            } else {
                z = true;
            }
            state.allowCertificateStatus = z;
            if (state.resumedSession || !TlsUtils.hasExpectedEmptyExtensionData(state.serverExtensions, TlsProtocol.EXT_SessionTicket, 80)) {
                z2 = false;
            }
            state.expectSessionTicket = z2;
            TlsProtocol.writeExtensions(buf, state.serverExtensions);
        }
        securityParameters.prfAlgorithm = TlsProtocol.getPRFAlgorithm(state.serverContext, securityParameters.getCipherSuite());
        securityParameters.verifyDataLength = 12;
        return buf.toByteArray();
    }

    /* access modifiers changed from: protected */
    public void notifyClientCertificate(ServerHandshakeState state, Certificate clientCertificate) throws IOException {
        if (state.certificateRequest == null) {
            throw new IllegalStateException();
        } else if (state.clientCertificate != null) {
            throw new TlsFatalAlert(10);
        } else {
            state.clientCertificate = clientCertificate;
            if (clientCertificate.isEmpty()) {
                state.keyExchange.skipClientCredentials();
            } else {
                state.clientCertificateType = TlsUtils.getClientCertificateType(clientCertificate, state.serverCredentials.getCertificate());
                state.keyExchange.processClientCertificate(clientCertificate);
            }
            state.server.notifyClientCertificate(clientCertificate);
        }
    }

    /* access modifiers changed from: protected */
    public void processClientCertificate(ServerHandshakeState state, byte[] body) throws IOException {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        Certificate clientCertificate = Certificate.parse(buf);
        TlsProtocol.assertEmpty(buf);
        notifyClientCertificate(state, clientCertificate);
    }

    /* access modifiers changed from: protected */
    public void processCertificateVerify(ServerHandshakeState state, byte[] body, TlsHandshakeHash prepareFinishHash) throws IOException {
        byte[] hash;
        if (state.certificateRequest == null) {
            throw new IllegalStateException();
        }
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        TlsServerContextImpl context = state.serverContext;
        DigitallySigned clientCertificateVerify = DigitallySigned.parse(context, buf);
        TlsProtocol.assertEmpty(buf);
        try {
            SignatureAndHashAlgorithm signatureAlgorithm = clientCertificateVerify.getAlgorithm();
            if (TlsUtils.isTLSv12((TlsContext) context)) {
                TlsUtils.verifySupportedSignatureAlgorithm(state.certificateRequest.getSupportedSignatureAlgorithms(), signatureAlgorithm);
                hash = prepareFinishHash.getFinalHash(signatureAlgorithm.getHash());
            } else {
                hash = context.getSecurityParameters().getSessionHash();
            }
            AsymmetricKeyParameter publicKey = PublicKeyFactory.createKey(state.clientCertificate.getCertificateAt(0).getSubjectPublicKeyInfo());
            TlsSigner tlsSigner = TlsUtils.createTlsSigner(state.clientCertificateType);
            tlsSigner.init(context);
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
    public void processClientHello(ServerHandshakeState state, byte[] body) throws IOException {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        ProtocolVersion client_version = TlsUtils.readVersion(buf);
        if (!client_version.isDTLS()) {
            throw new TlsFatalAlert(47);
        }
        byte[] client_random = TlsUtils.readFully(32, (InputStream) buf);
        if (TlsUtils.readOpaque8(buf).length > 32) {
            throw new TlsFatalAlert(47);
        }
        byte[] readOpaque8 = TlsUtils.readOpaque8(buf);
        int cipher_suites_length = TlsUtils.readUint16(buf);
        if (cipher_suites_length < 2 || (cipher_suites_length & 1) != 0) {
            throw new TlsFatalAlert(50);
        }
        state.offeredCipherSuites = TlsUtils.readUint16Array(cipher_suites_length / 2, buf);
        int compression_methods_length = TlsUtils.readUint8(buf);
        if (compression_methods_length < 1) {
            throw new TlsFatalAlert(47);
        }
        state.offeredCompressionMethods = TlsUtils.readUint8Array(compression_methods_length, buf);
        state.clientExtensions = TlsProtocol.readExtensions(buf);
        TlsServerContextImpl context = state.serverContext;
        SecurityParameters securityParameters = context.getSecurityParameters();
        securityParameters.extendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension(state.clientExtensions);
        context.setClientVersion(client_version);
        state.server.notifyClientVersion(client_version);
        state.server.notifyFallback(Arrays.contains(state.offeredCipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV));
        securityParameters.clientRandom = client_random;
        state.server.notifyOfferedCipherSuites(state.offeredCipherSuites);
        state.server.notifyOfferedCompressionMethods(state.offeredCompressionMethods);
        if (Arrays.contains(state.offeredCipherSuites, 255)) {
            state.secure_renegotiation = true;
        }
        byte[] renegExtData = TlsUtils.getExtensionData(state.clientExtensions, TlsProtocol.EXT_RenegotiationInfo);
        if (renegExtData != null) {
            state.secure_renegotiation = true;
            if (!Arrays.constantTimeAreEqual(renegExtData, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert(40);
            }
        }
        state.server.notifySecureRenegotiation(state.secure_renegotiation);
        if (state.clientExtensions != null) {
            state.server.processClientExtensions(state.clientExtensions);
        }
    }

    /* access modifiers changed from: protected */
    public void processClientKeyExchange(ServerHandshakeState state, byte[] body) throws IOException {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        state.keyExchange.processClientKeyExchange(buf);
        TlsProtocol.assertEmpty(buf);
    }

    /* access modifiers changed from: protected */
    public void processClientSupplementalData(ServerHandshakeState state, byte[] body) throws IOException {
        state.server.processClientSupplementalData(TlsProtocol.readSupplementalDataMessage(new ByteArrayInputStream(body)));
    }

    /* access modifiers changed from: protected */
    public boolean expectCertificateVerifyMessage(ServerHandshakeState state) {
        return state.clientCertificateType >= 0 && TlsUtils.hasSigningCapability(state.clientCertificateType);
    }
}
