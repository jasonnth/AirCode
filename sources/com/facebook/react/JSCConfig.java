package com.facebook.react;

import com.facebook.react.bridge.WritableNativeMap;

public interface JSCConfig {
    public static final JSCConfig EMPTY = new JSCConfig() {
        public WritableNativeMap getConfigMap() {
            return new WritableNativeMap();
        }
    };

    WritableNativeMap getConfigMap();
}
