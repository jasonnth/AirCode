package net.p318sf.scuba.tlv;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.spongycastle.asn1.eac.CertificateBody;

/* renamed from: net.sf.scuba.tlv.TLVInputStream */
public class TLVInputStream extends InputStream {
    private static final int MAX_BUFFER_LENGTH = 65535;
    private int bufferSize = 0;
    private DataInputStream inputStream;
    private TLVInputState markedState;
    private final InputStream originalInputStream;
    private TLVInputState state;

    public TLVInputStream(InputStream inputStream2) {
        try {
            if ((inputStream2 instanceof BufferedInputStream) || (inputStream2 instanceof ByteArrayInputStream)) {
                this.bufferSize = inputStream2.available();
            }
        } catch (IOException e) {
        }
        this.originalInputStream = inputStream2;
        this.inputStream = inputStream2 instanceof DataInputStream ? (DataInputStream) inputStream2 : new DataInputStream(inputStream2);
        this.state = new TLVInputState();
        this.markedState = null;
    }

    private long skipValue() throws IOException {
        if (!this.state.isAtStartOfTag() && !this.state.isAtStartOfLength()) {
            return skip((long) this.state.getValueBytesLeft());
        }
        return 0;
    }

    public int available() throws IOException {
        return this.inputStream.available();
    }

    public void close() throws IOException {
        this.inputStream.close();
    }

    public synchronized void mark(int i) {
        this.inputStream.mark(i);
        this.markedState = (TLVInputState) this.state.clone();
    }

    public boolean markSupported() {
        return this.inputStream.markSupported();
    }

    public int read() throws IOException {
        int read = this.inputStream.read();
        if (read < 0) {
            return -1;
        }
        this.state.updateValueBytesProcessed(1);
        return read;
    }

    public int readLength() throws IOException {
        int i = 0;
        try {
            if (!this.state.isAtStartOfLength()) {
                throw new IllegalStateException("Not at start of length");
            }
            int readUnsignedByte = this.inputStream.readUnsignedByte();
            int i2 = 1;
            if ((readUnsignedByte & 128) != 0) {
                int i3 = readUnsignedByte & CertificateBody.profileType;
                readUnsignedByte = 0;
                while (i < i3) {
                    int i4 = i2 + 1;
                    i++;
                    readUnsignedByte = (readUnsignedByte << 8) | this.inputStream.readUnsignedByte();
                    i2 = i4;
                }
            }
            this.state.setLengthProcessed(readUnsignedByte, i2);
            return readUnsignedByte;
        } catch (IOException e) {
            throw e;
        }
    }

    public int readTag() throws IOException {
        int i;
        if (this.state.isAtStartOfTag() || this.state.isProcessingValue()) {
            try {
                int readUnsignedByte = this.inputStream.readUnsignedByte();
                int i2 = 1;
                while (true) {
                    if (readUnsignedByte != 0 && readUnsignedByte != 255) {
                        break;
                    }
                    readUnsignedByte = this.inputStream.readUnsignedByte();
                    i2 = i + 1;
                }
                switch (readUnsignedByte & 31) {
                    case 31:
                        i++;
                        int i3 = readUnsignedByte;
                        int readUnsignedByte2 = this.inputStream.readUnsignedByte();
                        while ((readUnsignedByte2 & 128) == 128) {
                            i3 = (i3 << 8) | (readUnsignedByte2 & CertificateBody.profileType);
                            readUnsignedByte2 = this.inputStream.readUnsignedByte();
                            i++;
                        }
                        readUnsignedByte = (readUnsignedByte2 & CertificateBody.profileType) | (i3 << 8);
                        break;
                }
                this.state.setTagProcessed(readUnsignedByte, i);
                return readUnsignedByte;
            } catch (IOException e) {
                throw e;
            }
        } else {
            throw new IllegalStateException("Not at start of tag");
        }
    }

    public byte[] readValue() throws IOException {
        try {
            if (!this.state.isProcessingValue()) {
                throw new IllegalStateException("Not yet processing value!");
            }
            int length = this.state.getLength();
            byte[] bArr = new byte[length];
            this.inputStream.readFully(bArr);
            this.state.updateValueBytesProcessed(length);
            return bArr;
        } catch (IOException e) {
            throw e;
        }
    }

    public synchronized void reset() throws IOException {
        if (!markSupported()) {
            throw new IOException("mark/reset not supported");
        }
        this.inputStream.reset();
        this.state = this.markedState;
        this.markedState = null;
    }

    public long skip(long j) throws IOException {
        if (j <= 0) {
            return 0;
        }
        long skip = this.inputStream.skip(j);
        this.state.updateValueBytesProcessed((int) skip);
        return skip;
    }

    public void skipToTag(int i) throws IOException {
        while (true) {
            if (!this.state.isAtStartOfTag()) {
                if (this.state.isAtStartOfLength()) {
                    readLength();
                    if (TLVUtil.isPrimitive(this.state.getTag())) {
                        skipValue();
                    }
                } else if (TLVUtil.isPrimitive(this.state.getTag())) {
                    skipValue();
                }
            }
            int readTag = readTag();
            if (readTag != i) {
                if (TLVUtil.isPrimitive(readTag) && ((int) skipValue()) < readLength()) {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public String toString() {
        return this.state.toString();
    }
}
