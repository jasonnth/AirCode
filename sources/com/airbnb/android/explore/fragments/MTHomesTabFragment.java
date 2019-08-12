package com.airbnb.android.explore.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.models.CategorizedFilters;
import com.airbnb.android.core.models.ExploreSection;
import com.airbnb.android.core.models.ExploreSection.ResultType;
import com.airbnb.android.core.models.ExploreTab;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.FilterSuggestionItem;
import com.airbnb.android.core.models.find.SearchFilters;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.ExploreComponent.Builder;
import com.airbnb.android.explore.adapters.CategorizedFiltersCarouselController;
import com.airbnb.android.lib.ExploreBindings;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import icepick.State;
import java.util.Collection;
import java.util.List;

public class MTHomesTabFragment extends MTTabFragment {
    private static final int SCROLL_THRESHOLD = 30;
    private CategorizedFiltersCarouselController carouselController;
    @State
    int dummyState;
    private List<CategorizedFilters> filterItems;
    @BindView
    AirTextView filtersButton;
    @BindView
    Carousel filtersCarousel;
    @BindView
    AirImageView filtersIconButton;
    /* access modifiers changed from: private */
    public int overallXScroll;

    public static MTHomesTabFragment newInstance() {
        return (MTHomesTabFragment) ((FragmentBundleBuilder) FragmentBundler.make(new MTHomesTabFragment()).putString("tab_id", Tab.HOME.getTabId())).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((ExploreBindings) CoreApplication.instance(getContext()).componentProvider()).exploreComponentProvider().get()).build().inject(this);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupFilterBar();
    }

    /* access modifiers changed from: protected */
    public int getLayout() {
        return C0857R.layout.fragment_mt_tab_homes;
    }

    @OnClick
    public void onFiltersClick() {
        this.exploreJitneyLogger.clickFiltersOnList();
        this.exploreNavigationController.showFilters();
    }

    public void onTabContentUpdated(String tabId, boolean fromNetwork, NetworkException exception) {
        super.onTabContentUpdated(tabId, fromNetwork, exception);
        if (Tab.HOME.isSameAs(tabId)) {
            syncFilterState();
        }
    }

    public void onTabsLoaded(String currentTabId, boolean fromNetwork) {
        super.onTabsLoaded(currentTabId, fromNetwork);
        syncFilterState();
    }

    private void setupFilterBar() {
        this.carouselController = new CategorizedFiltersCarouselController(MTHomesTabFragment$$Lambda$1.lambdaFactory$(this));
        this.filtersCarousel.addOnScrollListener(new OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                MTHomesTabFragment.this.overallXScroll = MTHomesTabFragment.this.overallXScroll + dx;
                boolean scrollLeft = MTHomesTabFragment.this.overallXScroll > 30;
                ViewLibUtils.setGoneIf(MTHomesTabFragment.this.filtersButton, scrollLeft);
                ViewLibUtils.setVisibleIf(MTHomesTabFragment.this.filtersIconButton, scrollLeft);
            }
        });
        this.filtersCarousel.swapAdapter(this.carouselController.getAdapter(), true);
        syncFilterState();
    }

    static /* synthetic */ void lambda$setupFilterBar$0(MTHomesTabFragment mTHomesTabFragment, View view, FilterSuggestionItem item) {
        item.setSelected(!item.isSelected());
        for (CategorizedFilters categorizedFilters : mTHomesTabFragment.filterItems) {
            if (mTHomesTabFragment.isMatchingFilter(item, categorizedFilters)) {
                if (item.isSelected()) {
                    categorizedFilters.getItems().clear();
                    categorizedFilters.getItems().add(item);
                } else {
                    categorizedFilters.clearFilterItem(item);
                }
            }
        }
        mTHomesTabFragment.carouselController.setData(mTHomesTabFragment.filterItems);
        if (item.isSelected()) {
            mTHomesTabFragment.dataController.onFilterSuggestionPillClicked(item);
        } else {
            mTHomesTabFragment.dataController.onFilterRemovalPillClicked(item);
        }
    }

    private boolean isMatchingFilter(FilterSuggestionItem item, CategorizedFilters categorizedFilters) {
        return (categorizedFilters.isRoomTypeFilters() && item.getFilters().hasRoomTypes()) || (categorizedFilters.isBedroomFilters() && item.getFilters().hasNumBedrooms()) || (categorizedFilters.isBedFilters() && item.getFilters().hasNumBeds());
    }

    private void syncFiltersSelectedState() {
        SearchFilters homesSearchFilters = this.dataController.getHomesSearchFilters();
        for (CategorizedFilters categorizedFilters : this.filterItems) {
            if (categorizedFilters.isRoomTypeFilters()) {
                categorizedFilters.setRoomTypeFilters(homesSearchFilters.getRoomTypes());
            } else if (categorizedFilters.isBedroomFilters()) {
                categorizedFilters.setBedroomFilters(homesSearchFilters.getNumBedrooms());
            } else if (categorizedFilters.isBedFilters()) {
                categorizedFilters.setBedFilters(homesSearchFilters.getNumBeds());
            }
        }
        this.carouselController.setData(this.filterItems);
    }

    private void syncFilterState() {
        ExploreTab homeTab = this.dataController.findTab(Tab.HOME);
        if (homeTab != null) {
            List<ExploreSection> sections = homeTab.getSections();
            if (!ListUtils.isEmpty((Collection<?>) sections)) {
                for (ExploreSection section : sections) {
                    if (section.getResultType() == ResultType.FilterBarSuggestions) {
                        List<CategorizedFilters> apiReturnedFilters = section.getFilterBarSuggestions();
                        if (!ListUtils.isEmpty((Collection<?>) apiReturnedFilters)) {
                            this.filterItems = apiReturnedFilters;
                            syncFiltersSelectedState();
                            return;
                        }
                        return;
                    }
                }
            }
        }
    }
}
