package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.messaging.MessagingRequestFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class InboxAdapter_MembersInjector implements MembersInjector<InboxAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!InboxAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<MessagingRequestFactory> messagingRequestFactoryProvider;

    public InboxAdapter_MembersInjector(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<MessagingRequestFactory> messagingRequestFactoryProvider2) {
        if ($assertionsDisabled || accountManagerProvider2 != null) {
            this.accountManagerProvider = accountManagerProvider2;
            if ($assertionsDisabled || messagingRequestFactoryProvider2 != null) {
                this.messagingRequestFactoryProvider = messagingRequestFactoryProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<InboxAdapter> create(Provider<AirbnbAccountManager> accountManagerProvider2, Provider<MessagingRequestFactory> messagingRequestFactoryProvider2) {
        return new InboxAdapter_MembersInjector(accountManagerProvider2, messagingRequestFactoryProvider2);
    }

    public void injectMembers(InboxAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
        instance.messagingRequestFactory = (MessagingRequestFactory) this.messagingRequestFactoryProvider.get();
    }

    public static void injectAccountManager(InboxAdapter instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }

    public static void injectMessagingRequestFactory(InboxAdapter instance, Provider<MessagingRequestFactory> messagingRequestFactoryProvider2) {
        instance.messagingRequestFactory = (MessagingRequestFactory) messagingRequestFactoryProvider2.get();
    }
}
