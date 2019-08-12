package com.airbnb.android.core.modules;

import com.airbnb.android.core.graphql.GraphistClient;
import com.apollographql.apollo.ApolloClient;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class NetworkModule_ProvideGraphistClientFactory implements Factory<GraphistClient> {
    static final /* synthetic */ boolean $assertionsDisabled = (!NetworkModule_ProvideGraphistClientFactory.class.desiredAssertionStatus());
    private final Provider<ApolloClient> clientProvider;
    private final NetworkModule module;

    public NetworkModule_ProvideGraphistClientFactory(NetworkModule module2, Provider<ApolloClient> clientProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || clientProvider2 != null) {
                this.clientProvider = clientProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public GraphistClient get() {
        return (GraphistClient) Preconditions.checkNotNull(this.module.provideGraphistClient((ApolloClient) this.clientProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<GraphistClient> create(NetworkModule module2, Provider<ApolloClient> clientProvider2) {
        return new NetworkModule_ProvideGraphistClientFactory(module2, clientProvider2);
    }

    public static GraphistClient proxyProvideGraphistClient(NetworkModule instance, ApolloClient client) {
        return instance.provideGraphistClient(client);
    }
}
