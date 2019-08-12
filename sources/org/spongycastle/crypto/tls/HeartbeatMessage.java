package org.spongycastle.crypto.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.p333io.Streams;

public class HeartbeatMessage {
    protected int paddingLength;
    protected byte[] payload;
    protected short type;

    static class PayloadBuffer extends ByteArrayOutputStream {
        PayloadBuffer() {
        }

        /* access modifiers changed from: 0000 */
        public byte[] toTruncatedByteArray(int payloadLength) {
            if (this.count < payloadLength + 16) {
                return null;
            }
            return Arrays.copyOf(this.buf, payloadLength);
        }
    }

    public HeartbeatMessage(short type2, byte[] payload2, int paddingLength2) {
        if (!HeartbeatMessageType.isValid(type2)) {
            throw new IllegalArgumentException("'type' is not a valid HeartbeatMessageType value");
        } else if (payload2 == null || payload2.length >= 65536) {
            throw new IllegalArgumentException("'payload' must have length < 2^16");
        } else if (paddingLength2 < 16) {
            throw new IllegalArgumentException("'paddingLength' must be at least 16");
        } else {
            this.type = type2;
            this.payload = payload2;
            this.paddingLength = paddingLength2;
        }
    }

    public void encode(TlsContext context, OutputStream output) throws IOException {
        TlsUtils.writeUint8(this.type, output);
        TlsUtils.checkUint16(this.payload.length);
        TlsUtils.writeUint16(this.payload.length, output);
        output.write(this.payload);
        byte[] padding = new byte[this.paddingLength];
        context.getNonceRandomGenerator().nextBytes(padding);
        output.write(padding);
    }

    public static HeartbeatMessage parse(InputStream input) throws IOException {
        short type2 = TlsUtils.readUint8(input);
        if (!HeartbeatMessageType.isValid(type2)) {
            throw new TlsFatalAlert(47);
        }
        int payload_length = TlsUtils.readUint16(input);
        PayloadBuffer buf = new PayloadBuffer();
        Streams.pipeAll(input, buf);
        byte[] payload2 = buf.toTruncatedByteArray(payload_length);
        if (payload2 == null) {
            return null;
        }
        return new HeartbeatMessage(type2, payload2, buf.size() - payload2.length);
    }
}
