package com.airbnb.android.collections.fragments;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class SelectInvitationListingPickerFragment$$Lambda$4 implements Action1 {
    private final SelectInvitationListingPickerFragment arg$1;

    private SelectInvitationListingPickerFragment$$Lambda$4(SelectInvitationListingPickerFragment selectInvitationListingPickerFragment) {
        this.arg$1 = selectInvitationListingPickerFragment;
    }

    public static Action1 lambdaFactory$(SelectInvitationListingPickerFragment selectInvitationListingPickerFragment) {
        return new SelectInvitationListingPickerFragment$$Lambda$4(selectInvitationListingPickerFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowRetryableErrorWithSnackbar(this.arg$1.getView(), (NetworkException) (AirRequestNetworkException) obj, SelectInvitationListingPickerFragment$$Lambda$7.lambdaFactory$(this.arg$1));
    }
}
