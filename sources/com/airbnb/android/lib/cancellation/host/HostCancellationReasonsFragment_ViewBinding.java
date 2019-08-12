package com.airbnb.android.lib.cancellation.host;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.StandardRow;

public class HostCancellationReasonsFragment_ViewBinding implements Unbinder {
    private HostCancellationReasonsFragment target;
    private View view2131756359;
    private View view2131756360;
    private View view2131756361;
    private View view2131756362;
    private View view2131756363;
    private View view2131756364;
    private View view2131756365;

    public HostCancellationReasonsFragment_ViewBinding(final HostCancellationReasonsFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.dates_unavailable_row, "field 'datesUnavailableRow' and method 'onClickDatesUnavailableRow'");
        target2.datesUnavailableRow = (StandardRow) Utils.castView(view, C0880R.C0882id.dates_unavailable_row, "field 'datesUnavailableRow'", StandardRow.class);
        this.view2131756359 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickDatesUnavailableRow();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.alter_reservation_row, "method 'onClickAlterReservationsRow'");
        this.view2131756360 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickAlterReservationsRow();
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.undergoing_maintenance_row, "method 'onClickUndergoingMaintenanceRow'");
        this.view2131756361 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickUndergoingMaintenanceRow();
            }
        });
        View view4 = Utils.findRequiredView(source, C0880R.C0882id.extenuating_circumstances_row, "method 'onClickExtenuatingCircumstancesRow'");
        this.view2131756362 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickExtenuatingCircumstancesRow();
            }
        });
        View view5 = Utils.findRequiredView(source, C0880R.C0882id.guest_cancel_row, "method 'onClickGuestCancelRow'");
        this.view2131756363 = view5;
        view5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickGuestCancelRow();
            }
        });
        View view6 = Utils.findRequiredView(source, C0880R.C0882id.uncomfortable_behavior_row, "method 'onClickUncomfortableBehaviorRow'");
        this.view2131756364 = view6;
        view6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickUncomfortableBehaviorRow();
            }
        });
        View view7 = Utils.findRequiredView(source, C0880R.C0882id.other_row, "method 'onClickOtherRow'");
        this.view2131756365 = view7;
        view7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickOtherRow();
            }
        });
    }

    public void unbind() {
        HostCancellationReasonsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.datesUnavailableRow = null;
        this.view2131756359.setOnClickListener(null);
        this.view2131756359 = null;
        this.view2131756360.setOnClickListener(null);
        this.view2131756360 = null;
        this.view2131756361.setOnClickListener(null);
        this.view2131756361 = null;
        this.view2131756362.setOnClickListener(null);
        this.view2131756362 = null;
        this.view2131756363.setOnClickListener(null);
        this.view2131756363 = null;
        this.view2131756364.setOnClickListener(null);
        this.view2131756364 = null;
        this.view2131756365.setOnClickListener(null);
        this.view2131756365 = null;
    }
}
