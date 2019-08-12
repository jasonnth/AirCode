package com.airbnb.android.core.modules;

import com.airbnb.airrequest.BaseUrl;
import com.airbnb.android.core.data.ConverterFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Retrofit.Builder;

public final class NetworkModule_ProvideRetrofitBuilderFactory implements Factory<Builder> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideRetrofitBuilderFactory.class.desiredAssertionStatus());
    private final Provider<BaseUrl> baseUrlProvider;
    private final Provider<CallAdapter.Factory> callAdapterFactoryProvider;
    private final Provider<Executor> callbackExecutorProvider;
    private final Provider<OkHttpClient> clientProvider;
    private final Provider<ConverterFactory> converterFactoryProvider;
    private final NetworkModule module;

    public NetworkModule_ProvideRetrofitBuilderFactory(NetworkModule module2, Provider<OkHttpClient> clientProvider2, Provider<CallAdapter.Factory> callAdapterFactoryProvider2, Provider<Executor> callbackExecutorProvider2, Provider<ConverterFactory> converterFactoryProvider2, Provider<BaseUrl> baseUrlProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || clientProvider2 != null) {
                this.clientProvider = clientProvider2;
                if ($assertionsDisabled || callAdapterFactoryProvider2 != null) {
                    this.callAdapterFactoryProvider = callAdapterFactoryProvider2;
                    if ($assertionsDisabled || callbackExecutorProvider2 != null) {
                        this.callbackExecutorProvider = callbackExecutorProvider2;
                        if ($assertionsDisabled || converterFactoryProvider2 != null) {
                            this.converterFactoryProvider = converterFactoryProvider2;
                            if ($assertionsDisabled || baseUrlProvider2 != null) {
                                this.baseUrlProvider = baseUrlProvider2;
                                return;
                            }
                            throw new AssertionError();
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public Builder get() {
        return (Builder) Preconditions.checkNotNull(this.module.provideRetrofitBuilder((OkHttpClient) this.clientProvider.get(), (CallAdapter.Factory) this.callAdapterFactoryProvider.get(), (Executor) this.callbackExecutorProvider.get(), (ConverterFactory) this.converterFactoryProvider.get(), (BaseUrl) this.baseUrlProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Builder> create(NetworkModule module2, Provider<OkHttpClient> clientProvider2, Provider<CallAdapter.Factory> callAdapterFactoryProvider2, Provider<Executor> callbackExecutorProvider2, Provider<ConverterFactory> converterFactoryProvider2, Provider<BaseUrl> baseUrlProvider2) {
        return new NetworkModule_ProvideRetrofitBuilderFactory(module2, clientProvider2, callAdapterFactoryProvider2, callbackExecutorProvider2, converterFactoryProvider2, baseUrlProvider2);
    }

    public static Builder proxyProvideRetrofitBuilder(NetworkModule instance, OkHttpClient client, CallAdapter.Factory callAdapterFactory, Executor callbackExecutor, ConverterFactory converterFactory, BaseUrl baseUrl) {
        return instance.provideRetrofitBuilder(client, callAdapterFactory, callbackExecutor, converterFactory, baseUrl);
    }
}
