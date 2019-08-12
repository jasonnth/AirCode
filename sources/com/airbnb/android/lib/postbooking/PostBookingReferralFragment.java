package com.airbnb.android.lib.postbooking;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.intents.ReferralsIntents;
import com.airbnb.android.core.requests.ReferralStatusRequest;
import com.airbnb.android.core.responses.ReferralStatusResponse;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.HeroMarquee;
import icepick.State;
import p032rx.Observer;

public class PostBookingReferralFragment extends PostBookingBaseFragment {
    @BindView
    HeroMarquee heroMarquee;
    @State
    String referralBonusGuest;
    final RequestListener<ReferralStatusResponse> requestListener = new C0699RL().onResponse(PostBookingReferralFragment$$Lambda$1.lambdaFactory$(this)).onError(PostBookingReferralFragment$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(PostBookingReferralFragment postBookingReferralFragment, ReferralStatusResponse response) {
        postBookingReferralFragment.referralBonusGuest = response.getReferralBonusGuest(postBookingReferralFragment.mCurrencyHelper);
        if (!TextUtils.isEmpty(postBookingReferralFragment.referralBonusGuest)) {
            postBookingReferralFragment.heroMarquee.setCaption((CharSequence) postBookingReferralFragment.getString(C0880R.string.post_booking_referral_caption, postBookingReferralFragment.referralBonusGuest));
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.referral_entry_landing_page, container, false);
        bindViews(view);
        setupView();
        this.heroMarquee.setFirstButtonClickListener(PostBookingReferralFragment$$Lambda$3.lambdaFactory$(this));
        this.heroMarquee.setSecondButtonClickListener(PostBookingReferralFragment$$Lambda$4.lambdaFactory$(this));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$2(PostBookingReferralFragment postBookingReferralFragment, View v) {
        postBookingReferralFragment.postBookingFlowController.onCurrentStateFinished();
        postBookingReferralFragment.getActivity().startActivity(ReferralsIntents.newIntent(postBookingReferralFragment.getActivity(), ReferralsIntents.ENTRY_POINT_POST_BOOKING));
    }

    private void setupView() {
        if (TextUtils.isEmpty(this.referralBonusGuest)) {
            ReferralStatusRequest.newInstance(this.mAccountManager.getCurrentUser().getId()).withListener((Observer) this.requestListener).execute(this.requestManager);
            return;
        }
        this.heroMarquee.setCaption((CharSequence) getString(C0880R.string.post_booking_referral_caption, this.referralBonusGuest));
    }
}
