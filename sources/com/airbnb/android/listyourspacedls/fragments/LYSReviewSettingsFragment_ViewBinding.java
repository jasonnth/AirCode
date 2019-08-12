package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.StandardRow;

public class LYSReviewSettingsFragment_ViewBinding extends LYSBaseFragment_ViewBinding {
    private LYSReviewSettingsFragment target;
    private View view2131755495;

    public LYSReviewSettingsFragment_ViewBinding(final LYSReviewSettingsFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.scrollView = (VerboseScrollView) Utils.findRequiredViewAsType(source, C7251R.C7253id.scroll_view, "field 'scrollView'", VerboseScrollView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7251R.C7253id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.guestRequirementsRow = (StandardRow) Utils.findRequiredViewAsType(source, C7251R.C7253id.guest_requirements, "field 'guestRequirementsRow'", StandardRow.class);
        target2.houseRulesRow = (StandardRow) Utils.findRequiredViewAsType(source, C7251R.C7253id.house_rules, "field 'houseRulesRow'", StandardRow.class);
        target2.availabilityRow = (StandardRow) Utils.findRequiredViewAsType(source, C7251R.C7253id.availability, "field 'availabilityRow'", StandardRow.class);
        target2.pricingRow = (StandardRow) Utils.findRequiredViewAsType(source, C7251R.C7253id.pricing, "field 'pricingRow'", StandardRow.class);
        target2.additionalPricingRow = (StandardRow) Utils.findRequiredViewAsType(source, C7251R.C7253id.additional_pricing, "field 'additionalPricingRow'", StandardRow.class);
        View view = Utils.findRequiredView(source, C7251R.C7253id.next_btn, "method 'onClickNext'");
        this.view2131755495 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickNext();
            }
        });
    }

    public void unbind() {
        LYSReviewSettingsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.scrollView = null;
        target2.toolbar = null;
        target2.guestRequirementsRow = null;
        target2.houseRulesRow = null;
        target2.availabilityRow = null;
        target2.pricingRow = null;
        target2.additionalPricingRow = null;
        this.view2131755495.setOnClickListener(null);
        this.view2131755495 = null;
        super.unbind();
    }
}
