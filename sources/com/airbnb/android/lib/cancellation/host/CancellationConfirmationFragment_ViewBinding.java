package com.airbnb.android.lib.cancellation.host;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.SheetMarquee;

public class CancellationConfirmationFragment_ViewBinding implements Unbinder {
    private CancellationConfirmationFragment target;
    private View view2131756127;

    public CancellationConfirmationFragment_ViewBinding(final CancellationConfirmationFragment target2, View source) {
        this.target = target2;
        target2.marquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.marquee, "field 'marquee'", SheetMarquee.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.okay_button, "method 'onClickOkay'");
        this.view2131756127 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickOkay();
            }
        });
    }

    public void unbind() {
        CancellationConfirmationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marquee = null;
        this.view2131756127.setOnClickListener(null);
        this.view2131756127 = null;
    }
}