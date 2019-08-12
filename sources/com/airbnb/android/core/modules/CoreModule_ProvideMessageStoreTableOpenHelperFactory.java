package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.messaging.InboxUnreadCountManager;
import com.airbnb.android.core.messaging.p007db.MessageStoreTableOpenHelper;
import com.airbnb.android.core.messaging.p007db.ThreadDataMapper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideMessageStoreTableOpenHelperFactory implements Factory<MessageStoreTableOpenHelper> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideMessageStoreTableOpenHelperFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final Provider<InboxUnreadCountManager> inboxUnreadCountManagerProvider;
    private final Provider<ThreadDataMapper> threadDataMapperProvider;

    public CoreModule_ProvideMessageStoreTableOpenHelperFactory(Provider<Context> contextProvider2, Provider<ThreadDataMapper> threadDataMapperProvider2, Provider<InboxUnreadCountManager> inboxUnreadCountManagerProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            if ($assertionsDisabled || threadDataMapperProvider2 != null) {
                this.threadDataMapperProvider = threadDataMapperProvider2;
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

    public MessageStoreTableOpenHelper get() {
        return (MessageStoreTableOpenHelper) Preconditions.checkNotNull(CoreModule.provideMessageStoreTableOpenHelper((Context) this.contextProvider.get(), DoubleCheck.lazy(this.threadDataMapperProvider), (InboxUnreadCountManager) this.inboxUnreadCountManagerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MessageStoreTableOpenHelper> create(Provider<Context> contextProvider2, Provider<ThreadDataMapper> threadDataMapperProvider2, Provider<InboxUnreadCountManager> inboxUnreadCountManagerProvider2) {
        return new CoreModule_ProvideMessageStoreTableOpenHelperFactory(contextProvider2, threadDataMapperProvider2, inboxUnreadCountManagerProvider2);
    }

    public static MessageStoreTableOpenHelper proxyProvideMessageStoreTableOpenHelper(Context context, Lazy<ThreadDataMapper> threadDataMapper, InboxUnreadCountManager inboxUnreadCountManager) {
        return CoreModule.provideMessageStoreTableOpenHelper(context, threadDataMapper, inboxUnreadCountManager);
    }
}
