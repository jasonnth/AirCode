package com.airbnb.android.pickwishlist;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class PickWishListActivity_ViewBinding implements Unbinder {
    private PickWishListActivity target;
    private View view2131755301;

    public PickWishListActivity_ViewBinding(PickWishListActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public PickWishListActivity_ViewBinding(final PickWishListActivity target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C7614R.C7616id.scrim, "method 'onScrimClicked'");
        this.view2131755301 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onScrimClicked();
            }
        });
    }

    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view2131755301.setOnClickListener(null);
        this.view2131755301 = null;
    }
}
