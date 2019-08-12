package com.airbnb.android.core.payments.models.clientparameters;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.payments.models.BillProductType;
import java.util.ArrayList;
import java.util.List;

final class AutoValue_HomesClientParameters extends C$AutoValue_HomesClientParameters {
    public static final Creator<AutoValue_HomesClientParameters> CREATOR = new Creator<AutoValue_HomesClientParameters>() {
        public AutoValue_HomesClientParameters createFromParcel(Parcel in) {
            String str;
            String str2;
            Boolean bool;
            String str3;
            boolean z = true;
            String str4 = null;
            BillProductType valueOf = BillProductType.valueOf(in.readString());
            String readString = in.readString();
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            if (in.readInt() == 0) {
                str2 = in.readString();
            } else {
                str2 = null;
            }
            if (in.readInt() == 0) {
                if (in.readInt() != 1) {
                    z = false;
                }
                bool = Boolean.valueOf(z);
            } else {
                bool = null;
            }
            if (in.readInt() == 0) {
                str3 = in.readString();
            } else {
                str3 = null;
            }
            ArrayList readArrayList = in.readArrayList(GuestIdentity.class.getClassLoader());
            if (in.readInt() == 0) {
                str4 = in.readString();
            }
            return new AutoValue_HomesClientParameters(valueOf, readString, str, str2, bool, str3, readArrayList, str4);
        }

        public AutoValue_HomesClientParameters[] newArray(int size) {
            return new AutoValue_HomesClientParameters[size];
        }
    };

    AutoValue_HomesClientParameters(BillProductType productType, String reservationConfirmationCode, String messageToHost, String searchRankingId, Boolean isBusinessTrip, String businessTripNotes, List<GuestIdentity> guestIdentities, String p4Steps) {
        super(productType, reservationConfirmationCode, messageToHost, searchRankingId, isBusinessTrip, businessTripNotes, guestIdentities, p4Steps);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productType().name());
        dest.writeString(reservationConfirmationCode());
        if (messageToHost() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(messageToHost());
        }
        if (searchRankingId() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(searchRankingId());
        }
        if (isBusinessTrip() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(isBusinessTrip().booleanValue() ? 1 : 0);
        }
        if (businessTripNotes() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(businessTripNotes());
        }
        dest.writeList(guestIdentities());
        if (p4Steps() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(p4Steps());
    }

    public int describeContents() {
        return 0;
    }
}
