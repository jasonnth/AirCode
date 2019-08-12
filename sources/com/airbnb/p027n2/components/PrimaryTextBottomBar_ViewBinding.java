package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.PrimaryTextBottomBar_ViewBinding */
public class PrimaryTextBottomBar_ViewBinding implements Unbinder {
    private PrimaryTextBottomBar target;

    public PrimaryTextBottomBar_ViewBinding(PrimaryTextBottomBar target2, View source) {
        this.target = target2;
        target2.description = (AirTextView) Utils.findRequiredViewAsType(source, R.id.description, "field 'description'", AirTextView.class);
        target2.button = (AirButton) Utils.findRequiredViewAsType(source, R.id.button, "field 'button'", AirButton.class);
        target2.dividerView = Utils.findRequiredView(source, R.id.divider, "field 'dividerView'");
    }

    public void unbind() {
        PrimaryTextBottomBar target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.description = null;
        target2.button = null;
        target2.dividerView = null;
    }
}
