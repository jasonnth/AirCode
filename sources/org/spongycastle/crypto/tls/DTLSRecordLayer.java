package org.spongycastle.crypto.tls;

import java.io.IOException;

class DTLSRecordLayer implements DatagramTransport {
    private static final int MAX_FRAGMENT_LENGTH = 16384;
    private static final int RECORD_HEADER_LENGTH = 13;
    private static final long RETRANSMIT_TIMEOUT = 240000;
    private static final long TCP_MSL = 120000;
    private volatile boolean closed = false;
    private final TlsContext context;
    private DTLSEpoch currentEpoch;
    private volatile boolean failed = false;
    private volatile boolean inHandshake;
    private final TlsPeer peer;
    private DTLSEpoch pendingEpoch;
    private volatile int plaintextLimit;
    private DTLSEpoch readEpoch;
    private volatile ProtocolVersion readVersion = null;
    private final ByteQueue recordQueue = new ByteQueue();
    private DTLSHandshakeRetransmit retransmit = null;
    private DTLSEpoch retransmitEpoch = null;
    private long retransmitExpiry = 0;
    private final DatagramTransport transport;
    private DTLSEpoch writeEpoch;
    private volatile ProtocolVersion writeVersion = null;

    DTLSRecordLayer(DatagramTransport transport2, TlsContext context2, TlsPeer peer2, short contentType) {
        this.transport = transport2;
        this.context = context2;
        this.peer = peer2;
        this.inHandshake = true;
        this.currentEpoch = new DTLSEpoch(0, new TlsNullCipher(context2));
        this.pendingEpoch = null;
        this.readEpoch = this.currentEpoch;
        this.writeEpoch = this.currentEpoch;
        setPlaintextLimit(16384);
    }

