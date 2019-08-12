package com.airbnb.android.booking.fragments;

import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.BookingNavigationView;
import com.airbnb.p027n2.components.KickerMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.primitives.messaging.MessageItemReceiver;

public class BookingEditTextFragment_ViewBinding implements Unbinder {
    private BookingEditTextFragment target;
    private View view2131755508;

    public BookingEditTextFragment_ViewBinding(final BookingEditTextFragment target2, View source) {
        this.target = target2;
        target2.scrollView = (VerboseScrollView) Utils.findRequiredViewAsType(source, C0704R.C0706id.scroll_view, "field 'scrollView'", VerboseScrollView.class);
        target2.marquee = (KickerMarquee) Utils.findRequiredViewAsType(source, C0704R.C0706id.marquee, "field 'marquee'", KickerMarquee.class);
        target2.quoteContainer = (FrameLayout) Utils.findRequiredViewAsType(source, C0704R.C0706id.quoteContainer, "field 'quoteContainer'", FrameLayout.class);
        target2.messageItem = (MessageItemReceiver) Utils.findRequiredViewAsType(source, C0704R.C0706id.messageItem, "field 'messageItem'", MessageItemReceiver.class);
        target2.hostImage = (HaloImageView) Utils.findRequiredViewAsType(source, C0704R.C0706id.hostImage, "field 'hostImage'", HaloImageView.class);
        target2.editTextView = (AirEditTextView) Utils.findRequiredViewAsType(source, C0704R.C0706id.edit_text, "field 'editTextView'", AirEditTextView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0704R.C0706id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.navView = (BookingNavigationView) Utils.findRequiredViewAsType(source, C0704R.C0706id.nav_view, "field 'navView'", BookingNavigationView.class);
        View view = Utils.findRequiredView(source, C0704R.C0706id.bookingNavButton, "field 'nextButton' and method 'onClickNext'");
        target2.nextButton = (AirButton) Utils.castView(view, C0704R.C0706id.bookingNavButton, "field 'nextButton'", AirButton.class);
        this.view2131755508 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickNext();
            }
        });
    }

    public void unbind() {
        BookingEditTextFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.scrollView = null;
        target2.marquee = null;
        target2.quoteContainer = null;
        target2.messageItem = null;
        target2.hostImage = null;
        target2.editTextView = null;
        target2.toolbar = null;
        target2.navView = null;
        target2.nextButton = null;
        this.view2131755508.setOnClickListener(null);
        this.view2131755508 = null;
    }
}
