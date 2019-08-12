package com.facebook.react.cxxbridge;

import android.content.res.AssetManager;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ExecutorToken;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.JavaScriptModuleRegistry;
import com.facebook.react.bridge.MemoryPressure;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.bridge.Systrace;
import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.bridge.queue.ReactQueueConfigurationImpl;
import com.facebook.react.bridge.queue.ReactQueueConfigurationSpec;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.soloader.SoLoader;
import com.facebook.systrace.TraceListener;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@DoNotStrip
public class CatalystInstanceImpl implements CatalystInstance {
    static final String REACT_NATIVE_LIB = "reactnativejnifb";
    private static final AtomicInteger sNextInstanceIdForTrace = new AtomicInteger(1);
    private volatile boolean mAcceptCalls;
    private final CopyOnWriteArrayList<NotThreadSafeBridgeIdleDebugListener> mBridgeIdleListeners;
    private volatile boolean mDestroyed;
    private final HybridData mHybridData;
    private boolean mInitialized;
    private boolean mJSBundleHasLoaded;
    private final JSBundleLoader mJSBundleLoader;
    private final ArrayList<PendingJSCall> mJSCallsPendingInit;
    private final Object mJSCallsPendingInitLock;
    private final JavaScriptModuleRegistry mJSModuleRegistry;
    /* access modifiers changed from: private */
    public final NativeModuleRegistry mJavaRegistry;
    private final String mJsPendingCallsTitleForTrace;
    private ExecutorToken mMainExecutorToken;
    private final NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    private final AtomicInteger mPendingJSCalls;
    private final ReactQueueConfigurationImpl mReactQueueConfiguration;
    private final TraceListener mTraceListener;

    private static class BridgeCallback implements ReactCallback {
        private final WeakReference<CatalystInstanceImpl> mOuter;

        public BridgeCallback(CatalystInstanceImpl outer) {
            this.mOuter = new WeakReference<>(outer);
        }

        public void onBatchComplete() {
            CatalystInstanceImpl impl = (CatalystInstanceImpl) this.mOuter.get();
            if (impl != null) {
                impl.mJavaRegistry.onBatchComplete();
            }
        }

        public void incrementPendingJSCalls() {
            CatalystInstanceImpl impl = (CatalystInstanceImpl) this.mOuter.get();
            if (impl != null) {
                impl.incrementPendingJSCalls();
            }
        }

        public void decrementPendingJSCalls() {
            CatalystInstanceImpl impl = (CatalystInstanceImpl) this.mOuter.get();
            if (impl != null) {
                impl.decrementPendingJSCalls();
            }
        }

        public void onNativeException(Exception e) {
            CatalystInstanceImpl impl = (CatalystInstanceImpl) this.mOuter.get();
            if (impl != null) {
                impl.onNativeException(e);
            }
        }
    }

    public static class Builder {
        private JSBundleLoader mJSBundleLoader;
        private JavaScriptExecutor mJSExecutor;
        private JavaScriptModuleRegistry mJSModuleRegistry;
        private NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
        private ReactQueueConfigurationSpec mReactQueueConfigurationSpec;
        private NativeModuleRegistry mRegistry;

        public Builder setReactQueueConfigurationSpec(ReactQueueConfigurationSpec ReactQueueConfigurationSpec) {
            this.mReactQueueConfigurationSpec = ReactQueueConfigurationSpec;
            return this;
        }

        public Builder setRegistry(NativeModuleRegistry registry) {
            this.mRegistry = registry;
            return this;
        }

        public Builder setJSModuleRegistry(JavaScriptModuleRegistry jsModuleRegistry) {
            this.mJSModuleRegistry = jsModuleRegistry;
            return this;
        }

        public Builder setJSBundleLoader(JSBundleLoader jsBundleLoader) {
            this.mJSBundleLoader = jsBundleLoader;
            return this;
        }

