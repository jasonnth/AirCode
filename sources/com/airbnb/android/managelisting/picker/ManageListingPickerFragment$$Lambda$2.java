package com.airbnb.android.managelisting.picker;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingPickerFragment$$Lambda$2 implements Action1 {
    private final ManageListingPickerFragment arg$1;

    private ManageListingPickerFragment$$Lambda$2(ManageListingPickerFragment manageListingPickerFragment) {
        this.arg$1 = manageListingPickerFragment;
    }

    public static Action1 lambdaFactory$(ManageListingPickerFragment manageListingPickerFragment) {
        return new ManageListingPickerFragment$$Lambda$2(manageListingPickerFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowRetryableErrorWithSnackbar(this.arg$1.getView(), (NetworkException) (AirRequestNetworkException) obj, ManageListingPickerFragment$$Lambda$5.lambdaFactory$(this.arg$1));
    }
}
