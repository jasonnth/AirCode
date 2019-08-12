package com.airbnb.android.lib.cancellation.host;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.components.UserDetailsActionRow;

public class CancellationOverviewFragment_ViewBinding implements Unbinder {
    private CancellationOverviewFragment target;
    private View view2131756071;

    public CancellationOverviewFragment_ViewBinding(final CancellationOverviewFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.guestSummary = (UserDetailsActionRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.guest_summary, "field 'guestSummary'", UserDetailsActionRow.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.submit_button, "field 'submitButton' and method 'onSubmitCancellation'");
        target2.submitButton = (PrimaryButton) Utils.castView(view, C0880R.C0882id.submit_button, "field 'submitButton'", PrimaryButton.class);
        this.view2131756071 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSubmitCancellation();
            }
        });
        target2.originalPayoutRow = (StandardRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.payout_breakdown_original_payout_row, "field 'originalPayoutRow'", StandardRow.class);
        target2.breakdownFeeRow = (StandardRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.payout_breakdown_fee_row, "field 'breakdownFeeRow'", StandardRow.class);
    }

    public void unbind() {
        CancellationOverviewFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.guestSummary = null;
        target2.submitButton = null;
        target2.originalPayoutRow = null;
        target2.breakdownFeeRow = null;
        this.view2131756071.setOnClickListener(null);
        this.view2131756071 = null;
    }
}
