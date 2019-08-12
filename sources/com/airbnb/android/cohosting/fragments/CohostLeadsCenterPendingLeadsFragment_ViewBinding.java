package com.airbnb.android.cohosting.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.p027n2.collections.AirRecyclerView;

public class CohostLeadsCenterPendingLeadsFragment_ViewBinding implements Unbinder {
    private CohostLeadsCenterPendingLeadsFragment target;

    public CohostLeadsCenterPendingLeadsFragment_ViewBinding(CohostLeadsCenterPendingLeadsFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C5658R.C5660id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
    }

    public void unbind() {
        CohostLeadsCenterPendingLeadsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
    }
}