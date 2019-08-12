package com.airbnb.android.lib.reviews.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.SheetFlowActivity.SheetTheme;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.messaging.MessagingRequestFactory;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.requests.UpdateReviewRequest;
import com.airbnb.android.core.responses.ReviewResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.viewcomponents.models.ListingDetailsSummaryEpoxyModel_;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.KonaReviewAnalytics;
import com.airbnb.android.lib.fragments.reviews.FeedbackExitFragment;
import com.airbnb.android.lib.reviews.fragments.WriteFeedbackIntroFragment.FeedbackField;
import com.airbnb.android.lib.viewcomponents.viewmodels.ReservationDetailsSummaryEpoxyModel_;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.components.EntryMarquee;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.components.UserDetailsActionRow;
import p032rx.Observer;

public class FeedbackSummaryFragment extends BaseWriteReviewFragment {
    private static final int DIALOG_REQ_SUBMIT = 1990;
    @BindView
    UserDetailsActionRow listingHostRow;
    @BindView
    EntryMarquee marquee;
    MessagingRequestFactory messagingRequestFactory;
    @BindView
    StandardRow overallRating;
    @BindView
    StandardRow privateJumboRow;
    @BindView
    StandardRow publicJumboRow;
    @BindView
    StandardRow recommendRating;
    @BindView
    PrimaryButton submitButton;
    final RequestListener<ReviewResponse> submitReviewListener = new C0699RL().onResponse(FeedbackSummaryFragment$$Lambda$1.lambdaFactory$(this)).onError(FeedbackSummaryFragment$$Lambda$2.lambdaFactory$(this)).build();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_feedback_summary, null);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        bindViews(view);
        Reservation reservation = getReview().getReservation();
        this.marquee.setTitle(C0880R.string.review_summary_title);
        this.marquee.setCaption((CharSequence) getContext().getResources().getQuantityString(C0880R.plurals.x_nights_in_city, reservation.getReservedNightsCount(), new Object[]{Integer.valueOf(reservation.getReservedNightsCount()), reservation.getListing().getCity()}));
        switch (getReview().getReviewRole()) {
            case Host:
                this.publicJumboRow.setTitle(C0880R.string.review_public_feedback_summary_title_reviewing_guest);
                this.privateJumboRow.setTitle(C0880R.string.review_private_feedback_title_reviewing_guest);
                new ReservationDetailsSummaryEpoxyModel_().reservation(reservation).bind(this.listingHostRow);
                break;
            case Guest:
                this.publicJumboRow.setTitle(C0880R.string.review_public_feedback_summary_title_reviewing_host);
                this.privateJumboRow.setTitle(C0880R.string.review_private_feedback_title_reviewing_host);
                new ListingDetailsSummaryEpoxyModel_().reservation(reservation).bind(this.listingHostRow);
                break;
            default:
                throw new IllegalArgumentException("Cannot handle role: " + getReview().getReviewRole());
        }
        this.publicJumboRow.setSubtitleText((CharSequence) getReview().getPublicFeedback());
        this.publicJumboRow.setOnClickListener(FeedbackSummaryFragment$$Lambda$3.lambdaFactory$(this));
        this.privateJumboRow.setSubtitleText((CharSequence) getReview().getPrivateFeedback());
        this.privateJumboRow.setOnClickListener(FeedbackSummaryFragment$$Lambda$4.lambdaFactory$(this));
        ViewUtils.setGoneIf(this.privateJumboRow, TextUtils.isEmpty(getReview().getPrivateFeedback()));
        this.overallRating.setActionText((CharSequence) getReview().getAverageRating().toString());
        this.recommendRating.setActionText(getReview().isRecommended().booleanValue() ? C0880R.string.yes : C0880R.string.f1211no);
        this.submitButton.setOnClickListener(FeedbackSummaryFragment$$Lambda$5.lambdaFactory$(this));
        KonaReviewAnalytics.trackSummaryImpression(getReview());
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(FeedbackSummaryFragment feedbackSummaryFragment, View v1) {
        feedbackSummaryFragment.wrInterface.showFragment(WriteFeedbackFragment.newInstanceForEdit(FeedbackField.PUBLIC));
        KonaReviewAnalytics.trackSummaryClick(feedbackSummaryFragment.getReview());
    }

    static /* synthetic */ void lambda$onCreateView$1(FeedbackSummaryFragment feedbackSummaryFragment, View v1) {
        feedbackSummaryFragment.wrInterface.showFragment(WriteFeedbackFragment.newInstanceForEdit(FeedbackField.PRIVATE));
        KonaReviewAnalytics.trackSummaryClick(feedbackSummaryFragment.getReview());
    }

    /* access modifiers changed from: private */
    public void promptSubmitReview() {
        ZenDialog.builder().withBodyText(C0880R.string.prompt_submit_review).withDualButton(C0880R.string.cancel, 0, C0880R.string.submit, DIALOG_REQ_SUBMIT).create().show(getFragmentManager(), (String) null);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DIALOG_REQ_SUBMIT) {
            submitReview();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    static /* synthetic */ void lambda$new$3(FeedbackSummaryFragment feedbackSummaryFragment, ReviewResponse response) {
        feedbackSummaryFragment.submitButton.setNormal();
        feedbackSummaryFragment.getActivity().setResult(-1);
        feedbackSummaryFragment.getActivity().finish();
        feedbackSummaryFragment.startActivity(FeedbackExitFragment.defaultIntent(feedbackSummaryFragment.getActivity(), feedbackSummaryFragment.getReview()));
        feedbackSummaryFragment.messagingRequestFactory.sync(InboxType.inboxFromIsHost(feedbackSummaryFragment.getReview().isHostReviewingGuest()));
    }

    static /* synthetic */ void lambda$new$4(FeedbackSummaryFragment feedbackSummaryFragment, AirRequestNetworkException e) {
        feedbackSummaryFragment.submitButton.setNormal();
        NetworkUtil.toastGenericNetworkError(feedbackSummaryFragment.getActivity());
    }

    private void submitReview() {
        this.submitButton.setLoading();
        UpdateReviewRequest.forSubmit(getReview()).withListener((Observer) this.submitReviewListener).execute(this.requestManager);
        KonaReviewAnalytics.trackSummarySubmit(getReview());
    }

    /* access modifiers changed from: 0000 */
    public SheetTheme getTheme() {
        return SheetTheme.WHITE;
    }
}
