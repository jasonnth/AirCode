package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenInboxMetadata;

public class InboxMetadata extends GenInboxMetadata {
    public static final Creator<InboxMetadata> CREATOR = new Creator<InboxMetadata>() {
        public InboxMetadata[] newArray(int size) {
            return new InboxMetadata[size];
        }

        public InboxMetadata createFromParcel(Parcel source) {
            InboxMetadata object = new InboxMetadata();
            object.readFromParcel(source);
            return object;
        }
    };

    public void setUnreadCount(boolean isHost, long count) {
        if (isHost) {
            setUnreadHostCount(count);
        } else {
            setUnreadGuestCount(count);
        }
    }
}
