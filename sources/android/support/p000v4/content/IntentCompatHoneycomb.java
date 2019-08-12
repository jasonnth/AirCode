package android.support.p000v4.content;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;

@TargetApi(11)
/* renamed from: android.support.v4.content.IntentCompatHoneycomb */
class IntentCompatHoneycomb {
    public static Intent makeMainActivity(ComponentName mainActivity) {
        return Intent.makeMainActivity(mainActivity);
    }
}
