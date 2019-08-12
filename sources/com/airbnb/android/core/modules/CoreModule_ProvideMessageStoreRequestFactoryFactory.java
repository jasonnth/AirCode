package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.analytics.MessagingJitneyLogger;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.messaging.InboxUnreadCountManager;
import com.airbnb.android.core.messaging.MessageStore;
import com.airbnb.android.core.messaging.MessagingRequestFactory;
import com.airbnb.android.core.messaging.SyncRequestFactory;
import com.airbnb.android.core.utils.PhotoCompressor;
import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideMessageStoreRequestFactoryFactory implements Factory<MessagingRequestFactory> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideMessageStoreRequestFactoryFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<Bus> busProvider;
    private final Provider<Context> contextProvider;
    private final Provider<InboxUnreadCountManager> inboxUnreadCountManagerProvider;
    private final Provider<MessagingJitneyLogger> jitneyLoggerProvider;
    private final Provider<PhotoCompressor> photoCompressorProvider;
    private final Provider<MessageStore> storeProvider;
    private final Provider<SyncRequestFactory> syncRequestFactoryProvider;

    public CoreModule_ProvideMessageStoreRequestFactoryFactory(Provider<Context> contextProvider2, Provider<Bus> busProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<MessageStore> storeProvider2, Provider<SyncRequestFactory> syncRequestFactoryProvider2, Provider<MessagingJitneyLogger> jitneyLoggerProvider2, Provider<PhotoCompressor> photoCompressorProvider2, Provider<InboxUnreadCountManager> inboxUnreadCountManagerProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            if ($assertionsDisabled || busProvider2 != null) {
                this.busProvider = busProvider2;
                if ($assertionsDisabled || accountManagerProvider2 != null) {
                    this.accountManagerProvider = accountManagerProvider2;
                    if ($assertionsDisabled || storeProvider2 != null) {
                        this.storeProvider = storeProvider2;
                        if ($assertionsDisabled || syncRequestFactoryProvider2 != null) {
                            this.syncRequestFactoryProvider = syncRequestFactoryProvider2;
                            if ($assertionsDisabled || jitneyLoggerProvider2 != null) {
                                this.jitneyLoggerProvider = jitneyLoggerProvider2;
                                if ($assertionsDisabled || photoCompressorProvider2 != null) {
                                    this.photoCompressorProvider = photoCompressorProvider2;
                                    if ($assertionsDisabled || inboxUnreadCountManagerProvider2 != null) {
                                        this.inboxUnreadCountManagerProvider = inboxUnreadCountManagerProvider2;
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

    public MessagingRequestFactory get() {
        return (MessagingRequestFactory) Preconditions.checkNotNull(CoreModule.provideMessageStoreRequestFactory((Context) this.contextProvider.get(), (Bus) this.busProvider.get(), (AirbnbAccountManager) this.accountManagerProvider.get(), (MessageStore) this.storeProvider.get(), (SyncRequestFactory) this.syncRequestFactoryProvider.get(), (MessagingJitneyLogger) this.jitneyLoggerProvider.get(), (PhotoCompressor) this.photoCompressorProvider.get(), (InboxUnreadCountManager) this.inboxUnreadCountManagerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MessagingRequestFactory> create(Provider<Context> contextProvider2, Provider<Bus> busProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<MessageStore> storeProvider2, Provider<SyncRequestFactory> syncRequestFactoryProvider2, Provider<MessagingJitneyLogger> jitneyLoggerProvider2, Provider<PhotoCompressor> photoCompressorProvider2, Provider<InboxUnreadCountManager> inboxUnreadCountManagerProvider2) {
        return new CoreModule_ProvideMessageStoreRequestFactoryFactory(contextProvider2, busProvider2, accountManagerProvider2, storeProvider2, syncRequestFactoryProvider2, jitneyLoggerProvider2, photoCompressorProvider2, inboxUnreadCountManagerProvider2);
    }

    public static MessagingRequestFactory proxyProvideMessageStoreRequestFactory(Context context, Bus bus, AirbnbAccountManager accountManager, MessageStore store, SyncRequestFactory syncRequestFactory, MessagingJitneyLogger jitneyLogger, PhotoCompressor photoCompressor, InboxUnreadCountManager inboxUnreadCountManager) {
        return CoreModule.provideMessageStoreRequestFactory(context, bus, accountManager, store, syncRequestFactory, jitneyLogger, photoCompressor, inboxUnreadCountManager);
    }
}
