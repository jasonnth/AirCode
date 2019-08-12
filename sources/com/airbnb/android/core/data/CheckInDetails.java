package com.airbnb.android.core.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.Listing;

public abstract class CheckInDetails implements Parcelable {

    public static abstract class Builder {
        public abstract CheckInDetails build();

        /* access modifiers changed from: 0000 */
        public abstract Builder checkIn(String str);

        /* access modifiers changed from: 0000 */
        public abstract Builder checkOut(String str);

        /* access modifiers changed from: 0000 */
        public abstract Builder latestCheckIn(String str);

        /* Debug info: failed to restart local var, previous not found, register: 2 */
        public Builder setTermsValue(Term term, String value) {
            switch (term) {
                case CheckInTime:
                    return checkIn(value);
                case CheckOutTime:
                    return checkOut(value);
                case LatestCheckInTime:
                    return latestCheckIn(value);
                default:
                    return this;
            }
        }
    }

    public enum Term implements Parcelable {
        CheckInTime,
        LatestCheckInTime,
        CheckOutTime;
        
        public static final Creator<Term> CREATOR = null;

        static {
            CREATOR = new Creator<Term>() {
                public Term createFromParcel(Parcel source) {
                    return Term.values()[source.readInt()];
                }

                public Term[] newArray(int size) {
                    return new Term[size];
                }
            };
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(ordinal());
        }
    }

    public abstract String checkIn();

    public abstract String checkOut();

    public abstract String latestCheckIn();

    public abstract Builder toBuilder();

    public static Parcelable fromListing(Listing listing) {
        return builder().setTermsValue(Term.CheckInTime, listing.getCheckInTimeStart()).setTermsValue(Term.CheckOutTime, listing.getCheckOutTime() != null ? listing.getCheckOutTime().toString() : null).setTermsValue(Term.LatestCheckInTime, listing.getCheckInTimeEnd()).build();
    }

    public static Builder builder() {
        return new Builder();
    }
}
