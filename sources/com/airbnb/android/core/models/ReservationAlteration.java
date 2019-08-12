package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenReservationAlteration;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservationAlteration extends GenReservationAlteration {
    public static final Creator<ReservationAlteration> CREATOR = new Creator<ReservationAlteration>() {
        public ReservationAlteration[] newArray(int size) {
            return new ReservationAlteration[size];
        }

        public ReservationAlteration createFromParcel(Parcel source) {
            ReservationAlteration object = new ReservationAlteration();
            object.readFromParcel(source);
            return object;
        }
    };
    private static final String INITIATOR_GUEST = "guest";
    private static final String INITIATOR_HOST = "host";

    public enum Status {
        Transient(-1),
        Pending(0),
        Accepted(1),
        Declined(2),
        Void(3);
        
        private final int statusCode;

        private Status(int statusCode2) {
            this.statusCode = statusCode2;
        }

        public int getStatusCode() {
            return this.statusCode;
        }

        public static Status fromCode(int statusCode2) {
            Status[] values;
            for (Status status : values()) {
                if (status.statusCode == statusCode2) {
                    return status;
                }
            }
            return null;
        }
    }

    @JsonProperty("status")
    public void setStatus(int statusCode) {
        this.mStatus = Status.fromCode(statusCode);
    }

    public boolean isAccepted() {
        return getStatus() == Status.Accepted;
    }

    public boolean isDeclined() {
        return getStatus() == Status.Declined;
    }

    public boolean isPending() {
        return getStatus() == Status.Pending;
    }

    public boolean isVoid() {
        return getStatus() == Status.Void;
    }

    public boolean isInitiatedByGuest() {
        return "guest".equalsIgnoreCase(getInitiatedBy());
    }

    public boolean isInitiatedByHost() {
        return "host".equalsIgnoreCase(getInitiatedBy());
    }

    public boolean isInitiatedByUser(User user) {
        return user.getId() == this.mInitiatorId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.mId != ((ReservationAlteration) o).mId) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.mId ^ (this.mId >>> 32));
    }
}
