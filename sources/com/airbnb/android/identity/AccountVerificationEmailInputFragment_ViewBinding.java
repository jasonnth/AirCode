package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButtonExpandable;

public class AccountVerificationEmailInputFragment_ViewBinding implements Unbinder {
    private AccountVerificationEmailInputFragment target;
    private View view2131755595;
    private View view2131755596;

    public AccountVerificationEmailInputFragment_ViewBinding(final AccountVerificationEmailInputFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C6533R.C6535id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.emailConfirmationSheetMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C6533R.C6535id.email_confirmation_sheet_marquee, "field 'emailConfirmationSheetMarquee'", SheetMarquee.class);
        target2.emailInputField = (SheetInputText) Utils.findRequiredViewAsType(source, C6533R.C6535id.account_verification_email_text_input_field, "field 'emailInputField'", SheetInputText.class);
        View view = Utils.findRequiredView(source, C6533R.C6535id.account_verification_email_next_btn, "field 'nextButton' and method 'onNext'");
        target2.nextButton = (AirButtonExpandable) Utils.castView(view, C6533R.C6535id.account_verification_email_next_btn, "field 'nextButton'", AirButtonExpandable.class);
        this.view2131755595 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNext();
            }
        });
        View view2 = Utils.findRequiredView(source, C6533R.C6535id.account_verification_email_booking_next_btn, "field 'bookingNextButton' and method 'onBookingNext'");
        target2.bookingNextButton = (AirButton) Utils.castView(view2, C6533R.C6535id.account_verification_email_booking_next_btn, "field 'bookingNextButton'", AirButton.class);
        this.view2131755596 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onBookingNext();
            }
        });
    }

    public void unbind() {
        AccountVerificationEmailInputFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.emailConfirmationSheetMarquee = null;
        target2.emailInputField = null;
        target2.nextButton = null;
        target2.bookingNextButton = null;
        this.view2131755595.setOnClickListener(null);
        this.view2131755595 = null;
        this.view2131755596.setOnClickListener(null);
        this.view2131755596 = null;
    }
}
