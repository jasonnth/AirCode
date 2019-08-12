package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.BigNumberRow_ViewBinding */
public class BigNumberRow_ViewBinding implements Unbinder {
    private BigNumberRow target;

    public BigNumberRow_ViewBinding(BigNumberRow target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleText'", AirTextView.class);
        target2.primarySubtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.primary_subtitle, "field 'primarySubtitleText'", AirTextView.class);
        target2.secondarySubtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.secondary_subtitle, "field 'secondarySubtitleText'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
    }

    public void unbind() {
        BigNumberRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.primarySubtitleText = null;
        target2.secondarySubtitleText = null;
        target2.divider = null;
    }
}
