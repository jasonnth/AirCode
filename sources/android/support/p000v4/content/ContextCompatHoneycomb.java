package android.support.p000v4.content;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;

@TargetApi(11)
/* renamed from: android.support.v4.content.ContextCompatHoneycomb */
class ContextCompatHoneycomb {
    static void startActivities(Context context, Intent[] intents) {
        context.startActivities(intents);
    }
}
