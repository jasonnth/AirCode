package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public final class WritableObjectId {
    public final ObjectIdGenerator<?> generator;

    /* renamed from: id */
    public Object f3143id;
    protected boolean idWritten = false;

    public WritableObjectId(ObjectIdGenerator<?> generator2) {
        this.generator = generator2;
    }

    public boolean writeAsId(JsonGenerator gen, SerializerProvider provider, ObjectIdWriter w) throws IOException {
        if (this.f3143id == null || (!this.idWritten && !w.alwaysAsId)) {
            return false;
        }
        if (gen.canWriteObjectId()) {
            gen.writeObjectRef(String.valueOf(this.f3143id));
        } else {
            w.serializer.serialize(this.f3143id, gen, provider);
        }
        return true;
    }

    public Object generateId(Object forPojo) {
        if (this.f3143id == null) {
            this.f3143id = this.generator.generateId(forPojo);
        }
        return this.f3143id;
    }

    public void writeAsField(JsonGenerator gen, SerializerProvider provider, ObjectIdWriter w) throws IOException {
        this.idWritten = true;
        if (gen.canWriteObjectId()) {
            gen.writeObjectId(String.valueOf(this.f3143id));
            return;
        }
        SerializableString name = w.propertyName;
        if (name != null) {
            gen.writeFieldName(name);
            w.serializer.serialize(this.f3143id, gen, provider);
        }
    }
}
