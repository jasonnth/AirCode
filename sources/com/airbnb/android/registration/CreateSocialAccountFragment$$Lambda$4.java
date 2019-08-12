package com.airbnb.android.registration;

import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class CreateSocialAccountFragment$$Lambda$4 implements OnCheckedChangeListener {
    private final CreateSocialAccountFragment arg$1;

    private CreateSocialAccountFragment$$Lambda$4(CreateSocialAccountFragment createSocialAccountFragment) {
        this.arg$1 = createSocialAccountFragment;
    }

    public static OnCheckedChangeListener lambdaFactory$(CreateSocialAccountFragment createSocialAccountFragment) {
        return new CreateSocialAccountFragment$$Lambda$4(createSocialAccountFragment);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.PROMO_OPTION_SWITCH, this.arg$1.prefilledData.getRegistrationServiceForAnalytics(), this.arg$1.getNavigationTrackingTag(), Strap.make().mo11640kv("value", z));
    }
}
