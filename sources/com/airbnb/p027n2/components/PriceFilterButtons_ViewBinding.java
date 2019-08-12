package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.PriceFilterButtons_ViewBinding */
public class PriceFilterButtons_ViewBinding implements Unbinder {
    private PriceFilterButtons target;

    public PriceFilterButtons_ViewBinding(PriceFilterButtons target2, View source) {
        this.target = target2;
        target2.button1 = (AirTextView) Utils.findRequiredViewAsType(source, R.id.button1, "field 'button1'", AirTextView.class);
        target2.button1Divider = Utils.findRequiredView(source, R.id.button1_divider, "field 'button1Divider'");
        target2.button2 = (AirTextView) Utils.findRequiredViewAsType(source, R.id.button2, "field 'button2'", AirTextView.class);
        target2.button2Divider = Utils.findRequiredView(source, R.id.button2_divider, "field 'button2Divider'");
        target2.button3 = (AirTextView) Utils.findRequiredViewAsType(source, R.id.button3, "field 'button3'", AirTextView.class);
    }

    public void unbind() {
        PriceFilterButtons target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.button1 = null;
        target2.button1Divider = null;
        target2.button2 = null;
        target2.button2Divider = null;
        target2.button3 = null;
    }
}
