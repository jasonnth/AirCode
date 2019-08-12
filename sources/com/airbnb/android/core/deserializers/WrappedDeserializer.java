package com.airbnb.android.core.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import java.io.IOException;

public class WrappedDeserializer extends JsonDeserializer<Object> implements ContextualDeserializer {
    private Class<?> wrappedType;
    private String wrapperKey;

    public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
        this.wrapperKey = ((WrappedObject) property.getAnnotation(WrappedObject.class)).value();
        this.wrappedType = property.getType().getRawClass();
        return this;
    }

    public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = jp.getCodec();
        JsonNode jsonNode = (JsonNode) codec.readTree(jp);
        if (!jsonNode.isObject() || !jsonNode.has(this.wrapperKey)) {
            return codec.readValue(jsonNode.traverse(codec), this.wrappedType);
        }
        return codec.readValue(jsonNode.get(this.wrapperKey).traverse(codec), this.wrappedType);
    }
}
