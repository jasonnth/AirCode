package com.airbnb.android.explore.fragments;

import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class MTHomesTabFragment_ViewBinding extends MTTabFragment_ViewBinding {
    private MTHomesTabFragment target;
    private View view2131755559;

    public MTHomesTabFragment_ViewBinding(final MTHomesTabFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.filtersCarousel = (Carousel) Utils.findRequiredViewAsType(source, C0857R.C0859id.filters_carousel, "field 'filtersCarousel'", Carousel.class);
        target2.filtersButton = (AirTextView) Utils.findRequiredViewAsType(source, C0857R.C0859id.filters, "field 'filtersButton'", AirTextView.class);
        target2.filtersIconButton = (AirImageView) Utils.findRequiredViewAsType(source, C0857R.C0859id.filters_icon, "field 'filtersIconButton'", AirImageView.class);
        View view = Utils.findRequiredView(source, C0857R.C0859id.filters_container, "method 'onFiltersClick'");
        this.view2131755559 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onFiltersClick();
            }
        });
    }

    public void unbind() {
        MTHomesTabFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.filtersCarousel = null;
        target2.filtersButton = null;
        target2.filtersIconButton = null;
        this.view2131755559.setOnClickListener(null);
        this.view2131755559 = null;
        super.unbind();
    }
}
