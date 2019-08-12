package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.p307io.SerializedString;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;

public final class ObjectIdWriter {
    public final boolean alwaysAsId;
    public final ObjectIdGenerator<?> generator;
    public final JavaType idType;
    public final SerializableString propertyName;
    public final JsonSerializer<Object> serializer;

    protected ObjectIdWriter(JavaType t, SerializableString propName, ObjectIdGenerator<?> gen, JsonSerializer<?> ser, boolean alwaysAsId2) {
        this.idType = t;
        this.propertyName = propName;
        this.generator = gen;
        this.serializer = ser;
        this.alwaysAsId = alwaysAsId2;
    }

    public static ObjectIdWriter construct(JavaType idType2, PropertyName propName, ObjectIdGenerator<?> generator2, boolean alwaysAsId2) {
        return construct(idType2, propName == null ? null : propName.getSimpleName(), generator2, alwaysAsId2);
    }

    @Deprecated
    public static ObjectIdWriter construct(JavaType idType2, String propName, ObjectIdGenerator<?> generator2, boolean alwaysAsId2) {
        return new ObjectIdWriter(idType2, propName == null ? null : new SerializedString(propName), generator2, null, alwaysAsId2);
    }

    public ObjectIdWriter withSerializer(JsonSerializer<?> ser) {
        return new ObjectIdWriter(this.idType, this.propertyName, this.generator, ser, this.alwaysAsId);
    }

    /* Debug info: failed to restart local var, previous not found, register: 6 */
    public ObjectIdWriter withAlwaysAsId(boolean newState) {
        return newState == this.alwaysAsId ? this : new ObjectIdWriter(this.idType, this.propertyName, this.generator, this.serializer, newState);
    }
}
