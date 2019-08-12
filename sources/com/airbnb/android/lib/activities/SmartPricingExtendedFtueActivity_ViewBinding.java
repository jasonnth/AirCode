package com.airbnb.android.lib.activities;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirButton;

public class SmartPricingExtendedFtueActivity_ViewBinding implements Unbinder {
    private SmartPricingExtendedFtueActivity target;
    private View view2131755408;
    private View view2131755475;

    public SmartPricingExtendedFtueActivity_ViewBinding(SmartPricingExtendedFtueActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public SmartPricingExtendedFtueActivity_ViewBinding(final SmartPricingExtendedFtueActivity target2, View source) {
        this.target = target2;
        target2.titleText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.smart_pricing_ftue_title, "field 'titleText'", TextView.class);
        target2.descText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.smart_pricing_ftue_desc, "field 'descText'", TextView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.smart_pricing_ftue_button, "field 'actionButton' and method 'onActionButtonClicked'");
        target2.actionButton = (AirButton) Utils.castView(view, C0880R.C0882id.smart_pricing_ftue_button, "field 'actionButton'", AirButton.class);
        this.view2131755475 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onActionButtonClicked();
            }
        });
        target2.imageView = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.smart_pricing_ftue_image, "field 'imageView'", ImageView.class);
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.btn_close, "method 'onCloseClicked'");
        this.view2131755408 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onCloseClicked();
            }
        });
    }

    public void unbind() {
        SmartPricingExtendedFtueActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.descText = null;
        target2.actionButton = null;
        target2.imageView = null;
        this.view2131755475.setOnClickListener(null);
        this.view2131755475 = null;
        this.view2131755408.setOnClickListener(null);
        this.view2131755408 = null;
    }
}
