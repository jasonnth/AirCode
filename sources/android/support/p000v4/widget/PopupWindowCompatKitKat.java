package android.support.p000v4.widget;

import android.annotation.TargetApi;
import android.view.View;
import android.widget.PopupWindow;

@TargetApi(19)
/* renamed from: android.support.v4.widget.PopupWindowCompatKitKat */
class PopupWindowCompatKitKat {
    public static void showAsDropDown(PopupWindow popup, View anchor, int xoff, int yoff, int gravity) {
        popup.showAsDropDown(anchor, xoff, yoff, gravity);
    }
}
