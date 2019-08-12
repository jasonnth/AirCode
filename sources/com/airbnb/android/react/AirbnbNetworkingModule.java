package com.airbnb.android.react;

import com.airbnb.android.core.requests.base.AirlockErrorHandler;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ExecutorToken;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.modules.network.NetworkingModule;
import com.facebook.react.modules.network.OkHttpClientProvider;
import com.facebook.react.modules.network.ResponseCallback;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import p032rx.Observer;
import p032rx.Single;
import retrofit2.Retrofit;

@ReactModule(canOverrideExistingModule = true, name = "RCTNetworking", supportsWebWorkers = true)
public final class AirbnbNetworkingModule extends ReactContextBaseJavaModule {
    private final AirlockErrorHandler airlockErrorHandler;
    private final OkHttpClient client;
    private final NetworkingModule delegate;
    private final ResponseMapper responseMapper;

    AirbnbNetworkingModule(Retrofit retrofit, ReactApplicationContext reactContext, OkHttpClient client2, AirlockErrorHandler airlockErrorHandler2, NetworkingModule delegate2) {
        super(reactContext);
        this.client = client2;
        this.airlockErrorHandler = airlockErrorHandler2;
        this.responseMapper = new ResponseMapper(retrofit);
        this.delegate = delegate2;
    }

    public AirbnbNetworkingModule(Retrofit retrofit, ReactApplicationContext context, AirlockErrorHandler airlockErrorHandler2, NetworkingModule delegate2) {
        this(retrofit, context, OkHttpClientProvider.getOkHttpClient(), airlockErrorHandler2, delegate2);
    }

    public void initialize() {
        this.delegate.initialize();
    }

    public boolean canOverrideExistingModule() {
        return true;
    }

    public void onCatalystInstanceDestroy() {
        this.delegate.onCatalystInstanceDestroy();
    }

    @ReactMethod
    public void sendRequest(ExecutorToken executorToken, String method, String url, int requestId, ReadableArray headers, ReadableMap data, String responseType, boolean useIncrementalUpdates, int timeout) {
        RCTDeviceEventEmitter eventEmitter = this.delegate.getEventEmitter(executorToken);
        Builder requestBuilder = this.delegate.buildRequest(executorToken, method, url, requestId, headers, data, eventEmitter);
        if (requestBuilder != null) {
            OkHttpClient buildOkHttpClient = this.delegate.buildOkHttpClient(responseType, useIncrementalUpdates, timeout, eventEmitter, requestId);
            this.delegate.addRequest(requestId);
            fireRequest(requestBuilder, new ResponseCallback(requestId, responseType, eventEmitter, useIncrementalUpdates, this.delegate));
        }
    }

    private void fireRequest(Builder requestBuilder, ResponseCallback callback) {
        Single.create(AirbnbNetworkingModule$$Lambda$1.lambdaFactory$(this, requestBuilder)).flatMap(this.responseMapper).doOnError(this.airlockErrorHandler).doOnError(AirbnbNetworkingModule$$Lambda$2.lambdaFactory$()).subscribe((Observer<? super T>) new DelegatingObserver<Object>(callback));
    }

    @ReactMethod
    public void abortRequest(ExecutorToken executorToken, int requestId) {
        this.delegate.abortRequest(executorToken, requestId);
    }

    @ReactMethod
    public void clearCookies(ExecutorToken executorToken, Callback callback) {
        this.delegate.clearCookies(executorToken, callback);
    }

    public String getName() {
        return "RCTNetworking";
    }

    public boolean supportsWebWorkers() {
        return true;
    }
}
