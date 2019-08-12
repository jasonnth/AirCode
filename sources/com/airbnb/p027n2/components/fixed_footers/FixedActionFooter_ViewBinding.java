package com.airbnb.p027n2.components.fixed_footers;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;

/* renamed from: com.airbnb.n2.components.fixed_footers.FixedActionFooter_ViewBinding */
public final class FixedActionFooter_ViewBinding implements Unbinder {
    private FixedActionFooter target;

    public FixedActionFooter_ViewBinding(FixedActionFooter target2, View source) {
        this.target = target2;
        target2.divider = Utils.findRequiredView(source, R.id.fixed_action_footer_divider, "field 'divider'");
        target2.button = (AirButton) Utils.findRequiredViewAsType(source, R.id.fixed_action_footer_button, "field 'button'", AirButton.class);
    }

    public void unbind() {
        FixedActionFooter target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.divider = null;
        target2.button = null;
    }
}
