package com.airbnb.android.core.utils;

import android.content.Context;
import android.support.p000v4.util.ArrayMap;
import com.airbnb.android.airmapview.AirMapMarker;
import com.airbnb.android.airmapview.AirMapMarker.Builder;
import com.airbnb.android.airmapview.AirMapView;
import com.airbnb.android.airmapview.NativeGoogleMapFragment;
import com.airbnb.android.core.beta.models.guidebook.LocalAttraction;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.MapMarkerable;
import com.airbnb.android.utils.ConcurrentUtil.MainThreadExecutor;
import com.airbnb.android.utils.SimpleFutureCallback;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;

public class GoogleMapMarkerManager<T extends MapMarkerable> extends MapMarkerManager<T> {
    private final ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
    private final Map<Long, AirMapMarker<?>> localAttractionToMarkerMap = new ArrayMap();
    private AirMapMarker<?> mCurrentHighlightedLocalAttractionMarker;
    private final MainThreadExecutor mainThreadExecutor = new MainThreadExecutor();
    private final MapMarkerGenerator mapMarkerGenerator;
    private final Map<AirMapMarker<?>, LocalAttraction> markerToLocalAttractionsMap = new ArrayMap();
    /* access modifiers changed from: private */
    public final Map<AirMapMarker<?>, T> markerToMarkerablesMap = new ArrayMap();
    /* access modifiers changed from: private */
    public final Map<Long, AirMapMarker<?>> markerableToMarkerMap = new ArrayMap();
    private final Map<Long, Long> viewedMarkerables = new ArrayMap();

    public GoogleMapMarkerManager(Context context) {
        this.mapMarkerGenerator = new MapMarkerGenerator(context);
    }

    public void addMarkerableToMap(AirMapView airMapView, T markerable) {
        if (!this.markerToMarkerablesMap.containsValue(markerable)) {
            createListingMarker(airMapView, markerable);
        }
    }

    public void addLocalAttractionToMap(AirMapView airMapView, LocalAttraction attraction) {
        if (!this.markerToLocalAttractionsMap.containsValue(attraction)) {
            AirMapMarker<LocalAttraction> marker = new Builder().mo19456id(attraction.getResourceId()).object(attraction).position(new LatLng(attraction.getLatitude(), attraction.getLongitude())).anchor(0.5f, 1.0f).bitmapDescriptor(BitmapDescriptorFactory.fromBitmap(MapMarkerGenerator.getLocalAttractionMarker(airMapView.getContext(), attraction, false))).build();
            airMapView.addMarker(marker);
            this.markerToLocalAttractionsMap.put(marker, attraction);
            this.localAttractionToMarkerMap.put(Long.valueOf(attraction.getResourceId()), marker);
        }
    }

    public void clear() {
        this.markerToMarkerablesMap.clear();
        this.markerableToMarkerMap.clear();
    }

    private void createListingMarker(final AirMapView airMapView, final T markerable) {
        boolean viewed = this.viewedMarkerables.containsKey(Long.valueOf(markerable.getId()));
        Listing listing = getListingFromMarkerable(markerable);
        GoogleMap googleMap = ((NativeGoogleMapFragment) airMapView.getMapInterface()).getGoogleMap();
        if (listing != null && googleMap != null) {
            Futures.addCallback(this.executor.submit(GoogleMapMarkerManager$$Lambda$1.lambdaFactory$(this, markerable, airMapView.getContext(), viewed, listing)), new SimpleFutureCallback<AirMapMarker<?>>() {
                public void onSuccess(AirMapMarker<?> marker) {
                    airMapView.addMarker(marker);
                    GoogleMapMarkerManager.this.markerToMarkerablesMap.put(marker, markerable);
                    GoogleMapMarkerManager.this.markerableToMarkerMap.put(Long.valueOf(markerable.getId()), marker);
                }
            }, this.mainThreadExecutor);
        }
    }

    static /* synthetic */ AirMapMarker lambda$createListingMarker$0(GoogleMapMarkerManager googleMapMarkerManager, MapMarkerable markerable, Context context, boolean viewed, Listing listing) throws Exception {
        Context context2 = context;
        return new Builder().object(markerable).mo19456id(markerable.getId()).position(listing.getAndroidLatLng()).anchor(0.5f, 1.0f).bitmap(googleMapMarkerManager.mapMarkerGenerator.getPriceMarker(context2, markerable.getPrice(), viewed, markerable.isInstantBookable(), false, false)).build();
    }

    public void highlightListingMarker(Context context, AirMapView airMapView, T prevMarkerable, T currMarkerable) {
        this.viewedMarkerables.put(Long.valueOf(currMarkerable.getId()), Long.valueOf(System.currentTimeMillis()));
        setListingMarkerIcon(context, prevMarkerable, false);
        setListingMarkerIcon(context, currMarkerable, true);
        AirMapMarker<?> marker = (AirMapMarker) this.markerableToMarkerMap.get(Long.valueOf(currMarkerable.getId()));
        if (marker != null) {
            marker.getMarker().showInfoWindow();
        }
    }

    public void highlightLocalAttractionMarker(Context context, LocalAttraction attraction) {
        if (context != null) {
            if (this.mCurrentHighlightedLocalAttractionMarker != null) {
                this.mCurrentHighlightedLocalAttractionMarker.getMarker().setIcon(BitmapDescriptorFactory.fromBitmap(MapMarkerGenerator.getLocalAttractionMarker(context, (LocalAttraction) this.markerToLocalAttractionsMap.get(this.mCurrentHighlightedLocalAttractionMarker), false)));
            }
            this.mCurrentHighlightedLocalAttractionMarker = (AirMapMarker) this.localAttractionToMarkerMap.get(Long.valueOf(attraction.getResourceId()));
            this.mCurrentHighlightedLocalAttractionMarker.getMarker().setIcon(BitmapDescriptorFactory.fromBitmap(MapMarkerGenerator.getLocalAttractionMarker(context, attraction, true)));
        }
    }

    private void setListingMarkerIcon(Context context, T markerable, boolean highlighted) {
        if (markerable != null) {
            final AirMapMarker<?> marker = (AirMapMarker) this.markerableToMarkerMap.get(Long.valueOf(markerable.getId()));
            if (marker != null) {
                boolean shouldShowIb = markerable.isInstantBookable();
                Futures.addCallback(this.executor.submit(GoogleMapMarkerManager$$Lambda$2.lambdaFactory$(this, context, markerable, this.viewedMarkerables.containsKey(Long.valueOf(markerable.getId())), shouldShowIb, highlighted)), new SimpleFutureCallback<BitmapDescriptor>() {
                    public void onSuccess(BitmapDescriptor bitmapDescriptor) {
                        marker.getMarker().setIcon(bitmapDescriptor);
                    }
                }, this.mainThreadExecutor);
            }
        }
    }

    private Listing getListingFromMarkerable(MapMarkerable mapMarkerable) {
        if (mapMarkerable != null) {
            return mapMarkerable.getListing();
        }
        return null;
    }

    public Set<AirMapMarker<?>> getAllMarkers() {
        return this.markerToMarkerablesMap.keySet();
    }
}
