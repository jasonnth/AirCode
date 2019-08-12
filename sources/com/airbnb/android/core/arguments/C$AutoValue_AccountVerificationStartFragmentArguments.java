package com.airbnb.android.core.arguments;

import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.GovernmentIdResult;
import com.airbnb.android.core.models.User;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.airbnb.android.core.arguments.$AutoValue_AccountVerificationStartFragmentArguments reason: invalid class name */
abstract class C$AutoValue_AccountVerificationStartFragmentArguments extends AccountVerificationStartFragmentArguments {
    private final String firstVerificationStep;
    private final GovernmentIdResult governmentIdResult;
    private final User host;
    private final List<AccountVerification> incompleteVerifications;
    private final Boolean isIdentityOnP4;
    private final boolean isMoveToLastStep;
    private final long listingId;
    private final String p4Steps;
    private final String phoneVerificationCode;
    private final String reservationFrozenReason;
    private final ArrayList<String> selfiePhotoFilePaths;
    private final VerificationFlow verificationFlow;
    private final User verificationUser;

    /* renamed from: com.airbnb.android.core.arguments.$AutoValue_AccountVerificationStartFragmentArguments$Builder */
    static final class Builder extends com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments.Builder {
        private String firstVerificationStep;
        private GovernmentIdResult governmentIdResult;
        private User host;
        private List<AccountVerification> incompleteVerifications;
        private Boolean isIdentityOnP4;
        private Boolean isMoveToLastStep;
        private Long listingId;
        private String p4Steps;
        private String phoneVerificationCode;
        private String reservationFrozenReason;
        private ArrayList<String> selfiePhotoFilePaths;
        private VerificationFlow verificationFlow;
        private User verificationUser;

        Builder() {
        }

        public com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments.Builder verificationFlow(VerificationFlow verificationFlow2) {
            if (verificationFlow2 == null) {
                throw new NullPointerException("Null verificationFlow");
            }
            this.verificationFlow = verificationFlow2;
            return this;
        }

        public com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments.Builder incompleteVerifications(List<AccountVerification> incompleteVerifications2) {
            this.incompleteVerifications = incompleteVerifications2;
            return this;
        }

        public com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments.Builder host(User host2) {
            this.host = host2;
            return this;
        }

        public com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments.Builder verificationUser(User verificationUser2) {
            this.verificationUser = verificationUser2;
            return this;
        }

        public com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments.Builder listingId(long listingId2) {
            this.listingId = Long.valueOf(listingId2);
            return this;
        }

        public com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments.Builder firstVerificationStep(String firstVerificationStep2) {
            this.firstVerificationStep = firstVerificationStep2;
            return this;
        }

        public com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments.Builder phoneVerificationCode(String phoneVerificationCode2) {
            this.phoneVerificationCode = phoneVerificationCode2;
            return this;
        }

        public com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments.Builder governmentIdResult(GovernmentIdResult governmentIdResult2) {
            this.governmentIdResult = governmentIdResult2;
            return this;
        }

        public com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments.Builder isMoveToLastStep(boolean isMoveToLastStep2) {
            this.isMoveToLastStep = Boolean.valueOf(isMoveToLastStep2);
            return this;
        }

        public com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments.Builder isIdentityOnP4(Boolean isIdentityOnP42) {
            this.isIdentityOnP4 = isIdentityOnP42;
            return this;
        }

        public com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments.Builder selfiePhotoFilePaths(ArrayList<String> selfiePhotoFilePaths2) {
            this.selfiePhotoFilePaths = selfiePhotoFilePaths2;
            return this;
        }

        public com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments.Builder p4Steps(String p4Steps2) {
            this.p4Steps = p4Steps2;
            return this;
        }

        public com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments.Builder reservationFrozenReason(String reservationFrozenReason2) {
            this.reservationFrozenReason = reservationFrozenReason2;
            return this;
        }

        public AccountVerificationStartFragmentArguments build() {
            String missing = "";
            if (this.verificationFlow == null) {
                missing = missing + " verificationFlow";
            }
            if (this.listingId == null) {
                missing = missing + " listingId";
            }
            if (this.isMoveToLastStep == null) {
                missing = missing + " isMoveToLastStep";
            }
            if (missing.isEmpty()) {
                return new AutoValue_AccountVerificationStartFragmentArguments(this.verificationFlow, this.incompleteVerifications, this.host, this.verificationUser, this.listingId.longValue(), this.firstVerificationStep, this.phoneVerificationCode, this.governmentIdResult, this.isMoveToLastStep.booleanValue(), this.isIdentityOnP4, this.selfiePhotoFilePaths, this.p4Steps, this.reservationFrozenReason);
            }
            throw new IllegalStateException("Missing required properties:" + missing);
        }
    }

    C$AutoValue_AccountVerificationStartFragmentArguments(VerificationFlow verificationFlow2, List<AccountVerification> incompleteVerifications2, User host2, User verificationUser2, long listingId2, String firstVerificationStep2, String phoneVerificationCode2, GovernmentIdResult governmentIdResult2, boolean isMoveToLastStep2, Boolean isIdentityOnP42, ArrayList<String> selfiePhotoFilePaths2, String p4Steps2, String reservationFrozenReason2) {
        if (verificationFlow2 == null) {
            throw new NullPointerException("Null verificationFlow");
        }
        this.verificationFlow = verificationFlow2;
        this.incompleteVerifications = incompleteVerifications2;
        this.host = host2;
        this.verificationUser = verificationUser2;
        this.listingId = listingId2;
        this.firstVerificationStep = firstVerificationStep2;
        this.phoneVerificationCode = phoneVerificationCode2;
        this.governmentIdResult = governmentIdResult2;
        this.isMoveToLastStep = isMoveToLastStep2;
        this.isIdentityOnP4 = isIdentityOnP42;
        this.selfiePhotoFilePaths = selfiePhotoFilePaths2;
        this.p4Steps = p4Steps2;
        this.reservationFrozenReason = reservationFrozenReason2;
    }

