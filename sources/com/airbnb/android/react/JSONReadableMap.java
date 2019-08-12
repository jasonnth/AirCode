package com.airbnb.android.react;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONReadableMap extends JSONObject {
    private static final String NOT_SUPPORTED_MESSAGE = "JSONReadableMap does not implement this method";
    private final ReadableMap map;

    public JSONReadableMap(ReadableMap map2) {
        this.map = map2;
    }

    public int length() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONObject put(String name, boolean value) throws JSONException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONObject put(String name, double value) throws JSONException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONObject put(String name, int value) throws JSONException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONObject put(String name, long value) throws JSONException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONObject put(String name, Object value) throws JSONException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONObject putOpt(String name, Object value) throws JSONException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONObject accumulate(String name, Object value) throws JSONException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public Object remove(String name) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public boolean isNull(String name) {
        return this.map.isNull(name);
    }

    public JSONArray toJSONArray(JSONArray names) throws JSONException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public Iterator<String> keys() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONArray names() {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public Object opt(String name) {
        if (!this.map.hasKey(name)) {
            return null;
        }
        switch (this.map.getType(name)) {
            case Array:
                return new JSONReadableArray(this.map.getArray(name));
            case Map:
                return new JSONReadableMap(this.map.getMap(name));
            case Boolean:
                return Boolean.valueOf(this.map.getBoolean(name));
            case Number:
                try {
                    return Integer.valueOf(this.map.getInt(name));
                } catch (Exception e) {
                    return Double.valueOf(this.map.getDouble(name));
                }
            case String:
                return this.map.getString(name);
            default:
                return null;
        }
    }

    public boolean optBoolean(String name) {
        return optBoolean(name, false);
    }

    public boolean optBoolean(String name, boolean fallback) {
        if (!this.map.hasKey(name) || this.map.getType(name) != ReadableType.Boolean) {
            return fallback;
        }
        return this.map.getBoolean(name);
    }

    public double optDouble(String name) {
        return optDouble(name, Double.NaN);
    }

    public double optDouble(String name, double fallback) {
        if (!this.map.hasKey(name) || this.map.getType(name) != ReadableType.Number) {
            return fallback;
        }
        return this.map.getDouble(name);
    }

    public int optInt(String name) {
        return optInt(name, 0);
    }

    public int optInt(String name, int fallback) {
        if (!this.map.hasKey(name) || this.map.getType(name) != ReadableType.Number) {
            return fallback;
        }
        return this.map.getInt(name);
    }

    public long optLong(String name) {
        return optLong(name, 0);
    }

    public long optLong(String name, long fallback) {
        if (!this.map.hasKey(name) || this.map.getType(name) != ReadableType.Number) {
            return fallback;
        }
        try {
            return (long) this.map.getInt(name);
        } catch (Exception e) {
            return fallback;
        }
    }

    public String optString(String name) {
        return optString(name, "");
    }

    public String optString(String name, String fallback) {
        if (!this.map.hasKey(name) || this.map.getType(name) != ReadableType.String) {
            return fallback;
        }
        return this.map.getString(name);
    }

    public JSONArray optJSONArray(String name) {
        if (!this.map.hasKey(name) || this.map.getType(name) != ReadableType.Array) {
            return null;
        }
        return new JSONReadableArray(this.map.getArray(name));
    }

    public JSONObject optJSONObject(String name) {
        if (!this.map.hasKey(name) || this.map.getType(name) != ReadableType.Map) {
            return null;
        }
        return new JSONReadableMap(this.map.getMap(name));
    }

    public boolean has(String name) {
        return this.map.hasKey(name);
    }

    public boolean getBoolean(String name) throws JSONException {
        try {
            return this.map.getBoolean(name);
        } catch (RuntimeException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public int getInt(String name) throws JSONException {
        try {
            return this.map.getInt(name);
        } catch (RuntimeException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public long getLong(String name) throws JSONException {
        try {
            return (long) this.map.getInt(name);
        } catch (RuntimeException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public double getDouble(String name) throws JSONException {
        try {
            return this.map.getDouble(name);
        } catch (RuntimeException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public String getString(String name) throws JSONException {
        try {
            return this.map.getString(name);
        } catch (RuntimeException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public JSONArray getJSONArray(String name) throws JSONException {
        try {
            return new JSONReadableArray(this.map.getArray(name));
        } catch (RuntimeException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public JSONObject getJSONObject(String name) throws JSONException {
        try {
            return new JSONReadableMap(this.map.getMap(name));
        } catch (RuntimeException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public Object get(String name) throws JSONException {
        try {
            switch (this.map.getType(name)) {
                case Array:
                    return getJSONArray(name);
                case Map:
                    return getJSONObject(name);
                case Boolean:
                    return Boolean.valueOf(getBoolean(name));
                case Number:
                    try {
                        return Integer.valueOf(this.map.getInt(name));
                    } catch (RuntimeException e) {
                        return Double.valueOf(this.map.getDouble(name));
                    }
                case String:
                    return getString(name);
                case Null:
                    return null;
                default:
                    throw new JSONException("Could not convert object with key '" + name + "'.");
            }
        } catch (RuntimeException e2) {
            throw new JSONException(e2.getMessage());
        }
        throw new JSONException(e2.getMessage());
    }
}
