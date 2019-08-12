package com.airbnb.android.core.modules;

import com.airbnb.android.core.ViewBreadcrumbManager;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class CoreModule_ProvideViewBreadcrumbManagerFactory implements Factory<ViewBreadcrumbManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideViewBreadcrumbManagerFactory.class.desiredAssertionStatus());
    private final CoreModule module;

    public CoreModule_ProvideViewBreadcrumbManagerFactory(CoreModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public ViewBreadcrumbManager get() {
        return (ViewBreadcrumbManager) Preconditions.checkNotNull(this.module.provideViewBreadcrumbManager(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ViewBreadcrumbManager> create(CoreModule module2) {
        return new CoreModule_ProvideViewBreadcrumbManagerFactory(module2);
    }

    public static ViewBreadcrumbManager proxyProvideViewBreadcrumbManager(CoreModule instance) {
        return instance.provideViewBreadcrumbManager();
    }
}
