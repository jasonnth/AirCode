package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.crypto.tls.SessionParameters.Builder;
import org.spongycastle.util.Arrays;

public class DTLSClientProtocol extends DTLSProtocol {

    protected static class ClientHandshakeState {
        boolean allowCertificateStatus = false;
        TlsAuthentication authentication = null;
        CertificateRequest certificateRequest = null;
        CertificateStatus certificateStatus = null;
        TlsClient client = null;
        TlsClientContextImpl clientContext = null;
        TlsCredentials clientCredentials = null;
        Hashtable clientExtensions = null;
        boolean expectSessionTicket = false;
        TlsKeyExchange keyExchange = null;
        int[] offeredCipherSuites = null;
        short[] offeredCompressionMethods = null;
        boolean resumedSession = false;
        boolean secure_renegotiation = false;
        byte[] selectedSessionID = null;
        Hashtable serverExtensions = null;
        SessionParameters sessionParameters = null;
        Builder sessionParametersBuilder = null;
        TlsSession tlsSession = null;

        protected ClientHandshakeState() {
        }
    }

    public DTLSClientProtocol(SecureRandom secureRandom) {
        super(secureRandom);
    }

    public DTLSTransport connect(TlsClient client, DatagramTransport transport) throws IOException {
        if (client == null) {
            throw new IllegalArgumentException("'client' cannot be null");
        } else if (transport == null) {
            throw new IllegalArgumentException("'transport' cannot be null");
        } else {
            SecurityParameters securityParameters = new SecurityParameters();
            securityParameters.entity = 1;
            ClientHandshakeState state = new ClientHandshakeState();
            state.client = client;
            state.clientContext = new TlsClientContextImpl(this.secureRandom, securityParameters);
            securityParameters.clientRandom = TlsProtocol.createRandomBlock(client.shouldUseGMTUnixTime(), state.clientContext.getNonceRandomGenerator());
            client.init(state.clientContext);
            DTLSRecordLayer recordLayer = new DTLSRecordLayer(transport, state.clientContext, client, 22);
            TlsSession sessionToResume = state.client.getSessionToResume();
            if (sessionToResume != null && sessionToResume.isResumable()) {
                SessionParameters sessionParameters = sessionToResume.exportSessionParameters();
                if (sessionParameters != null) {
                    state.tlsSession = sessionToResume;
                    state.sessionParameters = sessionParameters;
                }
            }
            try {
                return clientHandshake(state, recordLayer);
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
    public DTLSTransport clientHandshake(ClientHandshakeState state, DTLSRecordLayer recordLayer) throws IOException {
        byte[] hash;
        SecurityParameters securityParameters = state.clientContext.getSecurityParameters();
        DTLSReliableHandshake handshake = new DTLSReliableHandshake(state.clientContext, recordLayer);
        byte[] clientHelloBody = generateClientHello(state, state.client);
        recordLayer.setWriteVersion(ProtocolVersion.DTLSv10);
        handshake.sendMessage(1, clientHelloBody);
        C5360Message serverMessage = handshake.receiveMessage();
        while (serverMessage.getType() == 3) {
            if (!recordLayer.getReadVersion().isEqualOrEarlierVersionOf(state.clientContext.getClientVersion())) {
                throw new TlsFatalAlert(47);
            }
            recordLayer.setReadVersion(null);
            byte[] patched = patchClientHelloWithCookie(clientHelloBody, processHelloVerifyRequest(state, serverMessage.getBody()));
            handshake.resetHandshakeMessagesDigest();
            handshake.sendMessage(1, patched);
            serverMessage = handshake.receiveMessage();
        }
        if (serverMessage.getType() == 2) {
            ProtocolVersion recordLayerVersion = recordLayer.getReadVersion();
            reportServerVersion(state, recordLayerVersion);
            recordLayer.setWriteVersion(recordLayerVersion);
            processServerHello(state, serverMessage.getBody());
            handshake.notifyHelloComplete();
            applyMaxFragmentLengthExtension(recordLayer, securityParameters.maxFragmentLength);
            if (state.resumedSession) {
                securityParameters.masterSecret = Arrays.clone(state.sessionParameters.getMasterSecret());
                recordLayer.initPendingEpoch(state.client.getCipher());
                processFinished(handshake.receiveMessageBody(20), TlsUtils.calculateVerifyData(state.clientContext, ExporterLabel.server_finished, TlsProtocol.getCurrentPRFHash(state.clientContext, handshake.getHandshakeHash(), null)));
                handshake.sendMessage(20, TlsUtils.calculateVerifyData(state.clientContext, ExporterLabel.client_finished, TlsProtocol.getCurrentPRFHash(state.clientContext, handshake.getHandshakeHash(), null)));
                handshake.finish();
                state.clientContext.setResumableSession(state.tlsSession);
                state.client.notifyHandshakeComplete();
                DTLSTransport dTLSTransport = new DTLSTransport(recordLayer);
                return dTLSTransport;
            }
            invalidateSession(state);
            if (state.selectedSessionID.length > 0) {
                state.tlsSession = new TlsSessionImpl(state.selectedSessionID, null);
            }
            C5360Message serverMessage2 = handshake.receiveMessage();
            if (serverMessage2.getType() == 23) {
                processServerSupplementalData(state, serverMessage2.getBody());
                serverMessage2 = handshake.receiveMessage();
            } else {
                state.client.processServerSupplementalData(null);
            }
            state.keyExchange = state.client.getKeyExchange();
            state.keyExchange.init(state.clientContext);
            Certificate serverCertificate = null;
            if (serverMessage2.getType() == 11) {
                serverCertificate = processServerCertificate(state, serverMessage2.getBody());
                serverMessage2 = handshake.receiveMessage();
            } else {
                state.keyExchange.skipServerCredentials();
            }
            if (serverCertificate == null || serverCertificate.isEmpty()) {
                state.allowCertificateStatus = false;
            }
            if (serverMessage2.getType() == 22) {
                processCertificateStatus(state, serverMessage2.getBody());
                serverMessage2 = handshake.receiveMessage();
            }
            if (serverMessage2.getType() == 12) {
                processServerKeyExchange(state, serverMessage2.getBody());
                serverMessage2 = handshake.receiveMessage();
            } else {
                state.keyExchange.skipServerKeyExchange();
            }
            if (serverMessage2.getType() == 13) {
                processCertificateRequest(state, serverMessage2.getBody());
                TlsUtils.trackHashAlgorithms(handshake.getHandshakeHash(), state.certificateRequest.getSupportedSignatureAlgorithms());
                serverMessage2 = handshake.receiveMessage();
            }
            if (serverMessage2.getType() != 14) {
                throw new TlsFatalAlert(10);
            } else if (serverMessage2.getBody().length != 0) {
                throw new TlsFatalAlert(50);
            } else {
                handshake.getHandshakeHash().sealHashAlgorithms();
                Vector clientSupplementalData = state.client.getClientSupplementalData();
                if (clientSupplementalData != null) {
                    handshake.sendMessage(23, generateSupplementalData(clientSupplementalData));
                }
                if (state.certificateRequest != null) {
                    state.clientCredentials = state.authentication.getClientCredentials(state.certificateRequest);
                    Certificate clientCertificate = null;
                    if (state.clientCredentials != null) {
                        clientCertificate = state.clientCredentials.getCertificate();
                    }
                    if (clientCertificate == null) {
                        clientCertificate = Certificate.EMPTY_CHAIN;
                    }
                    handshake.sendMessage(11, generateCertificate(clientCertificate));
                }
                if (state.clientCredentials != null) {
                    state.keyExchange.processClientCredentials(state.clientCredentials);
                } else {
                    state.keyExchange.skipClientCredentials();
                }
                handshake.sendMessage(16, generateClientKeyExchange(state));
                TlsHandshakeHash prepareFinishHash = handshake.prepareToFinish();
                securityParameters.sessionHash = TlsProtocol.getCurrentPRFHash(state.clientContext, prepareFinishHash, null);
                TlsProtocol.establishMasterSecret(state.clientContext, state.keyExchange);
                recordLayer.initPendingEpoch(state.client.getCipher());
                if (state.clientCredentials != null && (state.clientCredentials instanceof TlsSignerCredentials)) {
                    TlsSignerCredentials signerCredentials = (TlsSignerCredentials) state.clientCredentials;
                    SignatureAndHashAlgorithm signatureAndHashAlgorithm = TlsUtils.getSignatureAndHashAlgorithm(state.clientContext, signerCredentials);
                    if (signatureAndHashAlgorithm == null) {
                        hash = securityParameters.getSessionHash();
                    } else {
                        hash = prepareFinishHash.getFinalHash(signatureAndHashAlgorithm.getHash());
                    }
                    handshake.sendMessage(15, generateCertificateVerify(state, new DigitallySigned(signatureAndHashAlgorithm, signerCredentials.generateCertificateSignature(hash))));
                }
                handshake.sendMessage(20, TlsUtils.calculateVerifyData(state.clientContext, ExporterLabel.client_finished, TlsProtocol.getCurrentPRFHash(state.clientContext, handshake.getHandshakeHash(), null)));
                if (state.expectSessionTicket) {
                    C5360Message serverMessage3 = handshake.receiveMessage();
                    if (serverMessage3.getType() == 4) {
                        processNewSessionTicket(state, serverMessage3.getBody());
                    } else {
                        throw new TlsFatalAlert(10);
                    }
                }
                processFinished(handshake.receiveMessageBody(20), TlsUtils.calculateVerifyData(state.clientContext, ExporterLabel.server_finished, TlsProtocol.getCurrentPRFHash(state.clientContext, handshake.getHandshakeHash(), null)));
                handshake.finish();
                if (state.tlsSession != null) {
                    state.sessionParameters = new Builder().setCipherSuite(securityParameters.getCipherSuite()).setCompressionAlgorithm(securityParameters.getCompressionAlgorithm()).setMasterSecret(securityParameters.getMasterSecret()).setPeerCertificate(serverCertificate).setPSKIdentity(securityParameters.getPSKIdentity()).setSRPIdentity(securityParameters.getSRPIdentity()).setServerExtensions(state.serverExtensions).build();
                    state.tlsSession = TlsUtils.importSession(state.tlsSession.getSessionID(), state.sessionParameters);
                    state.clientContext.setResumableSession(state.tlsSession);
                }
                state.client.notifyHandshakeComplete();
                DTLSTransport dTLSTransport2 = new DTLSTransport(recordLayer);
                return dTLSTransport2;
            }
        } else {
            throw new TlsFatalAlert(10);
        }
    }

    /* access modifiers changed from: protected */
    public byte[] generateCertificateVerify(ClientHandshakeState state, DigitallySigned certificateVerify) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        certificateVerify.encode(buf);
        return buf.toByteArray();
    }

    /* access modifiers changed from: protected */
    public byte[] generateClientHello(ClientHandshakeState state, TlsClient client) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        ProtocolVersion client_version = client.getClientVersion();
        if (!client_version.isDTLS()) {
            throw new TlsFatalAlert(80);
        }
        TlsClientContextImpl context = state.clientContext;
        context.setClientVersion(client_version);
        TlsUtils.writeVersion(client_version, buf);
        buf.write(context.getSecurityParameters().getClientRandom());
        byte[] session_id = TlsUtils.EMPTY_BYTES;
        if (state.tlsSession != null) {
            session_id = state.tlsSession.getSessionID();
            if (session_id == null || session_id.length > 32) {
                session_id = TlsUtils.EMPTY_BYTES;
            }
        }
        TlsUtils.writeOpaque8(session_id, buf);
        TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, buf);
        boolean fallback = client.isFallback();
        state.offeredCipherSuites = client.getCipherSuites();
        state.clientExtensions = client.getClientExtensions();
        boolean noRenegExt = TlsUtils.getExtensionData(state.clientExtensions, TlsProtocol.EXT_RenegotiationInfo) == null;
        boolean noRenegSCSV = !Arrays.contains(state.offeredCipherSuites, 255);
        if (noRenegExt && noRenegSCSV) {
            state.offeredCipherSuites = Arrays.append(state.offeredCipherSuites, 255);
        }
        if (fallback && !Arrays.contains(state.offeredCipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV)) {
            state.offeredCipherSuites = Arrays.append(state.offeredCipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV);
        }
        TlsUtils.writeUint16ArrayWithUint16Length(state.offeredCipherSuites, buf);
        state.offeredCompressionMethods = new short[]{0};
        TlsUtils.writeUint8ArrayWithUint8Length(state.offeredCompressionMethods, buf);
        if (state.clientExtensions != null) {
            TlsProtocol.writeExtensions(buf, state.clientExtensions);
        }
        return buf.toByteArray();
    }

