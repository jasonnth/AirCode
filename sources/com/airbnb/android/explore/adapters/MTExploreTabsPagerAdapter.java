package com.airbnb.android.explore.adapters;

import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.app.FragmentManager;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.adapters.BaseTabFragmentPager;
import com.airbnb.android.core.models.ExploreTab;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.explore.controllers.ExploreDataController;
import com.airbnb.android.explore.fragments.MTHomesTabFragment;
import com.airbnb.android.explore.fragments.MTTabFragment;
import java.util.ArrayList;
import java.util.List;

public class MTExploreTabsPagerAdapter extends BaseTabFragmentPager {
    private final List<ExploreTab> tabs;

    public MTExploreTabsPagerAdapter(FragmentActivity activity, FragmentManager manager, ExploreDataController dataController) {
        super(activity, manager);
        this.tabs = new ArrayList(dataController.getTabs().values());
    }

    public int getPageIconId(int position) {
        return 0;
    }

    public int getPageTitleId(int position) {
        return 0;
    }

    public CharSequence getPageTitle(int position) {
        return ((ExploreTab) this.tabs.get(position)).getTabName();
    }

    public boolean isSwipePagingEnabled(int position) {
        return false;
    }

    public Fragment getItem(int position) {
        String tabId = ((ExploreTab) this.tabs.get(position)).getTabId();
        CoreApplication.instance().component().debugSettings();
        if (!DebugSettings.ENABLE_FILTER_SUGGESTION_BAR.isEnabled() || !Tab.HOME.isSameAs(tabId)) {
            return MTTabFragment.newInstance(tabId);
        }
        return MTHomesTabFragment.newInstance();
    }

    public int getCount() {
        return this.tabs.size();
    }
}
