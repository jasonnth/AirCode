package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.Call;
import okhttp3.ResponseBody;

class TypedRawCallFactory<T> implements CallFactory<T> {
    private final TypedRequest request;
    private final Converter<ResponseBody, T> responseConverter;
    private final Retrofit retrofit;

    TypedRawCallFactory(Retrofit retrofit3, Type responseType, TypedRequest request2) {
        this.retrofit = retrofit3;
        this.request = request2;
        this.responseConverter = retrofit3.responseBodyConverter(responseType, new Annotation[0]);
    }

    public Call create(Object... args) throws IOException {
        return this.retrofit.callFactory().newCall(new TypedRequestRawRequestBuilder(this.retrofit, this.request).build());
    }

    public T toResponse(ResponseBody body) throws IOException {
        return this.responseConverter.convert(body);
    }
}
