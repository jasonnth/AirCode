package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ArrayBuilders.BooleanBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.ByteBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.DoubleBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.FloatBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.IntBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.LongBuilder;
import com.fasterxml.jackson.databind.util.ArrayBuilders.ShortBuilder;
import java.io.IOException;

public abstract class PrimitiveArrayDeserializers<T> extends StdDeserializer<T> implements ContextualDeserializer {
    protected final Boolean _unwrapSingle;

    @JacksonStdImpl
    static final class BooleanDeser extends PrimitiveArrayDeserializers<boolean[]> {
        public BooleanDeser() {
            super(boolean[].class);
        }

        protected BooleanDeser(BooleanDeser base, Boolean unwrapSingle) {
            super(base, unwrapSingle);
        }

        /* access modifiers changed from: protected */
        public PrimitiveArrayDeserializers<?> withResolved(Boolean unwrapSingle) {
            return new BooleanDeser(this, unwrapSingle);
        }

        public boolean[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (!p.isExpectedStartArrayToken()) {
                return (boolean[]) handleNonArray(p, ctxt);
            }
            BooleanBuilder builder = ctxt.getArrayBuilders().getBooleanBuilder();
            boolean[] chunk = (boolean[]) builder.resetAndStart();
            int ix = 0;
            while (p.nextToken() != JsonToken.END_ARRAY) {
                try {
                    boolean value = _parseBooleanPrimitive(p, ctxt);
                    if (ix >= chunk.length) {
                        chunk = (boolean[]) builder.appendCompletedChunk(chunk, ix);
                        ix = 0;
                    }
                    int ix2 = ix;
                    ix = ix2 + 1;
                    chunk[ix2] = value;
                } catch (Exception e) {
                    throw JsonMappingException.wrapWithPath((Throwable) e, (Object) chunk, builder.bufferedSize() + ix);
                }
            }
            return (boolean[]) builder.completeAndClearBuffer(chunk, ix);
        }

        /* access modifiers changed from: protected */
        public boolean[] handleSingleElementUnwrapped(JsonParser p, DeserializationContext ctxt) throws IOException {
            return new boolean[]{_parseBooleanPrimitive(p, ctxt)};
        }
    }

    @JacksonStdImpl
    static final class ByteDeser extends PrimitiveArrayDeserializers<byte[]> {
        public ByteDeser() {
            super(byte[].class);
        }

        protected ByteDeser(ByteDeser base, Boolean unwrapSingle) {
            super(base, unwrapSingle);
        }

        /* access modifiers changed from: protected */
        public PrimitiveArrayDeserializers<?> withResolved(Boolean unwrapSingle) {
            return new ByteDeser(this, unwrapSingle);
        }

        public byte[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            byte value;
            JsonToken t = p.getCurrentToken();
            if (t == JsonToken.VALUE_STRING) {
                return p.getBinaryValue(ctxt.getBase64Variant());
            }
            if (t == JsonToken.VALUE_EMBEDDED_OBJECT) {
                Object ob = p.getEmbeddedObject();
                if (ob == null) {
                    return null;
                }
                if (ob instanceof byte[]) {
                    return (byte[]) ob;
                }
            }
            if (!p.isExpectedStartArrayToken()) {
                return (byte[]) handleNonArray(p, ctxt);
            }
            ByteBuilder builder = ctxt.getArrayBuilders().getByteBuilder();
            byte[] chunk = (byte[]) builder.resetAndStart();
            int ix = 0;
            while (true) {
                try {
                    JsonToken t2 = p.nextToken();
                    if (t2 == JsonToken.END_ARRAY) {
                        return (byte[]) builder.completeAndClearBuffer(chunk, ix);
                    }
                    if (t2 == JsonToken.VALUE_NUMBER_INT || t2 == JsonToken.VALUE_NUMBER_FLOAT) {
                        value = p.getByteValue();
                    } else if (t2 == JsonToken.VALUE_NULL) {
                        value = 0;
                    } else {
                        value = ((Number) ctxt.handleUnexpectedToken(this._valueClass.getComponentType(), p)).byteValue();
                    }
                    if (ix >= chunk.length) {
                        chunk = (byte[]) builder.appendCompletedChunk(chunk, ix);
                        ix = 0;
                    }
                    int ix2 = ix;
                    ix = ix2 + 1;
                    chunk[ix2] = value;
                } catch (Exception e) {
                    throw JsonMappingException.wrapWithPath((Throwable) e, (Object) chunk, builder.bufferedSize() + ix);
                }
            }
        }

