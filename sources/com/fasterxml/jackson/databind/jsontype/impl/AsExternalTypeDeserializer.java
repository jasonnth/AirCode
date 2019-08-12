package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.C1092As;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;

public class AsExternalTypeDeserializer extends AsArrayTypeDeserializer {
    public AsExternalTypeDeserializer(JavaType bt, TypeIdResolver idRes, String typePropertyName, boolean typeIdVisible, JavaType defaultImpl) {
        super(bt, idRes, typePropertyName, typeIdVisible, defaultImpl);
    }

    public AsExternalTypeDeserializer(AsExternalTypeDeserializer src, BeanProperty property) {
        super(src, property);
    }

    /* Debug info: failed to restart local var, previous not found, register: 1 */
    public TypeDeserializer forProperty(BeanProperty prop) {
        return prop == this._property ? this : new AsExternalTypeDeserializer(this, prop);
    }

    public C1092As getTypeInclusion() {
        return C1092As.EXTERNAL_PROPERTY;
    }

    /* access modifiers changed from: protected */
    public boolean _usesExternalId() {
        return true;
    }
}
