package com.microsoft.thrifty.protocol;

import com.microsoft.thrifty.transport.Transport;
import java.io.EOFException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import okio.ByteString;
import p005cn.jpush.android.JPushConstants;

public class BinaryProtocol extends Protocol {
    private static final StructMetadata NO_STRUCT = new StructMetadata("");
    private final byte[] buffer;
    private final long containerLengthLimit;
    private boolean strictRead;
    private boolean strictWrite;
    private final long stringLengthLimit;

    public BinaryProtocol(Transport transport) {
        this(transport, -1, -1);
    }

    public BinaryProtocol(Transport transport, int stringLengthLimit2, int containerLengthLimit2) {
        super(transport);
        this.buffer = new byte[8];
        this.stringLengthLimit = (long) stringLengthLimit2;
        this.containerLengthLimit = (long) containerLengthLimit2;
    }

    public void writeMessageBegin(String name, byte typeId, int seqId) throws IOException {
        if (this.strictWrite) {
            writeI32(-2147418112 | (typeId & 255));
            writeString(name);
            writeI32(seqId);
            return;
        }
        writeString(name);
        writeByte(typeId);
        writeI32(seqId);
    }

    public void writeMessageEnd() throws IOException {
    }

    public void writeStructBegin(String structName) throws IOException {
    }

    public void writeStructEnd() throws IOException {
    }

    public void writeFieldBegin(String fieldName, int fieldId, byte typeId) throws IOException {
        writeByte(typeId);
        writeI16((short) fieldId);
    }

    public void writeFieldEnd() throws IOException {
    }

    public void writeFieldStop() throws IOException {
        writeByte(0);
    }

    public void writeMapBegin(byte keyTypeId, byte valueTypeId, int mapSize) throws IOException {
        writeByte(keyTypeId);
        writeByte(valueTypeId);
        writeI32(mapSize);
    }

    public void writeMapEnd() throws IOException {
    }

    public void writeListBegin(byte elementTypeId, int listSize) throws IOException {
        writeByte(elementTypeId);
        writeI32(listSize);
    }

    public void writeListEnd() throws IOException {
    }

    public void writeSetBegin(byte elementTypeId, int setSize) throws IOException {
        writeByte(elementTypeId);
        writeI32(setSize);
    }

    public void writeSetEnd() throws IOException {
    }

    public void writeBool(boolean b) throws IOException {
        writeByte(b ? (byte) 1 : 0);
    }

    public void writeByte(byte b) throws IOException {
        this.buffer[0] = b;
        this.transport.write(this.buffer, 0, 1);
    }

    public void writeI16(short i16) throws IOException {
        this.buffer[0] = (byte) ((i16 >> 8) & 255);
        this.buffer[1] = (byte) (i16 & 255);
        this.transport.write(this.buffer, 0, 2);
    }

    public void writeI32(int i32) throws IOException {
        this.buffer[0] = (byte) ((i32 >> 24) & 255);
        this.buffer[1] = (byte) ((i32 >> 16) & 255);
        this.buffer[2] = (byte) ((i32 >> 8) & 255);
        this.buffer[3] = (byte) (i32 & 255);
        this.transport.write(this.buffer, 0, 4);
    }

    public void writeI64(long i64) throws IOException {
        this.buffer[0] = (byte) ((int) ((i64 >> 56) & 255));
        this.buffer[1] = (byte) ((int) ((i64 >> 48) & 255));
        this.buffer[2] = (byte) ((int) ((i64 >> 40) & 255));
        this.buffer[3] = (byte) ((int) ((i64 >> 32) & 255));
        this.buffer[4] = (byte) ((int) ((i64 >> 24) & 255));
        this.buffer[5] = (byte) ((int) ((i64 >> 16) & 255));
        this.buffer[6] = (byte) ((int) ((i64 >> 8) & 255));
        this.buffer[7] = (byte) ((int) (i64 & 255));
        this.transport.write(this.buffer, 0, 8);
    }

    public void writeDouble(double dub) throws IOException {
        writeI64(Double.doubleToLongBits(dub));
    }

