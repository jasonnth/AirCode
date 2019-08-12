package com.airbnb.android.managelisting.settings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;

public class ManageListingInstantBookTipFragment_ViewBinding implements Unbinder {
    private ManageListingInstantBookTipFragment target;
    private View view2131755536;
    private View view2131755537;
    private View view2131755538;

    public ManageListingInstantBookTipFragment_ViewBinding(final ManageListingInstantBookTipFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7368R.C7370id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C7368R.C7370id.house_rules_link, "method 'onHouseRulesClicked'");
        this.view2131755536 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onHouseRulesClicked();
            }
        });
        View view2 = Utils.findRequiredView(source, C7368R.C7370id.guest_requirements_link, "method 'onGuestRequirementsClicked'");
        this.view2131755537 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onGuestRequirementsClicked();
            }
        });
        View view3 = Utils.findRequiredView(source, C7368R.C7370id.try_it_out_button, "method 'onTryClicked'");
        this.view2131755538 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onTryClicked();
            }
        });
    }

    public void unbind() {
        ManageListingInstantBookTipFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        this.view2131755536.setOnClickListener(null);
        this.view2131755536 = null;
        this.view2131755537.setOnClickListener(null);
        this.view2131755537 = null;
        this.view2131755538.setOnClickListener(null);
        this.view2131755538 = null;
    }
}
