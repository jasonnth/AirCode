package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.util.EnumValues;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashSet;
import java.util.Set;

@JacksonStdImpl
public class EnumSerializer extends StdScalarSerializer<Enum<?>> implements ContextualSerializer {
    protected final Boolean _serializeAsIndex;
    protected final EnumValues _values;

    public EnumSerializer(EnumValues v, Boolean serializeAsIndex) {
        super(v.getEnumClass(), false);
        this._values = v;
        this._serializeAsIndex = serializeAsIndex;
    }

    public static EnumSerializer construct(Class<?> enumClass, SerializationConfig config, BeanDescription beanDesc, Value format) {
        return new EnumSerializer(EnumValues.constructFromName(config, enumClass), _isShapeWrittenUsingIndex(enumClass, format, true, null));
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
        if (property == null) {
            return this;
        }
        Value format = findFormatOverrides(serializers, property, handledType());
        if (format == null) {
            return this;
        }
        Boolean serializeAsIndex = _isShapeWrittenUsingIndex(property.getType().getRawClass(), format, false, this._serializeAsIndex);
        if (serializeAsIndex != this._serializeAsIndex) {
            return new EnumSerializer(this._values, serializeAsIndex);
        }
        return this;
    }

    public final void serialize(Enum<?> en, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (_serializeAsIndex(serializers)) {
            gen.writeNumber(en.ordinal());
        } else if (serializers.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
            gen.writeString(en.toString());
        } else {
            gen.writeString(this._values.serializedValueFor(en));
        }
    }

    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        if (_serializeAsIndex(provider)) {
            return createSchemaNode("integer", true);
        }
        ObjectNode objectNode = createSchemaNode("string", true);
        if (typeHint == null || !provider.constructType(typeHint).isEnumType()) {
            return objectNode;
        }
        ArrayNode enumNode = objectNode.putArray("enum");
        for (SerializableString value : this._values.values()) {
            enumNode.add(value.getValue());
        }
        return objectNode;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        SerializerProvider serializers = visitor.getProvider();
        if (_serializeAsIndex(serializers)) {
            visitIntFormat(visitor, typeHint, NumberType.INT);
            return;
        }
        JsonStringFormatVisitor stringVisitor = visitor.expectStringFormat(typeHint);
        if (stringVisitor != null) {
            Set<String> enums = new LinkedHashSet<>();
            if (serializers == null || !serializers.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
                for (SerializableString value : this._values.values()) {
                    enums.add(value.getValue());
                }
            } else {
                for (Enum<?> e : this._values.enums()) {
                    enums.add(e.toString());
                }
            }
            stringVisitor.enumTypes(enums);
        }
    }

    /* access modifiers changed from: protected */
    public final boolean _serializeAsIndex(SerializerProvider serializers) {
        if (this._serializeAsIndex != null) {
            return this._serializeAsIndex.booleanValue();
        }
        return serializers.isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX);
    }

    protected static Boolean _isShapeWrittenUsingIndex(Class<?> enumClass, Value format, boolean fromClass, Boolean defaultValue) {
        Shape shape = format == null ? null : format.getShape();
        if (shape == null || shape == Shape.ANY || shape == Shape.SCALAR) {
            return defaultValue;
        }
        if (shape == Shape.STRING || shape == Shape.NATURAL) {
            return Boolean.FALSE;
        }
        if (shape.isNumeric() || shape == Shape.ARRAY) {
            return Boolean.TRUE;
        }
        String str = "Unsupported serialization shape (%s) for Enum %s, not supported as %s annotation";
        Object[] objArr = new Object[3];
        objArr[0] = shape;
        objArr[1] = enumClass.getName();
        objArr[2] = fromClass ? "class" : "property";
        throw new IllegalArgumentException(String.format(str, objArr));
    }
}
