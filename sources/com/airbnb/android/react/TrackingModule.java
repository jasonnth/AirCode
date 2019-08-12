package com.airbnb.android.react;

import android.support.p000v4.util.ArrayMap;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class TrackingModule extends VersionedReactModuleBase {
    private static final int VERSION = 1;

    TrackingModule(ReactApplicationContext reactContext) {
        super(reactContext, 1);
    }

    public String getName() {
        return "TrackingBridge";
    }

    @ReactMethod
    public void logEvent(ReadableMap data) {
        AirbnbEventLogger.track(data.getString("event_name"), mapFromReadableMap(data.getMap("event_data")));
    }

    @ReactMethod
    public void logJitneyEvent(ReadableMap data) {
        AirbnbEventLogger.trackJitneyJSON(mapFromReadableMap(data));
    }

    private static List<Object> arrayFromReadableArray(ReadableArray array) {
        List<Object> result = new ArrayList<>(array.size());
        for (int index = 0; index < array.size(); index++) {
            result.add(readArrayAtIndex(array, index));
        }
        return result;
    }

    private static Map<String, Object> mapFromReadableMap(ReadableMap map) {
        Map<String, Object> result = new ArrayMap<>();
        ReadableMapKeySetIterator iterator = map.keySetIterator();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            Object value = readMapKey(map, key);
            if (value != null) {
                result.put(key, value);
            }
        }
        return result;
    }

    private static Object readMapKey(ReadableMap map, String key) {
        switch (map.getType(key)) {
            case String:
                return map.getString(key);
            case Boolean:
                return Boolean.valueOf(map.getBoolean(key));
            case Number:
                return Double.valueOf(map.getDouble(key));
            case Array:
                return arrayFromReadableArray(map.getArray(key));
            case Map:
                return mapFromReadableMap(map.getMap(key));
            case Null:
                return null;
            default:
                throw new IllegalArgumentException("Could not convert object with key: " + key);
        }
    }

    private static Object readArrayAtIndex(ReadableArray array, int index) {
        switch (array.getType(index)) {
            case String:
                return array.getString(index);
            case Boolean:
                return Boolean.valueOf(array.getBoolean(index));
            case Number:
                return Double.valueOf(array.getDouble(index));
            case Array:
                return arrayFromReadableArray(array.getArray(index));
            case Map:
                return mapFromReadableMap(array.getMap(index));
            case Null:
                return null;
            default:
                throw new IllegalArgumentException("Could not convert object with index: " + index + ".");
        }
    }
}
