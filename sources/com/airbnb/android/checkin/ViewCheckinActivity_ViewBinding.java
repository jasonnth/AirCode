package com.airbnb.android.checkin;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.RefreshLoader;

public class ViewCheckinActivity_ViewBinding implements Unbinder {
    private ViewCheckinActivity target;

    public ViewCheckinActivity_ViewBinding(ViewCheckinActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public ViewCheckinActivity_ViewBinding(ViewCheckinActivity target2, View source) {
        this.target = target2;
        target2.loader = (RefreshLoader) Utils.findRequiredViewAsType(source, C5618R.C5620id.loading_row, "field 'loader'", RefreshLoader.class);
    }

    public void unbind() {
        ViewCheckinActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.loader = null;
    }
}
