package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.util.Arrays;

public class TlsClientProtocol extends TlsProtocol {
    protected TlsAuthentication authentication = null;
    protected CertificateRequest certificateRequest = null;
    protected CertificateStatus certificateStatus = null;
    protected TlsKeyExchange keyExchange = null;
    protected byte[] selectedSessionID = null;
    protected TlsClient tlsClient = null;
    TlsClientContextImpl tlsClientContext = null;

    public TlsClientProtocol(InputStream input, OutputStream output, SecureRandom secureRandom) {
        super(input, output, secureRandom);
    }

    public TlsClientProtocol(SecureRandom secureRandom) {
        super(secureRandom);
    }

    public void connect(TlsClient tlsClient2) throws IOException {
        if (tlsClient2 == null) {
            throw new IllegalArgumentException("'tlsClient' cannot be null");
        } else if (this.tlsClient != null) {
            throw new IllegalStateException("'connect' can only be called once");
        } else {
            this.tlsClient = tlsClient2;
            this.securityParameters = new SecurityParameters();
            this.securityParameters.entity = 1;
            this.tlsClientContext = new TlsClientContextImpl(this.secureRandom, this.securityParameters);
            this.securityParameters.clientRandom = createRandomBlock(tlsClient2.shouldUseGMTUnixTime(), this.tlsClientContext.getNonceRandomGenerator());
            this.tlsClient.init(this.tlsClientContext);
            this.recordStream.init(this.tlsClientContext);
            TlsSession sessionToResume = tlsClient2.getSessionToResume();
            if (sessionToResume != null && sessionToResume.isResumable()) {
                SessionParameters sessionParameters = sessionToResume.exportSessionParameters();
                if (sessionParameters != null) {
                    this.tlsSession = sessionToResume;
                    this.sessionParameters = sessionParameters;
                }
            }
            sendClientHelloMessage();
            this.connection_state = 1;
            blockForHandshake();
        }
    }

    /* access modifiers changed from: protected */
    public void cleanupHandshake() {
        super.cleanupHandshake();
        this.selectedSessionID = null;
        this.keyExchange = null;
        this.authentication = null;
        this.certificateStatus = null;
        this.certificateRequest = null;
    }

    /* access modifiers changed from: protected */
    public TlsContext getContext() {
        return this.tlsClientContext;
    }

    /* access modifiers changed from: 0000 */
    public AbstractTlsContext getContextAdmin() {
        return this.tlsClientContext;
    }

