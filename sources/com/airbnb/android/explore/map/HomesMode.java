package com.airbnb.android.explore.map;

import android.content.Context;
import android.view.View;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.ExploreSection;
import com.airbnb.android.core.models.ExploreSection.ResultType;
import com.airbnb.android.core.models.ExploreTab;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Mappable;
import com.airbnb.android.core.models.SearchResult;
import com.airbnb.android.core.utils.MapMarkerGenerator;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.HomeCardEpoxyModel_;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.android.core.wishlists.WishListableType;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.ExploreJitneyLogger;
import com.airbnb.android.explore.adapters.MTExperiencesCarouselAdapter;
import com.airbnb.android.explore.adapters.MTExperiencesCarouselAdapter.PaginationListener;
import com.airbnb.android.explore.controllers.ExploreDataController;
import com.airbnb.android.explore.controllers.ExploreNavigationController;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HomesMode implements PaginationListener, MapMode<SearchResultMarkerable> {
    private final BusinessTravelAccountManager btAccountManager;
    private final MTExperiencesCarouselAdapter carouselAdapter;
    private final Context context;
    private final ExploreDataController dataController;
    private final ExploreJitneyLogger logger;
    private List<Mappable> mappables = Collections.emptyList();
    private final ExploreNavigationController navController;
    private final MapMarkerGenerator searchResultMarkerGenerator;
    private final WishListManager wlManager;

    public HomesMode(Context context2, ExploreDataController dataController2, ExploreNavigationController navigationController, WishListManager wlManager2, BusinessTravelAccountManager btAccountManager2, ExploreJitneyLogger logger2) {
        this.dataController = dataController2;
        this.navController = navigationController;
        this.wlManager = wlManager2;
        this.btAccountManager = btAccountManager2;
        this.logger = logger2;
        this.carouselAdapter = new MTExperiencesCarouselAdapter(new HomeCardEpoxyModel_().loading(true));
        this.carouselAdapter.setPaginationListener(this);
        this.searchResultMarkerGenerator = new MapMarkerGenerator(context2);
        this.context = context2;
    }

    public AirEpoxyAdapter getCarouselAdapter() {
        return this.carouselAdapter;
    }

    public SearchResultMarkerable createMarkerable(Mappable mappable) {
        SearchResult searchResult = (SearchResult) mappable;
        return new SearchResultMarkerable(searchResult, this.wlManager.isItemWishListed(WishListableType.Home, searchResult.getListing().getId()), this.btAccountManager.isVerifiedBusinessTraveler(), this.searchResultMarkerGenerator, this.context);
    }

    public void initDataAndAddToCarousel(ExploreTab tab) {
        List<ExploreSection> sections = tab.getSections();
        if (ListUtils.isEmpty((Collection<?>) sections)) {
            this.mappables = Collections.emptyList();
            this.carouselAdapter.setModels(Collections.emptyList());
            return;
        }
        List<SearchResult> searchResults = getSearchResults(sections);
        ImmutableList<SearchResult> dedupedResult = ImmutableSet.copyOf((Collection<? extends E>) searchResults).asList();
        this.mappables = FluentIterable.from((Iterable<E>) dedupedResult).transform(HomesMode$$Lambda$1.lambdaFactory$()).toList();
        List<EpoxyModel<?>> homeCardItems = new ArrayList<>(dedupedResult.size());
        UnmodifiableIterator it = dedupedResult.iterator();
        while (it.hasNext()) {
            homeCardItems.add(buildListingModel((SearchResult) it.next()));
        }
        if (searchResults.size() > 0 && FeatureToggles.addBlankItemsToCarouselToEnableFullScroll()) {
            SearchResult firstResult = (SearchResult) dedupedResult.get(0);
            int numBlankItems = ((int) ((HomeCardEpoxyModel_) homeCardItems.get(0)).displayOptions().cardsPerRow()) - 1;
            for (int i = 0; i < numBlankItems; i++) {
                homeCardItems.add(buildListingModel(firstResult).m4726id(Long.MAX_VALUE - ((long) i)).invisible(true));
            }
        }
        this.carouselAdapter.setModels(homeCardItems);
    }

    static /* synthetic */ Mappable lambda$initDataAndAddToCarousel$0(SearchResult searchResult) {
        return searchResult;
    }

    private List<SearchResult> getSearchResults(List<ExploreSection> sections) {
        List<SearchResult> searchResults = new ArrayList<>();
        for (ExploreSection section : sections) {
            if (searchResults.size() >= 16) {
                return searchResults.subList(0, 16);
            }
            if (section.getResultType() == ResultType.Listings) {
                searchResults.addAll(section.getListings());
            }
        }
        return searchResults;
    }

    private HomeCardEpoxyModel_ buildListingModel(SearchResult searchResult) {
        Listing listing = searchResult.getListing();
        return new HomeCardEpoxyModel_().listing(listing).pricingQuote(searchResult.getPricingQuote()).wishListableData(WishListableData.forHome(listing).source(C2813WishlistSource.Explore).searchSessionId(this.dataController.getSearchSessionId()).allowAutoAdd(true).build()).showDivider(false).displayOptions(DisplayOptions.forHomeCard(this.context, DisplayType.Horizontal)).clickListener(HomesMode$$Lambda$2.lambdaFactory$(this, listing, searchResult));
    }

    static /* synthetic */ void lambda$buildListingModel$1(HomesMode homesMode, Listing listing, SearchResult searchResult, View v) {
        homesMode.logger.homeClick(listing.getId());
        homesMode.navController.launchP3(v, listing, searchResult.getPricingQuote(), homesMode.dataController.getTopLevelSearchParams(), homesMode.dataController.getSearchSessionId(), P3Arguments.FROM_EXPLORE);
    }

    public void setSelectedPositionOnAdapter(int selectedPosition) {
        int itemCount = this.carouselAdapter.getItemCount();
        int i = 0;
        while (i < itemCount) {
            EpoxyModel<?> model = this.carouselAdapter.getItemAt(i);
            if (model instanceof HomeCardEpoxyModel_) {
                HomeCardEpoxyModel_ item = (HomeCardEpoxyModel_) model;
                boolean wasSelected = item.selectionHighlight();
                boolean shouldBeSelected = selectedPosition == i;
                if (wasSelected != shouldBeSelected) {
                    item.selectionHighlight(shouldBeSelected);
                    this.carouselAdapter.notifyItemChanged(i);
                }
            }
            i++;
        }
    }

    public List<Mappable> getMappables() {
        return this.mappables;
    }

    public String getTypeString() {
        return this.context.getString(C0857R.string.airbnb_homes);
    }

    public String getAssociatedTabId() {
        return Tab.HOME.getTabId();
    }

    public void onPaginationModelBound() {
        String tabId = Tab.HOME.getTabId();
        if (!this.dataController.isTabSectionLoading(tabId)) {
            this.dataController.fetchNextPageForTab(tabId, ((ExploreTab) this.dataController.getTabs().get(tabId)).getPaginationMetadata());
        }
    }
}
