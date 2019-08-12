package android.support.p000v4.widget;

import android.annotation.TargetApi;
import android.widget.OverScroller;

@TargetApi(14)
/* renamed from: android.support.v4.widget.ScrollerCompatIcs */
class ScrollerCompatIcs {
    public static float getCurrVelocity(Object scroller) {
        return ((OverScroller) scroller).getCurrVelocity();
    }
}
