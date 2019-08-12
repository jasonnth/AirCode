package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.views.AirEditTextPageView.Listener;

final /* synthetic */ class CheckinNoteTextSettingFragment$$Lambda$7 implements Listener {
    private final CheckinNoteTextSettingFragment arg$1;

    private CheckinNoteTextSettingFragment$$Lambda$7(CheckinNoteTextSettingFragment checkinNoteTextSettingFragment) {
        this.arg$1 = checkinNoteTextSettingFragment;
    }

    public static Listener lambdaFactory$(CheckinNoteTextSettingFragment checkinNoteTextSettingFragment) {
        return new CheckinNoteTextSettingFragment$$Lambda$7(checkinNoteTextSettingFragment);
    }

    public void validityChanged(boolean z) {
        this.arg$1.updateSaveButton(z);
    }
}
