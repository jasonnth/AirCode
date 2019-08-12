package com.airbnb.android.lib.cancellation.host;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LinkActionRow;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.primitives.AirButton;

public class CancellationOtherFragment_ViewBinding implements Unbinder {
    private CancellationOtherFragment target;
    private View view2131756104;
    private View view2131756128;
    private View view2131756130;

    public CancellationOtherFragment_ViewBinding(final CancellationOtherFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.edit_row, "field 'editRow' and method 'onClickEditRow'");
        target2.editRow = (LinkActionRow) Utils.castView(view, C0880R.C0882id.edit_row, "field 'editRow'", LinkActionRow.class);
        this.view2131756128 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickEditRow();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.edit_button, "field 'editButton' and method 'onClickEditButton'");
        target2.editButton = (AirButton) Utils.castView(view2, C0880R.C0882id.edit_button, "field 'editButton'", AirButton.class);
        this.view2131756130 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickEditButton();
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.continue_button, "field 'continueButton' and method 'onClickContinueButton'");
        target2.continueButton = (PrimaryButton) Utils.castView(view3, C0880R.C0882id.continue_button, "field 'continueButton'", PrimaryButton.class);
        this.view2131756104 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickContinueButton();
            }
        });
        target2.reportMessageView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.report_message, "field 'reportMessageView'", TextView.class);
    }

    public void unbind() {
        CancellationOtherFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.editRow = null;
        target2.editButton = null;
        target2.continueButton = null;
        target2.reportMessageView = null;
        this.view2131756128.setOnClickListener(null);
        this.view2131756128 = null;
        this.view2131756130.setOnClickListener(null);
        this.view2131756130 = null;
        this.view2131756104.setOnClickListener(null);
        this.view2131756104 = null;
    }
}
