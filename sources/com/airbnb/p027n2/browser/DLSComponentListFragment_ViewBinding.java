package com.airbnb.p027n2.browser;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.AirToolbar;

/* renamed from: com.airbnb.n2.browser.DLSComponentListFragment_ViewBinding */
public class DLSComponentListFragment_ViewBinding implements Unbinder {
    private DLSComponentListFragment target;

    public DLSComponentListFragment_ViewBinding(DLSComponentListFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.itemMaxHeight = source.getContext().getResources().getDimensionPixelSize(R.dimen.n2_browser_preview_max_height);
    }

    public void unbind() {
        DLSComponentListFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
    }
}
