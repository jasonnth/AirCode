package com.microsoft.thrifty.protocol;

import com.microsoft.thrifty.transport.Transport;
import com.microsoft.thrifty.util.UnsafeByteArrayOutputStream;
import java.io.IOException;
import java.net.ProtocolException;
import java.nio.charset.Charset;
import java.util.ArrayDeque;
import java.util.Deque;
import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import okio.ByteString;
import p005cn.jpush.android.JPushConstants;

public class SimpleJsonProtocol extends Protocol {
    /* access modifiers changed from: private */
    public static final byte[] COLON = {58};
    /* access modifiers changed from: private */
    public static final byte[] COMMA = {ISO7816.INS_UNBLOCK_CHV};
    private static final byte[] COMMA_SPACE = {ISO7816.INS_UNBLOCK_CHV, ISO7816.INS_VERIFY};
    private static final byte[][] ESCAPES = new byte[128][];
    private static final byte[] FALSE = {102, 97, 108, 115, 101};
    private static final byte[] LBRACE = {123};
    private static final byte[] LBRACKET = {91};
    private static final byte[] RBRACE = {125};
    private static final byte[] RBRACKET = {93};
    private static final byte[] TRUE = {116, 114, 117, 101};
    private static final Charset UTF8 = Charset.forName(JPushConstants.ENCODING_UTF_8);
    private BinaryOutputMode binaryOutputMode = BinaryOutputMode.HEX;
    private final WriteContext defaultWriteContext = new WriteContext() {
        /* access modifiers changed from: 0000 */
        public void beforeWrite() throws IOException {
        }
    };
    private Deque<WriteContext> writeStack = new ArrayDeque();

    public enum BinaryOutputMode {
        HEX,
        BASE_64,
        UNICODE
    }

    private class ListWriteContext extends WriteContext {
        private boolean hasWritten;

        private ListWriteContext() {
            super();
            this.hasWritten = false;
        }

        /* access modifiers changed from: 0000 */
        public void beforeWrite() throws IOException {
            if (this.hasWritten) {
                SimpleJsonProtocol.this.transport.write(SimpleJsonProtocol.COMMA);
            } else {
                this.hasWritten = true;
            }
        }
    }

    private class MapWriteContext extends WriteContext {
        private boolean hasWritten;
        private boolean mode;

        private MapWriteContext() {
            super();
            this.hasWritten = false;
            this.mode = false;
        }

        /* access modifiers changed from: 0000 */
        public void beforeWrite() throws IOException {
            boolean z = true;
            if (!this.hasWritten) {
                this.hasWritten = true;
            } else if (!this.mode) {
                SimpleJsonProtocol.this.transport.write(SimpleJsonProtocol.COMMA);
            } else {
                SimpleJsonProtocol.this.transport.write(SimpleJsonProtocol.COLON);
            }
            if (this.mode) {
                z = false;
            }
            this.mode = z;
        }

        /* access modifiers changed from: 0000 */
        public void onPop() throws IOException {
            if (this.mode) {
                throw new ProtocolException("Incomplete JSON map, expected a value");
            }
        }
    }

    private class WriteContext {
        private WriteContext() {
        }

        /* access modifiers changed from: 0000 */
        public void beforeWrite() throws IOException {
        }

        /* access modifiers changed from: 0000 */
        public void onPop() throws IOException {
        }
    }

    static {
        for (int i = 0; i < 32; i++) {
            ESCAPES[i] = String.format("\\u%04x", new Object[]{Integer.valueOf(i)}).getBytes(UTF8);
        }
        ESCAPES[92] = new byte[]{92, 92};
        ESCAPES[34] = new byte[]{92, ISO7816.INS_MSE};
        ESCAPES[8] = new byte[]{92, ISOFileInfo.FCP_BYTE};
        ESCAPES[12] = new byte[]{92, 102};
        ESCAPES[13] = new byte[]{92, 114};
        ESCAPES[10] = new byte[]{92, 110};
        ESCAPES[9] = new byte[]{92, 116};
    }

    public SimpleJsonProtocol(Transport transport) {
        super(transport);
    }

    public SimpleJsonProtocol withBinaryOutputMode(BinaryOutputMode mode) {
        this.binaryOutputMode = mode;
        return this;
    }

    public void writeMessageBegin(String name, byte typeId, int seqId) throws IOException {
        writeMapBegin(typeId, typeId, 0);
        writeString("name");
        writeString(name);
        writeString("value");
    }

    public void writeMessageEnd() throws IOException {
        writeMapEnd();
    }

    public void writeStructBegin(String structName) throws IOException {
        writeContext().beforeWrite();
        pushWriteContext(new MapWriteContext());
        this.transport.write(LBRACE);
        writeString("__thriftStruct");
        writeString(structName);
    }

    public void writeStructEnd() throws IOException {
        this.transport.write(RBRACE);
        popWriteContext();
    }

    public void writeFieldBegin(String fieldName, int fieldId, byte typeId) throws IOException {
        writeString(fieldName);
    }

    public void writeFieldEnd() throws IOException {
    }

    public void writeFieldStop() throws IOException {
    }

