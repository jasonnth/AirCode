package com.airbnb.android.lib.fragments.communitycommitment;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.content.ContextCompat;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.analytics.CommunityCommitmentJitneyLogger;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager.TargetUserType;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.requests.GetActiveAccountRequest;
import com.airbnb.android.core.requests.UserCommunityCommitmentRequest;
import com.airbnb.android.core.responses.UserCommunityCommitmentResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.TextUtil;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.ClickableLinkUtils;
import p032rx.Observer;

public class CommunityCommitmentFragment extends AirFragment implements OnBackListener {
    private static final String ACCEPT_BUTTON = "accept_button";
    final RequestListener<UserCommunityCommitmentResponse> acceptCommunityCommitmentListener = new C0699RL().onResponse(CommunityCommitmentFragment$$Lambda$1.lambdaFactory$(this)).onError(CommunityCommitmentFragment$$Lambda$2.lambdaFactory$(this)).build();
    CommunityCommitmentJitneyLogger logger;
    @BindView
    DocumentMarquee marquee;
    private TargetUserType targetUserType;

    static /* synthetic */ void lambda$new$0(CommunityCommitmentFragment communityCommitmentFragment, UserCommunityCommitmentResponse response) {
        new GetActiveAccountRequest(communityCommitmentFragment.getContext()).execute(NetworkUtil.singleFireExecutor());
        communityCommitmentFragment.logger.clickItemOnIntroScreenEvent(ACCEPT_BUTTON);
        communityCommitmentFragment.getActivity().setResult(-1);
        communityCommitmentFragment.getActivity().finish();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_community_commitment, container, false);
        bindViews(view);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        this.targetUserType = (TargetUserType) Check.notNull(getArguments().getSerializable(CommunityCommitmentManager.KEY_TARGET_USER_TYPE));
        getAirActivity().setOnBackPressedListener(this);
        this.marquee.setTitle(CommunityCommitmentContentUtil.getCommitmentTitle(this.targetUserType));
        setUpCaption();
        return view;
    }

    @OnClick
    public void accept() {
        new UserCommunityCommitmentRequest(this.mAccountManager.getCurrentUserId(), true).withListener((Observer) this.acceptCommunityCommitmentListener).execute(this.requestManager);
    }

    @OnClick
    public void showCancellationContent() {
        startActivity(CommunityCommitmentCancelAccountFragment.createIntent(getContext(), this.targetUserType));
    }

    public boolean onBackPressed() {
        return true;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CommunityCommitmentIntroScreen;
    }

    private void setUpCaption() {
        int fullTextStr = CommunityCommitmentContentUtil.getCommitmentCaption(this.targetUserType);
        String learnMoreText = getString(C0880R.string.community_commitment_learn_more);
        SpannableString textWithLink = new SpannableString(TextUtil.fromHtmlSafe(getString(fullTextStr, learnMoreText)));
        Intent startLearnMoreScreenIntent = CommunityCommitmentLearnMoreFragment.createIntent(getContext(), this.targetUserType);
        AirTextView captionView = this.marquee.getCaptionTextView();
        ClickableLinkUtils.setupClickableTextView((TextView) captionView, textWithLink.toString(), learnMoreText, C0880R.color.canonical_press_darken, CommunityCommitmentFragment$$Lambda$3.lambdaFactory$(this, startLearnMoreScreenIntent));
        captionView.setLinkTextColor(ContextCompat.getColor(getContext(), C0880R.color.n2_babu));
        captionView.setVisibility(0);
    }
}
