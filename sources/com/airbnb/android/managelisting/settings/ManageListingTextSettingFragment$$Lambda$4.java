package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingTextSettingFragment$$Lambda$4 implements Action1 {
    private final ManageListingTextSettingFragment arg$1;

    private ManageListingTextSettingFragment$$Lambda$4(ManageListingTextSettingFragment manageListingTextSettingFragment) {
        this.arg$1 = manageListingTextSettingFragment;
    }

    public static Action1 lambdaFactory$(ManageListingTextSettingFragment manageListingTextSettingFragment) {
        return new ManageListingTextSettingFragment$$Lambda$4(manageListingTextSettingFragment);
    }

    public void call(Object obj) {
        this.arg$1.onListenerError((AirRequestNetworkException) obj);
    }
}