    public void writeMapBegin(byte keyTypeId, byte valueTypeId, int mapSize) throws IOException {
        writeContext().beforeWrite();
        pushWriteContext(new MapWriteContext());
        this.transport.write(LBRACE);
    }

    public void writeMapEnd() throws IOException {
        this.transport.write(RBRACE);
        popWriteContext();
    }

    public void writeListBegin(byte elementTypeId, int listSize) throws IOException {
        writeContext().beforeWrite();
        pushWriteContext(new ListWriteContext());
        this.transport.write(LBRACKET);
    }

    public void writeListEnd() throws IOException {
        this.transport.write(RBRACKET);
        popWriteContext();
    }

    public void writeSetBegin(byte elementTypeId, int setSize) throws IOException {
        writeContext().beforeWrite();
        pushWriteContext(new ListWriteContext());
        this.transport.write(LBRACKET);
    }

    public void writeSetEnd() throws IOException {
        this.transport.write(RBRACKET);
        popWriteContext();
    }

    public void writeBool(boolean b) throws IOException {
        writeContext().beforeWrite();
        this.transport.write(b ? TRUE : FALSE);
    }

    public void writeByte(byte b) throws IOException {
        writeContext().beforeWrite();
        this.transport.write(String.valueOf(b).getBytes(UTF8));
    }

    public void writeI16(short i16) throws IOException {
        writeContext().beforeWrite();
        this.transport.write(String.valueOf(i16).getBytes(UTF8));
    }

    public void writeI32(int i32) throws IOException {
        writeContext().beforeWrite();
        this.transport.write(String.valueOf(i32).getBytes(UTF8));
    }

    public void writeI64(long i64) throws IOException {
        writeContext().beforeWrite();
        this.transport.write(String.valueOf(i64).getBytes(UTF8));
    }

    public void writeDouble(double dub) throws IOException {
        writeContext().beforeWrite();
        this.transport.write(String.valueOf(dub).getBytes(UTF8));
    }

    public void writeString(String str) throws IOException {
        writeContext().beforeWrite();
        int len = str.length();
        UnsafeByteArrayOutputStream baos = new UnsafeByteArrayOutputStream(len);
        baos.write(34);
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (c < 128) {
                byte[] maybeEscape = ESCAPES[c];
                if (maybeEscape != null) {
                    baos.write(maybeEscape);
                } else {
                    baos.write(c);
                }
            } else {
                baos.write(c);
            }
        }
        baos.write(34);
        this.transport.write(baos.getBuffer(), 0, baos.size());
    }

    public void writeBinary(ByteString buf) throws IOException {
        String out;
        switch (this.binaryOutputMode) {
            case HEX:
                out = buf.hex();
                break;
            case BASE_64:
                out = buf.base64();
                break;
            case UNICODE:
                out = buf.utf8();
                break;
            default:
                throw new AssertionError("Unexpected BinaryOutputMode value: " + this.binaryOutputMode);
        }
        writeString(out);
    }

    private void pushWriteContext(WriteContext context) {
        this.writeStack.push(context);
    }

    private WriteContext writeContext() {
        WriteContext top = (WriteContext) this.writeStack.peek();
        if (top == null) {
            return this.defaultWriteContext;
        }
        return top;
    }

    private void popWriteContext() throws IOException {
        WriteContext context = (WriteContext) this.writeStack.pollFirst();
        if (context == null) {
            throw new ProtocolException("stack underflow");
        }
        context.onPop();
    }

    public MessageMetadata readMessageBegin() throws IOException {
        throw new UnsupportedOperationException();
    }

    public void readMessageEnd() throws IOException {
        throw new UnsupportedOperationException();
    }

    public StructMetadata readStructBegin() throws IOException {
        throw new UnsupportedOperationException();
    }

    public void readStructEnd() throws IOException {
        throw new UnsupportedOperationException();
    }

    public FieldMetadata readFieldBegin() throws IOException {
        throw new UnsupportedOperationException();
    }

    public void readFieldEnd() throws IOException {
        throw new UnsupportedOperationException();
    }

    public MapMetadata readMapBegin() throws IOException {
        throw new UnsupportedOperationException();
    }

    public void readMapEnd() throws IOException {
        throw new UnsupportedOperationException();
    }

    public ListMetadata readListBegin() throws IOException {
        throw new UnsupportedOperationException();
    }

    public void readListEnd() throws IOException {
        throw new UnsupportedOperationException();
    }

    public SetMetadata readSetBegin() throws IOException {
        throw new UnsupportedOperationException();
    }

    public void readSetEnd() throws IOException {
        throw new UnsupportedOperationException();
    }

    public boolean readBool() throws IOException {
        throw new UnsupportedOperationException();
    }

    public byte readByte() throws IOException {
        throw new UnsupportedOperationException();
    }

    public short readI16() throws IOException {
        throw new UnsupportedOperationException();
    }

    public int readI32() throws IOException {
        throw new UnsupportedOperationException();
    }

    public long readI64() throws IOException {
        throw new UnsupportedOperationException();
    }

    public double readDouble() throws IOException {
        throw new UnsupportedOperationException();
    }

    public String readString() throws IOException {
        throw new UnsupportedOperationException();
    }

    public ByteString readBinary() throws IOException {
        throw new UnsupportedOperationException();
    }
}
