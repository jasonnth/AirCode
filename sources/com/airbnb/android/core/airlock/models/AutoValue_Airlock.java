package com.airbnb.android.core.airlock.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.List;

final class AutoValue_Airlock extends C$AutoValue_Airlock {
    public static final Creator<AutoValue_Airlock> CREATOR = new Creator<AutoValue_Airlock>() {
        public AutoValue_Airlock createFromParcel(Parcel in) {
            return new AutoValue_Airlock(in.readLong(), in.readLong(), in.readInt() == 0 ? in.readString() : null, in.readString(), (FrictionInitData) in.readParcelable(FrictionInitData.class.getClassLoader()), in.readArrayList(AirlockFrictionType.class.getClassLoader()));
        }

        public AutoValue_Airlock[] newArray(int size) {
            return new AutoValue_Airlock[size];
        }
    };

    AutoValue_Airlock(long id, long userId, String actionName, String firstName, FrictionInitData frictionInitData, List<List<AirlockFrictionType>> frictions) {
        super(id, userId, actionName, firstName, frictionInitData, frictions);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mo8253id());
        dest.writeLong(userId());
        if (actionName() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(actionName());
        }
        dest.writeString(firstName());
        dest.writeParcelable(frictionInitData(), flags);
        dest.writeList(frictions());
    }

    public int describeContents() {
        return 0;
    }
}
