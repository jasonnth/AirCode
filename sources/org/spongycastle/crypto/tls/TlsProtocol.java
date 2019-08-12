package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.crypto.Digest;
import org.spongycastle.crypto.prng.RandomGenerator;
import org.spongycastle.crypto.tls.SessionParameters.Builder;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;

public abstract class TlsProtocol {
    protected static final short CS_CERTIFICATE_REQUEST = 7;
    protected static final short CS_CERTIFICATE_STATUS = 5;
    protected static final short CS_CERTIFICATE_VERIFY = 12;
    protected static final short CS_CLIENT_CERTIFICATE = 10;
    protected static final short CS_CLIENT_FINISHED = 13;
    protected static final short CS_CLIENT_HELLO = 1;
    protected static final short CS_CLIENT_KEY_EXCHANGE = 11;
    protected static final short CS_CLIENT_SUPPLEMENTAL_DATA = 9;
    protected static final short CS_END = 16;
    protected static final short CS_SERVER_CERTIFICATE = 4;
    protected static final short CS_SERVER_FINISHED = 15;
    protected static final short CS_SERVER_HELLO = 2;
    protected static final short CS_SERVER_HELLO_DONE = 8;
    protected static final short CS_SERVER_KEY_EXCHANGE = 6;
    protected static final short CS_SERVER_SESSION_TICKET = 14;
    protected static final short CS_SERVER_SUPPLEMENTAL_DATA = 3;
    protected static final short CS_START = 0;
    protected static final Integer EXT_RenegotiationInfo = Integers.valueOf(65281);
    protected static final Integer EXT_SessionTicket = Integers.valueOf(35);
    private static final String TLS_ERROR_MESSAGE = "Internal TLS error, this could be an attack";
    private ByteQueue alertQueue;
    protected boolean allowCertificateStatus;
    private volatile boolean appDataReady;
    private ByteQueue applicationDataQueue;
    protected boolean blocking;
    protected Hashtable clientExtensions;
    private volatile boolean closed;
    protected short connection_state;
    protected boolean expectSessionTicket;
    private byte[] expected_verify_data;
    private volatile boolean failedWithError;
    private ByteQueue handshakeQueue;
    protected ByteQueueInputStream inputBuffers;
    protected int[] offeredCipherSuites;
    protected short[] offeredCompressionMethods;
    protected ByteQueueOutputStream outputBuffer;
    protected Certificate peerCertificate;
    protected boolean receivedChangeCipherSpec;
    RecordStream recordStream;
    protected boolean resumedSession;
    protected SecureRandom secureRandom;
    protected boolean secure_renegotiation;
    protected SecurityParameters securityParameters;
    protected Hashtable serverExtensions;
    protected SessionParameters sessionParameters;
    private volatile boolean splitApplicationDataRecords;
    private TlsInputStream tlsInputStream;
    private TlsOutputStream tlsOutputStream;
    protected TlsSession tlsSession;

    class HandshakeMessage extends ByteArrayOutputStream {
        HandshakeMessage(TlsProtocol this$02, short handshakeType) throws IOException {
            this(handshakeType, 60);
        }

        HandshakeMessage(short handshakeType, int length) throws IOException {
            super(length + 4);
            TlsUtils.writeUint8(handshakeType, (OutputStream) this);
            this.count += 3;
        }

        /* access modifiers changed from: 0000 */
        public void writeToRecordStream() throws IOException {
            int length = this.count - 4;
            TlsUtils.checkUint24(length);
            TlsUtils.writeUint24(length, this.buf, 1);
            TlsProtocol.this.writeHandshakeMessage(this.buf, 0, this.count);
            this.buf = null;
        }
    }

    /* access modifiers changed from: protected */
    public abstract TlsContext getContext();

    /* access modifiers changed from: 0000 */
    public abstract AbstractTlsContext getContextAdmin();

    /* access modifiers changed from: protected */
    public abstract TlsPeer getPeer();

