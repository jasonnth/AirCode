package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import java.io.IOException;
import java.util.EnumMap;

public class EnumMapDeserializer extends ContainerDeserializerBase<EnumMap<?, ?>> implements ContextualDeserializer {
    protected final Class<?> _enumClass;
    protected KeyDeserializer _keyDeserializer;
    protected final JavaType _mapType;
    protected JsonDeserializer<Object> _valueDeserializer;
    protected final TypeDeserializer _valueTypeDeserializer;

    public EnumMapDeserializer(JavaType mapType, KeyDeserializer keyDeserializer, JsonDeserializer<?> valueDeser, TypeDeserializer valueTypeDeser) {
        super(mapType);
        this._mapType = mapType;
        this._enumClass = mapType.getKeyType().getRawClass();
        this._keyDeserializer = keyDeserializer;
        this._valueDeserializer = valueDeser;
        this._valueTypeDeserializer = valueTypeDeser;
    }

    /* Debug info: failed to restart local var, previous not found, register: 3 */
    public EnumMapDeserializer withResolved(KeyDeserializer keyDeserializer, JsonDeserializer<?> valueDeserializer, TypeDeserializer valueTypeDeser) {
        return (keyDeserializer == this._keyDeserializer && valueDeserializer == this._valueDeserializer && valueTypeDeser == this._valueTypeDeserializer) ? this : new EnumMapDeserializer(this._mapType, keyDeserializer, valueDeserializer, this._valueTypeDeserializer);
    }

    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        JsonDeserializer<?> vd;
        KeyDeserializer kd = this._keyDeserializer;
        if (kd == null) {
            kd = ctxt.findKeyDeserializer(this._mapType.getKeyType(), property);
        }
        JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
        JavaType vt = this._mapType.getContentType();
        if (jsonDeserializer == null) {
            vd = ctxt.findContextualValueDeserializer(vt, property);
        } else {
            vd = ctxt.handleSecondaryContextualization(jsonDeserializer, property, vt);
        }
        TypeDeserializer vtd = this._valueTypeDeserializer;
        if (vtd != null) {
            vtd = vtd.forProperty(property);
        }
        return withResolved(kd, vd, vtd);
    }

    public boolean isCachable() {
        return this._valueDeserializer == null && this._keyDeserializer == null && this._valueTypeDeserializer == null;
    }

    public JsonDeserializer<Object> getContentDeserializer() {
        return this._valueDeserializer;
    }

    public EnumMap<?, ?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Object value;
        if (p.getCurrentToken() != JsonToken.START_OBJECT) {
            return (EnumMap) _deserializeFromEmpty(p, ctxt);
        }
        EnumMap result = constructMap();
        JsonDeserializer<Object> valueDes = this._valueDeserializer;
        TypeDeserializer typeDeser = this._valueTypeDeserializer;
        while (p.nextToken() == JsonToken.FIELD_NAME) {
            String keyName = p.getCurrentName();
            Enum<?> key = (Enum) this._keyDeserializer.deserializeKey(keyName, ctxt);
            if (key != null) {
                try {
                    if (p.nextToken() == JsonToken.VALUE_NULL) {
                        value = valueDes.getNullValue(ctxt);
                    } else if (typeDeser == null) {
                        value = valueDes.deserialize(p, ctxt);
                    } else {
                        value = valueDes.deserializeWithType(p, ctxt, typeDeser);
                    }
                    result.put(key, value);
                } catch (Exception e) {
                    wrapAndThrow(e, result, keyName);
                    return null;
                }
            } else if (!ctxt.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return (EnumMap) ctxt.handleWeirdStringValue(this._enumClass, keyName, "value not one of declared Enum instance names for %s", this._mapType.getKeyType());
            } else {
                p.nextToken();
                p.skipChildren();
            }
        }
        return result;
    }

    public Object deserializeWithType(JsonParser jp, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromObject(jp, ctxt);
    }

    /* access modifiers changed from: protected */
    public EnumMap<?, ?> constructMap() {
        return new EnumMap<>(this._enumClass);
    }
}
