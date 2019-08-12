package com.airbnb.android.cohosting.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class CohostingStopShareEarningsFragment_ViewBinding implements Unbinder {
    private CohostingStopShareEarningsFragment target;
    private View view2131755499;

    public CohostingStopShareEarningsFragment_ViewBinding(final CohostingStopShareEarningsFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5658R.C5660id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C5658R.C5660id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view = Utils.findRequiredView(source, C5658R.C5660id.stop_button, "field 'stopButton' and method 'RemoveContract'");
        target2.stopButton = (AirButton) Utils.castView(view, C5658R.C5660id.stop_button, "field 'stopButton'", AirButton.class);
        this.view2131755499 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.RemoveContract();
            }
        });
    }

    public void unbind() {
        CohostingStopShareEarningsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.stopButton = null;
        this.view2131755499.setOnClickListener(null);
        this.view2131755499 = null;
    }
}
