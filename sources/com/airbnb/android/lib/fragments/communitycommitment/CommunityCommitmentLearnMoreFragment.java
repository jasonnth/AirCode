package com.airbnb.android.lib.fragments.communitycommitment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.OnClick;
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
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.ScrollViewWithCustomListener;
import com.airbnb.android.lib.views.ScrollViewWithCustomListener.ScrollViewListener;
import com.airbnb.android.utils.EmailUtils;
import com.airbnb.jitney.event.logging.CommunityBackButtonType.p070v1.C1960CommunityBackButtonType;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import icepick.State;

public class CommunityCommitmentLearnMoreFragment extends AirFragment implements OnBackListener, ScrollViewListener {
    private static final String LEARN_MORE_LINK = "learn_more_link";
    @BindDimen
    int bottomPadding;
    @BindView
    AirTextView declineExplanationBodyRow;
    @BindView
    AirTextView declineExplanationTitleRow;
    @BindView
    AirTextView disabilityConcernBodyRow;
    @BindView
    AirTextView disabilityConcernTitleRow;
    @State
    boolean hasScrolledToBottom;
    @BindView
    AirTextView lawConcernBodyRow;
    @BindView
    AirTextView lawConcernTitleRow;
    CommunityCommitmentJitneyLogger logger;
    @BindView
    TextView moreHelpInfoBodyRow;
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

    public static Intent createIntent(Context context, TargetUserType targetUserType2) {
        return ((Builder) AutoFragmentActivity.create(context, CommunityCommitmentLearnMoreFragment.class).putSerializable(CommunityCommitmentManager.KEY_TARGET_USER_TYPE, targetUserType2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_community_commitment_learn_more, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(CommunityCommitmentLearnMoreFragment$$Lambda$1.lambdaFactory$(this));
        getAirActivity().setOnBackPressedListener(this);
        this.targetUserType = (TargetUserType) Check.notNull(getArguments().getSerializable(CommunityCommitmentManager.KEY_TARGET_USER_TYPE));
        this.titleMarquee.setTitle(C0880R.string.community_commitment_about_title);
        setupShareFeedbackBodyRow();
        setupGetMoreHelpInfoRow();
        switch (this.targetUserType) {
            case ExistingGuest:
                this.safetyConcernTitleRow.setVisibility(8);
                this.safetyConcernBodyRow.setVisibility(8);
                this.lawConcernTitleRow.setVisibility(8);
                this.lawConcernBodyRow.setVisibility(8);
                this.disabilityConcernTitleRow.setVisibility(8);
                this.disabilityConcernBodyRow.setVisibility(8);
                break;
            case ExistingHost:
                this.safetyConcernTitleRow.setText(C0880R.string.f1179x36854257);
                break;
            case NewUser:
                this.safetyConcernTitleRow.setText(C0880R.string.f1180x722093b2);
                break;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new UnhandledStateException(this.targetUserType));
                break;
        }
        this.scrollView.setScrollViewListener(this);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        this.logger.clickItemOnIntroScreenEvent(LEARN_MORE_LINK);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(CommunityCommitmentLearnMoreFragment communityCommitmentLearnMoreFragment, View v) {
        communityCommitmentLearnMoreFragment.getActivity().finish();
        communityCommitmentLearnMoreFragment.logger.goBackFromLearnMoreScreenEvent(C1960CommunityBackButtonType.TopArrow);
    }

    public void onScrollChanged(ScrollViewWithCustomListener scrollView2, int x, int y, int oldx, int oldy) {
        int diff = scrollView2.getChildAt(scrollView2.getChildCount() - 1).getBottom() - (scrollView2.getHeight() + scrollView2.getScrollY());
        if (!this.hasScrolledToBottom && diff <= this.bottomPadding) {
            this.logger.scrollToViewButtonOnLearnMoreScreenEvent();
            this.hasScrolledToBottom = true;
        }
    }

    @OnClick
    public void goBackToPreviousScreen() {
        this.logger.goBackFromLearnMoreScreenEvent(C1960CommunityBackButtonType.GoBackBottomButton);
        getActivity().finish();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CommunityCommitmentLearnMoreScreen;
    }

    public boolean onBackPressed() {
        this.logger.goBackFromLearnMoreScreenEvent(C1960CommunityBackButtonType.AndroidDeviceBackButton);
        return false;
    }

    private void setupShareFeedbackBodyRow() {
        String email = getString(C0880R.string.community_commitment_feedback_email);
        ClickableLinkUtils.setupClickableTextView(this.shareFeedbackBodyRow, getString(C0880R.string.community_commitment_cancellation_screen_share_feedback_body, email), email, C0880R.color.n2_transparent, CommunityCommitmentLearnMoreFragment$$Lambda$2.lambdaFactory$(this, email));
    }

    static /* synthetic */ void lambda$setupShareFeedbackBodyRow$1(CommunityCommitmentLearnMoreFragment communityCommitmentLearnMoreFragment, String email, int linkIndex) {
        communityCommitmentLearnMoreFragment.logger.clickItemOnLearnMoreScreenEvent(CommunityCommitmentJitneyLogger.EMAIL_LINK);
        EmailUtils.send(communityCommitmentLearnMoreFragment.getContext(), email, null, null);
    }

    private void setupGetMoreHelpInfoRow() {
        String hostResourcesLinkText = getString(C0880R.string.host_resources_text);
        ClickableLinkUtils.setupClickableTextView(this.moreHelpInfoBodyRow, getString(C0880R.string.community_commitment_cancellation_screen_get_more_help_info_body, hostResourcesLinkText), hostResourcesLinkText, C0880R.color.canonical_press_darken, CommunityCommitmentLearnMoreFragment$$Lambda$3.lambdaFactory$(this, HelpCenterIntents.intentForHelpCenterArticle(getContext(), HelpCenterArticle.HOST_RESOURCES_NON_DISCRIMINATION).title(hostResourcesLinkText).toIntent()));
    }

    static /* synthetic */ void lambda$setupGetMoreHelpInfoRow$2(CommunityCommitmentLearnMoreFragment communityCommitmentLearnMoreFragment, Intent helpIntent, int v) {
        communityCommitmentLearnMoreFragment.startActivity(helpIntent);
        communityCommitmentLearnMoreFragment.logger.clickItemOnLearnMoreScreenEvent(CommunityCommitmentJitneyLogger.HOST_RESOURCE_HELP_CENTER_LINK);
    }
}
