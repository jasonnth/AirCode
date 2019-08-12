package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.SummaryInterstitial_ViewBinding */
public class SummaryInterstitial_ViewBinding implements Unbinder {
    private SummaryInterstitial target;

    public SummaryInterstitial_ViewBinding(SummaryInterstitial target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleText'", AirTextView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle_text, "field 'subtitleText'", AirTextView.class);
        target2.thirdRowText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.third_row_text, "field 'thirdRowText'", AirTextView.class);
        target2.firstButton = (AirButton) Utils.findRequiredViewAsType(source, R.id.left_button, "field 'firstButton'", AirButton.class);
        target2.secondButton = (AirButton) Utils.findRequiredViewAsType(source, R.id.right_button, "field 'secondButton'", AirButton.class);
    }

    public void unbind() {
        SummaryInterstitial target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.subtitleText = null;
        target2.thirdRowText = null;
        target2.firstButton = null;
        target2.secondButton = null;
    }
}
