package com.airbnb.android.p011p3;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.InputMarquee;

/* renamed from: com.airbnb.android.p3.P3ReviewSearchFragment_ViewBinding */
public class P3ReviewSearchFragment_ViewBinding implements Unbinder {
    private P3ReviewSearchFragment target;

    public P3ReviewSearchFragment_ViewBinding(P3ReviewSearchFragment target2, View source) {
        this.target = target2;
        target2.searchRecyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C7532R.C7534id.review_search_recycler_view, "field 'searchRecyclerView'", RecyclerView.class);
        target2.marquee = (InputMarquee) Utils.findRequiredViewAsType(source, C7532R.C7534id.input_marquee, "field 'marquee'", InputMarquee.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7532R.C7534id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        P3ReviewSearchFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.searchRecyclerView = null;
        target2.marquee = null;
        target2.toolbar = null;
    }
}
