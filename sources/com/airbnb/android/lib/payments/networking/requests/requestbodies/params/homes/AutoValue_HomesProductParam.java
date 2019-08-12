package com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.lib.payments.networking.requests.requestbodies.params.homes.guestidentity.HomesGuestIdentifications;

final class AutoValue_HomesProductParam extends C$AutoValue_HomesProductParam {
    public static final Creator<AutoValue_HomesProductParam> CREATOR = new Creator<AutoValue_HomesProductParam>() {
        public AutoValue_HomesProductParam createFromParcel(Parcel in) {
            String str;
            String str2;
            String str3;
            Boolean bool = null;
            String readString = in.readString();
            String readString2 = in.readString();
            HomesRequestInfoParam homesRequestInfoParam = (HomesRequestInfoParam) in.readParcelable(HomesRequestInfoParam.class.getClassLoader());
            HomesReservationDetailsProductParam homesReservationDetailsProductParam = (HomesReservationDetailsProductParam) in.readParcelable(HomesReservationDetailsProductParam.class.getClassLoader());
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
                str3 = in.readString();
            } else {
                str3 = null;
            }
            if (in.readInt() == 0) {
                bool = Boolean.valueOf(in.readInt() == 1);
            }
            return new AutoValue_HomesProductParam(readString, readString2, homesRequestInfoParam, homesReservationDetailsProductParam, str, str2, str3, bool, (HomesBusinessTravelProductParam) in.readParcelable(HomesBusinessTravelProductParam.class.getClassLoader()), (HomesGuestIdentifications) in.readParcelable(HomesGuestIdentifications.class.getClassLoader()));
        }

        public AutoValue_HomesProductParam[] newArray(int size) {
            return new AutoValue_HomesProductParam[size];
        }
    };

    AutoValue_HomesProductParam(String productType, String code, HomesRequestInfoParam requestInfo, HomesReservationDetailsProductParam reservationDetails, String couponCode, String intents, String searchRankingId, Boolean isBusinessTrip, HomesBusinessTravelProductParam businessTravel, HomesGuestIdentifications guestIdentifications) {
        super(productType, code, requestInfo, reservationDetails, couponCode, intents, searchRankingId, isBusinessTrip, businessTravel, guestIdentifications);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = 1;
        dest.writeString(productType());
        dest.writeString(code());
        dest.writeParcelable(requestInfo(), flags);
        dest.writeParcelable(reservationDetails(), flags);
        if (couponCode() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(couponCode());
        }
        if (intents() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(intents());
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
            if (!isBusinessTrip().booleanValue()) {
                i = 0;
            }
            dest.writeInt(i);
        }
        dest.writeParcelable(businessTravel(), flags);
        dest.writeParcelable(guestIdentifications(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
