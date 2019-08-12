package com.facebook.react;

import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.bridge.Systrace;
import com.facebook.react.devsupport.HMRClient;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.modules.core.ExceptionsManagerModule;
import com.facebook.react.modules.core.HeadlessJsTaskSupportModule;
import com.facebook.react.modules.core.JSTimersExecution;
import com.facebook.react.modules.core.RCTNativeAppEventEmitter;
import com.facebook.react.modules.core.Timing;
import com.facebook.react.modules.debug.AnimationsDebugModule;
import com.facebook.react.modules.debug.SourceCodeModule;
import com.facebook.react.modules.systeminfo.AndroidInfoModule;
import com.facebook.react.uimanager.AppRegistry;
import com.facebook.react.uimanager.UIImplementationProvider;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Provider;

class CoreModulesPackage extends LazyReactPackage {
    /* access modifiers changed from: private */
    public final DefaultHardwareBackBtnHandler mHardwareBackBtnHandler;
    /* access modifiers changed from: private */
    public final ReactInstanceManager mReactInstanceManager;
    private final UIImplementationProvider mUIImplementationProvider;

    CoreModulesPackage(ReactInstanceManager reactInstanceManager, DefaultHardwareBackBtnHandler hardwareBackBtnHandler, UIImplementationProvider uiImplementationProvider) {
        this.mReactInstanceManager = reactInstanceManager;
        this.mHardwareBackBtnHandler = hardwareBackBtnHandler;
        this.mUIImplementationProvider = uiImplementationProvider;
    }

    public List<ModuleSpec> getNativeModules(final ReactApplicationContext reactContext) {
        List<ModuleSpec> moduleSpecList = new ArrayList<>();
        moduleSpecList.add(new ModuleSpec(AndroidInfoModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new AndroidInfoModule();
            }
        }));
        moduleSpecList.add(new ModuleSpec(AnimationsDebugModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new AnimationsDebugModule(reactContext, CoreModulesPackage.this.mReactInstanceManager.getDevSupportManager().getDevSettings());
            }
        }));
        moduleSpecList.add(new ModuleSpec(DeviceEventManagerModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new DeviceEventManagerModule(reactContext, CoreModulesPackage.this.mHardwareBackBtnHandler);
            }
        }));
        moduleSpecList.add(new ModuleSpec(ExceptionsManagerModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new ExceptionsManagerModule(CoreModulesPackage.this.mReactInstanceManager.getDevSupportManager());
            }
        }));
        moduleSpecList.add(new ModuleSpec(HeadlessJsTaskSupportModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new HeadlessJsTaskSupportModule(reactContext);
            }
        }));
        moduleSpecList.add(new ModuleSpec(SourceCodeModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new SourceCodeModule(CoreModulesPackage.this.mReactInstanceManager.getSourceUrl());
            }
        }));
        moduleSpecList.add(new ModuleSpec(Timing.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return new Timing(reactContext, CoreModulesPackage.this.mReactInstanceManager.getDevSupportManager());
            }
        }));
        moduleSpecList.add(new ModuleSpec(UIManagerModule.class, new Provider<NativeModule>() {
            public NativeModule get() {
                return CoreModulesPackage.this.createUIManager(reactContext);
            }
        }));
        return moduleSpecList;
    }

    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return new ArrayList<>(Arrays.asList(new Class[]{RCTDeviceEventEmitter.class, JSTimersExecution.class, RCTEventEmitter.class, RCTNativeAppEventEmitter.class, AppRegistry.class, Systrace.class, HMRClient.class}));
    }

    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return LazyReactPackage.getReactModuleInfoProviderViaReflection(this);
    }

    /* access modifiers changed from: private */
    public UIManagerModule createUIManager(ReactApplicationContext reactContext) {
        ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_START);
        com.facebook.systrace.Systrace.beginSection(0, "createUIManagerModule");
        try {
            return new UIManagerModule(reactContext, this.mReactInstanceManager.createAllViewManagers(reactContext), this.mUIImplementationProvider);
        } finally {
            com.facebook.systrace.Systrace.endSection(0);
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_UI_MANAGER_MODULE_END);
        }
    }
}
