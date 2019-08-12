package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirbnbSlidingTabLayout;
import com.airbnb.android.core.views.OptionalSwipingViewPager;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;

public class ManageListingSettingsTabFragment_ViewBinding implements Unbinder {
    private ManageListingSettingsTabFragment target;

    public ManageListingSettingsTabFragment_ViewBinding(ManageListingSettingsTabFragment target2, View source) {
        this.target = target2;
        target2.viewPager = (OptionalSwipingViewPager) Utils.findRequiredViewAsType(source, C7368R.C7370id.view_pager, "field 'viewPager'", OptionalSwipingViewPager.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7368R.C7370id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.tabLayout = (AirbnbSlidingTabLayout) Utils.findRequiredViewAsType(source, C7368R.C7370id.tabs, "field 'tabLayout'", AirbnbSlidingTabLayout.class);
        Context context = source.getContext();
        target2.selectedColorForSelectListing = ContextCompat.getColor(context, C7368R.color.n2_hackberry_light);
        target2.unselectedColorForSelectListing = ContextCompat.getColor(context, C7368R.color.n2_text_color_main);
    }

    public void unbind() {
        ManageListingSettingsTabFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.viewPager = null;
        target2.toolbar = null;
        target2.tabLayout = null;
    }
}
