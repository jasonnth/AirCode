package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;
import java.lang.reflect.Array;

@JacksonStdImpl
public class ObjectArrayDeserializer extends ContainerDeserializerBase<Object[]> implements ContextualDeserializer {
    protected final ArrayType _arrayType;
    protected final Class<?> _elementClass;
    protected JsonDeserializer<Object> _elementDeserializer;
    protected final TypeDeserializer _elementTypeDeserializer;
    protected final boolean _untyped;
    protected final Boolean _unwrapSingle;

    public ObjectArrayDeserializer(ArrayType arrayType, JsonDeserializer<Object> elemDeser, TypeDeserializer elemTypeDeser) {
        super(arrayType);
        this._arrayType = arrayType;
        this._elementClass = arrayType.getContentType().getRawClass();
        this._untyped = this._elementClass == Object.class;
        this._elementDeserializer = elemDeser;
        this._elementTypeDeserializer = elemTypeDeser;
        this._unwrapSingle = null;
    }

    protected ObjectArrayDeserializer(ObjectArrayDeserializer base, JsonDeserializer<Object> elemDeser, TypeDeserializer elemTypeDeser, Boolean unwrapSingle) {
        super(base._arrayType);
        this._arrayType = base._arrayType;
        this._elementClass = base._elementClass;
        this._untyped = base._untyped;
        this._elementDeserializer = elemDeser;
        this._elementTypeDeserializer = elemTypeDeser;
        this._unwrapSingle = unwrapSingle;
    }

    /* Debug info: failed to restart local var, previous not found, register: 1 */
    public ObjectArrayDeserializer withResolved(TypeDeserializer elemTypeDeser, JsonDeserializer<?> elemDeser, Boolean unwrapSingle) {
        return (unwrapSingle == this._unwrapSingle && elemDeser == this._elementDeserializer && elemTypeDeser == this._elementTypeDeserializer) ? this : new ObjectArrayDeserializer(this, elemDeser, elemTypeDeser, unwrapSingle);
    }

    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        JsonDeserializer<?> deser;
        JsonDeserializer<Object> jsonDeserializer = this._elementDeserializer;
        Boolean unwrapSingle = findFormatFeature(ctxt, property, this._arrayType.getRawClass(), Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        JsonDeserializer<?> deser2 = findConvertingContentDeserializer(ctxt, property, jsonDeserializer);
        JavaType vt = this._arrayType.getContentType();
        if (deser2 == null) {
            deser = ctxt.findContextualValueDeserializer(vt, property);
        } else {
            deser = ctxt.handleSecondaryContextualization(deser2, property, vt);
        }
        TypeDeserializer elemTypeDeser = this._elementTypeDeserializer;
        if (elemTypeDeser != null) {
            elemTypeDeser = elemTypeDeser.forProperty(property);
        }
        return withResolved(elemTypeDeser, deser, unwrapSingle);
    }

    public boolean isCachable() {
        return this._elementDeserializer == null && this._elementTypeDeserializer == null;
    }

    public JsonDeserializer<Object> getContentDeserializer() {
        return this._elementDeserializer;
    }

    public Object[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Object[] result;
        Object value;
        if (!p.isExpectedStartArrayToken()) {
            return handleNonArray(p, ctxt);
        }
        ObjectBuffer buffer = ctxt.leaseObjectBuffer();
        Object[] chunk = buffer.resetAndStart();
        int ix = 0;
        TypeDeserializer typeDeser = this._elementTypeDeserializer;
        while (true) {
            try {
                JsonToken t = p.nextToken();
                if (t == JsonToken.END_ARRAY) {
                    break;
                }
                if (t == JsonToken.VALUE_NULL) {
                    value = this._elementDeserializer.getNullValue(ctxt);
                } else if (typeDeser == null) {
                    value = this._elementDeserializer.deserialize(p, ctxt);
                } else {
                    value = this._elementDeserializer.deserializeWithType(p, ctxt, typeDeser);
                }
                if (ix >= chunk.length) {
                    chunk = buffer.appendCompletedChunk(chunk);
                    ix = 0;
                }
                int ix2 = ix;
                ix = ix2 + 1;
                chunk[ix2] = value;
            } catch (Exception e) {
                throw JsonMappingException.wrapWithPath((Throwable) e, (Object) chunk, buffer.bufferedSize() + ix);
            }
        }
        if (this._untyped) {
            result = buffer.completeAndClearBuffer(chunk, ix);
        } else {
            result = buffer.completeAndClearBuffer(chunk, ix, this._elementClass);
        }
        ctxt.returnObjectBuffer(buffer);
        return result;
    }

    public Object[] deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return (Object[]) typeDeserializer.deserializeTypedFromArray(p, ctxt);
    }

    /* access modifiers changed from: protected */
    public Byte[] deserializeFromBase64(JsonParser p, DeserializationContext ctxt) throws IOException {
        byte[] b = p.getBinaryValue(ctxt.getBase64Variant());
        Byte[] result = new Byte[b.length];
        int len = b.length;
        for (int i = 0; i < len; i++) {
            result[i] = Byte.valueOf(b[i]);
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public Object[] handleNonArray(JsonParser p, DeserializationContext ctxt) throws IOException {
        boolean canWrap;
        Object value;
        Object[] result;
        if (p.hasToken(JsonToken.VALUE_STRING) && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && p.getText().length() == 0) {
            return null;
        }
        if (this._unwrapSingle == Boolean.TRUE || (this._unwrapSingle == null && ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))) {
            canWrap = true;
        } else {
            canWrap = false;
        }
        if (canWrap) {
            if (p.getCurrentToken() == JsonToken.VALUE_NULL) {
                value = this._elementDeserializer.getNullValue(ctxt);
            } else if (this._elementTypeDeserializer == null) {
                value = this._elementDeserializer.deserialize(p, ctxt);
            } else {
                value = this._elementDeserializer.deserializeWithType(p, ctxt, this._elementTypeDeserializer);
            }
            if (this._untyped) {
                result = new Object[1];
            } else {
                result = (Object[]) Array.newInstance(this._elementClass, 1);
            }
            result[0] = value;
            return result;
        } else if (p.getCurrentToken() == JsonToken.VALUE_STRING && this._elementClass == Byte.class) {
            return deserializeFromBase64(p, ctxt);
        } else {
            return (Object[]) ctxt.handleUnexpectedToken(this._arrayType.getRawClass(), p);
        }
    }
}
