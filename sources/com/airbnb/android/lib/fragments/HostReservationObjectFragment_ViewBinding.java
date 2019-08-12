package com.airbnb.android.lib.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.PrimaryButton;

public class HostReservationObjectFragment_ViewBinding implements Unbinder {
    private HostReservationObjectFragment target;
    private View view2131756374;

    public HostReservationObjectFragment_ViewBinding(final HostReservationObjectFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.loader = Utils.findRequiredView(source, C0880R.C0882id.full_loader, "field 'loader'");
        View view = Utils.findRequiredView(source, C0880R.C0882id.write_review_button, "field 'reviewButton' and method 'onWriteReview'");
        target2.reviewButton = (PrimaryButton) Utils.castView(view, C0880R.C0882id.write_review_button, "field 'reviewButton'", PrimaryButton.class);
        this.view2131756374 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onWriteReview();
            }
        });
    }

    public void unbind() {
        HostReservationObjectFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.loader = null;
        target2.reviewButton = null;
        this.view2131756374.setOnClickListener(null);
        this.view2131756374 = null;
    }
}
