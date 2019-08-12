package com.airbnb.android.pickwishlist;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.collections.Carousel;

public class PickWishListFragment_ViewBinding implements Unbinder {
    private PickWishListFragment target;
    private View view2131755524;

    public PickWishListFragment_ViewBinding(final PickWishListFragment target2, View source) {
        this.target = target2;
        target2.wishListRecyclerView = (Carousel) Utils.findRequiredViewAsType(source, C7614R.C7616id.pick_wish_list_recycler_view, "field 'wishListRecyclerView'", Carousel.class);
        View view = Utils.findRequiredView(source, C7614R.C7616id.btn_create_wishlist, "method 'launchCreateWishList'");
        this.view2131755524 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.launchCreateWishList();
            }
        });
    }

    public void unbind() {
        PickWishListFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.wishListRecyclerView = null;
        this.view2131755524.setOnClickListener(null);
        this.view2131755524 = null;
    }
}
