package com.fasterxml.jackson.databind.jsonFormatVisitors;

import java.util.HashMap;
import java.util.Map;

public enum JsonFormatTypes {
    STRING,
    NUMBER,
    INTEGER,
    BOOLEAN,
    OBJECT,
    ARRAY,
    NULL,
    ANY;
    
    private static final Map<String, JsonFormatTypes> _byLCName = null;

    static {
        JsonFormatTypes[] arr$;
        _byLCName = new HashMap();
        for (JsonFormatTypes t : values()) {
            _byLCName.put(t.name().toLowerCase(), t);
        }
    }
}
