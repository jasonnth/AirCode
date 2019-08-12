package com.airbnb.android.nestedlistings;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.LoadingView;

public class NestedListingsActivity_ViewBinding implements Unbinder {
    private NestedListingsActivity target;

    public NestedListingsActivity_ViewBinding(NestedListingsActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public NestedListingsActivity_ViewBinding(NestedListingsActivity target2, View source) {
        this.target = target2;
        target2.fullLoader = (LoadingView) Utils.findRequiredViewAsType(source, C7496R.C7498id.loading_view, "field 'fullLoader'", LoadingView.class);
    }

    public void unbind() {
        NestedListingsActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.fullLoader = null;
    }
}
