package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.InlineCaution_ViewBinding */
public class InlineCaution_ViewBinding implements Unbinder {
    private InlineCaution target;

    public InlineCaution_ViewBinding(InlineCaution target2, View source) {
        this.target = target2;
        target2.cautionText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.caution_text, "field 'cautionText'", AirTextView.class);
    }

    public void unbind() {
        InlineCaution target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.cautionText = null;
    }
}
