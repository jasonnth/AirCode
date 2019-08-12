package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.lang.reflect.Type;

public class StdDelegatingSerializer extends StdSerializer<Object> implements JsonFormatVisitable, SchemaAware, ContextualSerializer, ResolvableSerializer {
    protected final Converter<Object, ?> _converter;
    protected final JsonSerializer<Object> _delegateSerializer;
    protected final JavaType _delegateType;

    public StdDelegatingSerializer(Converter<Object, ?> converter, JavaType delegateType, JsonSerializer<?> delegateSerializer) {
        super(delegateType);
        this._converter = converter;
        this._delegateType = delegateType;
        this._delegateSerializer = delegateSerializer;
    }

    /* access modifiers changed from: protected */
    public StdDelegatingSerializer withDelegate(Converter<Object, ?> converter, JavaType delegateType, JsonSerializer<?> delegateSerializer) {
        if (getClass() == StdDelegatingSerializer.class) {
            return new StdDelegatingSerializer(converter, delegateType, delegateSerializer);
        }
        throw new IllegalStateException("Sub-class " + getClass().getName() + " must override 'withDelegate'");
    }

    public void resolve(SerializerProvider provider) throws JsonMappingException {
        if (this._delegateSerializer != null && (this._delegateSerializer instanceof ResolvableSerializer)) {
            ((ResolvableSerializer) this._delegateSerializer).resolve(provider);
        }
    }

    /* Debug info: failed to restart local var, previous not found, register: 4 */
    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
        JsonSerializer<Object> jsonSerializer = this._delegateSerializer;
        JavaType delegateType = this._delegateType;
        if (jsonSerializer == null) {
            if (delegateType == null) {
                delegateType = this._converter.getOutputType(provider.getTypeFactory());
            }
            if (!delegateType.isJavaLangObject()) {
                jsonSerializer = provider.findValueSerializer(delegateType);
            }
        }
        if (jsonSerializer instanceof ContextualSerializer) {
            jsonSerializer = provider.handleSecondaryContextualization(jsonSerializer, property);
        }
        return (jsonSerializer == this._delegateSerializer && delegateType == this._delegateType) ? this : withDelegate(this._converter, delegateType, jsonSerializer);
    }

    public JsonSerializer<?> getDelegatee() {
        return this._delegateSerializer;
    }

    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        Object delegateValue = convertValue(value);
        if (delegateValue == null) {
            provider.defaultSerializeNull(gen);
            return;
        }
        JsonSerializer<Object> ser = this._delegateSerializer;
        if (ser == null) {
            ser = _findSerializer(delegateValue, provider);
        }
        ser.serialize(delegateValue, gen, provider);
    }

    public void serializeWithType(Object value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        Object delegateValue = convertValue(value);
        JsonSerializer<Object> ser = this._delegateSerializer;
        if (ser == null) {
            ser = _findSerializer(value, provider);
        }
        ser.serializeWithType(delegateValue, gen, provider, typeSer);
    }

    @Deprecated
    public boolean isEmpty(Object value) {
        return isEmpty(null, value);
    }

    public boolean isEmpty(SerializerProvider prov, Object value) {
        Object delegateValue = convertValue(value);
        if (this._delegateSerializer == null) {
            return value == null;
        }
        return this._delegateSerializer.isEmpty(prov, delegateValue);
    }

    public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
        if (this._delegateSerializer instanceof SchemaAware) {
            return ((SchemaAware) this._delegateSerializer).getSchema(provider, typeHint);
        }
        return super.getSchema(provider, typeHint);
    }

    public JsonNode getSchema(SerializerProvider provider, Type typeHint, boolean isOptional) throws JsonMappingException {
        if (this._delegateSerializer instanceof SchemaAware) {
            return ((SchemaAware) this._delegateSerializer).getSchema(provider, typeHint, isOptional);
        }
        return super.getSchema(provider, typeHint);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        if (this._delegateSerializer != null) {
            this._delegateSerializer.acceptJsonFormatVisitor(visitor, typeHint);
        }
    }

    /* access modifiers changed from: protected */
    public Object convertValue(Object value) {
        return this._converter.convert(value);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _findSerializer(Object value, SerializerProvider serializers) throws JsonMappingException {
        return serializers.findValueSerializer(value.getClass());
    }
}
