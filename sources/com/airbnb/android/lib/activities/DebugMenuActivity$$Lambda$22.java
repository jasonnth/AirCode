package com.airbnb.android.lib.activities;

import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.lib.activities.DebugMenuActivity.OnUserInputListener;

final /* synthetic */ class DebugMenuActivity$$Lambda$22 implements OnUserInputListener {
    private final DebugMenuActivity arg$1;

    private DebugMenuActivity$$Lambda$22(DebugMenuActivity debugMenuActivity) {
        this.arg$1 = debugMenuActivity;
    }

    public static OnUserInputListener lambdaFactory$(DebugMenuActivity debugMenuActivity) {
        return new DebugMenuActivity$$Lambda$22(debugMenuActivity);
    }

    public void onInput(String str) {
        this.arg$1.startActivity(ReactNativeIntents.intentForReviews(this.arg$1, Long.valueOf(str).longValue(), "John"));
    }
}
