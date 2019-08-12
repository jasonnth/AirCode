package com.airbnb.android.identity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.p002v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.ScrollView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.enums.VerificationFlowText;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.models.User;
import com.airbnb.p027n2.components.KickerMarquee;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.ArrayList;
import java.util.Iterator;

public class AccountVerificationStart extends ScrollView {
    private AccountVerificationStartListener accountVerificationStartListener;
    @BindView
    KickerMarquee headerText;
    @BindView
    AirButton nextButton;
    @BindView
    PrimaryButton primaryButton;

    public AccountVerificationStart(Context context) {
        this(context, null);
    }

    public AccountVerificationStart(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AccountVerificationStart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        ButterKnife.bind(this, inflate(context, C6533R.layout.account_verification_start, this));
        setFillViewport(true);
        setVerticalScrollBarEnabled(false);
    }

    private static int getRowIdForStep(AccountVerificationStep step) {
        switch (step) {
            case ProfilePhoto:
                return C6533R.C6535id.account_verification_photo_step;
            case Phone:
            case Email:
                return C6533R.C6535id.account_verification_contact_details_step;
            case OfflineId:
            case Selfie:
                return C6533R.C6535id.account_verification_scan_id_step;
            default:
                throw new IllegalArgumentException("Unexpected step: " + step.name());
        }
    }

    public void setData(User host, User currentUser, VerificationFlow flow, ArrayList<AccountVerificationStep> requiredSteps, AccountVerificationStartListener accountVerificationStartListener2, String p4Steps) {
        this.accountVerificationStartListener = accountVerificationStartListener2;
        setMarquee(host, currentUser, flow, requiredSteps, p4Steps);
        setCheckMarks(requiredSteps);
        if (flow.isWhiteBackground()) {
            this.nextButton.setVisibility(0);
        } else {
            this.primaryButton.setVisibility(0);
        }
    }

    private void setMarquee(User host, User currentUser, VerificationFlow flow, ArrayList<AccountVerificationStep> requiredSteps, String p4Steps) {
        String str = null;
        this.headerText.setTitle(flow.getText().getStartTitle(getContext(), host == null ? null : host.getFirstName()));
        KickerMarquee kickerMarquee = this.headerText;
        VerificationFlowText text = flow.getText();
        Context context = getContext();
        if (currentUser != null) {
            str = currentUser.getFirstName();
        }
        kickerMarquee.setSubtitle(text.getStartSubtitle(context, str, requiredSteps.contains(AccountVerificationStep.OfflineId) || requiredSteps.contains(AccountVerificationStep.Selfie)));
        this.headerText.setKicker((CharSequence) p4Steps);
    }

    private void setCheckMarks(ArrayList<AccountVerificationStep> requiredSteps) {
        Drawable inactiveCheckmark = AppCompatResources.getDrawable(getContext(), C6533R.C6534drawable.n2_ic_check_inactive);
        Iterator it = requiredSteps.iterator();
        while (it.hasNext()) {
            StandardRow row = (StandardRow) ViewLibUtils.findById(this, getRowIdForStep((AccountVerificationStep) it.next()));
            row.setVisibility(0);
            row.setRowDrawable(inactiveCheckmark);
        }
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
