package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueDeserializer extends CollectionDeserializer {
    public ArrayBlockingQueueDeserializer(JavaType collectionType, JsonDeserializer<Object> valueDeser, TypeDeserializer valueTypeDeser, ValueInstantiator valueInstantiator) {
        super(collectionType, valueDeser, valueTypeDeser, valueInstantiator);
    }

    protected ArrayBlockingQueueDeserializer(JavaType collectionType, JsonDeserializer<Object> valueDeser, TypeDeserializer valueTypeDeser, ValueInstantiator valueInstantiator, JsonDeserializer<Object> delegateDeser, Boolean unwrapSingle) {
        super(collectionType, valueDeser, valueTypeDeser, valueInstantiator, delegateDeser, unwrapSingle);
    }

    /* Debug info: failed to restart local var, previous not found, register: 7 */
    /* access modifiers changed from: protected */
    public ArrayBlockingQueueDeserializer withResolved(JsonDeserializer<?> dd, JsonDeserializer<?> vd, TypeDeserializer vtd, Boolean unwrapSingle) {
        if (dd == this._delegateDeserializer && vd == this._valueDeserializer && vtd == this._valueTypeDeserializer && this._unwrapSingle == unwrapSingle) {
            return this;
        }
        return new ArrayBlockingQueueDeserializer(this._collectionType, vd, vtd, this._valueInstantiator, dd, unwrapSingle);
    }

    public Collection<Object> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        if (this._delegateDeserializer != null) {
            return (Collection) this._valueInstantiator.createUsingDelegate(ctxt, this._delegateDeserializer.deserialize(jp, ctxt));
        }
        if (jp.getCurrentToken() == JsonToken.VALUE_STRING) {
            String str = jp.getText();
            if (str.length() == 0) {
                return (Collection) this._valueInstantiator.createFromString(ctxt, str);
            }
        }
        return deserialize(jp, ctxt, null);
    }

    public Collection<Object> deserialize(JsonParser jp, DeserializationContext ctxt, Collection<Object> result0) throws IOException {
        Object value;
        if (!jp.isExpectedStartArrayToken()) {
            return handleNonArray(jp, ctxt, new ArrayBlockingQueue(1));
        }
        ArrayList<Object> tmp = new ArrayList<>();
        JsonDeserializer<Object> valueDes = this._valueDeserializer;
        TypeDeserializer typeDeser = this._valueTypeDeserializer;
        while (true) {
            try {
                JsonToken t = jp.nextToken();
                if (t == JsonToken.END_ARRAY) {
                    break;
                }
                if (t == JsonToken.VALUE_NULL) {
                    value = valueDes.getNullValue(ctxt);
                } else if (typeDeser == null) {
                    value = valueDes.deserialize(jp, ctxt);
                } else {
                    value = valueDes.deserializeWithType(jp, ctxt, typeDeser);
                }
                tmp.add(value);
            } catch (Exception e) {
                throw JsonMappingException.wrapWithPath((Throwable) e, (Object) tmp, tmp.size());
            }
        }
        if (result0 == null) {
            return new ArrayBlockingQueue<>(tmp.size(), false, tmp);
        }
        result0.addAll(tmp);
        return result0;
    }

    public Object deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromArray(jp, ctxt);
    }
}