    public VerificationFlow verificationFlow() {
        return this.verificationFlow;
    }

    public List<AccountVerification> incompleteVerifications() {
        return this.incompleteVerifications;
    }

    public User host() {
        return this.host;
    }

    public User verificationUser() {
        return this.verificationUser;
    }

    public long listingId() {
        return this.listingId;
    }

    public String firstVerificationStep() {
        return this.firstVerificationStep;
    }

    public String phoneVerificationCode() {
        return this.phoneVerificationCode;
    }

    public GovernmentIdResult governmentIdResult() {
        return this.governmentIdResult;
    }

    public boolean isMoveToLastStep() {
        return this.isMoveToLastStep;
    }

    public Boolean isIdentityOnP4() {
        return this.isIdentityOnP4;
    }

    public ArrayList<String> selfiePhotoFilePaths() {
        return this.selfiePhotoFilePaths;
    }

    public String p4Steps() {
        return this.p4Steps;
    }

    public String reservationFrozenReason() {
        return this.reservationFrozenReason;
    }

    public String toString() {
        return "AccountVerificationStartFragmentArguments{verificationFlow=" + this.verificationFlow + ", incompleteVerifications=" + this.incompleteVerifications + ", host=" + this.host + ", verificationUser=" + this.verificationUser + ", listingId=" + this.listingId + ", firstVerificationStep=" + this.firstVerificationStep + ", phoneVerificationCode=" + this.phoneVerificationCode + ", governmentIdResult=" + this.governmentIdResult + ", isMoveToLastStep=" + this.isMoveToLastStep + ", isIdentityOnP4=" + this.isIdentityOnP4 + ", selfiePhotoFilePaths=" + this.selfiePhotoFilePaths + ", p4Steps=" + this.p4Steps + ", reservationFrozenReason=" + this.reservationFrozenReason + "}";
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AccountVerificationStartFragmentArguments)) {
            return false;
        }
        AccountVerificationStartFragmentArguments that = (AccountVerificationStartFragmentArguments) o;
        if (this.verificationFlow.equals(that.verificationFlow()) && (this.incompleteVerifications != null ? this.incompleteVerifications.equals(that.incompleteVerifications()) : that.incompleteVerifications() == null) && (this.host != null ? this.host.equals(that.host()) : that.host() == null) && (this.verificationUser != null ? this.verificationUser.equals(that.verificationUser()) : that.verificationUser() == null) && this.listingId == that.listingId() && (this.firstVerificationStep != null ? this.firstVerificationStep.equals(that.firstVerificationStep()) : that.firstVerificationStep() == null) && (this.phoneVerificationCode != null ? this.phoneVerificationCode.equals(that.phoneVerificationCode()) : that.phoneVerificationCode() == null) && (this.governmentIdResult != null ? this.governmentIdResult.equals(that.governmentIdResult()) : that.governmentIdResult() == null) && this.isMoveToLastStep == that.isMoveToLastStep() && (this.isIdentityOnP4 != null ? this.isIdentityOnP4.equals(that.isIdentityOnP4()) : that.isIdentityOnP4() == null) && (this.selfiePhotoFilePaths != null ? this.selfiePhotoFilePaths.equals(that.selfiePhotoFilePaths()) : that.selfiePhotoFilePaths() == null) && (this.p4Steps != null ? this.p4Steps.equals(that.p4Steps()) : that.p4Steps() == null)) {
            if (this.reservationFrozenReason == null) {
                if (that.reservationFrozenReason() == null) {
                    return true;
                }
            } else if (this.reservationFrozenReason.equals(that.reservationFrozenReason())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int h = ((((((((((((((((int) (((long) (((((((((1 * 1000003) ^ this.verificationFlow.hashCode()) * 1000003) ^ (this.incompleteVerifications == null ? 0 : this.incompleteVerifications.hashCode())) * 1000003) ^ (this.host == null ? 0 : this.host.hashCode())) * 1000003) ^ (this.verificationUser == null ? 0 : this.verificationUser.hashCode())) * 1000003)) ^ ((this.listingId >>> 32) ^ this.listingId))) * 1000003) ^ (this.firstVerificationStep == null ? 0 : this.firstVerificationStep.hashCode())) * 1000003) ^ (this.phoneVerificationCode == null ? 0 : this.phoneVerificationCode.hashCode())) * 1000003) ^ (this.governmentIdResult == null ? 0 : this.governmentIdResult.hashCode())) * 1000003) ^ (this.isMoveToLastStep ? 1231 : HelpCenterArticle.VERIFIED_ID_LEARN_MORE)) * 1000003) ^ (this.isIdentityOnP4 == null ? 0 : this.isIdentityOnP4.hashCode())) * 1000003) ^ (this.selfiePhotoFilePaths == null ? 0 : this.selfiePhotoFilePaths.hashCode())) * 1000003) ^ (this.p4Steps == null ? 0 : this.p4Steps.hashCode())) * 1000003;
        if (this.reservationFrozenReason != null) {
            i = this.reservationFrozenReason.hashCode();
        }
        return h ^ i;
    }
}
