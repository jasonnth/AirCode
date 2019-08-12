package com.airbnb.android.lib.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.airmapview.AirMapView;
import com.airbnb.android.core.utils.LocationUtil;
import com.airbnb.android.lib.C0880R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

public class MapScale extends FrameLayout {
    private static final double EARTH_RADIUS_KM = 6372.8d;
    private static final int FADE_IN_OUT_MS = 600;
    private static final int MAX_SUPPORTED_ZOOM = 15;
    private static final float MILE_PER_KM = 0.621371f;
    private static final int MIN_SUPPORTED_ZOOM = 6;
    private static final float SCALE_RATIO = 0.35f;
    private static final int SCALE_VISIBLE_MS = 3000;
    private final Handler mHandler;
    private final Runnable mHideScaleRunnable;
    @BindView
    View mKmBar;
    @BindView
    TextView mKmText;
    @BindView
    View mMilesBar;
    @BindView
    TextView mMilesText;

    public MapScale(Context context) {
        super(context);
        this.mHandler = new Handler();
        this.mHideScaleRunnable = MapScale$$Lambda$1.lambdaFactory$(this);
        init();
    }

    public MapScale(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mHandler = new Handler();
        this.mHideScaleRunnable = MapScale$$Lambda$2.lambdaFactory$(this);
        init();
    }

    public MapScale(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mHandler = new Handler();
        this.mHideScaleRunnable = MapScale$$Lambda$3.lambdaFactory$(this);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(C0880R.layout.map_scale, this, true);
        ButterKnife.bind((View) this);
        setMinimumHeight(getContext().getResources().getDimensionPixelSize(C0880R.dimen.map_scale_min_height));
        setAlpha(0.0f);
        setVisibility(0);
    }

    public void updateScale(AirMapView airMapView, int zoomLevel) {
        if (zoomLevel < 6 || zoomLevel > 15) {
            this.mHideScaleRunnable.run();
            this.mHandler.removeCallbacks(this.mHideScaleRunnable);
            return;
        }
        airMapView.getScreenBounds(MapScale$$Lambda$4.lambdaFactory$(this, airMapView));
    }

    static /* synthetic */ void lambda$updateScale$1(MapScale mapScale, AirMapView airMapView, LatLngBounds bounds) {
        LatLng ne = bounds.northeast;
        LatLng sw = bounds.southwest;
        LatLng latLng = new LatLng(sw.latitude, ne.longitude);
        float mapWidthKm = (float) ((LocationUtil.getMetersBetweenPoints(sw.latitude, sw.longitude, latLng.latitude, latLng.longitude) * 0.3499999940395355d) / 1000.0d);
        int mapWidthPx = (int) (SCALE_RATIO * ((float) airMapView.getWidth()));
        float flooredWidthKm = floorTo125((double) mapWidthKm);
        mapScale.mKmText.setText(mapScale.getContext().getString(C0880R.string.map_scale_kilometer_abbreviated, new Object[]{Float.valueOf(flooredWidthKm)}));
        mapScale.mKmBar.getLayoutParams().width = (int) (((float) mapWidthPx) * (flooredWidthKm / mapWidthKm));
        float mapWidthMiles = mapWidthKm * MILE_PER_KM;
        float flooredWidthMiles = floorTo125((double) mapWidthMiles);
        mapScale.mMilesText.setText(mapScale.getContext().getString(C0880R.string.map_scale_mile_abbreviated, new Object[]{Float.valueOf(flooredWidthMiles)}));
        mapScale.mMilesBar.getLayoutParams().width = (int) (((float) mapWidthPx) * (flooredWidthMiles / mapWidthMiles));
        MapScale mapScale2 = mapScale;
        ObjectAnimator.ofFloat(mapScale2, "alpha", new float[]{1.0f}).setDuration(600).start();
        mapScale.requestLayout();
        mapScale.mHandler.removeCallbacks(mapScale.mHideScaleRunnable);
        mapScale.mHandler.postDelayed(mapScale.mHideScaleRunnable, 3000);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mHandler.removeCallbacks(this.mHideScaleRunnable);
    }

    private static float floorTo125(double i) {
        double magnitude = Math.pow(10.0d, Math.floor(Math.log10(i)));
        double i2 = i / magnitude;
        if (i2 >= 5.0d) {
            i2 = 5.0d;
        } else if (i2 >= 2.0d) {
            i2 = 2.0d;
        } else if (i2 >= 1.0d) {
            i2 = 1.0d;
        }
        return (float) (i2 * magnitude);
    }
}
