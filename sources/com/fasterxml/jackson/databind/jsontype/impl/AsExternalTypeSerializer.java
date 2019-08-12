package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.C1092As;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

public class AsExternalTypeSerializer extends TypeSerializerBase {
    protected final String _typePropertyName;

    public AsExternalTypeSerializer(TypeIdResolver idRes, BeanProperty property, String propName) {
        super(idRes, property);
        this._typePropertyName = propName;
    }

    /* Debug info: failed to restart local var, previous not found, register: 3 */
    public AsExternalTypeSerializer forProperty(BeanProperty prop) {
        return this._property == prop ? this : new AsExternalTypeSerializer(this._idResolver, prop, this._typePropertyName);
    }

    public String getPropertyName() {
        return this._typePropertyName;
    }

    public C1092As getTypeInclusion() {
        return C1092As.EXTERNAL_PROPERTY;
    }

    public void writeTypePrefixForObject(Object value, JsonGenerator gen) throws IOException {
        _writeObjectPrefix(value, gen);
    }

    public void writeTypePrefixForArray(Object value, JsonGenerator gen) throws IOException {
        _writeArrayPrefix(value, gen);
    }

    public void writeTypePrefixForScalar(Object value, JsonGenerator gen) throws IOException {
        _writeScalarPrefix(value, gen);
    }

    public void writeTypePrefixForScalar(Object value, JsonGenerator gen, Class<?> cls) throws IOException {
        _writeScalarPrefix(value, gen);
    }

    public void writeTypeSuffixForObject(Object value, JsonGenerator gen) throws IOException {
        _writeObjectSuffix(value, gen, idFromValue(value));
    }

    public void writeTypeSuffixForArray(Object value, JsonGenerator gen) throws IOException {
        _writeArraySuffix(value, gen, idFromValue(value));
    }

    public void writeTypeSuffixForScalar(Object value, JsonGenerator gen) throws IOException {
        _writeScalarSuffix(value, gen, idFromValue(value));
    }

    public void writeCustomTypePrefixForObject(Object value, JsonGenerator gen, String typeId) throws IOException {
        _writeObjectPrefix(value, gen);
    }

    public void writeCustomTypePrefixForArray(Object value, JsonGenerator gen, String typeId) throws IOException {
        _writeArrayPrefix(value, gen);
    }

    public void writeCustomTypeSuffixForObject(Object value, JsonGenerator gen, String typeId) throws IOException {
        _writeObjectSuffix(value, gen, typeId);
    }

    public void writeCustomTypeSuffixForArray(Object value, JsonGenerator gen, String typeId) throws IOException {
        _writeArraySuffix(value, gen, typeId);
    }

    /* access modifiers changed from: protected */
    public final void _writeScalarPrefix(Object value, JsonGenerator gen) throws IOException {
    }

    /* access modifiers changed from: protected */
    public final void _writeObjectPrefix(Object value, JsonGenerator gen) throws IOException {
        gen.writeStartObject();
    }

    /* access modifiers changed from: protected */
    public final void _writeArrayPrefix(Object value, JsonGenerator gen) throws IOException {
        gen.writeStartArray();
    }

    /* access modifiers changed from: protected */
    public final void _writeScalarSuffix(Object value, JsonGenerator gen, String typeId) throws IOException {
        if (typeId != null) {
            gen.writeStringField(this._typePropertyName, typeId);
        }
    }

    /* access modifiers changed from: protected */
    public final void _writeObjectSuffix(Object value, JsonGenerator gen, String typeId) throws IOException {
        gen.writeEndObject();
        if (typeId != null) {
            gen.writeStringField(this._typePropertyName, typeId);
        }
    }

    /* access modifiers changed from: protected */
    public final void _writeArraySuffix(Object value, JsonGenerator gen, String typeId) throws IOException {
        gen.writeEndArray();
        if (typeId != null) {
            gen.writeStringField(this._typePropertyName, typeId);
        }
    }
}
