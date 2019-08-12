package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.MarketingCard_ViewBinding */
public class MarketingCard_ViewBinding implements Unbinder {
    private MarketingCard target;

    public MarketingCard_ViewBinding(MarketingCard target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageView'", AirImageView.class);
        target2.marketingText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.marketing_text, "field 'marketingText'", AirTextView.class);
        target2.callToActionView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.call_to_action, "field 'callToActionView'", AirTextView.class);
        target2.bottomSpace = Utils.findRequiredView(source, R.id.bottom_space, "field 'bottomSpace'");
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
        target2.clickOverlay = Utils.findRequiredView(source, R.id.click_overlay, "field 'clickOverlay'");
    }

    public void unbind() {
        MarketingCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.marketingText = null;
        target2.callToActionView = null;
        target2.bottomSpace = null;
        target2.divider = null;
        target2.clickOverlay = null;
    }
}
