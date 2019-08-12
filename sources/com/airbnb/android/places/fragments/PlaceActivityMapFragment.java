package com.airbnb.android.places.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindColor;
import butterknife.BindView;
import com.airbnb.android.airmapview.listeners.OnMapInitializedListener;
import com.airbnb.android.core.beta.models.guidebook.Place;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.views.AirbnbMapView;
import com.airbnb.android.places.C7627R;
import com.airbnb.android.places.map.PlaceMarkerGenerator;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.StandardRow;
import com.google.android.gms.maps.model.LatLng;
import icepick.State;

public class PlaceActivityMapFragment extends BasePlaceActivityFragment implements OnMapInitializedListener {
    private static final String ARG_PLACE = "place";
    private static final int ZOOM_LEVEL = 14;
    @BindColor
    int actionableTextColor;
    private final Runnable airMapViewInitRunnable = PlaceActivityMapFragment$$Lambda$1.lambdaFactory$(this);
    @State
    LatLng mapCenter;
    @BindView
    AirbnbMapView mapView;
    @State
    int mapZoom;
    private PlaceMarkerGenerator markerGenerator;
    private Place place;
    @BindView
    StandardRow placeDetailsRow;
    @BindView
    AirToolbar toolbar;

    public static PlaceActivityMapFragment newInstance(Place place2) {
        return (PlaceActivityMapFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PlaceActivityMapFragment()).putParcelable(ARG_PLACE, place2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.place = (Place) getArguments().getParcelable(ARG_PLACE);
        Check.notNull(this.place);
        View view = inflater.inflate(C7627R.layout.fragment_place_activity_map, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.mapView.setOnMapInitializedListener(this);
        if (savedInstanceState == null) {
            this.mapView.postDelayed(this.airMapViewInitRunnable, (long) getResources().getInteger(17694722));
        }
        this.markerGenerator = new PlaceMarkerGenerator(getContext(), this.mapView);
        CharSequence placeNameWithAirmoji = new StringBuilder(this.place.getName()).append("  ").append(this.place.getAirmojiForDisplay());
        new StandardRowEpoxyModel_().title(placeNameWithAirmoji).subtitle(SpannableUtils.makeColoredString(this.place.getAddressPlusCity(), this.actionableTextColor)).clickListener(PlaceActivityMapFragment$$Lambda$2.lambdaFactory$(this)).bind(this.placeDetailsRow);
        return view;
    }

    static /* synthetic */ void lambda$new$1(PlaceActivityMapFragment placeActivityMapFragment) {
        if (placeActivityMapFragment.mapView != null) {
            placeActivityMapFragment.mapView.initialize(placeActivityMapFragment.getChildFragmentManager());
        }
    }

    public void onMapInitialized() {
        if (this.mapCenter != null) {
            this.mapView.setCenterZoom(this.mapCenter, this.mapZoom);
        } else {
            this.mapView.setCenterZoom(new LatLng(this.place.getLatitude(), this.place.getLongitude()), 14);
        }
        this.mapView.addMarker(this.markerGenerator.buildMarker(this.place));
    }

    public void onSaveInstanceState(Bundle outState) {
        if (this.mapView != null) {
            this.mapCenter = this.mapView.getCenter();
            this.mapZoom = this.mapView.getZoom();
        }
        super.onSaveInstanceState(outState);
    }

    public void onDestroyView() {
        this.mapView.removeCallbacks(this.airMapViewInitRunnable);
        this.mapView.onDestroyView();
        this.mapView.setOnMapInitializedListener(null);
        super.onDestroyView();
    }
}
