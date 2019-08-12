package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.support.p000v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.pager.CohostLeadsCenterFragmentPager;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.views.OptionalSwipingViewPager;

public class CohostLeadsCenterTabsFragment extends AirFragment {
    private CohostLeadsCenterFragmentPager adapter;
    @BindView
    OptionalSwipingViewPager viewPager;

    public static CohostLeadsCenterTabsFragment create() {
        return new CohostLeadsCenterTabsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5658R.layout.fragment_cohost_leads_center_tabs_layout, container, false);
        bindViews(view);
        this.adapter = new CohostLeadsCenterFragmentPager(getActivity(), getChildFragmentManager());
        this.viewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            public void onPageSelected(int position) {
            }
        });
        this.viewPager.setAdapter(this.adapter);
        this.viewPager.setCurrentItem(0);
        return view;
    }
}
