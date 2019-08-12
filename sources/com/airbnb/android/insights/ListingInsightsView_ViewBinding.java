package com.airbnb.android.insights;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class ListingInsightsView_ViewBinding implements Unbinder {
    private ListingInsightsView target;

    public ListingInsightsView_ViewBinding(ListingInsightsView target2) {
        this(target2, target2);
    }

    public ListingInsightsView_ViewBinding(ListingInsightsView target2, View source) {
        this.target = target2;
        target2.listingImage = (AirImageView) Utils.findRequiredViewAsType(source, C6552R.C6554id.listing_image_view, "field 'listingImage'", AirImageView.class);
        target2.titleView = (AirTextView) Utils.findRequiredViewAsType(source, C6552R.C6554id.title_view, "field 'titleView'", AirTextView.class);
        target2.descriptionView = (AirTextView) Utils.findRequiredViewAsType(source, C6552R.C6554id.description_view, "field 'descriptionView'", AirTextView.class);
        target2.peekWidth = source.getContext().getResources().getDimensionPixelSize(C6552R.dimen.n2_horizontal_padding_medium);
    }

    public void unbind() {
        ListingInsightsView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.listingImage = null;
        target2.titleView = null;
        target2.descriptionView = null;
    }
}
