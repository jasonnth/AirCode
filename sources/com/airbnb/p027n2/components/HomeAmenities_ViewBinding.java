package com.airbnb.p027n2.components;

import android.content.res.Resources;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.HomeAmenities_ViewBinding */
public class HomeAmenities_ViewBinding implements Unbinder {
    private HomeAmenities target;

    public HomeAmenities_ViewBinding(HomeAmenities target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.amenities_heading, "field 'title'", AirTextView.class);
        target2.amenityContainer = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.amenities, "field 'amenityContainer'", LinearLayout.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
        Resources res = source.getContext().getResources();
        target2.iconSize = res.getDimensionPixelSize(R.dimen.n2_listing_amenities_icon_size);
        target2.minSpacing = res.getDimensionPixelSize(R.dimen.n2_listing_amenities_min_spacing);
    }

    public void unbind() {
        HomeAmenities target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.amenityContainer = null;
        target2.divider = null;
    }
}