    /* access modifiers changed from: protected */
    public abstract void handleHandshakeMessage(short s, byte[] bArr) throws IOException;

    public TlsProtocol(InputStream input, OutputStream output, SecureRandom secureRandom2) {
        this.applicationDataQueue = new ByteQueue();
        this.alertQueue = new ByteQueue(2);
        this.handshakeQueue = new ByteQueue();
        this.tlsInputStream = null;
        this.tlsOutputStream = null;
        this.closed = false;
        this.failedWithError = false;
        this.appDataReady = false;
        this.splitApplicationDataRecords = true;
        this.expected_verify_data = null;
        this.tlsSession = null;
        this.sessionParameters = null;
        this.securityParameters = null;
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.connection_state = 0;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
        this.blocking = true;
        this.recordStream = new RecordStream(this, input, output);
        this.secureRandom = secureRandom2;
    }

    public TlsProtocol(SecureRandom secureRandom2) {
        this.applicationDataQueue = new ByteQueue();
        this.alertQueue = new ByteQueue(2);
        this.handshakeQueue = new ByteQueue();
        this.tlsInputStream = null;
        this.tlsOutputStream = null;
        this.closed = false;
        this.failedWithError = false;
        this.appDataReady = false;
        this.splitApplicationDataRecords = true;
        this.expected_verify_data = null;
        this.tlsSession = null;
        this.sessionParameters = null;
        this.securityParameters = null;
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.connection_state = 0;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
        this.blocking = false;
        this.inputBuffers = new ByteQueueInputStream();
        this.outputBuffer = new ByteQueueOutputStream();
        this.recordStream = new RecordStream(this, this.inputBuffers, this.outputBuffer);
        this.secureRandom = secureRandom2;
    }

    /* access modifiers changed from: protected */
    public void handleChangeCipherSpecMessage() throws IOException {
    }

    /* access modifiers changed from: protected */
    public void handleWarningMessage(short description) throws IOException {
    }

    /* access modifiers changed from: protected */
    public void applyMaxFragmentLengthExtension() throws IOException {
        if (this.securityParameters.maxFragmentLength < 0) {
            return;
        }
        if (!MaxFragmentLength.isValid(this.securityParameters.maxFragmentLength)) {
            throw new TlsFatalAlert(80);
        }
        this.recordStream.setPlaintextLimit(1 << (this.securityParameters.maxFragmentLength + CS_SERVER_HELLO_DONE));
    }

    /* access modifiers changed from: protected */
    public void checkReceivedChangeCipherSpec(boolean expected) throws IOException {
        if (expected != this.receivedChangeCipherSpec) {
            throw new TlsFatalAlert(10);
        }
    }

    /* access modifiers changed from: protected */
    public void cleanupHandshake() {
        if (this.expected_verify_data != null) {
            Arrays.fill(this.expected_verify_data, 0);
            this.expected_verify_data = null;
        }
        this.securityParameters.clear();
        this.peerCertificate = null;
        this.offeredCipherSuites = null;
        this.offeredCompressionMethods = null;
        this.clientExtensions = null;
        this.serverExtensions = null;
        this.resumedSession = false;
        this.receivedChangeCipherSpec = false;
        this.secure_renegotiation = false;
        this.allowCertificateStatus = false;
        this.expectSessionTicket = false;
    }

