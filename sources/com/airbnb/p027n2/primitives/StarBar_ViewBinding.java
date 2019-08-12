package com.airbnb.p027n2.primitives;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.primitives.StarBar_ViewBinding */
public class StarBar_ViewBinding implements Unbinder {
    private StarBar target;

    public StarBar_ViewBinding(StarBar target2, View source) {
        this.target = target2;
        target2.starLabel = (AirTextView) Utils.findRequiredViewAsType(source, R.id.star_label, "field 'starLabel'", AirTextView.class);
        target2.rightText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.right_label, "field 'rightText'", AirTextView.class);
        target2.filledSection = (StandardsBarContent) Utils.findRequiredViewAsType(source, R.id.filled_section, "field 'filledSection'", StandardsBarContent.class);
    }

    public void unbind() {
        StarBar target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.starLabel = null;
        target2.rightText = null;
        target2.filledSection = null;
    }
}
