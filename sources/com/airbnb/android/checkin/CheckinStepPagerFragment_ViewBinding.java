package com.airbnb.android.checkin;

import android.support.design.widget.TabLayout;
import android.support.p000v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;

public class CheckinStepPagerFragment_ViewBinding implements Unbinder {
    private CheckinStepPagerFragment target;

    public CheckinStepPagerFragment_ViewBinding(CheckinStepPagerFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5618R.C5620id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.stepPager = (ViewPager) Utils.findRequiredViewAsType(source, C5618R.C5620id.view_pager, "field 'stepPager'", ViewPager.class);
        target2.dotsIndicator = (TabLayout) Utils.findRequiredViewAsType(source, C5618R.C5620id.dots_indicator, "field 'dotsIndicator'", TabLayout.class);
        target2.actionFooter = (FixedActionFooter) Utils.findRequiredViewAsType(source, C5618R.C5620id.action_footer, "field 'actionFooter'", FixedActionFooter.class);
    }

    public void unbind() {
        CheckinStepPagerFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.stepPager = null;
        target2.dotsIndicator = null;
        target2.actionFooter = null;
    }
}
