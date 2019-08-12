package com.airbnb.android.profile_completion;

import android.text.TextUtils;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.UserRequest;
import com.airbnb.android.core.responses.UserResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.profile_completion.analytics.ProfileCompletionJitneyLogger;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import p032rx.Observer;

public class ProfileCompletionManager {
    private final AirbnbAccountManager accountManager;
    private final ArrayList<CompletionStep> completedSteps = new ArrayList<>(Arrays.asList(CompletionStep.values()));
    private final ArrayList<CompletionStep> incompleteSteps = new ArrayList<>();
    /* access modifiers changed from: private */
    public final CopyOnWriteArrayList<ProfileCompletionListener> listeners = new CopyOnWriteArrayList<>();
    /* access modifiers changed from: private */
    public final ProfileCompletionJitneyLogger profileCompletionJitneyLogger;
    /* access modifiers changed from: private */
    public User updatedUser;

    public interface ProfileCompletionListener {
        void onFetchStatusError(NetworkException networkException);

        void onFetchStatusSuccess(boolean z);
    }

    public ProfileCompletionManager(AirbnbAccountManager airbnbAccountManager, ProfileCompletionJitneyLogger profileCompletionJitneyLogger2) {
        this.accountManager = airbnbAccountManager;
        this.profileCompletionJitneyLogger = profileCompletionJitneyLogger2;
    }

    public boolean hasIncompleteSteps() {
        return !this.incompleteSteps.isEmpty();
    }

    public boolean hasIncompleteBadgingSteps() {
        return FluentIterable.from((Iterable<E>) this.incompleteSteps).anyMatch(ProfileCompletionManager$$Lambda$1.lambdaFactory$());
    }

    public boolean hasCompletedSteps() {
        return !this.completedSteps.isEmpty();
    }

    public List<CompletionStep> getCompletedSteps() {
        return this.completedSteps;
    }

    public List<CompletionStep> getIncompleteSteps() {
        return this.incompleteSteps;
    }

    public List<CompletionStep> getAllSteps() {
        return Arrays.asList(CompletionStep.values());
    }

    public void fetchStatus() {
        if (this.accountManager.isCurrentUserAuthorized()) {
            UserRequest.newRequestForProfileCompletion(this.accountManager.getCurrentUserId()).withListener((Observer) new NonResubscribableRequestListener<UserResponse>() {
                public void onResponse(UserResponse data) {
                    ProfileCompletionManager.this.updatedUser = data.user;
                    boolean completedStepsChanged = ProfileCompletionManager.this.updateCompletedSteps(data.user);
                    ProfileCompletionManager.this.profileCompletionJitneyLogger.logStatusCheck(ProfileCompletionManager.this);
                    Iterator it = ProfileCompletionManager.this.listeners.iterator();
                    while (it.hasNext()) {
                        ((ProfileCompletionListener) it.next()).onFetchStatusSuccess(completedStepsChanged);
                    }
                }

                public void onErrorResponse(AirRequestNetworkException e) {
                    Iterator it = ProfileCompletionManager.this.listeners.iterator();
                    while (it.hasNext()) {
                        ((ProfileCompletionListener) it.next()).onFetchStatusError(e);
                    }
                }
            }).execute(NetworkUtil.singleFireExecutor());
        }
    }

    public void addUpdateListener(ProfileCompletionListener listener) {
        if (!this.listeners.contains(listener)) {
            this.listeners.add(listener);
        }
    }

    public void removeUpdateListener(ProfileCompletionListener listener) {
        this.listeners.remove(listener);
    }

    /* access modifiers changed from: private */
    public boolean updateCompletedSteps(User user) {
        boolean z = true;
        updateCurrentUser(user);
        updateCompletedStep(CompletionStep.SignUp, true);
        boolean completedStepsChanged = updateCompletedStep(CompletionStep.Verificaton, user.hasCompletedAccountVerificationsForProfileCompletion()) | updateCompletedStep(CompletionStep.AddPaymentMethod, user.hasValidPayinGibraltarInstruments());
        CompletionStep completionStep = CompletionStep.CompleteAboutMe;
        if (TextUtils.isEmpty(user.getAbout())) {
            z = false;
        }
        return completedStepsChanged | updateCompletedStep(completionStep, z);
    }

    /* access modifiers changed from: protected */
    public void updateCurrentUser(User updatedUser2) {
        User currentUser = this.accountManager.getCurrentUser();
        if (currentUser != null) {
            currentUser.setHasCompletedAccountVerificationsForProfileCompletion(updatedUser2.hasCompletedAccountVerificationsForProfileCompletion());
            currentUser.setHasValidPayinGibraltarInstruments(updatedUser2.hasValidPayinGibraltarInstruments());
            currentUser.setAbout(updatedUser2.getAbout());
            currentUser.setReservationsAsGuestCount(updatedUser2.getReservationsAsGuestCount());
        }
    }

    private boolean updateCompletedStep(CompletionStep step, boolean isComplete) {
        if (!this.completedSteps.contains(step) && isComplete) {
            this.completedSteps.add(step);
            this.incompleteSteps.remove(step);
            return true;
        } else if (!this.completedSteps.contains(step) || isComplete) {
            return false;
        } else {
            this.completedSteps.remove(step);
            this.incompleteSteps.add(step);
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public User getMostRecentlyFetchedUser() {
        return this.updatedUser;
    }
}
