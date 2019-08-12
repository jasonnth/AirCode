package com.airbnb.android.booking.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.core.views.guestpicker.GuestsPickerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.BookingNavigationView;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;

public class BookingGuestsPickerFragment_ViewBinding implements Unbinder {
    private BookingGuestsPickerFragment target;
    private View view2131755509;

    public BookingGuestsPickerFragment_ViewBinding(final BookingGuestsPickerFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0704R.C0706id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.marquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0704R.C0706id.marquee, "field 'marquee'", DocumentMarquee.class);
        target2.guestsPickerView = (GuestsPickerView) Utils.findRequiredViewAsType(source, C0704R.C0706id.guests_picker, "field 'guestsPickerView'", GuestsPickerView.class);
        View view = Utils.findRequiredView(source, C0704R.C0706id.continue_button, "field 'continueButton' and method 'onContinueClicked'");
        target2.continueButton = (FixedActionFooter) Utils.castView(view, C0704R.C0706id.continue_button, "field 'continueButton'", FixedActionFooter.class);
        this.view2131755509 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onContinueClicked();
            }
        });
        target2.navView = (BookingNavigationView) Utils.findRequiredViewAsType(source, C0704R.C0706id.nav_view, "field 'navView'", BookingNavigationView.class);
    }

    public void unbind() {
        BookingGuestsPickerFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.marquee = null;
        target2.guestsPickerView = null;
        target2.continueButton = null;
        target2.navView = null;
        this.view2131755509.setOnClickListener(null);
        this.view2131755509 = null;
    }
}