    public void writeString(String str) throws IOException {
        try {
            byte[] bs = str.getBytes(JPushConstants.ENCODING_UTF_8);
            writeI32(bs.length);
            this.transport.write(bs);
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public void writeBinary(ByteString buf) throws IOException {
        writeI32(buf.size());
        this.transport.write(buf.toByteArray());
    }

    public MessageMetadata readMessageBegin() throws IOException {
        int size = readI32();
        if (size < 0) {
            if ((size & -65536) == -2147418112) {
                return new MessageMetadata(readString(), (byte) (size & 255), readI32());
            }
            throw new ProtocolException("Bad version in readMessageBegin");
        } else if (!this.strictRead) {
            return new MessageMetadata(readStringWithSize(size), readByte(), readI32());
        } else {
            throw new ProtocolException("Missing version in readMessageBegin");
        }
    }

    public void readMessageEnd() throws IOException {
    }

    public StructMetadata readStructBegin() throws IOException {
        return NO_STRUCT;
    }

    public void readStructEnd() throws IOException {
    }

    public FieldMetadata readFieldBegin() throws IOException {
        byte typeId = readByte();
        return new FieldMetadata("", typeId, typeId == 0 ? 0 : readI16());
    }

    public void readFieldEnd() throws IOException {
    }

    public MapMetadata readMapBegin() throws IOException {
        byte keyTypeId = readByte();
        byte valueTypeId = readByte();
        int size = readI32();
        if (this.containerLengthLimit == -1 || ((long) size) <= this.containerLengthLimit) {
            return new MapMetadata(keyTypeId, valueTypeId, size);
        }
        throw new ProtocolException("Container size limit exceeded");
    }

    public void readMapEnd() throws IOException {
    }

    public ListMetadata readListBegin() throws IOException {
        byte elementTypeId = readByte();
        int size = readI32();
        if (this.containerLengthLimit == -1 || ((long) size) <= this.containerLengthLimit) {
            return new ListMetadata(elementTypeId, size);
        }
        throw new ProtocolException("Container size limit exceeded");
    }

    public void readListEnd() throws IOException {
    }

    public SetMetadata readSetBegin() throws IOException {
        byte elementTypeId = readByte();
        int size = readI32();
        if (this.containerLengthLimit == -1 || ((long) size) <= this.containerLengthLimit) {
            return new SetMetadata(elementTypeId, size);
        }
        throw new ProtocolException("Container size limit exceeded");
    }

    public void readSetEnd() throws IOException {
    }

    public boolean readBool() throws IOException {
        return readByte() == 1;
    }

    public byte readByte() throws IOException {
        readFully(this.buffer, 1);
        return this.buffer[0];
    }

    public short readI16() throws IOException {
        readFully(this.buffer, 2);
        return (short) (((this.buffer[0] & 255) << 8) | (this.buffer[1] & 255));
    }

    public int readI32() throws IOException {
        readFully(this.buffer, 4);
        return ((this.buffer[0] & 255) << 24) | ((this.buffer[1] & 255) << 16) | ((this.buffer[2] & 255) << 8) | (this.buffer[3] & 255);
    }

    public long readI64() throws IOException {
        readFully(this.buffer, 8);
        return ((((long) this.buffer[0]) & 255) << 56) | ((((long) this.buffer[1]) & 255) << 48) | ((((long) this.buffer[2]) & 255) << 40) | ((((long) this.buffer[3]) & 255) << 32) | ((((long) this.buffer[4]) & 255) << 24) | ((((long) this.buffer[5]) & 255) << 16) | ((((long) this.buffer[6]) & 255) << 8) | (((long) this.buffer[7]) & 255);
    }

    public double readDouble() throws IOException {
        return Double.longBitsToDouble(readI64());
    }

    public String readString() throws IOException {
        int sizeInBytes = readI32();
        if (this.stringLengthLimit == -1 || ((long) sizeInBytes) <= this.stringLengthLimit) {
            return readStringWithSize(sizeInBytes);
        }
        throw new ProtocolException("String size limit exceeded");
    }

    public ByteString readBinary() throws IOException {
        int sizeInBytes = readI32();
        if (this.stringLengthLimit == -1 || ((long) sizeInBytes) <= this.stringLengthLimit) {
            byte[] data = new byte[sizeInBytes];
            readFully(data, data.length);
            return ByteString.m3949of(data);
        }
        throw new ProtocolException("Binary size limit exceeded");
    }

    private String readStringWithSize(int size) throws IOException {
        byte[] encoded = new byte[size];
        readFully(encoded, size);
        return new String(encoded, JPushConstants.ENCODING_UTF_8);
    }

    private void readFully(byte[] buffer2, int count) throws IOException {
        int toRead = count;
        int offset = 0;
        while (toRead > 0) {
            int read = this.transport.read(buffer2, offset, toRead);
            if (read == -1) {
                throw new EOFException("Expected " + count + " bytes; got " + offset);
            }
            toRead -= read;
            offset += read;
        }
    }
}
