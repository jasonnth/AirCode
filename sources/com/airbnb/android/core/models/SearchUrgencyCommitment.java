package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.enums.UrgencyMessageType;
import com.airbnb.android.core.models.generated.GenSearchUrgencyCommitment;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchUrgencyCommitment extends GenSearchUrgencyCommitment {
    public static final Creator<SearchUrgencyCommitment> CREATOR = new Creator<SearchUrgencyCommitment>() {
        public SearchUrgencyCommitment[] newArray(int size) {
            return new SearchUrgencyCommitment[size];
        }

        public SearchUrgencyCommitment createFromParcel(Parcel source) {
            SearchUrgencyCommitment object = new SearchUrgencyCommitment();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("message_type")
    public void setMessageType(String serverKey) {
        setMessageType(UrgencyMessageType.fromKey(serverKey));
    }

    public String getTitle() {
        return this.mMessage.getHeadline();
    }

    public String getSubtitle() {
        return this.mMessage.getBody();
    }

    public String getContexualMessage() {
        return this.mMessage.getContextualMessage();
    }

    public boolean isTypeSupported() {
        return (this.mMessageType == null || this.mMessageType == UrgencyMessageType.Unknown) ? false : true;
    }
}
