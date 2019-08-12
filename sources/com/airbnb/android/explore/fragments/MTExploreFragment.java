package com.airbnb.android.explore.fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.view.ViewPager.SimpleOnPageChangeListener;
import android.support.p002v7.widget.RecyclerView.RecycledViewPool;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ExploreTab;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.views.OptionalSwipingViewPager;
import com.airbnb.android.core.wishlists.WishListSnackBarHelper;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.ExploreJitneyLogger;
import com.airbnb.android.explore.adapters.MTExploreTabsPagerAdapter;
import com.airbnb.android.explore.controllers.ExploreControllerInterface;
import com.airbnb.android.explore.controllers.ExploreDataController;
import com.airbnb.android.explore.controllers.ExploreNavigationController;
import com.airbnb.android.explore.controllers.ExploreNavigationController.ExploreMode;
import com.airbnb.android.explore.views.ExploreScrollController;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.components.NavigationPill;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.collect.ImmutableSet;
import java.util.Set;

public class MTExploreFragment extends BaseExploreFragment implements ExploreControllerInterface {
    public static final Set<String> TABS_WITH_FILTERS = ImmutableSet.m1300of(Tab.EXPERIENCE.getTabId(), Tab.HOME.getTabId(), Tab.PLACES.getTabId());
    public static final Set<String> TABS_WITH_MAP = ImmutableSet.m1299of(Tab.HOME.getTabId());
    @BindView
    CoordinatorLayout coordinatorLayout;
    @BindView
    NavigationPill navigationPill;
    /* access modifiers changed from: private */
    public String previousTabId = "";
    @BindView
    OptionalSwipingViewPager viewPager;

    public static MTExploreFragment newInstance() {
        return new MTExploreFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0857R.layout.fragment_mt_explore, container, false);
        bindViews(view);
        WishListSnackBarHelper.registerAndShowWithView(this, this.coordinatorLayout, this.wishListManager);
        this.viewPager.setSwipingEnabled(false);
        if (Experiments.pillShowThreeButtons()) {
            this.navigationPill.setLeftButtonText(getString(C0857R.string.list_pill_caps));
            this.navigationPill.setLeftButtonIcon(C0857R.C0858drawable.n2_navigation_pill_list_icon_babu);
            this.navigationPill.setLeftButtonTextColor(C0857R.color.n2_jellyfish_babu_bg);
            this.navigationPill.setLeftButtonClickListener(MTExploreFragment$$Lambda$1.lambdaFactory$());
            this.navigationPill.setMiddleButtonText(getString(C0857R.string.map_pill_caps));
            this.navigationPill.setMiddleButtonIcon(C0857R.C0858drawable.n2_navigation_pill_map_icon);
            this.navigationPill.setMiddleButtonClickListener(MTExploreFragment$$Lambda$2.lambdaFactory$(this));
        } else {
            this.navigationPill.setMode(2);
            this.navigationPill.setLeftButtonClickListener(MTExploreFragment$$Lambda$3.lambdaFactory$(this));
        }
        this.navigationPill.setRightButtonClickListener(MTExploreFragment$$Lambda$4.lambdaFactory$(this));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(View v) {
    }

    static /* synthetic */ void lambda$onCreateView$1(MTExploreFragment mTExploreFragment, View v) {
        mTExploreFragment.exploreJitneyLogger.toggleToMap();
        mTExploreFragment.exploreNavigationController.showMap();
    }

    static /* synthetic */ void lambda$onCreateView$2(MTExploreFragment mTExploreFragment, View v) {
        mTExploreFragment.exploreJitneyLogger.toggleToMap();
        mTExploreFragment.exploreNavigationController.showMap();
    }

    static /* synthetic */ void lambda$onCreateView$3(MTExploreFragment mTExploreFragment, View v) {
        mTExploreFragment.exploreJitneyLogger.clickFiltersOnList();
        mTExploreFragment.exploreNavigationController.showFilters();
    }

    public void onDestroyView() {
        this.scrollController.clearViewPager();
        WishListSnackBarHelper.unregister(this);
        super.onDestroyView();
    }

