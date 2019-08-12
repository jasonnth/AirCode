package com.airbnb.android.p011p3;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.AirToolbar;

/* renamed from: com.airbnb.android.p3.P3ReviewFragment_ViewBinding */
public class P3ReviewFragment_ViewBinding implements Unbinder {
    private P3ReviewFragment target;

    public P3ReviewFragment_ViewBinding(P3ReviewFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C7532R.C7534id.reviews_recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.airToolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7532R.C7534id.toolbar, "field 'airToolbar'", AirToolbar.class);
    }

    public void unbind() {
        P3ReviewFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.airToolbar = null;
    }
}
