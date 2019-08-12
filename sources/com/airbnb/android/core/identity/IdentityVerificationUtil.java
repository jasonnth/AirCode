package com.airbnb.android.core.identity;

import android.content.Context;
import android.content.Intent;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments;
import com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments.Builder;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.intents.AccountVerificationStartActivityIntents;
import com.airbnb.android.core.intents.VerifiedIdActivityIntents;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.lib.adapters.VerificationsAdapter;
import com.bugsnag.android.Severity;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class IdentityVerificationUtil {
    public static final String ARG_SKIPPED = "arg_skipped";
    private static final Set<String> basicInfoVerifications = ImmutableSet.builder().add((Object) "manual_online").add((Object) "manual_offline").add((Object) AccountVerification.SCANID).add((Object) "jumio").add((Object) AccountVerification.SELFIE).add((Object) "phone").add((Object) "email").add((Object) "kba").add((Object) "idology").add((Object) "rsa").add((Object) "sesame").add((Object) "sent_id").add((Object) "experian").add((Object) "gdc").add((Object) "work_email").build();
    private static final Set<String> connectedAccountsVerifications = ImmutableSet.builder().add((Object) "facebook").add((Object) "google").add((Object) VerificationsAdapter.VERIFICATION_LINKEDIN).add((Object) "microsoft").add((Object) RegistrationAnalytics.WEIBO).add((Object) "yahoo").add((Object) "xing").add((Object) "amex").build();

    public static void verify(Fragment fragment, VerificationFlow flow, User primaryHost, User verificationUser, long listingId, int requestCode, Boolean isIdentityOnP4, IdentityJitneyLogger logger, String... types) {
        IdentityJitneyLogger identityJitneyLogger = logger;
        fragment.startActivityForResult(AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(identityJitneyLogger, fragment.getContext(), generateAccountVerificationArgs(flow, primaryHost, verificationUser, listingId, types).isIdentityOnP4(isIdentityOnP4).build()), requestCode);
    }

    public static Intent verify(Context context, VerificationFlow flow, User primaryHost, User verificationUser, long listingId, String... types) {
        return AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(context, generateAccountVerificationArgs(flow, primaryHost, verificationUser, listingId, types).build());
    }

    private static Builder generateAccountVerificationArgs(VerificationFlow flow, User primaryHost, User verificationUser, long listingId, String... types) {
        List<AccountVerification> verificationsToDo = new ArrayList<>();
        for (String type : types) {
            AccountVerification verification = new AccountVerification();
            verification.setType(type);
            verificationsToDo.add(verification);
        }
        return AccountVerificationStartFragmentArguments.builder().verificationFlow(flow).incompleteVerifications(verificationsToDo).host(primaryHost).verificationUser(verificationUser).listingId(listingId).firstVerificationStep((types == null || types.length <= 0) ? null : types[0]);
    }

    public static Intent getIntentForVerifiedIdOrIdentity(IdentityJitneyLogger logger, Context context, User verificationUser, VerificationFlow flow) {
        if (FeatureToggles.replaceVerifiedIdWithIdentity(verificationUser)) {
            return AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(logger, context, AccountVerificationStartFragmentArguments.builder().verificationFlow(flow).build());
        }
        return VerifiedIdActivityIntents.intentForVerifiedId(context);
    }

    public static Set<String> getUserVerifications(User user) {
        return getFilteredVerifications(user, basicInfoVerifications);
    }

    public static Set<String> getConnectedVerifications(User user) {
        return getFilteredVerifications(user, connectedAccountsVerifications);
    }

    private static Set<String> getFilteredVerifications(User user, Set<String> filter) {
        Set<String> verifications = Sets.newHashSet();
        List<String> userVerifications = user.getVerifications();
        List<String> userVerificationLabels = user.getVerificationLabels();
        if (userVerifications != null && userVerificationLabels != null && userVerifications.size() == userVerificationLabels.size()) {
            for (int i = 0; i < userVerifications.size(); i++) {
                String label = (String) userVerificationLabels.get(i);
                if (label != null && filter.contains(userVerifications.get(i))) {
                    verifications.add(label);
                }
            }
        } else if (!(userVerifications == null && userVerificationLabels == null)) {
            BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Verification mismatch error:  User " + user.getId() + " has " + userVerifications + " verifications and " + userVerificationLabels + " labels."), Severity.WARNING);
        }
        return verifications;
    }

    public static boolean shouldUseIdentityFlowForFrozenReservation(Reservation reservation, User verificationUser) {
        return shouldReservationBeFrozen(reservation) && FeatureToggles.replaceVerifiedIdWithIdentity(verificationUser);
    }

    public static boolean shouldReservationBeFrozen(Reservation reservation) {
        return (reservation == null || reservation.getFreezeDetails() == null || !reservation.getFreezeDetails().isShouldBeFrozen()) ? false : true;
    }

    public static boolean isInstantBookableIfGovIdProvided(Reservation reservation, User verificationUser) {
        return Trebuchet.launch(TrebuchetKeys.IDENTITY_FOR_INSTANT_BOOK) && !shouldUseIdentityFlowForFrozenReservation(reservation, verificationUser) && reservation != null && reservation.isGovernmentIdRequiredForInstantBook();
    }

    public static boolean isFiveAxioms(ArrayList<AccountVerificationStep> steps) {
        if (steps == null || steps.isEmpty()) {
            return false;
        }
        Iterator it = steps.iterator();
        while (it.hasNext()) {
            AccountVerificationStep step = (AccountVerificationStep) it.next();
            if (step != AccountVerificationStep.ProfilePhoto && step != AccountVerificationStep.Phone && step != AccountVerificationStep.Email) {
                return false;
            }
        }
        return true;
    }
}
