package com.airbnb.android.core.modules;

import com.airbnb.android.core.messaging.MessageStore;
import com.airbnb.android.core.messaging.p007db.MessageStoreTableOpenHelper;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideMessageStoreFactory implements Factory<MessageStore> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideMessageStoreFactory.class.desiredAssertionStatus());
    private final Provider<MessageStoreTableOpenHelper> messageStoreTableOpenHelperProvider;
    private final CoreModule module;

    public CoreModule_ProvideMessageStoreFactory(CoreModule module2, Provider<MessageStoreTableOpenHelper> messageStoreTableOpenHelperProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || messageStoreTableOpenHelperProvider2 != null) {
                this.messageStoreTableOpenHelperProvider = messageStoreTableOpenHelperProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public MessageStore get() {
        return (MessageStore) Preconditions.checkNotNull(this.module.provideMessageStore(DoubleCheck.lazy(this.messageStoreTableOpenHelperProvider)), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<MessageStore> create(CoreModule module2, Provider<MessageStoreTableOpenHelper> messageStoreTableOpenHelperProvider2) {
        return new CoreModule_ProvideMessageStoreFactory(module2, messageStoreTableOpenHelperProvider2);
    }

    public static MessageStore proxyProvideMessageStore(CoreModule instance, Lazy<MessageStoreTableOpenHelper> messageStoreTableOpenHelper) {
        return instance.provideMessageStore(messageStoreTableOpenHelper);
    }
}
