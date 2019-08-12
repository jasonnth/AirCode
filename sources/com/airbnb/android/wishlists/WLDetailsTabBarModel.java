package com.airbnb.android.wishlists;

import android.support.design.widget.TabLayout.OnTabSelectedListener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import java.util.List;

abstract class WLDetailsTabBarModel extends AirEpoxyModel<WLDetailsTabBar> {
    OnTabSelectedListener onTabSelectedListener;
    int selectedPosition;
    List<WLTab> tabs;

    WLDetailsTabBarModel() {
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C1758R.layout.model_wl_details_tab_bar;
    }

    public void bind(WLDetailsTabBar tabBar) {
        super.bind(tabBar);
        tabBar.bind(this.tabs, this.selectedPosition, this.onTabSelectedListener);
    }

    public int getDividerViewType() {
        return 3;
    }
}