        /* access modifiers changed from: protected */
        public byte[] handleSingleElementUnwrapped(JsonParser p, DeserializationContext ctxt) throws IOException {
            byte value;
            JsonToken t = p.getCurrentToken();
            if (t == JsonToken.VALUE_NUMBER_INT || t == JsonToken.VALUE_NUMBER_FLOAT) {
                value = p.getByteValue();
            } else if (t == JsonToken.VALUE_NULL) {
                return null;
            } else {
                value = ((Number) ctxt.handleUnexpectedToken(this._valueClass.getComponentType(), p)).byteValue();
            }
            return new byte[]{value};
        }
    }

    @JacksonStdImpl
    static final class CharDeser extends PrimitiveArrayDeserializers<char[]> {
        public CharDeser() {
            super(char[].class);
        }

        /* access modifiers changed from: protected */
        public PrimitiveArrayDeserializers<?> withResolved(Boolean unwrapSingle) {
            return this;
        }

        public char[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            String str;
            JsonToken t = p.getCurrentToken();
            if (t == JsonToken.VALUE_STRING) {
                char[] buffer = p.getTextCharacters();
                int offset = p.getTextOffset();
                int len = p.getTextLength();
                char[] result = new char[len];
                System.arraycopy(buffer, offset, result, 0, len);
                return result;
            } else if (p.isExpectedStartArrayToken()) {
                StringBuilder sb = new StringBuilder(64);
                while (true) {
                    JsonToken t2 = p.nextToken();
                    if (t2 == JsonToken.END_ARRAY) {
                        return sb.toString().toCharArray();
                    }
                    if (t2 == JsonToken.VALUE_STRING) {
                        str = p.getText();
                    } else {
                        str = ((CharSequence) ctxt.handleUnexpectedToken(Character.TYPE, p)).toString();
                    }
                    if (str.length() != 1) {
                        ctxt.reportMappingException("Can not convert a JSON String of length %d into a char element of char array", Integer.valueOf(str.length()));
                    }
                    sb.append(str.charAt(0));
                }
            } else {
                if (t == JsonToken.VALUE_EMBEDDED_OBJECT) {
                    Object ob = p.getEmbeddedObject();
                    if (ob == null) {
                        return null;
                    }
                    if (ob instanceof char[]) {
                        return (char[]) ob;
                    }
                    if (ob instanceof String) {
                        return ((String) ob).toCharArray();
                    }
                    if (ob instanceof byte[]) {
                        return Base64Variants.getDefaultVariant().encode((byte[]) ob, false).toCharArray();
                    }
                }
                return (char[]) ctxt.handleUnexpectedToken(this._valueClass, p);
            }
        }

        /* access modifiers changed from: protected */
        public char[] handleSingleElementUnwrapped(JsonParser p, DeserializationContext ctxt) throws IOException {
            return (char[]) ctxt.handleUnexpectedToken(this._valueClass, p);
        }
    }

    @JacksonStdImpl
    static final class DoubleDeser extends PrimitiveArrayDeserializers<double[]> {
        public DoubleDeser() {
            super(double[].class);
        }

        protected DoubleDeser(DoubleDeser base, Boolean unwrapSingle) {
            super(base, unwrapSingle);
        }

        /* access modifiers changed from: protected */
        public PrimitiveArrayDeserializers<?> withResolved(Boolean unwrapSingle) {
            return new DoubleDeser(this, unwrapSingle);
        }

        public double[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            if (!p.isExpectedStartArrayToken()) {
                return (double[]) handleNonArray(p, ctxt);
            }
            DoubleBuilder builder = ctxt.getArrayBuilders().getDoubleBuilder();
            double[] chunk = (double[]) builder.resetAndStart();
            int ix = 0;
            while (p.nextToken() != JsonToken.END_ARRAY) {
                try {
                    double value = _parseDoublePrimitive(p, ctxt);
                    if (ix >= chunk.length) {
                        chunk = (double[]) builder.appendCompletedChunk(chunk, ix);
                        ix = 0;
                    }
                    int ix2 = ix;
                    ix = ix2 + 1;
                    chunk[ix2] = value;
                } catch (Exception e) {
                    throw JsonMappingException.wrapWithPath((Throwable) e, (Object) chunk, builder.bufferedSize() + ix);
                }
            }
            return (double[]) builder.completeAndClearBuffer(chunk, ix);
        }

