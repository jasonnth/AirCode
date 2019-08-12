package com.airbnb.android.lib.activities;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public final class TrebuchetOverrideActivity_ViewBinding implements Unbinder {
    private TrebuchetOverrideActivity target;

    public TrebuchetOverrideActivity_ViewBinding(TrebuchetOverrideActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public TrebuchetOverrideActivity_ViewBinding(TrebuchetOverrideActivity target2, View source) {
        this.target = target2;
        target2.list = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.list_trebuchet_items, "field 'list'", RecyclerView.class);
    }

    public void unbind() {
        TrebuchetOverrideActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.list = null;
    }
}
