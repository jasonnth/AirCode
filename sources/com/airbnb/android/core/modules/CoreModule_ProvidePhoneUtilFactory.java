package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.utils.PhoneUtil;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvidePhoneUtilFactory implements Factory<PhoneUtil> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvidePhoneUtilFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;

    public CoreModule_ProvidePhoneUtilFactory(Provider<Context> contextProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            return;
        }
        throw new AssertionError();
    }

    public PhoneUtil get() {
        return (PhoneUtil) Preconditions.checkNotNull(CoreModule.providePhoneUtil((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PhoneUtil> create(Provider<Context> contextProvider2) {
        return new CoreModule_ProvidePhoneUtilFactory(contextProvider2);
    }

    public static PhoneUtil proxyProvidePhoneUtil(Context context) {
        return CoreModule.providePhoneUtil(context);
    }
}
