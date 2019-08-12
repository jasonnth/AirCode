package com.airbnb.android.lib.fragments.unlist;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.analytics.LegacyUnlistAnalytics;
import com.airbnb.android.lib.enums.LegacyUnlistReason;
import com.airbnb.p027n2.utils.ClickableLinkUtils;

public class UnlistTrustQuestionsFragment extends BaseSnoozeListingFragment {
    @BindView
    TextView hostGuaranteeTextView;
    @BindView
    TextView reservationRequirementTextView;

    private enum UnlistForTrustQuestionLink {
        ReservationRequirements(0, HelpCenterArticle.SET_RESERVATION_REQUIREMENTS),
        VerifiedIdProcess(1, 450);
        
        /* access modifiers changed from: private */
        public final int article;
        private final int linkIndex;

        private UnlistForTrustQuestionLink(int linkIndex2, int article2) {
            this.linkIndex = linkIndex2;
            this.article = article2;
        }

        public String getUrl(Context context) {
            return HelpCenterIntents.getHelpCenterArticleUrl(context, this.article);
        }

        public static UnlistForTrustQuestionLink fromLinkIndex(int linkIndex2) {
            UnlistForTrustQuestionLink[] values;
            for (UnlistForTrustQuestionLink link : values()) {
                if (link.linkIndex == linkIndex2) {
                    return link;
                }
            }
            throw new IllegalStateException("Invalid link index: " + linkIndex2);
        }
    }

    public static UnlistTrustQuestionsFragment newInstance() {
        return new UnlistTrustQuestionsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_unlist_trust_questions, container, false);
        bindViews(view);
        initializeHostGuaranteeLink();
        initializeReservationRequirementLinks();
        return view;
    }

    private void initializeHostGuaranteeLink() {
        ClickableLinkUtils.setupClickableTextView(this.hostGuaranteeTextView, getString(C0880R.string.ml_unlisting_reason_trust_questions_subtitle_host_guarantee), getString(C0880R.string.f1209xa431720b), C0880R.color.canonical_press_darken, UnlistTrustQuestionsFragment$$Lambda$1.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$initializeHostGuaranteeLink$0(UnlistTrustQuestionsFragment unlistTrustQuestionsFragment, int linkIndex) {
        LegacyUnlistAnalytics.trackClickLinkInUnlistDialog(unlistTrustQuestionsFragment.listing, unlistTrustQuestionsFragment.getString(C0880R.string.url_host_guarantee));
        WebViewIntentBuilder.startMobileWebActivity(unlistTrustQuestionsFragment.getActivity(), unlistTrustQuestionsFragment.getString(C0880R.string.url_host_guarantee));
    }

    private void initializeReservationRequirementLinks() {
        ClickableLinkUtils.setupClickableTextView(this.reservationRequirementTextView, getString(C0880R.string.f1210x9a8035a7), C0880R.color.canonical_press_darken, UnlistTrustQuestionsFragment$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$initializeReservationRequirementLinks$1(UnlistTrustQuestionsFragment unlistTrustQuestionsFragment, int linkIndex) {
        UnlistForTrustQuestionLink link = UnlistForTrustQuestionLink.fromLinkIndex(linkIndex);
        LegacyUnlistAnalytics.trackClickLinkInUnlistDialog(unlistTrustQuestionsFragment.listing, link.getUrl(unlistTrustQuestionsFragment.getContext()));
        unlistTrustQuestionsFragment.startActivity(HelpCenterIntents.intentForHelpCenterArticle(unlistTrustQuestionsFragment.getContext(), link.article).toIntent());
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void keepListingListed() {
        getActivity().finish();
    }

    /* access modifiers changed from: protected */
    public LegacyUnlistReason getUnlistReason() {
        return LegacyUnlistReason.TrustQuestions;
    }
}
