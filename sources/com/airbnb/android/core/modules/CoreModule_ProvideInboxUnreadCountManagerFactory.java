package com.airbnb.android.core.modules;

import com.airbnb.android.core.messaging.InboxUnreadCountManager;
import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideInboxUnreadCountManagerFactory implements Factory<InboxUnreadCountManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideInboxUnreadCountManagerFactory.class.desiredAssertionStatus());
    private final Provider<Bus> busProvider;
    private final CoreModule module;

    public CoreModule_ProvideInboxUnreadCountManagerFactory(CoreModule module2, Provider<Bus> busProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || busProvider2 != null) {
                this.busProvider = busProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public InboxUnreadCountManager get() {
        return (InboxUnreadCountManager) Preconditions.checkNotNull(this.module.provideInboxUnreadCountManager((Bus) this.busProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<InboxUnreadCountManager> create(CoreModule module2, Provider<Bus> busProvider2) {
        return new CoreModule_ProvideInboxUnreadCountManagerFactory(module2, busProvider2);
    }

    public static InboxUnreadCountManager proxyProvideInboxUnreadCountManager(CoreModule instance, Bus bus) {
        return instance.provideInboxUnreadCountManager(bus);
    }
}
