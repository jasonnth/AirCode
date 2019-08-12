package com.airbnb.airrequest;

import android.content.Context;
import com.airbnb.airrequest.Transformer.Factory;
import com.airbnb.rxgroups.RequestSubscription;
import java.io.IOException;
import java.lang.annotation.Annotation;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import p032rx.Observable;
import retrofit2.Converter;
import retrofit2.Response;

public abstract class MockRequest<T> extends DebugOnlyRequest<T> {

    public static class TransformerFactory implements Factory {
        /* access modifiers changed from: private */
        public final Context context;

        public TransformerFactory(Context context2) {
            this.context = context2;
        }

        public Transformer<?> transformerFor(AirRequest request, final AirRequestInitializer initializer) {
            if (!(request instanceof MockRequest)) {
                return null;
            }
            final MockRequest<?> mockRequest = (MockRequest) request;
            return new Transformer<Object>() {
                public Observable<AirResponse<Object>> call(Observable<AirResponse<Object>> observable) {
                    return Observable.just(new AirResponse(mockRequest, Response.success(mockRequest.convert(initializer, mockRequest.assetContents(TransformerFactory.this.context)))));
                }
            };
        }
    }

    /* access modifiers changed from: protected */
    public abstract String responseJsonFile();

    public /* bridge */ /* synthetic */ RequestSubscription execute(RequestExecutor requestExecutor) {
        return super.execute(requestExecutor);
    }

    public String getPath() {
        return "doesnt-matter";
    }

    private Converter<ResponseBody, Object> converter(AirRequestInitializer initializer) {
        return initializer.retrofit().responseBodyConverter(successResponseType(), new Annotation[0]);
    }

    /* access modifiers changed from: private */
    public T convert(AirRequestInitializer initializer, byte[] content) {
        try {
            return converter(initializer).convert(ResponseBody.create(MediaType.parse("application/json"), content));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public byte[] assetContents(Context context) {
        return Utils.getAssetAsByteArray(context, responseJsonFile());
    }
}
