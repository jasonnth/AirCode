package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.components.PriceSummary_ViewBinding */
public class PriceSummary_ViewBinding implements Unbinder {
    private PriceSummary target;

    public PriceSummary_ViewBinding(PriceSummary target2, View source) {
        this.target = target2;
        target2.priceTextView = (TextView) Utils.findRequiredViewAsType(source, R.id.price_text_view, "field 'priceTextView'", TextView.class);
        target2.currencyTextView = (TextView) Utils.findRequiredViewAsType(source, R.id.currency_text_view, "field 'currencyTextView'", TextView.class);
        target2.priceBreakdownTextView = (TextView) Utils.findRequiredViewAsType(source, R.id.price_breakdown_text_view, "field 'priceBreakdownTextView'", TextView.class);
        target2.loadingView = (LoadingView) Utils.findRequiredViewAsType(source, R.id.loading_view, "field 'loadingView'", LoadingView.class);
        target2.divider = Utils.findRequiredView(source, R.id.section_divider, "field 'divider'");
    }

    public void unbind() {
        PriceSummary target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.priceTextView = null;
        target2.currencyTextView = null;
        target2.priceBreakdownTextView = null;
        target2.loadingView = null;
        target2.divider = null;
    }
}
