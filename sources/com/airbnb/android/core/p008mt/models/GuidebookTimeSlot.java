package com.airbnb.android.core.p008mt.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;

/* renamed from: com.airbnb.android.core.mt.models.GuidebookTimeSlot */
public enum GuidebookTimeSlot implements Parcelable {
    MORNING(0, C0716R.string.explore_places_morning),
    AFTERNOON(1, C0716R.string.explore_places_afternoon),
    EVENING(2, C0716R.string.explore_places_evening),
    LATE_NIGHT(3, C0716R.string.explore_places_late_night);
    
    public static final Creator<GuidebookTimeSlot> CREATOR = null;

    /* renamed from: id */
    private final int f8480id;
    private final int stringRes;

    static {
        CREATOR = new Creator<GuidebookTimeSlot>() {
            public GuidebookTimeSlot createFromParcel(Parcel source) {
                return GuidebookTimeSlot.values()[source.readInt()];
            }

            public GuidebookTimeSlot[] newArray(int size) {
                return new GuidebookTimeSlot[size];
            }
        };
    }

    private GuidebookTimeSlot(int id, int stringRes2) {
        this.f8480id = id;
        this.stringRes = stringRes2;
    }

    public static GuidebookTimeSlot from(int givenId) {
        GuidebookTimeSlot[] values;
        for (GuidebookTimeSlot timeSlot : values()) {
            if (timeSlot.f8480id == givenId) {
                return timeSlot;
            }
        }
        return null;
    }

    public int getId() {
        return this.f8480id;
    }

    public int getStringRes() {
        return this.stringRes;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
