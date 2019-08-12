package android.support.p000v4.content;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

@TargetApi(16)
/* renamed from: android.support.v4.content.ContextCompatJellybean */
class ContextCompatJellybean {
    public static void startActivities(Context context, Intent[] intents, Bundle options) {
        context.startActivities(intents, options);
    }

    public static void startActivity(Context context, Intent intent, Bundle options) {
        context.startActivity(intent, options);
    }
}