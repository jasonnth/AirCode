package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.FixedActionFooterWithText_ViewBinding */
public class FixedActionFooterWithText_ViewBinding implements Unbinder {
    private FixedActionFooterWithText target;

    public FixedActionFooterWithText_ViewBinding(FixedActionFooterWithText target2, View source) {
        this.target = target2;
        target2.textView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.text, "field 'textView'", AirTextView.class);
        target2.button = (AirButton) Utils.findRequiredViewAsType(source, R.id.button, "field 'button'", AirButton.class);
    }

    public void unbind() {
        FixedActionFooterWithText target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.textView = null;
        target2.button = null;
    }
}
