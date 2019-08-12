package com.airbnb.android.host_referrals.epoxycontrollers;

import android.content.Context;
import com.airbnb.android.core.models.HostReferralReferrerInfo;
import com.airbnb.android.core.viewcomponents.models.BasicRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.android.host_referrals.C6405R;
import com.airbnb.android.referrals.views.SingleButtonRow_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class HostReferralsEpoxyController extends AirEpoxyController {
    SingleButtonRow_ button;
    private final Context context;
    BasicRowEpoxyModel_ credit;
    SubsectionDividerEpoxyModel_ divider;
    private final Listener listener;
    private final HostReferralReferrerInfo referrerInfo;
    BasicRowEpoxyModel_ terms;
    DocumentMarqueeEpoxyModel_ title;

    public interface Listener {
        void onShareYourLinkClicked();

        void onTermsClicked();

        void onTravelCreditClicked();
    }

    public HostReferralsEpoxyController(Context context2, HostReferralReferrerInfo info, Listener listener2) {
        this.context = context2;
        this.referrerInfo = info;
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        this.title.withNoTopPaddingLayout().titleText((CharSequence) this.context.getString(C6405R.string.host_referral_landing_title, new Object[]{this.referrerInfo.getReferralReward().getReferralRewardReferrer().formattedForDisplay()})).captionText((CharSequence) this.context.getString(C6405R.string.host_referral_landing_subtitle, new Object[]{this.referrerInfo.getReferralReward().getReferralRewardReferrer().formattedForDisplay(), Integer.valueOf(this.referrerInfo.getReferralReward().getRewardCountLimit())})).addTo(this);
        this.button.text(this.context.getString(C6405R.string.host_referral_share_link)).showDivider(false).clickListener(HostReferralsEpoxyController$$Lambda$1.lambdaFactory$(this)).addTo(this);
        this.divider.addTo(this);
        this.credit.titleText(this.context.getString(C6405R.string.host_referral_earnings)).showDivider(true).clickListener(HostReferralsEpoxyController$$Lambda$2.lambdaFactory$(this)).addTo(this);
        this.terms.titleText(this.context.getString(C6405R.string.host_referral_terms_and_conditions)).showDivider(true).clickListener(HostReferralsEpoxyController$$Lambda$3.lambdaFactory$(this)).addTo(this);
    }
}
