package com.airbnb.android.explore.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.explore.C0857R;

public class MTTabFragment_ViewBinding implements Unbinder {
    private MTTabFragment target;

    public MTTabFragment_ViewBinding(MTTabFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0857R.C0859id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    }

    public void unbind() {
        MTTabFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
    }
}
