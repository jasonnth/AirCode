package com.airbnb.android.p011p3;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindView;
import com.airbnb.android.airmapview.listeners.OnMapInitializedListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.views.AirbnbMapView;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.StandardRow;
import com.google.android.gms.maps.model.LatLng;
import icepick.State;

/* renamed from: com.airbnb.android.p3.P3MapFragment */
public class P3MapFragment extends P3BaseFragment implements OnMapInitializedListener {
    private static final int CIRCLE_RADIUS_METERS = 800;
    private static final int ZOOM_LEVEL = 14;
    @BindView
    StandardRow addressAndHood;
    @BindView
    AirbnbMapView airMapView;
    private final Runnable airMapViewInitRunnable = new Runnable() {
        public void run() {
            if (P3MapFragment.this.airMapView != null) {
                P3MapFragment.this.airMapView.initialize(P3MapFragment.this.getChildFragmentManager());
            }
        }
    };
    @BindColor
    int circleBorderColor;
    @BindColor
    int circleFillColor;
    @BindDimen
    int circleStrokeWidth;
    private Listing listing;
    @State
    LatLng mapCenter;
    @State
    int mapZoom;
    @BindView
    AirToolbar toolbar;

    public static P3MapFragment newInstance() {
        return new P3MapFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CharSequence subtitle;
        View view = inflater.inflate(C7532R.layout.fragment_p3_map, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.listing = this.controller.getState().listing();
        this.airMapView.setOnMapInitializedListener(this);
        if (savedInstanceState == null) {
            this.airMapView.postDelayed(this.airMapViewInitRunnable, (long) getResources().getInteger(17694722));
        }
        if (this.listing.getNeighborhood() != null) {
            subtitle = getString(C7532R.string.neighborhood_x, this.listing.getNeighborhood());
        } else {
            subtitle = null;
        }
        if (Experiments.showExactLocationDisclaimerOnP3Map()) {
            subtitle = getString(C7532R.string.p3_map_exact_location_disclaimer);
        }
        new StandardRowEpoxyModel_().title((CharSequence) this.listing.getPublicAddress()).subtitle(subtitle).bind(this.addressAndHood);
        return view;
    }

    public void onMapInitialized() {
        LatLng listingLocation = this.listing.getAndroidLatLng();
        if (this.mapCenter != null) {
            this.airMapView.setCenterZoom(this.mapCenter, this.mapZoom);
        } else {
            this.airMapView.setCenterZoom(listingLocation, 14);
        }
        this.airMapView.drawCircle(listingLocation, 800, this.circleBorderColor, this.circleStrokeWidth, this.circleFillColor);
    }

    public void onSaveInstanceState(Bundle outState) {
        if (this.airMapView != null) {
            this.mapCenter = this.airMapView.getCenter();
            this.mapZoom = this.airMapView.getZoom();
        }
        super.onSaveInstanceState(outState);
    }

    public void onDestroyView() {
        this.airMapView.removeCallbacks(this.airMapViewInitRunnable);
        this.airMapView.onDestroyView();
        this.airMapView.setOnMapInitializedListener(null);
        super.onDestroyView();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ListingMap;
    }

    public boolean shouldShowAsDialog(Context context) {
        return false;
    }
}