    public void onStart() {
        super.onStart();
        updateTripsTogglePill();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupTabBar();
        this.scrollController.setViewPager(this.viewPager);
    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            updateTripsTogglePill();
        }
    }

    private void setupTabBar() {
        final MTExploreTabsPagerAdapter pagerAdapter = new MTExploreTabsPagerAdapter(getActivity(), getChildFragmentManager(), this.dataController);
        this.viewPager.setAdapter(pagerAdapter);
        this.viewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
            public void onPageSelected(int position) {
                if (pagerAdapter.getCount() > 0) {
                    Fragment fragment = pagerAdapter.getFragment(position);
                    if (fragment != null) {
                        fragment.setUserVisibleHint(true);
                        String tabId = MTExploreFragment.this.dataController.getTabIdAtPosition(position);
                        if (tabId != null && !tabId.equals(MTExploreFragment.this.previousTabId) && Tab.HOME.isSameAs(tabId)) {
                            MTExploreFragment.this.exploreJitneyLogger.homeResultsImpression();
                            MTExploreFragment.this.exploreMParticleLogger.homeImpression(tabId, MTExploreFragment.this.getContext());
                        }
                        MTExploreFragment.this.previousTabId = tabId;
                    }
                }
            }
        });
        ((MTExploreParentFragment) getParentFragment()).getTabLayout().setViewPager(this.viewPager);
        this.viewPager.setCurrentItem(this.dataController.getPositionOfTabId(this.exploreNavigationController.getActiveTabId()), true);
    }

    public void onTabsLoaded(String currentTabId, boolean fromNetwork) {
        setupTabBar();
        updateTripsTogglePill();
        if (fromNetwork && Tab.HOME.isSameAs(this.exploreNavigationController.getActiveTabId())) {
            this.exploreJitneyLogger.homeResultsImpression();
            this.exploreMParticleLogger.homeImpression(this.exploreNavigationController.getActiveTabId(), getContext());
        }
    }

    public void onTabContentUpdated(String tabId, boolean fromNetwork, NetworkException exception) {
        updateTripsTogglePill();
    }

    public void onExploreNavStateUpdated(ExploreMode mode, String activeTabId) {
        int positionToSelect = this.dataController.getPositionOfTabId(activeTabId);
        if (this.viewPager.getCurrentItem() != positionToSelect) {
            this.viewPager.setCurrentItem(positionToSelect, true);
        }
        updateTripsTogglePill();
    }

    public ExploreNavigationController getNavigationController() {
        return this.exploreNavigationController;
    }

    public ExploreDataController getDataController() {
        return this.dataController;
    }

    public ExploreScrollController getScrollController() {
        return this.scrollController;
    }

    public ExploreJitneyLogger getLogger() {
        return this.exploreJitneyLogger;
    }

    public RecycledViewPool getViewPool() {
        return this.recycledViewPool;
    }

    private void updateTripsTogglePill() {
        boolean shouldHideFilters;
        boolean hasMap;
        boolean z = false;
        String currentTabId = this.exploreNavigationController.getActiveTabId();
        ExploreTab currentTab = this.dataController.findTabForId(currentTabId);
        if (currentTab == null) {
            this.navigationPill.setVisibility(8);
            return;
        }
        if (currentTab.getEmptyStateMetadata() == null || !currentTab.hasResults()) {
            shouldHideFilters = false;
        } else {
            shouldHideFilters = true;
        }
        if (!TABS_WITH_MAP.contains(currentTabId) || !currentTab.hasResults()) {
            hasMap = false;
        } else {
            hasMap = true;
        }
        boolean hasFilters = TABS_WITH_FILTERS.contains(currentTabId);
        NavigationPill navigationPill2 = this.navigationPill;
        if (!shouldHideFilters && (hasMap || hasFilters)) {
            z = true;
        }
        ViewLibUtils.setVisibleIf(navigationPill2, z);
        if (this.navigationPill.getVisibility() == 0) {
            if (hasMap) {
                this.navigationPill.setMode(Experiments.pillShowThreeButtons() ? 3 : 2);
            } else {
                this.navigationPill.setMode(1);
            }
            this.navigationPill.setRightButtonBadgeCount(this.dataController.getDetailFiltersCount(currentTabId));
        }
    }

    public boolean shouldShowBottomBar() {
        return true;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ExplorePage;
    }
}
