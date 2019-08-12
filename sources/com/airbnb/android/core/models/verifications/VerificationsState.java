package com.airbnb.android.core.models.verifications;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.Verification;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VerificationsState implements Parcelable {
    public static final Creator<VerificationsState> CREATOR = new Creator<VerificationsState>() {
        public VerificationsState createFromParcel(Parcel source) {
            return new VerificationsState(source);
        }

        public VerificationsState[] newArray(int size) {
            return new VerificationsState[size];
        }
    };
    private int displayedVerificationIndex;
    private final List<Verification> verifications;

    private VerificationsState(List<Verification> currentVerifications) {
        this.verifications = currentVerifications;
    }

    public static VerificationsState initializeFromIncompleteAccountVerifications(List<AccountVerification> incompleteAccountVerifications) {
        return initialize(new ArrayList(FluentIterable.from((Iterable<E>) incompleteAccountVerifications).filter(VerificationsState$$Lambda$1.lambdaFactory$()).transform(VerificationsState$$Lambda$2.lambdaFactory$()).toList()));
    }

    static /* synthetic */ boolean lambda$initializeFromIncompleteAccountVerifications$0(AccountVerification accountVerification) {
        return Verification.getTypeFromAccountVerification(accountVerification) != null;
    }

    static /* synthetic */ Verification lambda$initializeFromIncompleteAccountVerifications$1(AccountVerification input) {
        return new Verification(Verification.getTypeFromAccountVerification(input), 0);
    }

    public static VerificationsState initialize(List<Verification> verifications2) {
        VerificationsState verificationsState = new VerificationsState(verifications2);
        if (verificationsState.hasIncompleteVerifications() && !verificationsState.getCurrentVerification().isIncomplete()) {
            verificationsState.moveToNextVerification();
        }
        verificationsState.moveCompletedVerificationsToFront();
        verificationsState.setCurrentVerificationIndex(verificationsState.numberOfCompletedVerifications());
        return verificationsState;
    }

    public boolean updateVerifications(List<Verification> updatedVerifications) {
        boolean didCompleteVerification = updateCurrentVerificationStatuses(updatedVerifications);
        moveCompletedVerificationsToFront();
        if (didCompleteVerification) {
            setCurrentVerificationIndex(numberOfCompletedVerifications() - 1);
        }
        return didCompleteVerification;
    }

    private boolean updateCurrentVerificationStatuses(List<Verification> updatedVerifications) {
        boolean didCompleteVerification = false;
        for (Verification verification : this.verifications) {
            for (Verification updatedVerification : updatedVerifications) {
                if (updatedVerification.equals(verification) && updatedVerification.isCompleted() && !verification.isCompleted()) {
                    didCompleteVerification = true;
                    verification.markCompleted();
                }
            }
        }
        return didCompleteVerification;
    }

    private void moveCompletedVerificationsToFront() {
        Collections.sort(this.verifications, VerificationsState$$Lambda$3.lambdaFactory$());
    }

    static /* synthetic */ int lambda$moveCompletedVerificationsToFront$2(Verification lhs, Verification rhs) {
        if (lhs.getType() == rhs.getType() || (!lhs.isCompleted() && !rhs.isCompleted())) {
            return 0;
        }
        return lhs.isCompleted() ? -1 : 1;
    }

    public boolean isLastVerification() {
        return this.displayedVerificationIndex == this.verifications.size() + -1;
    }

    public boolean hasIncompleteVerifications() {
        for (Verification verification : this.verifications) {
            if (verification.isIncomplete()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSkippedVerifications() {
        for (int i = 0; i < this.displayedVerificationIndex; i++) {
            if (((Verification) this.verifications.get(i)).isIncomplete()) {
                return true;
            }
        }
        return false;
    }

    public Verification getCurrentVerification() {
        assertCorrectVerificationState();
        return (Verification) this.verifications.get(this.displayedVerificationIndex);
    }

    public int getCurrentVerificationIndex() {
        return this.displayedVerificationIndex;
    }

    public void setCurrentVerificationIndex(int index) {
        this.displayedVerificationIndex = index;
    }

    public void moveToNextVerification() {
        assertCorrectVerificationState();
        if (!hasIncompleteVerifications()) {
            throw new IllegalStateException("There are no remaining verifications to display");
        }
        Verification nextPendingVerification = getNextPendingVerification();
        if (nextPendingVerification == null) {
            throw new IllegalStateException("Did not mark a previous verification as skipped or completed");
        }
        this.displayedVerificationIndex = this.verifications.indexOf(nextPendingVerification);
    }

    public void moveToPreviousVerification() {
        assertCorrectVerificationState();
        if (this.displayedVerificationIndex == 0) {
            throw new IllegalStateException("Attempt to go to non-existent previous verification");
        }
        Verification previousPendingVerification = getPreviousPendingVerification();
        if (previousPendingVerification == null) {
            throw new IllegalStateException("There are no incomplete or skipped verifications to go back to");
        }
        this.displayedVerificationIndex = this.verifications.indexOf(previousPendingVerification);
    }

    public void markCurrentVerificationAsComplete() {
        assertCorrectVerificationState();
        ((Verification) this.verifications.get(this.displayedVerificationIndex)).markCompleted();
    }

    public int numberOfCompletedVerifications() {
        int count = 0;
        for (Verification verification : this.verifications) {
            if (verification.isCompleted()) {
                count++;
            }
        }
        return count;
    }

    public int size() {
        return this.verifications.size();
    }

    private Verification getNextPendingVerification() {
        for (int i = this.displayedVerificationIndex + 1; i < this.verifications.size(); i++) {
            Verification nextVerification = (Verification) this.verifications.get(i);
            if (nextVerification.isIncomplete()) {
                return nextVerification;
            }
        }
        return null;
    }

    private Verification getPreviousPendingVerification() {
        for (int i = this.displayedVerificationIndex; i > 0; i--) {
            Verification previousVerification = (Verification) this.verifications.get(i - 1);
            if (previousVerification.isIncomplete()) {
                return previousVerification;
            }
        }
        return null;
    }

    private void assertCorrectVerificationState() {
        if (this.displayedVerificationIndex < 0 || this.displayedVerificationIndex >= this.verifications.size()) {
            throw new IndexOutOfBoundsException("Index of displayed verification out of bounds.");
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.verifications.size());
        for (Verification verification : this.verifications) {
            dest.writeParcelable(verification, flags);
        }
        dest.writeInt(this.displayedVerificationIndex);
    }

    private VerificationsState(Parcel in) {
        int size = in.readInt();
        this.verifications = new ArrayList();
        for (int i = 0; i < size; i++) {
            this.verifications.add((Verification) in.readParcelable(Verification.class.getClassLoader()));
        }
        this.displayedVerificationIndex = in.readInt();
    }
}