    /* access modifiers changed from: protected */
    public byte[] generateClientKeyExchange(ClientHandshakeState state) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        state.keyExchange.generateClientKeyExchange(buf);
        return buf.toByteArray();
    }

    /* access modifiers changed from: protected */
    public void invalidateSession(ClientHandshakeState state) {
        if (state.sessionParameters != null) {
            state.sessionParameters.clear();
            state.sessionParameters = null;
        }
        if (state.tlsSession != null) {
            state.tlsSession.invalidate();
            state.tlsSession = null;
        }
    }

    /* access modifiers changed from: protected */
    public void processCertificateRequest(ClientHandshakeState state, byte[] body) throws IOException {
        if (state.authentication == null) {
            throw new TlsFatalAlert(40);
        }
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        state.certificateRequest = CertificateRequest.parse(state.clientContext, buf);
        TlsProtocol.assertEmpty(buf);
        state.keyExchange.validateCertificateRequest(state.certificateRequest);
    }

    /* access modifiers changed from: protected */
    public void processCertificateStatus(ClientHandshakeState state, byte[] body) throws IOException {
        if (!state.allowCertificateStatus) {
            throw new TlsFatalAlert(10);
        }
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        state.certificateStatus = CertificateStatus.parse(buf);
        TlsProtocol.assertEmpty(buf);
    }

    /* access modifiers changed from: protected */
    public byte[] processHelloVerifyRequest(ClientHandshakeState state, byte[] body) throws IOException {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        ProtocolVersion server_version = TlsUtils.readVersion(buf);
        byte[] cookie = TlsUtils.readOpaque8(buf);
        TlsProtocol.assertEmpty(buf);
        if (!server_version.isEqualOrEarlierVersionOf(state.clientContext.getClientVersion())) {
            throw new TlsFatalAlert(47);
        } else if (ProtocolVersion.DTLSv12.isEqualOrEarlierVersionOf(server_version) || cookie.length <= 32) {
            return cookie;
        } else {
            throw new TlsFatalAlert(47);
        }
    }

    /* access modifiers changed from: protected */
    public void processNewSessionTicket(ClientHandshakeState state, byte[] body) throws IOException {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        NewSessionTicket newSessionTicket = NewSessionTicket.parse(buf);
        TlsProtocol.assertEmpty(buf);
        state.client.notifyNewSessionTicket(newSessionTicket);
    }

    /* access modifiers changed from: protected */
    public Certificate processServerCertificate(ClientHandshakeState state, byte[] body) throws IOException {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        Certificate serverCertificate = Certificate.parse(buf);
        TlsProtocol.assertEmpty(buf);
        state.keyExchange.processServerCertificate(serverCertificate);
        state.authentication = state.client.getAuthentication();
        state.authentication.notifyServerCertificate(serverCertificate);
        return serverCertificate;
    }

    /* access modifiers changed from: protected */
    public void processServerHello(ClientHandshakeState state, byte[] body) throws IOException {
        boolean z;
        SecurityParameters securityParameters = state.clientContext.getSecurityParameters();
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        reportServerVersion(state, TlsUtils.readVersion(buf));
        securityParameters.serverRandom = TlsUtils.readFully(32, (InputStream) buf);
        state.selectedSessionID = TlsUtils.readOpaque8(buf);
        if (state.selectedSessionID.length > 32) {
            throw new TlsFatalAlert(47);
        }
        state.client.notifySessionID(state.selectedSessionID);
        state.resumedSession = state.selectedSessionID.length > 0 && state.tlsSession != null && Arrays.areEqual(state.selectedSessionID, state.tlsSession.getSessionID());
        int selectedCipherSuite = TlsUtils.readUint16(buf);
        if (!Arrays.contains(state.offeredCipherSuites, selectedCipherSuite) || selectedCipherSuite == 0 || CipherSuite.isSCSV(selectedCipherSuite) || !TlsUtils.isValidCipherSuiteForVersion(selectedCipherSuite, state.clientContext.getServerVersion())) {
            throw new TlsFatalAlert(47);
        }
        validateSelectedCipherSuite(selectedCipherSuite, 47);
        state.client.notifySelectedCipherSuite(selectedCipherSuite);
        short selectedCompressionMethod = TlsUtils.readUint8(buf);
        if (!Arrays.contains(state.offeredCompressionMethods, selectedCompressionMethod)) {
            throw new TlsFatalAlert(47);
        }
        state.client.notifySelectedCompressionMethod(selectedCompressionMethod);
        state.serverExtensions = TlsProtocol.readExtensions(buf);
        if (state.serverExtensions != null) {
            Enumeration e = state.serverExtensions.keys();
            while (e.hasMoreElements()) {
                Integer extType = (Integer) e.nextElement();
                if (!extType.equals(TlsProtocol.EXT_RenegotiationInfo)) {
                    if (TlsUtils.getExtensionData(state.clientExtensions, extType) == null) {
                        throw new TlsFatalAlert(AlertDescription.unsupported_extension);
                    } else if (state.resumedSession) {
                    }
                }
            }
        }
        byte[] renegExtData = TlsUtils.getExtensionData(state.serverExtensions, TlsProtocol.EXT_RenegotiationInfo);
        if (renegExtData != null) {
            state.secure_renegotiation = true;
            if (!Arrays.constantTimeAreEqual(renegExtData, TlsProtocol.createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert(40);
            }
        }
        state.client.notifySecureRenegotiation(state.secure_renegotiation);
        Hashtable sessionClientExtensions = state.clientExtensions;
        Hashtable sessionServerExtensions = state.serverExtensions;
        if (state.resumedSession) {
            if (selectedCipherSuite == state.sessionParameters.getCipherSuite() && selectedCompressionMethod == state.sessionParameters.getCompressionAlgorithm()) {
                sessionClientExtensions = null;
                sessionServerExtensions = state.sessionParameters.readServerExtensions();
            } else {
                throw new TlsFatalAlert(47);
            }
        }
        securityParameters.cipherSuite = selectedCipherSuite;
        securityParameters.compressionAlgorithm = selectedCompressionMethod;
        if (sessionServerExtensions != null) {
            boolean serverSentEncryptThenMAC = TlsExtensionsUtils.hasEncryptThenMACExtension(sessionServerExtensions);
            if (!serverSentEncryptThenMAC || TlsUtils.isBlockCipherSuite(securityParameters.getCipherSuite())) {
                securityParameters.encryptThenMAC = serverSentEncryptThenMAC;
                securityParameters.extendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension(sessionServerExtensions);
                securityParameters.maxFragmentLength = evaluateMaxFragmentLengthExtension(state.resumedSession, sessionClientExtensions, sessionServerExtensions, 47);
                securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(sessionServerExtensions);
                state.allowCertificateStatus = !state.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(sessionServerExtensions, TlsExtensionsUtils.EXT_status_request, 47);
                if (state.resumedSession || !TlsUtils.hasExpectedEmptyExtensionData(sessionServerExtensions, TlsProtocol.EXT_SessionTicket, 47)) {
                    z = false;
                } else {
                    z = true;
                }
                state.expectSessionTicket = z;
            } else {
                throw new TlsFatalAlert(47);
            }
        }
        if (sessionClientExtensions != null) {
            state.client.processServerExtensions(sessionServerExtensions);
        }
        securityParameters.prfAlgorithm = TlsProtocol.getPRFAlgorithm(state.clientContext, securityParameters.getCipherSuite());
        securityParameters.verifyDataLength = 12;
    }

    /* access modifiers changed from: protected */
    public void processServerKeyExchange(ClientHandshakeState state, byte[] body) throws IOException {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        state.keyExchange.processServerKeyExchange(buf);
        TlsProtocol.assertEmpty(buf);
    }

    /* access modifiers changed from: protected */
    public void processServerSupplementalData(ClientHandshakeState state, byte[] body) throws IOException {
        state.client.processServerSupplementalData(TlsProtocol.readSupplementalDataMessage(new ByteArrayInputStream(body)));
    }

    /* access modifiers changed from: protected */
    public void reportServerVersion(ClientHandshakeState state, ProtocolVersion server_version) throws IOException {
        TlsClientContextImpl clientContext = state.clientContext;
        ProtocolVersion currentServerVersion = clientContext.getServerVersion();
        if (currentServerVersion == null) {
            clientContext.setServerVersion(server_version);
            state.client.notifyServerVersion(server_version);
        } else if (!currentServerVersion.equals(server_version)) {
            throw new TlsFatalAlert(47);
        }
    }

    protected static byte[] patchClientHelloWithCookie(byte[] clientHelloBody, byte[] cookie) throws IOException {
        int cookieLengthPos = TlsUtils.readUint8(clientHelloBody, 34) + 35;
        int cookiePos = cookieLengthPos + 1;
        byte[] patched = new byte[(clientHelloBody.length + cookie.length)];
        System.arraycopy(clientHelloBody, 0, patched, 0, cookieLengthPos);
        TlsUtils.checkUint8(cookie.length);
        TlsUtils.writeUint8(cookie.length, patched, cookieLengthPos);
        System.arraycopy(cookie, 0, patched, cookiePos, cookie.length);
        System.arraycopy(clientHelloBody, cookiePos, patched, cookie.length + cookiePos, clientHelloBody.length - cookiePos);
        return patched;
    }
}
