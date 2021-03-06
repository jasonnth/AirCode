package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.common.ReactConstants;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.WeakHashMap;

public class JavaScriptModuleRegistry {
    private final WeakHashMap<ExecutorToken, HashMap<Class<? extends JavaScriptModule>, JavaScriptModule>> mModuleInstances = new WeakHashMap<>();
    private final HashMap<Class<? extends JavaScriptModule>, JavaScriptModuleRegistration> mModuleRegistrations = new HashMap<>();

    public static class Builder {
        private List<JavaScriptModuleRegistration> mModules = new ArrayList();

        public Builder add(Class<? extends JavaScriptModule> moduleInterfaceClass) {
            this.mModules.add(new JavaScriptModuleRegistration(moduleInterfaceClass));
            return this;
        }

        public JavaScriptModuleRegistry build() {
            return new JavaScriptModuleRegistry(this.mModules);
        }
    }

    private static class JavaScriptModuleInvocationHandler implements InvocationHandler {
        private final CatalystInstance mCatalystInstance;
        private final WeakReference<ExecutorToken> mExecutorToken;
        private final JavaScriptModuleRegistration mModuleRegistration;

        public JavaScriptModuleInvocationHandler(ExecutorToken executorToken, CatalystInstance catalystInstance, JavaScriptModuleRegistration moduleRegistration) {
            this.mExecutorToken = new WeakReference<>(executorToken);
            this.mCatalystInstance = catalystInstance;
            this.mModuleRegistration = moduleRegistration;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            ExecutorToken executorToken = (ExecutorToken) this.mExecutorToken.get();
            if (executorToken == null) {
                FLog.m1847w(ReactConstants.TAG, "Dropping JS call, ExecutorToken went away...");
            } else {
                this.mCatalystInstance.callFunction(executorToken, this.mModuleRegistration.getName(), method.getName(), args != null ? Arguments.fromJavaArgs(args) : new WritableNativeArray());
            }
            return null;
        }
    }

    public JavaScriptModuleRegistry(List<JavaScriptModuleRegistration> config) {
        for (JavaScriptModuleRegistration registration : config) {
            this.mModuleRegistrations.put(registration.getModuleInterface(), registration);
        }
    }

    public synchronized <T extends JavaScriptModule> T getJavaScriptModule(CatalystInstance instance, ExecutorToken executorToken, Class<T> moduleInterface) {
        JavaScriptModule module;
        HashMap<Class<? extends JavaScriptModule>, JavaScriptModule> instancesForContext = (HashMap) this.mModuleInstances.get(executorToken);
        if (instancesForContext == null) {
            instancesForContext = new HashMap<>();
            this.mModuleInstances.put(executorToken, instancesForContext);
        }
        module = (JavaScriptModule) instancesForContext.get(moduleInterface);
        if (module == null) {
            JavaScriptModuleRegistration registration = (JavaScriptModuleRegistration) Assertions.assertNotNull(this.mModuleRegistrations.get(moduleInterface), "JS module " + moduleInterface.getSimpleName() + " hasn't been registered!");
            JavaScriptModule interfaceProxy = (JavaScriptModule) Proxy.newProxyInstance(moduleInterface.getClassLoader(), new Class[]{moduleInterface}, new JavaScriptModuleInvocationHandler(executorToken, instance, registration));
            instancesForContext.put(moduleInterface, interfaceProxy);
            module = interfaceProxy;
        }
        return module;
    }
}
