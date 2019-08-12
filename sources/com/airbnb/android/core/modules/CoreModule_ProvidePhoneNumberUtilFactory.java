package com.airbnb.android.core.modules;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoreModule_ProvidePhoneNumberUtilFactory implements Factory<PhoneNumberUtil> {
    private static final CoreModule_ProvidePhoneNumberUtilFactory INSTANCE = new CoreModule_ProvidePhoneNumberUtilFactory();

    public PhoneNumberUtil get() {
        return (PhoneNumberUtil) Preconditions.checkNotNull(CoreModule.providePhoneNumberUtil(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PhoneNumberUtil> create() {
        return INSTANCE;
    }

    public static PhoneNumberUtil proxyProvidePhoneNumberUtil() {
        return CoreModule.providePhoneNumberUtil();
    }
}
