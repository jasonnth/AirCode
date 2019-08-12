package com.airbnb.android.react;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONReadableArray extends JSONArray {
    private static final String NOT_SUPPORTED_MESSAGE = "JSONReadableArray does not implement this method";
    private final ReadableArray array;
    private final Object[] cache;

    public JSONReadableArray(ReadableArray array2) {
        this.array = array2;
        this.cache = new Object[array2.size()];
    }

    public JSONArray put(boolean value) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONArray put(double value) throws JSONException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONArray put(int value) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONArray put(long value) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONArray put(Object value) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONArray put(int index, boolean value) throws JSONException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONArray put(int index, double value) throws JSONException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONArray put(int index, int value) throws JSONException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONArray put(int index, long value) throws JSONException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONArray put(int index, Object value) throws JSONException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public Object remove(int index) {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public JSONObject toJSONObject(JSONArray names) throws JSONException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public String join(String separator) throws JSONException {
        throw new UnsupportedOperationException(NOT_SUPPORTED_MESSAGE);
    }

    public boolean isNull(int index) {
        return this.array.isNull(index);
    }

    public Object opt(int index) {
        Object obj = null;
        if (index >= this.array.size()) {
            return obj;
        }
        try {
            switch (this.array.getType(index)) {
                case Array:
                    return getJSONArray(index);
                case Map:
                    return getJSONObject(index);
                case Boolean:
                    return Boolean.valueOf(getBoolean(index));
                case Number:
                    try {
                        return Integer.valueOf(getInt(index));
                    } catch (Exception e) {
                        return Double.valueOf(getDouble(index));
                    }
                case String:
                    return getString(index);
                default:
                    return obj;
            }
        } catch (JSONException e2) {
            return obj;
        }
        return obj;
    }

    public boolean optBoolean(int index) {
        return optBoolean(index, false);
    }

    public boolean optBoolean(int index, boolean fallback) {
        if (index >= this.array.size() || this.array.getType(index) != ReadableType.Boolean) {
            return fallback;
        }
        try {
            return getBoolean(index);
        } catch (JSONException e) {
            return fallback;
        }
    }

    public double optDouble(int index) {
        return optDouble(index, Double.NaN);
    }

    public double optDouble(int index, double fallback) {
        if (index >= this.array.size() || this.array.getType(index) != ReadableType.Number) {
            return fallback;
        }
        try {
            return getDouble(index);
        } catch (JSONException e) {
            return fallback;
        }
    }

    public int optInt(int index) {
        return optInt(index, 0);
    }

    public int optInt(int index, int fallback) {
        if (index >= this.array.size() || this.array.getType(index) != ReadableType.Number) {
            return fallback;
        }
        try {
            return getInt(index);
        } catch (JSONException e) {
            return fallback;
        }
    }

    public long optLong(int index) {
        return optLong(index, 0);
    }

    public long optLong(int index, long fallback) {
        if (index >= this.array.size() || this.array.getType(index) != ReadableType.Number) {
            return fallback;
        }
        try {
            return getLong(index);
        } catch (JSONException e) {
            return fallback;
        }
    }

    public String optString(int index) {
        return optString(index, "");
    }

    public String optString(int index, String fallback) {
        if (index >= this.array.size() || this.array.getType(index) != ReadableType.String) {
            return fallback;
        }
        try {
            return getString(index);
        } catch (JSONException e) {
            return fallback;
        }
    }

    public JSONArray optJSONArray(int index) {
        JSONArray jSONArray = null;
        if (index >= this.array.size() || this.array.getType(index) != ReadableType.Array) {
            return jSONArray;
        }
        try {
            return getJSONArray(index);
        } catch (JSONException e) {
            return jSONArray;
        }
    }

    public JSONObject optJSONObject(int index) {
        JSONObject jSONObject = null;
        if (index >= this.array.size() || this.array.getType(index) != ReadableType.Map) {
            return jSONObject;
        }
        try {
            return getJSONObject(index);
        } catch (JSONException e) {
            return jSONObject;
        }
    }

    public int length() {
        return this.array.size();
    }

    public JSONObject getJSONObject(int index) throws JSONException {
        if (this.cache[index] != null) {
            return (JSONReadableMap) this.cache[index];
        }
        try {
            JSONReadableMap val = new JSONReadableMap(this.array.getMap(index));
            this.cache[index] = val;
            return val;
        } catch (RuntimeException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public JSONArray getJSONArray(int index) throws JSONException {
        if (this.cache[index] != null) {
            return (JSONReadableArray) this.cache[index];
        }
        try {
            JSONReadableArray val = new JSONReadableArray(this.array.getArray(index));
            this.cache[index] = val;
            return val;
        } catch (RuntimeException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public String getString(int index) throws JSONException {
        if (this.cache[index] != null) {
            return (String) this.cache[index];
        }
        try {
            String val = this.array.getString(index);
            this.cache[index] = val;
            return val;
        } catch (RuntimeException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public double getDouble(int index) throws JSONException {
        if (this.cache[index] != null) {
            return ((Double) this.cache[index]).doubleValue();
        }
        try {
            Double val = Double.valueOf(this.array.getDouble(index));
            this.cache[index] = val;
            return val.doubleValue();
        } catch (RuntimeException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public int getInt(int index) throws JSONException {
        if (this.cache[index] != null) {
            Object val = this.cache[index];
            if (val instanceof Double) {
                return ((Double) val).intValue();
            }
        }
        try {
            Double val2 = Double.valueOf(this.array.getDouble(index));
            this.cache[index] = val2;
            return val2.intValue();
        } catch (RuntimeException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public long getLong(int index) throws JSONException {
        if (this.cache[index] != null) {
            Object val = this.cache[index];
            if (val instanceof Double) {
                return ((Double) val).longValue();
            }
        }
        try {
            Double val2 = Double.valueOf(this.array.getDouble(index));
            this.cache[index] = val2;
            return val2.longValue();
        } catch (RuntimeException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public boolean getBoolean(int index) throws JSONException {
        if (this.cache[index] != null) {
            return ((Boolean) this.cache[index]).booleanValue();
        }
        try {
            Boolean val = Boolean.valueOf(this.array.getBoolean(index));
            this.cache[index] = val;
            return val.booleanValue();
        } catch (RuntimeException e) {
            throw new JSONException(e.getMessage());
        }
    }

    public Object get(int index) throws JSONException {
        Object jSONReadableArray;
        if (this.cache[index] != null) {
            return this.cache[index];
        }
        try {
            switch (this.array.getType(index)) {
                case Array:
                    jSONReadableArray = new JSONReadableArray(this.array.getArray(index));
                    break;
                case Map:
                    jSONReadableArray = new JSONReadableMap(this.array.getMap(index));
                    break;
                case Boolean:
                    jSONReadableArray = Boolean.valueOf(this.array.getBoolean(index));
                    break;
                case Number:
                    Double dbl = Double.valueOf(this.array.getDouble(index));
                    this.cache[index] = dbl;
                    if (dbl.doubleValue() != Math.floor(dbl.doubleValue()) || Double.isInfinite(dbl.doubleValue())) {
                        return dbl;
                    }
                    return Integer.valueOf(dbl.intValue());
                case String:
                    jSONReadableArray = this.array.getString(index);
                    break;
                case Null:
                    jSONReadableArray = null;
                    break;
                default:
                    throw new JSONException("Could not convert object");
            }
            this.cache[index] = jSONReadableArray;
            return jSONReadableArray;
        } catch (RuntimeException e) {
            throw new JSONException(e.getMessage());
        }
    }
}
