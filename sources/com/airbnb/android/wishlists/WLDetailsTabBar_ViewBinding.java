package com.airbnb.android.wishlists;

import android.support.design.widget.TabLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class WLDetailsTabBar_ViewBinding implements Unbinder {
    private WLDetailsTabBar target;

    public WLDetailsTabBar_ViewBinding(WLDetailsTabBar target2) {
        this(target2, target2);
    }

    public WLDetailsTabBar_ViewBinding(WLDetailsTabBar target2, View source) {
        this.target = target2;
        target2.tabLayout = (TabLayout) Utils.findRequiredViewAsType(source, C1758R.C1760id.tab_layout, "field 'tabLayout'", TabLayout.class);
        target2.divider = Utils.findRequiredView(source, C1758R.C1760id.section_divider, "field 'divider'");
    }

    public void unbind() {
        WLDetailsTabBar target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.tabLayout = null;
        target2.divider = null;
    }
}
