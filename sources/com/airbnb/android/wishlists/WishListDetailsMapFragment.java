package com.airbnb.android.wishlists;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.map.ExploreMapMarkerable;
import com.airbnb.android.core.map.ExploreMapView;
import com.airbnb.android.core.map.ExploreMapView.ExploreMapViewDataProvider;
import com.airbnb.android.core.map.ExploreMapView.ExploreMapViewEventCallbacks;
import com.airbnb.android.core.models.Mappable;
import com.airbnb.android.core.models.WishlistedListing;
import com.airbnb.android.core.utils.MapMarkerGenerator;
import com.airbnb.android.core.viewcomponents.SimpleAirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.HomeCardEpoxyModel_;
import com.airbnb.android.core.wishlists.WishListSnackBarHelper;
import com.airbnb.android.core.wishlists.WishListableData;
import com.airbnb.android.core.wishlists.WishListableType;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;
import com.airbnb.p027n2.transitions.AutoSharedElementCallback;
import com.airbnb.p027n2.utils.ScrollDirectionListener;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WishListDetailsMapFragment extends BaseWishListDetailsFragment implements ExploreMapViewDataProvider, ExploreMapViewEventCallbacks {
    private static final String EXTRA_LISTINGS = "EXTRA_LISTINGS";
    private List<WishlistedListing> listings;
    @BindView
    ExploreMapView mapView;
    private List<Mappable> mappables;
    private MapMarkerGenerator markerGenerator;
    private int selectedItemPosition = 0;
    private final SimpleAirEpoxyAdapter simpleAirEpoxyAdapter = new SimpleAirEpoxyAdapter(false);

    public static Fragment instance(List<WishlistedListing> listings2) {
        return ((FragmentBundleBuilder) FragmentBundler.make(new WishListDetailsMapFragment()).putParcelableArrayList(EXTRA_LISTINGS, new ArrayList(listings2))).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.listings = ImmutableList.copyOf((Collection<? extends E>) getArguments().getParcelableArrayList(EXTRA_LISTINGS));
        this.mappables = ImmutableList.copyOf((Collection<? extends E>) this.listings);
        this.markerGenerator = new MapMarkerGenerator(getContext());
        updateAdapter();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1758R.layout.fragment_wish_list_details_map, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.mapView.getToolbar());
        this.mapView.initialize(this, this, getChildFragmentManager(), this.wishListManager, null);
        this.mapView.setFiltersEnabled(false);
        this.mapView.setRedoSearchEnabled(false);
        WishListSnackBarHelper.registerAndShowWithView(this, this.mapView.getSnackbarCoordinator(), this.wishListManager);
        return view;
    }

    public void onDestroyView() {
        this.mapView.onDestroyView();
        WishListSnackBarHelper.unregister(this);
        super.onDestroyView();
    }

    /* access modifiers changed from: protected */
    public void onFragmentClosed() {
        this.parentFragment.getWlLogger().clickCloseMap(getWishList());
    }

    public boolean isLoadingData() {
        return false;
    }

    public List<Mappable> getMappables() {
        return this.mappables;
    }

    public ExploreMapMarkerable createMarkerable(Mappable mappable) {
        WishlistedListing searchResult = (WishlistedListing) mappable;
        return new WishListedListingMapMarker(searchResult, this.wishListManager.isItemWishListed(WishListableType.Home, searchResult.getListing().getId()), this.markerGenerator, getContext());
    }

    public void onFiltersButtonClicked() {
    }

    public void onListButtonClicked() {
    }

    public void onMapMarkerClicked(Mappable selectedItem) {
        this.parentFragment.getWlLogger().clickMapMarker(getWishList(), (WishlistedListing) selectedItem);
    }

    public void onRedoSearchClicked(LatLngBounds bounds) {
    }

    public void onCarouselScrolled(boolean userSwipedLeft, int selectedPosition, Mappable selectedMappable) {
        this.parentFragment.getWlLogger().swipeMapCarousel(getWishList(), userSwipedLeft ? ScrollDirectionListener.SCROLL_LEFT : ScrollDirectionListener.SCROLL_RIGHT, selectedPosition);
    }

    public EpoxyAdapter getAdapter() {
        return this.simpleAirEpoxyAdapter;
    }

    public void setSelectedCarouselPosition(int selectedPosition) {
        this.selectedItemPosition = selectedPosition;
        updateAdapter();
    }

    private void updateAdapter() {
        boolean z;
        List<HomeCardEpoxyModel_> models = new ArrayList<>(this.listings.size());
        for (int i = 0; i < this.listings.size(); i++) {
            WishlistedListing listing = (WishlistedListing) this.listings.get(i);
            if (i == this.selectedItemPosition) {
                z = true;
            } else {
                z = false;
            }
            models.add(buildListingModel(listing, z));
        }
        if (this.listings.size() > 0 && FeatureToggles.addBlankItemsToCarouselToEnableFullScroll()) {
            WishlistedListing firstlisting = (WishlistedListing) this.listings.get(0);
            int numBlankItems = ((int) ((HomeCardEpoxyModel_) models.get(0)).displayOptions().cardsPerRow()) - 1;
            for (int i2 = 0; i2 < numBlankItems; i2++) {
                models.add(buildListingModel(firstlisting, false).m4726id(Long.MAX_VALUE - ((long) i2)).invisible(true));
            }
        }
        this.simpleAirEpoxyAdapter.setModels(models);
    }

    private HomeCardEpoxyModel_ buildListingModel(WishlistedListing wishlistedListing, boolean selected) {
        return new HomeCardEpoxyModel_().listing(wishlistedListing.getListing()).m4726id(wishlistedListing.getId()).pricingQuote(wishlistedListing.getPricingQuote()).wishListableData(WishListableData.forHome(wishlistedListing.getListing()).source(C2813WishlistSource.SavedHomes).allowAutoAdd(true).build()).showDivider(false).selectionHighlight(selected).displayOptions(DisplayOptions.forHomeCard(getContext(), DisplayType.Horizontal)).clickListener(WishListDetailsMapFragment$$Lambda$1.lambdaFactory$(this, wishlistedListing));
    }

    static /* synthetic */ void lambda$buildListingModel$0(WishListDetailsMapFragment wishListDetailsMapFragment, WishlistedListing wishlistedListing, View v) {
        wishListDetailsMapFragment.parentFragment.getWlLogger().clickMapHomeCard(wishListDetailsMapFragment.getWishList(), wishlistedListing);
        wishListDetailsMapFragment.getContext().startActivity(P3ActivityIntents.withListingPricingAndGuests(wishListDetailsMapFragment.getContext(), wishlistedListing.getListing(), wishlistedListing.getPricingQuote(), wishListDetailsMapFragment.getWishList().getGuestDetails(), "wishlist"), AutoSharedElementCallback.getActivityOptionsBundle(wishListDetailsMapFragment.getActivity(), v));
    }
}
