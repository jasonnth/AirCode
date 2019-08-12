package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.LinkableLegalTextRow_ViewBinding */
public class LinkableLegalTextRow_ViewBinding implements Unbinder {
    private LinkableLegalTextRow target;

    public LinkableLegalTextRow_ViewBinding(LinkableLegalTextRow target2, View source) {
        this.target = target2;
        target2.termsTitle = (AirTextView) Utils.findRequiredViewAsType(source, R.id.termsTitle, "field 'termsTitle'", AirTextView.class);
        target2.termsBody = (AirTextView) Utils.findRequiredViewAsType(source, R.id.termsBody, "field 'termsBody'", AirTextView.class);
        target2.fxBody = (AirTextView) Utils.findRequiredViewAsType(source, R.id.fxBody, "field 'fxBody'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.section_divider, "field 'divider'");
    }

    public void unbind() {
        LinkableLegalTextRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.termsTitle = null;
        target2.termsBody = null;
        target2.fxBody = null;
        target2.divider = null;
    }
}
