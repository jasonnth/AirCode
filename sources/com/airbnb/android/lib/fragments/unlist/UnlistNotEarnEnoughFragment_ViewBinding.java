package com.airbnb.android.lib.fragments.unlist;

import android.view.View;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.GroupedCheck;

public class UnlistNotEarnEnoughFragment_ViewBinding extends BaseSnoozeListingFragment_ViewBinding {
    private UnlistNotEarnEnoughFragment target;
    private View view2131756845;
    private View view2131756849;

    public UnlistNotEarnEnoughFragment_ViewBinding(final UnlistNotEarnEnoughFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.sendAlertsCheckbox = (GroupedCheck) Utils.findRequiredViewAsType(source, C0880R.C0882id.not_earn_enough_send_alert_checkbox, "field 'sendAlertsCheckbox'", GroupedCheck.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.not_earn_enough_keep_listing_listed_button, "method 'keepListingListed'");
        this.view2131756849 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.keepListingListed();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.unlist_my_space_btn, "method 'unlistListing'");
        this.view2131756845 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.unlistListing();
            }
        });
    }

    public void unbind() {
        UnlistNotEarnEnoughFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.sendAlertsCheckbox = null;
        this.view2131756849.setOnClickListener(null);
        this.view2131756849 = null;
        this.view2131756845.setOnClickListener(null);
        this.view2131756845 = null;
        super.unbind();
    }
}
