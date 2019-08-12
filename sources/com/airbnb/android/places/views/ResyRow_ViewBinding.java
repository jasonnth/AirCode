package com.airbnb.android.places.views;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.places.C7627R;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.primitives.AirTextView;

public class ResyRow_ViewBinding implements Unbinder {
    private ResyRow target;

    public ResyRow_ViewBinding(ResyRow target2) {
        this(target2, target2);
    }

    public ResyRow_ViewBinding(ResyRow target2, View source) {
        this.target = target2;
        target2.timesCarousel = (Carousel) Utils.findRequiredViewAsType(source, C7627R.C7629id.carousel, "field 'timesCarousel'", Carousel.class);
        target2.titleView = (AirTextView) Utils.findRequiredViewAsType(source, C7627R.C7629id.title, "field 'titleView'", AirTextView.class);
        target2.actionView = (LinearLayout) Utils.findRequiredViewAsType(source, C7627R.C7629id.action, "field 'actionView'", LinearLayout.class);
        target2.changeButton = Utils.findRequiredView(source, C7627R.C7629id.change_button, "field 'changeButton'");
        target2.loadingView = Utils.findRequiredView(source, C7627R.C7629id.loading_view, "field 'loadingView'");
        target2.emptyState = Utils.findRequiredView(source, C7627R.C7629id.empty_state, "field 'emptyState'");
        target2.sectionDivider = Utils.findRequiredView(source, C7627R.C7629id.section_divider, "field 'sectionDivider'");
    }

    public void unbind() {
        ResyRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.timesCarousel = null;
        target2.titleView = null;
        target2.actionView = null;
        target2.changeButton = null;
        target2.loadingView = null;
        target2.emptyState = null;
        target2.sectionDivider = null;
    }
}
