package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.PhoneNumberInputSheet;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class AccountVerificationPhoneNumberInputFragment_ViewBinding implements Unbinder {
    private AccountVerificationPhoneNumberInputFragment target;
    private View view2131755608;
    private View view2131755609;

    public AccountVerificationPhoneNumberInputFragment_ViewBinding(final AccountVerificationPhoneNumberInputFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C6533R.C6535id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.phoneConfirmationSheetMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C6533R.C6535id.phone_confirmation_sheet_marquee, "field 'phoneConfirmationSheetMarquee'", SheetMarquee.class);
        target2.phoneNumberInputSheet = (PhoneNumberInputSheet) Utils.findRequiredViewAsType(source, C6533R.C6535id.phone_number_input_view, "field 'phoneNumberInputSheet'", PhoneNumberInputSheet.class);
        View view = Utils.findRequiredView(source, C6533R.C6535id.account_verification_phone_number_next_btn, "field 'nextButton' and method 'onNext'");
        target2.nextButton = (AirButton) Utils.castView(view, C6533R.C6535id.account_verification_phone_number_next_btn, "field 'nextButton'", AirButton.class);
        this.view2131755608 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNext();
            }
        });
        View view2 = Utils.findRequiredView(source, C6533R.C6535id.account_verification_phone_number_booking_next_btn, "field 'bookingNextButton' and method 'onBookingNext'");
        target2.bookingNextButton = (AirButton) Utils.castView(view2, C6533R.C6535id.account_verification_phone_number_booking_next_btn, "field 'bookingNextButton'", AirButton.class);
        this.view2131755609 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onBookingNext();
            }
        });
    }

    public void unbind() {
        AccountVerificationPhoneNumberInputFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.phoneConfirmationSheetMarquee = null;
        target2.phoneNumberInputSheet = null;
        target2.nextButton = null;
        target2.bookingNextButton = null;
        this.view2131755608.setOnClickListener(null);
        this.view2131755608 = null;
        this.view2131755609.setOnClickListener(null);
        this.view2131755609 = null;
    }
}
