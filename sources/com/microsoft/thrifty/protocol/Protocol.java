package com.microsoft.thrifty.protocol;

import android.support.p000v4.app.NotificationCompat;
import com.microsoft.thrifty.transport.Transport;
import java.io.Closeable;
import java.io.IOException;
import okio.ByteString;

public abstract class Protocol implements Closeable {
    protected final Transport transport;

    public abstract ByteString readBinary() throws IOException;

    public abstract boolean readBool() throws IOException;

    public abstract byte readByte() throws IOException;

    public abstract double readDouble() throws IOException;

    public abstract FieldMetadata readFieldBegin() throws IOException;

    public abstract void readFieldEnd() throws IOException;

    public abstract short readI16() throws IOException;

    public abstract int readI32() throws IOException;

    public abstract long readI64() throws IOException;

    public abstract ListMetadata readListBegin() throws IOException;

    public abstract void readListEnd() throws IOException;

    public abstract MapMetadata readMapBegin() throws IOException;

    public abstract void readMapEnd() throws IOException;

    public abstract MessageMetadata readMessageBegin() throws IOException;

    public abstract void readMessageEnd() throws IOException;

    public abstract SetMetadata readSetBegin() throws IOException;

    public abstract void readSetEnd() throws IOException;

    public abstract String readString() throws IOException;

    public abstract StructMetadata readStructBegin() throws IOException;

    public abstract void readStructEnd() throws IOException;

    public abstract void writeBinary(ByteString byteString) throws IOException;

    public abstract void writeBool(boolean z) throws IOException;

    public abstract void writeByte(byte b) throws IOException;

    public abstract void writeDouble(double d) throws IOException;

    public abstract void writeFieldBegin(String str, int i, byte b) throws IOException;

    public abstract void writeFieldEnd() throws IOException;

    public abstract void writeFieldStop() throws IOException;

    public abstract void writeI16(short s) throws IOException;

    public abstract void writeI32(int i) throws IOException;

    public abstract void writeI64(long j) throws IOException;

    public abstract void writeListBegin(byte b, int i) throws IOException;

    public abstract void writeListEnd() throws IOException;

    public abstract void writeMapBegin(byte b, byte b2, int i) throws IOException;

    public abstract void writeMapEnd() throws IOException;

    public abstract void writeMessageBegin(String str, byte b, int i) throws IOException;

    public abstract void writeMessageEnd() throws IOException;

    public abstract void writeSetBegin(byte b, int i) throws IOException;

    public abstract void writeSetEnd() throws IOException;

    public abstract void writeString(String str) throws IOException;

    public abstract void writeStructBegin(String str) throws IOException;

    public abstract void writeStructEnd() throws IOException;

    protected Protocol(Transport transport2) {
        if (transport2 == null) {
            throw new NullPointerException(NotificationCompat.CATEGORY_TRANSPORT);
        }
        this.transport = transport2;
    }

    public void flush() throws IOException {
        this.transport.flush();
    }

    public void reset() {
    }

    public void close() throws IOException {
        this.transport.close();
    }
}
