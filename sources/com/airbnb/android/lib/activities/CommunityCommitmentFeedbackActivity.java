package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager.TargetUserType;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.intents.EntryActivityIntents;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.communitycommitment.CommunityCommitmentFeedbackIntroFragment;

public class CommunityCommitmentFeedbackActivity extends AirActivity {
    public static final int REQUEST_CODE_FEEDBACK_SENT = 1;

    public static Intent createIntent(Context context, TargetUserType targetUserType, long userId) {
        return new Intent(context, CommunityCommitmentFeedbackActivity.class).putExtra(CommunityCommitmentManager.KEY_TARGET_USER_TYPE, targetUserType).putExtra(CommunityCommitmentManager.KEY_TARGET_USER_ID, userId);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_community_commitment_feedback_blank);
        if (savedInstanceState == null) {
            showCommunityCommitmentFeedbackIntroFragment();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && requestCode == 1) {
            finish();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void finish() {
        startActivity(EntryActivityIntents.newIntent(this).addFlags(335544320));
        super.finish();
        this.airbnbApi.logout();
    }

    /* access modifiers changed from: protected */
    public boolean denyRequireAccountFromChild() {
        return true;
    }

    private void showCommunityCommitmentFeedbackIntroFragment() {
        Intent intent = getIntent();
        showFragment(CommunityCommitmentFeedbackIntroFragment.newInstance((TargetUserType) intent.getSerializableExtra(CommunityCommitmentManager.KEY_TARGET_USER_TYPE), intent.getLongExtra(CommunityCommitmentManager.KEY_TARGET_USER_ID, -1)), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true, null);
    }
}
