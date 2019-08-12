package com.airbnb.android.nestedlistings.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.nestedlistings.C7496R;
import com.airbnb.p027n2.components.AirToolbar;

public class NestedListingsChooseParentFragment_ViewBinding implements Unbinder {
    private NestedListingsChooseParentFragment target;

    public NestedListingsChooseParentFragment_ViewBinding(NestedListingsChooseParentFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7496R.C7498id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C7496R.C7498id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    }

    public void unbind() {
        NestedListingsChooseParentFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
    }
}
