package com.facebook.react.cxxbridge;

import android.util.Pair;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.OnBatchCompleteListener;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.systrace.Systrace;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class NativeModuleRegistry {
    private final ArrayList<OnBatchCompleteListener> mBatchCompleteListenerModules;
    private final Map<Class<? extends NativeModule>, ModuleHolder> mModules;

    public NativeModuleRegistry(List<ModuleSpec> moduleSpecList, Map<Class, ReactModuleInfo> reactModuleInfoMap, boolean isLazyNativeModulesEnabled) {
        Map<String, Pair<Class<? extends NativeModule>, ModuleHolder>> namesToSpecs = new HashMap<>();
        for (ModuleSpec module : moduleSpecList) {
            Class<? extends NativeModule> type = module.getType();
            ReactModuleInfo reactModuleInfo = (ReactModuleInfo) reactModuleInfoMap.get(type);
            if (!isLazyNativeModulesEnabled || reactModuleInfo != null || !BaseJavaModule.class.isAssignableFrom(type)) {
                ModuleHolder holder = new ModuleHolder(type, reactModuleInfo, module.getProvider());
                String name = holder.getInfo().name();
                Class cls = namesToSpecs.containsKey(name) ? (Class) ((Pair) namesToSpecs.get(name)).first : null;
                if (cls == null || holder.getInfo().canOverrideExistingModule()) {
                    namesToSpecs.put(name, new Pair(type, holder));
                } else {
                    throw new IllegalStateException("Native module " + type.getSimpleName() + " tried to override " + cls.getSimpleName() + " for module name " + name + ". If this was your intention, set canOverrideExistingModule=true");
                }
            } else {
                throw new IllegalStateException("Native Java module " + type.getSimpleName() + " should be annotated with @ReactModule and added to a @ReactModuleList.");
            }
        }
        this.mModules = new HashMap();
        for (Pair<Class<? extends NativeModule>, ModuleHolder> pair : namesToSpecs.values()) {
            this.mModules.put(pair.first, pair.second);
        }
        this.mBatchCompleteListenerModules = new ArrayList<>();
        for (Class<? extends NativeModule> type2 : this.mModules.keySet()) {
            if (OnBatchCompleteListener.class.isAssignableFrom(type2)) {
                final ModuleHolder holder2 = (ModuleHolder) this.mModules.get(type2);
                this.mBatchCompleteListenerModules.add(new OnBatchCompleteListener() {
                    public void onBatchComplete() {
                        ((OnBatchCompleteListener) holder2.getModule()).onBatchComplete();
                    }
                });
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public ModuleRegistryHolder getModuleRegistryHolder(CatalystInstanceImpl catalystInstanceImpl) {
        ArrayList<JavaModuleWrapper> javaModules = new ArrayList<>();
        ArrayList<CxxModuleWrapper> cxxModules = new ArrayList<>();
        for (Entry<Class<? extends NativeModule>, ModuleHolder> entry : this.mModules.entrySet()) {
            Class<?> type = (Class) entry.getKey();
            ModuleHolder moduleHolder = (ModuleHolder) entry.getValue();
            if (BaseJavaModule.class.isAssignableFrom(type)) {
                javaModules.add(new JavaModuleWrapper(catalystInstanceImpl, moduleHolder));
            } else if (CxxModuleWrapper.class.isAssignableFrom(type)) {
                cxxModules.add((CxxModuleWrapper) moduleHolder.getModule());
            } else {
                throw new IllegalArgumentException("Unknown module type " + type);
            }
        }
        return new ModuleRegistryHolder(catalystInstanceImpl, javaModules, cxxModules);
    }

    /* access modifiers changed from: 0000 */
    public void notifyCatalystInstanceDestroy() {
        UiThreadUtil.assertOnUiThread();
        Systrace.beginSection(0, "NativeModuleRegistry_notifyCatalystInstanceDestroy");
        try {
            for (ModuleHolder module : this.mModules.values()) {
                module.destroy();
            }
        } finally {
            Systrace.endSection(0);
        }
    }

    /* access modifiers changed from: 0000 */
    public void notifyCatalystInstanceInitialized() {
        UiThreadUtil.assertOnUiThread();
        ReactMarker.logMarker(ReactMarkerConstants.NATIVE_MODULE_INITIALIZE_START);
        Systrace.beginSection(0, "NativeModuleRegistry_notifyCatalystInstanceInitialized");
        try {
            for (ModuleHolder module : this.mModules.values()) {
                module.initialize();
            }
        } finally {
            Systrace.endSection(0);
            ReactMarker.logMarker(ReactMarkerConstants.NATIVE_MODULE_INITIALIZE_END);
        }
    }

    public void onBatchComplete() {
        for (int i = 0; i < this.mBatchCompleteListenerModules.size(); i++) {
            ((OnBatchCompleteListener) this.mBatchCompleteListenerModules.get(i)).onBatchComplete();
        }
    }

    public <T extends NativeModule> boolean hasModule(Class<T> moduleInterface) {
        return this.mModules.containsKey(moduleInterface);
    }

    public <T extends NativeModule> T getModule(Class<T> moduleInterface) {
        return ((ModuleHolder) Assertions.assertNotNull(this.mModules.get(moduleInterface))).getModule();
    }

    public List<NativeModule> getAllModules() {
        List<NativeModule> modules = new ArrayList<>();
        for (ModuleHolder module : this.mModules.values()) {
            modules.add(module.getModule());
        }
        return modules;
    }
}
