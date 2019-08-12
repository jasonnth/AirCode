package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.collections.ProfilePhotoSheet;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class AccountVerificationSelfieConfirmFragment_ViewBinding implements Unbinder {
    private AccountVerificationSelfieConfirmFragment target;
    private View view2131755612;
    private View view2131755613;
    private View view2131755614;

    public AccountVerificationSelfieConfirmFragment_ViewBinding(final AccountVerificationSelfieConfirmFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C6533R.C6535id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.profilePhotoSheet = (ProfilePhotoSheet) Utils.findRequiredViewAsType(source, C6533R.C6535id.verification_profile_photo_sheet, "field 'profilePhotoSheet'", ProfilePhotoSheet.class);
        View view = Utils.findRequiredView(source, C6533R.C6535id.account_verification_confirm_and_finish_button, "field 'confirmAndFinishButton' and method 'onConfirmAndFinishClick'");
        target2.confirmAndFinishButton = (AirButton) Utils.castView(view, C6533R.C6535id.account_verification_confirm_and_finish_button, "field 'confirmAndFinishButton'", AirButton.class);
        this.view2131755612 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onConfirmAndFinishClick();
            }
        });
        View view2 = Utils.findRequiredView(source, C6533R.C6535id.next_button, "field 'nextButton' and method 'onNextClick'");
        target2.nextButton = (AirButton) Utils.castView(view2, C6533R.C6535id.next_button, "field 'nextButton'", AirButton.class);
        this.view2131755613 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNextClick();
            }
        });
        View view3 = Utils.findRequiredView(source, C6533R.C6535id.booking_next_button, "field 'bookingNextButton' and method 'onBookingNextClick'");
        target2.bookingNextButton = (AirButton) Utils.castView(view3, C6533R.C6535id.booking_next_button, "field 'bookingNextButton'", AirButton.class);
        this.view2131755614 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onBookingNextClick();
            }
        });
    }

    public void unbind() {
        AccountVerificationSelfieConfirmFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.profilePhotoSheet = null;
        target2.confirmAndFinishButton = null;
        target2.nextButton = null;
        target2.bookingNextButton = null;
        this.view2131755612.setOnClickListener(null);
        this.view2131755612 = null;
        this.view2131755613.setOnClickListener(null);
        this.view2131755613 = null;
        this.view2131755614.setOnClickListener(null);
        this.view2131755614 = null;
    }
}
