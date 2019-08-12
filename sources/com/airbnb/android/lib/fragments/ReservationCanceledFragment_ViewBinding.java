package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;

public class ReservationCanceledFragment_ViewBinding implements Unbinder {
    private ReservationCanceledFragment target;
    private View view2131756035;

    public ReservationCanceledFragment_ViewBinding(final ReservationCanceledFragment target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.next_button, "field 'nextButton' and method 'clickNext'");
        target2.nextButton = (AirButton) Utils.castView(view, C0880R.C0882id.next_button, "field 'nextButton'", AirButton.class);
        this.view2131756035 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickNext();
            }
        });
        target2.marquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.canceled_marquee, "field 'marquee'", SheetMarquee.class);
        target2.loader = (FrameLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.full_loader, "field 'loader'", FrameLayout.class);
    }

    public void unbind() {
        ReservationCanceledFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.nextButton = null;
        target2.marquee = null;
        target2.loader = null;
        this.view2131756035.setOnClickListener(null);
        this.view2131756035 = null;
    }
}
