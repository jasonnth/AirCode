package com.airbnb.android.core.map;

import android.util.LongSparseArray;
import com.airbnb.android.airmapview.AirMapMarker;
import com.airbnb.android.airmapview.AirMapView;
import com.airbnb.android.core.models.Mappable;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.views.AirbnbMapView;
import com.airbnb.android.utils.ConcurrentUtil.MainThreadExecutor;
import com.airbnb.android.utils.SimpleFutureCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

public class ExploreGoogleMapMarkerManager extends ExploreMapMarkerManager {
    private static final long NONE = -1;
    private final ListeningExecutorService executor = MoreExecutors.listeningDecorator(Executors.newSingleThreadExecutor());
    private final MainThreadExecutor mainThreadExecutor = new MainThreadExecutor();
    /* access modifiers changed from: private */
    public AirMapView mapView;
    /* access modifiers changed from: private */
    public final LongSparseArray<AirMapMarker<Mappable>> markerableIdToMarkerMap = new LongSparseArray<>();
    private final List<ExploreMapMarkerable> markerables = new ArrayList();
    private long selectedMarkerableId = -1;
    private final HashSet<Long> viewedMarkerableIds = new HashSet<>();

    public void setup(AirbnbMapView mapView2) {
        this.mapView = mapView2;
    }

    public void teardown() {
        clearMarkers();
        this.viewedMarkerableIds.clear();
        this.mapView = null;
    }

    public void addMarker(ExploreMapMarkerable markerable) {
        addMarker(markerable, false);
    }

    public void addMarker(ExploreMapMarkerable markerable, boolean selected) {
        if (!hasMarkerableById(markerable.getId())) {
            this.markerables.add(markerable);
            if (selected) {
                selectMarker(markerable.getId(), false);
            }
            addMarkerableAsync(markerable, selected);
            return;
        }
        if (selected) {
            selectMarker(markerable.getId(), false);
        }
        replaceMarker(markerable);
    }

    private void replaceMarker(ExploreMapMarkerable markerable) {
        if (removeMarkerableById(markerable.getId())) {
            this.markerables.add(markerable);
            setMarkerIconAsync(markerable, isSelected(markerable.getId()));
        }
    }

    public void removeMarker(long markerableId) {
        if (markerableId == this.selectedMarkerableId) {
            this.selectedMarkerableId = -1;
        }
        this.mapView.removeMarker((AirMapMarker) this.markerableIdToMarkerMap.get(markerableId));
        removeMarkerableById(markerableId);
        this.markerableIdToMarkerMap.remove(markerableId);
    }

    public void selectMarker(long markerableId) {
        selectMarker(markerableId, true);
    }

    private void selectMarker(long markerableId, boolean setSelectedMarkerIcon) {
        if (markerableId != this.selectedMarkerableId) {
            if (this.selectedMarkerableId != -1) {
                ExploreMapMarkerable selectedMarkerable = getMarkerableById(this.selectedMarkerableId);
                Check.notNull(selectedMarkerable, "No markerable found with id = " + this.selectedMarkerableId + ". Markerable size is " + this.markerables.size());
                setMarkerIconAsync(selectedMarkerable, false);
            }
            ExploreMapMarkerable markerable = getMarkerableById(markerableId);
            Check.notNull(markerable, "No markerable found with id = " + markerableId + ". Markerable size is " + this.markerables.size());
            if (setSelectedMarkerIcon) {
                setMarkerIconAsync(markerable, true);
            }
            AirMapMarker<Mappable> marker = (AirMapMarker) this.markerableIdToMarkerMap.get(markerableId);
            if (marker != null) {
                marker.getMarker().showInfoWindow();
            }
            this.viewedMarkerableIds.add(Long.valueOf(markerableId));
            this.selectedMarkerableId = markerableId;
        }
    }

    public void clearMarkers() {
        int l = this.markerableIdToMarkerMap.size();
        for (int i = 0; i < l; i++) {
            this.mapView.removeMarker((AirMapMarker) this.markerableIdToMarkerMap.valueAt(i));
        }
        this.selectedMarkerableId = -1;
        this.markerables.clear();
        this.markerableIdToMarkerMap.clear();
    }

    public List<ExploreMapMarkerable> getMarkerables() {
        return new ArrayList(this.markerables);
    }

    private ExploreMapMarkerable getMarkerableById(long markerableId) {
        for (ExploreMapMarkerable markerable : this.markerables) {
            if (markerableId == markerable.getId()) {
                return markerable;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public boolean hasMarkerableById(long markerableId) {
        return getMarkerableById(markerableId) != null;
    }

    private boolean removeMarkerableById(long markerableId) {
        Iterator<ExploreMapMarkerable> iterator = this.markerables.iterator();
        while (iterator.hasNext()) {
            if (markerableId == ((ExploreMapMarkerable) iterator.next()).getId()) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    private boolean isSelected(long markerableId) {
        return this.selectedMarkerableId != -1 && markerableId == this.selectedMarkerableId;
    }

    private void addMarkerableAsync(final ExploreMapMarkerable markerable, boolean selected) {
        Futures.addCallback(this.executor.submit(getMarkerCallable(markerable, selected, this.viewedMarkerableIds.contains(Long.valueOf(markerable.getId())))), new SimpleFutureCallback<AirMapMarker<Mappable>>() {
            public void onSuccess(AirMapMarker<Mappable> marker) {
                if (ExploreGoogleMapMarkerManager.this.hasMarkerableById(markerable.getId()) && marker != null) {
                    ExploreGoogleMapMarkerManager.this.mapView.addMarker(marker);
                    ExploreGoogleMapMarkerManager.this.markerableIdToMarkerMap.put(markerable.getId(), marker);
                }
            }
        }, this.mainThreadExecutor);
    }

    private Callable<AirMapMarker<Mappable>> getMarkerCallable(ExploreMapMarkerable markerable, boolean isHighlighted, boolean isViewed) {
        return ExploreGoogleMapMarkerManager$$Lambda$1.lambdaFactory$(markerable, isHighlighted, isViewed);
    }

    private void setMarkerIconAsync(final ExploreMapMarkerable markerable, final boolean selected) {
        if (markerable != null) {
            Futures.addCallback(this.executor.submit(ExploreGoogleMapMarkerManager$$Lambda$2.lambdaFactory$(markerable, selected, this.viewedMarkerableIds.contains(Long.valueOf(markerable.getId())))), new SimpleFutureCallback<BitmapDescriptor>() {
                public void onSuccess(BitmapDescriptor bitmapDescriptor) {
                    AirMapMarker<?> marker = (AirMapMarker) ExploreGoogleMapMarkerManager.this.markerableIdToMarkerMap.get(markerable.getId());
                    if (marker != null) {
                        marker.getMarker().setIcon(bitmapDescriptor);
                        if (selected) {
                            marker.getMarker().showInfoWindow();
                        }
                    }
                }
            }, this.mainThreadExecutor);
        }
    }
}
