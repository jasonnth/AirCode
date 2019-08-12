package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenAccount;
import java.util.Arrays;

public class Account extends GenAccount {
    public static final Creator<Account> CREATOR = new Creator<Account>() {
        public Account[] newArray(int size) {
            return new Account[size];
        }

        public Account createFromParcel(Parcel source) {
            Account object = new Account();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean tosRequired() {
        return hasRequiredStep("tos");
    }

    public boolean acceptCommunityCommitmentRequired() {
        return hasRequiredStep("community_commitment");
    }

    private boolean hasRequiredStep(String key) {
        return Arrays.asList(getRequiredSteps()).contains(key);
    }
}
