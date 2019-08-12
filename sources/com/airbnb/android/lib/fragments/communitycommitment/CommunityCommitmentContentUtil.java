package com.airbnb.android.lib.fragments.communitycommitment;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.communitycommitment.CommunityCommitmentManager.TargetUserType;
import com.airbnb.android.lib.C0880R;

public class CommunityCommitmentContentUtil {
    public static int getCommitmentTitle(TargetUserType type) {
        switch (type) {
            case ExistingHost:
                return C0880R.string.community_commitment_title_existing_host;
            case ExistingGuest:
                return C0880R.string.community_commitment_title_existing_guest;
            case NewUser:
                return C0880R.string.community_commitment_title_new_user;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new UnhandledStateException(type));
                return 0;
        }
    }

    public static int getCommitmentCaption(TargetUserType type) {
        switch (type) {
            case ExistingHost:
                return C0880R.string.community_commitment_intro_existing_host;
            case ExistingGuest:
            case NewUser:
                return C0880R.string.community_commitment_intro_guest;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new UnhandledStateException(type));
                return 0;
        }
    }

    public static int getCancellationScreenTitle(TargetUserType type) {
        switch (type) {
            case ExistingHost:
            case ExistingGuest:
                return C0880R.string.community_commitment_cancellation_screen_title_existing_user;
            case NewUser:
                return C0880R.string.community_commitment_cancellation_screen_title_new_user;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new UnhandledStateException(type));
                return 0;
        }
    }

    public static int getCancellationScreenIntroductionText(TargetUserType type) {
        switch (type) {
            case ExistingHost:
            case ExistingGuest:
                return C0880R.string.community_commitment_cancellation_screen_intro_existing_user;
            case NewUser:
                return C0880R.string.community_commitment_cancellation_screen_intro_new_user;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new UnhandledStateException(type));
                return 0;
        }
    }

    public static int getCancelAccountButtonText(TargetUserType type) {
        switch (type) {
            case ExistingHost:
            case ExistingGuest:
                return C0880R.string.cancel_account;
            case NewUser:
                return C0880R.string.cancel_signup;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new UnhandledStateException(type));
                return 0;
        }
    }

    public static int getFeedbackIntroTitle(TargetUserType type) {
        switch (type) {
            case ExistingHost:
            case ExistingGuest:
                return C0880R.string.account_canceled_confirmation;
            case NewUser:
                return C0880R.string.signup_canceled_confirmation;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new UnhandledStateException(type));
                return 0;
        }
    }
}
