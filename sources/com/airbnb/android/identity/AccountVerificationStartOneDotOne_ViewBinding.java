package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.KickerMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class AccountVerificationStartOneDotOne_ViewBinding implements Unbinder {
    private AccountVerificationStartOneDotOne target;
    private View view2131755318;
    private View view2131755323;

    public AccountVerificationStartOneDotOne_ViewBinding(AccountVerificationStartOneDotOne target2) {
        this(target2, target2);
    }

    public AccountVerificationStartOneDotOne_ViewBinding(final AccountVerificationStartOneDotOne target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C6533R.C6535id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.headerText = (KickerMarquee) Utils.findRequiredViewAsType(source, C6533R.C6535id.account_verifications_start_header, "field 'headerText'", KickerMarquee.class);
        target2.profilePhoto = (HaloImageView) Utils.findRequiredViewAsType(source, C6533R.C6535id.account_verification_preview_profile_photo, "field 'profilePhoto'", HaloImageView.class);
        target2.logoImage = (AirImageView) Utils.findRequiredViewAsType(source, C6533R.C6535id.logo_image, "field 'logoImage'", AirImageView.class);
        target2.helpLink = (AirTextView) Utils.findRequiredViewAsType(source, C6533R.C6535id.account_verification_learn_more_link, "field 'helpLink'", AirTextView.class);
        View view = Utils.findRequiredView(source, C6533R.C6535id.account_verifications_start_btn, "field 'continueButton' and method 'onContinueClick'");
        target2.continueButton = (AirButton) Utils.castView(view, C6533R.C6535id.account_verifications_start_btn, "field 'continueButton'", AirButton.class);
        this.view2131755318 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onContinueClick();
            }
        });
        View view2 = Utils.findRequiredView(source, C6533R.C6535id.account_verifications_next_btn, "field 'nextButton' and method 'onNextClick'");
        target2.nextButton = (AirButton) Utils.castView(view2, C6533R.C6535id.account_verifications_next_btn, "field 'nextButton'", AirButton.class);
        this.view2131755323 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNextClick();
            }
        });
    }

    public void unbind() {
        AccountVerificationStartOneDotOne target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.headerText = null;
        target2.profilePhoto = null;
        target2.logoImage = null;
        target2.helpLink = null;
        target2.continueButton = null;
        target2.nextButton = null;
        this.view2131755318.setOnClickListener(null);
        this.view2131755318 = null;
        this.view2131755323.setOnClickListener(null);
        this.view2131755323 = null;
    }
}
