package com.airbnb.p027n2.components.fixed_footers;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.fixed_footers.FixedFlowActionFooter_ViewBinding */
public class FixedFlowActionFooter_ViewBinding implements Unbinder {
    private FixedFlowActionFooter target;

    public FixedFlowActionFooter_ViewBinding(FixedFlowActionFooter target2, View source) {
        this.target = target2;
        target2.divider = Utils.findRequiredView(source, R.id.fixed_flow_action_footer_divider, "field 'divider'");
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.fixed_flow_action_footer_title, "field 'title'", AirTextView.class);
        target2.subtitle = (AirTextView) Utils.findRequiredViewAsType(source, R.id.fixed_flow_action_footer_subtitle, "field 'subtitle'", AirTextView.class);
        target2.button = (AirButton) Utils.findRequiredViewAsType(source, R.id.fixed_flow_action_footer_button, "field 'button'", AirButton.class);
    }

    public void unbind() {
        FixedFlowActionFooter target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.divider = null;
        target2.title = null;
        target2.subtitle = null;
        target2.button = null;
    }
}
