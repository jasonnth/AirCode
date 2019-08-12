package com.fasterxml.jackson.databind.exc;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;

public class InvalidFormatException extends JsonMappingException {
    protected final Class<?> _targetType;
    protected final Object _value;

    public InvalidFormatException(JsonParser p, String msg, Object value, Class<?> targetType) {
        super(p, msg);
        this._value = value;
        this._targetType = targetType;
    }

    public static InvalidFormatException from(JsonParser p, String msg, Object value, Class<?> targetType) {
        return new InvalidFormatException(p, msg, value, targetType);
    }
}
