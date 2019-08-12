package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.C1092As;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

public class AsArrayTypeSerializer extends TypeSerializerBase {
    public AsArrayTypeSerializer(TypeIdResolver idRes, BeanProperty property) {
        super(idRes, property);
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public AsArrayTypeSerializer forProperty(BeanProperty prop) {
        return this._property == prop ? this : new AsArrayTypeSerializer(this._idResolver, prop);
    }

    public C1092As getTypeInclusion() {
        return C1092As.WRAPPER_ARRAY;
    }

    public void writeTypePrefixForObject(Object value, JsonGenerator g) throws IOException {
        String typeId = idFromValue(value);
        if (!g.canWriteTypeId()) {
            g.writeStartArray();
            g.writeString(typeId);
        } else if (typeId != null) {
            g.writeTypeId(typeId);
        }
        g.writeStartObject();
    }

    public void writeTypePrefixForArray(Object value, JsonGenerator g) throws IOException {
        String typeId = idFromValue(value);
        if (!g.canWriteTypeId()) {
            g.writeStartArray();
            g.writeString(typeId);
        } else if (typeId != null) {
            g.writeTypeId(typeId);
        }
        g.writeStartArray();
    }

    public void writeTypePrefixForScalar(Object value, JsonGenerator g) throws IOException {
        String typeId = idFromValue(value);
        if (!g.canWriteTypeId()) {
            g.writeStartArray();
            g.writeString(typeId);
        } else if (typeId != null) {
            g.writeTypeId(typeId);
        }
    }

    public void writeTypePrefixForScalar(Object value, JsonGenerator g, Class<?> type) throws IOException {
        String typeId = idFromValueAndType(value, type);
        if (!g.canWriteTypeId()) {
            g.writeStartArray();
            g.writeString(typeId);
        } else if (typeId != null) {
            g.writeTypeId(typeId);
        }
    }

    public void writeTypeSuffixForObject(Object value, JsonGenerator g) throws IOException {
        g.writeEndObject();
        if (!g.canWriteTypeId()) {
            g.writeEndArray();
        }
    }

    public void writeTypeSuffixForArray(Object value, JsonGenerator g) throws IOException {
        g.writeEndArray();
        if (!g.canWriteTypeId()) {
            g.writeEndArray();
        }
    }

    public void writeTypeSuffixForScalar(Object value, JsonGenerator g) throws IOException {
        if (!g.canWriteTypeId()) {
            g.writeEndArray();
        }
    }

    public void writeCustomTypePrefixForObject(Object value, JsonGenerator g, String typeId) throws IOException {
        if (!g.canWriteTypeId()) {
            g.writeStartArray();
            g.writeString(typeId);
        } else if (typeId != null) {
            g.writeTypeId(typeId);
        }
        g.writeStartObject();
    }

    public void writeCustomTypePrefixForArray(Object value, JsonGenerator g, String typeId) throws IOException {
        if (!g.canWriteTypeId()) {
            g.writeStartArray();
            g.writeString(typeId);
        } else if (typeId != null) {
            g.writeTypeId(typeId);
        }
        g.writeStartArray();
    }

    public void writeCustomTypeSuffixForObject(Object value, JsonGenerator g, String typeId) throws IOException {
        if (!g.canWriteTypeId()) {
            writeTypeSuffixForObject(value, g);
        }
    }

    public void writeCustomTypeSuffixForArray(Object value, JsonGenerator g, String typeId) throws IOException {
        if (!g.canWriteTypeId()) {
            writeTypeSuffixForArray(value, g);
        }
    }
}
