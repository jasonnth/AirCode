package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.SectionHeader_ViewBinding */
public class SectionHeader_ViewBinding implements Unbinder {
    private SectionHeader target;

    public SectionHeader_ViewBinding(SectionHeader target2, View source) {
        this.target = target2;
        target2.titleView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.section_header_title, "field 'titleView'", AirTextView.class);
        target2.descriptionView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.section_header_description, "field 'descriptionView'", AirTextView.class);
        target2.button = (AirTextView) Utils.findRequiredViewAsType(source, R.id.section_header_button, "field 'button'", AirTextView.class);
    }

    public void unbind() {
        SectionHeader target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleView = null;
        target2.descriptionView = null;
        target2.button = null;
    }
}
