package com.airbnb.android.lib.views;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.identity.IdentityVerificationUtil;
import com.airbnb.android.core.models.User;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import com.airbnb.p027n2.utils.TextUtil;
import java.util.ArrayList;
import java.util.Set;

public class UserVerificationView extends LinearLayout {
    @BindView
    AirTextView connectedAccountsText;
    @BindView
    AirTextView connectedAccountsTitle;
    @BindView
    AirTextView learnMoreLink;
    @BindView
    LinearLayout nameView;
    @BindView
    AirTextView reviewCountVerifiedTextView;
    @BindView
    AirTextView verificationFullNameTextView;
    @BindView
    LinearLayout verificationPanel;
    @BindView
    AirTextView verificationUserFromTextView;
    @BindView
    AirTextView verificationsText;
    @BindView
    AirTextView verificationsTitle;
    @BindView
    AirImageView verifiedIcon;

    public UserVerificationView(Context context) {
        super(context);
        init();
    }

    public UserVerificationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public UserVerificationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        ((LinearLayout) LayoutInflater.from(getContext()).inflate(C0880R.layout.guest_verifications_layout, this, true)).setOrientation(1);
        ButterKnife.bind((View) this);
    }

    public void setUser(User user, OnClickListener learnMoreClickListener) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        String string;
        boolean z5 = true;
        this.verificationFullNameTextView.setText(user.getName());
        this.nameView.setVisibility(0);
        int reviewCount = user.getRevieweeCount();
        if (user.isVerifiedId()) {
            AirTextView airTextView = this.reviewCountVerifiedTextView;
            if (reviewCount > 0) {
                string = getContext().getResources().getQuantityString(C0880R.plurals.reviews_verified, reviewCount, new Object[]{Integer.valueOf(reviewCount)});
            } else {
                string = getContext().getString(C0880R.string.user_profile_verified);
            }
            airTextView.setText(string);
            this.reviewCountVerifiedTextView.setVisibility(0);
        }
        if (!TextUtils.isEmpty(user.getLocation())) {
            this.verificationUserFromTextView.setText(user.getLocation());
            this.verificationUserFromTextView.setVisibility(0);
        }
        ViewUtils.setVisibleIf((View) this.verifiedIcon, user.isVerifiedId());
        Set<String> verifications = IdentityVerificationUtil.getUserVerifications(user);
        this.verificationsText.setText(TextUtil.join(new ArrayList(verifications)));
        AirTextView airTextView2 = this.verificationsTitle;
        if (!verifications.isEmpty()) {
            z = true;
        } else {
            z = false;
        }
        ViewUtils.setVisibleIf((View) airTextView2, z);
        AirTextView airTextView3 = this.verificationsText;
        if (!verifications.isEmpty()) {
            z2 = true;
        } else {
            z2 = false;
        }
        ViewUtils.setVisibleIf((View) airTextView3, z2);
        AirTextView airTextView4 = this.learnMoreLink;
        if (!verifications.isEmpty()) {
            z3 = true;
        } else {
            z3 = false;
        }
        ViewUtils.setVisibleIf((View) airTextView4, z3);
        if (learnMoreClickListener != null && !verifications.isEmpty()) {
            ClickableLinkUtils.setupClickableTextView((TextView) this.learnMoreLink, getContext().getString(C0880R.string.verified_id_learn_more_link), UserVerificationView$$Lambda$1.lambdaFactory$(this, learnMoreClickListener), C0880R.color.canonical_press_darken, false);
        }
        Set<String> connectedAccounts = IdentityVerificationUtil.getConnectedVerifications(user);
        this.connectedAccountsText.setText(TextUtil.join(new ArrayList(connectedAccounts)));
        AirTextView airTextView5 = this.connectedAccountsTitle;
        if (!connectedAccounts.isEmpty()) {
            z4 = true;
        } else {
            z4 = false;
        }
        ViewUtils.setVisibleIf((View) airTextView5, z4);
        AirTextView airTextView6 = this.connectedAccountsText;
        if (connectedAccounts.isEmpty()) {
            z5 = false;
        }
        ViewUtils.setVisibleIf((View) airTextView6, z5);
    }

    public void setIconClickHandler(OnClickListener listener) {
        this.verifiedIcon.setOnClickListener(listener);
    }

    public void hideVerifications() {
        this.verificationPanel.setVisibility(8);
    }
}
