package com.airbnb.android.cohosting.pager;

import android.content.Context;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.fragments.CohostLeadsCenterBrowseLeadsFragment;
import com.airbnb.android.cohosting.fragments.CohostLeadsCenterConfirmedLeadsFragment;
import com.airbnb.android.cohosting.fragments.CohostLeadsCenterPendingLeadsFragment;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.adapters.BaseTabFragmentPager;

public class CohostLeadsCenterFragmentPager extends BaseTabFragmentPager {
    public static final TabType[] COHOST_DASHBOARD_VIEW_TYPES = {TabType.Browse, TabType.Pending, TabType.Confirm};

    public enum TabType {
        Browse,
        Pending,
        Confirm
    }

    public CohostLeadsCenterFragmentPager(Context context, FragmentManager fragmentManager) {
        super(context, fragmentManager);
    }

    public int getPageIconId(int position) {
        return 0;
    }

    public int getPageTitleId(int position) {
        TabType tab = COHOST_DASHBOARD_VIEW_TYPES[position];
        switch (tab) {
            case Browse:
                return C5658R.string.cohost_leads_center_title_all_leads;
            case Pending:
                return C5658R.string.cohost_leads_center_title_pending_leads;
            case Confirm:
                return C5658R.string.cohost_leads_center_title_confirmed_leads;
            default:
                throw new UnhandledStateException(tab);
        }
    }

    public boolean isSwipePagingEnabled(int position) {
        return true;
    }

    public Fragment getItem(int position) {
        TabType tab = COHOST_DASHBOARD_VIEW_TYPES[position];
        switch (tab) {
            case Browse:
                return CohostLeadsCenterBrowseLeadsFragment.newInstance();
            case Pending:
                return CohostLeadsCenterPendingLeadsFragment.newInstance();
            case Confirm:
                return CohostLeadsCenterConfirmedLeadsFragment.newInstance();
            default:
                throw new UnhandledStateException(tab);
        }
    }

    public int getCount() {
        return COHOST_DASHBOARD_VIEW_TYPES.length;
    }
}
