package android.support.p000v4.view.accessibility;

import android.annotation.TargetApi;
import android.view.accessibility.AccessibilityEvent;

@TargetApi(19)
/* renamed from: android.support.v4.view.accessibility.AccessibilityEventCompatKitKat */
class AccessibilityEventCompatKitKat {
    public static void setContentChangeTypes(AccessibilityEvent event, int changeTypes) {
        event.setContentChangeTypes(changeTypes);
    }

    public static int getContentChangeTypes(AccessibilityEvent event) {
        return event.getContentChangeTypes();
    }
}
