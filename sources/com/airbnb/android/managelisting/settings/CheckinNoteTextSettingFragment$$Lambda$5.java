package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.CheckInGuideResponse;
import p032rx.functions.Action1;

final /* synthetic */ class CheckinNoteTextSettingFragment$$Lambda$5 implements Action1 {
    private final CheckinNoteTextSettingFragment arg$1;

    private CheckinNoteTextSettingFragment$$Lambda$5(CheckinNoteTextSettingFragment checkinNoteTextSettingFragment) {
        this.arg$1 = checkinNoteTextSettingFragment;
    }

    public static Action1 lambdaFactory$(CheckinNoteTextSettingFragment checkinNoteTextSettingFragment) {
        return new CheckinNoteTextSettingFragment$$Lambda$5(checkinNoteTextSettingFragment);
    }

    public void call(Object obj) {
        CheckinNoteTextSettingFragment.lambda$new$4(this.arg$1, (CheckInGuideResponse) obj);
    }
}
