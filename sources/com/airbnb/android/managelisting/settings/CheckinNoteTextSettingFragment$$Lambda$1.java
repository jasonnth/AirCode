package com.airbnb.android.managelisting.settings;

import p032rx.functions.Action1;

final /* synthetic */ class CheckinNoteTextSettingFragment$$Lambda$1 implements Action1 {
    private final CheckinNoteTextSettingFragment arg$1;

    private CheckinNoteTextSettingFragment$$Lambda$1(CheckinNoteTextSettingFragment checkinNoteTextSettingFragment) {
        this.arg$1 = checkinNoteTextSettingFragment;
    }

    public static Action1 lambdaFactory$(CheckinNoteTextSettingFragment checkinNoteTextSettingFragment) {
        return new CheckinNoteTextSettingFragment$$Lambda$1(checkinNoteTextSettingFragment);
    }

    public void call(Object obj) {
        this.arg$1.refreshGuide();
    }
}