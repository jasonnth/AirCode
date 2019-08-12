package com.airbnb.android.booking.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.p336n2.ArrivalTimeSelectionView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.BookingNavigationView;

public class BookingArrivalDetailsFragment_ViewBinding implements Unbinder {
    private BookingArrivalDetailsFragment target;

    public BookingArrivalDetailsFragment_ViewBinding(BookingArrivalDetailsFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0704R.C0706id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.selectionView = (ArrivalTimeSelectionView) Utils.findRequiredViewAsType(source, C0704R.C0706id.selection_view, "field 'selectionView'", ArrivalTimeSelectionView.class);
        target2.navView = (BookingNavigationView) Utils.findRequiredViewAsType(source, C0704R.C0706id.nav_view, "field 'navView'", BookingNavigationView.class);
    }

    public void unbind() {
        BookingArrivalDetailsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.selectionView = null;
        target2.navView = null;
    }
}