        public Builder setJSExecutor(JavaScriptExecutor jsExecutor) {
            this.mJSExecutor = jsExecutor;
            return this;
        }

        public Builder setNativeModuleCallExceptionHandler(NativeModuleCallExceptionHandler handler) {
            this.mNativeModuleCallExceptionHandler = handler;
            return this;
        }

        public CatalystInstanceImpl build() {
            return new CatalystInstanceImpl((ReactQueueConfigurationSpec) Assertions.assertNotNull(this.mReactQueueConfigurationSpec), (JavaScriptExecutor) Assertions.assertNotNull(this.mJSExecutor), (NativeModuleRegistry) Assertions.assertNotNull(this.mRegistry), (JavaScriptModuleRegistry) Assertions.assertNotNull(this.mJSModuleRegistry), (JSBundleLoader) Assertions.assertNotNull(this.mJSBundleLoader), (NativeModuleCallExceptionHandler) Assertions.assertNotNull(this.mNativeModuleCallExceptionHandler));
        }
    }

    private static class JSProfilerTraceListener implements TraceListener {
        private final WeakReference<CatalystInstanceImpl> mOuter;

        public JSProfilerTraceListener(CatalystInstanceImpl outer) {
            this.mOuter = new WeakReference<>(outer);
        }

        public void onTraceStarted() {
            CatalystInstanceImpl impl = (CatalystInstanceImpl) this.mOuter.get();
            if (impl != null) {
                ((Systrace) impl.getJSModule(Systrace.class)).setEnabled(true);
            }
        }

        public void onTraceStopped() {
            CatalystInstanceImpl impl = (CatalystInstanceImpl) this.mOuter.get();
            if (impl != null) {
                ((Systrace) impl.getJSModule(Systrace.class)).setEnabled(false);
            }
        }
    }

    private class NativeExceptionHandler implements QueueThreadExceptionHandler {
        private NativeExceptionHandler() {
        }

        public void handleException(Exception e) {
            CatalystInstanceImpl.this.onNativeException(e);
        }
    }

    private static class PendingJSCall {
        public NativeArray mArguments;
        public ExecutorToken mExecutorToken;
        public String mMethod;
        public String mModule;

        public PendingJSCall(ExecutorToken executorToken, String module, String method, NativeArray arguments) {
            this.mExecutorToken = executorToken;
            this.mModule = module;
            this.mMethod = method;
            this.mArguments = arguments;
        }
    }

    private native void callJSCallback(ExecutorToken executorToken, int i, NativeArray nativeArray);

    private native void callJSFunction(ExecutorToken executorToken, String str, String str2, NativeArray nativeArray);

    private native ExecutorToken getMainExecutorToken();

    private native void handleMemoryPressureCritical();

    private native void handleMemoryPressureModerate();

    private native void handleMemoryPressureUiHidden();

    private static native HybridData initHybrid();

    private native void initializeBridge(ReactCallback reactCallback, JavaScriptExecutor javaScriptExecutor, MessageQueueThread messageQueueThread, MessageQueueThread messageQueueThread2, ModuleRegistryHolder moduleRegistryHolder);

    public native long getJavaScriptContext();

    /* access modifiers changed from: 0000 */
    public native void loadScriptFromAssets(AssetManager assetManager, String str);

    /* access modifiers changed from: 0000 */
    public native void loadScriptFromFile(String str, String str2);

    /* access modifiers changed from: 0000 */
    public native void loadScriptFromOptimizedBundle(String str, String str2, int i);

    public native void setGlobalVariable(String str, String str2);

    public native void startProfiler(String str);

    public native void stopProfiler(String str, String str2);

    public native boolean supportsProfiling();

    static {
        SoLoader.loadLibrary(REACT_NATIVE_LIB);
    }

