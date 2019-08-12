package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class FailingDeserializer extends StdDeserializer<Object> {
    protected final String _message;

    public FailingDeserializer(String m) {
        super(Object.class);
        this._message = m;
    }

    public Object deserialize(JsonParser jp, DeserializationContext ctxt) throws JsonMappingException {
        ctxt.reportMappingException(this._message, new Object[0]);
        return null;
    }
}
