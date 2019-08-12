package com.airbnb.android.lib.fragments.communitycommitment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import com.airbnb.android.core.activities.AutoFragmentActivity.Builder;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager.TargetUserType;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;

public class CommunityCommitmentFeedbackIntroFragment extends AirFragment implements OnBackListener {
    @BindView
    DocumentMarquee introMarquee;
    private TargetUserType targetUserType;
    @BindView
    AirToolbar toolbar;
    private long userId;

    public static CommunityCommitmentFeedbackIntroFragment newInstance(TargetUserType targetUserType2, long userId2) {
        return (CommunityCommitmentFeedbackIntroFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CommunityCommitmentFeedbackIntroFragment()).putSerializable(CommunityCommitmentManager.KEY_TARGET_USER_TYPE, targetUserType2)).putLong(CommunityCommitmentManager.KEY_TARGET_USER_ID, userId2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_community_commitment_feedback_intro, null);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(CommunityCommitmentFeedbackIntroFragment$$Lambda$1.lambdaFactory$(this));
        this.targetUserType = (TargetUserType) Check.notNull(getArguments().getSerializable(CommunityCommitmentManager.KEY_TARGET_USER_TYPE));
        this.userId = getArguments().getLong(CommunityCommitmentManager.KEY_TARGET_USER_ID);
        this.introMarquee.setTitle(CommunityCommitmentContentUtil.getFeedbackIntroTitle(this.targetUserType));
        getAirActivity().setOnBackPressedListener(this);
        return view;
    }

    @OnClick
    public void writeFeeback() {
        getActivity().startActivityForResult(((Builder) ((Builder) AutoFragmentActivity.create(getContext(), CommunityCommitmentWriteFeedbackFragment.class).allowAccessWithoutSession().putSerializable(CommunityCommitmentManager.KEY_TARGET_USER_TYPE, this.targetUserType)).putLong(CommunityCommitmentManager.KEY_TARGET_USER_ID, this.userId)).build(), 1);
    }

    public boolean onBackPressed() {
        Toast.makeText(getContext(), C0880R.string.account_canceled_confirmation, 1).show();
        getActivity().finish();
        return true;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CommunityCommitmentFeedbackIntroScreen;
    }
}