    private CatalystInstanceImpl(ReactQueueConfigurationSpec ReactQueueConfigurationSpec, JavaScriptExecutor jsExecutor, NativeModuleRegistry registry, JavaScriptModuleRegistry jsModuleRegistry, JSBundleLoader jsBundleLoader, NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler) {
        this.mPendingJSCalls = new AtomicInteger(0);
        this.mJsPendingCallsTitleForTrace = "pending_js_calls_instance" + sNextInstanceIdForTrace.getAndIncrement();
        this.mDestroyed = false;
        this.mJSCallsPendingInit = new ArrayList<>();
        this.mJSCallsPendingInitLock = new Object();
        this.mInitialized = false;
        this.mAcceptCalls = false;
        FLog.m1795d(ReactConstants.TAG, "Initializing React Xplat Bridge.");
        this.mHybridData = initHybrid();
        this.mReactQueueConfiguration = ReactQueueConfigurationImpl.create(ReactQueueConfigurationSpec, new NativeExceptionHandler());
        this.mBridgeIdleListeners = new CopyOnWriteArrayList<>();
        this.mJavaRegistry = registry;
        this.mJSModuleRegistry = jsModuleRegistry;
        this.mJSBundleLoader = jsBundleLoader;
        this.mNativeModuleCallExceptionHandler = nativeModuleCallExceptionHandler;
        this.mTraceListener = new JSProfilerTraceListener(this);
        initializeBridge(new BridgeCallback(this), jsExecutor, this.mReactQueueConfiguration.getJSQueueThread(), this.mReactQueueConfiguration.getNativeModulesQueueThread(), this.mJavaRegistry.getModuleRegistryHolder(this));
        this.mMainExecutorToken = getMainExecutorToken();
    }

    public void runJSBundle() {
        Assertions.assertCondition(!this.mJSBundleHasLoaded, "JS bundle was already loaded!");
        this.mJSBundleHasLoaded = true;
        this.mJSBundleLoader.loadScript(this);
        synchronized (this.mJSCallsPendingInitLock) {
            this.mAcceptCalls = true;
            Iterator it = this.mJSCallsPendingInit.iterator();
            while (it.hasNext()) {
                PendingJSCall call = (PendingJSCall) it.next();
                callJSFunction(call.mExecutorToken, call.mModule, call.mMethod, call.mArguments);
            }
            this.mJSCallsPendingInit.clear();
        }
        com.facebook.systrace.Systrace.registerListener(this.mTraceListener);
    }

    public void callFunction(ExecutorToken executorToken, String module, String method, NativeArray arguments) {
        if (this.mDestroyed) {
            FLog.m1847w(ReactConstants.TAG, "Calling JS function after bridge has been destroyed.");
            return;
        }
        if (!this.mAcceptCalls) {
            synchronized (this.mJSCallsPendingInitLock) {
                if (!this.mAcceptCalls) {
                    this.mJSCallsPendingInit.add(new PendingJSCall(executorToken, module, method, arguments));
                    return;
                }
            }
        }
        callJSFunction(executorToken, module, method, arguments);
    }

    public void invokeCallback(ExecutorToken executorToken, int callbackID, NativeArray arguments) {
        if (this.mDestroyed) {
            FLog.m1847w(ReactConstants.TAG, "Invoking JS callback after bridge has been destroyed.");
        } else {
            callJSCallback(executorToken, callbackID, arguments);
        }
    }

    public void destroy() {
        boolean wasIdle = true;
        UiThreadUtil.assertOnUiThread();
        if (!this.mDestroyed) {
            this.mDestroyed = true;
            this.mHybridData.resetNative();
            this.mJavaRegistry.notifyCatalystInstanceDestroy();
            if (this.mPendingJSCalls.getAndSet(0) != 0) {
                wasIdle = false;
            }
            if (!wasIdle && !this.mBridgeIdleListeners.isEmpty()) {
                Iterator it = this.mBridgeIdleListeners.iterator();
                while (it.hasNext()) {
                    ((NotThreadSafeBridgeIdleDebugListener) it.next()).onTransitionToBridgeIdle();
                }
            }
            com.facebook.systrace.Systrace.unregisterListener(this.mTraceListener);
        }
    }

