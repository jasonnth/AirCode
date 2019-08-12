package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.PricingBreakdownRow_ViewBinding */
public class PricingBreakdownRow_ViewBinding implements Unbinder {
    private PricingBreakdownRow target;

    public PricingBreakdownRow_ViewBinding(PricingBreakdownRow target2, View source) {
        this.target = target2;
        target2.pricingItemContainer = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.pricing_item_container, "field 'pricingItemContainer'", LinearLayout.class);
        target2.totalPriceTitle = (AirTextView) Utils.findRequiredViewAsType(source, R.id.total_price_title, "field 'totalPriceTitle'", AirTextView.class);
        target2.totalPriceInfoText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.total_price_infoText, "field 'totalPriceInfoText'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.section_divider, "field 'divider'");
    }

    public void unbind() {
        PricingBreakdownRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.pricingItemContainer = null;
        target2.totalPriceTitle = null;
        target2.totalPriceInfoText = null;
        target2.divider = null;
    }
}
