package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.RangeDisplay_ViewBinding */
public class RangeDisplay_ViewBinding implements Unbinder {
    private RangeDisplay target;

    public RangeDisplay_ViewBinding(RangeDisplay target2, View source) {
        this.target = target2;
        target2.startTitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.start_title, "field 'startTitleText'", AirTextView.class);
        target2.startSubtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.start_subtitle, "field 'startSubtitleText'", AirTextView.class);
        target2.endTitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.end_title, "field 'endTitleText'", AirTextView.class);
        target2.endSubtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.end_subtitle, "field 'endSubtitleText'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
    }

    public void unbind() {
        RangeDisplay target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.startTitleText = null;
        target2.startSubtitleText = null;
        target2.endTitleText = null;
        target2.endSubtitleText = null;
        target2.divider = null;
    }
}
