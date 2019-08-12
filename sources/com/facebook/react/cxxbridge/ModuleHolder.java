package com.facebook.react.cxxbridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.common.futures.SimpleSettableFuture;
import com.facebook.react.module.model.Info;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import com.facebook.systrace.SystraceMessage.Builder;
import java.util.concurrent.ExecutionException;
import javax.inject.Provider;

public class ModuleHolder {
    private final Info mInfo;
    private boolean mInitializeNeeded;
    private NativeModule mModule;
    private Provider<? extends NativeModule> mProvider;

    private class LegacyModuleInfo implements Info {
        public final Class<?> mType;

        public LegacyModuleInfo(Class<?> type) {
            this.mType = type;
        }

        public String name() {
            return ModuleHolder.this.getModule().getName();
        }

        public boolean canOverrideExistingModule() {
            return ModuleHolder.this.getModule().canOverrideExistingModule();
        }

        public boolean supportsWebWorkers() {
            return ModuleHolder.this.getModule().supportsWebWorkers();
        }

        public boolean needsEagerInit() {
            return true;
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=com.facebook.react.module.model.ReactModuleInfo, code=com.facebook.react.module.model.Info, for r3v0, types: [com.facebook.react.module.model.Info, com.facebook.react.module.model.ReactModuleInfo] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ModuleHolder(java.lang.Class<? extends com.facebook.react.bridge.NativeModule> r2, com.facebook.react.module.model.Info r3, javax.inject.Provider<? extends com.facebook.react.bridge.NativeModule> r4) {
        /*
            r1 = this;
            r1.<init>()
            if (r3 != 0) goto L_0x000a
            com.facebook.react.cxxbridge.ModuleHolder$LegacyModuleInfo r3 = new com.facebook.react.cxxbridge.ModuleHolder$LegacyModuleInfo
            r3.<init>(r2)
        L_0x000a:
            r1.mInfo = r3
            r1.mProvider = r4
            com.facebook.react.module.model.Info r0 = r1.mInfo
            boolean r0 = r0.needsEagerInit()
            if (r0 == 0) goto L_0x001c
            com.facebook.react.bridge.NativeModule r0 = r1.doCreate()
            r1.mModule = r0
        L_0x001c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.react.cxxbridge.ModuleHolder.<init>(java.lang.Class, com.facebook.react.module.model.ReactModuleInfo, javax.inject.Provider):void");
    }

    public synchronized void initialize() {
        if (this.mModule != null) {
            doInitialize(this.mModule);
        } else {
            this.mInitializeNeeded = true;
        }
    }

    public synchronized void destroy() {
        if (this.mModule != null) {
            this.mModule.onCatalystInstanceDestroy();
        }
    }

    public Info getInfo() {
        return this.mInfo;
    }

    public synchronized NativeModule getModule() {
        if (this.mModule == null) {
            this.mModule = doCreate();
        }
        return this.mModule;
    }

    private NativeModule doCreate() {
        NativeModule module = create();
        this.mProvider = null;
        return module;
    }

    private NativeModule create() {
        boolean isEagerModule = this.mInfo instanceof LegacyModuleInfo;
        String name = isEagerModule ? ((LegacyModuleInfo) this.mInfo).mType.getSimpleName() : this.mInfo.name();
        if (!isEagerModule) {
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_START);
        }
        SystraceMessage.beginSection(0, "createModule").arg("name", (Object) name).flush();
        NativeModule module = (NativeModule) ((Provider) Assertions.assertNotNull(this.mProvider)).get();
        if (this.mInitializeNeeded) {
            doInitialize(module);
            this.mInitializeNeeded = false;
        }
        Systrace.endSection(0);
        if (!isEagerModule) {
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
        }
        return module;
    }

    private void doInitialize(NativeModule module) {
        Builder section = SystraceMessage.beginSection(0, "initialize");
        if (module instanceof CxxModuleWrapper) {
            section.arg("className", (Object) module.getClass().getSimpleName());
        } else {
            section.arg("name", (Object) this.mInfo.name());
        }
        section.flush();
        callInitializeOnUiThread(module);
        Systrace.endSection(0);
    }

    private static void callInitializeOnUiThread(final NativeModule module) {
        if (UiThreadUtil.isOnUiThread()) {
            module.initialize();
            return;
        }
        final SimpleSettableFuture<Void> future = new SimpleSettableFuture<>();
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                Systrace.beginSection(0, "initializeOnUiThread");
                try {
                    module.initialize();
                    future.set(null);
                } catch (Exception e) {
                    future.setException(e);
                }
                Systrace.endSection(0);
            }
        });
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
