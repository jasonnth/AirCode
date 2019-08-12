package com.airbnb.android.login.p339ui;

import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.login.C7331R;
import com.airbnb.p027n2.components.HeroMarquee;

/* renamed from: com.airbnb.android.login.ui.MobileWebLandingFragment_ViewBinding */
public class MobileWebLandingFragment_ViewBinding implements Unbinder {
    private MobileWebLandingFragment target;

    public MobileWebLandingFragment_ViewBinding(MobileWebLandingFragment target2, View source) {
        this.target = target2;
        target2.marquee = (HeroMarquee) Utils.findRequiredViewAsType(source, C7331R.C7333id.login_landing_marquee, "field 'marquee'", HeroMarquee.class);
        target2.container = (FrameLayout) Utils.findRequiredViewAsType(source, C7331R.C7333id.layout_container, "field 'container'", FrameLayout.class);
    }

    public void unbind() {
        MobileWebLandingFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marquee = null;
        target2.container = null;
    }
}
