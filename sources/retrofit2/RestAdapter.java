package retrofit2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

public final class RestAdapter {
    private final Retrofit retrofit;
    private final Map<Method, ServiceMethod> serviceMethodCache = new LinkedHashMap();
    private final boolean validateEagerly;

    public RestAdapter(Retrofit retrofit3, boolean validateEagerly2) {
        this.retrofit = retrofit3;
        this.validateEagerly = validateEagerly2;
    }

    public <T> T create(final Class<T> service) {
        Utils.validateServiceInterface(service);
        if (this.validateEagerly) {
            eagerlyValidateMethods(service);
        }
        return Proxy.newProxyInstance(service.getClassLoader(), new Class[]{service}, new InvocationHandler() {
            private final Platform platform = Platform.get();

            public Object invoke(Object proxy, Method method, Object... args) throws Throwable {
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, args);
                }
                if (this.platform.isDefaultMethod(method)) {
                    return this.platform.invokeDefaultMethod(method, service, proxy, args);
                }
                ServiceMethod serviceMethod = RestAdapter.this.loadServiceMethod(method);
                return serviceMethod.callAdapter.adapt(new OkHttpCall(new DefaultCallFactory(serviceMethod), args));
            }
        });
    }

    private void eagerlyValidateMethods(Class<?> service) {
        Method[] declaredMethods;
        Platform platform = Platform.get();
        for (Method method : service.getDeclaredMethods()) {
            if (!platform.isDefaultMethod(method)) {
                loadServiceMethod(method);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod result;
        synchronized (this.serviceMethodCache) {
            result = (ServiceMethod) this.serviceMethodCache.get(method);
            if (result == null) {
                result = new Builder(this.retrofit, method).build();
                this.serviceMethodCache.put(method, result);
            }
        }
        return result;
    }
}
