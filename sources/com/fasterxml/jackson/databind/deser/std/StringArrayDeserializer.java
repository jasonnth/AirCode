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
import com.fasterxml.jackson.databind.util.ObjectBuffer;
import java.io.IOException;

@JacksonStdImpl
public final class StringArrayDeserializer extends StdDeserializer<String[]> implements ContextualDeserializer {
    public static final StringArrayDeserializer instance = new StringArrayDeserializer();
    protected JsonDeserializer<String> _elementDeserializer;
    protected final Boolean _unwrapSingle;

    public StringArrayDeserializer() {
        this(null, null);
    }

    protected StringArrayDeserializer(JsonDeserializer<?> deser, Boolean unwrapSingle) {
        super(String[].class);
        this._elementDeserializer = deser;
        this._unwrapSingle = unwrapSingle;
    }

    /* Debug info: failed to restart local var, previous not found, register: 5 */
    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        JsonDeserializer<?> deser;
        JsonDeserializer<?> deser2 = findConvertingContentDeserializer(ctxt, property, this._elementDeserializer);
        JavaType type = ctxt.constructType(String.class);
        if (deser2 == null) {
            deser = ctxt.findContextualValueDeserializer(type, property);
        } else {
            deser = ctxt.handleSecondaryContextualization(deser2, property, type);
        }
        Boolean unwrapSingle = findFormatFeature(ctxt, property, String[].class, Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        if (deser != null && isDefaultDeserializer(deser)) {
            deser = null;
        }
        return (this._elementDeserializer == deser && this._unwrapSingle == unwrapSingle) ? this : new StringArrayDeserializer(deser, unwrapSingle);
    }

    public String[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (!p.isExpectedStartArrayToken()) {
            return handleNonArray(p, ctxt);
        }
        if (this._elementDeserializer != null) {
            return _deserializeCustom(p, ctxt);
        }
        ObjectBuffer buffer = ctxt.leaseObjectBuffer();
        Object[] chunk = buffer.resetAndStart();
        int ix = 0;
        while (true) {
            try {
                String value = p.nextTextValue();
                if (value == null) {
                    JsonToken t = p.getCurrentToken();
                    if (t == JsonToken.END_ARRAY) {
                        String[] strArr = (String[]) buffer.completeAndClearBuffer(chunk, ix, String.class);
                        ctxt.returnObjectBuffer(buffer);
                        return strArr;
                    } else if (t != JsonToken.VALUE_NULL) {
                        value = _parseString(p, ctxt);
                    }
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
    }

    /* access modifiers changed from: protected */
    public final String[] _deserializeCustom(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value;
        ObjectBuffer buffer = ctxt.leaseObjectBuffer();
        Object[] chunk = buffer.resetAndStart();
        JsonDeserializer<String> deser = this._elementDeserializer;
        int ix = 0;
        while (true) {
            try {
                if (p.nextTextValue() == null) {
                    JsonToken t = p.getCurrentToken();
                    if (t == JsonToken.END_ARRAY) {
                        String[] result = (String[]) buffer.completeAndClearBuffer(chunk, ix, String.class);
                        ctxt.returnObjectBuffer(buffer);
                        return result;
                    }
                    value = t == JsonToken.VALUE_NULL ? (String) deser.getNullValue(ctxt) : (String) deser.deserialize(p, ctxt);
                } else {
                    value = (String) deser.deserialize(p, ctxt);
                }
                if (ix >= chunk.length) {
                    chunk = buffer.appendCompletedChunk(chunk);
                    ix = 0;
                }
                int ix2 = ix;
                ix = ix2 + 1;
                chunk[ix2] = value;
            } catch (Exception e) {
                throw JsonMappingException.wrapWithPath((Throwable) e, (Object) String.class, ix);
            }
        }
    }

    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(p, ctxt);
    }

    private final String[] handleNonArray(JsonParser p, DeserializationContext ctxt) throws IOException {
        boolean canWrap;
        String str = null;
        if (this._unwrapSingle == Boolean.TRUE || (this._unwrapSingle == null && ctxt.isEnabled(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY))) {
            canWrap = true;
        } else {
            canWrap = false;
        }
        if (canWrap) {
            String[] strArr = new String[1];
            if (!p.hasToken(JsonToken.VALUE_NULL)) {
                str = _parseString(p, ctxt);
            }
            strArr[0] = str;
            return strArr;
        } else if (!p.hasToken(JsonToken.VALUE_STRING) || !ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) || p.getText().length() != 0) {
            return (String[]) ctxt.handleUnexpectedToken(this._valueClass, p);
        } else {
            return null;
        }
    }
}
