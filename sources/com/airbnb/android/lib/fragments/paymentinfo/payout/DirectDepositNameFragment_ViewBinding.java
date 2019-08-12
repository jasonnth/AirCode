package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;

public class DirectDepositNameFragment_ViewBinding implements Unbinder {
    private DirectDepositNameFragment target;
    private View view2131756035;

    public DirectDepositNameFragment_ViewBinding(final DirectDepositNameFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.next_button, "method 'onNextButtonClick'");
        this.view2131756035 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNextButtonClick();
            }
        });
    }

    public void unbind() {
        DirectDepositNameFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        this.view2131756035.setOnClickListener(null);
        this.view2131756035 = null;
    }
}
