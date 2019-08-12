package com.airbnb.android.p011p3;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.InfiniteDotIndicator;
import com.airbnb.p027n2.primitives.AirButton;

/* renamed from: com.airbnb.android.p3.HomeTourFragment_ViewBinding */
public class HomeTourFragment_ViewBinding implements Unbinder {
    private HomeTourFragment target;

    public HomeTourFragment_ViewBinding(HomeTourFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7532R.C7534id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.carousel = (Carousel) Utils.findRequiredViewAsType(source, C7532R.C7534id.room_item_carousel, "field 'carousel'", Carousel.class);
        target2.dotIndicator = (InfiniteDotIndicator) Utils.findRequiredViewAsType(source, C7532R.C7534id.home_tour_dot_indicator, "field 'dotIndicator'", InfiniteDotIndicator.class);
        target2.showAllRoomsButton = (AirButton) Utils.findRequiredViewAsType(source, C7532R.C7534id.show_all_rooms_button, "field 'showAllRoomsButton'", AirButton.class);
    }

    public void unbind() {
        HomeTourFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.carousel = null;
        target2.dotIndicator = null;
        target2.showAllRoomsButton = null;
    }
}
