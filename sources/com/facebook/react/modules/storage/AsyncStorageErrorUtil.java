package com.facebook.react.modules.storage;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

public class AsyncStorageErrorUtil {
    static WritableMap getError(String key, String errorMessage) {
        WritableMap errorMap = Arguments.createMap();
        errorMap.putString("message", errorMessage);
        if (key != null) {
            errorMap.putString("key", key);
        }
        return errorMap;
    }

    static WritableMap getInvalidKeyError(String key) {
        return getError(key, "Invalid key");
    }

    static WritableMap getInvalidValueError(String key) {
        return getError(key, "Invalid Value");
    }

    static WritableMap getDBError(String key) {
        return getError(key, "Database Error");
    }
}
