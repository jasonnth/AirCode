package com.airbnb.android.lib.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class ReservationCancellationWithUserInputFragment_ViewBinding implements Unbinder {
    private ReservationCancellationWithUserInputFragment target;
    private View view2131756035;

    public ReservationCancellationWithUserInputFragment_ViewBinding(final ReservationCancellationWithUserInputFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.next_button, "field 'nextButton' and method 'clickNext'");
        target2.nextButton = (AirButton) Utils.castView(view, C0880R.C0882id.next_button, "field 'nextButton'", AirButton.class);
        this.view2131756035 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickNext();
            }
        });
    }

    public void unbind() {
        ReservationCancellationWithUserInputFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.nextButton = null;
        this.view2131756035.setOnClickListener(null);
        this.view2131756035 = null;
    }
}
