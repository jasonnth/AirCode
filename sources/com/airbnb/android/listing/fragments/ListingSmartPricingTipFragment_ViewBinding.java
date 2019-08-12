package com.airbnb.android.listing.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.listing.C7213R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirButton;

public class ListingSmartPricingTipFragment_ViewBinding implements Unbinder {
    private ListingSmartPricingTipFragment target;
    private View view2131755199;

    public ListingSmartPricingTipFragment_ViewBinding(final ListingSmartPricingTipFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7213R.C7215id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.marquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C7213R.C7215id.document_marquee, "field 'marquee'", DocumentMarquee.class);
        View view = Utils.findRequiredView(source, C7213R.C7215id.button, "field 'button' and method 'onTryClicked'");
        target2.button = (AirButton) Utils.castView(view, C7213R.C7215id.button, "field 'button'", AirButton.class);
        this.view2131755199 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onTryClicked();
            }
        });
    }

    public void unbind() {
        ListingSmartPricingTipFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.marquee = null;
        target2.button = null;
        this.view2131755199.setOnClickListener(null);
        this.view2131755199 = null;
    }
}
