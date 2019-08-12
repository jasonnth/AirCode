package com.airbnb.android.booking.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.p336n2.ArrivalTimeSelectionView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class ArrivalDetailsFragment_ViewBinding implements Unbinder {
    private ArrivalDetailsFragment target;
    private View view2131755480;

    public ArrivalDetailsFragment_ViewBinding(final ArrivalDetailsFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0704R.C0706id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.selectionView = (ArrivalTimeSelectionView) Utils.findRequiredViewAsType(source, C0704R.C0706id.selection_view, "field 'selectionView'", ArrivalTimeSelectionView.class);
        View view = Utils.findRequiredView(source, C0704R.C0706id.apply_button, "field 'applyButton' and method 'confirmArrivalTime'");
        target2.applyButton = (AirButton) Utils.castView(view, C0704R.C0706id.apply_button, "field 'applyButton'", AirButton.class);
        this.view2131755480 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.confirmArrivalTime();
            }
        });
    }

    public void unbind() {
        ArrivalDetailsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.selectionView = null;
        target2.applyButton = null;
        this.view2131755480.setOnClickListener(null);
        this.view2131755480 = null;
    }
}
