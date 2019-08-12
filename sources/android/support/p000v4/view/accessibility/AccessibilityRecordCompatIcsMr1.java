package android.support.p000v4.view.accessibility;

import android.annotation.TargetApi;
import android.view.accessibility.AccessibilityRecord;

@TargetApi(15)
/* renamed from: android.support.v4.view.accessibility.AccessibilityRecordCompatIcsMr1 */
class AccessibilityRecordCompatIcsMr1 {
    public static void setMaxScrollX(Object record, int maxScrollX) {
        ((AccessibilityRecord) record).setMaxScrollX(maxScrollX);
    }

    public static void setMaxScrollY(Object record, int maxScrollY) {
        ((AccessibilityRecord) record).setMaxScrollY(maxScrollY);
    }
}
