package com.airbnb.android.react;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.common.MapBuilder;
import java.util.Map;

public abstract class VersionedReactModuleBase extends ReactContextBaseJavaModule {
    static final String VERSION_CONSTANT_KEY = "VERSION";
    private final int Version;

    public VersionedReactModuleBase(ReactApplicationContext reactContext, int version) {
        super(reactContext);
        this.Version = version;
    }

    public Map<String, Object> getConstants() {
        return MapBuilder.builder().put(VERSION_CONSTANT_KEY, Integer.valueOf(this.Version)).build();
    }
}
