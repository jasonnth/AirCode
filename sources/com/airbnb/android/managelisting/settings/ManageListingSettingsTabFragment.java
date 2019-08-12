package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p000v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindColor;
import butterknife.BindView;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.listing.ListingDisplayUtils;
import com.airbnb.android.core.views.AirbnbSlidingTabLayout;
import com.airbnb.android.core.views.OptionalSwipingViewPager;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.ManageListingGraph;
import com.airbnb.android.managelisting.settings.ManageListingSettingsFragment.ManageListingPage;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;

public class ManageListingSettingsTabFragment extends ManageListingBaseFragment {
    /* access modifiers changed from: private */
    public ManageListingFragmentPager adapter;
    CohostingManagementJitneyLogger cohostingManagementJitneyLogger;
    @State
    boolean initialDataLoaded;
    @BindColor
    int selectedColorForSelectListing;
    @BindView
    AirbnbSlidingTabLayout tabLayout;
    @BindView
    AirToolbar toolbar;
    @BindColor
    int unselectedColorForSelectListing;
    @BindView
    OptionalSwipingViewPager viewPager;

    public static ManageListingSettingsTabFragment create() {
        return new ManageListingSettingsTabFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ManageListingGraph) CoreApplication.instance().component()).inject(this);
        View view = inflater.inflate(C7368R.layout.fragment_manage_listing_dls, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.adapter = new ManageListingFragmentPager(getActivity(), getChildFragmentManager());
        this.viewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            public void onPageSelected(int position) {
                ManageListingSettingsTabFragment.this.trackExplicitNavigation(ManageListingSettingsTabFragment.this.adapter.getPageAnalyticsTag(position));
                if (ManageListingSettingsTabFragment.this.controller.initialDataLoaded) {
                    ManageListingSettingsTabFragment.this.logCohostingTabImpression(position);
                }
            }
        });
        this.viewPager.setAdapter(this.adapter);
        this.viewPager.setCurrentItem(0);
        return view;
    }

    public void showPage(ManageListingPage page) {
        this.viewPager.setCurrentItem(this.adapter.getPagePosition(page));
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public void dataUpdated() {
        super.dataUpdated();
        if (!this.initialDataLoaded) {
            logCohostingTabImpression(this.viewPager.getCurrentItem());
        }
        this.initialDataLoaded = true;
        String marketplaceTitle = ListingDisplayUtils.getNameOrPlaceholder(getContext(), this.controller.getListing());
        AirToolbar airToolbar = this.toolbar;
        if (this.controller.shouldShowSelectML()) {
            marketplaceTitle = this.controller.selectListing.getName();
        }
        airToolbar.setTitle((CharSequence) marketplaceTitle);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Ignore;
    }

    /* access modifiers changed from: private */
    public void trackExplicitNavigation(NavigationTag tag) {
        Strap strap = new Strap();
        Listing listing = this.controller.getListing();
        if (listing != null) {
            strap.mo11638kv("listing_id", listing.getId());
        }
        this.navigationAnalytics.trackImpressionExplicitly(tag, strap);
    }

    /* access modifiers changed from: private */
    public void logCohostingTabImpression(int position) {
        if (this.adapter.getPageType(position) == ManageListingPage.BookingSettings) {
            this.cohostingManagementJitneyLogger.logCohostsTabInManageListingSettingPageImpressionForDlsML(this.controller.getListingId());
        }
    }
}
