package com.airbnb.android.referrals.adapters;

import android.content.Context;
import com.airbnb.android.core.intents.ReferralsIntents;
import com.airbnb.android.core.models.ReferralStatusForMobile;
import com.airbnb.android.core.viewcomponents.models.BasicRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.android.referrals.C1532R;
import com.airbnb.android.referrals.views.SingleButtonRow_;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class ReferralModulesController extends AirEpoxyController {
    SingleButtonRow_ button;
    private final Context context;
    BasicRowEpoxyModel_ credit;
    SubsectionDividerEpoxyModel_ divider;
    private final String entryPoint;
    private ReferralsFragmentInterface listener;
    private final ReferralStatusForMobile referralStatus;
    BasicRowEpoxyModel_ terms;
    DocumentMarqueeEpoxyModel_ title;

    public interface ReferralsFragmentInterface {
        void onShareYourLinkClicked();

        void onTermsClicked();

        void onTravelCreditClicked();
    }

    public ReferralModulesController(Context context2, ReferralStatusForMobile referralStatus2, String entryPoint2) {
        this.context = context2;
        this.referralStatus = referralStatus2;
        this.entryPoint = entryPoint2;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        String titleText;
        String caption;
        String buttonText;
        if (!ReferralsIntents.ENTRY_POINT_POST_REVIEW.equals(this.entryPoint) || !Experiments.shouldShowPostReviewTitleCopy()) {
            titleText = this.context.getString(C1532R.string.referrals_referral_offer, new Object[]{this.referralStatus.getOfferReceiverCreditLocalized(), this.referralStatus.getOfferSenderCreditLocalized()});
        } else {
            titleText = this.context.getString(C1532R.string.referrals_referral_offer_post_review, new Object[]{this.referralStatus.getOfferSenderCreditLocalized()});
        }
        if (!ReferralsIntents.ENTRY_POINT_POST_REVIEW.equals(this.entryPoint) || !Experiments.shouldShowPostReviewDetailsCopy()) {
            caption = this.context.getString(C1532R.string.referrals_referral_offer_details, new Object[]{this.referralStatus.getOfferReceiverCreditLocalized(), this.referralStatus.getOfferMinTripValueRequirementLocalized(), this.referralStatus.getOfferSenderCreditLocalized()});
        } else {
            caption = this.context.getString(C1532R.string.referrals_referral_offer_details_post_review, new Object[]{this.referralStatus.getOfferReceiverCreditLocalized(), this.referralStatus.getOfferMinTripValueRequirementLocalized(), this.referralStatus.getOfferSenderCreditLocalized()});
        }
        if (!ReferralsIntents.ENTRY_POINT_POST_REVIEW.equals(this.entryPoint) || !Experiments.shouldShowPostReviewButtonCopy()) {
            buttonText = this.context.getString(C1532R.string.referrals_share_your_link);
        } else {
            buttonText = this.context.getString(C1532R.string.referrals_share_your_link_post_review);
        }
        this.title.titleText((CharSequence) titleText).captionText((CharSequence) caption).addTo(this);
        this.button.text(buttonText).showDivider(false).clickListener(ReferralModulesController$$Lambda$1.lambdaFactory$(this)).addTo(this);
        this.divider.addTo(this);
        this.credit.titleRes(C1532R.string.referrals_travel_credit).showDivider(true).clickListener(ReferralModulesController$$Lambda$2.lambdaFactory$(this)).addTo(this);
        this.terms.titleRes(C1532R.string.referrals_read_terms).showDivider(true).clickListener(ReferralModulesController$$Lambda$3.lambdaFactory$(this)).addTo(this);
    }

    public void setListener(ReferralsFragmentInterface listener2) {
        this.listener = listener2;
    }
}
