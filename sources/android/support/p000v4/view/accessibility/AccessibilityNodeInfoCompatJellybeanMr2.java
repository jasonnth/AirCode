package android.support.p000v4.view.accessibility;

import android.annotation.TargetApi;
import android.view.accessibility.AccessibilityNodeInfo;

@TargetApi(18)
/* renamed from: android.support.v4.view.accessibility.AccessibilityNodeInfoCompatJellybeanMr2 */
class AccessibilityNodeInfoCompatJellybeanMr2 {
    public static String getViewIdResourceName(Object info) {
        return ((AccessibilityNodeInfo) info).getViewIdResourceName();
    }
}
