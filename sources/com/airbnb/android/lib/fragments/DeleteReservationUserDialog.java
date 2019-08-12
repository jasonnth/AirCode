package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.models.ReservationUser;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.BundleBuilder;

public class DeleteReservationUserDialog extends ZenDialog {
    public static final String KEY_RESERVATION_USER_ID = "reservation_user_id";

    public static DeleteReservationUserDialog newInstance(ReservationUser reservationUser, int requestCode, Fragment targetFragment) {
        return (DeleteReservationUserDialog) new ZenBuilder(new DeleteReservationUserDialog()).withBodyText(AirbnbApplication.appContext(targetFragment.getActivity()).getString(C0880R.string.share_itinerary_confirm_delete, new Object[]{getNameToUse(reservationUser)})).withDualButton(C0880R.string.cancel, 0, C0880R.string.delete, requestCode, targetFragment).withArguments(((BundleBuilder) new BundleBuilder().putLong(KEY_RESERVATION_USER_ID, reservationUser.getId())).toBundle()).create();
    }

    private static String getNameToUse(ReservationUser user) {
        return TextUtils.isEmpty(user.getName()) ? user.getEmail() : user.getName();
    }

    /* access modifiers changed from: protected */
    public void clickRightButton(int requestCodeRight) {
        sendActivityResult(requestCodeRight, -1, new Intent().putExtra(KEY_RESERVATION_USER_ID, getArguments().getLong(KEY_RESERVATION_USER_ID)));
    }
}
