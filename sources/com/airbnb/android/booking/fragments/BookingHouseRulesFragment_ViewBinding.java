package com.airbnb.android.booking.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.BookingNavigationView;

public class BookingHouseRulesFragment_ViewBinding implements Unbinder {
    private BookingHouseRulesFragment target;
    private View view2131755199;

    public BookingHouseRulesFragment_ViewBinding(final BookingHouseRulesFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0704R.C0706id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C0704R.C0706id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
        target2.navView = (BookingNavigationView) Utils.findRequiredViewAsType(source, C0704R.C0706id.nav_view, "field 'navView'", BookingNavigationView.class);
        View view = Utils.findRequiredView(source, C0704R.C0706id.button, "method 'confirmReadingHouseRules'");
        this.view2131755199 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.confirmReadingHouseRules();
            }
        });
    }

    public void unbind() {
        BookingHouseRulesFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.navView = null;
        this.view2131755199.setOnClickListener(null);
        this.view2131755199 = null;
    }
}
