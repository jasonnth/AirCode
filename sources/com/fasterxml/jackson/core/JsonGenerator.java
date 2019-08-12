package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.p307io.CharacterEscapes;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public abstract class JsonGenerator implements Closeable, Flushable {
    protected PrettyPrinter _cfgPrettyPrinter;

    public enum Feature {
        AUTO_CLOSE_TARGET(true),
        AUTO_CLOSE_JSON_CONTENT(true),
        FLUSH_PASSED_TO_STREAM(true),
        QUOTE_FIELD_NAMES(true),
        QUOTE_NON_NUMERIC_NUMBERS(true),
        WRITE_NUMBERS_AS_STRINGS(false),
        WRITE_BIGDECIMAL_AS_PLAIN(false),
        ESCAPE_NON_ASCII(false),
        STRICT_DUPLICATE_DETECTION(false),
        IGNORE_UNKNOWN(false);
        
        private final boolean _defaultState;
        private final int _mask;

        public static int collectDefaults() {
            Feature[] arr$;
            int flags = 0;
            for (Feature f : values()) {
                if (f.enabledByDefault()) {
                    flags |= f.getMask();
                }
            }
            return flags;
        }

        private Feature(boolean defaultState) {
            this._defaultState = defaultState;
            this._mask = 1 << ordinal();
        }

        public boolean enabledByDefault() {
            return this._defaultState;
        }

        public boolean enabledIn(int flags) {
            return (this._mask & flags) != 0;
        }

        public int getMask() {
            return this._mask;
        }
    }

    public abstract void close() throws IOException;

    public abstract JsonGenerator disable(Feature feature);

    public abstract void flush() throws IOException;

    public abstract int getFeatureMask();

    public abstract JsonStreamContext getOutputContext();

    @Deprecated
    public abstract JsonGenerator setFeatureMask(int i);

    public abstract int writeBinary(Base64Variant base64Variant, InputStream inputStream, int i) throws IOException;

    public abstract void writeBinary(Base64Variant base64Variant, byte[] bArr, int i, int i2) throws IOException;

    public abstract void writeBoolean(boolean z) throws IOException;

    public abstract void writeEndArray() throws IOException;

    public abstract void writeEndObject() throws IOException;

    public abstract void writeFieldName(SerializableString serializableString) throws IOException;

    public abstract void writeFieldName(String str) throws IOException;

    public abstract void writeNull() throws IOException;

    public abstract void writeNumber(double d) throws IOException;

    public abstract void writeNumber(float f) throws IOException;

    public abstract void writeNumber(int i) throws IOException;

    public abstract void writeNumber(long j) throws IOException;

    public abstract void writeNumber(String str) throws IOException;

    public abstract void writeNumber(BigDecimal bigDecimal) throws IOException;

    public abstract void writeNumber(BigInteger bigInteger) throws IOException;

    public abstract void writeObject(Object obj) throws IOException;

    public abstract void writeRaw(char c) throws IOException;

    public abstract void writeRaw(String str) throws IOException;

    public abstract void writeRaw(char[] cArr, int i, int i2) throws IOException;

    public abstract void writeRawValue(String str) throws IOException;

    public abstract void writeStartArray() throws IOException;

    public abstract void writeStartObject() throws IOException;

    public abstract void writeString(SerializableString serializableString) throws IOException;

    public abstract void writeString(String str) throws IOException;

    public abstract void writeString(char[] cArr, int i, int i2) throws IOException;

    protected JsonGenerator() {
    }

    public JsonGenerator overrideStdFeatures(int values, int mask) {
        return setFeatureMask(((mask ^ -1) & getFeatureMask()) | (values & mask));
    }

    public JsonGenerator overrideFormatFeatures(int values, int mask) {
        throw new IllegalArgumentException("No FormatFeatures defined for generator of type " + getClass().getName());
    }

    public void setSchema(FormatSchema schema) {
        throw new UnsupportedOperationException("Generator of type " + getClass().getName() + " does not support schema of type '" + schema.getSchemaType() + "'");
    }

    public JsonGenerator setPrettyPrinter(PrettyPrinter pp) {
        this._cfgPrettyPrinter = pp;
        return this;
    }

    public PrettyPrinter getPrettyPrinter() {
        return this._cfgPrettyPrinter;
    }

    public JsonGenerator setHighestNonEscapedChar(int charCode) {
        return this;
    }

    public JsonGenerator setCharacterEscapes(CharacterEscapes esc) {
        return this;
    }

    public JsonGenerator setRootValueSeparator(SerializableString sep) {
        throw new UnsupportedOperationException();
    }

    public void setCurrentValue(Object v) {
        JsonStreamContext ctxt = getOutputContext();
        if (ctxt != null) {
            ctxt.setCurrentValue(v);
        }
    }

    public boolean canWriteObjectId() {
        return false;
    }

    public boolean canWriteTypeId() {
        return false;
    }

    public boolean canWriteBinaryNatively() {
        return false;
    }

    public boolean canOmitFields() {
        return true;
    }

    public void writeStartArray(int size) throws IOException {
        writeStartArray();
    }

    public void writeStartObject(Object forValue) throws IOException {
        writeStartObject();
        setCurrentValue(forValue);
    }

    public void writeArray(int[] array, int offset, int length) throws IOException {
        if (array == null) {
            throw new IllegalArgumentException("null array");
        }
        _verifyOffsets(array.length, offset, length);
        writeStartArray();
        int end = offset + length;
        for (int i = offset; i < end; i++) {
            writeNumber(array[i]);
        }
        writeEndArray();
    }

    public void writeArray(long[] array, int offset, int length) throws IOException {
        if (array == null) {
            throw new IllegalArgumentException("null array");
        }
        _verifyOffsets(array.length, offset, length);
        writeStartArray();
        int end = offset + length;
        for (int i = offset; i < end; i++) {
            writeNumber(array[i]);
        }
        writeEndArray();
    }

    public void writeArray(double[] array, int offset, int length) throws IOException {
        if (array == null) {
            throw new IllegalArgumentException("null array");
        }
        _verifyOffsets(array.length, offset, length);
        writeStartArray();
        int end = offset + length;
        for (int i = offset; i < end; i++) {
            writeNumber(array[i]);
        }
        writeEndArray();
    }

    public void writeRaw(SerializableString raw) throws IOException {
        writeRaw(raw.getValue());
    }

    public void writeRawValue(SerializableString raw) throws IOException {
        writeRawValue(raw.getValue());
    }

    public void writeBinary(byte[] data, int offset, int len) throws IOException {
        writeBinary(Base64Variants.getDefaultVariant(), data, offset, len);
    }

    public void writeBinary(byte[] data) throws IOException {
        writeBinary(Base64Variants.getDefaultVariant(), data, 0, data.length);
    }

    public int writeBinary(InputStream data, int dataLength) throws IOException {
        return writeBinary(Base64Variants.getDefaultVariant(), data, dataLength);
    }

    public void writeNumber(short v) throws IOException {
        writeNumber((int) v);
    }

    public void writeEmbeddedObject(Object object) throws IOException {
        if (object == null) {
            writeNull();
        } else if (object instanceof byte[]) {
            writeBinary((byte[]) object);
        } else {
            throw new JsonGenerationException("No native support for writing embedded objects of type " + object.getClass().getName(), this);
        }
    }

    public void writeObjectId(Object id) throws IOException {
        throw new JsonGenerationException("No native support for writing Object Ids", this);
    }

    public void writeObjectRef(Object id) throws IOException {
        throw new JsonGenerationException("No native support for writing Object Ids", this);
    }

    public void writeTypeId(Object id) throws IOException {
        throw new JsonGenerationException("No native support for writing Type Ids", this);
    }

    public void writeStringField(String fieldName, String value) throws IOException {
        writeFieldName(fieldName);
        writeString(value);
    }

    public final void writeArrayFieldStart(String fieldName) throws IOException {
        writeFieldName(fieldName);
        writeStartArray();
    }

    public final void writeObjectFieldStart(String fieldName) throws IOException {
        writeFieldName(fieldName);
        writeStartObject();
    }

    public void writeOmittedField(String fieldName) throws IOException {
    }

    public void copyCurrentEvent(JsonParser p) throws IOException {
        JsonToken t = p.currentToken();
        if (t == null) {
            _reportError("No current event to copy");
        }
        switch (t.mo34327id()) {
            case -1:
                _reportError("No current event to copy");
                return;
            case 1:
                writeStartObject();
                return;
            case 2:
                writeEndObject();
                return;
            case 3:
                writeStartArray();
                return;
            case 4:
                writeEndArray();
                return;
            case 5:
                writeFieldName(p.getCurrentName());
                return;
            case 6:
                if (p.hasTextCharacters()) {
                    writeString(p.getTextCharacters(), p.getTextOffset(), p.getTextLength());
                    return;
                } else {
                    writeString(p.getText());
                    return;
                }
            case 7:
                NumberType n = p.getNumberType();
                if (n == NumberType.INT) {
                    writeNumber(p.getIntValue());
                    return;
                } else if (n == NumberType.BIG_INTEGER) {
                    writeNumber(p.getBigIntegerValue());
                    return;
                } else {
                    writeNumber(p.getLongValue());
                    return;
                }
            case 8:
                NumberType n2 = p.getNumberType();
                if (n2 == NumberType.BIG_DECIMAL) {
                    writeNumber(p.getDecimalValue());
                    return;
                } else if (n2 == NumberType.FLOAT) {
                    writeNumber(p.getFloatValue());
                    return;
                } else {
                    writeNumber(p.getDoubleValue());
                    return;
                }
            case 9:
                writeBoolean(true);
                return;
            case 10:
                writeBoolean(false);
                return;
            case 11:
                writeNull();
                return;
            case 12:
                writeObject(p.getEmbeddedObject());
                return;
            default:
                _throwInternal();
                return;
        }
    }

    public void copyCurrentStructure(JsonParser p) throws IOException {
        JsonToken t = p.currentToken();
        if (t == null) {
            _reportError("No current event to copy");
        }
        int id = t.mo34327id();
        if (id == 5) {
            writeFieldName(p.getCurrentName());
            id = p.nextToken().mo34327id();
        }
        switch (id) {
            case 1:
                writeStartObject();
                while (p.nextToken() != JsonToken.END_OBJECT) {
                    copyCurrentStructure(p);
                }
                writeEndObject();
                return;
            case 3:
                writeStartArray();
                while (p.nextToken() != JsonToken.END_ARRAY) {
                    copyCurrentStructure(p);
                }
                writeEndArray();
                return;
            default:
                copyCurrentEvent(p);
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void _reportError(String msg) throws JsonGenerationException {
        throw new JsonGenerationException(msg, this);
    }

    /* access modifiers changed from: protected */
    public final void _throwInternal() {
        VersionUtil.throwInternal();
    }

    /* access modifiers changed from: protected */
    public void _reportUnsupportedOperation() {
        throw new UnsupportedOperationException("Operation not supported by generator of type " + getClass().getName());
    }

    /* access modifiers changed from: protected */
    public final void _verifyOffsets(int arrayLength, int offset, int length) {
        if (offset < 0 || offset + length > arrayLength) {
            throw new IllegalArgumentException(String.format("invalid argument(s) (offset=%d, length=%d) for input array of %d element", new Object[]{Integer.valueOf(offset), Integer.valueOf(length), Integer.valueOf(arrayLength)}));
        }
    }

    /* access modifiers changed from: protected */
    public void _writeSimpleObject(Object value) throws IOException {
        if (value == null) {
            writeNull();
        } else if (value instanceof String) {
            writeString((String) value);
        } else {
            if (value instanceof Number) {
                Number n = (Number) value;
                if (n instanceof Integer) {
                    writeNumber(n.intValue());
                    return;
                } else if (n instanceof Long) {
                    writeNumber(n.longValue());
                    return;
                } else if (n instanceof Double) {
                    writeNumber(n.doubleValue());
                    return;
                } else if (n instanceof Float) {
                    writeNumber(n.floatValue());
                    return;
                } else if (n instanceof Short) {
                    writeNumber(n.shortValue());
                    return;
                } else if (n instanceof Byte) {
                    writeNumber((short) n.byteValue());
                    return;
                } else if (n instanceof BigInteger) {
                    writeNumber((BigInteger) n);
                    return;
                } else if (n instanceof BigDecimal) {
                    writeNumber((BigDecimal) n);
                    return;
                } else if (n instanceof AtomicInteger) {
                    writeNumber(((AtomicInteger) n).get());
                    return;
                } else if (n instanceof AtomicLong) {
                    writeNumber(((AtomicLong) n).get());
                    return;
                }
            } else if (value instanceof byte[]) {
                writeBinary((byte[]) value);
                return;
            } else if (value instanceof Boolean) {
                writeBoolean(((Boolean) value).booleanValue());
                return;
            } else if (value instanceof AtomicBoolean) {
                writeBoolean(((AtomicBoolean) value).get());
                return;
            }
            throw new IllegalStateException("No ObjectCodec defined for the generator, can only serialize simple wrapper types (type passed " + value.getClass().getName() + ")");
        }
    }
}
