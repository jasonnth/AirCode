package com.airbnb.android.lib.fragments.communitycommitment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import com.airbnb.android.core.analytics.CommunityCommitmentJitneyLogger;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager.TargetUserType;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.utils.SnackbarWrapper;

public class CommunityCommitmentWriteFeedbackFragment extends AirFragment {
    @BindView
    AirEditTextView editText;
    CommunityCommitmentJitneyLogger logger;
    private TargetUserType targetUserType;
    @BindView
    AirToolbar toolbar;
    private long userId;

    public static CommunityCommitmentWriteFeedbackFragment newInstance(TargetUserType targetUserType2, long userId2) {
        return (CommunityCommitmentWriteFeedbackFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CommunityCommitmentWriteFeedbackFragment()).putSerializable(CommunityCommitmentManager.KEY_TARGET_USER_TYPE, targetUserType2)).putLong(CommunityCommitmentManager.KEY_TARGET_USER_ID, userId2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_community_commitment_write_feedback, null);
        bindViews(view);
        setHasOptionsMenu(true);
        setToolbar(this.toolbar);
        this.targetUserType = (TargetUserType) Check.notNull(getArguments().getSerializable(CommunityCommitmentManager.KEY_TARGET_USER_TYPE));
        this.userId = getArguments().getLong(CommunityCommitmentManager.KEY_TARGET_USER_ID);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0880R.C0883menu.send, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0880R.C0882id.menu_send) {
            return super.onOptionsItemSelected(item);
        }
        KeyboardUtils.hideSoftKeyboardForCurrentlyFocusedView(getActivity());
        String text = this.editText.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            new SnackbarWrapper().view(getView()).title(C0880R.string.enter_feedback_text_empty_error, true).duration(0).buildAndShow();
            return true;
        }
        this.logger.submitFeedbackEvent(text, this.userId);
        Toast.makeText(getContext(), this.targetUserType == TargetUserType.NewUser ? C0880R.string.community_commitment_thanks_for_entering_feedback_new_user : C0880R.string.community_commitment_thanks_for_entering_feedback_existing_user, 1).show();
        getActivity().setResult(-1);
        getActivity().finish();
        return true;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CommunityCommitmentFeedbackSubmissionScreen;
    }
}
