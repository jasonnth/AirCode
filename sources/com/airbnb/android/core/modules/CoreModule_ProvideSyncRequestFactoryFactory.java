package com.airbnb.android.core.modules;

import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.messaging.MessageStore;
import com.airbnb.android.core.messaging.SyncRequestFactory;
import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideSyncRequestFactoryFactory implements Factory<SyncRequestFactory> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideSyncRequestFactoryFactory.class.desiredAssertionStatus());
    private final Provider<AirRequestInitializer> airRequestInitializerProvider;
    private final Provider<Bus> busProvider;
    private final Provider<MessagingJitneyLogger> jitneyLoggerProvider;
    private final Provider<MessageStore> messageStoreProvider;
    private final CoreModule module;

    public CoreModule_ProvideSyncRequestFactoryFactory(CoreModule module2, Provider<MessageStore> messageStoreProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<Bus> busProvider2, Provider<MessagingJitneyLogger> jitneyLoggerProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || messageStoreProvider2 != null) {
                this.messageStoreProvider = messageStoreProvider2;
                if ($assertionsDisabled || airRequestInitializerProvider2 != null) {
                    this.airRequestInitializerProvider = airRequestInitializerProvider2;
                    if ($assertionsDisabled || busProvider2 != null) {
                        this.busProvider = busProvider2;
                        if ($assertionsDisabled || jitneyLoggerProvider2 != null) {
                            this.jitneyLoggerProvider = jitneyLoggerProvider2;
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

    public SyncRequestFactory get() {
        return (SyncRequestFactory) Preconditions.checkNotNull(this.module.provideSyncRequestFactory((MessageStore) this.messageStoreProvider.get(), (AirRequestInitializer) this.airRequestInitializerProvider.get(), (Bus) this.busProvider.get(), (MessagingJitneyLogger) this.jitneyLoggerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SyncRequestFactory> create(CoreModule module2, Provider<MessageStore> messageStoreProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2, Provider<Bus> busProvider2, Provider<MessagingJitneyLogger> jitneyLoggerProvider2) {
        return new CoreModule_ProvideSyncRequestFactoryFactory(module2, messageStoreProvider2, airRequestInitializerProvider2, busProvider2, jitneyLoggerProvider2);
    }

    public static SyncRequestFactory proxyProvideSyncRequestFactory(CoreModule instance, MessageStore messageStore, AirRequestInitializer airRequestInitializer, Bus bus, MessagingJitneyLogger jitneyLogger) {
        return instance.provideSyncRequestFactory(messageStore, airRequestInitializer, bus, jitneyLogger);
    }
}
