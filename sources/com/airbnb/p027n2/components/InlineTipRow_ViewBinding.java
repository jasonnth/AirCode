package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.InlineTipRow_ViewBinding */
public class InlineTipRow_ViewBinding implements Unbinder {
    private InlineTipRow target;

    public InlineTipRow_ViewBinding(InlineTipRow target2, View source) {
        this.target = target2;
        target2.tipText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.tip_text, "field 'tipText'", AirTextView.class);
        target2.closeView = (ImageView) Utils.findRequiredViewAsType(source, R.id.close_button, "field 'closeView'", ImageView.class);
    }

    public void unbind() {
        InlineTipRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.tipText = null;
        target2.closeView = null;
    }
}
