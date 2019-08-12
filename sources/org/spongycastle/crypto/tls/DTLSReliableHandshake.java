package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import org.spongycastle.util.Integers;
import p005cn.jpush.android.JPushConstants;

class DTLSReliableHandshake {
    private static final int MAX_RECEIVE_AHEAD = 10;
    /* access modifiers changed from: private */
    public Hashtable currentInboundFlight = new Hashtable();
    private TlsHandshakeHash handshakeHash;
    private int message_seq = 0;
    /* access modifiers changed from: private */
    public int next_receive_seq = 0;
    private Vector outboundFlight = new Vector();
    private Hashtable previousInboundFlight = null;
    private final DTLSRecordLayer recordLayer;
    private boolean sending = true;

    /* renamed from: org.spongycastle.crypto.tls.DTLSReliableHandshake$Message */
    static class C5360Message {
        private final byte[] body;
        private final int message_seq;
        private final short msg_type;

        private C5360Message(int message_seq2, short msg_type2, byte[] body2) {
            this.message_seq = message_seq2;
            this.msg_type = msg_type2;
            this.body = body2;
        }

        public int getSeq() {
            return this.message_seq;
        }

        public short getType() {
            return this.msg_type;
        }

        public byte[] getBody() {
            return this.body;
        }
    }

    static class RecordLayerBuffer extends ByteArrayOutputStream {
        RecordLayerBuffer(int size) {
            super(size);
        }

        /* access modifiers changed from: 0000 */
        public void sendToRecordLayer(DTLSRecordLayer recordLayer) throws IOException {
            recordLayer.send(this.buf, 0, this.count);
            this.buf = null;
        }
    }

