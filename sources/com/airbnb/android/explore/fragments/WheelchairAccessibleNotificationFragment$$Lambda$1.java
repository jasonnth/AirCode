package com.airbnb.android.explore.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class WheelchairAccessibleNotificationFragment$$Lambda$1 implements OnClickListener {
    private final WheelchairAccessibleNotificationFragment arg$1;

    private WheelchairAccessibleNotificationFragment$$Lambda$1(WheelchairAccessibleNotificationFragment wheelchairAccessibleNotificationFragment) {
        this.arg$1 = wheelchairAccessibleNotificationFragment;
    }

    public static OnClickListener lambdaFactory$(WheelchairAccessibleNotificationFragment wheelchairAccessibleNotificationFragment) {
        return new WheelchairAccessibleNotificationFragment$$Lambda$1(wheelchairAccessibleNotificationFragment);
    }

    public void onClick(View view) {
        this.arg$1.getActivity().onBackPressed();
    }
}
