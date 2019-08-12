package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.SmallMarquee_ViewBinding */
public class SmallMarquee_ViewBinding implements Unbinder {
    private SmallMarquee target;

    public SmallMarquee_ViewBinding(SmallMarquee target2, View source) {
        this.target = target2;
        target2.marqueeTitleView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.marquee_title, "field 'marqueeTitleView'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
    }

    public void unbind() {
        SmallMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marqueeTitleView = null;
        target2.divider = null;
    }
}
