package com.airbnb.android.core.wishlists;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.jitney.event.logging.WishlistSource.p295v3.C2813WishlistSource;

final class AutoValue_WishListableData extends C$AutoValue_WishListableData {
    public static final Creator<AutoValue_WishListableData> CREATOR = new Creator<AutoValue_WishListableData>() {
        public AutoValue_WishListableData createFromParcel(Parcel in) {
            String str;
            String str2 = null;
            boolean z = true;
            WishListableType valueOf = WishListableType.valueOf(in.readString());
            long readLong = in.readLong();
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            GuestDetails guestDetails = (GuestDetails) in.readParcelable(GuestDetails.class.getClassLoader());
            AirDate airDate = (AirDate) in.readParcelable(AirDate.class.getClassLoader());
            AirDate airDate2 = (AirDate) in.readParcelable(AirDate.class.getClassLoader());
            if (in.readInt() == 0) {
                str2 = in.readString();
            }
            C2813WishlistSource valueOf2 = C2813WishlistSource.valueOf(in.readString());
            if (in.readInt() != 1) {
                z = false;
            }
            return new AutoValue_WishListableData(valueOf, readLong, str, guestDetails, airDate, airDate2, str2, valueOf2, z);
        }

        public AutoValue_WishListableData[] newArray(int size) {
            return new AutoValue_WishListableData[size];
        }
    };

    AutoValue_WishListableData(WishListableType type, long itemId, String suggestedWishListName, GuestDetails guestDetails, AirDate checkIn, AirDate checkOut, String searchSessionId, C2813WishlistSource source, boolean allowAutoAdd) {
        super(type, itemId, suggestedWishListName, guestDetails, checkIn, checkOut, searchSessionId, source, allowAutoAdd);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = 1;
        dest.writeString(type().name());
        dest.writeLong(itemId());
        if (suggestedWishListName() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(suggestedWishListName());
        }
        dest.writeParcelable(guestDetails(), flags);
        dest.writeParcelable(checkIn(), flags);
        dest.writeParcelable(checkOut(), flags);
        if (searchSessionId() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(searchSessionId());
        }
        dest.writeString(source().name());
        if (!allowAutoAdd()) {
            i = 0;
        }
        dest.writeInt(i);
    }

    public int describeContents() {
        return 0;
    }
}