        /* access modifiers changed from: protected */
        public double[] handleSingleElementUnwrapped(JsonParser p, DeserializationContext ctxt) throws IOException {
            return new double[]{_parseDoublePrimitive(p, ctxt)};
        }
    }

    @JacksonStdImpl
    static final class FloatDeser extends PrimitiveArrayDeserializers<float[]> {
        public FloatDeser() {
            super(float[].class);
        }

        protected FloatDeser(FloatDeser base, Boolean unwrapSingle) {
            super(base, unwrapSingle);
        }

        /* access modifiers changed from: protected */
        public PrimitiveArrayDeserializers<?> withResolved(Boolean unwrapSingle) {
            return new FloatDeser(this, unwrapSingle);
        }

        public float[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            if (!p.isExpectedStartArrayToken()) {
                return (float[]) handleNonArray(p, ctxt);
            }
            FloatBuilder builder = ctxt.getArrayBuilders().getFloatBuilder();
            float[] chunk = (float[]) builder.resetAndStart();
            int ix = 0;
            while (p.nextToken() != JsonToken.END_ARRAY) {
                try {
                    float value = _parseFloatPrimitive(p, ctxt);
                    if (ix >= chunk.length) {
                        chunk = (float[]) builder.appendCompletedChunk(chunk, ix);
                        ix = 0;
                    }
                    int ix2 = ix;
                    ix = ix2 + 1;
                    chunk[ix2] = value;
                } catch (Exception e) {
                    throw JsonMappingException.wrapWithPath((Throwable) e, (Object) chunk, builder.bufferedSize() + ix);
                }
            }
            return (float[]) builder.completeAndClearBuffer(chunk, ix);
        }

        /* access modifiers changed from: protected */
        public float[] handleSingleElementUnwrapped(JsonParser p, DeserializationContext ctxt) throws IOException {
            return new float[]{_parseFloatPrimitive(p, ctxt)};
        }
    }

    @JacksonStdImpl
    static final class IntDeser extends PrimitiveArrayDeserializers<int[]> {
        public static final IntDeser instance = new IntDeser();

        public IntDeser() {
            super(int[].class);
        }

        protected IntDeser(IntDeser base, Boolean unwrapSingle) {
            super(base, unwrapSingle);
        }

        /* access modifiers changed from: protected */
        public PrimitiveArrayDeserializers<?> withResolved(Boolean unwrapSingle) {
            return new IntDeser(this, unwrapSingle);
        }

        public int[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            if (!p.isExpectedStartArrayToken()) {
                return (int[]) handleNonArray(p, ctxt);
            }
            IntBuilder builder = ctxt.getArrayBuilders().getIntBuilder();
            int[] chunk = (int[]) builder.resetAndStart();
            int ix = 0;
            while (p.nextToken() != JsonToken.END_ARRAY) {
                try {
                    int value = _parseIntPrimitive(p, ctxt);
                    if (ix >= chunk.length) {
                        chunk = (int[]) builder.appendCompletedChunk(chunk, ix);
                        ix = 0;
                    }
                    int ix2 = ix;
                    ix = ix2 + 1;
                    chunk[ix2] = value;
                } catch (Exception e) {
                    throw JsonMappingException.wrapWithPath((Throwable) e, (Object) chunk, builder.bufferedSize() + ix);
                }
            }
            return (int[]) builder.completeAndClearBuffer(chunk, ix);
        }

        /* access modifiers changed from: protected */
        public int[] handleSingleElementUnwrapped(JsonParser p, DeserializationContext ctxt) throws IOException {
            return new int[]{_parseIntPrimitive(p, ctxt)};
        }
    }

    @JacksonStdImpl
    static final class LongDeser extends PrimitiveArrayDeserializers<long[]> {
        public static final LongDeser instance = new LongDeser();

        public LongDeser() {
            super(long[].class);
        }

        protected LongDeser(LongDeser base, Boolean unwrapSingle) {
            super(base, unwrapSingle);
        }

        /* access modifiers changed from: protected */
        public PrimitiveArrayDeserializers<?> withResolved(Boolean unwrapSingle) {
            return new LongDeser(this, unwrapSingle);
        }

