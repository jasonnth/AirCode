package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.OptionalSwipingViewPager;
import com.airbnb.android.core.views.SlidingTabLayout;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class SingleCalendarFragment_ViewBinding implements Unbinder {
    private SingleCalendarFragment target;
    private View view2131755523;

    public SingleCalendarFragment_ViewBinding(final SingleCalendarFragment target2, View source) {
        this.target = target2;
        target2.viewPager = (OptionalSwipingViewPager) Utils.findRequiredViewAsType(source, C6418R.C6420id.view_pager, "field 'viewPager'", OptionalSwipingViewPager.class);
        target2.tabLayout = (SlidingTabLayout) Utils.findRequiredViewAsType(source, C6418R.C6420id.tabs, "field 'tabLayout'", SlidingTabLayout.class);
        View view = Utils.findRequiredView(source, C6418R.C6420id.edit_button, "field 'editButton' and method 'goToEditDates'");
        target2.editButton = (AirButton) Utils.castView(view, C6418R.C6420id.edit_button, "field 'editButton'", AirButton.class);
        this.view2131755523 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.goToEditDates();
            }
        });
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C6418R.C6420id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        SingleCalendarFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.viewPager = null;
        target2.tabLayout = null;
        target2.editButton = null;
        target2.toolbar = null;
        this.view2131755523.setOnClickListener(null);
        this.view2131755523 = null;
    }
}
