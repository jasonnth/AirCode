package com.airbnb.android.core.fragments;

import android.os.Bundle;
import android.support.p000v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airmapview.NativeGoogleMapFragment;
import com.airbnb.android.contentframework.imagepicker.MediaLoader;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.adapters.MapLocalAttractionViewPagerAdapter;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.beta.models.guidebook.LocalAttraction;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.LocalAttractionsRequest;
import com.airbnb.android.core.responses.LocalAttractionsResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.GoogleMapMarkerManager;
import com.airbnb.android.core.utils.MapMarkerManager;
import com.airbnb.android.core.utils.WebMapMarkerManager;
import com.airbnb.android.core.views.AirbnbMapView;
import com.airbnb.android.core.views.LoopingViewPager;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.google.android.gms.maps.model.LatLng;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class LocalAttractionsFragment extends AirFragment {
    public static final String ARG_ATTRACTIONS = "attractions";
    public static final String ARG_LISTING = "listing";
    final RequestListener<LocalAttractionsResponse> localAttractionsRequestListener = new C0699RL().onResponse(LocalAttractionsFragment$$Lambda$1.lambdaFactory$(this)).onError(LocalAttractionsFragment$$Lambda$2.lambdaFactory$(this)).build();
    /* access modifiers changed from: private */
    public MapLocalAttractionViewPagerAdapter mAdapter;
    @BindView
    AirbnbMapView mAirMapView;
    private ArrayList<LocalAttraction> mAttractions;
    private Listing mListing;
    @State
    int mLoopingListingPosition;
    @BindView
    LoopingViewPager mLoopingViewPager;
    private MapMarkerManager mMapMarkerManager;

    public static Bundle bundleWithListing(Listing listing) {
        Bundle args = new Bundle();
        args.putParcelable("listing", listing);
        return args;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("listing", this.mListing);
        outState.putParcelableArrayList(ARG_ATTRACTIONS, this.mAttractions);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0716R.layout.fragment_local_attractions, container, false);
        bindViews(view);
        AirbnbEventLogger.track("local_attractions", Strap.make().mo11639kv("page", P3Arguments.FROM_MAP).mo11639kv("action", "open"));
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            this.mListing = (Listing) savedInstanceState.getParcelable("listing");
            setupAttractions(savedInstanceState.getParcelableArrayList(ARG_ATTRACTIONS));
            return;
        }
        this.mListing = (Listing) getArguments().getParcelable("listing");
        Check.notNull(this.mListing, "Must specify a listing");
        new LocalAttractionsRequest(this.mListing.getId(), this.localAttractionsRequestListener).doubleResponse().execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$1(LocalAttractionsFragment localAttractionsFragment, AirRequestNetworkException error) {
        Toast.makeText(localAttractionsFragment.getActivity(), C0716R.string.guidebooks_error, 1).show();
        BuildHelper.debugLog("Guidebook", "Error getting recommendations");
    }

    /* access modifiers changed from: private */
    public void setupAttractions(ArrayList<LocalAttraction> attractions) {
        if (!ListUtils.isEmpty((Collection<?>) attractions)) {
            this.mAttractions = attractions;
            getActionBar().setTitle(C0716R.string.guidebook_title);
            getActionBar().setSubtitle((CharSequence) getResources().getQuantityString(C0716R.plurals.x_recommendations, attractions.size(), new Object[]{Integer.valueOf(attractions.size())}));
            setupLoopingViewPager();
            setupMap();
        }
    }

    private void setupLoopingViewPager() {
        this.mAdapter = new MapLocalAttractionViewPagerAdapter(this.mAttractions);
        this.mLoopingViewPager.setAdapter(this.mAdapter);
        this.mLoopingViewPager.setPeekEnabled(false);
        this.mLoopingViewPager.getViewPager().addOnPageChangeListener(new SimpleOnPageChangeListener() {
            public void onPageSelected(int position) {
                LocalAttractionsFragment.this.mLoopingListingPosition = LocalAttractionsFragment.this.mAdapter.getAdjustedPosition(position);
                LocalAttractionsFragment.this.selectAttraction(LocalAttractionsFragment.this.mLoopingListingPosition);
            }
        });
        this.mLoopingViewPager.setViewPagerClickListener(LocalAttractionsFragment$$Lambda$3.lambdaFactory$(this));
        this.mLoopingViewPager.setVisibility(0);
    }

    private LocalAttraction getAttraction(int position) {
        if (this.mMapMarkerManager != null) {
            return (LocalAttraction) this.mAttractions.get(this.mLoopingListingPosition);
        }
        return null;
    }

    private void setupMap() {
        this.mAirMapView.setOnMapInitializedListener(LocalAttractionsFragment$$Lambda$4.lambdaFactory$(this));
        this.mAirMapView.initialize(getChildFragmentManager());
    }

    static /* synthetic */ void lambda$setupMap$4(LocalAttractionsFragment localAttractionsFragment) {
        AirbnbEventLogger.track("local_attractions", Strap.make().mo11639kv("page", P3Arguments.FROM_MAP).mo11639kv("action", "loaded").mo11637kv(MediaLoader.COLUMN_COUNT, localAttractionsFragment.mAttractions.size()));
        localAttractionsFragment.mMapMarkerManager = localAttractionsFragment.getMapMarkerManager();
        localAttractionsFragment.mAirMapView.setCenterZoom(localAttractionsFragment.mListing.getAndroidLatLng(), localAttractionsFragment.getResources().getInteger(C0716R.integer.set_location_zoom_level));
        Iterator it = localAttractionsFragment.mAttractions.iterator();
        while (it.hasNext()) {
            localAttractionsFragment.mMapMarkerManager.addLocalAttractionToMap(localAttractionsFragment.mAirMapView, (LocalAttraction) it.next());
        }
        localAttractionsFragment.mAirMapView.setOnMarkerClickListener(LocalAttractionsFragment$$Lambda$5.lambdaFactory$(localAttractionsFragment));
        localAttractionsFragment.selectAttraction(localAttractionsFragment.mLoopingListingPosition);
    }

    private MapMarkerManager getMapMarkerManager() {
        if (this.mAirMapView.getMapInterface() instanceof NativeGoogleMapFragment) {
            return new GoogleMapMarkerManager(getContext());
        }
        return new WebMapMarkerManager();
    }

    /* access modifiers changed from: private */
    public void selectAttraction(int position) {
        selectAttraction(getAttraction(position));
    }

    /* access modifiers changed from: private */
    public void selectAttraction(LocalAttraction attraction) {
        if (attraction != null) {
            handleMapMarkerClick(attraction);
            scrollViewPagerToAttraction(attraction);
        }
    }

    private void handleMapMarkerClick(LocalAttraction attraction) {
        this.mMapMarkerManager.highlightLocalAttractionMarker(getActivity(), attraction);
        this.mMapMarkerManager.highlightLocalAttractionMarker(getActivity(), attraction);
        this.mAirMapView.animateCenter(new LatLng(attraction.getLatitude(), attraction.getLongitude()));
        AirbnbEventLogger.track("local_attractions", Strap.make().mo11639kv("page", P3Arguments.FROM_MAP).mo11639kv("action", "callout").mo11639kv("attraction_name", attraction.getName()).mo11638kv("attraction_id", attraction.getResourceId()));
        scrollViewPagerToAttraction(attraction);
    }

    private void scrollViewPagerToAttraction(LocalAttraction attraction) {
        long attractionResourceId = attraction.getResourceId();
        MapLocalAttractionViewPagerAdapter adapter = (MapLocalAttractionViewPagerAdapter) this.mLoopingViewPager.getAdapter();
        if (adapter != null) {
            int position = adapter.getLocalAttractionPosition(attractionResourceId);
            if (position > -1) {
                this.mLoopingViewPager.setVisibility(0);
                this.mLoopingViewPager.getViewPager().setCurrentItem(adapter.itemToPagePosition(position), false);
            }
        }
    }
}
