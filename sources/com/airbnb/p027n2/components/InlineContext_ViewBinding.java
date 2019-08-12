package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.InlineContext_ViewBinding */
public class InlineContext_ViewBinding implements Unbinder {
    private InlineContext target;

    public InlineContext_ViewBinding(InlineContext target2, View source) {
        this.target = target2;
        target2.textViewStatus = (AirTextView) Utils.findRequiredViewAsType(source, R.id.text_status, "field 'textViewStatus'", AirTextView.class);
        target2.textViewStatusDetails = (AirTextView) Utils.findRequiredViewAsType(source, R.id.text_status_details, "field 'textViewStatusDetails'", AirTextView.class);
    }

    public void unbind() {
        InlineContext target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.textViewStatus = null;
        target2.textViewStatusDetails = null;
    }
}
