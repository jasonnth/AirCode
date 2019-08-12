package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.generated.GenGovernmentIdResult;

public class GovernmentIdResult extends GenGovernmentIdResult {
    public static final Creator<GovernmentIdResult> CREATOR = new Creator<GovernmentIdResult>() {
        public GovernmentIdResult[] newArray(int size) {
            return new GovernmentIdResult[size];
        }

        public GovernmentIdResult createFromParcel(Parcel source) {
            GovernmentIdResult object = new GovernmentIdResult();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum Status {
        Approved("approved"),
        Denied("denied"),
        Unsupported("unsupported"),
        Awaiting("awaiting"),
        Unmapped("unmapped");
        
        /* access modifiers changed from: private */
        public final String name;

        private Status(String name2) {
            this.name = name2;
        }
    }

    public Status getStatusFromString() {
        Status[] values;
        String statusName = getStatus();
        for (Status status : Status.values()) {
            if (status.name.equals(statusName)) {
                return status;
            }
        }
        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Status " + statusName + " is unhandled by the client."));
        return null;
    }
}