    public boolean isDestroyed() {
        return this.mDestroyed;
    }

    @VisibleForTesting
    public void initialize() {
        UiThreadUtil.assertOnUiThread();
        Assertions.assertCondition(!this.mInitialized, "This catalyst instance has already been initialized");
        Assertions.assertCondition(this.mAcceptCalls, "RunJSBundle hasn't completed.");
        this.mInitialized = true;
        this.mJavaRegistry.notifyCatalystInstanceInitialized();
    }

    public ReactQueueConfiguration getReactQueueConfiguration() {
        return this.mReactQueueConfiguration;
    }

    public <T extends JavaScriptModule> T getJSModule(Class<T> jsInterface) {
        return getJSModule(this.mMainExecutorToken, jsInterface);
    }

    public <T extends JavaScriptModule> T getJSModule(ExecutorToken executorToken, Class<T> jsInterface) {
        return ((JavaScriptModuleRegistry) Assertions.assertNotNull(this.mJSModuleRegistry)).getJavaScriptModule(this, executorToken, jsInterface);
    }

    public <T extends NativeModule> boolean hasNativeModule(Class<T> nativeModuleInterface) {
        return this.mJavaRegistry.hasModule(nativeModuleInterface);
    }

    public <T extends NativeModule> T getNativeModule(Class<T> nativeModuleInterface) {
        return this.mJavaRegistry.getModule(nativeModuleInterface);
    }

    public Collection<NativeModule> getNativeModules() {
        return this.mJavaRegistry.getAllModules();
    }

    public void handleMemoryPressure(MemoryPressure level) {
        if (!this.mDestroyed) {
            switch (level) {
                case UI_HIDDEN:
                    handleMemoryPressureUiHidden();
                    return;
                case MODERATE:
                    handleMemoryPressureModerate();
                    return;
                case CRITICAL:
                    handleMemoryPressureCritical();
                    return;
                default:
                    return;
            }
        }
    }

    public void addBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener listener) {
        this.mBridgeIdleListeners.add(listener);
    }

    public void removeBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener listener) {
        this.mBridgeIdleListeners.remove(listener);
    }

    /* access modifiers changed from: private */
    public void incrementPendingJSCalls() {
        int oldPendingCalls = this.mPendingJSCalls.getAndIncrement();
        boolean wasIdle = oldPendingCalls == 0;
        com.facebook.systrace.Systrace.traceCounter(0, this.mJsPendingCallsTitleForTrace, oldPendingCalls + 1);
        if (wasIdle && !this.mBridgeIdleListeners.isEmpty()) {
            Iterator it = this.mBridgeIdleListeners.iterator();
            while (it.hasNext()) {
                ((NotThreadSafeBridgeIdleDebugListener) it.next()).onTransitionToBridgeBusy();
            }
        }
    }

    /* access modifiers changed from: private */
    public void decrementPendingJSCalls() {
        int newPendingCalls = this.mPendingJSCalls.decrementAndGet();
        boolean isNowIdle = newPendingCalls == 0;
        com.facebook.systrace.Systrace.traceCounter(0, this.mJsPendingCallsTitleForTrace, newPendingCalls);
        if (isNowIdle && !this.mBridgeIdleListeners.isEmpty()) {
            Iterator it = this.mBridgeIdleListeners.iterator();
            while (it.hasNext()) {
                ((NotThreadSafeBridgeIdleDebugListener) it.next()).onTransitionToBridgeIdle();
            }
        }
    }

    /* access modifiers changed from: private */
    public void onNativeException(Exception e) {
        this.mNativeModuleCallExceptionHandler.handleException(e);
        this.mReactQueueConfiguration.getUIQueueThread().runOnQueue(new Runnable() {
            public void run() {
                CatalystInstanceImpl.this.destroy();
            }
        });
    }
}
