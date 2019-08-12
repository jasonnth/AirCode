package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.NestedListingToggleRow_ViewBinding */
public class NestedListingToggleRow_ViewBinding implements Unbinder {
    private NestedListingToggleRow target;

    public NestedListingToggleRow_ViewBinding(NestedListingToggleRow target2, View source) {
        this.target = target2;
        target2.imageDrawable = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageDrawable'", AirImageView.class);
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.checkboxView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.row_drawable, "field 'checkboxView'", AirImageView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle, "field 'subtitleText'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.section_divider, "field 'divider'");
    }

    public void unbind() {
        NestedListingToggleRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageDrawable = null;
        target2.titleText = null;
        target2.checkboxView = null;
        target2.subtitleText = null;
        target2.divider = null;
    }
}
