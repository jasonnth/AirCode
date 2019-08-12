package com.airbnb.android.listing.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.listing.C7213R;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionFooter;
import com.airbnb.p027n2.primitives.AirTextView;

public class WhatsMyPlaceWorthFragment_ViewBinding implements Unbinder {
    private WhatsMyPlaceWorthFragment target;
    private View view2131755494;
    private View view2131755542;

    public WhatsMyPlaceWorthFragment_ViewBinding(final WhatsMyPlaceWorthFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7213R.C7215id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C7213R.C7215id.scroll_view, "field 'recyclerView'", AirRecyclerView.class);
        View view = Utils.findRequiredView(source, C7213R.C7215id.footer, "field 'footer' and method 'toggleDisclaimerVisibility'");
        target2.footer = (FixedFlowActionFooter) Utils.castView(view, C7213R.C7215id.footer, "field 'footer'", FixedFlowActionFooter.class);
        this.view2131755494 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.toggleDisclaimerVisibility();
            }
        });
        View view2 = Utils.findRequiredView(source, C7213R.C7215id.disclaimer_text, "field 'disclaimer' and method 'toggleDisclaimerVisibility'");
        target2.disclaimer = (AirTextView) Utils.castView(view2, C7213R.C7215id.disclaimer_text, "field 'disclaimer'", AirTextView.class);
        this.view2131755542 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.toggleDisclaimerVisibility();
            }
        });
    }

    public void unbind() {
        WhatsMyPlaceWorthFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.footer = null;
        target2.disclaimer = null;
        this.view2131755494.setOnClickListener(null);
        this.view2131755494 = null;
        this.view2131755542.setOnClickListener(null);
        this.view2131755542 = null;
    }
}
