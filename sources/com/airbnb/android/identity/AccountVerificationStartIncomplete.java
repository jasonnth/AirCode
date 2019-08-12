package com.airbnb.android.identity;

import android.content.Context;
import android.support.p000v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.GovernmentIdResult;
import com.airbnb.android.core.models.User;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class AccountVerificationStartIncomplete extends ScrollView {
    private AccountVerificationStartListener accountVerificationStartListener;
    @BindView
    AirButton continueButton;
    @BindView
    SheetMarquee headerText;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;

    public AccountVerificationStartIncomplete(Context context) {
        this(context, null);
    }

    public AccountVerificationStartIncomplete(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AccountVerificationStartIncomplete(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private AccountVerificationStartIncomplete init(Context context) {
        ButterKnife.bind(this, inflate(context, C6533R.layout.account_verification_start_incomplete, this));
        setFillViewport(true);
        setVerticalScrollBarEnabled(false);
        return this;
    }

    public AccountVerificationStartIncomplete setData(User host, User currentUser, VerificationFlow flow, boolean selfieOnly, GovernmentIdResult governmentIdResult, AccountVerificationStartListener accountVerificationStartListener2) {
        this.accountVerificationStartListener = accountVerificationStartListener2;
        setMarquee(host, currentUser, flow, selfieOnly, governmentIdResult);
        setStyle(IdentityStyle.getStyle(flow));
        return this;
    }

    private void setStyle(IdentityStyle style) {
        setBackgroundColor(ContextCompat.getColor(getContext(), style.backgroundColorRes));
        style.marqueeStyle.setStyle(this.headerText);
        ViewUtils.setVisibleIf((View) this.jellyfishView, style.hasJellyFish);
        ViewUtils.setVisibleIf((View) this.continueButton, style.babuNextButtonVisible);
        ViewUtils.setVisibleIf((View) this.nextButton, style.whiteNextButtonVisible);
    }

    private void setMarquee(User host, User currentUser, VerificationFlow flow, boolean selfieOnly, GovernmentIdResult governmentIdResult) {
        if (selfieOnly) {
            String hostFirstName = host == null ? null : host.getFirstName();
            String guestFirstName = currentUser == null ? null : currentUser.getFirstName();
            this.headerText.setTitle(flow.getText().getStartTitleOneDotOne(getContext(), true, null));
            this.headerText.setSubtitle(flow.getText().getStartSubtitleOneDotOne(getContext(), guestFirstName, hostFirstName, true));
            return;
        }
        this.headerText.setTitle(C6533R.string.verifications_try_providing_id_again);
        this.headerText.setSubtitle(governmentIdResult.getLocalizedDenialReason());
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