        public long[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            if (!p.isExpectedStartArrayToken()) {
                return (long[]) handleNonArray(p, ctxt);
            }
            LongBuilder builder = ctxt.getArrayBuilders().getLongBuilder();
            long[] chunk = (long[]) builder.resetAndStart();
            int ix = 0;
            while (p.nextToken() != JsonToken.END_ARRAY) {
                try {
                    long value = _parseLongPrimitive(p, ctxt);
                    if (ix >= chunk.length) {
                        chunk = (long[]) builder.appendCompletedChunk(chunk, ix);
                        ix = 0;
                    }
                    int ix2 = ix;
                    ix = ix2 + 1;
                    chunk[ix2] = value;
                } catch (Exception e) {
                    throw JsonMappingException.wrapWithPath((Throwable) e, (Object) chunk, builder.bufferedSize() + ix);
                }
            }
            return (long[]) builder.completeAndClearBuffer(chunk, ix);
        }

        /* access modifiers changed from: protected */
        public long[] handleSingleElementUnwrapped(JsonParser p, DeserializationContext ctxt) throws IOException {
            return new long[]{_parseLongPrimitive(p, ctxt)};
        }
    }

    @JacksonStdImpl
    static final class ShortDeser extends PrimitiveArrayDeserializers<short[]> {
        public ShortDeser() {
            super(short[].class);
        }

        protected ShortDeser(ShortDeser base, Boolean unwrapSingle) {
            super(base, unwrapSingle);
        }

        /* access modifiers changed from: protected */
        public PrimitiveArrayDeserializers<?> withResolved(Boolean unwrapSingle) {
            return new ShortDeser(this, unwrapSingle);
        }

        public short[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            if (!p.isExpectedStartArrayToken()) {
                return (short[]) handleNonArray(p, ctxt);
            }
            ShortBuilder builder = ctxt.getArrayBuilders().getShortBuilder();
            short[] chunk = (short[]) builder.resetAndStart();
            int ix = 0;
            while (p.nextToken() != JsonToken.END_ARRAY) {
                try {
                    short value = _parseShortPrimitive(p, ctxt);
                    if (ix >= chunk.length) {
                        chunk = (short[]) builder.appendCompletedChunk(chunk, ix);
                        ix = 0;
                    }
                    int ix2 = ix;
                    ix = ix2 + 1;
                    chunk[ix2] = value;
                } catch (Exception e) {
                    throw JsonMappingException.wrapWithPath((Throwable) e, (Object) chunk, builder.bufferedSize() + ix);
                }
            }
            return (short[]) builder.completeAndClearBuffer(chunk, ix);
        }

        /* access modifiers changed from: protected */
        public short[] handleSingleElementUnwrapped(JsonParser p, DeserializationContext ctxt) throws IOException {
            return new short[]{_parseShortPrimitive(p, ctxt)};
        }
    }

    /* access modifiers changed from: protected */
    public abstract T handleSingleElementUnwrapped(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException;

    /* access modifiers changed from: protected */
    public abstract PrimitiveArrayDeserializers<?> withResolved(Boolean bool);

    protected PrimitiveArrayDeserializers(Class<T> cls) {
        super(cls);
        this._unwrapSingle = null;
    }

    protected PrimitiveArrayDeserializers(PrimitiveArrayDeserializers<?> base, Boolean unwrapSingle) {
        super(base._valueClass);
        this._unwrapSingle = unwrapSingle;
    }

    public static JsonDeserializer<?> forType(Class<?> rawType) {
        if (rawType == Integer.TYPE) {
            return IntDeser.instance;
        }
        if (rawType == Long.TYPE) {
            return LongDeser.instance;
        }
        if (rawType == Byte.TYPE) {
            return new ByteDeser();
        }
        if (rawType == Short.TYPE) {
            return new ShortDeser();
        }
        if (rawType == Float.TYPE) {
            return new FloatDeser();
        }
        if (rawType == Double.TYPE) {
            return new DoubleDeser();
        }
        if (rawType == Boolean.TYPE) {
            return new BooleanDeser();
        }
        if (rawType == Character.TYPE) {
            return new CharDeser();
        }
        throw new IllegalStateException();
    }

    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        Boolean unwrapSingle = findFormatFeature(ctxt, property, this._valueClass, Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        return unwrapSingle == this._unwrapSingle ? this : withResolved(unwrapSingle);
    }

    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(p, ctxt);
    }

    /* access modifiers changed from: protected */
    public T handleNonArray(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.hasToken(JsonToken.VALUE_STRING) && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && p.getText().length() == 0) {
            return null;
        }
        if (this._unwrapSingle == Boolean.TRUE || (this._unwrapSingle == null && ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))) {
            return handleSingleElementUnwrapped(p, ctxt);
        }
        return ctxt.handleUnexpectedToken(this._valueClass, p);
    }
}
