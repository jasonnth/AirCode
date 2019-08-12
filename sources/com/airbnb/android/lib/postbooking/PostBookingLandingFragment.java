package com.airbnb.android.lib.postbooking;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.viewcomponents.models.HeroMarqueeEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.HeroMarquee;

public class PostBookingLandingFragment extends PostBookingBaseFragment {
    @icepick.State
    State currentState;
    @BindView
    HeroMarquee heroMarquee;

    public enum State {
        IBSingle(C0880R.color.n2_rausch, C0880R.string.post_booking_landing_ib_single_title, C0880R.string.post_booking_landing_ib_single_caption, -1, C0880R.string.next),
        IBMultiple(C0880R.color.n2_rausch, C0880R.string.post_booking_landing_ib_multiple_title, C0880R.string.post_booking_landing_ib_multiple_caption, C0880R.string.share_itinerary_invite_guests, C0880R.string.action_skip),
        RTBSingle(C0880R.color.n2_babu, C0880R.string.post_booking_landing_rtb_title, C0880R.string.post_booking_landing_rtb_single_caption, -1, C0880R.string.next),
        RTBMultiple(C0880R.color.n2_babu, C0880R.string.post_booking_landing_rtb_title, C0880R.string.post_booking_landing_rtb_multiple_caption, C0880R.string.share_itinerary_invite_guests, C0880R.string.action_skip);
        
        public final int captionStringRes;
        public final int firstButtonTextStringRes;
        public final int secondButtonTextStringRes;
        public final int themeColorRes;
        public final int titleStringRes;

        private State(int themeColorRes2, int titleStringRes2, int captionStringRes2, int firstButtonTextStringRes2, int secondButtonTextStringRes2) {
            this.themeColorRes = themeColorRes2;
            this.titleStringRes = titleStringRes2;
            this.captionStringRes = captionStringRes2;
            this.firstButtonTextStringRes = firstButtonTextStringRes2;
            this.secondButtonTextStringRes = secondButtonTextStringRes2;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_post_booking_landing, container, false);
        bindViews(view);
        setUpViews();
        return view;
    }

    private void setUpViews() {
        Reservation ro = this.postBookingFlowController.getReservation();
        findState(ro);
        MParticleAnalytics.logEvent(MParticleAnalytics.REQUEST_BOOKING, Strap.make().mo11637kv("nights", ro.getReservedNightsCount()));
        HeroMarqueeEpoxyModel_ marqueeModel = new HeroMarqueeEpoxyModel_().iconDrawableRes(C0880R.C0881drawable.belo_white_00).themeColorRes(this.currentState.themeColorRes).title(String.format(getString(this.currentState.titleStringRes), new Object[]{ro.getListing().getLocalizedCity()})).caption(String.format(getString(this.currentState.captionStringRes), new Object[]{this.mAccountManager.getCurrentUser().getEmailAddress()})).secondButtonText(getString(this.currentState.secondButtonTextStringRes)).secondButtonClickListener(PostBookingLandingFragment$$Lambda$1.lambdaFactory$(this));
        this.heroMarquee.setSecondButtonState(com.airbnb.p027n2.primitives.AirButton.State.Normal);
        if (TextUtils.isEmpty(this.mAccountManager.getCurrentUser().getEmailAddress()) && this.currentState == State.IBSingle) {
            marqueeModel.caption(getString(C0880R.string.post_booking_landing_ib_caption_no_email));
        }
        if (this.currentState.firstButtonTextStringRes > 0) {
            marqueeModel.firstButtonText(getString(this.currentState.firstButtonTextStringRes)).firstButtonClickListener(PostBookingLandingFragment$$Lambda$2.lambdaFactory$(this));
        }
        marqueeModel.bind(this.heroMarquee);
    }

    static /* synthetic */ void lambda$setUpViews$0(PostBookingLandingFragment postBookingLandingFragment, View v) {
        if (!postBookingLandingFragment.postBookingFlowController.isNextStepReady()) {
            postBookingLandingFragment.heroMarquee.setSecondButtonState(com.airbnb.p027n2.primitives.AirButton.State.Loading);
        }
        postBookingLandingFragment.postBookingFlowController.onCurrentStateFinished();
    }

    private void findState(Reservation ro) {
        boolean singleGuest = true;
        if (ro.isCancelled()) {
            BugsnagWrapper.notify((Throwable) new RuntimeException("p5 is seeing a canceld reservation"));
        }
        boolean isAccepted = ro.isAccepted();
        if (ro.getGuestCount() != 1) {
            singleGuest = false;
        }
        if (isAccepted && singleGuest) {
            this.currentState = State.IBSingle;
        } else if (isAccepted && !singleGuest) {
            this.currentState = State.IBMultiple;
        } else if (isAccepted || !singleGuest) {
            this.currentState = State.RTBMultiple;
        } else {
            this.currentState = State.RTBSingle;
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.BookingLandingPage;
    }
}
