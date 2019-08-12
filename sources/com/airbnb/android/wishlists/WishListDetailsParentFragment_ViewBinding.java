package com.airbnb.android.wishlists;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;

public class WishListDetailsParentFragment_ViewBinding implements Unbinder {
    private WishListDetailsParentFragment target;

    public WishListDetailsParentFragment_ViewBinding(WishListDetailsParentFragment target2, View source) {
        this.target = target2;
        target2.contentContainer = (ViewGroup) Utils.findRequiredViewAsType(source, C1758R.C1760id.content_container, "field 'contentContainer'", ViewGroup.class);
    }

    public void unbind() {
        WishListDetailsParentFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.contentContainer = null;
    }
}
