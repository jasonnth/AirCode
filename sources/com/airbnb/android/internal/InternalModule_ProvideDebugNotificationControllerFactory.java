package com.airbnb.android.internal;

import android.content.Context;
import com.airbnb.android.core.AppForegroundDetector;
import com.airbnb.android.internal.bugreporter.DebugNotificationController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class InternalModule_ProvideDebugNotificationControllerFactory implements Factory<DebugNotificationController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!InternalModule_ProvideDebugNotificationControllerFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final Provider<AppForegroundDetector> foregroundDetectorProvider;
    private final InternalModule module;

    public InternalModule_ProvideDebugNotificationControllerFactory(InternalModule module2, Provider<Context> contextProvider2, Provider<AppForegroundDetector> foregroundDetectorProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || foregroundDetectorProvider2 != null) {
                    this.foregroundDetectorProvider = foregroundDetectorProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public DebugNotificationController get() {
        return (DebugNotificationController) Preconditions.checkNotNull(this.module.provideDebugNotificationController((Context) this.contextProvider.get(), (AppForegroundDetector) this.foregroundDetectorProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<DebugNotificationController> create(InternalModule module2, Provider<Context> contextProvider2, Provider<AppForegroundDetector> foregroundDetectorProvider2) {
        return new InternalModule_ProvideDebugNotificationControllerFactory(module2, contextProvider2, foregroundDetectorProvider2);
    }

    public static DebugNotificationController proxyProvideDebugNotificationController(InternalModule instance, Context context, AppForegroundDetector foregroundDetector) {
        return instance.provideDebugNotificationController(context, foregroundDetector);
    }
}
