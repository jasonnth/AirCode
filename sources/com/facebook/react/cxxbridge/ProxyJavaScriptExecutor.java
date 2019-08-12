package com.facebook.react.cxxbridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.soloader.SoLoader;

@DoNotStrip
public class ProxyJavaScriptExecutor extends JavaScriptExecutor {
    private JavaJSExecutor mJavaJSExecutor;

    public static class Factory implements com.facebook.react.cxxbridge.JavaScriptExecutor.Factory {
        private final com.facebook.react.bridge.JavaJSExecutor.Factory mJavaJSExecutorFactory;

        public Factory(com.facebook.react.bridge.JavaJSExecutor.Factory javaJSExecutorFactory) {
            this.mJavaJSExecutorFactory = javaJSExecutorFactory;
        }

        public JavaScriptExecutor create() throws Exception {
            return new ProxyJavaScriptExecutor(this.mJavaJSExecutorFactory.create());
        }
    }

    private static native HybridData initHybrid(JavaJSExecutor javaJSExecutor);

    static {
        SoLoader.loadLibrary("reactnativejnifb");
    }

    public ProxyJavaScriptExecutor(JavaJSExecutor executor) {
        super(initHybrid(executor));
        this.mJavaJSExecutor = executor;
    }

    public void close() {
        if (this.mJavaJSExecutor != null) {
            this.mJavaJSExecutor.close();
            this.mJavaJSExecutor = null;
        }
    }
}
