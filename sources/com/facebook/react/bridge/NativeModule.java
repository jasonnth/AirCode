package com.facebook.react.bridge;

import java.util.Map;

public interface NativeModule {

    public interface NativeMethod {
        String getType();

        void invoke(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray readableNativeArray);
    }

    public interface SyncNativeHook {
    }

    boolean canOverrideExistingModule();

    Map<String, NativeMethod> getMethods();

    String getName();

    void initialize();

    void onCatalystInstanceDestroy();

    boolean supportsWebWorkers();
}
