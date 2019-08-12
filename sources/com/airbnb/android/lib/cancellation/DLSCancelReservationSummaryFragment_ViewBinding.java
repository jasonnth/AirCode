package com.airbnb.android.lib.cancellation;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.PrimaryButton;

public class DLSCancelReservationSummaryFragment_ViewBinding implements Unbinder {
    private DLSCancelReservationSummaryFragment target;
    private View view2131755720;

    public DLSCancelReservationSummaryFragment_ViewBinding(final DLSCancelReservationSummaryFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.cancel_button, "field 'cancelButton' and method 'initiateCancellation'");
        target2.cancelButton = (PrimaryButton) Utils.castView(view, C0880R.C0882id.cancel_button, "field 'cancelButton'", PrimaryButton.class);
        this.view2131755720 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.initiateCancellation();
            }
        });
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        DLSCancelReservationSummaryFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.cancelButton = null;
        target2.toolbar = null;
        this.view2131755720.setOnClickListener(null);
        this.view2131755720 = null;
    }
}
