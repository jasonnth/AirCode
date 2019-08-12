package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenVerificationRequirements;
import java.util.List;

public class VerificationRequirements extends GenVerificationRequirements {
    public static final Creator<VerificationRequirements> CREATOR = new Creator<VerificationRequirements>() {
        public VerificationRequirements[] newArray(int size) {
            return new VerificationRequirements[size];
        }

        public VerificationRequirements createFromParcel(Parcel source) {
            VerificationRequirements object = new VerificationRequirements();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final int NUMBER_VERIFIED_ID_STEPS = 5;

    public boolean emailVerificationComplete() {
        for (Verification v : this.mVerificationGroups.getBasic().getItems()) {
            if ("confirmed_email".equals(v.getId()) && "complete".equals(v.getStatus())) {
                return true;
            }
        }
        return false;
    }

    public boolean profilePhotoComplete() {
        for (Verification v : this.mVerificationGroups.getBasic().getItems()) {
            if ("profile_pic".equals(v.getId()) && "complete".equals(v.getStatus())) {
                return true;
            }
        }
        return false;
    }

    public boolean phoneComplete() {
        for (Verification v : this.mVerificationGroups.getBasic().getItems()) {
            if ("phone_verified".equals(v.getId()) && "complete".equals(v.getStatus())) {
                return true;
            }
        }
        return false;
    }

    public boolean basicVerificationsComplete() {
        return emailVerificationComplete() && profilePhotoComplete() && phoneComplete();
    }

    public boolean onlineVerificationComplete() {
        for (Verification v : this.mVerificationGroups.getOnline().getItems()) {
            if ("complete".equals(v.getStatus())) {
                return true;
            }
        }
        return false;
    }

    public boolean offlineVerificationComplete() {
        for (Verification v : this.mVerificationGroups.getOffline().getItems()) {
            if ("complete".equals(v.getStatus())) {
                return true;
            }
        }
        return false;
    }

    public int numberStepsRemaining() {
        int numberStepsRemaining = 5;
        if (profilePhotoComplete()) {
            numberStepsRemaining = 5 - 1;
        }
        if (emailVerificationComplete()) {
            numberStepsRemaining--;
        }
        if (phoneComplete()) {
            numberStepsRemaining--;
        }
        if (offlineVerificationComplete()) {
            numberStepsRemaining--;
        }
        if (onlineVerificationComplete()) {
            return numberStepsRemaining - 1;
        }
        return numberStepsRemaining;
    }

    public boolean isAccountActivationComplete() {
        for (Verification verification : getVerificationGroups().getAccountActivation().getItems()) {
            if (!verification.isCompleted()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        if (this.mVerificationGroups.getBasic() != null) {
            return String.format("{Email: %b}, {Photo: %b}, {Phone: %b}, {Online: %b}, {Offline: %b}", new Object[]{Boolean.valueOf(emailVerificationComplete()), Boolean.valueOf(profilePhotoComplete()), Boolean.valueOf(phoneComplete()), Boolean.valueOf(onlineVerificationComplete()), Boolean.valueOf(offlineVerificationComplete())});
        } else if (this.mVerificationGroups.getAccountActivation() == null) {
            return "";
        } else {
            List<Verification> verifications = this.mVerificationGroups.getAccountActivation().getItems();
            StringBuilder builder = new StringBuilder("{");
            for (Verification verification : verifications) {
                builder.append(" " + verification.toString());
            }
            builder.append(" }");
            return builder.toString();
        }
    }
}
