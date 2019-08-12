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
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class AccountVerificationStartComplete extends ScrollView {
    private AccountVerificationStartListener accountVerificationStartListener;
    @BindView
    AirButton continueButton;
    @BindView
    SheetMarquee headerText;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;

    public AccountVerificationStartComplete(Context context) {
        this(context, null);
    }

    public AccountVerificationStartComplete(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AccountVerificationStartComplete(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        ButterKnife.bind(this, inflate(context, C6533R.layout.account_verification_start_complete, this));
        setFillViewport(true);
        setVerticalScrollBarEnabled(false);
    }

    public AccountVerificationStartComplete setData(VerificationFlow flow, boolean isPending, AccountVerificationStartListener accountVerificationStartListener2) {
        this.accountVerificationStartListener = accountVerificationStartListener2;
        setMarquee(isPending ? C6533R.string.verifications_processing_your_id : C6533R.string.verifications_complete_all_set);
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

    private void setMarquee(int stringRes) {
        this.headerText.setTitle(stringRes);
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
