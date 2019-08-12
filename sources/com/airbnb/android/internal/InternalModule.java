package com.airbnb.android.internal;

import android.content.Context;
import com.airbnb.android.core.AppForegroundDetector;
import com.airbnb.android.core.PostApplicationCreatedInitializer;
import com.airbnb.android.internal.bugreporter.DebugNotificationController;

public class InternalModule {

    public interface Declarations {
        PostApplicationCreatedInitializer bindDebugNotificationController(DebugNotificationController debugNotificationController);
    }

    /* access modifiers changed from: 0000 */
    public DebugNotificationController provideDebugNotificationController(Context context, AppForegroundDetector foregroundDetector) {
        return new DebugNotificationController(context, foregroundDetector);
    }
}
