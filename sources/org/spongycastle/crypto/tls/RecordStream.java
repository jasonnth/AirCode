package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class RecordStream {
    private static int DEFAULT_PLAINTEXT_LIMIT = 16384;
    static final int TLS_HEADER_LENGTH_OFFSET = 3;
    static final int TLS_HEADER_SIZE = 5;
    static final int TLS_HEADER_TYPE_OFFSET = 0;
    static final int TLS_HEADER_VERSION_OFFSET = 1;
    private ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    private int ciphertextLimit;
    private int compressedLimit;
    private TlsProtocol handler;
    private TlsHandshakeHash handshakeHash = null;
    private InputStream input;
    private OutputStream output;
    private TlsCipher pendingCipher = null;
    private TlsCompression pendingCompression = null;
    private int plaintextLimit;
    private TlsCipher readCipher = null;
    private TlsCompression readCompression = null;
    private long readSeqNo = 0;
    private ProtocolVersion readVersion = null;
    private boolean restrictReadVersion = true;
    private TlsCipher writeCipher = null;
    private TlsCompression writeCompression = null;
    private long writeSeqNo = 0;
    private ProtocolVersion writeVersion = null;

    RecordStream(TlsProtocol handler2, InputStream input2, OutputStream output2) {
        this.handler = handler2;
        this.input = input2;
        this.output = output2;
        this.readCompression = new TlsNullCompression();
        this.writeCompression = this.readCompression;
    }

    /* access modifiers changed from: 0000 */
    public void init(TlsContext context) {
        this.readCipher = new TlsNullCipher(context);
        this.writeCipher = this.readCipher;
        this.handshakeHash = new DeferredHash();
        this.handshakeHash.init(context);
        setPlaintextLimit(DEFAULT_PLAINTEXT_LIMIT);
    }

    /* access modifiers changed from: 0000 */
    public int getPlaintextLimit() {
        return this.plaintextLimit;
    }

    /* access modifiers changed from: 0000 */
    public void setPlaintextLimit(int plaintextLimit2) {
        this.plaintextLimit = plaintextLimit2;
        this.compressedLimit = this.plaintextLimit + 1024;
        this.ciphertextLimit = this.compressedLimit + 1024;
    }

    /* access modifiers changed from: 0000 */
    public ProtocolVersion getReadVersion() {
        return this.readVersion;
    }

    /* access modifiers changed from: 0000 */
    public void setReadVersion(ProtocolVersion readVersion2) {
        this.readVersion = readVersion2;
    }

    /* access modifiers changed from: 0000 */
    public void setWriteVersion(ProtocolVersion writeVersion2) {
        this.writeVersion = writeVersion2;
    }

    /* access modifiers changed from: 0000 */
    public void setRestrictReadVersion(boolean enabled) {
        this.restrictReadVersion = enabled;
    }

    /* access modifiers changed from: 0000 */
    public void setPendingConnectionState(TlsCompression tlsCompression, TlsCipher tlsCipher) {
        this.pendingCompression = tlsCompression;
        this.pendingCipher = tlsCipher;
    }

    /* access modifiers changed from: 0000 */
    public void sentWriteCipherSpec() throws IOException {
        if (this.pendingCompression == null || this.pendingCipher == null) {
            throw new TlsFatalAlert(40);
        }
        this.writeCompression = this.pendingCompression;
        this.writeCipher = this.pendingCipher;
        this.writeSeqNo = 0;
    }

    /* access modifiers changed from: 0000 */
    public void receivedReadCipherSpec() throws IOException {
        if (this.pendingCompression == null || this.pendingCipher == null) {
            throw new TlsFatalAlert(40);
        }
        this.readCompression = this.pendingCompression;
        this.readCipher = this.pendingCipher;
        this.readSeqNo = 0;
    }

    /* access modifiers changed from: 0000 */
    public void finaliseHandshake() throws IOException {
        if (this.readCompression == this.pendingCompression && this.writeCompression == this.pendingCompression && this.readCipher == this.pendingCipher && this.writeCipher == this.pendingCipher) {
            this.pendingCompression = null;
            this.pendingCipher = null;
            return;
        }
        throw new TlsFatalAlert(40);
    }

    /* access modifiers changed from: 0000 */
    public boolean readRecord() throws IOException {
        byte[] recordHeader = TlsUtils.readAllOrNothing(5, this.input);
        if (recordHeader == null) {
            return false;
        }
        short type = TlsUtils.readUint8(recordHeader, 0);
        checkType(type, 10);
        if (this.restrictReadVersion) {
            ProtocolVersion version = TlsUtils.readVersion(recordHeader, 1);
            if (this.readVersion == null) {
                this.readVersion = version;
            } else if (!version.equals(this.readVersion)) {
                throw new TlsFatalAlert(47);
            }
        } else if ((TlsUtils.readVersionRaw(recordHeader, 1) & -256) != 768) {
            throw new TlsFatalAlert(47);
        }
        byte[] plaintext = decodeAndVerify(type, this.input, TlsUtils.readUint16(recordHeader, 3));
        this.handler.processRecord(type, plaintext, 0, plaintext.length);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public byte[] decodeAndVerify(short type, InputStream input2, int len) throws IOException {
        checkLength(len, this.ciphertextLimit, 22);
        byte[] buf = TlsUtils.readFully(len, input2);
        TlsCipher tlsCipher = this.readCipher;
        long j = this.readSeqNo;
        this.readSeqNo = 1 + j;
        byte[] decoded = tlsCipher.decodeCiphertext(j, type, buf, 0, buf.length);
        checkLength(decoded.length, this.compressedLimit, 22);
        OutputStream cOut = this.readCompression.decompress(this.buffer);
        if (cOut != this.buffer) {
            cOut.write(decoded, 0, decoded.length);
            cOut.flush();
            decoded = getBufferContents();
        }
        checkLength(decoded.length, this.plaintextLimit, 30);
        if (decoded.length >= 1 || type == 23) {
            return decoded;
        }
        throw new TlsFatalAlert(47);
    }

    /* access modifiers changed from: 0000 */
    public void writeRecord(short type, byte[] plaintext, int plaintextOffset, int plaintextLength) throws IOException {
        byte[] ciphertext;
        if (this.writeVersion != null) {
            checkType(type, 80);
            checkLength(plaintextLength, this.plaintextLimit, 80);
            if (plaintextLength >= 1 || type == 23) {
                if (type == 22) {
                    updateHandshakeData(plaintext, plaintextOffset, plaintextLength);
                }
                OutputStream cOut = this.writeCompression.compress(this.buffer);
                if (cOut == this.buffer) {
                    TlsCipher tlsCipher = this.writeCipher;
                    long j = this.writeSeqNo;
                    this.writeSeqNo = 1 + j;
                    ciphertext = tlsCipher.encodePlaintext(j, type, plaintext, plaintextOffset, plaintextLength);
                } else {
                    cOut.write(plaintext, plaintextOffset, plaintextLength);
                    cOut.flush();
                    byte[] compressed = getBufferContents();
                    checkLength(compressed.length, plaintextLength + 1024, 80);
                    TlsCipher tlsCipher2 = this.writeCipher;
                    long j2 = this.writeSeqNo;
                    this.writeSeqNo = 1 + j2;
                    ciphertext = tlsCipher2.encodePlaintext(j2, type, compressed, 0, compressed.length);
                }
                checkLength(ciphertext.length, this.ciphertextLimit, 80);
                byte[] record = new byte[(ciphertext.length + 5)];
                TlsUtils.writeUint8(type, record, 0);
                TlsUtils.writeVersion(this.writeVersion, record, 1);
                TlsUtils.writeUint16(ciphertext.length, record, 3);
                System.arraycopy(ciphertext, 0, record, 5, ciphertext.length);
                this.output.write(record);
                this.output.flush();
                return;
            }
            throw new TlsFatalAlert(80);
        }
    }

    /* access modifiers changed from: 0000 */
    public void notifyHelloComplete() {
        this.handshakeHash = this.handshakeHash.notifyPRFDetermined();
    }

    /* access modifiers changed from: 0000 */
    public TlsHandshakeHash getHandshakeHash() {
        return this.handshakeHash;
    }

    /* access modifiers changed from: 0000 */
    public TlsHandshakeHash prepareToFinish() {
        TlsHandshakeHash result = this.handshakeHash;
        this.handshakeHash = this.handshakeHash.stopTracking();
        return result;
    }

    /* access modifiers changed from: 0000 */
    public void updateHandshakeData(byte[] message, int offset, int len) {
        this.handshakeHash.update(message, offset, len);
    }

    /* access modifiers changed from: 0000 */
    public void safeClose() {
        try {
            this.input.close();
        } catch (IOException e) {
        }
        try {
            this.output.close();
        } catch (IOException e2) {
        }
    }

    /* access modifiers changed from: 0000 */
    public void flush() throws IOException {
        this.output.flush();
    }

    private byte[] getBufferContents() {
        byte[] contents = this.buffer.toByteArray();
        this.buffer.reset();
        return contents;
    }

    private static void checkType(short type, short alertDescription) throws IOException {
        switch (type) {
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
                return;
            default:
                throw new TlsFatalAlert(alertDescription);
        }
    }

    private static void checkLength(int length, int limit, short alertDescription) throws IOException {
        if (length > limit) {
            throw new TlsFatalAlert(alertDescription);
        }
    }
}
