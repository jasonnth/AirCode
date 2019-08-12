package com.airbnb.android.identity;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.models.User;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.KickerMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.interfaces.LinkOnClickListener;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import java.util.ArrayList;

public class AccountVerificationStartOneDotOne extends ScrollView {
    private AccountVerificationStartListener accountVerificationStartListener;
    @BindView
    AirButton continueButton;
    @BindView
    KickerMarquee headerText;
    @BindView
    AirTextView helpLink;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirImageView logoImage;
    @BindView
    AirButton nextButton;
    @BindView
    HaloImageView profilePhoto;

    public AccountVerificationStartOneDotOne(Context context) {
        this(context, null);
    }

    public AccountVerificationStartOneDotOne(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AccountVerificationStartOneDotOne(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        ButterKnife.bind(this, inflate(context, C6533R.layout.account_verification_start_1_1, this));
        setFillViewport(true);
        setVerticalScrollBarEnabled(false);
    }

    public void setData(User host, User currentUser, VerificationFlow flow, ArrayList<AccountVerificationStep> requiredSteps, LinkOnClickListener linkOnClickListener, AccountVerificationStartListener accountVerificationStartListener2, String p4Steps) {
        this.accountVerificationStartListener = accountVerificationStartListener2;
        setMarquee(host, currentUser, flow, requiredSteps, p4Steps);
        IdentityStyle style = IdentityStyle.getStyle(flow);
        setStyle(style);
        setProfilePhoto(currentUser);
        ClickableLinkUtils.setupClickableTextView((TextView) this.helpLink, getContext().getString(style.learnMoreTextRes), linkOnClickListener, C6533R.color.canonical_press_darken, false);
    }

    private void setStyle(IdentityStyle style) {
        setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColorRes));
        this.profilePhoto.setPlaceholderResId(style.photoPlaceholderRes);
        this.profilePhoto.setBorder(ContextCompat.getColor(getContext(), style.photoBorderColorRes), getResources().getDimension(C6533R.dimen.verifications_halo_border_thickness));
        style.kickerMarqueeStyle.setStyle(this.headerText);
        this.logoImage.setColorFilter(ContextCompat.getColor(getContext(), style.imageTint));
        ViewUtils.setVisibleIf((View) this.jellyfishView, style.hasJellyFish);
        ViewUtils.setVisibleIf((View) this.continueButton, style.babuNextButtonVisible);
        ViewUtils.setVisibleIf((View) this.nextButton, style.whiteNextButtonVisible);
    }

    private void setProfilePhoto(User currentUser) {
        if (currentUser.hasProfilePic()) {
            this.profilePhoto.setImageUrl(currentUser.getPictureUrl());
            return;
        }
        this.profilePhoto.setImageResource(this.profilePhoto.getPlaceholderResId());
        this.profilePhoto.removeBorder();
    }

    private void setMarquee(User host, User currentUser, VerificationFlow flow, ArrayList<AccountVerificationStep> requiredSteps, String p4Steps) {
        boolean selfieOnly = requiredSteps.contains(AccountVerificationStep.Selfie) && !requiredSteps.contains(AccountVerificationStep.OfflineId);
        String hostFirstName = host == null ? null : host.getFirstName();
        this.headerText.setTitle(flow.getText().getStartTitleOneDotOne(getContext(), selfieOnly, hostFirstName));
        this.headerText.setSubtitle(flow.getText().getStartSubtitleOneDotOne(getContext(), currentUser.getFirstName(), hostFirstName, selfieOnly));
        this.headerText.setKicker((CharSequence) p4Steps);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onContinueClick() {
        this.accountVerificationStartListener.onContinueClick();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNextClick() {
        this.accountVerificationStartListener.onContinueClick();
    }
}
