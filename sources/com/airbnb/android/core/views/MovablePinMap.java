package com.airbnb.android.core.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Parcelable;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.IcepickWrapper;
import com.google.android.gms.maps.model.LatLng;
import icepick.Icepick;
import icepick.State;

public class MovablePinMap extends RelativeLayout {
    private static final double LOCATION_COORDINATE_OFFSET = 5.0E-4d;
    private static final int ZOOM_LEVEL = 17;
    @BindView
    AirbnbMapView airbnbMapView;
    private AnimationManager animationManager;
    @State
    LatLng currentLocation;
    @State
    int currentZoom;
    @State
    boolean hasUserTriggeredCameraMove = false;
    @State
    LatLng initialLocation;
    @BindView
    ImageView locationPin;
    @BindView
    ImageView locationPinCircle;
    @BindView
    ImageView locationPinShadow;
    private Integer maxZoom;
    private Integer minZoom;
    private Double scrollLimit;

    private class AnimationManager {
        private final int LOCATION_PIN_ANIMATION_DURATION_MS = 50;
        private final int LOCATION_PIN_DIRECTION_UP = -1;
        Interpolator interpolator = new FastOutSlowInInterpolator();
        private final ObjectAnimator locationPinAnimator;

        AnimationManager() {
            int locationPinAnimationHeight = MovablePinMap.this.getResources().getDimensionPixelSize(C0716R.dimen.location_pin_animation_height) * -1;
            this.locationPinAnimator = ObjectAnimator.ofFloat(MovablePinMap.this.locationPin, View.TRANSLATION_Y, new float[]{(float) locationPinAnimationHeight});
        }

        /* access modifiers changed from: 0000 */
        public void animateLocationPinUp() {
            this.locationPinAnimator.cancel();
            this.locationPinAnimator.setDuration(50);
            this.locationPinAnimator.setInterpolator(this.interpolator);
            this.locationPinAnimator.start();
        }

        /* access modifiers changed from: 0000 */
        public void animateLocationPinDown() {
            this.locationPinAnimator.cancel();
            this.locationPinAnimator.setDuration(50);
            this.locationPinAnimator.setInterpolator(this.interpolator);
            this.locationPinAnimator.reverse();
        }
    }

    public void onMapInitialized() {
        this.airbnbMapView.setCenterZoom(this.currentLocation, this.currentZoom);
    }

    public void onCameraMove() {
        if (!this.hasUserTriggeredCameraMove) {
            this.hasUserTriggeredCameraMove = true;
            this.animationManager.animateLocationPinUp();
            this.locationPinCircle.setVisibility(4);
        }
    }

    public void onCameraChanged(LatLng latLng, int zoom) {
        if (this.hasUserTriggeredCameraMove) {
            this.hasUserTriggeredCameraMove = false;
            if (this.scrollLimit == null || !pinHasBeenMoved(latLng, this.scrollLimit.doubleValue())) {
                this.currentLocation = latLng;
            } else {
                this.airbnbMapView.animateCenterZoom(this.currentLocation, this.currentZoom);
            }
            this.animationManager.animateLocationPinDown();
            this.locationPinCircle.setVisibility(0);
            if ((this.minZoom == null || zoom >= this.minZoom.intValue()) && (this.maxZoom == null || zoom <= this.maxZoom.intValue())) {
                this.currentZoom = zoom;
            } else {
                this.airbnbMapView.animateCenterZoom(this.currentLocation, this.currentZoom);
            }
        }
    }

    public MovablePinMap(Context context) {
        super(context);
        initView();
    }

    public MovablePinMap(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MovablePinMap(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), C0716R.layout.movable_pin_map, this);
        ButterKnife.bind((View) this);
    }

    public Parcelable onSaveInstanceState() {
        return IcepickWrapper.saveInstanceState(this, super.onSaveInstanceState());
    }

    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(Icepick.restoreInstanceState(this, state));
    }

    public void initialize(FragmentManager fragmentManager, LatLng listingLocation, int minZoom2, int maxZoom2, double scrollLimit2) {
        this.minZoom = Integer.valueOf(minZoom2);
        this.maxZoom = Integer.valueOf(maxZoom2);
        this.scrollLimit = Double.valueOf(scrollLimit2);
        initialize(fragmentManager, listingLocation);
    }

    public void initialize(FragmentManager fragmentManager, LatLng listingLocation) {
        this.airbnbMapView.initialize(fragmentManager);
        this.initialLocation = listingLocation;
        this.currentLocation = listingLocation;
        this.currentZoom = 17;
        this.animationManager = new AnimationManager();
        this.airbnbMapView.setOnMapInitializedListener(MovablePinMap$$Lambda$1.lambdaFactory$(this));
        this.airbnbMapView.setOnCameraMoveListener(MovablePinMap$$Lambda$2.lambdaFactory$(this));
        this.airbnbMapView.setOnCameraChangeListener(MovablePinMap$$Lambda$3.lambdaFactory$(this));
        this.airbnbMapView.post(MovablePinMap$$Lambda$4.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$initialize$0(MovablePinMap movablePinMap) {
        movablePinMap.locationPin.setVisibility(0);
        movablePinMap.locationPinShadow.setVisibility(0);
        movablePinMap.locationPinCircle.setVisibility(0);
    }

    public LatLng getCurrentLocation() {
        return this.currentLocation;
    }

    public boolean pinHasBeenMoved(LatLng listingLocation) {
        return pinHasBeenMoved(listingLocation, this.currentLocation, LOCATION_COORDINATE_OFFSET);
    }

    private boolean pinHasBeenMoved(LatLng latLng, double limit) {
        return pinHasBeenMoved(this.initialLocation, latLng, limit);
    }

    private boolean pinHasBeenMoved(LatLng initialLatLng, LatLng updatedLatLng, double limit) {
        return Math.abs(initialLatLng.latitude - updatedLatLng.latitude) > limit || Math.abs(initialLatLng.longitude - updatedLatLng.longitude) > limit;
    }
}
