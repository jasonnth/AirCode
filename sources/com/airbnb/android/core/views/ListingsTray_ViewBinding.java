package com.airbnb.android.core.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.collections.Carousel;
import com.airbnb.p027n2.components.SectionHeader;

public class ListingsTray_ViewBinding implements Unbinder {
    private ListingsTray target;

    public ListingsTray_ViewBinding(ListingsTray target2) {
        this(target2, target2);
    }

    public ListingsTray_ViewBinding(ListingsTray target2, View source) {
        this.target = target2;
        target2.header = (SectionHeader) Utils.findRequiredViewAsType(source, C0716R.C0718id.section_header, "field 'header'", SectionHeader.class);
        target2.carousel = (Carousel) Utils.findRequiredViewAsType(source, C0716R.C0718id.carousel, "field 'carousel'", Carousel.class);
    }

    public void unbind() {
        ListingsTray target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.header = null;
        target2.carousel = null;
    }
}
