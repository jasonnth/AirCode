package com.airbnb.android.cohosting.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.core.views.OptionalSwipingViewPager;

public class CohostLeadsCenterTabsFragment_ViewBinding implements Unbinder {
    private CohostLeadsCenterTabsFragment target;

    public CohostLeadsCenterTabsFragment_ViewBinding(CohostLeadsCenterTabsFragment target2, View source) {
        this.target = target2;
        target2.viewPager = (OptionalSwipingViewPager) Utils.findRequiredViewAsType(source, C5658R.C5660id.view_pager, "field 'viewPager'", OptionalSwipingViewPager.class);
    }

    public void unbind() {
        CohostLeadsCenterTabsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.viewPager = null;
    }
}
