package com.airbnb.android.lib.activities;

import com.airbnb.android.lib.activities.DebugMenuActivity.OnUserInputListener;
import com.airbnb.android.lib.tripassistant.HelpThreadActivity;

final /* synthetic */ class DebugMenuActivity$$Lambda$6 implements OnUserInputListener {
    private final DebugMenuActivity arg$1;

    private DebugMenuActivity$$Lambda$6(DebugMenuActivity debugMenuActivity) {
        this.arg$1 = debugMenuActivity;
    }

    public static OnUserInputListener lambdaFactory$(DebugMenuActivity debugMenuActivity) {
        return new DebugMenuActivity$$Lambda$6(debugMenuActivity);
    }

    public void onInput(String str) {
        this.arg$1.startActivity(HelpThreadActivity.intentForId(this.arg$1, Long.valueOf(str).longValue()));
    }
}
