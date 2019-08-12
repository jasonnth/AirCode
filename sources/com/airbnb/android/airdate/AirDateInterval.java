package com.airbnb.android.airdate;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class AirDateInterval implements Parcelable {
    public static final Creator<AirDateInterval> CREATOR = new Creator<AirDateInterval>() {
        public AirDateInterval createFromParcel(Parcel in) {
            return new AirDateInterval(in);
        }

        public AirDateInterval[] newArray(int size) {
            return new AirDateInterval[size];
        }
    };
    private final AirDate end;
    private final AirDate start;

    public AirDateInterval(AirDate start2, AirDate end2) {
        if (!start2.isSameDayOrBefore(end2)) {
            throw new IllegalStateException("Start AirDate: {" + start2.getIsoDateString() + "} cannot be after end AirDate: {" + end2.getIsoDateString() + "}");
        }
        this.start = start2;
        this.end = end2;
    }

    public boolean contains(AirDate date) {
        return contains(date, true, true);
    }

    public boolean contains(AirDate date, boolean startInclusive, boolean endInclusive) {
        int startComparison = this.start.compareTo(date);
        int endComparison = this.end.compareTo(date);
        if (startComparison < 0 && endComparison > 0) {
            return true;
        }
        if (!startInclusive && startComparison == 0) {
            return false;
        }
        if (!endInclusive && endComparison == 0) {
            return false;
        }
        if (startInclusive && startComparison == 0) {
            return true;
        }
        if (!endInclusive || endComparison != 0) {
            return false;
        }
        return true;
    }

    protected AirDateInterval(Parcel in) {
        this.start = (AirDate) in.readParcelable(AirDate.class.getClassLoader());
        this.end = (AirDate) in.readParcelable(AirDate.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.start, flags);
        dest.writeParcelable(this.end, flags);
    }

    public AirDate getStart() {
        return this.start;
    }

    public AirDate getEnd() {
        return this.end;
    }
}
