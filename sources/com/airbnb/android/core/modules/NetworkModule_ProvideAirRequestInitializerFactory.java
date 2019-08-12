package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.airbnb.android.core.requests.base.AirRequestHeadersInterceptor;
import com.airbnb.android.core.requests.base.AirlockErrorHandler;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import com.airbnb.rxgroups.ObservableManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import retrofit2.Retrofit;

public final class NetworkModule_ProvideAirRequestInitializerFactory implements Factory<AirRequestInitializer> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideAirRequestInitializerFactory.class.desiredAssertionStatus());
    private final Provider<AirlockErrorHandler> airlockErrorHandlerProvider;
    private final Provider<Context> contextProvider;
    private final Provider<ExperimentsProvider> experimentsProvider;
    private final Provider<AirRequestHeadersInterceptor> headersInterceptorProvider;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final NetworkModule module;
    private final Provider<ObservableManager> observableManagerProvider;
    private final Provider<Retrofit> retrofitProvider;
    private final Provider<SharedPrefsHelper> sharedPrefsHelperProvider;

    public NetworkModule_ProvideAirRequestInitializerFactory(NetworkModule module2, Provider<Context> contextProvider2, Provider<Retrofit> retrofitProvider2, Provider<AirRequestHeadersInterceptor> headersInterceptorProvider2, Provider<ObservableManager> observableManagerProvider2, Provider<ExperimentsProvider> experimentsProvider2, Provider<AirlockErrorHandler> airlockErrorHandlerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || retrofitProvider2 != null) {
                    this.retrofitProvider = retrofitProvider2;
                    if ($assertionsDisabled || headersInterceptorProvider2 != null) {
                        this.headersInterceptorProvider = headersInterceptorProvider2;
                        if ($assertionsDisabled || observableManagerProvider2 != null) {
                            this.observableManagerProvider = observableManagerProvider2;
                            if ($assertionsDisabled || experimentsProvider2 != null) {
                                this.experimentsProvider = experimentsProvider2;
                                if ($assertionsDisabled || airlockErrorHandlerProvider2 != null) {
                                    this.airlockErrorHandlerProvider = airlockErrorHandlerProvider2;
                                    if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
                                        this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
                                        if ($assertionsDisabled || sharedPrefsHelperProvider2 != null) {
                                            this.sharedPrefsHelperProvider = sharedPrefsHelperProvider2;
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
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public AirRequestInitializer get() {
        return (AirRequestInitializer) Preconditions.checkNotNull(this.module.provideAirRequestInitializer((Context) this.contextProvider.get(), (Retrofit) this.retrofitProvider.get(), (AirRequestHeadersInterceptor) this.headersInterceptorProvider.get(), (ObservableManager) this.observableManagerProvider.get(), (ExperimentsProvider) this.experimentsProvider.get(), (AirlockErrorHandler) this.airlockErrorHandlerProvider.get(), (LoggingContextFactory) this.loggingContextFactoryProvider.get(), (SharedPrefsHelper) this.sharedPrefsHelperProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<AirRequestInitializer> create(NetworkModule module2, Provider<Context> contextProvider2, Provider<Retrofit> retrofitProvider2, Provider<AirRequestHeadersInterceptor> headersInterceptorProvider2, Provider<ObservableManager> observableManagerProvider2, Provider<ExperimentsProvider> experimentsProvider2, Provider<AirlockErrorHandler> airlockErrorHandlerProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        return new NetworkModule_ProvideAirRequestInitializerFactory(module2, contextProvider2, retrofitProvider2, headersInterceptorProvider2, observableManagerProvider2, experimentsProvider2, airlockErrorHandlerProvider2, loggingContextFactoryProvider2, sharedPrefsHelperProvider2);
    }

    public static AirRequestInitializer proxyProvideAirRequestInitializer(NetworkModule instance, Context context, Retrofit retrofit, AirRequestHeadersInterceptor headersInterceptor, ObservableManager observableManager, ExperimentsProvider experimentsProvider2, AirlockErrorHandler airlockErrorHandler, LoggingContextFactory loggingContextFactory, SharedPrefsHelper sharedPrefsHelper) {
        return instance.provideAirRequestInitializer(context, retrofit, headersInterceptor, observableManager, experimentsProvider2, airlockErrorHandler, loggingContextFactory, sharedPrefsHelper);
    }
}
