package com.airbnb.android.lib.cancellation;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.primitives.AirButton;

public class DLSCancelReservationOtherFragment_ViewBinding implements Unbinder {
    private DLSCancelReservationOtherFragment target;
    private View view2131756292;

    public DLSCancelReservationOtherFragment_ViewBinding(final DLSCancelReservationOtherFragment target2, View source) {
        this.target = target2;
        target2.marquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.marquee, "field 'marquee'", DocumentMarquee.class);
        target2.textRow = (SimpleTextRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.simple_text, "field 'textRow'", SimpleTextRow.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.btn, "field 'btn' and method 'changeReservation'");
        target2.btn = (AirButton) Utils.castView(view, C0880R.C0882id.btn, "field 'btn'", AirButton.class);
        this.view2131756292 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.changeReservation();
            }
        });
    }

    public void unbind() {
        DLSCancelReservationOtherFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marquee = null;
        target2.textRow = null;
        target2.toolbar = null;
        target2.btn = null;
        this.view2131756292.setOnClickListener(null);
        this.view2131756292 = null;
    }
}
