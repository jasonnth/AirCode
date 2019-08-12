package com.airbnb.android.core.map;

import android.animation.Animator;
import android.content.Context;
import android.os.Handler;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.p000v4.app.FragmentManager;
import android.support.p002v7.widget.RecyclerView.RecycledViewPool;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.airmapview.AirMapMarker;
import com.airbnb.android.airmapview.NativeGoogleMapFragment;
import com.airbnb.android.airmapview.listeners.OnCameraChangeListener;
import com.airbnb.android.airmapview.listeners.OnCameraMoveListener;
import com.airbnb.android.airmapview.listeners.OnMapClickListener;
import com.airbnb.android.airmapview.listeners.OnMapInitializedListener;
import com.airbnb.android.airmapview.listeners.OnMapMarkerClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.Mappable;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.views.AirbnbMapView;
import com.airbnb.android.core.wishlists.WishListChangeInfo;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.core.wishlists.WishListsChangedListener;
import com.airbnb.android.utils.MapState;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.android.utils.animation.SimpleAnimatorListener;
import com.airbnb.epoxy.EpoxyAdapter;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.collections.Carousel.OnSnapToPositionListener;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.MapSearchButton;
import com.airbnb.p027n2.components.NavigationPill;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.LatLngBounds.Builder;
import icepick.Icepick;
import icepick.State;
import java.util.List;

public class ExploreMapView extends FrameLayout implements OnCameraChangeListener, OnCameraMoveListener, OnMapClickListener, OnMapInitializedListener, OnMapMarkerClickListener, OnSnapToPositionListener {
    @BindView
    AirbnbMapView airMapView;
    @BindView
    Carousel carousel;
    @BindView
    ViewGroup carouselAndCoordinatorContainer;
    @BindView
    CoordinatorLayout coordinatorLayout;
    @State
    MapState currentMapState;
    @State
    Mappable currentlyHighlightedItem;
    private ExploreMapViewDataProvider dataProvider;
    private ExploreMapViewEventCallbacks eventCallbacks;
    @State
    boolean firstMapLoad;
    private final Handler handler;
    @State
    boolean isInQuietMode;
    private MapWindowAdapter mapInfoWindowAdapter;
    private ExploreMapMarkerManager mapMarkerManager;
    @BindDimen
    int mapPaddingPx;
    @BindView
    NavigationPill pillButton;
    @BindView
    MapSearchButton redoSearchButton;
    private boolean redoSearchEnabled;
    @BindView
    AirToolbar toolbar;
    private WishListManager wishListManager;
    private final WishListsChangedListener wishListsChangedListener;

    public interface ExploreMapViewDataProvider {
        ExploreMapMarkerable createMarkerable(Mappable mappable);

        EpoxyAdapter getAdapter();

        List<Mappable> getMappables();

        boolean isLoadingData();

        void setSelectedCarouselPosition(int i);
    }

    public interface ExploreMapViewEventCallbacks {
        void onCarouselScrolled(boolean z, int i, Mappable mappable);

        void onFiltersButtonClicked();

        void onListButtonClicked();

        void onMapMarkerClicked(Mappable mappable);

        void onRedoSearchClicked(LatLngBounds latLngBounds);
    }

    public ExploreMapView(Context context) {
        super(context);
        this.firstMapLoad = true;
        this.redoSearchEnabled = true;
        this.handler = new Handler();
        this.wishListsChangedListener = ExploreMapView$$Lambda$1.lambdaFactory$(this);
        init();
    }

