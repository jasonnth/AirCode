package com.airbnb.android.managelisting.settings;

import com.airbnb.airrequest.AirRequestNetworkException;
import p032rx.functions.Action1;

final /* synthetic */ class CheckinNoteTextSettingFragment$$Lambda$4 implements Action1 {
    private final CheckinNoteTextSettingFragment arg$1;

    private CheckinNoteTextSettingFragment$$Lambda$4(CheckinNoteTextSettingFragment checkinNoteTextSettingFragment) {
        this.arg$1 = checkinNoteTextSettingFragment;
    }

    public static Action1 lambdaFactory$(CheckinNoteTextSettingFragment checkinNoteTextSettingFragment) {
        return new CheckinNoteTextSettingFragment$$Lambda$4(checkinNoteTextSettingFragment);
    }

    public void call(Object obj) {
        CheckinNoteTextSettingFragment.lambda$new$3(this.arg$1, (AirRequestNetworkException) obj);
    }
}
