package com.airbnb.android.lib.postbooking;

import android.os.Bundle;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.businesstravel.BusinessTravelJitneyLogger;
import com.airbnb.android.core.intents.BusinessTravelIntents;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.businesstravel.models.BTMobileSignupPromotion;
import com.airbnb.p027n2.components.HeroMarquee;
import icepick.State;

public class PostBookingBusinessTravelPromoFragment extends PostBookingBaseFragment {
    BusinessTravelJitneyLogger businessTravelJitneyLogger;
    @State
    String confirmationCode;
    @BindView
    HeroMarquee heroMarquee;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_post_booking_business_travel_promo, container, false);
        bindViews(view);
        setupHeroMarquee();
        this.confirmationCode = this.postBookingFlowController.getReservation().getConfirmationCode();
        this.businessTravelJitneyLogger.logMobileP5PromoImpression(this.confirmationCode);
        return view;
    }

    private void setupHeroMarquee() {
        BTMobileSignupPromotion btMobileSignupPromotion = this.postBookingFlowController.getBTMobileSignupPromotion();
        Check.notNull(btMobileSignupPromotion);
        SpannableString promoCreditString = SpannableUtils.makeBoldedSubString(btMobileSignupPromotion.body(), getContext(), btMobileSignupPromotion.boldText());
        this.heroMarquee.setTitle((CharSequence) btMobileSignupPromotion.title());
        this.heroMarquee.setCaption((CharSequence) promoCreditString);
        this.heroMarquee.setFirstButtonClickListener(PostBookingBusinessTravelPromoFragment$$Lambda$1.lambdaFactory$(this));
        this.heroMarquee.setSecondButtonClickListener(PostBookingBusinessTravelPromoFragment$$Lambda$2.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void goToAddEmail() {
        this.businessTravelJitneyLogger.logMobileP5PromoClickAddEmail(this.confirmationCode);
        startActivity(BusinessTravelIntents.intentForAddWorkEmailFromP5Promo(getContext(), this.confirmationCode));
    }

    /* access modifiers changed from: private */
    public void skipAddEmail() {
        this.businessTravelJitneyLogger.logMobileP5PromoClickSkip(this.confirmationCode);
        this.postBookingFlowController.onCurrentStateFinished();
    }
}
