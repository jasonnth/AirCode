package com.airbnb.android.core.arguments;

import com.airbnb.android.core.Activities;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.GovernmentIdResult;
import com.airbnb.android.core.models.User;
import java.util.ArrayList;
import java.util.List;

public abstract class AccountVerificationStartFragmentArguments extends Arguments {
    public static final long INVALID_ID = -1;

    public static abstract class Builder {
        public abstract AccountVerificationStartFragmentArguments build();

        public abstract Builder firstVerificationStep(String str);

        public abstract Builder governmentIdResult(GovernmentIdResult governmentIdResult);

        public abstract Builder host(User user);

        public abstract Builder incompleteVerifications(List<AccountVerification> list);

        public abstract Builder isIdentityOnP4(Boolean bool);

        public abstract Builder isMoveToLastStep(boolean z);

        public abstract Builder listingId(long j);

        public abstract Builder p4Steps(String str);

        public abstract Builder phoneVerificationCode(String str);

        public abstract Builder reservationFrozenReason(String str);

        public abstract Builder selfiePhotoFilePaths(ArrayList<String> arrayList);

        public abstract Builder verificationFlow(VerificationFlow verificationFlow);

        public abstract Builder verificationUser(User user);
    }

    public abstract String firstVerificationStep();

    public abstract GovernmentIdResult governmentIdResult();

    public abstract User host();

    public abstract List<AccountVerification> incompleteVerifications();

    public abstract Boolean isIdentityOnP4();

    public abstract boolean isMoveToLastStep();

    public abstract long listingId();

    public abstract String p4Steps();

    public abstract String phoneVerificationCode();

    public abstract String reservationFrozenReason();

    public abstract ArrayList<String> selfiePhotoFilePaths();

    public abstract VerificationFlow verificationFlow();

    public abstract User verificationUser();

    public Class<?> getIntentClass() {
        return Activities.accountVerificationStart();
    }

    public static Builder builder() {
        return new Builder().listingId(-1).isMoveToLastStep(false);
    }
}
