package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class UserVerificationView_ViewBinding implements Unbinder {
    private UserVerificationView target;

    public UserVerificationView_ViewBinding(UserVerificationView target2) {
        this(target2, target2);
    }

    public UserVerificationView_ViewBinding(UserVerificationView target2, View source) {
        this.target = target2;
        target2.verificationPanel = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.verification_panel, "field 'verificationPanel'", LinearLayout.class);
        target2.verificationsTitle = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.verifications_title, "field 'verificationsTitle'", AirTextView.class);
        target2.verificationsText = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.verifications_list, "field 'verificationsText'", AirTextView.class);
        target2.learnMoreLink = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.account_verification_learn_more_link, "field 'learnMoreLink'", AirTextView.class);
        target2.connectedAccountsTitle = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.connected_accounts_title, "field 'connectedAccountsTitle'", AirTextView.class);
        target2.connectedAccountsText = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.connected_accounts, "field 'connectedAccountsText'", AirTextView.class);
        target2.verificationFullNameTextView = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.user_full_name_text_view, "field 'verificationFullNameTextView'", AirTextView.class);
        target2.verificationUserFromTextView = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.user_from_text_view, "field 'verificationUserFromTextView'", AirTextView.class);
        target2.reviewCountVerifiedTextView = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.reviews_count_verified, "field 'reviewCountVerifiedTextView'", AirTextView.class);
        target2.verifiedIcon = (AirImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.verification_icon, "field 'verifiedIcon'", AirImageView.class);
        target2.nameView = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.name_view, "field 'nameView'", LinearLayout.class);
    }

    public void unbind() {
        UserVerificationView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.verificationPanel = null;
        target2.verificationsTitle = null;
        target2.verificationsText = null;
        target2.learnMoreLink = null;
        target2.connectedAccountsTitle = null;
        target2.connectedAccountsText = null;
        target2.verificationFullNameTextView = null;
        target2.verificationUserFromTextView = null;
        target2.reviewCountVerifiedTextView = null;
        target2.verifiedIcon = null;
        target2.nameView = null;
    }
}
