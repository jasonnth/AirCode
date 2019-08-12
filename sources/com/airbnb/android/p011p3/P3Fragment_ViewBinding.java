package com.airbnb.android.p011p3;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;

/* renamed from: com.airbnb.android.p3.P3Fragment_ViewBinding */
public class P3Fragment_ViewBinding implements Unbinder {
    private P3Fragment target;
    private View view2131755473;

    public P3Fragment_ViewBinding(final P3Fragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C7532R.C7534id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
        target2.coordinatorLayout = (CoordinatorLayout) Utils.findRequiredViewAsType(source, C7532R.C7534id.coordinator_layout, "field 'coordinatorLayout'", CoordinatorLayout.class);
        target2.bookButton = (ExploreBookButton) Utils.findRequiredViewAsType(source, C7532R.C7534id.explore_book_it, "field 'bookButton'", ExploreBookButton.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7532R.C7534id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C7532R.C7534id.explore_book_button, "method 'checkPreconditionsAndGoToP4'");
        this.view2131755473 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.checkPreconditionsAndGoToP4();
            }
        });
    }

    public void unbind() {
        P3Fragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.coordinatorLayout = null;
        target2.bookButton = null;
        target2.toolbar = null;
        this.view2131755473.setOnClickListener(null);
        this.view2131755473 = null;
    }
}