    DTLSReliableHandshake(TlsContext context, DTLSRecordLayer transport) {
        this.recordLayer = transport;
        this.handshakeHash = new DeferredHash();
        this.handshakeHash.init(context);
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
    public void sendMessage(short msg_type, byte[] body) throws IOException {
        TlsUtils.checkUint24(body.length);
        if (!this.sending) {
            checkInboundFlight();
            this.sending = true;
            this.outboundFlight.removeAllElements();
        }
        int i = this.message_seq;
        this.message_seq = i + 1;
        C5360Message message = new C5360Message(i, msg_type, body);
        this.outboundFlight.addElement(message);
        writeMessage(message);
        updateHandshakeMessagesDigest(message);
    }

    /* access modifiers changed from: 0000 */
    public byte[] receiveMessageBody(short msg_type) throws IOException {
        C5360Message message = receiveMessage();
        if (message.getType() == msg_type) {
            return message.getBody();
        }
        throw new TlsFatalAlert(10);
    }

    /* access modifiers changed from: 0000 */
    public C5360Message receiveMessage() throws IOException {
        if (this.sending) {
            this.sending = false;
            prepareInboundFlight();
        }
        DTLSReassembler next = (DTLSReassembler) this.currentInboundFlight.get(Integers.valueOf(this.next_receive_seq));
        if (next != null) {
            byte[] body = next.getBodyIfComplete();
            if (body != null) {
                this.previousInboundFlight = null;
                int i = this.next_receive_seq;
                this.next_receive_seq = i + 1;
                return updateHandshakeMessagesDigest(new C5360Message(i, next.getMsgType(), body));
            }
        }
        byte[] buf = null;
        int readTimeoutMillis = 1000;
        while (true) {
            int receiveLimit = this.recordLayer.getReceiveLimit();
            if (buf == null || buf.length < receiveLimit) {
                buf = new byte[receiveLimit];
            }
            while (true) {
                try {
                    int received = this.recordLayer.receive(buf, 0, receiveLimit, readTimeoutMillis);
                    if (received < 0) {
                        continue;
                        break;
                    } else if (received >= 12) {
                        int fragment_length = TlsUtils.readUint24(buf, 9);
                        if (received == fragment_length + 12) {
                            int seq = TlsUtils.readUint16(buf, 4);
                            if (seq <= this.next_receive_seq + 10) {
                                short msg_type = TlsUtils.readUint8(buf, 0);
                                int length = TlsUtils.readUint24(buf, 1);
                                int fragment_offset = TlsUtils.readUint24(buf, 6);
                                if (fragment_offset + fragment_length > length) {
                                    continue;
                                } else if (seq >= this.next_receive_seq) {
                                    DTLSReassembler reassembler = (DTLSReassembler) this.currentInboundFlight.get(Integers.valueOf(seq));
                                    if (reassembler == null) {
                                        reassembler = new DTLSReassembler(msg_type, length);
                                        this.currentInboundFlight.put(Integers.valueOf(seq), reassembler);
                                    }
                                    reassembler.contributeFragment(msg_type, length, buf, 12, fragment_offset, fragment_length);
                                    if (seq == this.next_receive_seq) {
                                        byte[] body2 = reassembler.getBodyIfComplete();
                                        if (body2 != null) {
                                            this.previousInboundFlight = null;
                                            int i2 = this.next_receive_seq;
                                            this.next_receive_seq = i2 + 1;
                                            return updateHandshakeMessagesDigest(new C5360Message(i2, reassembler.getMsgType(), body2));
                                        }
                                    } else {
                                        continue;
                                    }
                                } else if (this.previousInboundFlight != null) {
                                    DTLSReassembler reassembler2 = (DTLSReassembler) this.previousInboundFlight.get(Integers.valueOf(seq));
                                    if (reassembler2 != null) {
                                        reassembler2.contributeFragment(msg_type, length, buf, 12, fragment_offset, fragment_length);
                                        if (checkAll(this.previousInboundFlight)) {
                                            resendOutboundFlight();
                                            readTimeoutMillis = Math.min(readTimeoutMillis * 2, JPushConstants.ONE_MINUTE);
                                            resetAll(this.previousInboundFlight);
                                        }
                                    }
                                }
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    }
                } catch (IOException e) {
                }
            }
            resendOutboundFlight();
            readTimeoutMillis = Math.min(readTimeoutMillis * 2, JPushConstants.ONE_MINUTE);
        }
    }

    /* access modifiers changed from: 0000 */
    public void finish() {
        DTLSHandshakeRetransmit retransmit = null;
        if (!this.sending) {
            checkInboundFlight();
        } else if (this.currentInboundFlight != null) {
            retransmit = new DTLSHandshakeRetransmit() {
                public void receivedHandshakeRecord(int epoch, byte[] buf, int off, int len) throws IOException {
                    if (len >= 12) {
                        int fragment_length = TlsUtils.readUint24(buf, off + 9);
                        if (len == fragment_length + 12) {
                            int seq = TlsUtils.readUint16(buf, off + 4);
                            if (seq < DTLSReliableHandshake.this.next_receive_seq) {
                                short msg_type = TlsUtils.readUint8(buf, off);
                                if (epoch == (msg_type == 20 ? 1 : 0)) {
                                    int length = TlsUtils.readUint24(buf, off + 1);
                                    int fragment_offset = TlsUtils.readUint24(buf, off + 6);
                                    if (fragment_offset + fragment_length <= length) {
                                        DTLSReassembler reassembler = (DTLSReassembler) DTLSReliableHandshake.this.currentInboundFlight.get(Integers.valueOf(seq));
                                        if (reassembler != null) {
                                            reassembler.contributeFragment(msg_type, length, buf, off + 12, fragment_offset, fragment_length);
                                            if (DTLSReliableHandshake.checkAll(DTLSReliableHandshake.this.currentInboundFlight)) {
                                                DTLSReliableHandshake.this.resendOutboundFlight();
                                                DTLSReliableHandshake.resetAll(DTLSReliableHandshake.this.currentInboundFlight);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            };
        }
        this.recordLayer.handshakeSuccessful(retransmit);
    }

    /* access modifiers changed from: 0000 */
    public void resetHandshakeMessagesDigest() {
        this.handshakeHash.reset();
    }

    /* JADX WARNING: Removed duplicated region for block: B:3:0x000c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void checkInboundFlight() {
        /*
            r4 = this;
            java.util.Hashtable r2 = r4.currentInboundFlight
            java.util.Enumeration r0 = r2.keys()
        L_0x0006:
            boolean r2 = r0.hasMoreElements()
            if (r2 == 0) goto L_0x001b
            java.lang.Object r1 = r0.nextElement()
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r2 = r1.intValue()
            int r3 = r4.next_receive_seq
            if (r2 < r3) goto L_0x0006
            goto L_0x0006
        L_0x001b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.tls.DTLSReliableHandshake.checkInboundFlight():void");
    }

    private void prepareInboundFlight() {
        resetAll(this.currentInboundFlight);
        this.previousInboundFlight = this.currentInboundFlight;
        this.currentInboundFlight = new Hashtable();
    }

    /* access modifiers changed from: private */
    public void resendOutboundFlight() throws IOException {
        this.recordLayer.resetWriteEpoch();
        for (int i = 0; i < this.outboundFlight.size(); i++) {
            writeMessage((C5360Message) this.outboundFlight.elementAt(i));
        }
    }

    private C5360Message updateHandshakeMessagesDigest(C5360Message message) throws IOException {
        if (message.getType() != 0) {
            byte[] body = message.getBody();
            byte[] buf = new byte[12];
            TlsUtils.writeUint8(message.getType(), buf, 0);
            TlsUtils.writeUint24(body.length, buf, 1);
            TlsUtils.writeUint16(message.getSeq(), buf, 4);
            TlsUtils.writeUint24(0, buf, 6);
            TlsUtils.writeUint24(body.length, buf, 9);
            this.handshakeHash.update(buf, 0, buf.length);
            this.handshakeHash.update(body, 0, body.length);
        }
        return message;
    }

    private void writeMessage(C5360Message message) throws IOException {
        int fragmentLimit = this.recordLayer.getSendLimit() - 12;
        if (fragmentLimit < 1) {
            throw new TlsFatalAlert(80);
        }
        int length = message.getBody().length;
        int fragment_offset = 0;
        do {
            int fragment_length = Math.min(length - fragment_offset, fragmentLimit);
            writeHandshakeFragment(message, fragment_offset, fragment_length);
            fragment_offset += fragment_length;
        } while (fragment_offset < length);
    }

    private void writeHandshakeFragment(C5360Message message, int fragment_offset, int fragment_length) throws IOException {
        RecordLayerBuffer fragment = new RecordLayerBuffer(fragment_length + 12);
        TlsUtils.writeUint8(message.getType(), (OutputStream) fragment);
        TlsUtils.writeUint24(message.getBody().length, fragment);
        TlsUtils.writeUint16(message.getSeq(), fragment);
        TlsUtils.writeUint24(fragment_offset, fragment);
        TlsUtils.writeUint24(fragment_length, fragment);
        fragment.write(message.getBody(), fragment_offset, fragment_length);
        fragment.sendToRecordLayer(this.recordLayer);
    }

    /* access modifiers changed from: private */
    public static boolean checkAll(Hashtable inboundFlight) {
        Enumeration e = inboundFlight.elements();
        while (e.hasMoreElements()) {
            if (((DTLSReassembler) e.nextElement()).getBodyIfComplete() == null) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: private */
    public static void resetAll(Hashtable inboundFlight) {
        Enumeration e = inboundFlight.elements();
        while (e.hasMoreElements()) {
            ((DTLSReassembler) e.nextElement()).reset();
        }
    }
}