    /* access modifiers changed from: 0000 */
    public void setPlaintextLimit(int plaintextLimit2) {
        this.plaintextLimit = plaintextLimit2;
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
    public void initPendingEpoch(TlsCipher pendingCipher) {
        if (this.pendingEpoch != null) {
            throw new IllegalStateException();
        }
        this.pendingEpoch = new DTLSEpoch(this.writeEpoch.getEpoch() + 1, pendingCipher);
    }

    /* access modifiers changed from: 0000 */
    public void handshakeSuccessful(DTLSHandshakeRetransmit retransmit2) {
        if (this.readEpoch == this.currentEpoch || this.writeEpoch == this.currentEpoch) {
            throw new IllegalStateException();
        }
        if (retransmit2 != null) {
            this.retransmit = retransmit2;
            this.retransmitEpoch = this.currentEpoch;
            this.retransmitExpiry = System.currentTimeMillis() + RETRANSMIT_TIMEOUT;
        }
        this.inHandshake = false;
        this.currentEpoch = this.pendingEpoch;
        this.pendingEpoch = null;
    }

    /* access modifiers changed from: 0000 */
    public void resetWriteEpoch() {
        if (this.retransmitEpoch != null) {
            this.writeEpoch = this.retransmitEpoch;
        } else {
            this.writeEpoch = this.currentEpoch;
        }
    }

    public int getReceiveLimit() throws IOException {
        return Math.min(this.plaintextLimit, this.readEpoch.getCipher().getPlaintextLimit(this.transport.getReceiveLimit() - 13));
    }

    public int getSendLimit() throws IOException {
        return Math.min(this.plaintextLimit, this.writeEpoch.getCipher().getPlaintextLimit(this.transport.getSendLimit() - 13));
    }

    public int receive(byte[] buf, int off, int len, int waitMillis) throws IOException {
        byte[] plaintext;
        byte[] record = null;
        while (true) {
            int receiveLimit = Math.min(len, getReceiveLimit()) + 13;
            if (record == null || record.length < receiveLimit) {
                record = new byte[receiveLimit];
            }
            try {
                if (this.retransmit != null && System.currentTimeMillis() > this.retransmitExpiry) {
                    this.retransmit = null;
                    this.retransmitEpoch = null;
                }
                int received = receiveRecord(record, 0, receiveLimit, waitMillis);
                if (received >= 0) {
                    if (received >= 13 && received == TlsUtils.readUint16(record, 11) + 13) {
                        short type = TlsUtils.readUint8(record, 0);
                        switch (type) {
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                                int epoch = TlsUtils.readUint16(record, 3);
                                DTLSEpoch recordEpoch = null;
                                if (epoch == this.readEpoch.getEpoch()) {
                                    recordEpoch = this.readEpoch;
                                } else if (type == 22 && this.retransmitEpoch != null && epoch == this.retransmitEpoch.getEpoch()) {
                                    recordEpoch = this.retransmitEpoch;
                                }
                                if (recordEpoch != null) {
                                    long seq = TlsUtils.readUint48(record, 5);
                                    if (!recordEpoch.getReplayWindow().shouldDiscard(seq)) {
                                        ProtocolVersion version = TlsUtils.readVersion(record, 1);
                                        if (version.isDTLS() && (this.readVersion == null || this.readVersion.equals(version))) {
                                            plaintext = recordEpoch.getCipher().decodeCiphertext(getMacSequenceNumber(recordEpoch.getEpoch(), seq), type, record, 13, received - 13);
                                            recordEpoch.getReplayWindow().reportAuthenticated(seq);
                                            if (plaintext.length <= this.plaintextLimit) {
                                                if (this.readVersion == null) {
                                                    this.readVersion = version;
                                                }
                                                switch (type) {
                                                    case 20:
                                                        for (int i = 0; i < plaintext.length; i++) {
                                                            if (TlsUtils.readUint8(plaintext, i) == 1 && this.pendingEpoch != null) {
                                                                this.readEpoch = this.pendingEpoch;
                                                            }
                                                        }
                                                        continue;
                                                    case 21:
                                                        if (plaintext.length == 2) {
                                                            short alertLevel = (short) plaintext[0];
                                                            short alertDescription = (short) plaintext[1];
                                                            this.peer.notifyAlertReceived(alertLevel, alertDescription);
                                                            if (alertLevel != 2) {
                                                                if (alertDescription != 0) {
                                                                    break;
                                                                } else {
                                                                    closeTransport();
                                                                    break;
                                                                }
                                                            } else {
                                                                fail(alertDescription);
                                                                throw new TlsFatalAlert(alertDescription);
                                                            }
                                                        } else {
                                                            continue;
                                                        }
                                                    case 22:
                                                        if (this.inHandshake) {
                                                            break;
                                                        } else if (this.retransmit != null) {
                                                            this.retransmit.receivedHandshakeRecord(epoch, plaintext, 0, plaintext.length);
                                                            break;
                                                        } else {
                                                            continue;
                                                        }
                                                    case 23:
                                                        if (!this.inHandshake) {
                                                            break;
                                                        } else {
                                                            continue;
                                                        }
                                                    case 24:
                                                        break;
                                                }
                                            } else {
                                                continue;
                                            }
                                        }
                                    } else {
                                        continue;
                                    }
                                } else {
                                    continue;
                                }
                                break;
                        }
                    }
                } else {
                    return received;
                }
            } catch (IOException e) {
                throw e;
            }
        }
        if (!this.inHandshake && this.retransmit != null) {
            this.retransmit = null;
            this.retransmitEpoch = null;
        }
        System.arraycopy(plaintext, 0, buf, off, plaintext.length);
        return plaintext.length;
    }

    public void send(byte[] buf, int off, int len) throws IOException {
        short contentType = 23;
        if (this.inHandshake || this.writeEpoch == this.retransmitEpoch) {
            contentType = 22;
            if (TlsUtils.readUint8(buf, off) == 20) {
                DTLSEpoch nextEpoch = null;
                if (this.inHandshake) {
                    nextEpoch = this.pendingEpoch;
                } else if (this.writeEpoch == this.retransmitEpoch) {
                    nextEpoch = this.currentEpoch;
                }
                if (nextEpoch == null) {
                    throw new IllegalStateException();
                }
                byte[] data = {1};
                sendRecord(20, data, 0, data.length);
                this.writeEpoch = nextEpoch;
            }
        }
        sendRecord(contentType, buf, off, len);
    }

    public void close() throws IOException {
        if (!this.closed) {
            if (this.inHandshake) {
                warn(90, "User canceled handshake");
            }
            closeTransport();
        }
    }

    /* access modifiers changed from: 0000 */
    public void fail(short alertDescription) {
        if (!this.closed) {
            try {
                raiseAlert(2, alertDescription, null, null);
            } catch (Exception e) {
            }
            this.failed = true;
            closeTransport();
        }
    }

    /* access modifiers changed from: 0000 */
    public void warn(short alertDescription, String message) throws IOException {
        raiseAlert(1, alertDescription, message, null);
    }

    private void closeTransport() {
        if (!this.closed) {
            try {
                if (!this.failed) {
                    warn(0, null);
                }
                this.transport.close();
            } catch (Exception e) {
            }
            this.closed = true;
        }
    }

    private void raiseAlert(short alertLevel, short alertDescription, String message, Throwable cause) throws IOException {
        this.peer.notifyAlertRaised(alertLevel, alertDescription, message, cause);
        sendRecord(21, new byte[]{(byte) alertLevel, (byte) alertDescription}, 0, 2);
    }

    private int receiveRecord(byte[] buf, int off, int len, int waitMillis) throws IOException {
        if (this.recordQueue.available() > 0) {
            int length = 0;
            if (this.recordQueue.available() >= 13) {
                byte[] lengthBytes = new byte[2];
                this.recordQueue.read(lengthBytes, 0, 2, 11);
                length = TlsUtils.readUint16(lengthBytes, 0);
            }
            int received = Math.min(this.recordQueue.available(), length + 13);
            this.recordQueue.removeData(buf, off, received, 0);
            return received;
        }
        int received2 = this.transport.receive(buf, off, len, waitMillis);
        if (received2 < 13) {
            return received2;
        }
        int recordLength = TlsUtils.readUint16(buf, off + 11) + 13;
        if (received2 <= recordLength) {
            return received2;
        }
        this.recordQueue.addData(buf, off + recordLength, received2 - recordLength);
        return recordLength;
    }

    private void sendRecord(short contentType, byte[] buf, int off, int len) throws IOException {
        if (this.writeVersion != null) {
            if (len > this.plaintextLimit) {
                throw new TlsFatalAlert(80);
            } else if (len >= 1 || contentType == 23) {
                int recordEpoch = this.writeEpoch.getEpoch();
                long recordSequenceNumber = this.writeEpoch.allocateSequenceNumber();
                byte[] ciphertext = this.writeEpoch.getCipher().encodePlaintext(getMacSequenceNumber(recordEpoch, recordSequenceNumber), contentType, buf, off, len);
                byte[] record = new byte[(ciphertext.length + 13)];
                TlsUtils.writeUint8(contentType, record, 0);
                TlsUtils.writeVersion(this.writeVersion, record, 1);
                TlsUtils.writeUint16(recordEpoch, record, 3);
                TlsUtils.writeUint48(recordSequenceNumber, record, 5);
                TlsUtils.writeUint16(ciphertext.length, record, 11);
                System.arraycopy(ciphertext, 0, record, 13, ciphertext.length);
                this.transport.send(record, 0, record.length);
            } else {
                throw new TlsFatalAlert(80);
            }
        }
    }

    private static long getMacSequenceNumber(int epoch, long sequence_number) {
        return ((((long) epoch) & 4294967295L) << 48) | sequence_number;
    }
}