    public ExploreMapView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.firstMapLoad = true;
        this.redoSearchEnabled = true;
        this.handler = new Handler();
        this.wishListsChangedListener = ExploreMapView$$Lambda$2.lambdaFactory$(this);
        init();
    }

    public ExploreMapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.firstMapLoad = true;
        this.redoSearchEnabled = true;
        this.handler = new Handler();
        this.wishListsChangedListener = ExploreMapView$$Lambda$3.lambdaFactory$(this);
        init();
    }

    private void init() {
        inflate(getContext(), C0716R.layout.view_explore_map, this);
        ButterKnife.bind((View) this);
        this.airMapView.setOnMapInitializedListener(this);
        this.airMapView.setOnMarkerClickListener(this);
        this.airMapView.setOnCameraChangeListener(this);
        this.airMapView.setOnCameraMoveListener(this);
        this.airMapView.setOnMapClickListener(this);
        this.pillButton.setMode(1);
        this.pillButton.setLeftButtonClickListener(ExploreMapView$$Lambda$4.lambdaFactory$(this));
        this.pillButton.setRightButtonClickListener(ExploreMapView$$Lambda$5.lambdaFactory$(this));
        initCarousel();
    }

    static /* synthetic */ void lambda$init$1(ExploreMapView exploreMapView, View v) {
        exploreMapView.isInQuietMode = false;
        exploreMapView.eventCallbacks.onFiltersButtonClicked();
    }

    public void setupNavigationPill() {
        if (Experiments.pillShowThreeButtons()) {
            this.pillButton.setMode(3);
            this.pillButton.setLeftButtonText(getContext().getString(C0716R.string.list_pill_caps));
            this.pillButton.setLeftButtonIcon(C0716R.C0717drawable.n2_navigation_pill_list_icon);
            this.pillButton.setLeftButtonClickListener(ExploreMapView$$Lambda$6.lambdaFactory$(this));
            this.pillButton.setMiddleButtonText(getContext().getString(C0716R.string.map_pill_caps));
            this.pillButton.setMiddleButtonIcon(C0716R.C0717drawable.n2_navigation_pill_map_icon_babu);
            this.pillButton.setMiddleButtonTextColor(C0716R.color.n2_jellyfish_babu_bg);
            this.pillButton.setMiddleButtonClickListener(ExploreMapView$$Lambda$7.lambdaFactory$());
        }
    }

    static /* synthetic */ void lambda$setupNavigationPill$3(View v) {
    }

    private void initCarousel() {
        this.carousel.setSnapToPositionListener(this);
    }

    public void initialize(ExploreMapViewEventCallbacks eventCallbacks2, ExploreMapViewDataProvider dataProvider2, FragmentManager fragmentManager, WishListManager wishListManager2, RecycledViewPool recycledViewPool) {
        this.eventCallbacks = eventCallbacks2;
        this.dataProvider = dataProvider2;
        this.wishListManager = wishListManager2;
        this.airMapView.initialize(fragmentManager);
        this.carousel.setRecycledViewPool(recycledViewPool);
        wishListManager2.addWishListsChangedListener(this.wishListsChangedListener);
        initMarkerManager();
    }

    public AirToolbar getToolbar() {
        return this.toolbar;
    }

    public CoordinatorLayout getSnackbarCoordinator() {
        return this.coordinatorLayout;
    }

    private void initMarkerManager() {
        Check.state(this.mapMarkerManager == null, "mapMarkerManager already initialized");
        Check.notNull(this.airMapView.getMapInterface(), "You must initialize the map view before calling this.");
        if (this.airMapView.getMapInterface() instanceof NativeGoogleMapFragment) {
            this.mapMarkerManager = new ExploreGoogleMapMarkerManager();
        } else {
            this.mapMarkerManager = new ExploreWebMapMarkerManager();
        }
        this.mapMarkerManager.setup(this.airMapView);
        this.mapInfoWindowAdapter = new MapWindowAdapter(getContext());
    }

    public void setFiltersCount(int filterCount) {
        this.pillButton.setRightButtonBadgeCount(filterCount);
    }

    public void setFiltersEnabled(boolean filtersEnabled) {
        ViewUtils.setVisibleIf((View) this.pillButton, filtersEnabled);
    }

    public void setRedoSearchEnabled(boolean redoSearchEnabled2) {
        this.redoSearchEnabled = redoSearchEnabled2;
        if (!redoSearchEnabled2) {
            this.redoSearchButton.hide();
        }
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void onRedoSearchClicked() {
        this.redoSearchButton.show(true);
        this.airMapView.getScreenBounds(ExploreMapView$$Lambda$8.lambdaFactory$(this));
    }

    public void onSnappedToPosition(int position, boolean userSwipedLeft, boolean autoScroll) {
        List<Mappable> mappables = this.dataProvider.getMappables();
        if (!FeatureToggles.addBlankItemsToCarouselToEnableFullScroll() || position < mappables.size()) {
            Mappable mappable = (Mappable) mappables.get(position);
            selectMarkerAndCarousel(mappable, true);
            this.eventCallbacks.onCarouselScrolled(userSwipedLeft, position, mappable);
        }
    }

    public void onMapInitialized() {
        if (this.currentMapState != null) {
            this.airMapView.setCenter(this.currentMapState.latLng);
            this.airMapView.setZoom(this.currentMapState.zoom);
        }
        redrawMapData();
    }

    public void onDataChanged() {
        if (!this.redoSearchEnabled || !this.dataProvider.isLoadingData()) {
            this.redoSearchButton.hide();
        } else {
            this.redoSearchButton.show(true);
        }
        redrawMapData();
        setQuietMode(false);
    }

    private void redrawMapData() {
        boolean isSelected;
        if (this.airMapView.isInitialized()) {
            this.mapMarkerManager.clearMarkers();
            EpoxyAdapter adapter = this.dataProvider.getAdapter();
            if (adapter != this.carousel.getAdapter()) {
                this.carousel.swapAdapter(adapter, false);
            }
            List<Mappable> mappables = this.dataProvider.getMappables();
            if (!mappables.contains(this.currentlyHighlightedItem)) {
                this.currentlyHighlightedItem = null;
            }
            if (!mappables.isEmpty()) {
                Builder builder = LatLngBounds.builder();
                for (Mappable mappable : mappables) {
                    builder.include(new LatLng(mappable.getLatitude(), mappable.getLongitude()));
                    if (this.currentlyHighlightedItem == mappable) {
                        isSelected = true;
                    } else {
                        isSelected = false;
                    }
                    this.mapMarkerManager.addMarker(this.dataProvider.createMarkerable(mappable), isSelected);
                }
                if (this.firstMapLoad) {
                    this.airMapView.setBounds(builder.build(), this.mapPaddingPx);
                }
                selectMarkerAndCarousel(this.currentlyHighlightedItem == null ? (Mappable) mappables.get(0) : this.currentlyHighlightedItem, this.firstMapLoad);
                this.firstMapLoad = false;
                this.airMapView.setInfoWindowAdapter(this.mapInfoWindowAdapter, this.mapInfoWindowAdapter);
            }
            invalidateCarouselVisibility();
            setQuietMode(this.isInQuietMode);
        }
    }

    private void invalidateCarouselVisibility() {
        this.handler.post(ExploreMapView$$Lambda$9.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$invalidateCarouselVisibility$5(ExploreMapView exploreMapView) {
        final boolean visible;
        int translationAmount = 0;
        if (!exploreMapView.isInQuietMode) {
            visible = true;
        } else {
            visible = false;
        }
        if (!visible) {
            translationAmount = exploreMapView.carousel.getHeight();
        }
        if (exploreMapView.carouselAndCoordinatorContainer.getTranslationY() != ((float) translationAmount)) {
            exploreMapView.carouselAndCoordinatorContainer.animate().translationY((float) translationAmount).withLayer().setListener(new SimpleAnimatorListener() {
                public void onAnimationStart(Animator animation) {
                    ExploreMapView.this.invalidateMapPadding(false);
                }

                public void onAnimationEnd(Animator animation) {
                    ExploreMapView.this.invalidateMapPadding(visible);
                }
            });
        } else {
            exploreMapView.invalidateMapPadding(visible);
        }
    }

    /* access modifiers changed from: private */
    public void invalidateMapPadding(boolean useBottomPadding) {
        this.handler.post(ExploreMapView$$Lambda$10.lambdaFactory$(this, useBottomPadding));
    }

    static /* synthetic */ void lambda$invalidateMapPadding$6(ExploreMapView exploreMapView, boolean useBottomPadding) {
        int paddingBottom;
        int paddingTop = exploreMapView.isInQuietMode ? 0 : exploreMapView.toolbar.getHeight();
        if (useBottomPadding) {
            paddingBottom = exploreMapView.carousel.getHeight();
        } else {
            paddingBottom = 0;
        }
        exploreMapView.airMapView.setPadding(0, paddingTop, 0, paddingBottom);
    }

    public void onMapMarkerClick(AirMapMarker<?> airMarker) {
        if (airMarker.object() != null) {
            Mappable selectedItem = (Mappable) airMarker.object();
            selectMarkerAndCarousel(selectedItem, true);
            this.redoSearchButton.hide();
            setQuietMode(false);
            this.eventCallbacks.onMapMarkerClicked(selectedItem);
        }
    }

    private void selectMarkerAndCarousel(Mappable toSelect, boolean animateToCenter) {
        this.mapMarkerManager.selectMarker(toSelect.getMappableId());
        if (animateToCenter) {
            this.airMapView.animateCenter(new LatLng(toSelect.getLatitude(), toSelect.getLongitude()));
        }
        int selectedPosition = this.dataProvider.getMappables().indexOf(toSelect);
        if (selectedPosition == -1) {
            selectedPosition = 0;
        }
        this.dataProvider.setSelectedCarouselPosition(selectedPosition);
        this.carousel.scrollToPosition(selectedPosition);
        this.currentlyHighlightedItem = toSelect;
    }

    private void toggleQuietMode() {
        setQuietMode(!this.isInQuietMode);
    }

    private void setQuietMode(boolean isInQuietMode2) {
        float f;
        float f2 = 0.0f;
        this.isInQuietMode = isInQuietMode2;
        invalidateCarouselVisibility();
        ViewPropertyAnimator animate = this.toolbar.animate();
        if (isInQuietMode2) {
            f = (float) (-this.toolbar.getHeight());
        } else {
            f = 0.0f;
        }
        animate.translationY(f);
        ViewPropertyAnimator animate2 = this.redoSearchButton.animate();
        if (isInQuietMode2) {
            f2 = (float) (-this.toolbar.getHeight());
        }
        animate2.translationY(f2);
    }

    public void onCameraChanged(LatLng latLng, int zoom) {
        this.currentMapState = new MapState(latLng, zoom);
        invalidateCarouselVisibility();
    }

    public void onCameraMove() {
        if (this.redoSearchEnabled) {
            this.redoSearchButton.show(this.dataProvider.isLoadingData());
        }
    }

    static /* synthetic */ void lambda$new$7(ExploreMapView exploreMapView, List wishLists, WishListChangeInfo changeInfo) {
        if (changeInfo != null) {
            for (Mappable mappable : exploreMapView.dataProvider.getMappables()) {
                if (changeInfo.getId() == mappable.getMappableId()) {
                    exploreMapView.mapMarkerManager.addMarker(exploreMapView.dataProvider.createMarkerable(mappable));
                    return;
                }
            }
        }
    }

    public void onMapClick(LatLng latLng) {
        toggleQuietMode();
    }

    public void onDestroyView() {
        this.wishListManager.removeWishListsChangedListener(this.wishListsChangedListener);
        this.handler.removeCallbacksAndMessages(null);
    }

    public Parcelable onSaveInstanceState() {
        return IcepickWrapper.saveInstanceState(this, super.onSaveInstanceState());
    }

    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(Icepick.restoreInstanceState(this, state));
    }
}
