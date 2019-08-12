package com.airbnb.android.lib.cancellation.host;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.StandardRow;

public class PenaltyFreeCancellationTrialFragment_ViewBinding implements Unbinder {
    private PenaltyFreeCancellationTrialFragment target;
    private View view2131756104;
    private View view2131756618;
    private View view2131756619;

    public PenaltyFreeCancellationTrialFragment_ViewBinding(final PenaltyFreeCancellationTrialFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.description = (StandardRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.description, "field 'description'", StandardRow.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.continue_button, "method 'onClickContinue'");
        this.view2131756104 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickContinue();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.view_penalties_link, "method 'onClickViewPenalties'");
        this.view2131756618 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickViewPenalties();
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.keep_reservation_link, "method 'onClickKeepReservation'");
        this.view2131756619 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickKeepReservation();
            }
        });
    }

    public void unbind() {
        PenaltyFreeCancellationTrialFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.description = null;
        this.view2131756104.setOnClickListener(null);
        this.view2131756104 = null;
        this.view2131756618.setOnClickListener(null);
        this.view2131756618 = null;
        this.view2131756619.setOnClickListener(null);
        this.view2131756619 = null;
    }
}
