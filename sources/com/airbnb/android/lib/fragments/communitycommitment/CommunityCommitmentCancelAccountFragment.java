package com.airbnb.android.lib.fragments.communitycommitment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import com.airbnb.android.core.activities.AutoFragmentActivity.Builder;
import com.airbnb.android.core.analytics.CommunityCommitmentJitneyLogger;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager.TargetUserType;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.requests.UserCommunityCommitmentRequest;
import com.airbnb.android.core.responses.UserCommunityCommitmentResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.CommunityCommitmentFeedbackActivity;
import com.airbnb.android.lib.views.ScrollViewWithCustomListener;
import com.airbnb.android.lib.views.ScrollViewWithCustomListener.ScrollViewListener;
import com.airbnb.android.utils.EmailUtils;
import com.airbnb.android.utils.TextUtil;
import com.airbnb.jitney.event.logging.CommunityBackButtonType.p070v1.C1960CommunityBackButtonType;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import icepick.State;
import p032rx.Observer;

public class CommunityCommitmentCancelAccountFragment extends AirFragment implements OnBackListener, ScrollViewListener {
    private static final String CANCEL_ACCOUNT_BUTTON = "cancel_account_button";
    private static final String DECLINE_BUTTON = "decline_button";
    @BindDimen
    int bottomPadding;
    @BindView
    AirButton cancelAccountButton;
    final RequestListener<UserCommunityCommitmentResponse> declineCommunityCommitmentListener = new C0699RL().onResponse(CommunityCommitmentCancelAccountFragment$$Lambda$1.lambdaFactory$(this)).onError(CommunityCommitmentCancelAccountFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirTextView disabilityConcernBodyRow;
    @BindView
    AirTextView disabilityConcernTitleRow;
    @State
    boolean hasLoggedToBottom;
    @BindView
    AirTextView introText;
    @BindView
    AirTextView lawConcernBodyRow;
    @BindView
    AirTextView lawConcernTitleRow;
    CommunityCommitmentJitneyLogger logger;
    @BindView
    TextView moreHelpInfoBodyRow;
    @BindView
    AirTextView reservationConcernBodyRow;
    @BindView
    AirTextView reservationConcernTitleRow;
    @BindView
    AirTextView safetyConcernBodyRow;
    @BindView
    AirTextView safetyConcernTitleRow;
    @BindView
    ScrollViewWithCustomListener scrollView;
    @BindView
    TextView shareFeedbackBodyRow;
    private TargetUserType targetUserType;
    @BindView
    DocumentMarquee titleMarquee;
    @BindView
    AirToolbar toolbar;
    private long userId;

    static /* synthetic */ void lambda$new$0(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment, UserCommunityCommitmentResponse response) {
        communityCommitmentCancelAccountFragment.logger.clickItemOnCancelScreenEvent(CANCEL_ACCOUNT_BUTTON);
        communityCommitmentCancelAccountFragment.startActivity(CommunityCommitmentFeedbackActivity.createIntent(communityCommitmentCancelAccountFragment.getContext(), communityCommitmentCancelAccountFragment.targetUserType, communityCommitmentCancelAccountFragment.userId));
        communityCommitmentCancelAccountFragment.getActivity().finishAffinity();
    }

    public static Intent createIntent(Context context, TargetUserType targetUserType2) {
        return ((Builder) AutoFragmentActivity.create(context, CommunityCommitmentCancelAccountFragment.class).putSerializable(CommunityCommitmentManager.KEY_TARGET_USER_TYPE, targetUserType2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_community_commitment_cancel_account, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(CommunityCommitmentCancelAccountFragment$$Lambda$3.lambdaFactory$(this));
        getAirActivity().setOnBackPressedListener(this);
        this.targetUserType = (TargetUserType) Check.notNull(getArguments().getSerializable(CommunityCommitmentManager.KEY_TARGET_USER_TYPE));
        this.titleMarquee.setTitle(CommunityCommitmentContentUtil.getCancellationScreenTitle(this.targetUserType));
        this.introText.setText(CommunityCommitmentContentUtil.getCancellationScreenIntroductionText(this.targetUserType));
        this.cancelAccountButton.setText(CommunityCommitmentContentUtil.getCancelAccountButtonText(this.targetUserType));
        setupShareFeedbackBodyRow();
        setupGetMoreHelpInfoRow();
        switch (this.targetUserType) {
            case ExistingGuest:
                this.reservationConcernTitleRow.setText(C0880R.string.f1178x831cc7d1);
                setupReservationConcernBodyRow(C0880R.string.f1176x81206722);
                this.safetyConcernTitleRow.setVisibility(8);
                this.safetyConcernBodyRow.setVisibility(8);
                this.lawConcernTitleRow.setVisibility(8);
                this.lawConcernBodyRow.setVisibility(8);
                this.disabilityConcernTitleRow.setVisibility(8);
                this.disabilityConcernBodyRow.setVisibility(8);
                break;
            case ExistingHost:
                this.reservationConcernTitleRow.setText(C0880R.string.f1178x831cc7d1);
                setupReservationConcernBodyRow(C0880R.string.f1177x5f016b1e);
                this.safetyConcernTitleRow.setText(C0880R.string.f1179x36854257);
                break;
            case NewUser:
                this.reservationConcernTitleRow.setVisibility(8);
                this.reservationConcernBodyRow.setVisibility(8);
                this.safetyConcernTitleRow.setText(C0880R.string.f1180x722093b2);
                break;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new UnhandledStateException(this.targetUserType));
                break;
        }
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        this.logger.clickItemOnIntroScreenEvent(DECLINE_BUTTON);
        this.scrollView.setScrollViewListener(this);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$2(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment, View v) {
        communityCommitmentCancelAccountFragment.getActivity().finish();
        communityCommitmentCancelAccountFragment.logger.goBackFromCancelScreenEvent(C1960CommunityBackButtonType.TopArrow);
    }

    @OnClick
    public void goBackToPreviousScreen() {
        this.logger.goBackFromCancelScreenEvent(C1960CommunityBackButtonType.GoBackBottomButton);
        getActivity().finish();
    }

    @OnClick
    public void cancelAccountAndAskForFeedback() {
        this.userId = this.mAccountManager.getCurrentUserId();
        new UserCommunityCommitmentRequest(this.mAccountManager.getCurrentUserId(), false).withListener((Observer) this.declineCommunityCommitmentListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CommunityCommitmentCancelScreen;
    }

    public boolean onBackPressed() {
        this.logger.goBackFromCancelScreenEvent(C1960CommunityBackButtonType.AndroidDeviceBackButton);
        return false;
    }

    public void onScrollChanged(ScrollViewWithCustomListener scrollView2, int x, int y, int oldx, int oldy) {
        int diff = scrollView2.getChildAt(scrollView2.getChildCount() - 1).getBottom() - (scrollView2.getHeight() + scrollView2.getScrollY());
        if (!this.hasLoggedToBottom && diff <= this.bottomPadding) {
            this.logger.scrollToViewButtonOnCancelScreenEvent();
            this.hasLoggedToBottom = true;
        }
    }

    private void setupShareFeedbackBodyRow() {
        String email = getString(C0880R.string.community_commitment_feedback_email);
        ClickableLinkUtils.setupClickableTextView(this.shareFeedbackBodyRow, getString(C0880R.string.community_commitment_cancellation_screen_share_feedback_body, email), email, C0880R.color.n2_transparent, CommunityCommitmentCancelAccountFragment$$Lambda$4.lambdaFactory$(this, email));
    }

    static /* synthetic */ void lambda$setupShareFeedbackBodyRow$3(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment, String email, int linkIndex) {
        communityCommitmentCancelAccountFragment.logger.clickItemOnCancelScreenEvent(CommunityCommitmentJitneyLogger.EMAIL_LINK);
        EmailUtils.send(communityCommitmentCancelAccountFragment.getContext(), email, null, null);
    }

    private void setupGetMoreHelpInfoRow() {
        String hostResourcesLinkText = getString(C0880R.string.host_resources_text);
        ClickableLinkUtils.setupClickableTextView(this.moreHelpInfoBodyRow, getString(C0880R.string.community_commitment_cancellation_screen_get_more_help_info_body, hostResourcesLinkText), hostResourcesLinkText, C0880R.color.canonical_press_darken, CommunityCommitmentCancelAccountFragment$$Lambda$5.lambdaFactory$(this, HelpCenterIntents.intentForHelpCenterArticle(getContext(), HelpCenterArticle.HOST_RESOURCES_NON_DISCRIMINATION).title(hostResourcesLinkText).toIntent()));
    }

    static /* synthetic */ void lambda$setupGetMoreHelpInfoRow$4(CommunityCommitmentCancelAccountFragment communityCommitmentCancelAccountFragment, Intent helpIntent, int v) {
        communityCommitmentCancelAccountFragment.startActivity(helpIntent);
        communityCommitmentCancelAccountFragment.logger.clickItemOnCancelScreenEvent(CommunityCommitmentJitneyLogger.HOST_RESOURCE_HELP_CENTER_LINK);
    }

    private void setupReservationConcernBodyRow(int textStr) {
        String learnMoreText = getString(C0880R.string.community_commitment_learn_more);
        ClickableLinkUtils.setupClickableTextView((TextView) this.reservationConcernBodyRow, new SpannableString(TextUtil.fromHtmlSafe(getString(textStr, learnMoreText))).toString(), learnMoreText, C0880R.color.canonical_press_darken, CommunityCommitmentCancelAccountFragment$$Lambda$6.lambdaFactory$(this, HelpCenterIntents.intentForHelpCenterArticle(getContext(), 149).title(learnMoreText).toIntent()));
    }
}
