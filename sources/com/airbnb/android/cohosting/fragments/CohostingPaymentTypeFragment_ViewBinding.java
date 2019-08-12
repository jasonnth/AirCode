package com.airbnb.android.cohosting.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.p027n2.components.AirToolbar;

public class CohostingPaymentTypeFragment_ViewBinding implements Unbinder {
    private CohostingPaymentTypeFragment target;
    private View view2131755488;

    public CohostingPaymentTypeFragment_ViewBinding(final CohostingPaymentTypeFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5658R.C5660id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C5658R.C5660id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view = Utils.findRequiredView(source, C5658R.C5660id.save_button, "method 'saveFeeType'");
        this.view2131755488 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.saveFeeType();
            }
        });
    }

    public void unbind() {
        CohostingPaymentTypeFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        this.view2131755488.setOnClickListener(null);
        this.view2131755488 = null;
    }
}