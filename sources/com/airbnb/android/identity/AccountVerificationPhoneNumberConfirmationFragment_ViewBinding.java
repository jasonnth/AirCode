package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class AccountVerificationPhoneNumberConfirmationFragment_ViewBinding implements Unbinder {
    private AccountVerificationPhoneNumberConfirmationFragment target;
    private View view2131755608;
    private View view2131755609;

    public AccountVerificationPhoneNumberConfirmationFragment_ViewBinding(final AccountVerificationPhoneNumberConfirmationFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C6533R.C6535id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
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
        target2.sheetMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C6533R.C6535id.phone_confirmation_sheet_marquee, "field 'sheetMarquee'", SheetMarquee.class);
        target2.inputText = (SheetInputText) Utils.findRequiredViewAsType(source, C6533R.C6535id.phone_number_input_view, "field 'inputText'", SheetInputText.class);
    }

    public void unbind() {
        AccountVerificationPhoneNumberConfirmationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.nextButton = null;
        target2.bookingNextButton = null;
        target2.sheetMarquee = null;
        target2.inputText = null;
        this.view2131755608.setOnClickListener(null);
        this.view2131755608 = null;
        this.view2131755609.setOnClickListener(null);
        this.view2131755609 = null;
    }
}
