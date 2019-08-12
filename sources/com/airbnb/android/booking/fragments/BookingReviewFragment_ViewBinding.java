package com.airbnb.android.booking.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.core.views.UrgencyView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.BookingNavigationView;
import com.airbnb.p027n2.components.KickerMarquee;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.components.SwitchRow;
import com.airbnb.p027n2.components.UserDetailsActionRow;

public class BookingReviewFragment_ViewBinding implements Unbinder {
    private BookingReviewFragment target;
    private View view2131755487;
    private View view2131755511;

    public BookingReviewFragment_ViewBinding(final BookingReviewFragment target2, View source) {
        this.target = target2;
        target2.marquee = (KickerMarquee) Utils.findRequiredViewAsType(source, C0704R.C0706id.marquee, "field 'marquee'", KickerMarquee.class);
        target2.listingDetailsSummary = (UserDetailsActionRow) Utils.findRequiredViewAsType(source, C0704R.C0706id.listing_details_summary, "field 'listingDetailsSummary'", UserDetailsActionRow.class);
        View view = Utils.findRequiredView(source, C0704R.C0706id.dates_standard_row, "field 'datesRow' and method 'clickDateSelection'");
        target2.datesRow = (StandardRow) Utils.castView(view, C0704R.C0706id.dates_standard_row, "field 'datesRow'", StandardRow.class);
        this.view2131755511 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickDateSelection();
            }
        });
        View view2 = Utils.findRequiredView(source, C0704R.C0706id.guests_standard_row, "field 'guestRow' and method 'clickGuestDetails'");
        target2.guestRow = (StandardRow) Utils.castView(view2, C0704R.C0706id.guests_standard_row, "field 'guestRow'", StandardRow.class);
        this.view2131755487 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickGuestDetails();
            }
        });
        target2.businessTripToggleRow = (SwitchRow) Utils.findRequiredViewAsType(source, C0704R.C0706id.business_trip_toggle_row, "field 'businessTripToggleRow'", SwitchRow.class);
        target2.refundPolicyView = (StandardRow) Utils.findRequiredViewAsType(source, C0704R.C0706id.cancellation_row, "field 'refundPolicyView'", StandardRow.class);
        target2.urgencyView = (UrgencyView) Utils.findRequiredViewAsType(source, C0704R.C0706id.urgency_row, "field 'urgencyView'", UrgencyView.class);
        target2.navView = (BookingNavigationView) Utils.findRequiredViewAsType(source, C0704R.C0706id.nav_view, "field 'navView'", BookingNavigationView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0704R.C0706id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        BookingReviewFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marquee = null;
        target2.listingDetailsSummary = null;
        target2.datesRow = null;
        target2.guestRow = null;
        target2.businessTripToggleRow = null;
        target2.refundPolicyView = null;
        target2.urgencyView = null;
        target2.navView = null;
        target2.toolbar = null;
        this.view2131755511.setOnClickListener(null);
        this.view2131755511 = null;
        this.view2131755487.setOnClickListener(null);
        this.view2131755487 = null;
    }
}
