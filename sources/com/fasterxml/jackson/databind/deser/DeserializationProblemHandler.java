package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import java.io.IOException;

public abstract class DeserializationProblemHandler {
    public static final Object NOT_HANDLED = new Object();

    public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser p, JsonDeserializer<?> jsonDeserializer, Object beanOrClass, String propertyName) throws IOException {
        return false;
    }

    public Object handleWeirdKey(DeserializationContext ctxt, Class<?> cls, String keyValue, String failureMsg) throws IOException {
        return NOT_HANDLED;
    }

    public Object handleWeirdStringValue(DeserializationContext ctxt, Class<?> cls, String valueToConvert, String failureMsg) throws IOException {
        return NOT_HANDLED;
    }

    public Object handleWeirdNumberValue(DeserializationContext ctxt, Class<?> cls, Number valueToConvert, String failureMsg) throws IOException {
        return NOT_HANDLED;
    }

    public Object handleUnexpectedToken(DeserializationContext ctxt, Class<?> cls, JsonToken t, JsonParser p, String failureMsg) throws IOException {
        return NOT_HANDLED;
    }

    public Object handleInstantiationProblem(DeserializationContext ctxt, Class<?> cls, Object argument, Throwable t) throws IOException {
        return NOT_HANDLED;
    }

    public Object handleMissingInstantiator(DeserializationContext ctxt, Class<?> cls, JsonParser p, String msg) throws IOException {
        return NOT_HANDLED;
    }

    public JavaType handleUnknownTypeId(DeserializationContext ctxt, JavaType baseType, String subTypeId, TypeIdResolver idResolver, String failureMsg) throws IOException {
        return null;
    }
}
