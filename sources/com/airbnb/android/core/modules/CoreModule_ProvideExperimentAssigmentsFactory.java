package com.airbnb.android.core.modules;

import com.airbnb.android.core.erf.ExperimentAssignments;
import com.airbnb.erf.Erf;
import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideExperimentAssigmentsFactory implements Factory<ExperimentAssignments> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideExperimentAssigmentsFactory.class.desiredAssertionStatus());
    private final Provider<Bus> busProvider;
    private final Provider<Erf> erfProvider;
    private final CoreModule module;

    public CoreModule_ProvideExperimentAssigmentsFactory(CoreModule module2, Provider<Bus> busProvider2, Provider<Erf> erfProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || busProvider2 != null) {
                this.busProvider = busProvider2;
                if ($assertionsDisabled || erfProvider2 != null) {
                    this.erfProvider = erfProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ExperimentAssignments get() {
        return (ExperimentAssignments) Preconditions.checkNotNull(this.module.provideExperimentAssigments((Bus) this.busProvider.get(), (Erf) this.erfProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ExperimentAssignments> create(CoreModule module2, Provider<Bus> busProvider2, Provider<Erf> erfProvider2) {
        return new CoreModule_ProvideExperimentAssigmentsFactory(module2, busProvider2, erfProvider2);
    }
}
