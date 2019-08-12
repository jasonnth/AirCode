package com.airbnb.android.lib.reviews.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.airbnb.android.core.activities.SheetFlowActivity.SheetTheme;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.ReferralsIntents;
import com.airbnb.android.core.messaging.MessagingRequestFactory;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReviewRole;
import com.airbnb.android.core.viewcomponents.models.ListingDetailsSummaryEpoxyModel_;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.KonaReviewAnalytics;
import com.airbnb.android.lib.reviews.fragments.WriteFeedbackIntroFragment.FeedbackField;
import com.airbnb.android.lib.viewcomponents.viewmodels.ReservationDetailsSummaryEpoxyModel_;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.UserDetailsActionRow;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import java.util.Map;

public class FeedbackIntroFragment extends BaseWriteReviewFragment {
    private static final String ARG_RN_RESULT_PAYLOAD = "payload";
    private static final String KEY_OVERALL_RATING = "overallRating";
    protected static final int REQUEST_ACTIVITY_REACT_NATIVE_REVIEW = 42;
    @BindView
    TextView aboutText;
    @BindView
    DocumentMarquee documentMarquee;
    @BindView
    View getStartedButton;
    @BindView
    UserDetailsActionRow listingHostRow;
    @BindView
    AirImageView listingImage;
    @BindView
    TextView listingNameText;
    MessagingRequestFactory messagingRequestFactory;

    public static FeedbackIntroFragment newInstance() {
        return new FeedbackIntroFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_feedback_intro, null);
        bindViews(view);
        ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        Reservation reservation = getReview().getReservation();
        String timeRemaining = getReview().getExpirationTime().getTimeRemaining(getActivity());
        switch (getReview().getReviewRole()) {
            case Guest:
                this.documentMarquee.setTitle((CharSequence) getString(C0880R.string.wr_intro_title_guest, reservation.getListing().getCity()));
                this.aboutText.setText(C0880R.string.write_review_guideline_guest);
                this.listingImage.setImageUrl(reservation.getListing().getPictureUrl());
                this.listingNameText.setText(reservation.getListing().getName());
                new ListingDetailsSummaryEpoxyModel_().reservation(reservation).bind(this.listingHostRow);
                break;
            case Host:
                this.documentMarquee.setTitle((CharSequence) getString(C0880R.string.wr_intro_title_host, reservation.getGuest().getName()));
                this.aboutText.setText(C0880R.string.write_review_guideline_host);
                this.listingImage.setVisibility(8);
                this.listingNameText.setVisibility(8);
                new ReservationDetailsSummaryEpoxyModel_().reservation(reservation).bind(this.listingHostRow);
                break;
            default:
                throw new IllegalArgumentException("Cannot handle role: " + getReview().getReviewRole());
        }
        this.documentMarquee.setCaption((CharSequence) getString(C0880R.string.time_left_to_complete, timeRemaining));
        this.getStartedButton.setOnClickListener(FeedbackIntroFragment$$Lambda$1.lambdaFactory$(this));
        KonaReviewAnalytics.trackStartImpression(getReview());
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(FeedbackIntroFragment feedbackIntroFragment, View v1) {
        if (feedbackIntroFragment.getReview().getReviewRole() == ReviewRole.Guest) {
            feedbackIntroFragment.startActivityForResult(ReactNativeIntents.intentForReviews(feedbackIntroFragment.getContext(), feedbackIntroFragment.getReview().getId(), feedbackIntroFragment.getReview().getRecipient() == null ? null : feedbackIntroFragment.getReview().getRecipient().getFirstName()), 42);
            return;
        }
        feedbackIntroFragment.wrInterface.showFragment(WriteFeedbackIntroFragment.newInstance(FeedbackField.PUBLIC));
        KonaReviewAnalytics.trackStartClick(feedbackIntroFragment.getReview());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 42 && resultCode == -1) {
            Map payload = (Map) data.getExtras().get(ARG_RN_RESULT_PAYLOAD);
            if (payload != null && payload.containsKey(KEY_OVERALL_RATING)) {
                ReferralsIntents.startIfNeededFromPostReview(getContext(), ((Double) payload.get(KEY_OVERALL_RATING)).intValue());
            }
            getActivity().setResult(-1);
            getActivity().finish();
            this.messagingRequestFactory.sync(InboxType.inboxFromIsHost(getReview().isHostReviewingGuest()));
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /* access modifiers changed from: 0000 */
    public SheetTheme getTheme() {
        return SheetTheme.WHITE;
    }
}
