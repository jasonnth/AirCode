package com.airbnb.android.airmapview;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.p000v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.airbnb.android.airmapview.listeners.InfoWindowCreator;
import com.airbnb.android.airmapview.listeners.OnCameraChangeListener;
import com.airbnb.android.airmapview.listeners.OnInfoWindowClickListener;
import com.airbnb.android.airmapview.listeners.OnLatLngScreenLocationCallback;
import com.airbnb.android.airmapview.listeners.OnMapBoundsCallback;
import com.airbnb.android.airmapview.listeners.OnMapClickListener;
import com.airbnb.android.airmapview.listeners.OnMapLoadedListener;
import com.airbnb.android.airmapview.listeners.OnMapMarkerClickListener;
import com.airbnb.android.airmapview.listeners.OnMapMarkerDragListener;
import com.airbnb.android.airmapview.listeners.OnSnapshotReadyListener;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class WebViewMapFragment extends Fragment implements AirMapInterface {
    private static final String TAG = WebViewMapFragment.class.getSimpleName();
    /* access modifiers changed from: private */
    public LatLng center;
    /* access modifiers changed from: private */
    public boolean ignoreNextMapMove;
    /* access modifiers changed from: private */
    public InfoWindowCreator infoWindowCreator;
    /* access modifiers changed from: private */
    public View infoWindowView;
    /* access modifiers changed from: private */
    public boolean loaded;
    /* access modifiers changed from: private */
    public ViewGroup mLayout;
    /* access modifiers changed from: private */
    public final Map<Long, AirMapMarker<?>> markers = new HashMap();
    /* access modifiers changed from: private */
    public OnCameraChangeListener onCameraChangeListener;
    /* access modifiers changed from: private */
    public OnInfoWindowClickListener onInfoWindowClickListener;
    /* access modifiers changed from: private */
    public OnLatLngScreenLocationCallback onLatLngScreenLocationCallback;
    /* access modifiers changed from: private */
    public OnMapBoundsCallback onMapBoundsCallback;
    /* access modifiers changed from: private */
    public OnMapClickListener onMapClickListener;
    /* access modifiers changed from: private */
    public OnMapLoadedListener onMapLoadedListener;
    /* access modifiers changed from: private */
    public OnMapMarkerClickListener onMapMarkerClickListener;
    /* access modifiers changed from: private */
    public OnMapMarkerDragListener onMapMarkerDragListener;
    private boolean trackUserLocation = false;
    protected WebView webView;
    /* access modifiers changed from: private */
    public int zoom;

    public class GeoWebChromeClient extends WebChromeClient {
        public GeoWebChromeClient() {
        }

        public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
            callback.invoke(origin, true, false);
        }
    }

    private class MapsJavaScriptInterface {
        private final Handler handler;

        private MapsJavaScriptInterface() {
            this.handler = new Handler(Looper.getMainLooper());
        }

        @JavascriptInterface
        public boolean isChinaMode() {
            return WebViewMapFragment.this.isChinaMode();
        }

        @JavascriptInterface
        public void onMapLoaded() {
            this.handler.post(new Runnable() {
                public void run() {
                    if (!WebViewMapFragment.this.loaded) {
                        WebViewMapFragment.this.loaded = true;
                        if (WebViewMapFragment.this.onMapLoadedListener != null) {
                            WebViewMapFragment.this.onMapLoadedListener.onMapLoaded();
                        }
                    }
                }
            });
        }

        @JavascriptInterface
        public void mapClick(double lat, double lng) {
            final double d = lat;
            final double d2 = lng;
            this.handler.post(new Runnable() {
                public void run() {
                    if (WebViewMapFragment.this.onMapClickListener != null) {
                        WebViewMapFragment.this.onMapClickListener.onMapClick(new LatLng(d, d2));
                    }
                    if (WebViewMapFragment.this.infoWindowView != null) {
                        WebViewMapFragment.this.mLayout.removeView(WebViewMapFragment.this.infoWindowView);
                    }
                }
            });
        }

        @JavascriptInterface
        public void getBoundsCallback(double neLat, double neLng, double swLat, double swLng) {
            final LatLngBounds bounds = new LatLngBounds(new LatLng(swLat, swLng), new LatLng(neLat, neLng));
            this.handler.post(new Runnable() {
                public void run() {
                    WebViewMapFragment.this.onMapBoundsCallback.onMapBoundsReady(bounds);
                }
            });
        }

        @JavascriptInterface
        public void getLatLngScreenLocationCallback(int x, int y) {
            final Point point = new Point(x, y);
            this.handler.post(new Runnable() {
                public void run() {
                    WebViewMapFragment.this.onLatLngScreenLocationCallback.onLatLngScreenLocationReady(point);
                }
            });
        }

        @JavascriptInterface
        public void mapMove(double lat, double lng, int zoom) {
            WebViewMapFragment.this.center = new LatLng(lat, lng);
            WebViewMapFragment.this.zoom = zoom;
            this.handler.post(new Runnable() {
                public void run() {
                    if (WebViewMapFragment.this.onCameraChangeListener != null) {
                        WebViewMapFragment.this.onCameraChangeListener.onCameraChanged(WebViewMapFragment.this.center, WebViewMapFragment.this.zoom);
                    }
                    if (WebViewMapFragment.this.ignoreNextMapMove) {
                        WebViewMapFragment.this.ignoreNextMapMove = false;
                    } else if (WebViewMapFragment.this.infoWindowView != null) {
                        WebViewMapFragment.this.mLayout.removeView(WebViewMapFragment.this.infoWindowView);
                    }
                }
            });
        }

        @JavascriptInterface
        public void markerClick(long markerId) {
            final AirMapMarker<?> airMapMarker = (AirMapMarker) WebViewMapFragment.this.markers.get(Long.valueOf(markerId));
            this.handler.post(new Runnable() {
                public void run() {
                    if (WebViewMapFragment.this.onMapMarkerClickListener != null) {
                        WebViewMapFragment.this.onMapMarkerClickListener.onMapMarkerClick(airMapMarker);
                    }
                    if (WebViewMapFragment.this.infoWindowView != null) {
                        WebViewMapFragment.this.mLayout.removeView(WebViewMapFragment.this.infoWindowView);
                    }
                    if (WebViewMapFragment.this.infoWindowCreator != null) {
                        WebViewMapFragment.this.infoWindowView = WebViewMapFragment.this.infoWindowCreator.createInfoWindow(airMapMarker);
                        if (WebViewMapFragment.this.infoWindowView != null) {
                            WebViewMapFragment.this.mLayout.addView(WebViewMapFragment.this.infoWindowView);
                            WebViewMapFragment.this.infoWindowView.setOnClickListener(new OnClickListener() {
                                public void onClick(View v) {
                                    if (WebViewMapFragment.this.onInfoWindowClickListener != null) {
                                        WebViewMapFragment.this.onInfoWindowClickListener.onInfoWindowClick(airMapMarker);
                                    }
                                }
                            });
                        }
                    } else {
                        WebViewMapFragment.this.webView.loadUrl(String.format(Locale.US, "javascript:showDefaultInfoWindow(%1$d);", new Object[]{Long.valueOf(airMapMarker.getId())}));
                    }
                    WebViewMapFragment.this.ignoreNextMapMove = true;
                }
            });
        }

        @JavascriptInterface
        public void markerDragStart(long markerId, double lat, double lng) {
            final long j = markerId;
            final double d = lat;
            final double d2 = lng;
            this.handler.post(new Runnable() {
                public void run() {
                    if (WebViewMapFragment.this.onMapMarkerDragListener != null) {
                        WebViewMapFragment.this.onMapMarkerDragListener.onMapMarkerDragStart(j, new LatLng(d, d2));
                    }
                }
            });
        }

        @JavascriptInterface
        public void markerDrag(long markerId, double lat, double lng) {
            final long j = markerId;
            final double d = lat;
            final double d2 = lng;
            this.handler.post(new Runnable() {
                public void run() {
                    if (WebViewMapFragment.this.onMapMarkerDragListener != null) {
                        WebViewMapFragment.this.onMapMarkerDragListener.onMapMarkerDrag(j, new LatLng(d, d2));
                    }
                }
            });
        }

        @JavascriptInterface
        public void markerDragEnd(long markerId, double lat, double lng) {
            final long j = markerId;
            final double d = lat;
            final double d2 = lng;
            this.handler.post(new Runnable() {
                public void run() {
                    if (WebViewMapFragment.this.onMapMarkerDragListener != null) {
                        WebViewMapFragment.this.onMapMarkerDragListener.onMapMarkerDragEnd(j, new LatLng(d, d2));
                    }
                }
            });
        }

        @JavascriptInterface
        public void defaultInfoWindowClick(long markerId) {
            final AirMapMarker<?> airMapMarker = (AirMapMarker) WebViewMapFragment.this.markers.get(Long.valueOf(markerId));
            this.handler.post(new Runnable() {
                public void run() {
                    if (WebViewMapFragment.this.onInfoWindowClickListener != null) {
                        WebViewMapFragment.this.onInfoWindowClickListener.onInfoWindowClick(airMapMarker);
                    }
                }
            });
        }
    }

    public WebViewMapFragment setArguments(AirMapType mapType) {
        setArguments(mapType.toBundle());
        return this;
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1666R.layout.fragment_webview, container, false);
        this.webView = (WebView) view.findViewById(C1666R.C1668id.webview);
        this.mLayout = (ViewGroup) view;
        WebSettings webViewSettings = this.webView.getSettings();
        webViewSettings.setSupportZoom(true);
        webViewSettings.setBuiltInZoomControls(false);
        webViewSettings.setJavaScriptEnabled(true);
        webViewSettings.setGeolocationEnabled(true);
        webViewSettings.setAllowFileAccess(false);
        webViewSettings.setAllowContentAccess(false);
        this.webView.setWebChromeClient(new GeoWebChromeClient());
        AirMapType mapType = AirMapType.fromBundle(getArguments());
        this.webView.loadDataWithBaseURL(mapType.getDomain(), mapType.getMapData(getResources()), "text/html", "base64", null);
        this.webView.addJavascriptInterface(new MapsJavaScriptInterface(), "AirMapView");
        return view;
    }

    public int getZoom() {
        return this.zoom;
    }

    public LatLng getCenter() {
        return this.center;
    }

    public void setCenter(LatLng latLng) {
        this.webView.loadUrl(String.format(Locale.US, "javascript:centerMap(%1$f, %2$f);", new Object[]{Double.valueOf(latLng.latitude), Double.valueOf(latLng.longitude)}));
    }

    public void animateCenter(LatLng latLng) {
        setCenter(latLng);
    }

    public void setZoom(int zoom2) {
        this.webView.loadUrl(String.format(Locale.US, "javascript:setZoom(%1$d);", new Object[]{Integer.valueOf(zoom2)}));
    }

    public void drawCircle(LatLng latLng, int radius) {
        drawCircle(latLng, radius, AirMapInterface.CIRCLE_BORDER_COLOR);
    }

    public void drawCircle(LatLng latLng, int radius, int borderColor) {
        drawCircle(latLng, radius, borderColor, 0);
    }

    public void drawCircle(LatLng latLng, int radius, int borderColor, int borderWidth) {
        drawCircle(latLng, radius, borderColor, borderWidth, AirMapInterface.CIRCLE_FILL_COLOR);
    }

    public void drawCircle(LatLng latLng, int radius, int borderColor, int borderWidth, int fillColor) {
        this.webView.loadUrl(String.format(Locale.US, "javascript:addCircle(%1$f, %2$f, %3$d, %4$d, %5$d, %6$d);", new Object[]{Double.valueOf(latLng.latitude), Double.valueOf(latLng.longitude), Integer.valueOf(radius), Integer.valueOf(borderColor), Integer.valueOf(borderWidth), Integer.valueOf(fillColor)}));
    }

    public void highlightMarker(long markerId) {
        if (markerId != -1) {
            this.webView.loadUrl(String.format(Locale.US, "javascript:highlightMarker(%1$d);", new Object[]{Long.valueOf(markerId)}));
        }
    }

    public void unhighlightMarker(long markerId) {
        if (markerId != -1) {
            this.webView.loadUrl(String.format(Locale.US, "javascript:unhighlightMarker(%1$d);", new Object[]{Long.valueOf(markerId)}));
        }
    }

    public boolean isInitialized() {
        return this.webView != null && this.loaded;
    }

    public void addMarker(AirMapMarker<?> marker) {
        LatLng latLng = marker.getLatLng();
        this.markers.put(Long.valueOf(marker.getId()), marker);
        this.webView.loadUrl(String.format(Locale.US, "javascript:addMarkerWithId(%1$f, %2$f, %3$d, '%4$s', '%5$s', %6$b);", new Object[]{Double.valueOf(latLng.latitude), Double.valueOf(latLng.longitude), Long.valueOf(marker.getId()), marker.getTitle(), marker.getSnippet(), Boolean.valueOf(marker.getMarkerOptions().isDraggable())}));
    }

    public void moveMarker(AirMapMarker<?> marker, LatLng to) {
        marker.setLatLng(to);
        this.webView.loadUrl(String.format(Locale.US, "javascript:moveMarker(%1$f, %2$f, '%3$s');", new Object[]{Double.valueOf(to.latitude), Double.valueOf(to.longitude), Long.valueOf(marker.getId())}));
    }

    public void removeMarker(AirMapMarker<?> marker) {
        this.markers.remove(Long.valueOf(marker.getId()));
        this.webView.loadUrl(String.format(Locale.US, "javascript:removeMarker(%1$d);", new Object[]{Long.valueOf(marker.getId())}));
    }

    public void clearMarkers() {
        this.markers.clear();
        this.webView.loadUrl("javascript:clearMarkers();");
    }

    public void setOnCameraChangeListener(OnCameraChangeListener listener) {
        this.onCameraChangeListener = listener;
    }

    public void setOnMapLoadedListener(OnMapLoadedListener listener) {
        this.onMapLoadedListener = listener;
        if (this.loaded) {
            this.onMapLoadedListener.onMapLoaded();
        }
    }

    public void setCenterZoom(LatLng latLng, int zoom2) {
        setCenter(latLng);
        setZoom(zoom2);
    }

    public void animateCenterZoom(LatLng latLng, int zoom2) {
        setCenterZoom(latLng, zoom2);
    }

    public void setOnMarkerClickListener(OnMapMarkerClickListener listener) {
        this.onMapMarkerClickListener = listener;
    }

    public void setOnMarkerDragListener(OnMapMarkerDragListener listener) {
        this.onMapMarkerDragListener = listener;
    }

    public void setPadding(int left, int top, int right, int bottom) {
    }

    public void setMyLocationEnabled(boolean trackUserLocationEnabled) {
        this.trackUserLocation = trackUserLocationEnabled;
        if (trackUserLocationEnabled) {
            RuntimePermissionUtils.checkLocationPermissions(getActivity(), this);
        } else {
            this.webView.loadUrl("javascript:stopTrackingUserLocation();");
        }
    }

    public void onLocationPermissionsGranted() {
        this.webView.loadUrl("javascript:startTrackingUserLocation();");
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        RuntimePermissionUtils.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    public boolean isMyLocationEnabled() {
        return this.trackUserLocation;
    }

    public void setMapToolbarEnabled(boolean enabled) {
    }

    public <T> void addPolyline(AirMapPolyline<T> polyline) {
        try {
            JSONArray array = new JSONArray();
            for (LatLng point : polyline.getPoints()) {
                JSONObject json = new JSONObject();
                json.put("lat", point.latitude);
                json.put("lng", point.longitude);
                array.put(json);
            }
            this.webView.loadUrl(String.format("javascript:addPolyline(" + array.toString() + ", %1$d, %2$d, %3$d);", new Object[]{Long.valueOf(polyline.getId()), Integer.valueOf(polyline.getStrokeWidth()), Integer.valueOf(polyline.getStrokeColor())}));
        } catch (JSONException e) {
            Log.e(TAG, "error constructing polyline JSON", e);
        }
    }

    public <T> void removePolyline(AirMapPolyline<T> polyline) {
        this.webView.loadUrl(String.format(Locale.US, "javascript:removePolyline(%1$d);", new Object[]{Long.valueOf(polyline.getId())}));
    }

    public <T> void addPolygon(AirMapPolygon<T> polygon) {
        try {
            JSONArray array = new JSONArray();
            for (LatLng point : polygon.getPolygonOptions().getPoints()) {
                JSONObject json = new JSONObject();
                json.put("lat", point.latitude);
                json.put("lng", point.longitude);
                array.put(json);
            }
            this.webView.loadUrl(String.format(Locale.US, "javascript:addPolygon(" + array.toString() + ", %1$d, %2$d, %3$d, %4$d);", new Object[]{Long.valueOf(polygon.getId()), Integer.valueOf((int) polygon.getPolygonOptions().getStrokeWidth()), Integer.valueOf(polygon.getPolygonOptions().getStrokeColor()), Integer.valueOf(polygon.getPolygonOptions().getFillColor())}));
        } catch (JSONException e) {
            Log.e(TAG, "error constructing polyline JSON", e);
        }
    }

    public <T> void removePolygon(AirMapPolygon<T> polygon) {
        this.webView.loadUrl(String.format(Locale.US, "javascript:removePolygon(%1$d);", new Object[]{Long.valueOf(polygon.getId())}));
    }

    public void setOnMapClickListener(OnMapClickListener listener) {
        this.onMapClickListener = listener;
    }

    public void setOnInfoWindowClickListener(OnInfoWindowClickListener listener) {
        this.onInfoWindowClickListener = listener;
    }

    public void setInfoWindowCreator(InfoWindowAdapter adapter, InfoWindowCreator creator) {
        this.infoWindowCreator = creator;
    }

    public void getMapScreenBounds(OnMapBoundsCallback callback) {
        this.onMapBoundsCallback = callback;
        this.webView.loadUrl("javascript:getBounds();");
    }

    public void getScreenLocation(LatLng latLng, OnLatLngScreenLocationCallback callback) {
        this.onLatLngScreenLocationCallback = callback;
        this.webView.loadUrl(String.format(Locale.US, "javascript:getScreenLocation(%1$f, %2$f);", new Object[]{Double.valueOf(latLng.latitude), Double.valueOf(latLng.longitude)}));
    }

    public void setCenter(LatLngBounds bounds, int boundsPadding) {
        this.webView.loadUrl(String.format(Locale.US, "javascript:setBounds(%1$f, %2$f, %3$f, %4$f);", new Object[]{Double.valueOf(bounds.northeast.latitude), Double.valueOf(bounds.northeast.longitude), Double.valueOf(bounds.southwest.latitude), Double.valueOf(bounds.southwest.longitude)}));
    }

    /* access modifiers changed from: protected */
    public boolean isChinaMode() {
        return false;
    }

    public void setGeoJsonLayer(AirMapGeoJsonLayer layer) {
        clearGeoJsonLayer();
        this.webView.loadUrl(String.format(Locale.US, "javascript:addGeoJsonLayer(%1$s, %2$f, %3$d, %4$d);", new Object[]{layer.geoJson, Float.valueOf(layer.strokeWidth), Integer.valueOf(layer.strokeColor), Integer.valueOf(layer.fillColor)}));
    }

    public void clearGeoJsonLayer() {
        this.webView.loadUrl("javascript:removeGeoJsonLayer();");
    }

    public void getSnapshot(OnSnapshotReadyListener listener) {
        boolean isDrawingCacheEnabled = this.webView.isDrawingCacheEnabled();
        this.webView.setDrawingCacheEnabled(true);
        Bitmap bitmap = this.webView.getDrawingCache();
        Bitmap newBitmap = null;
        if (bitmap != null) {
            newBitmap = bitmap.copy(Config.RGB_565, false);
        }
        this.webView.destroyDrawingCache();
        this.webView.setDrawingCacheEnabled(isDrawingCacheEnabled);
        listener.onSnapshotReady(newBitmap);
    }
}
