package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import java.lang.reflect.Type;
import java.util.Collection;

public abstract class StaticListSerializerBase<T extends Collection<?>> extends StdSerializer<T> implements ContextualSerializer {
    protected final JsonSerializer<String> _serializer;
    protected final Boolean _unwrapSingle;

    public abstract JsonSerializer<?> _withResolved(BeanProperty beanProperty, JsonSerializer<?> jsonSerializer, Boolean bool);

    /* access modifiers changed from: protected */
    public abstract void acceptContentVisitor(JsonArrayFormatVisitor jsonArrayFormatVisitor) throws JsonMappingException;

    /* access modifiers changed from: protected */
    public abstract JsonNode contentSchema();

    protected StaticListSerializerBase(Class<?> cls) {
        super(cls, false);
        this._serializer = null;
        this._unwrapSingle = null;
    }

    protected StaticListSerializerBase(StaticListSerializerBase<?> src, JsonSerializer<?> ser, Boolean unwrapSingle) {
        super((StdSerializer<?>) src);
        this._serializer = ser;
        this._unwrapSingle = unwrapSingle;
    }

    public JsonSerializer<?> createContextual(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
        JsonSerializer<?> ser;
        JsonSerializer<String> jsonSerializer = null;
        Boolean unwrapSingle = null;
        if (property != null) {
            AnnotationIntrospector intr = serializers.getAnnotationIntrospector();
            AnnotatedMember m = property.getMember();
            if (m != null) {
                Object serDef = intr.findContentSerializer(m);
                if (serDef != null) {
                    jsonSerializer = serializers.serializerInstance(m, serDef);
                }
            }
        }
        Value format = findFormatOverrides(serializers, property, handledType());
        if (format != null) {
            unwrapSingle = format.getFeature(Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
        }
        if (jsonSerializer == null) {
            jsonSerializer = this._serializer;
        }
        JsonSerializer<?> ser2 = findConvertingContentSerializer(serializers, property, jsonSerializer);
        if (ser2 == null) {
            ser = serializers.findValueSerializer(String.class, property);
        } else {
            ser = serializers.handleSecondaryContextualization(ser2, property);
        }
        if (isDefaultSerializer(ser)) {
            ser = null;
        }
        return (ser == this._serializer && unwrapSingle == this._unwrapSingle) ? this : _withResolved(property, ser, unwrapSingle);
    }

    @Deprecated
    public boolean isEmpty(T value) {
        return isEmpty((SerializerProvider) null, value);
    }

    public boolean isEmpty(SerializerProvider provider, T value) {
        return value == null || value.size() == 0;
    }

    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return createSchemaNode("array", true).set("items", contentSchema());
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        acceptContentVisitor(visitor.expectArrayFormat(typeHint));
    }
}
