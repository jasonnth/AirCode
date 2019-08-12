package com.airbnb.android.sms;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class SMSMonitorModule_ProvideSMSMonitorFactory implements Factory<SMSMonitor> {
    static final /* synthetic */ boolean $assertionsDisabled = (!SMSMonitorModule_ProvideSMSMonitorFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final SMSMonitorModule module;

    public SMSMonitorModule_ProvideSMSMonitorFactory(SMSMonitorModule module2, Provider<Context> contextProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public SMSMonitor get() {
        return (SMSMonitor) Preconditions.checkNotNull(this.module.provideSMSMonitor((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<SMSMonitor> create(SMSMonitorModule module2, Provider<Context> contextProvider2) {
        return new SMSMonitorModule_ProvideSMSMonitorFactory(module2, contextProvider2);
    }
}
