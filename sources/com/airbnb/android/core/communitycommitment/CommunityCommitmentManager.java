package com.airbnb.android.core.communitycommitment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Fragments;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import com.airbnb.android.core.activities.AutoFragmentActivity.Builder;

public class CommunityCommitmentManager {
    public static final String KEY_TARGET_USER_ID = "target_user_id";
    public static final String KEY_TARGET_USER_TYPE = "target_user_type";
    public static final int REQUEST_CODE_ACCEPT_COMMUNITY_COMMITMENT = 702;

    public enum TargetUserType {
        ExistingHost,
        ExistingGuest,
        NewUser
    }

    public static void launchCommunityCommitmentIfNeeded(boolean iscommunityCommitmentRequired, TargetUserType type, Activity activity) {
        if (iscommunityCommitmentRequired) {
            activity.startActivityForResult(createIntent(activity, type), REQUEST_CODE_ACCEPT_COMMUNITY_COMMITMENT);
        }
    }

    public static Intent createIntent(Context context, TargetUserType targetUserType) {
        return ((Builder) AutoFragmentActivity.create(context, Fragments.communityCommitment().getClass()).putSerializable(KEY_TARGET_USER_TYPE, targetUserType)).build();
    }
}
