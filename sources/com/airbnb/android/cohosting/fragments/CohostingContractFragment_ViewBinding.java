package com.airbnb.android.cohosting.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.RefreshLoader;

public class CohostingContractFragment_ViewBinding implements Unbinder {
    private CohostingContractFragment target;

    public CohostingContractFragment_ViewBinding(CohostingContractFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5658R.C5660id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C5658R.C5660id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.refreshLoader = (RefreshLoader) Utils.findRequiredViewAsType(source, C5658R.C5660id.loading_row, "field 'refreshLoader'", RefreshLoader.class);
    }

    public void unbind() {
        CohostingContractFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.refreshLoader = null;
    }
}