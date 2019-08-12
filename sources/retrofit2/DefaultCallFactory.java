package retrofit2;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.ResponseBody;

class DefaultCallFactory<T> implements CallFactory<T> {
    private final ServiceMethod<T> serviceMethod;

    public DefaultCallFactory(ServiceMethod<T> serviceMethod2) {
        this.serviceMethod = serviceMethod2;
    }

    public Call create(Object... args) throws IOException {
        return this.serviceMethod.callFactory.newCall(this.serviceMethod.toRequest(args));
    }

    public T toResponse(ResponseBody body) throws IOException {
        return this.serviceMethod.toResponse(body);
    }
}
