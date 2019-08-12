package com.airbnb.android.core.fragments;

import android.support.design.widget.CoordinatorLayout;
import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.AirToolbar;

public class LottieNuxCoverPageFragment_ViewBinding implements Unbinder {
    private LottieNuxCoverPageFragment target;

    public LottieNuxCoverPageFragment_ViewBinding(LottieNuxCoverPageFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0716R.C0718id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0716R.C0718id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.rootView = (CoordinatorLayout) Utils.findRequiredViewAsType(source, C0716R.C0718id.root_view, "field 'rootView'", CoordinatorLayout.class);
    }

    public void unbind() {
        LottieNuxCoverPageFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.rootView = null;
    }
}
