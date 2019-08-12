package com.airbnb.android.sharing.p033ui;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.sharing.C0921R;
import com.airbnb.p027n2.components.AirToolbar;

/* renamed from: com.airbnb.android.sharing.ui.ShareActivity_ViewBinding */
public class ShareActivity_ViewBinding implements Unbinder {
    private ShareActivity target;

    public ShareActivity_ViewBinding(ShareActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public ShareActivity_ViewBinding(ShareActivity target2, View source) {
        this.target = target2;
        target2.airToolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0921R.C0923id.toolbar, "field 'airToolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0921R.C0923id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    }

    public void unbind() {
        ShareActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.airToolbar = null;
        target2.recyclerView = null;
    }
}
