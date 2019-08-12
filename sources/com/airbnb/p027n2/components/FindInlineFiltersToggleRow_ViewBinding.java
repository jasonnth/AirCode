package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.FindInlineFiltersToggleRow_ViewBinding */
public class FindInlineFiltersToggleRow_ViewBinding implements Unbinder {
    private FindInlineFiltersToggleRow target;

    public FindInlineFiltersToggleRow_ViewBinding(FindInlineFiltersToggleRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.checkboxView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.row_drawable, "field 'checkboxView'", AirImageView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle, "field 'subtitleText'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.section_divider, "field 'divider'");
    }

    public void unbind() {
        FindInlineFiltersToggleRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.checkboxView = null;
        target2.subtitleText = null;
        target2.divider = null;
    }
}
