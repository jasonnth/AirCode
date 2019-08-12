package com.facebook.react.cxxbridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ExecutorToken;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.NativeModule.NativeMethod;
import com.facebook.react.bridge.ReadableNativeArray;
import com.facebook.soloader.SoLoader;
import java.util.Map;

@DoNotStrip
public class CxxModuleWrapper implements NativeModule {
    @DoNotStrip
    private HybridData mHybridData;

    @DoNotStrip
    private static class MethodWrapper implements NativeMethod {
        @DoNotStrip
        HybridData mHybridData = initHybrid();

        public native String getType();

        public native HybridData initHybrid();

        public native void invoke(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray readableNativeArray);

        MethodWrapper() {
        }
    }

    private native HybridData initHybrid(String str, String str2);

    public native String getConstantsJson();

    public native Map<String, NativeMethod> getMethods();

    public native String getName();

    static {
        SoLoader.loadLibrary("reactnativejnifb");
    }

    public CxxModuleWrapper(String library, String factory) {
        SoLoader.loadLibrary(library);
        this.mHybridData = initHybrid(SoLoader.unpackLibraryAndDependencies(library).getAbsolutePath(), factory);
    }

    public void initialize() {
    }

    public boolean canOverrideExistingModule() {
        return false;
    }

    public boolean supportsWebWorkers() {
        return false;
    }

    public void onCatalystInstanceDestroy() {
        this.mHybridData.resetNative();
    }

    protected CxxModuleWrapper(HybridData hd) {
        this.mHybridData = hd;
    }
}
