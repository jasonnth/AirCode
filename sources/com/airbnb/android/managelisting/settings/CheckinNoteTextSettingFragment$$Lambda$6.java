package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.utils.NetworkUtil;
import p032rx.functions.Action1;

final /* synthetic */ class CheckinNoteTextSettingFragment$$Lambda$6 implements Action1 {
    private final CheckinNoteTextSettingFragment arg$1;

    private CheckinNoteTextSettingFragment$$Lambda$6(CheckinNoteTextSettingFragment checkinNoteTextSettingFragment) {
        this.arg$1 = checkinNoteTextSettingFragment;
    }

    public static Action1 lambdaFactory$(CheckinNoteTextSettingFragment checkinNoteTextSettingFragment) {
        return new CheckinNoteTextSettingFragment$$Lambda$6(checkinNoteTextSettingFragment);
    }

    public void call(Object obj) {
        NetworkUtil.tryShowRetryableErrorWithSnackbar(this.arg$1.getView(), (NetworkException) (AirRequestNetworkException) obj, CheckinNoteTextSettingFragment$$Lambda$8.lambdaFactory$(this.arg$1));
    }
}
