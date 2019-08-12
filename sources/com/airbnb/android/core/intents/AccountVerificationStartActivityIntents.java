package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.text.TextUtils;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.User;
import com.airbnb.android.utils.BundleBuilder;
import java.util.ArrayList;
import java.util.List;

public final class AccountVerificationStartActivityIntents {
    public static final String ARG_FIRST_VERIFICATION_STEP = "arg_first_verification_step";
    public static final String ARG_GOVERNMENT_ID_RESULT = "arg_government_id_result";
    public static final String ARG_HOST = "arg_host";
    public static final String ARG_IS_MOVE_TO_LAST_STEP = "arg_is_move_to_last_step";
    public static final String ARG_LISTING_ID = "arg_listing_id";
    public static final String ARG_P4_STEPS = "arg_p4_steps";
    public static final String ARG_REQUIRED_VERIFICATION_STEPS = "arg_required_verification_steps";
    public static final String ARG_VERIFICATION_FLOW = "arg_verification_flow";
    public static final String ARG_VERIFICATION_USER = "arg_verification_user";
    public static final String EXTRA_BUNDLE = "bundle";

    public static Intent newIntentForDebug(Context context, VerificationFlow flow, ArrayList<? extends Parcelable> steps) {
        User fakeUser = new User();
        fakeUser.setName("Airbnb");
        return newIntentForSteps(context, AccountVerificationStartFragmentArguments.builder().verificationFlow(flow).incompleteVerifications(new ArrayList()).host(fakeUser).listingId(1234567890).build(), steps);
    }

    public static Intent newIntentForContactHostVerifications(Context context, List<AccountVerification> incompleteVerifications) {
        return newIntentForIncompleteVerifications(context, AccountVerificationStartFragmentArguments.builder().verificationFlow(VerificationFlow.ContactHost).incompleteVerifications(incompleteVerifications).build());
    }

    private static Intent newIntentForVerificationStep(Context context, AccountVerificationStartFragmentArguments arguments, ArrayList<? extends Parcelable> requiredSteps, boolean isModal) {
        return AccountVerificationActivityIntents.newIntentForSteps(context, requiredSteps, arguments.verificationFlow(), arguments.host(), arguments.verificationUser(), arguments.phoneVerificationCode(), arguments.isMoveToLastStep(), isModal, arguments.selfiePhotoFilePaths());
    }

    public static Intent newIntentForIncompleteVerifications(IdentityJitneyLogger logger, Context context, AccountVerificationStartFragmentArguments arguments) {
        logStartIdentityFlow(logger, arguments);
        return newIntentForIncompleteVerifications(context, arguments);
    }

    public static Intent newIntentForIncompleteVerifications(Context context, AccountVerificationStartFragmentArguments arguments) {
        if (arguments.incompleteVerifications() == null) {
            return newIntentForSteps(context, arguments, null);
        }
        ArrayList<? extends Parcelable> requiredSteps = getIncompleteVerificationSteps(arguments);
        String firstStep = arguments.firstVerificationStep();
        if (arguments.isMoveToLastStep()) {
            return newIntentForVerificationStep(context, arguments, requiredSteps, false);
        }
        if (!TextUtils.isEmpty(firstStep)) {
            if (requiredSteps.isEmpty() || requiredSteps.get(0) != AccountVerificationStep.toAccountVerificationStep(firstStep)) {
                AccountVerificationAnalytics.trackMobileHandOffCompletedStep(firstStep);
            } else {
                AccountVerificationAnalytics.trackDeepLink(firstStep);
                return newIntentForVerificationStep(context, arguments, requiredSteps, true);
            }
        }
        switch (arguments.verificationFlow()) {
            case ContactHost:
                return AccountVerificationActivityIntents.newIntentForSteps(context, requiredSteps, VerificationFlow.ContactHost, arguments.host(), arguments.verificationUser(), arguments.phoneVerificationCode(), arguments.isMoveToLastStep(), false, arguments.selfiePhotoFilePaths());
            default:
                return newIntentForSteps(context, arguments, requiredSteps);
        }
    }

    private static void logStartIdentityFlow(IdentityJitneyLogger logger, AccountVerificationStartFragmentArguments arguments) {
        if (logger == null) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Null IdentityJitneyLogger for flow " + arguments.verificationFlow()));
        } else {
            logger.logIdentityStartFlow(arguments.verificationFlow(), arguments.verificationUser(), arguments.listingId() == -1 ? null : Long.valueOf(arguments.listingId()), arguments.reservationFrozenReason(), Boolean.valueOf(arguments.verificationFlow().requiresIdentityTreatment() || (arguments.isIdentityOnP4() != null && arguments.isIdentityOnP4().booleanValue())), arguments.isIdentityOnP4());
        }
    }

    private static ArrayList<AccountVerificationStep> getIncompleteVerificationSteps(AccountVerificationStartFragmentArguments arguments) {
        ArrayList<AccountVerificationStep> requiredSteps = new ArrayList<>();
        String firstStep = arguments.firstVerificationStep();
        for (AccountVerification verification : arguments.incompleteVerifications()) {
            AccountVerificationStep step = AccountVerificationStep.toAccountVerificationStep(verification.getType());
            if (step == null) {
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Cannot handle verification type: " + verification));
            } else {
                requiredSteps.add(verification.getType().equals(firstStep) ? 0 : requiredSteps.size(), step);
            }
        }
        return requiredSteps;
    }

    private static Intent newIntentForSteps(Context context, AccountVerificationStartFragmentArguments arguments, ArrayList<? extends Parcelable> steps) {
        return new Intent(context, Activities.accountVerificationStart()).putExtra(EXTRA_BUNDLE, ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putLong(ARG_LISTING_ID, arguments.listingId())).putParcelableArrayList(ARG_REQUIRED_VERIFICATION_STEPS, steps)).putParcelable(ARG_HOST, arguments.host())).putParcelable(ARG_VERIFICATION_USER, arguments.verificationUser())).putSerializable("arg_verification_flow", arguments.verificationFlow())).putString(ARG_FIRST_VERIFICATION_STEP, arguments.firstVerificationStep())).putParcelable(ARG_GOVERNMENT_ID_RESULT, arguments.governmentIdResult())).putBoolean(ARG_IS_MOVE_TO_LAST_STEP, arguments.isMoveToLastStep())).putString(ARG_P4_STEPS, arguments.p4Steps())).toBundle());
    }
}
