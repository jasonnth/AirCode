package com.facebook.react.cxxbridge;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.BaseJavaModule.JavaMethod;
import com.facebook.react.bridge.BaseJavaModule.SyncJavaHook;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ExecutorToken;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeModule.NativeMethod;
import com.facebook.react.bridge.NativeModule.SyncNativeHook;
import com.facebook.react.bridge.ReadableNativeArray;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@DoNotStrip
class JavaModuleWrapper {
    private final CatalystInstance mCatalystInstance;
    private final ArrayList<JavaMethod> mMethods = new ArrayList<>();
    private final ModuleHolder mModuleHolder;

    @DoNotStrip
    public class MethodDescriptor {
        @DoNotStrip
        Method method;
        @DoNotStrip
        String name;
        @DoNotStrip
        String signature;
        @DoNotStrip
        String type;

        public MethodDescriptor() {
        }
    }

    public JavaModuleWrapper(CatalystInstance catalystinstance, ModuleHolder moduleHolder) {
        this.mCatalystInstance = catalystinstance;
        this.mModuleHolder = moduleHolder;
    }

    @DoNotStrip
    public BaseJavaModule getModule() {
        return (BaseJavaModule) this.mModuleHolder.getModule();
    }

    @DoNotStrip
    public String getName() {
        return this.mModuleHolder.getInfo().name();
    }

    @DoNotStrip
    public List<MethodDescriptor> getMethodDescriptors() {
        ArrayList<MethodDescriptor> descs = new ArrayList<>();
        for (Entry<String, NativeMethod> entry : getModule().getMethods().entrySet()) {
            MethodDescriptor md = new MethodDescriptor();
            md.name = (String) entry.getKey();
            md.type = ((NativeMethod) entry.getValue()).getType();
            this.mMethods.add((JavaMethod) entry.getValue());
            descs.add(md);
        }
        return descs;
    }

    @DoNotStrip
    public List<MethodDescriptor> newGetMethodDescriptors() {
        ArrayList<MethodDescriptor> descs = new ArrayList<>();
        for (Entry<String, NativeMethod> entry : getModule().getMethods().entrySet()) {
            MethodDescriptor md = new MethodDescriptor();
            md.name = (String) entry.getKey();
            md.type = ((NativeMethod) entry.getValue()).getType();
            JavaMethod method = (JavaMethod) entry.getValue();
            md.method = method.getMethod();
            md.signature = method.getSignature();
            descs.add(md);
        }
        for (Entry<String, SyncNativeHook> entry2 : getModule().getSyncHooks().entrySet()) {
            MethodDescriptor md2 = new MethodDescriptor();
            md2.name = (String) entry2.getKey();
            md2.type = BaseJavaModule.METHOD_TYPE_SYNC;
            SyncJavaHook method2 = (SyncJavaHook) entry2.getValue();
            md2.method = method2.getMethod();
            md2.signature = method2.getSignature();
            descs.add(md2);
        }
        return descs;
    }

    /* JADX INFO: finally extract failed */
    @DoNotStrip
    public NativeArray getConstants() {
        SystraceMessage.beginSection(0, "Map constants").arg("moduleName", (Object) getName()).flush();
        Map<String, Object> map = getModule().getConstants();
        Systrace.endSection(0);
        SystraceMessage.beginSection(0, "WritableNativeMap constants").arg("moduleName", (Object) getName()).flush();
        try {
            WritableNativeMap writableNativeMap = Arguments.makeNativeMap(map);
            Systrace.endSection(0);
            WritableNativeArray array = new WritableNativeArray();
            array.pushMap(writableNativeMap);
            return array;
        } catch (Throwable th) {
            Systrace.endSection(0);
            throw th;
        }
    }

    @DoNotStrip
    public boolean supportsWebWorkers() {
        return getModule().supportsWebWorkers();
    }

    @DoNotStrip
    public void invoke(ExecutorToken token, int methodId, ReadableNativeArray parameters) {
        if (this.mMethods != null && methodId < this.mMethods.size()) {
            ((JavaMethod) this.mMethods.get(methodId)).invoke(this.mCatalystInstance, token, parameters);
        }
    }
}
