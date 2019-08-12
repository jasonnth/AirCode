package com.airbnb.p027n2.components.fixed_footers;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;

/* renamed from: com.airbnb.n2.components.fixed_footers.FixedDualActionFooter_ViewBinding */
public class FixedDualActionFooter_ViewBinding implements Unbinder {
    private FixedDualActionFooter target;

    public FixedDualActionFooter_ViewBinding(FixedDualActionFooter target2, View source) {
        this.target = target2;
        target2.divider = Utils.findRequiredView(source, R.id.fixed_dual_action_footer_divider, "field 'divider'");
        target2.primaryButton = (AirButton) Utils.findRequiredViewAsType(source, R.id.fixed_dual_action_footer_button, "field 'primaryButton'", AirButton.class);
        target2.secondaryButton = (AirButton) Utils.findRequiredViewAsType(source, R.id.fixed_dual_action_footer_button_secondary, "field 'secondaryButton'", AirButton.class);
    }

    public void unbind() {
        FixedDualActionFooter target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.divider = null;
        target2.primaryButton = null;
        target2.secondaryButton = null;
    }
}
