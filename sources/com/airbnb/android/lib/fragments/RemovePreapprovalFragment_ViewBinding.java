package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;

public class RemovePreapprovalFragment_ViewBinding implements Unbinder {
    private RemovePreapprovalFragment target;
    private View view2131755720;
    private View view2131756679;

    public RemovePreapprovalFragment_ViewBinding(final RemovePreapprovalFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.remove_button, "field 'removeButton' and method 'clickAccept'");
        target2.removeButton = (AirButton) Utils.castView(view, C0880R.C0882id.remove_button, "field 'removeButton'", AirButton.class);
        this.view2131756679 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickAccept();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.cancel_button, "field 'cancelButton' and method 'clickCancel'");
        target2.cancelButton = (AirButton) Utils.castView(view2, C0880R.C0882id.cancel_button, "field 'cancelButton'", AirButton.class);
        this.view2131755720 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickCancel();
            }
        });
        target2.marquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.remove_marquee, "field 'marquee'", SheetMarquee.class);
    }

    public void unbind() {
        RemovePreapprovalFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.removeButton = null;
        target2.cancelButton = null;
        target2.marquee = null;
        this.view2131756679.setOnClickListener(null);
        this.view2131756679 = null;
        this.view2131755720.setOnClickListener(null);
        this.view2131755720 = null;
    }
}
