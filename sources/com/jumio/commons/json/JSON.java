package com.jumio.commons.json;

import org.json.JSONException;

public class JSON {
    static StringBuilder toStringBuilder(Object value) {
        if (value instanceof StringBuilder) {
            return (StringBuilder) value;
        }
        return null;
    }

    public static JSONException typeMismatch(Object indexOrName, Object actual, String requiredType) throws JSONException {
        if (actual == null) {
            throw new JSONException("Value at " + indexOrName + " is null.");
        }
        throw new JSONException("Value " + actual + " at " + indexOrName + " of type " + actual.getClass().getName() + " cannot be converted to " + requiredType);
    }

    public static JSONException typeMismatch(Object actual, String requiredType) throws JSONException {
        if (actual == null) {
            throw new JSONException("Value is null.");
        }
        throw new JSONException("Value " + actual + " of type " + actual.getClass().getName() + " cannot be converted to " + requiredType);
    }
}
