package com.airbnb.android.explore.fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.map.ExploreMapMarkerable;
import com.airbnb.android.core.map.ExploreMapView;
import com.airbnb.android.core.map.ExploreMapView.ExploreMapViewDataProvider;
import com.airbnb.android.core.map.ExploreMapView.ExploreMapViewEventCallbacks;
import com.airbnb.android.core.models.ExploreTab;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.Mappable;
import com.airbnb.android.core.models.find.MapBounds;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.wishlists.WishListSnackBarHelper;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.map.HomesMode;
import com.airbnb.android.explore.map.MapMode;
import com.airbnb.android.explore.map.SearchResultMarkerable;
import com.airbnb.p027n2.utils.ScrollDirectionListener;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Collections;
import java.util.List;

public class MTExploreMapFragment extends BaseExploreFragment implements ExploreMapViewDataProvider, ExploreMapViewEventCallbacks {
    private MapMode<SearchResultMarkerable> currentMode;
    private String currentTabId;
    @BindView
    ExploreMapView exploreMapView;
    private SnackbarWrapper noItemsInAreaSnackbar;
    private SnackbarWrapper overfilteringSnackbar;

    public static MTExploreMapFragment newInstance() {
        return new MTExploreMapFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0857R.layout.fragment_mt_map, container, false);
        bindViews(view);
        setToolbar(this.exploreMapView.getToolbar());
        this.exploreMapView.initialize(this, this, getChildFragmentManager(), this.wishListManager, this.recycledViewPool);
        this.exploreMapView.setupNavigationPill();
        CoordinatorLayout snackbarCoordinator = this.exploreMapView.getSnackbarCoordinator();
        this.noItemsInAreaSnackbar = new SnackbarWrapper().view(snackbarCoordinator);
        this.overfilteringSnackbar = new SnackbarWrapper().view(snackbarCoordinator).title(C0857R.string.find_overfiltering_error, true).action(C0857R.string.change_filters, MTExploreMapFragment$$Lambda$1.lambdaFactory$(this));
        WishListSnackBarHelper.registerAndShowWithView(this, snackbarCoordinator, this.wishListManager);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(MTExploreMapFragment mTExploreMapFragment, View snackAction) {
        mTExploreMapFragment.overfilteringSnackbar.dismiss();
        mTExploreMapFragment.exploreNavigationController.showFilters();
    }

    public void onDestroyView() {
        this.exploreNavigationController.closeMap();
        this.exploreMapView.onDestroyView();
        dismissSnackbars();
        WishListSnackBarHelper.unregister(this);
        super.onDestroyView();
    }

    public void onStart() {
        super.onStart();
        updateTripsTogglePill();
    }

    private void updateTripsTogglePill() {
        this.exploreMapView.setFiltersCount(this.dataController.getDetailFiltersCount(this.exploreNavigationController.getActiveTabId()));
    }

    public List<Mappable> getMappables() {
        if (this.currentMode == null) {
            return Collections.emptyList();
        }
        return this.currentMode.getMappables();
    }

    public void setSelectedCarouselPosition(int selectedPosition) {
        this.currentMode.setSelectedPositionOnAdapter(selectedPosition);
    }

    public ExploreMapMarkerable createMarkerable(Mappable mappable) {
        return this.currentMode.createMarkerable(mappable);
    }

    public void onCarouselScrolled(boolean userSwipedLeft, int selectedPosition, Mappable selectedMappable) {
        this.exploreJitneyLogger.swipeListingCarousel(userSwipedLeft ? ScrollDirectionListener.SCROLL_LEFT : ScrollDirectionListener.SCROLL_RIGHT, selectedMappable.getMappableId(), selectedPosition);
    }

    public AirEpoxyAdapter getAdapter() {
        updateCurrentMode();
        if (this.currentMode != null) {
            return this.currentMode.getCarouselAdapter();
        }
        return null;
    }

    private void updateCurrentMode() {
        ExploreTab selectedTab = (ExploreTab) this.dataController.getTabs().get(this.exploreNavigationController.getActiveTabId());
        if (selectedTab != null) {
            if (!selectedTab.getTabId().equals(this.currentTabId)) {
                this.currentTabId = selectedTab.getTabId();
            }
            if (Tab.HOME.isSameAs(this.currentTabId)) {
                this.currentMode = new HomesMode(getContext(), this.dataController, this.exploreNavigationController, this.wishListManager, this.businessTravelAccountManager, this.exploreJitneyLogger);
                this.currentMode.initDataAndAddToCarousel(selectedTab);
                if (shouldFetchFullPage(selectedTab)) {
                    this.dataController.fetchExploreTabs();
                    return;
                }
                return;
            }
            throw new IllegalStateException("There is no map support for " + this.currentTabId);
        }
    }

    private boolean shouldFetchFullPage(ExploreTab tab) {
        return tab.hasNextPage() && getMappables().size() < 16;
    }

    public void onTabContentUpdated(String tabId, boolean fromNetwork, NetworkException exception) {
        if (MTExploreFragment.TABS_WITH_MAP.contains(tabId)) {
            handleDataChanged(fromNetwork);
        }
    }

    public void onTabsLoaded(String currentTabId2, boolean fromNetwork) {
        handleDataChanged(fromNetwork);
    }

    private void handleDataChanged(boolean fromNetwork) {
        dismissSnackbars();
        updateTripsTogglePill();
        this.exploreMapView.onDataChanged();
        if (fromNetwork) {
            showSnackbarIfNeeded();
        }
    }

    private void dismissSnackbars() {
        this.noItemsInAreaSnackbar.dismiss();
        this.overfilteringSnackbar.dismiss();
    }

    private void showSnackbarIfNeeded() {
        if (this.currentMode != null && getMappables().isEmpty()) {
            this.noItemsInAreaSnackbar.title(getResources().getString(C0857R.string.explore_map_no_items_error, new Object[]{this.currentMode.getTypeString()}), true);
            SnackbarWrapper toShow = this.dataController.getTopLevelSearchParams().hasMapBounds() ? this.noItemsInAreaSnackbar : this.overfilteringSnackbar;
            if (!toShow.isShown()) {
                toShow.buildAndShow();
            }
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ExploreMap;
    }

    public void onFiltersButtonClicked() {
        this.exploreJitneyLogger.clickFiltersOnMap();
        this.exploreNavigationController.showFilters();
    }

    public void onListButtonClicked() {
        this.exploreJitneyLogger.toggleToList();
        this.exploreNavigationController.closeModal();
    }

    public void onMapMarkerClicked(Mappable selectedItem) {
        this.exploreJitneyLogger.tapMapMarker(selectedItem.getMappableId());
    }

    public void onRedoSearchClicked(LatLngBounds bounds) {
        this.exploreJitneyLogger.clickMapRedoSearch(bounds.northeast, bounds.southwest);
        this.dataController.setMapBounds(MapBounds.builder().latLngSW(bounds.southwest).latLngNE(bounds.northeast).build());
    }

    public boolean isLoadingData() {
        return this.dataController.areTabsLoading() || this.dataController.isTabSectionLoading(Tab.HOME) || (this.currentMode != null && this.dataController.isTabSectionLoading(this.currentMode.getAssociatedTabId()));
    }
}
