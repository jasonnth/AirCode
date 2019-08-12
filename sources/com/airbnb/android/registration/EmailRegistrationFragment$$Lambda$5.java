package com.airbnb.android.registration;

import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;

final /* synthetic */ class EmailRegistrationFragment$$Lambda$5 implements OnCheckedChangeListener {
    private final EmailRegistrationFragment arg$1;

    private EmailRegistrationFragment$$Lambda$5(EmailRegistrationFragment emailRegistrationFragment) {
        this.arg$1 = emailRegistrationFragment;
    }

    public static OnCheckedChangeListener lambdaFactory$(EmailRegistrationFragment emailRegistrationFragment) {
        return new EmailRegistrationFragment$$Lambda$5(emailRegistrationFragment);
    }

    public void onCheckedChanged(SwitchRowInterface switchRowInterface, boolean z) {
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.PROMO_OPTION_SWITCH, "email", this.arg$1.getNavigationTrackingTag(), Strap.make().mo11640kv("value", z));
    }
}