    /* access modifiers changed from: protected */
    public void blockForHandshake() throws IOException {
        if (this.blocking) {
            while (this.connection_state != 16) {
                if (this.closed) {
                }
                safeReadRecord();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void completeHandshake() throws IOException {
        boolean z = true;
        try {
            this.recordStream.finaliseHandshake();
            if (TlsUtils.isTLSv11(getContext())) {
                z = false;
            }
            this.splitApplicationDataRecords = z;
            if (!this.appDataReady) {
                this.appDataReady = true;
                if (this.blocking) {
                    this.tlsInputStream = new TlsInputStream(this);
                    this.tlsOutputStream = new TlsOutputStream(this);
                }
            }
            if (this.tlsSession != null) {
                if (this.sessionParameters == null) {
                    this.sessionParameters = new Builder().setCipherSuite(this.securityParameters.getCipherSuite()).setCompressionAlgorithm(this.securityParameters.getCompressionAlgorithm()).setMasterSecret(this.securityParameters.getMasterSecret()).setPeerCertificate(this.peerCertificate).setPSKIdentity(this.securityParameters.getPSKIdentity()).setSRPIdentity(this.securityParameters.getSRPIdentity()).setServerExtensions(this.serverExtensions).build();
                    this.tlsSession = new TlsSessionImpl(this.tlsSession.getSessionID(), this.sessionParameters);
                }
                getContextAdmin().setResumableSession(this.tlsSession);
            }
            getPeer().notifyHandshakeComplete();
        } finally {
            cleanupHandshake();
        }
    }

    /* access modifiers changed from: protected */
    public void processRecord(short protocol, byte[] buf, int offset, int len) throws IOException {
        switch (protocol) {
            case 20:
                processChangeCipherSpec(buf, offset, len);
                return;
            case 21:
                this.alertQueue.addData(buf, offset, len);
                processAlert();
                return;
            case 22:
                this.handshakeQueue.addData(buf, offset, len);
                processHandshake();
                return;
            case 23:
                if (!this.appDataReady) {
                    throw new TlsFatalAlert(10);
                }
                this.applicationDataQueue.addData(buf, offset, len);
                processApplicationData();
                return;
            case 24:
                if (!this.appDataReady) {
                    throw new TlsFatalAlert(10);
                }
                return;
            default:
                return;
        }
    }

    private void processHandshake() throws IOException {
        boolean read;
        boolean z;
        do {
            read = false;
            if (this.handshakeQueue.available() >= 4) {
                byte[] beginning = new byte[4];
                this.handshakeQueue.read(beginning, 0, 4, 0);
                short type = TlsUtils.readUint8(beginning, 0);
                int len = TlsUtils.readUint24(beginning, 1);
                if (this.handshakeQueue.available() >= len + 4) {
                    byte[] buf = this.handshakeQueue.removeData(len, 4);
                    if (this.connection_state == 16 || type == 20) {
                        z = true;
                    } else {
                        z = false;
                    }
                    checkReceivedChangeCipherSpec(z);
                    switch (type) {
                        case 0:
                            break;
                        case 20:
                            TlsContext ctx = getContext();
                            if (this.expected_verify_data == null && ctx.getSecurityParameters().getMasterSecret() != null) {
                                this.expected_verify_data = createVerifyData(!ctx.isServer());
                                break;
                            }
                    }
                    this.recordStream.updateHandshakeData(beginning, 0, 4);
                    this.recordStream.updateHandshakeData(buf, 0, len);
                    handleHandshakeMessage(type, buf);
                    read = true;
                    continue;
                } else {
                    continue;
                }
            }
        } while (read);
    }

    private void processApplicationData() {
    }

    private void processAlert() throws IOException {
        while (this.alertQueue.available() >= 2) {
            byte[] tmp = this.alertQueue.removeData(2, 0);
            short level = (short) tmp[0];
            short description = (short) tmp[1];
            getPeer().notifyAlertReceived(level, description);
            if (level == 2) {
                invalidateSession();
                this.failedWithError = true;
                this.closed = true;
                this.recordStream.safeClose();
                throw new IOException(TLS_ERROR_MESSAGE);
            }
            if (description == 0) {
                handleClose(false);
            }
            handleWarningMessage(description);
        }
    }

    private void processChangeCipherSpec(byte[] buf, int off, int len) throws IOException {
        int i = 0;
        while (i < len) {
            if (TlsUtils.readUint8(buf, off + i) != 1) {
                throw new TlsFatalAlert(50);
            } else if (this.receivedChangeCipherSpec || this.alertQueue.available() > 0 || this.handshakeQueue.available() > 0) {
                throw new TlsFatalAlert(10);
            } else {
                this.recordStream.receivedReadCipherSpec();
                this.receivedChangeCipherSpec = true;
                handleChangeCipherSpecMessage();
                i++;
            }
        }
    }

    /* access modifiers changed from: protected */
    public int applicationDataAvailable() {
        return this.applicationDataQueue.available();
    }

    /* access modifiers changed from: protected */
    public int readApplicationData(byte[] buf, int offset, int len) throws IOException {
        if (len < 1) {
            return 0;
        }
        while (this.applicationDataQueue.available() == 0) {
            if (!this.closed) {
                safeReadRecord();
            } else if (!this.failedWithError) {
                return -1;
            } else {
                throw new IOException(TLS_ERROR_MESSAGE);
            }
        }
        int len2 = Math.min(len, this.applicationDataQueue.available());
        this.applicationDataQueue.removeData(buf, offset, len2, 0);
        return len2;
    }

    /* access modifiers changed from: protected */
    public void safeReadRecord() throws IOException {
        try {
            if (!this.recordStream.readRecord()) {
                throw new EOFException();
            }
        } catch (TlsFatalAlert e) {
            if (!this.closed) {
                failWithError(2, e.getAlertDescription(), "Failed to read record", e);
            }
            throw e;
        } catch (IOException e2) {
            if (!this.closed) {
                failWithError(2, 80, "Failed to read record", e2);
            }
            throw e2;
        } catch (RuntimeException e3) {
            if (!this.closed) {
                failWithError(2, 80, "Failed to read record", e3);
            }
            throw e3;
        }
    }

    /* access modifiers changed from: protected */
    public void safeWriteRecord(short type, byte[] buf, int offset, int len) throws IOException {
        try {
            this.recordStream.writeRecord(type, buf, offset, len);
        } catch (TlsFatalAlert e) {
            if (!this.closed) {
                failWithError(2, e.getAlertDescription(), "Failed to write record", e);
            }
            throw e;
        } catch (IOException e2) {
            if (!this.closed) {
                failWithError(2, 80, "Failed to write record", e2);
            }
            throw e2;
        } catch (RuntimeException e3) {
            if (!this.closed) {
                failWithError(2, 80, "Failed to write record", e3);
            }
            throw e3;
        }
    }

    /* access modifiers changed from: protected */
    public void writeData(byte[] buf, int offset, int len) throws IOException {
        if (!this.closed) {
            while (len > 0) {
                if (this.splitApplicationDataRecords) {
                    safeWriteRecord(23, buf, offset, 1);
                    offset++;
                    len--;
                }
                if (len > 0) {
                    int toWrite = Math.min(len, this.recordStream.getPlaintextLimit());
                    safeWriteRecord(23, buf, offset, toWrite);
                    offset += toWrite;
                    len -= toWrite;
                }
            }
        } else if (this.failedWithError) {
            throw new IOException(TLS_ERROR_MESSAGE);
        } else {
            throw new IOException("Sorry, connection has been closed, you cannot write more data");
        }
    }

    /* access modifiers changed from: protected */
    public void writeHandshakeMessage(byte[] buf, int off, int len) throws IOException {
        while (len > 0) {
            int toWrite = Math.min(len, this.recordStream.getPlaintextLimit());
            safeWriteRecord(22, buf, off, toWrite);
            off += toWrite;
            len -= toWrite;
        }
    }

    public OutputStream getOutputStream() {
        if (this.blocking) {
            return this.tlsOutputStream;
        }
        throw new IllegalStateException("Cannot use OutputStream in non-blocking mode! Use offerOutput() instead.");
    }

    public InputStream getInputStream() {
        if (this.blocking) {
            return this.tlsInputStream;
        }
        throw new IllegalStateException("Cannot use InputStream in non-blocking mode! Use offerInput() instead.");
    }

    public void offerInput(byte[] input) throws IOException {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use offerInput() in blocking mode! Use getInputStream() instead.");
        } else if (this.closed) {
            throw new IOException("Connection is closed, cannot accept any more input");
        } else {
            this.inputBuffers.addBytes(input);
            while (this.inputBuffers.available() >= 5) {
                byte[] header = new byte[5];
                this.inputBuffers.peek(header);
                if (this.inputBuffers.available() >= TlsUtils.readUint16(header, 3) + 5) {
                    safeReadRecord();
                } else {
                    return;
                }
            }
        }
    }

    public int getAvailableInputBytes() {
        if (!this.blocking) {
            return applicationDataAvailable();
        }
        throw new IllegalStateException("Cannot use getAvailableInputBytes() in blocking mode! Use getInputStream().available() instead.");
    }

    public int readInput(byte[] buffer, int offset, int length) {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use readInput() in blocking mode! Use getInputStream() instead.");
        }
        try {
            return readApplicationData(buffer, offset, Math.min(length, applicationDataAvailable()));
        } catch (IOException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public void offerOutput(byte[] buffer, int offset, int length) throws IOException {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use offerOutput() in blocking mode! Use getOutputStream() instead.");
        } else if (!this.appDataReady) {
            throw new IOException("Application data cannot be sent until the handshake is complete!");
        } else {
            writeData(buffer, offset, length);
        }
    }

    public int getAvailableOutputBytes() {
        if (!this.blocking) {
            return this.outputBuffer.getBuffer().available();
        }
        throw new IllegalStateException("Cannot use getAvailableOutputBytes() in blocking mode! Use getOutputStream() instead.");
    }

    public int readOutput(byte[] buffer, int offset, int length) {
        if (this.blocking) {
            throw new IllegalStateException("Cannot use readOutput() in blocking mode! Use getOutputStream() instead.");
        }
        int bytesToRead = Math.min(getAvailableOutputBytes(), length);
        this.outputBuffer.getBuffer().removeData(buffer, offset, bytesToRead, 0);
        return bytesToRead;
    }

    /* access modifiers changed from: protected */
    public void failWithError(short alertLevel, short alertDescription, String message, Throwable cause) throws IOException {
        if (!this.closed) {
            this.closed = true;
            if (alertLevel == 2) {
                invalidateSession();
                this.failedWithError = true;
            }
            raiseAlert(alertLevel, alertDescription, message, cause);
            this.recordStream.safeClose();
            if (alertLevel != 2) {
                return;
            }
        }
        throw new IOException(TLS_ERROR_MESSAGE);
    }

    /* access modifiers changed from: protected */
    public void invalidateSession() {
        if (this.sessionParameters != null) {
            this.sessionParameters.clear();
            this.sessionParameters = null;
        }
        if (this.tlsSession != null) {
            this.tlsSession.invalidate();
            this.tlsSession = null;
        }
    }

    /* access modifiers changed from: protected */
    public void processFinishedMessage(ByteArrayInputStream buf) throws IOException {
        if (this.expected_verify_data == null) {
            throw new TlsFatalAlert(80);
        }
        byte[] verify_data = TlsUtils.readFully(this.expected_verify_data.length, (InputStream) buf);
        assertEmpty(buf);
        if (!Arrays.constantTimeAreEqual(this.expected_verify_data, verify_data)) {
            throw new TlsFatalAlert(51);
        }
    }

    /* access modifiers changed from: protected */
    public void raiseAlert(short alertLevel, short alertDescription, String message, Throwable cause) throws IOException {
        getPeer().notifyAlertRaised(alertLevel, alertDescription, message, cause);
        safeWriteRecord(21, new byte[]{(byte) alertLevel, (byte) alertDescription}, 0, 2);
    }

    /* access modifiers changed from: protected */
    public void raiseWarning(short alertDescription, String message) throws IOException {
        raiseAlert(1, alertDescription, message, null);
    }

    /* access modifiers changed from: protected */
    public void sendCertificateMessage(Certificate certificate) throws IOException {
        if (certificate == null) {
            certificate = Certificate.EMPTY_CHAIN;
        }
        if (certificate.isEmpty() && !getContext().isServer()) {
            ProtocolVersion serverVersion = getContext().getServerVersion();
            if (serverVersion.isSSL()) {
                raiseWarning(41, serverVersion.toString() + " client didn't provide credentials");
                return;
            }
        }
        HandshakeMessage message = new HandshakeMessage(this, 11);
        certificate.encode(message);
        message.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendChangeCipherSpecMessage() throws IOException {
        byte[] message = {1};
        safeWriteRecord(20, message, 0, message.length);
        this.recordStream.sentWriteCipherSpec();
    }

    /* access modifiers changed from: protected */
    public void sendFinishedMessage() throws IOException {
        byte[] verify_data = createVerifyData(getContext().isServer());
        HandshakeMessage message = new HandshakeMessage(20, verify_data.length);
        message.write(verify_data);
        message.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public void sendSupplementalDataMessage(Vector supplementalData) throws IOException {
        HandshakeMessage message = new HandshakeMessage(this, 23);
        writeSupplementalData(message, supplementalData);
        message.writeToRecordStream();
    }

    /* access modifiers changed from: protected */
    public byte[] createVerifyData(boolean isServer) {
        TlsContext context = getContext();
        return TlsUtils.calculateVerifyData(context, isServer ? ExporterLabel.server_finished : ExporterLabel.client_finished, getCurrentPRFHash(context, this.recordStream.getHandshakeHash(), isServer ? TlsUtils.SSL_SERVER : TlsUtils.SSL_CLIENT));
    }

    public void close() throws IOException {
        handleClose(true);
    }

    /* access modifiers changed from: protected */
    public void handleClose(boolean user_canceled) throws IOException {
        if (!this.closed) {
            if (user_canceled && !this.appDataReady) {
                raiseWarning(90, "User canceled handshake");
            }
            failWithError(1, 0, "Connection closed", null);
        }
    }

    /* access modifiers changed from: protected */
    public void flush() throws IOException {
        this.recordStream.flush();
    }

    public boolean isClosed() {
        return this.closed;
    }

    /* access modifiers changed from: protected */
    public short processMaxFragmentLengthExtension(Hashtable clientExtensions2, Hashtable serverExtensions2, short alertDescription) throws IOException {
        short maxFragmentLength = TlsExtensionsUtils.getMaxFragmentLengthExtension(serverExtensions2);
        if (maxFragmentLength < 0 || (MaxFragmentLength.isValid(maxFragmentLength) && (this.resumedSession || maxFragmentLength == TlsExtensionsUtils.getMaxFragmentLengthExtension(clientExtensions2)))) {
            return maxFragmentLength;
        }
        throw new TlsFatalAlert(alertDescription);
    }

    /* access modifiers changed from: protected */
    public void refuseRenegotiation() throws IOException {
        if (TlsUtils.isSSL(getContext())) {
            throw new TlsFatalAlert(40);
        }
        raiseWarning(100, "Renegotiation not supported");
    }

    protected static void assertEmpty(ByteArrayInputStream buf) throws IOException {
        if (buf.available() > 0) {
            throw new TlsFatalAlert(50);
        }
    }

    protected static byte[] createRandomBlock(boolean useGMTUnixTime, RandomGenerator randomGenerator) {
        byte[] result = new byte[32];
        randomGenerator.nextBytes(result);
        if (useGMTUnixTime) {
            TlsUtils.writeGMTUnixTime(result, 0);
        }
        return result;
    }

    protected static byte[] createRenegotiationInfo(byte[] renegotiated_connection) throws IOException {
        return TlsUtils.encodeOpaque8(renegotiated_connection);
    }

    protected static void establishMasterSecret(TlsContext context, TlsKeyExchange keyExchange) throws IOException {
        byte[] pre_master_secret = keyExchange.generatePremasterSecret();
        try {
            context.getSecurityParameters().masterSecret = TlsUtils.calculateMasterSecret(context, pre_master_secret);
        } finally {
            if (pre_master_secret != null) {
                Arrays.fill(pre_master_secret, 0);
            }
        }
    }

    protected static byte[] getCurrentPRFHash(TlsContext context, TlsHandshakeHash handshakeHash, byte[] sslSender) {
        Digest d = handshakeHash.forkPRFHash();
        if (sslSender != null && TlsUtils.isSSL(context)) {
            d.update(sslSender, 0, sslSender.length);
        }
        byte[] bs = new byte[d.getDigestSize()];
        d.doFinal(bs, 0);
        return bs;
    }

    protected static Hashtable readExtensions(ByteArrayInputStream input) throws IOException {
        if (input.available() < 1) {
            return null;
        }
        byte[] extBytes = TlsUtils.readOpaque16(input);
        assertEmpty(input);
        ByteArrayInputStream buf = new ByteArrayInputStream(extBytes);
        Hashtable extensions = new Hashtable();
        while (buf.available() > 0) {
            if (extensions.put(Integers.valueOf(TlsUtils.readUint16(buf)), TlsUtils.readOpaque16(buf)) != null) {
                throw new TlsFatalAlert(47);
            }
        }
        return extensions;
    }

    protected static Vector readSupplementalDataMessage(ByteArrayInputStream input) throws IOException {
        byte[] supp_data = TlsUtils.readOpaque24(input);
        assertEmpty(input);
        ByteArrayInputStream buf = new ByteArrayInputStream(supp_data);
        Vector supplementalData = new Vector();
        while (buf.available() > 0) {
            supplementalData.addElement(new SupplementalDataEntry(TlsUtils.readUint16(buf), TlsUtils.readOpaque16(buf)));
        }
        return supplementalData;
    }

    protected static void writeExtensions(OutputStream output, Hashtable extensions) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        Enumeration keys = extensions.keys();
        while (keys.hasMoreElements()) {
            Integer key = (Integer) keys.nextElement();
            int extension_type = key.intValue();
            byte[] extension_data = (byte[]) extensions.get(key);
            TlsUtils.checkUint16(extension_type);
            TlsUtils.writeUint16(extension_type, buf);
            TlsUtils.writeOpaque16(extension_data, buf);
        }
        TlsUtils.writeOpaque16(buf.toByteArray(), output);
    }

    protected static void writeSupplementalData(OutputStream output, Vector supplementalData) throws IOException {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        for (int i = 0; i < supplementalData.size(); i++) {
            SupplementalDataEntry entry = (SupplementalDataEntry) supplementalData.elementAt(i);
            int supp_data_type = entry.getDataType();
            TlsUtils.checkUint16(supp_data_type);
            TlsUtils.writeUint16(supp_data_type, buf);
            TlsUtils.writeOpaque16(entry.getData(), buf);
        }
        TlsUtils.writeOpaque24(buf.toByteArray(), output);
    }

    protected static int getPRFAlgorithm(TlsContext context, int ciphersuite) throws IOException {
        boolean isTLSv12 = TlsUtils.isTLSv12(context);
        switch (ciphersuite) {
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 103:
            case 104:
            case 105:
            case 106:
            case 107:
            case CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256 /*156*/:
            case 158:
            case 160:
            case CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256 /*162*/:
            case CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256 /*164*/:
            case 168:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256 /*170*/:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256 /*172*/:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256 /*186*/:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256 /*187*/:
            case 188:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256 /*189*/:
            case 190:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256 /*191*/:
            case 192:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256 /*193*/:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256 /*194*/:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256 /*195*/:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256 /*196*/:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256 /*197*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 /*49187*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 /*49189*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 /*49191*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 /*49193*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 /*49195*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 /*49197*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 /*49199*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 /*49201*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /*49266*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /*49268*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /*49270*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256 /*49272*/:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_128_GCM_SHA256 /*49274*/:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /*49276*/:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /*49278*/:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_GCM_SHA256 /*49280*/:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_GCM_SHA256 /*49282*/:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_GCM_SHA256 /*49284*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /*49286*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /*49288*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /*49290*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /*49292*/:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_128_GCM_SHA256 /*49294*/:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_128_GCM_SHA256 /*49296*/:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_128_GCM_SHA256 /*49298*/:
            case CipherSuite.TLS_RSA_WITH_AES_128_CCM /*49308*/:
            case CipherSuite.TLS_RSA_WITH_AES_256_CCM /*49309*/:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM /*49310*/:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM /*49311*/:
            case CipherSuite.TLS_RSA_WITH_AES_128_CCM_8 /*49312*/:
            case CipherSuite.TLS_RSA_WITH_AES_256_CCM_8 /*49313*/:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_128_CCM_8 /*49314*/:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_CCM_8 /*49315*/:
            case CipherSuite.TLS_PSK_WITH_AES_128_CCM /*49316*/:
            case CipherSuite.TLS_PSK_WITH_AES_256_CCM /*49317*/:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_128_CCM /*49318*/:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CCM /*49319*/:
            case CipherSuite.TLS_PSK_WITH_AES_128_CCM_8 /*49320*/:
            case CipherSuite.TLS_PSK_WITH_AES_256_CCM_8 /*49321*/:
            case CipherSuite.TLS_PSK_DHE_WITH_AES_128_CCM_8 /*49322*/:
            case CipherSuite.TLS_PSK_DHE_WITH_AES_256_CCM_8 /*49323*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM /*49324*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM /*49325*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CCM_8 /*49326*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CCM_8 /*49327*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /*52243*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /*52244*/:
            case CipherSuite.TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /*52245*/:
                if (isTLSv12) {
                    return 1;
                }
                throw new TlsFatalAlert(47);
            case CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384 /*157*/:
            case CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384 /*159*/:
            case CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384 /*161*/:
            case CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384 /*163*/:
            case CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384 /*165*/:
            case CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384 /*169*/:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384 /*171*/:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384 /*173*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 /*49188*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 /*49190*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 /*49192*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 /*49194*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 /*49196*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 /*49198*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 /*49200*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 /*49202*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /*49267*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /*49269*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384 /*49271*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384 /*49273*/:
            case CipherSuite.TLS_RSA_WITH_CAMELLIA_256_GCM_SHA384 /*49275*/:
            case CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /*49277*/:
            case CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /*49279*/:
            case CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_GCM_SHA384 /*49281*/:
            case CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_GCM_SHA384 /*49283*/:
            case CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_GCM_SHA384 /*49285*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /*49287*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /*49289*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /*49291*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /*49293*/:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_GCM_SHA384 /*49295*/:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_GCM_SHA384 /*49297*/:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_GCM_SHA384 /*49299*/:
                if (isTLSv12) {
                    return 2;
                }
                throw new TlsFatalAlert(47);
            case CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384 /*175*/:
            case CipherSuite.TLS_PSK_WITH_NULL_SHA384 /*177*/:
            case CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384 /*179*/:
            case CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384 /*181*/:
            case CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384 /*183*/:
            case CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384 /*185*/:
            case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384 /*49208*/:
            case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA384 /*49211*/:
            case CipherSuite.TLS_PSK_WITH_CAMELLIA_256_CBC_SHA384 /*49301*/:
            case CipherSuite.TLS_DHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /*49303*/:
            case CipherSuite.TLS_RSA_PSK_WITH_CAMELLIA_256_CBC_SHA384 /*49305*/:
            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /*49307*/:
                if (isTLSv12) {
                    return 2;
                }
                return 0;
            default:
                if (isTLSv12) {
                    return 1;
                }
                return 0;
        }
    }
}
