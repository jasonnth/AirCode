package com.airbnb.android.lib.fragments.reservationresponse;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.RejectionTip;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseBaseFragment.ReservationResponseNavigator;

/* renamed from: com.airbnb.android.lib.fragments.reservationresponse.ReservationDeclineTipsFragment$ReservationSettingsAdapter$$Lambda$1 */
final /* synthetic */ class C6976x64f43486 implements OnClickListener {
    private final ReservationResponseNavigator arg$1;
    private final SettingDeepLink arg$2;
    private final RejectionTip arg$3;

    private C6976x64f43486(ReservationResponseNavigator reservationResponseNavigator, SettingDeepLink settingDeepLink, RejectionTip rejectionTip) {
        this.arg$1 = reservationResponseNavigator;
        this.arg$2 = settingDeepLink;
        this.arg$3 = rejectionTip;
    }

    public static OnClickListener lambdaFactory$(ReservationResponseNavigator reservationResponseNavigator, SettingDeepLink settingDeepLink, RejectionTip rejectionTip) {
        return new C6976x64f43486(reservationResponseNavigator, settingDeepLink, rejectionTip);
    }

    public void onClick(View view) {
        this.arg$1.onTipSelected(this.arg$2, this.arg$3.getKey());
    }
}
