package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenFixItItem;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class FixItItem extends GenFixItItem {
    public static final Creator<FixItItem> CREATOR = new Creator<FixItItem>() {
        public FixItItem[] newArray(int size) {
            return new FixItItem[size];
        }

        public FixItItem createFromParcel(Parcel source) {
            FixItItem object = new FixItItem();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final int PROOF_REQUIRED_NONE = 0;
    public static final int PROOF_REQUIRED_PHOTOS = 1;
    public static final int SEVERITY_NOT_REQUIRED = 0;
    public static final int SEVERITY_REQUIRED = 1;
    public static final int STATUS_APPROVED = 2;
    public static final int STATUS_HOST_ACTION_REQUIRED = 0;
    public static final int STATUS_NEEDS_REVIEW = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ProofRequired {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SeverityLevel {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }

    public int getProofRequired() {
        return super.getProofRequired();
    }

    public int getSeverityLevel() {
        return super.getSeverityLevel();
    }

    public int getStatus() {
        return super.getStatus();
    }

    public boolean isMissingRequiredProof() {
        return getProofRequired() == 1 && getProofs().isEmpty();
    }

    public boolean isAwaitingReview() {
        return getStatus() == 1;
    }

    public boolean isApproved() {
        return getStatus() == 2;
    }

    public boolean requiresAction() {
        return getStatus() == 0;
    }

    public boolean isReadOnly() {
        return isAwaitingReview() || isApproved();
    }
}
