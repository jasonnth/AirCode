package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.ReservationUser;

final /* synthetic */ class ShareItineraryFragment$$Lambda$1 implements OnClickListener {
    private final ShareItineraryFragment arg$1;

    private ShareItineraryFragment$$Lambda$1(ShareItineraryFragment shareItineraryFragment) {
        this.arg$1 = shareItineraryFragment;
    }

    public static OnClickListener lambdaFactory$(ShareItineraryFragment shareItineraryFragment) {
        return new ShareItineraryFragment$$Lambda$1(shareItineraryFragment);
    }

    public void onClick(View view) {
        DeleteReservationUserDialog.newInstance((ReservationUser) view.getTag(), ShareItineraryFragment.DIALOG_REQ_DELETE_CONFIRM, this.arg$1).show(this.arg$1.getFragmentManager(), (String) null);
    }
}
