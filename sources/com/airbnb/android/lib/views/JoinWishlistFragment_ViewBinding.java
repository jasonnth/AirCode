package com.airbnb.android.lib.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.C0880R;

public class JoinWishlistFragment_ViewBinding implements Unbinder {
    private JoinWishlistFragment target;

    public JoinWishlistFragment_ViewBinding(JoinWishlistFragment target2, View source) {
        this.target = target2;
        target2.loaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C0880R.C0882id.loading_overlay, "field 'loaderFrame'", LoaderFrame.class);
    }

    public void unbind() {
        JoinWishlistFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.loaderFrame = null;
    }
}
