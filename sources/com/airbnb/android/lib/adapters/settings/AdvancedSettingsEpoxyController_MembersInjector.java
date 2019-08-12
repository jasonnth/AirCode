package com.airbnb.android.lib.adapters.settings;

import com.airbnb.android.core.utils.ShakeFeedbackSensorListener;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AdvancedSettingsEpoxyController_MembersInjector implements MembersInjector<AdvancedSettingsEpoxyController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AdvancedSettingsEpoxyController_MembersInjector.class.desiredAssertionStatus());
    private final Provider<ShakeFeedbackSensorListener> shakeFeedbackSensorListenerProvider;

    public AdvancedSettingsEpoxyController_MembersInjector(Provider<ShakeFeedbackSensorListener> shakeFeedbackSensorListenerProvider2) {
        if ($assertionsDisabled || shakeFeedbackSensorListenerProvider2 != null) {
            this.shakeFeedbackSensorListenerProvider = shakeFeedbackSensorListenerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<AdvancedSettingsEpoxyController> create(Provider<ShakeFeedbackSensorListener> shakeFeedbackSensorListenerProvider2) {
        return new AdvancedSettingsEpoxyController_MembersInjector(shakeFeedbackSensorListenerProvider2);
    }

    public void injectMembers(AdvancedSettingsEpoxyController instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.shakeFeedbackSensorListener = (ShakeFeedbackSensorListener) this.shakeFeedbackSensorListenerProvider.get();
    }

    public static void injectShakeFeedbackSensorListener(AdvancedSettingsEpoxyController instance, Provider<ShakeFeedbackSensorListener> shakeFeedbackSensorListenerProvider2) {
        instance.shakeFeedbackSensorListener = (ShakeFeedbackSensorListener) shakeFeedbackSensorListenerProvider2.get();
    }
}
