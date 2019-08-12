package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.ExpandableCollectionRow_ViewBinding */
public class ExpandableCollectionRow_ViewBinding implements Unbinder {
    private ExpandableCollectionRow target;

    public ExpandableCollectionRow_ViewBinding(ExpandableCollectionRow target2, View source) {
        this.target = target2;
        target2.container = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.container, "field 'container'", LinearLayout.class);
        target2.bottomText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.bottom_text, "field 'bottomText'", AirTextView.class);
        target2.expandText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.expand_text, "field 'expandText'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.section_divider, "field 'divider'");
    }

    public void unbind() {
        ExpandableCollectionRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.container = null;
        target2.bottomText = null;
        target2.expandText = null;
        target2.divider = null;
    }
}
