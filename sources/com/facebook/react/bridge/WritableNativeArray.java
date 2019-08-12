package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class WritableNativeArray extends ReadableNativeArray implements WritableArray {
    private static native HybridData initHybrid();

    private native void pushNativeArray(WritableNativeArray writableNativeArray);

    private native void pushNativeMap(WritableNativeMap writableNativeMap);

    public native void pushBoolean(boolean z);

    public native void pushDouble(double d);

    public native void pushInt(int i);

    public native void pushNull();

    public native void pushString(String str);

    static {
        ReactBridge.staticInit();
    }

    public WritableNativeArray() {
        super(initHybrid());
    }

    public void pushArray(WritableArray array) {
        Assertions.assertCondition(array == null || (array instanceof WritableNativeArray), "Illegal type provided");
        pushNativeArray((WritableNativeArray) array);
    }

    public void pushMap(WritableMap map) {
        Assertions.assertCondition(map == null || (map instanceof WritableNativeMap), "Illegal type provided");
        pushNativeMap((WritableNativeMap) map);
    }
}
