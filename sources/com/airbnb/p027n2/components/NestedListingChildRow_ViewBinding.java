package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.NestedListingChildRow_ViewBinding */
public class NestedListingChildRow_ViewBinding implements Unbinder {
    private NestedListingChildRow target;

    public NestedListingChildRow_ViewBinding(NestedListingChildRow target2, View source) {
        this.target = target2;
        target2.imageDrawable = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageDrawable'", AirImageView.class);
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle, "field 'subtitleText'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.section_divider, "field 'divider'");
    }

    public void unbind() {
        NestedListingChildRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageDrawable = null;
        target2.titleText = null;
        target2.subtitleText = null;
        target2.divider = null;
    }
}
