package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;

public class AboutSmartPricingFragment_ViewBinding implements Unbinder {
    private AboutSmartPricingFragment target;
    private View view2131755507;

    public AboutSmartPricingFragment_ViewBinding(final AboutSmartPricingFragment target2, View source) {
        this.target = target2;
        target2.scrollView = (VerboseScrollView) Utils.findRequiredViewAsType(source, C6418R.C6420id.scroll_view, "field 'scrollView'", VerboseScrollView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C6418R.C6420id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C6418R.C6420id.price_settings_button, "method 'onClickPriceSettingsButton'");
        this.view2131755507 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickPriceSettingsButton();
            }
        });
    }

    public void unbind() {
        AboutSmartPricingFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.scrollView = null;
        target2.toolbar = null;
        this.view2131755507.setOnClickListener(null);
        this.view2131755507 = null;
    }
}
