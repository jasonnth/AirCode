package com.airbnb.android.react;

import android.os.Bundle;
import android.util.Log;
import com.airbnb.android.utils.JacksonUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class ConversionUtil {
    private static final String TAG = ConversionUtil.class.getSimpleName();

    private ConversionUtil() {
    }

    public static Map<String, Object> toMap(ReadableMap readableMap) {
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        Map<String, Object> result = new HashMap<>();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (readableMap.getType(key)) {
                case Null:
                    result.put(key, null);
                    break;
                case Boolean:
                    result.put(key, Boolean.valueOf(readableMap.getBoolean(key)));
                    break;
                case Number:
                    result.put(key, Double.valueOf(readableMap.getDouble(key)));
                    break;
                case String:
                    result.put(key, readableMap.getString(key));
                    break;
                case Map:
                    result.put(key, toMap(readableMap.getMap(key)));
                    break;
                case Array:
                    result.put(key, toArray(readableMap.getArray(key)));
                    break;
                default:
                    Log.e(TAG, "Could not convert object with key: " + key + ".");
                    break;
            }
        }
        return result;
    }

    public static <T> T toType(ObjectMapper objectMapper, ReadableMap readableMap, Class<T> targetType) {
        try {
            return JacksonUtils.readerForType(objectMapper, targetType).readValue((JsonNode) toJsonObject(readableMap));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ObjectNode toJsonObject(ReadableMap readableMap) {
        ObjectNode result = JsonNodeFactory.instance.objectNode();
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (readableMap.getType(key)) {
                case Null:
                    result.putNull(key);
                    break;
                case Boolean:
                    result.put(key, readableMap.getBoolean(key));
                    break;
                case Number:
                    result.put(key, readableMap.getDouble(key));
                    break;
                case String:
                    result.put(key, readableMap.getString(key));
                    break;
                case Map:
                    result.set(key, toJsonObject(readableMap.getMap(key)));
                    break;
                case Array:
                    result.set(key, toJsonArray(readableMap.getArray(key)));
                    break;
                default:
                    Log.e(TAG, "Could not convert object with key: " + key + ".");
                    break;
            }
        }
        return result;
    }

    public static ArrayNode toJsonArray(ReadableArray readableArray) {
        ArrayNode result = JsonNodeFactory.instance.arrayNode();
        for (int i = 0; i < readableArray.size(); i++) {
            switch (readableArray.getType(i)) {
                case Null:
                    result.addNull();
                    break;
                case Boolean:
                    result.add(readableArray.getBoolean(i));
                    break;
                case Number:
                    result.add(readableArray.getDouble(i));
                    break;
                case String:
                    result.add(readableArray.getString(i));
                    break;
                case Map:
                    result.add((JsonNode) toJsonObject(readableArray.getMap(i)));
                    break;
                case Array:
                    result.add((JsonNode) toJsonArray(readableArray.getArray(i)));
                    break;
                default:
                    Log.e(TAG, "Could not convert object at index " + i + ".");
                    break;
            }
        }
        return result;
    }

    public static Bundle toBundle(ReadableMap readableMap) {
        Bundle result = new Bundle();
        if (readableMap != null) {
            ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
            while (iterator.hasNextKey()) {
                String key = iterator.nextKey();
                switch (readableMap.getType(key)) {
                    case Null:
                        result.putString(key, null);
                        break;
                    case Boolean:
                        result.putBoolean(key, readableMap.getBoolean(key));
                        break;
                    case Number:
                        try {
                            result.putInt(key, readableMap.getInt(key));
                            break;
                        } catch (Exception e) {
                            result.putDouble(key, readableMap.getDouble(key));
                            break;
                        }
                    case String:
                        result.putString(key, readableMap.getString(key));
                        break;
                    case Map:
                        result.putBundle(key, toBundle(readableMap.getMap(key)));
                        break;
                    case Array:
                        Log.e(TAG, "Cannot put arrays of objects into bundles. Failed on: " + key + ".");
                        break;
                    default:
                        Log.e(TAG, "Could not convert object with key: " + key + ".");
                        break;
                }
            }
        }
        return result;
    }

    public static Map<String, String> toStringMap(ReadableMap readableMap) {
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        Map<String, String> result = new HashMap<>();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (readableMap.getType(key)) {
                case Null:
                    result.put(key, null);
                    break;
                case String:
                    result.put(key, readableMap.getString(key));
                    break;
                default:
                    Log.e(TAG, "Could not convert object with key: " + key + ".");
                    break;
            }
        }
        return result;
    }

    public static Map<String, Double> toDoubleMap(ReadableMap readableMap) {
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        Map<String, Double> result = new HashMap<>();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (readableMap.getType(key)) {
                case Number:
                    result.put(key, Double.valueOf(readableMap.getDouble(key)));
                    break;
                default:
                    Log.e(TAG, "Could not convert object with key: " + key + ".");
                    break;
            }
        }
        return result;
    }

    public static Map<String, Integer> toIntegerMap(ReadableMap readableMap) {
        ReadableMapKeySetIterator iterator = readableMap.keySetIterator();
        Map<String, Integer> result = new HashMap<>();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            switch (readableMap.getType(key)) {
                case Number:
                    result.put(key, Integer.valueOf(readableMap.getInt(key)));
                    break;
                default:
                    Log.e(TAG, "Could not convert object with key: " + key + ".");
                    break;
            }
        }
        return result;
    }

    public static List<Object> toArray(ReadableArray readableArray) {
        List<Object> result = new ArrayList<>(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            switch (readableArray.getType(i)) {
                case Null:
                    result.add(i, null);
                    break;
                case Boolean:
                    result.add(i, Boolean.valueOf(readableArray.getBoolean(i)));
                    break;
                case Number:
                    result.add(i, Double.valueOf(readableArray.getDouble(i)));
                    break;
                case String:
                    result.add(i, readableArray.getString(i));
                    break;
                case Map:
                    result.add(i, toMap(readableArray.getMap(i)));
                    break;
                case Array:
                    result.add(i, toArray(readableArray.getArray(i)));
                    break;
                default:
                    Log.e(TAG, "Could not convert object at index " + i + ".");
                    break;
            }
        }
        return result;
    }

    public static List<String> toStringArray(ReadableArray readableArray) {
        List<String> result = new ArrayList<>(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            switch (readableArray.getType(i)) {
                case Null:
                    result.add(i, null);
                    break;
                case String:
                    result.add(i, readableArray.getString(i));
                    break;
                default:
                    Log.e(TAG, "Could not convert object at index " + i + ".");
                    break;
            }
        }
        return result;
    }

    public static List<Double> toDoubleArray(ReadableArray readableArray) {
        List<Double> result = new ArrayList<>(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            switch (readableArray.getType(i)) {
                case Number:
                    result.add(i, Double.valueOf(readableArray.getDouble(i)));
                    break;
                default:
                    Log.e(TAG, "Could not convert object at index " + i + ".");
                    break;
            }
        }
        return result;
    }

    public static List<Integer> toIntArray(ReadableArray readableArray) {
        List<Integer> result = new ArrayList<>(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            switch (readableArray.getType(i)) {
                case Number:
                    result.add(i, Integer.valueOf(readableArray.getInt(i)));
                    break;
                default:
                    Log.e(TAG, "Could not convert object at index " + i + ".");
                    break;
            }
        }
        return result;
    }

    public static List<Map<String, Object>> toMapArray(ReadableArray readableArray) {
        List<Map<String, Object>> result = new ArrayList<>(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            switch (readableArray.getType(i)) {
                case Map:
                    result.add(i, toMap(readableArray.getMap(i)));
                    break;
                default:
                    Log.e(TAG, "Could not convert object at index " + i + ".");
                    break;
            }
        }
        return result;
    }

    public static WritableMap toWritableMap(Map<String, Object> map) {
        WritableNativeMap result = new WritableNativeMap();
        for (Entry<String, Object> entry : map.entrySet()) {
            String key = (String) entry.getKey();
            Object value = entry.getValue();
            if (value == null) {
                result.putNull(key);
            } else if (value instanceof Map) {
                result.putMap(key, toWritableMap((Map) value));
            } else if (value instanceof List) {
                result.putArray(key, toWritableArray((List) value));
            } else if (value instanceof Boolean) {
                result.putBoolean(key, ((Boolean) value).booleanValue());
            } else if (value instanceof Integer) {
                result.putInt(key, ((Integer) value).intValue());
            } else if (value instanceof String) {
                result.putString(key, (String) value);
            } else if (value instanceof Double) {
                result.putDouble(key, ((Double) value).doubleValue());
            } else {
                Log.e(TAG, "Could not convert object " + value.toString());
            }
        }
        return result;
    }

    private static WritableArray toWritableArray(List<Object> array) {
        WritableNativeArray result = new WritableNativeArray();
        for (Object value : array) {
            if (value == null) {
                result.pushNull();
            } else if (value instanceof Map) {
                result.pushMap(toWritableMap((Map) value));
            } else if (value instanceof List) {
                result.pushArray(toWritableArray((List) value));
            } else if (value instanceof Boolean) {
                result.pushBoolean(((Boolean) value).booleanValue());
            } else if (value instanceof Integer) {
                result.pushInt(((Integer) value).intValue());
            } else if (value instanceof String) {
                result.pushString((String) value);
            } else if (value instanceof Double) {
                result.pushDouble(((Double) value).doubleValue());
            } else {
                Log.e(TAG, "Could not convert object " + value.toString());
            }
        }
        return result;
    }
}
