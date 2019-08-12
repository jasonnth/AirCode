package com.airbnb.android.explore.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.components.HeroMarquee;

public final class WheelchairAccessibleNotificationFragment_ViewBinding implements Unbinder {
    private WheelchairAccessibleNotificationFragment target;

    public WheelchairAccessibleNotificationFragment_ViewBinding(WheelchairAccessibleNotificationFragment target2, View source) {
        this.target = target2;
        target2.heroMarquee = (HeroMarquee) Utils.findRequiredViewAsType(source, C0857R.C0859id.marquee, "field 'heroMarquee'", HeroMarquee.class);
    }

    public void unbind() {
        WheelchairAccessibleNotificationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.heroMarquee = null;
    }
}
