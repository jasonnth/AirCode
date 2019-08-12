package com.airbnb.android.core.modules;

import android.content.Context;
import com.apollographql.apollo.ApolloClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

public final class NetworkModule_ProvideApolloClientFactory implements Factory<ApolloClient> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideApolloClientFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final NetworkModule module;
    private final Provider<OkHttpClient> okHttpClientProvider;

    public NetworkModule_ProvideApolloClientFactory(NetworkModule module2, Provider<Context> contextProvider2, Provider<OkHttpClient> okHttpClientProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || okHttpClientProvider2 != null) {
                    this.okHttpClientProvider = okHttpClientProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ApolloClient get() {
        return (ApolloClient) Preconditions.checkNotNull(this.module.provideApolloClient((Context) this.contextProvider.get(), (OkHttpClient) this.okHttpClientProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ApolloClient> create(NetworkModule module2, Provider<Context> contextProvider2, Provider<OkHttpClient> okHttpClientProvider2) {
        return new NetworkModule_ProvideApolloClientFactory(module2, contextProvider2, okHttpClientProvider2);
    }

    public static ApolloClient proxyProvideApolloClient(NetworkModule instance, Context context, OkHttpClient okHttpClient) {
        return instance.provideApolloClient(context, okHttpClient);
    }
}
