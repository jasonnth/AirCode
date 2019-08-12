package com.airbnb.android.core.views;

import android.content.Context;
import android.support.p000v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import com.airbnb.android.airmapview.AirMapInterface;
import com.airbnb.android.airmapview.AirMapType;
import com.airbnb.android.airmapview.AirMapView;
import com.airbnb.android.airmapview.AirMapViewTypes;
import com.airbnb.android.airmapview.DefaultAirMapViewBuilder;
import com.airbnb.android.airmapview.GoogleChinaMapType;
import com.airbnb.android.airmapview.GoogleWebMapType;
import com.airbnb.android.airmapview.NativeGoogleMapFragment;
import com.airbnb.android.airmapview.WebAirMapViewBuilder;
import com.airbnb.android.airmapview.WebViewMapFragment;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.utils.Strap;

public class AirbnbMapView extends AirMapView {
    private OnTouchListener interceptTouchEventListener;

    public AirbnbMapView(Context context) {
        this(context, null);
    }

    public AirbnbMapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AirbnbMapView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public final void initialize(FragmentManager fragmentManager) {
        super.initialize(fragmentManager, createMapInterface());
    }

    private AirMapInterface createMapInterface() {
        AirMapInterface ret;
        DefaultAirMapViewBuilder mapViewBuilder = new DefaultAirMapViewBuilder(getContext());
        WebAirMapViewBuilder webBuilder = (WebAirMapViewBuilder) mapViewBuilder.builder(AirMapViewTypes.WEB);
        DebugSettings debugSettings = CoreApplication.instance(getContext()).component().debugSettings();
        if (AppLaunchUtils.isUserInChina()) {
            ret = webBuilder.withOptions((AirMapType) new GoogleChinaMapType()).build();
        } else if (!BuildHelper.isDebugFeaturesEnabled() || !DebugSettings.SIMULATE_NO_GOOGLE_PLAY_SERVICES.isEnabled()) {
            ret = mapViewBuilder.builder().build();
        } else {
            ret = webBuilder.withOptions((AirMapType) new GoogleWebMapType()).build();
        }
        AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv("type", "map_type").mo11639kv("map_type", ret.getClass().getSimpleName()));
        return ret;
    }

    public void onMapLoaded() {
        super.onMapLoaded();
        if (isActivated()) {
            getMapInterface().setMyLocationEnabled(!AppLaunchUtils.isUserInKorea());
        }
        if (getMapInterface() instanceof NativeGoogleMapFragment) {
            ((NativeGoogleMapFragment) getMapInterface()).getGoogleMap().getUiSettings().setMapToolbarEnabled(false);
        }
    }

    public void highlightMarker(long prevId, long newId) {
        if (isInitialized() && (getMapInterface() instanceof WebViewMapFragment)) {
            WebViewMapFragment fragment = (WebViewMapFragment) this.mapInterface;
            fragment.unhighlightMarker(prevId);
            fragment.highlightMarker(newId);
        }
    }

    public void setInterceptTouchListener(OnTouchListener onTouchListener) {
        this.interceptTouchEventListener = onTouchListener;
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.interceptTouchEventListener != null) {
            this.interceptTouchEventListener.onTouch(this, ev);
        }
        return super.onInterceptTouchEvent(ev);
    }
}
