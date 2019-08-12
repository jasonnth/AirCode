package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.ScratchMicroRowWithRightText_ViewBinding */
public class ScratchMicroRowWithRightText_ViewBinding implements Unbinder {
    private ScratchMicroRowWithRightText target;

    public ScratchMicroRowWithRightText_ViewBinding(ScratchMicroRowWithRightText target2, View source) {
        this.target = target2;
        target2.textView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.text, "field 'textView'", AirTextView.class);
        target2.titleView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleView'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.section_divider, "field 'divider'");
    }

    public void unbind() {
        ScratchMicroRowWithRightText target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.textView = null;
        target2.titleView = null;
        target2.divider = null;
    }
}
