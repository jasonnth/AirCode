package com.airbnb.android.lib.paidamenities.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.PaidAmenityCategory;

final class AutoValue_PaidAmenityDetails extends C$AutoValue_PaidAmenityDetails {
    public static final Creator<AutoValue_PaidAmenityDetails> CREATOR = new Creator<AutoValue_PaidAmenityDetails>() {
        public AutoValue_PaidAmenityDetails createFromParcel(Parcel in) {
            Long l;
            String str;
            String str2;
            String str3;
            Integer num;
            boolean z;
            Boolean bool = null;
            if (in.readInt() == 0) {
                l = Long.valueOf(in.readLong());
            } else {
                l = null;
            }
            PaidAmenityCategory paidAmenityCategory = (PaidAmenityCategory) in.readParcelable(PaidAmenityCategory.class.getClassLoader());
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
                num = Integer.valueOf(in.readInt());
            } else {
                num = null;
            }
            if (in.readInt() == 0) {
                if (in.readInt() == 1) {
                    z = true;
                } else {
                    z = false;
                }
                bool = Boolean.valueOf(z);
            }
            return new AutoValue_PaidAmenityDetails(l, paidAmenityCategory, str, str2, str3, num, bool);
        }

        public AutoValue_PaidAmenityDetails[] newArray(int size) {
            return new AutoValue_PaidAmenityDetails[size];
        }
    };

    AutoValue_PaidAmenityDetails(Long listingId, PaidAmenityCategory paidAmenityType, String title, String description, String currency, Integer price, Boolean isComplementary) {
        super(listingId, paidAmenityType, title, description, currency, price, isComplementary);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = 1;
        if (listingId() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeLong(listingId().longValue());
        }
        dest.writeParcelable(paidAmenityType(), flags);
        if (title() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(title());
        }
        if (description() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(description());
        }
        if (currency() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(currency());
        }
        if (price() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeInt(price().intValue());
        }
        if (isComplementary() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        if (!isComplementary().booleanValue()) {
            i = 0;
        }
        dest.writeInt(i);
    }

    public int describeContents() {
        return 0;
    }
}
