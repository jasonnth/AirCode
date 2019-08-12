package com.airbnb.android.core.map;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.views.AirbnbMapView;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.MapSearchButton;
import com.airbnb.p027n2.components.NavigationPill;

public class ExploreMapView_ViewBinding implements Unbinder {
    private ExploreMapView target;
    private View view2131756030;

    public ExploreMapView_ViewBinding(ExploreMapView target2) {
        this(target2, target2);
    }

    public ExploreMapView_ViewBinding(final ExploreMapView target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0716R.C0718id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.airMapView = (AirbnbMapView) Utils.findRequiredViewAsType(source, C0716R.C0718id.airmapview, "field 'airMapView'", AirbnbMapView.class);
        View view = Utils.findRequiredView(source, C0716R.C0718id.redo_search_button, "field 'redoSearchButton' and method 'onRedoSearchClicked'");
        target2.redoSearchButton = (MapSearchButton) Utils.castView(view, C0716R.C0718id.redo_search_button, "field 'redoSearchButton'", MapSearchButton.class);
        this.view2131756030 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onRedoSearchClicked();
            }
        });
        target2.carousel = (Carousel) Utils.findRequiredViewAsType(source, C0716R.C0718id.map_listings_carousel, "field 'carousel'", Carousel.class);
        target2.pillButton = (NavigationPill) Utils.findRequiredViewAsType(source, C0716R.C0718id.trips_toggle_pill, "field 'pillButton'", NavigationPill.class);
        target2.coordinatorLayout = (CoordinatorLayout) Utils.findRequiredViewAsType(source, C0716R.C0718id.coordinator_layout, "field 'coordinatorLayout'", CoordinatorLayout.class);
        target2.carouselAndCoordinatorContainer = (ViewGroup) Utils.findRequiredViewAsType(source, C0716R.C0718id.carousel_and_coordinator_container, "field 'carouselAndCoordinatorContainer'", ViewGroup.class);
        target2.mapPaddingPx = source.getContext().getResources().getDimensionPixelSize(C0716R.dimen.explore_map_padding);
    }

    public void unbind() {
        ExploreMapView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.airMapView = null;
        target2.redoSearchButton = null;
        target2.carousel = null;
        target2.pillButton = null;
        target2.coordinatorLayout = null;
        target2.carouselAndCoordinatorContainer = null;
        this.view2131756030.setOnClickListener(null);
        this.view2131756030 = null;
    }
}
