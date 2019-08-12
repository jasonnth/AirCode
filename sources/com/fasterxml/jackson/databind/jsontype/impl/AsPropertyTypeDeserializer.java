package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo.C1092As;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserSequence;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;

public class AsPropertyTypeDeserializer extends AsArrayTypeDeserializer {
    protected final C1092As _inclusion;

    public AsPropertyTypeDeserializer(JavaType bt, TypeIdResolver idRes, String typePropertyName, boolean typeIdVisible, JavaType defaultImpl, C1092As inclusion) {
        super(bt, idRes, typePropertyName, typeIdVisible, defaultImpl);
        this._inclusion = inclusion;
    }

    public AsPropertyTypeDeserializer(AsPropertyTypeDeserializer src, BeanProperty property) {
        super(src, property);
        this._inclusion = src._inclusion;
    }

    /* Debug info: failed to restart local var, previous not found, register: 1 */
    public TypeDeserializer forProperty(BeanProperty prop) {
        return prop == this._property ? this : new AsPropertyTypeDeserializer(this, prop);
    }

    public C1092As getTypeInclusion() {
        return this._inclusion;
    }

    public Object deserializeTypedFromObject(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.canReadTypeId()) {
            Object typeId = p.getTypeId();
            if (typeId != null) {
                return _deserializeWithNativeTypeId(p, ctxt, typeId);
            }
        }
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.START_OBJECT) {
            t = p.nextToken();
        } else if (t != JsonToken.FIELD_NAME) {
            return _deserializeTypedUsingDefaultImpl(p, ctxt, null);
        }
        TokenBuffer tb = null;
        while (t == JsonToken.FIELD_NAME) {
            String name = p.getCurrentName();
            p.nextToken();
            if (name.equals(this._typePropertyName)) {
                return _deserializeTypedForId(p, ctxt, tb);
            }
            if (tb == null) {
                tb = new TokenBuffer(p, ctxt);
            }
            tb.writeFieldName(name);
            tb.copyCurrentStructure(p);
            t = p.nextToken();
        }
        return _deserializeTypedUsingDefaultImpl(p, ctxt, tb);
    }

    /* access modifiers changed from: protected */
    public Object _deserializeTypedForId(JsonParser p, DeserializationContext ctxt, TokenBuffer tb) throws IOException {
        String typeId = p.getText();
        JsonDeserializer<Object> deser = _findDeserializer(ctxt, typeId);
        if (this._typeIdVisible) {
            if (tb == null) {
                tb = new TokenBuffer(p, ctxt);
            }
            tb.writeFieldName(p.getCurrentName());
            tb.writeString(typeId);
        }
        if (tb != null) {
            p.clearCurrentToken();
            p = JsonParserSequence.createFlattened(false, tb.asParser(p), p);
        }
        p.nextToken();
        return deser.deserialize(p, ctxt);
    }

    /* access modifiers changed from: protected */
    public Object _deserializeTypedUsingDefaultImpl(JsonParser p, DeserializationContext ctxt, TokenBuffer tb) throws IOException {
        JsonDeserializer<Object> deser = _findDefaultImplDeserializer(ctxt);
        if (deser != null) {
            if (tb != null) {
                tb.writeEndObject();
                p = tb.asParser(p);
                p.nextToken();
            }
            return deser.deserialize(p, ctxt);
        }
        Object result = TypeDeserializer.deserializeIfNatural(p, ctxt, this._baseType);
        if (result != null) {
            return result;
        }
        if (p.isExpectedStartArrayToken()) {
            return super.deserializeTypedFromAny(p, ctxt);
        }
        if (p.hasToken(JsonToken.VALUE_STRING) && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && p.getText().trim().isEmpty()) {
            return null;
        }
        ctxt.reportWrongTokenException(p, JsonToken.FIELD_NAME, "missing property '" + this._typePropertyName + "' that is to contain type id  (for class " + baseTypeName() + ")", new Object[0]);
        return null;
    }

    public Object deserializeTypedFromAny(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.getCurrentToken() == JsonToken.START_ARRAY) {
            return super.deserializeTypedFromArray(p, ctxt);
        }
        return deserializeTypedFromObject(p, ctxt);
    }
}
