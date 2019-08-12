package com.facebook.react;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.cxxbridge.JSBundleLoader;
import com.facebook.react.devsupport.DevSupportManager;
import com.facebook.react.devsupport.RedBoxHandler;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.uimanager.UIImplementationProvider;
import com.facebook.react.uimanager.ViewManager;
import java.util.ArrayList;
import java.util.List;

public abstract class ReactInstanceManager {

    public static class Builder {
        protected Application mApplication;
        protected NotThreadSafeBridgeIdleDebugListener mBridgeIdleDebugListener;
        protected Activity mCurrentActivity;
        protected DefaultHardwareBackBtnHandler mDefaultHardwareBackBtnHandler;
        protected LifecycleState mInitialLifecycleState;
        protected String mJSBundleAssetUrl;
        protected JSBundleLoader mJSBundleLoader;
        protected JSCConfig mJSCConfig = JSCConfig.EMPTY;
        protected String mJSMainModuleName;
        protected boolean mLazyNativeModulesEnabled;
        protected boolean mLazyViewManagersEnabled;
        protected boolean mManuallyEnableDevSupport;
        protected NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
        protected final List<ReactPackage> mPackages = new ArrayList();
        protected RedBoxHandler mRedBoxHandler;
        protected UIImplementationProvider mUIImplementationProvider;
        protected boolean mUseDeveloperSupport;

        protected Builder() {
        }

        public Builder setUIImplementationProvider(UIImplementationProvider uiImplementationProvider) {
            this.mUIImplementationProvider = uiImplementationProvider;
            return this;
        }

        public Builder setBundleAssetName(String bundleAssetName) {
            this.mJSBundleAssetUrl = bundleAssetName == null ? null : "assets://" + bundleAssetName;
            this.mJSBundleLoader = null;
            return this;
        }

        /* Debug info: failed to restart local var, previous not found, register: 1 */
        public Builder setJSBundleFile(String jsBundleFile) {
            if (!jsBundleFile.startsWith("assets://")) {
                return setJSBundleLoader(JSBundleLoader.createFileLoader(jsBundleFile));
            }
            this.mJSBundleAssetUrl = jsBundleFile;
            this.mJSBundleLoader = null;
            return this;
        }

        public Builder setJSBundleLoader(JSBundleLoader jsBundleLoader) {
            this.mJSBundleLoader = jsBundleLoader;
            this.mJSBundleAssetUrl = null;
            return this;
        }

        public Builder setJSMainModuleName(String jsMainModuleName) {
            this.mJSMainModuleName = jsMainModuleName;
            return this;
        }

        public Builder addPackage(ReactPackage reactPackage) {
            this.mPackages.add(reactPackage);
            return this;
        }

        public Builder setBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener bridgeIdleDebugListener) {
            this.mBridgeIdleDebugListener = bridgeIdleDebugListener;
            return this;
        }

        public Builder setApplication(Application application) {
            this.mApplication = application;
            return this;
        }

        public Builder setCurrentActivity(Activity activity) {
            this.mCurrentActivity = activity;
            return this;
        }

        public Builder setDefaultHardwareBackBtnHandler(DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler) {
            this.mDefaultHardwareBackBtnHandler = defaultHardwareBackBtnHandler;
            return this;
        }

        public Builder setUseDeveloperSupport(boolean useDeveloperSupport) {
            this.mUseDeveloperSupport = useDeveloperSupport;
            return this;
        }

        public Builder setInitialLifecycleState(LifecycleState initialLifecycleState) {
            this.mInitialLifecycleState = initialLifecycleState;
            return this;
        }

        public Builder setNativeModuleCallExceptionHandler(NativeModuleCallExceptionHandler handler) {
            this.mNativeModuleCallExceptionHandler = handler;
            return this;
        }

        public Builder setJSCConfig(JSCConfig jscConfig) {
            this.mJSCConfig = jscConfig;
            return this;
        }

        public Builder setRedBoxHandler(RedBoxHandler redBoxHandler) {
            this.mRedBoxHandler = redBoxHandler;
            return this;
        }

        public Builder setLazyNativeModulesEnabled(boolean lazyNativeModulesEnabled) {
            this.mLazyNativeModulesEnabled = lazyNativeModulesEnabled;
            return this;
        }

        public Builder setLazyViewManagersEnabled(boolean lazyViewManagersEnabled) {
            this.mLazyViewManagersEnabled = lazyViewManagersEnabled;
            return this;
        }

        public Builder manuallyEnableDevSupport() {
            this.mManuallyEnableDevSupport = true;
            return this;
        }

        public ReactInstanceManager build() {
            Assertions.assertNotNull(this.mApplication, "Application property has not been set with this builder");
            Assertions.assertCondition((!this.mUseDeveloperSupport && this.mJSBundleAssetUrl == null && this.mJSBundleLoader == null) ? false : true, "JS Bundle File or Asset URL has to be provided when dev support is disabled");
            Assertions.assertCondition((this.mJSMainModuleName == null && this.mJSBundleAssetUrl == null && this.mJSBundleLoader == null) ? false : true, "Either MainModuleName or JS Bundle File needs to be provided");
            if (this.mUIImplementationProvider == null) {
                this.mUIImplementationProvider = new UIImplementationProvider();
            }
            return new XReactInstanceManagerImpl(this.mApplication, this.mCurrentActivity, this.mDefaultHardwareBackBtnHandler, (this.mJSBundleLoader != null || this.mJSBundleAssetUrl == null) ? this.mJSBundleLoader : JSBundleLoader.createAssetLoader(this.mApplication, this.mJSBundleAssetUrl), this.mJSMainModuleName, this.mPackages, this.mUseDeveloperSupport, this.mBridgeIdleDebugListener, (LifecycleState) Assertions.assertNotNull(this.mInitialLifecycleState, "Initial lifecycle state was not set"), this.mUIImplementationProvider, this.mNativeModuleCallExceptionHandler, this.mJSCConfig, this.mRedBoxHandler, this.mLazyNativeModulesEnabled, this.mLazyViewManagersEnabled, this.mManuallyEnableDevSupport);
        }
    }

    public interface ReactInstanceEventListener {
        void onReactContextInitialized(ReactContext reactContext);
    }

    public abstract void addReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener);

    public abstract void attachMeasuredRootView(ReactRootView reactRootView);

    public abstract List<ViewManager> createAllViewManagers(ReactApplicationContext reactApplicationContext);

    public abstract void createReactContextInBackground();

    public abstract void destroy();

    public abstract void detachRootView(ReactRootView reactRootView);

    @VisibleForTesting
    public abstract ReactContext getCurrentReactContext();

    public abstract DevSupportManager getDevSupportManager();

    public abstract String getJSBundleFile();

    public abstract LifecycleState getLifecycleState();

    public abstract MemoryPressureRouter getMemoryPressureRouter();

    public abstract String getSourceUrl();

    public abstract boolean hasStartedCreatingInitialContext();

    public abstract void onActivityResult(Activity activity, int i, int i2, Intent intent);

    public abstract void onBackPressed();

    @Deprecated
    public abstract void onHostDestroy();

    public abstract void onHostDestroy(Activity activity);

    @Deprecated
    public abstract void onHostPause();

    public abstract void onHostPause(Activity activity);

    public abstract void onHostResume(Activity activity, DefaultHardwareBackBtnHandler defaultHardwareBackBtnHandler);

    public abstract void onNewIntent(Intent intent);

    public abstract void removeReactInstanceEventListener(ReactInstanceEventListener reactInstanceEventListener);

    public abstract void showDevOptionsDialog();

    public static Builder builder() {
        return new Builder();
    }
}
