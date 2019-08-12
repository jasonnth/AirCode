package com.airbnb.p027n2.browser;

import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.browser.DLSComponentFragment_ViewBinding */
public class DLSComponentFragment_ViewBinding implements Unbinder {
    private DLSComponentFragment target;

    public DLSComponentFragment_ViewBinding(DLSComponentFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (Toolbar) Utils.findRequiredViewAsType(source, R.id.toolbar, "field 'toolbar'", Toolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, R.id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    }

    public void unbind() {
        DLSComponentFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
    }
}
