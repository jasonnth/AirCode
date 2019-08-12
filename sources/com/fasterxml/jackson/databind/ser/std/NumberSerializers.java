package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class NumberSerializers {

    @JacksonStdImpl
    public static final class DoubleSerializer extends Base<Object> {
        public /* bridge */ /* synthetic */ void acceptJsonFormatVisitor(JsonFormatVisitorWrapper x0, JavaType x1) throws JsonMappingException {
            super.acceptJsonFormatVisitor(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider x0, BeanProperty x1) throws JsonMappingException {
            return super.createContextual(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonNode getSchema(SerializerProvider x0, Type x1) {
            return super.getSchema(x0, x1);
        }

        public DoubleSerializer(Class<?> cls) {
            super(cls, NumberType.DOUBLE, "number");
        }

        public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeNumber(((Double) value).doubleValue());
        }

        public void serializeWithType(Object value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
            serialize(value, gen, provider);
        }
    }

    @JacksonStdImpl
    public static final class FloatSerializer extends Base<Object> {
        static final FloatSerializer instance = new FloatSerializer();

        public /* bridge */ /* synthetic */ void acceptJsonFormatVisitor(JsonFormatVisitorWrapper x0, JavaType x1) throws JsonMappingException {
            super.acceptJsonFormatVisitor(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider x0, BeanProperty x1) throws JsonMappingException {
            return super.createContextual(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonNode getSchema(SerializerProvider x0, Type x1) {
            return super.getSchema(x0, x1);
        }

        public FloatSerializer() {
            super(Float.class, NumberType.FLOAT, "number");
        }

        public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeNumber(((Float) value).floatValue());
        }
    }

    @JacksonStdImpl
    public static final class IntLikeSerializer extends Base<Object> {
        static final IntLikeSerializer instance = new IntLikeSerializer();

        public /* bridge */ /* synthetic */ void acceptJsonFormatVisitor(JsonFormatVisitorWrapper x0, JavaType x1) throws JsonMappingException {
            super.acceptJsonFormatVisitor(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider x0, BeanProperty x1) throws JsonMappingException {
            return super.createContextual(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonNode getSchema(SerializerProvider x0, Type x1) {
            return super.getSchema(x0, x1);
        }

        public IntLikeSerializer() {
            super(Number.class, NumberType.INT, "integer");
        }

        public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeNumber(((Number) value).intValue());
        }
    }

    @JacksonStdImpl
    public static final class IntegerSerializer extends Base<Object> {
        public /* bridge */ /* synthetic */ void acceptJsonFormatVisitor(JsonFormatVisitorWrapper x0, JavaType x1) throws JsonMappingException {
            super.acceptJsonFormatVisitor(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider x0, BeanProperty x1) throws JsonMappingException {
            return super.createContextual(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonNode getSchema(SerializerProvider x0, Type x1) {
            return super.getSchema(x0, x1);
        }

        public IntegerSerializer(Class<?> type) {
            super(type, NumberType.INT, "integer");
        }

        public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeNumber(((Integer) value).intValue());
        }

        public void serializeWithType(Object value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
            serialize(value, gen, provider);
        }
    }

    @JacksonStdImpl
    public static final class LongSerializer extends Base<Object> {
        public /* bridge */ /* synthetic */ void acceptJsonFormatVisitor(JsonFormatVisitorWrapper x0, JavaType x1) throws JsonMappingException {
            super.acceptJsonFormatVisitor(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider x0, BeanProperty x1) throws JsonMappingException {
            return super.createContextual(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonNode getSchema(SerializerProvider x0, Type x1) {
            return super.getSchema(x0, x1);
        }

        public LongSerializer(Class<?> cls) {
            super(cls, NumberType.LONG, "number");
        }

        public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeNumber(((Long) value).longValue());
        }
    }

    @JacksonStdImpl
    public static final class ShortSerializer extends Base<Object> {
        static final ShortSerializer instance = new ShortSerializer();

        public /* bridge */ /* synthetic */ void acceptJsonFormatVisitor(JsonFormatVisitorWrapper x0, JavaType x1) throws JsonMappingException {
            super.acceptJsonFormatVisitor(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonSerializer createContextual(SerializerProvider x0, BeanProperty x1) throws JsonMappingException {
            return super.createContextual(x0, x1);
        }

        public /* bridge */ /* synthetic */ JsonNode getSchema(SerializerProvider x0, Type x1) {
            return super.getSchema(x0, x1);
        }

        public ShortSerializer() {
            super(Short.class, NumberType.INT, "number");
        }

        public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
            gen.writeNumber(((Short) value).shortValue());
        }
    }

    protected static abstract class Base<T> extends StdScalarSerializer<T> implements ContextualSerializer {
        protected final boolean _isInt;
        protected final NumberType _numberType;
        protected final String _schemaType;

        protected Base(Class<?> cls, NumberType numberType, String schemaType) {
            boolean z = false;
            super(cls, false);
            this._numberType = numberType;
            this._schemaType = schemaType;
            if (numberType == NumberType.INT || numberType == NumberType.LONG || numberType == NumberType.BIG_INTEGER) {
                z = true;
            }
            this._isInt = z;
        }

        public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
            return createSchemaNode(this._schemaType, true);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
            if (this._isInt) {
                visitIntFormat(visitor, typeHint, this._numberType);
            } else {
                visitFloatFormat(visitor, typeHint, this._numberType);
            }
        }

        public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
            if (findFormatOverrides(prov, property, handledType()) == null) {
                return this;
            }
            switch (findFormatOverrides(prov, property, handledType()).getShape()) {
                case STRING:
                    return ToStringSerializer.instance;
                default:
                    return this;
            }
        }
    }

    public static void addAll(Map<String, JsonSerializer<?>> allDeserializers) {
        allDeserializers.put(Integer.class.getName(), new IntegerSerializer(Integer.class));
        allDeserializers.put(Integer.TYPE.getName(), new IntegerSerializer(Integer.TYPE));
        allDeserializers.put(Long.class.getName(), new LongSerializer(Long.class));
        allDeserializers.put(Long.TYPE.getName(), new LongSerializer(Long.TYPE));
        allDeserializers.put(Byte.class.getName(), IntLikeSerializer.instance);
        allDeserializers.put(Byte.TYPE.getName(), IntLikeSerializer.instance);
        allDeserializers.put(Short.class.getName(), ShortSerializer.instance);
        allDeserializers.put(Short.TYPE.getName(), ShortSerializer.instance);
        allDeserializers.put(Double.class.getName(), new DoubleSerializer(Double.class));
        allDeserializers.put(Double.TYPE.getName(), new DoubleSerializer(Double.TYPE));
        allDeserializers.put(Float.class.getName(), FloatSerializer.instance);
        allDeserializers.put(Float.TYPE.getName(), FloatSerializer.instance);
    }
}
