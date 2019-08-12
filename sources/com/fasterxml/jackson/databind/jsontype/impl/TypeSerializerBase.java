package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public abstract class TypeSerializerBase extends TypeSerializer {
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;

    protected TypeSerializerBase(TypeIdResolver idRes, BeanProperty property) {
        this._idResolver = idRes;
        this._property = property;
    }

    public String getPropertyName() {
        return null;
    }

    /* access modifiers changed from: protected */
    public String idFromValue(Object value) {
        String id = this._idResolver.idFromValue(value);
        if (id == null) {
            handleMissingId(value);
        }
        return id;
    }

    /* access modifiers changed from: protected */
    public String idFromValueAndType(Object value, Class<?> type) {
        String id = this._idResolver.idFromValueAndType(value, type);
        if (id == null) {
            handleMissingId(value);
        }
        return id;
    }

    /* access modifiers changed from: protected */
    public void handleMissingId(Object value) {
    }
}
