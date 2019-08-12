package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.C1092As;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

public class AsExistingPropertyTypeSerializer extends AsPropertyTypeSerializer {
    public AsExistingPropertyTypeSerializer(TypeIdResolver idRes, BeanProperty property, String propName) {
        super(idRes, property, propName);
    }

    /* Debug info: failed to restart local var, previous not found, register: 3 */
    public AsExistingPropertyTypeSerializer forProperty(BeanProperty prop) {
        return this._property == prop ? this : new AsExistingPropertyTypeSerializer(this._idResolver, prop, this._typePropertyName);
    }

    public C1092As getTypeInclusion() {
        return C1092As.EXISTING_PROPERTY;
    }

    public void writeTypePrefixForObject(Object value, JsonGenerator gen) throws IOException {
        String typeId = idFromValue(value);
        if (typeId != null && gen.canWriteTypeId()) {
            gen.writeTypeId(typeId);
        }
        gen.writeStartObject();
    }

    public void writeCustomTypePrefixForObject(Object value, JsonGenerator gen, String typeId) throws IOException {
        if (typeId != null && gen.canWriteTypeId()) {
            gen.writeTypeId(typeId);
        }
        gen.writeStartObject();
    }
}