    /* access modifiers changed from: protected */
    public TlsPeer getPeer() {
        return this.tlsClient;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:120:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x014f, code lost:
        r12.keyExchange.skipServerCredentials();
        r12.authentication = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0157, code lost:
        r12.keyExchange.skipServerKeyExchange();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x015c, code lost:
        assertEmpty(r0);
        r12.connection_state = 8;
        r12.recordStream.getHandshakeHash().sealHashAlgorithms();
        r3 = r12.tlsClient.getClientSupplementalData();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0172, code lost:
        if (r3 == null) goto L_0x0177;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0174, code lost:
        sendSupplementalDataMessage(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x0177, code lost:
        r12.connection_state = 9;
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x017e, code lost:
        if (r12.certificateRequest != null) goto L_0x01f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0180, code lost:
        r12.keyExchange.skipClientCredentials();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0185, code lost:
        r12.connection_state = 10;
        sendClientKeyExchangeMessage();
        r12.connection_state = 11;
        r5 = r12.recordStream.prepareToFinish();
        r12.securityParameters.sessionHash = getCurrentPRFHash(getContext(), r5, null);
        establishMasterSecret(getContext(), r12.keyExchange);
        r12.recordStream.setPendingConnectionState(getPeer().getCompression(), getPeer().getCipher());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01c1, code lost:
        if (r2 == null) goto L_0x01ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01c5, code lost:
        if ((r2 instanceof org.spongycastle.crypto.tls.TlsSignerCredentials) == false) goto L_0x01ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x01c7, code lost:
        r8 = (org.spongycastle.crypto.tls.TlsSignerCredentials) r2;
        r7 = org.spongycastle.crypto.tls.TlsUtils.getSignatureAndHashAlgorithm(getContext(), r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x01d2, code lost:
        if (r7 != null) goto L_0x021a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01d4, code lost:
        r4 = r12.securityParameters.getSessionHash();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01da, code lost:
        sendCertificateVerifyMessage(new org.spongycastle.crypto.tls.DigitallySigned(r7, r8.generateCertificateSignature(r4)));
        r12.connection_state = 12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01ea, code lost:
        sendChangeCipherSpecMessage();
        sendFinishedMessage();
        r12.connection_state = 13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01f6, code lost:
        r2 = r12.authentication.getClientCredentials(r12.certificateRequest);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01fe, code lost:
        if (r2 != null) goto L_0x020c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0200, code lost:
        r12.keyExchange.skipClientCredentials();
        sendCertificateMessage(org.spongycastle.crypto.tls.Certificate.EMPTY_CHAIN);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x020c, code lost:
        r12.keyExchange.processClientCredentials(r2);
        sendCertificateMessage(r2.getCertificate());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x021a, code lost:
        r4 = r5.getFinalHash(r7.getHash());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleHandshakeMessage(short r13, byte[] r14) throws java.io.IOException {
        /*
            r12 = this;
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r14)
            boolean r9 = r12.resumedSession
            if (r9 == 0) goto L_0x0030
            r9 = 20
            if (r13 != r9) goto L_0x0012
            short r9 = r12.connection_state
            r10 = 2
            if (r9 == r10) goto L_0x001a
        L_0x0012:
            org.spongycastle.crypto.tls.TlsFatalAlert r9 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r10 = 10
            r9.<init>(r10)
            throw r9
        L_0x001a:
            r12.processFinishedMessage(r0)
            r9 = 15
            r12.connection_state = r9
            r12.sendFinishedMessage()
            r9 = 13
            r12.connection_state = r9
            r9 = 16
            r12.connection_state = r9
            r12.completeHandshake()
        L_0x002f:
            return
        L_0x0030:
            switch(r13) {
                case 0: goto L_0x02b4;
                case 1: goto L_0x0033;
                case 2: goto L_0x00cd;
                case 3: goto L_0x0033;
                case 4: goto L_0x028f;
                case 5: goto L_0x0033;
                case 6: goto L_0x0033;
                case 7: goto L_0x0033;
                case 8: goto L_0x0033;
                case 9: goto L_0x0033;
                case 10: goto L_0x0033;
                case 11: goto L_0x003b;
                case 12: goto L_0x0223;
                case 13: goto L_0x0249;
                case 14: goto L_0x013e;
                case 15: goto L_0x0033;
                case 16: goto L_0x0033;
                case 17: goto L_0x0033;
                case 18: goto L_0x0033;
                case 19: goto L_0x0033;
                case 20: goto L_0x00a4;
                case 21: goto L_0x0033;
                case 22: goto L_0x007e;
                case 23: goto L_0x0128;
                default: goto L_0x0033;
            }
        L_0x0033:
            org.spongycastle.crypto.tls.TlsFatalAlert r9 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r10 = 10
            r9.<init>(r10)
            throw r9
        L_0x003b:
            short r9 = r12.connection_state
            switch(r9) {
                case 2: goto L_0x0048;
                case 3: goto L_0x004c;
                default: goto L_0x0040;
            }
        L_0x0040:
            org.spongycastle.crypto.tls.TlsFatalAlert r9 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r10 = 10
            r9.<init>(r10)
            throw r9
        L_0x0048:
            r9 = 0
            r12.handleSupplementalData(r9)
        L_0x004c:
            org.spongycastle.crypto.tls.Certificate r9 = org.spongycastle.crypto.tls.Certificate.parse(r0)
            r12.peerCertificate = r9
            assertEmpty(r0)
            org.spongycastle.crypto.tls.Certificate r9 = r12.peerCertificate
            if (r9 == 0) goto L_0x0061
            org.spongycastle.crypto.tls.Certificate r9 = r12.peerCertificate
            boolean r9 = r9.isEmpty()
            if (r9 == 0) goto L_0x0064
        L_0x0061:
            r9 = 0
            r12.allowCertificateStatus = r9
        L_0x0064:
            org.spongycastle.crypto.tls.TlsKeyExchange r9 = r12.keyExchange
            org.spongycastle.crypto.tls.Certificate r10 = r12.peerCertificate
            r9.processServerCertificate(r10)
            org.spongycastle.crypto.tls.TlsClient r9 = r12.tlsClient
            org.spongycastle.crypto.tls.TlsAuthentication r9 = r9.getAuthentication()
            r12.authentication = r9
            org.spongycastle.crypto.tls.TlsAuthentication r9 = r12.authentication
            org.spongycastle.crypto.tls.Certificate r10 = r12.peerCertificate
            r9.notifyServerCertificate(r10)
            r9 = 4
            r12.connection_state = r9
            goto L_0x002f
        L_0x007e:
            short r9 = r12.connection_state
            switch(r9) {
                case 4: goto L_0x008b;
                default: goto L_0x0083;
            }
        L_0x0083:
            org.spongycastle.crypto.tls.TlsFatalAlert r9 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r10 = 10
            r9.<init>(r10)
            throw r9
        L_0x008b:
            boolean r9 = r12.allowCertificateStatus
            if (r9 != 0) goto L_0x0097
            org.spongycastle.crypto.tls.TlsFatalAlert r9 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r10 = 10
            r9.<init>(r10)
            throw r9
        L_0x0097:
            org.spongycastle.crypto.tls.CertificateStatus r9 = org.spongycastle.crypto.tls.CertificateStatus.parse(r0)
            r12.certificateStatus = r9
            assertEmpty(r0)
            r9 = 5
            r12.connection_state = r9
            goto L_0x002f
        L_0x00a4:
            short r9 = r12.connection_state
            switch(r9) {
                case 13: goto L_0x00b1;
                case 14: goto L_0x00bd;
                default: goto L_0x00a9;
            }
        L_0x00a9:
            org.spongycastle.crypto.tls.TlsFatalAlert r9 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r10 = 10
            r9.<init>(r10)
            throw r9
        L_0x00b1:
            boolean r9 = r12.expectSessionTicket
            if (r9 == 0) goto L_0x00bd
            org.spongycastle.crypto.tls.TlsFatalAlert r9 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r10 = 10
            r9.<init>(r10)
            throw r9
        L_0x00bd:
            r12.processFinishedMessage(r0)
            r9 = 15
            r12.connection_state = r9
            r9 = 16
            r12.connection_state = r9
            r12.completeHandshake()
            goto L_0x002f
        L_0x00cd:
            short r9 = r12.connection_state
            switch(r9) {
                case 1: goto L_0x00da;
                default: goto L_0x00d2;
            }
        L_0x00d2:
            org.spongycastle.crypto.tls.TlsFatalAlert r9 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r10 = 10
            r9.<init>(r10)
            throw r9
        L_0x00da:
            r12.receiveServerHelloMessage(r0)
            r9 = 2
            r12.connection_state = r9
            org.spongycastle.crypto.tls.RecordStream r9 = r12.recordStream
            r9.notifyHelloComplete()
            r12.applyMaxFragmentLengthExtension()
            boolean r9 = r12.resumedSession
            if (r9 == 0) goto L_0x0114
            org.spongycastle.crypto.tls.SecurityParameters r9 = r12.securityParameters
            org.spongycastle.crypto.tls.SessionParameters r10 = r12.sessionParameters
            byte[] r10 = r10.getMasterSecret()
            byte[] r10 = org.spongycastle.util.Arrays.clone(r10)
            r9.masterSecret = r10
            org.spongycastle.crypto.tls.RecordStream r9 = r12.recordStream
            org.spongycastle.crypto.tls.TlsPeer r10 = r12.getPeer()
            org.spongycastle.crypto.tls.TlsCompression r10 = r10.getCompression()
            org.spongycastle.crypto.tls.TlsPeer r11 = r12.getPeer()
            org.spongycastle.crypto.tls.TlsCipher r11 = r11.getCipher()
            r9.setPendingConnectionState(r10, r11)
            r12.sendChangeCipherSpecMessage()
            goto L_0x002f
        L_0x0114:
            r12.invalidateSession()
            byte[] r9 = r12.selectedSessionID
            int r9 = r9.length
            if (r9 <= 0) goto L_0x002f
            org.spongycastle.crypto.tls.TlsSessionImpl r9 = new org.spongycastle.crypto.tls.TlsSessionImpl
            byte[] r10 = r12.selectedSessionID
            r11 = 0
            r9.<init>(r10, r11)
            r12.tlsSession = r9
            goto L_0x002f
        L_0x0128:
            short r9 = r12.connection_state
            switch(r9) {
                case 2: goto L_0x0135;
                default: goto L_0x012d;
            }
        L_0x012d:
            org.spongycastle.crypto.tls.TlsFatalAlert r9 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r10 = 10
            r9.<init>(r10)
            throw r9
        L_0x0135:
            java.util.Vector r9 = readSupplementalDataMessage(r0)
            r12.handleSupplementalData(r9)
            goto L_0x002f
        L_0x013e:
            short r9 = r12.connection_state
            switch(r9) {
                case 2: goto L_0x014b;
                case 3: goto L_0x014f;
                case 4: goto L_0x0157;
                case 5: goto L_0x0157;
                case 6: goto L_0x015c;
                case 7: goto L_0x015c;
                default: goto L_0x0143;
            }
        L_0x0143:
            org.spongycastle.crypto.tls.TlsFatalAlert r9 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r10 = 40
            r9.<init>(r10)
            throw r9
        L_0x014b:
            r9 = 0
            r12.handleSupplementalData(r9)
        L_0x014f:
            org.spongycastle.crypto.tls.TlsKeyExchange r9 = r12.keyExchange
            r9.skipServerCredentials()
            r9 = 0
            r12.authentication = r9
        L_0x0157:
            org.spongycastle.crypto.tls.TlsKeyExchange r9 = r12.keyExchange
            r9.skipServerKeyExchange()
        L_0x015c:
            assertEmpty(r0)
            r9 = 8
            r12.connection_state = r9
            org.spongycastle.crypto.tls.RecordStream r9 = r12.recordStream
            org.spongycastle.crypto.tls.TlsHandshakeHash r9 = r9.getHandshakeHash()
            r9.sealHashAlgorithms()
            org.spongycastle.crypto.tls.TlsClient r9 = r12.tlsClient
            java.util.Vector r3 = r9.getClientSupplementalData()
            if (r3 == 0) goto L_0x0177
            r12.sendSupplementalDataMessage(r3)
        L_0x0177:
            r9 = 9
            r12.connection_state = r9
            r2 = 0
            org.spongycastle.crypto.tls.CertificateRequest r9 = r12.certificateRequest
            if (r9 != 0) goto L_0x01f6
            org.spongycastle.crypto.tls.TlsKeyExchange r9 = r12.keyExchange
            r9.skipClientCredentials()
        L_0x0185:
            r9 = 10
            r12.connection_state = r9
            r12.sendClientKeyExchangeMessage()
            r9 = 11
            r12.connection_state = r9
            org.spongycastle.crypto.tls.RecordStream r9 = r12.recordStream
            org.spongycastle.crypto.tls.TlsHandshakeHash r5 = r9.prepareToFinish()
            org.spongycastle.crypto.tls.SecurityParameters r9 = r12.securityParameters
            org.spongycastle.crypto.tls.TlsContext r10 = r12.getContext()
            r11 = 0
            byte[] r10 = getCurrentPRFHash(r10, r5, r11)
            r9.sessionHash = r10
            org.spongycastle.crypto.tls.TlsContext r9 = r12.getContext()
            org.spongycastle.crypto.tls.TlsKeyExchange r10 = r12.keyExchange
            establishMasterSecret(r9, r10)
            org.spongycastle.crypto.tls.RecordStream r9 = r12.recordStream
            org.spongycastle.crypto.tls.TlsPeer r10 = r12.getPeer()
            org.spongycastle.crypto.tls.TlsCompression r10 = r10.getCompression()
            org.spongycastle.crypto.tls.TlsPeer r11 = r12.getPeer()
            org.spongycastle.crypto.tls.TlsCipher r11 = r11.getCipher()
            r9.setPendingConnectionState(r10, r11)
            if (r2 == 0) goto L_0x01ea
            boolean r9 = r2 instanceof org.spongycastle.crypto.tls.TlsSignerCredentials
            if (r9 == 0) goto L_0x01ea
            r8 = r2
            org.spongycastle.crypto.tls.TlsSignerCredentials r8 = (org.spongycastle.crypto.tls.TlsSignerCredentials) r8
            org.spongycastle.crypto.tls.TlsContext r9 = r12.getContext()
            org.spongycastle.crypto.tls.SignatureAndHashAlgorithm r7 = org.spongycastle.crypto.tls.TlsUtils.getSignatureAndHashAlgorithm(r9, r8)
            if (r7 != 0) goto L_0x021a
            org.spongycastle.crypto.tls.SecurityParameters r9 = r12.securityParameters
            byte[] r4 = r9.getSessionHash()
        L_0x01da:
            byte[] r6 = r8.generateCertificateSignature(r4)
            org.spongycastle.crypto.tls.DigitallySigned r1 = new org.spongycastle.crypto.tls.DigitallySigned
            r1.<init>(r7, r6)
            r12.sendCertificateVerifyMessage(r1)
            r9 = 12
            r12.connection_state = r9
        L_0x01ea:
            r12.sendChangeCipherSpecMessage()
            r12.sendFinishedMessage()
            r9 = 13
            r12.connection_state = r9
            goto L_0x002f
        L_0x01f6:
            org.spongycastle.crypto.tls.TlsAuthentication r9 = r12.authentication
            org.spongycastle.crypto.tls.CertificateRequest r10 = r12.certificateRequest
            org.spongycastle.crypto.tls.TlsCredentials r2 = r9.getClientCredentials(r10)
            if (r2 != 0) goto L_0x020c
            org.spongycastle.crypto.tls.TlsKeyExchange r9 = r12.keyExchange
            r9.skipClientCredentials()
            org.spongycastle.crypto.tls.Certificate r9 = org.spongycastle.crypto.tls.Certificate.EMPTY_CHAIN
            r12.sendCertificateMessage(r9)
            goto L_0x0185
        L_0x020c:
            org.spongycastle.crypto.tls.TlsKeyExchange r9 = r12.keyExchange
            r9.processClientCredentials(r2)
            org.spongycastle.crypto.tls.Certificate r9 = r2.getCertificate()
            r12.sendCertificateMessage(r9)
            goto L_0x0185
        L_0x021a:
            short r9 = r7.getHash()
            byte[] r4 = r5.getFinalHash(r9)
            goto L_0x01da
        L_0x0223:
            short r9 = r12.connection_state
            switch(r9) {
                case 2: goto L_0x0230;
                case 3: goto L_0x0234;
                case 4: goto L_0x023c;
                case 5: goto L_0x023c;
                default: goto L_0x0228;
            }
        L_0x0228:
            org.spongycastle.crypto.tls.TlsFatalAlert r9 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r10 = 10
            r9.<init>(r10)
            throw r9
        L_0x0230:
            r9 = 0
            r12.handleSupplementalData(r9)
        L_0x0234:
            org.spongycastle.crypto.tls.TlsKeyExchange r9 = r12.keyExchange
            r9.skipServerCredentials()
            r9 = 0
            r12.authentication = r9
        L_0x023c:
            org.spongycastle.crypto.tls.TlsKeyExchange r9 = r12.keyExchange
            r9.processServerKeyExchange(r0)
            assertEmpty(r0)
            r9 = 6
            r12.connection_state = r9
            goto L_0x002f
        L_0x0249:
            short r9 = r12.connection_state
            switch(r9) {
                case 4: goto L_0x0256;
                case 5: goto L_0x0256;
                case 6: goto L_0x025b;
                default: goto L_0x024e;
            }
        L_0x024e:
            org.spongycastle.crypto.tls.TlsFatalAlert r9 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r10 = 10
            r9.<init>(r10)
            throw r9
        L_0x0256:
            org.spongycastle.crypto.tls.TlsKeyExchange r9 = r12.keyExchange
            r9.skipServerKeyExchange()
        L_0x025b:
            org.spongycastle.crypto.tls.TlsAuthentication r9 = r12.authentication
            if (r9 != 0) goto L_0x0267
            org.spongycastle.crypto.tls.TlsFatalAlert r9 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r10 = 40
            r9.<init>(r10)
            throw r9
        L_0x0267:
            org.spongycastle.crypto.tls.TlsContext r9 = r12.getContext()
            org.spongycastle.crypto.tls.CertificateRequest r9 = org.spongycastle.crypto.tls.CertificateRequest.parse(r9, r0)
            r12.certificateRequest = r9
            assertEmpty(r0)
            org.spongycastle.crypto.tls.TlsKeyExchange r9 = r12.keyExchange
            org.spongycastle.crypto.tls.CertificateRequest r10 = r12.certificateRequest
            r9.validateCertificateRequest(r10)
            org.spongycastle.crypto.tls.RecordStream r9 = r12.recordStream
            org.spongycastle.crypto.tls.TlsHandshakeHash r9 = r9.getHandshakeHash()
            org.spongycastle.crypto.tls.CertificateRequest r10 = r12.certificateRequest
            java.util.Vector r10 = r10.getSupportedSignatureAlgorithms()
            org.spongycastle.crypto.tls.TlsUtils.trackHashAlgorithms(r9, r10)
            r9 = 7
            r12.connection_state = r9
            goto L_0x002f
        L_0x028f:
            short r9 = r12.connection_state
            switch(r9) {
                case 13: goto L_0x029c;
                default: goto L_0x0294;
            }
        L_0x0294:
            org.spongycastle.crypto.tls.TlsFatalAlert r9 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r10 = 10
            r9.<init>(r10)
            throw r9
        L_0x029c:
            boolean r9 = r12.expectSessionTicket
            if (r9 != 0) goto L_0x02a8
            org.spongycastle.crypto.tls.TlsFatalAlert r9 = new org.spongycastle.crypto.tls.TlsFatalAlert
            r10 = 10
            r9.<init>(r10)
            throw r9
        L_0x02a8:
            r12.invalidateSession()
            r12.receiveNewSessionTicketMessage(r0)
            r9 = 14
            r12.connection_state = r9
            goto L_0x002f
        L_0x02b4:
            assertEmpty(r0)
            short r9 = r12.connection_state
            r10 = 16
            if (r9 != r10) goto L_0x002f
            r12.refuseRenegotiation()
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.TlsClientProtocol.handleHandshakeMessage(short, byte[]):void");
    }

    /* access modifiers changed from: protected */
    public void handleSupplementalData(Vector serverSupplementalData) throws IOException {
        this.tlsClient.processServerSupplementalData(serverSupplementalData);
        this.connection_state = 3;
        this.keyExchange = this.tlsClient.getKeyExchange();
        this.keyExchange.init(getContext());
    }

    /* access modifiers changed from: protected */
    public void receiveNewSessionTicketMessage(ByteArrayInputStream buf) throws IOException {
        NewSessionTicket newSessionTicket = NewSessionTicket.parse(buf);
        assertEmpty(buf);
        this.tlsClient.notifyNewSessionTicket(newSessionTicket);
    }

    /* access modifiers changed from: protected */
    public void receiveServerHelloMessage(ByteArrayInputStream buf) throws IOException {
        boolean z;
        ProtocolVersion server_version = TlsUtils.readVersion(buf);
        if (server_version.isDTLS()) {
            throw new TlsFatalAlert(47);
        } else if (!server_version.equals(this.recordStream.getReadVersion())) {
            throw new TlsFatalAlert(47);
        } else if (!server_version.isEqualOrEarlierVersionOf(getContext().getClientVersion())) {
            throw new TlsFatalAlert(47);
        } else {
            this.recordStream.setWriteVersion(server_version);
            getContextAdmin().setServerVersion(server_version);
            this.tlsClient.notifyServerVersion(server_version);
            this.securityParameters.serverRandom = TlsUtils.readFully(32, (InputStream) buf);
            this.selectedSessionID = TlsUtils.readOpaque8(buf);
            if (this.selectedSessionID.length > 32) {
                throw new TlsFatalAlert(47);
            }
            this.tlsClient.notifySessionID(this.selectedSessionID);
            this.resumedSession = this.selectedSessionID.length > 0 && this.tlsSession != null && Arrays.areEqual(this.selectedSessionID, this.tlsSession.getSessionID());
            int selectedCipherSuite = TlsUtils.readUint16(buf);
            if (!Arrays.contains(this.offeredCipherSuites, selectedCipherSuite) || selectedCipherSuite == 0 || CipherSuite.isSCSV(selectedCipherSuite) || !TlsUtils.isValidCipherSuiteForVersion(selectedCipherSuite, getContext().getServerVersion())) {
                throw new TlsFatalAlert(47);
            }
            this.tlsClient.notifySelectedCipherSuite(selectedCipherSuite);
            short selectedCompressionMethod = TlsUtils.readUint8(buf);
            if (!Arrays.contains(this.offeredCompressionMethods, selectedCompressionMethod)) {
                throw new TlsFatalAlert(47);
            }
            this.tlsClient.notifySelectedCompressionMethod(selectedCompressionMethod);
            this.serverExtensions = readExtensions(buf);
            if (this.serverExtensions != null) {
                Enumeration e = this.serverExtensions.keys();
                while (e.hasMoreElements()) {
                    Integer extType = (Integer) e.nextElement();
                    if (!extType.equals(EXT_RenegotiationInfo)) {
                        if (TlsUtils.getExtensionData(this.clientExtensions, extType) == null) {
                            throw new TlsFatalAlert(AlertDescription.unsupported_extension);
                        } else if (this.resumedSession) {
                        }
                    }
                }
            }
            byte[] renegExtData = TlsUtils.getExtensionData(this.serverExtensions, EXT_RenegotiationInfo);
            if (renegExtData != null) {
                this.secure_renegotiation = true;
                if (!Arrays.constantTimeAreEqual(renegExtData, createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                    throw new TlsFatalAlert(40);
                }
            }
            this.tlsClient.notifySecureRenegotiation(this.secure_renegotiation);
            Hashtable sessionClientExtensions = this.clientExtensions;
            Hashtable sessionServerExtensions = this.serverExtensions;
            if (this.resumedSession) {
                if (selectedCipherSuite == this.sessionParameters.getCipherSuite() && selectedCompressionMethod == this.sessionParameters.getCompressionAlgorithm()) {
                    sessionClientExtensions = null;
                    sessionServerExtensions = this.sessionParameters.readServerExtensions();
                } else {
                    throw new TlsFatalAlert(47);
                }
            }
            this.securityParameters.cipherSuite = selectedCipherSuite;
            this.securityParameters.compressionAlgorithm = selectedCompressionMethod;
            if (sessionServerExtensions != null) {
                boolean serverSentEncryptThenMAC = TlsExtensionsUtils.hasEncryptThenMACExtension(sessionServerExtensions);
                if (!serverSentEncryptThenMAC || TlsUtils.isBlockCipherSuite(selectedCipherSuite)) {
                    this.securityParameters.encryptThenMAC = serverSentEncryptThenMAC;
                    this.securityParameters.extendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension(sessionServerExtensions);
                    this.securityParameters.maxFragmentLength = processMaxFragmentLengthExtension(sessionClientExtensions, sessionServerExtensions, 47);
                    this.securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(sessionServerExtensions);
                    this.allowCertificateStatus = !this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(sessionServerExtensions, TlsExtensionsUtils.EXT_status_request, 47);
                    if (this.resumedSession || !TlsUtils.hasExpectedEmptyExtensionData(sessionServerExtensions, TlsProtocol.EXT_SessionTicket, 47)) {
                        z = false;
                    } else {
                        z = true;
                    }
                    this.expectSessionTicket = z;
                } else {
                    throw new TlsFatalAlert(47);
                }
            }
            if (sessionClientExtensions != null) {
                this.tlsClient.processServerExtensions(sessionServerExtensions);
            }
            this.securityParameters.prfAlgorithm = getPRFAlgorithm(getContext(), this.securityParameters.getCipherSuite());
            this.securityParameters.verifyDataLength = 12;
        }
    }

    /* access modifiers changed from: protected */
    public void sendCertificateVerifyMessage(DigitallySigned certificateVerify) throws IOException {
        HandshakeMessage message = new HandshakeMessage(this, 15);
        certificateVerify.encode(message);
        message.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendClientHelloMessage() throws IOException {
        boolean noRenegExt;
        boolean noRenegSCSV;
        this.recordStream.setWriteVersion(this.tlsClient.getClientHelloRecordLayerVersion());
        ProtocolVersion client_version = this.tlsClient.getClientVersion();
        if (client_version.isDTLS()) {
            throw new TlsFatalAlert(80);
        }
        getContextAdmin().setClientVersion(client_version);
        byte[] session_id = TlsUtils.EMPTY_BYTES;
        if (this.tlsSession != null) {
            session_id = this.tlsSession.getSessionID();
            if (session_id == null || session_id.length > 32) {
                session_id = TlsUtils.EMPTY_BYTES;
            }
        }
        boolean fallback = this.tlsClient.isFallback();
        this.offeredCipherSuites = this.tlsClient.getCipherSuites();
        this.offeredCompressionMethods = this.tlsClient.getCompressionMethods();
        if (session_id.length > 0 && this.sessionParameters != null && (!Arrays.contains(this.offeredCipherSuites, this.sessionParameters.getCipherSuite()) || !Arrays.contains(this.offeredCompressionMethods, this.sessionParameters.getCompressionAlgorithm()))) {
            session_id = TlsUtils.EMPTY_BYTES;
        }
        this.clientExtensions = this.tlsClient.getClientExtensions();
        HandshakeMessage message = new HandshakeMessage(this, 1);
        TlsUtils.writeVersion(client_version, message);
        message.write(this.securityParameters.getClientRandom());
        TlsUtils.writeOpaque8(session_id, message);
        if (TlsUtils.getExtensionData(this.clientExtensions, EXT_RenegotiationInfo) == null) {
            noRenegExt = true;
        } else {
            noRenegExt = false;
        }
        if (!Arrays.contains(this.offeredCipherSuites, 255)) {
            noRenegSCSV = true;
        } else {
            noRenegSCSV = false;
        }
        if (noRenegExt && noRenegSCSV) {
            this.offeredCipherSuites = Arrays.append(this.offeredCipherSuites, 255);
        }
        if (fallback && !Arrays.contains(this.offeredCipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV)) {
            this.offeredCipherSuites = Arrays.append(this.offeredCipherSuites, (int) CipherSuite.TLS_FALLBACK_SCSV);
        }
        TlsUtils.writeUint16ArrayWithUint16Length(this.offeredCipherSuites, message);
        TlsUtils.writeUint8ArrayWithUint8Length(this.offeredCompressionMethods, message);
        if (this.clientExtensions != null) {
            writeExtensions(message, this.clientExtensions);
        }
        message.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendClientKeyExchangeMessage() throws IOException {
        HandshakeMessage message = new HandshakeMessage(this, 16);
        this.keyExchange.generateClientKeyExchange(message);
        message.writeToRecordStream();
    }
}
