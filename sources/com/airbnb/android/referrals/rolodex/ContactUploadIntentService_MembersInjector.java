package com.airbnb.android.referrals.rolodex;

import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.utils.SharedPrefsHelper;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ContactUploadIntentService_MembersInjector implements MembersInjector<ContactUploadIntentService> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ContactUploadIntentService_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<LoggingContextFactory> loggingContextFactoryProvider;
    private final Provider<SharedPrefsHelper> sharedPrefsHelperProvider;

    public ContactUploadIntentService_MembersInjector(Provider<SharedPrefsHelper> sharedPrefsHelperProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        if ($assertionsDisabled || sharedPrefsHelperProvider2 != null) {
            this.sharedPrefsHelperProvider = sharedPrefsHelperProvider2;
            if ($assertionsDisabled || loggingContextFactoryProvider2 != null) {
                this.loggingContextFactoryProvider = loggingContextFactoryProvider2;
                if ($assertionsDisabled || accountManagerProvider2 != null) {
                    this.accountManagerProvider = accountManagerProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ContactUploadIntentService> create(Provider<SharedPrefsHelper> sharedPrefsHelperProvider2, Provider<LoggingContextFactory> loggingContextFactoryProvider2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        return new ContactUploadIntentService_MembersInjector(sharedPrefsHelperProvider2, loggingContextFactoryProvider2, accountManagerProvider2);
    }

    public void injectMembers(ContactUploadIntentService instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.sharedPrefsHelper = (SharedPrefsHelper) this.sharedPrefsHelperProvider.get();
        instance.loggingContextFactory = (LoggingContextFactory) this.loggingContextFactoryProvider.get();
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
    }

    public static void injectSharedPrefsHelper(ContactUploadIntentService instance, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        instance.sharedPrefsHelper = (SharedPrefsHelper) sharedPrefsHelperProvider2.get();
    }

    public static void injectLoggingContextFactory(ContactUploadIntentService instance, Provider<LoggingContextFactory> loggingContextFactoryProvider2) {
        instance.loggingContextFactory = (LoggingContextFactory) loggingContextFactoryProvider2.get();
    }

    public static void injectAccountManager(ContactUploadIntentService instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }
}
