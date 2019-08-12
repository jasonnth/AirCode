package com.airbnb.android.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.HashSet;
import java.util.LinkedList;

public class ParcelableLongMaxSizeSet extends HashSet<Long> implements Parcelable {
    public static final Creator<ParcelableLongMaxSizeSet> CREATOR = new Creator<ParcelableLongMaxSizeSet>() {
        public ParcelableLongMaxSizeSet[] newArray(int size) {
            return new ParcelableLongMaxSizeSet[size];
        }

        public ParcelableLongMaxSizeSet createFromParcel(Parcel source) {
            ParcelableLongMaxSizeSet map = new ParcelableLongMaxSizeSet(source.readInt());
            ParcelableUtils.readParcelableIntoLongSet(source, map);
            ParcelableUtils.readParcelableIntoLongLinkedList(source, map.linkedList);
            return map;
        }
    };
    /* access modifiers changed from: private */
    public final LinkedList<Long> linkedList = new LinkedList<>();
    private final int maxSize;

    public ParcelableLongMaxSizeSet(int maxSize2) {
        this.maxSize = maxSize2;
    }

    public boolean add(Long l) {
        if (!super.add(l)) {
            return false;
        }
        this.linkedList.add(l);
        if (size() > this.maxSize) {
            remove(this.linkedList.getFirst());
        }
        return true;
    }

    public boolean remove(Object o) {
        if (!super.remove(o)) {
            return false;
        }
        this.linkedList.remove(o);
        return true;
    }

    public void clear() {
        super.clear();
        this.linkedList.clear();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ParcelableLongMaxSizeSet)) {
            return false;
        }
        ParcelableLongMaxSizeSet longs = (ParcelableLongMaxSizeSet) o;
        if (this.maxSize != longs.maxSize || !this.linkedList.equals(longs.linkedList)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((super.hashCode() * 31) + this.maxSize) * 31) + this.linkedList.hashCode();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.maxSize);
        ParcelableUtils.writeParcelableLongSet(dest, this);
        ParcelableUtils.writeParcelableLongLinkedList(dest, this.linkedList);
    }
}
