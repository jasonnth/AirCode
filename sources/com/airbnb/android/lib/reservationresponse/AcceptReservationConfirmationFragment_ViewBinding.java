package com.airbnb.android.lib.reservationresponse;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.SimpleTextRow;

public class AcceptReservationConfirmationFragment_ViewBinding implements Unbinder {
    private AcceptReservationConfirmationFragment target;
    private View view2131755990;
    private View view2131755991;

    public AcceptReservationConfirmationFragment_ViewBinding(final AcceptReservationConfirmationFragment target2, View source) {
        this.target = target2;
        target2.upsellRow = (SimpleTextRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.ib_upsell, "field 'upsellRow'", SimpleTextRow.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.done_button, "method 'onDoneClicked'");
        this.view2131755991 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onDoneClicked();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.ib_upsell_button, "method 'onUpsellClicked'");
        this.view2131755990 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onUpsellClicked();
            }
        });
    }

    public void unbind() {
        AcceptReservationConfirmationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.upsellRow = null;
        this.view2131755991.setOnClickListener(null);
        this.view2131755991 = null;
        this.view2131755990.setOnClickListener(null);
        this.view2131755990 = null;
    }
}
