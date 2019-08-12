package android.support.p000v4.view;

import android.annotation.TargetApi;
import android.view.MenuItem;

@TargetApi(14)
/* renamed from: android.support.v4.view.MenuItemCompatIcs */
class MenuItemCompatIcs {
    public static boolean expandActionView(MenuItem item) {
        return item.expandActionView();
    }

    public static boolean isActionViewExpanded(MenuItem item) {
        return item.isActionViewExpanded();
    }
}
