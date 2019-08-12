package com.airbnb.android.core.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.utils.Strap;
import java.util.Map.Entry;

public class ParcelStrap implements Parcelable {
    public static final Creator<ParcelStrap> CREATOR = new Creator<ParcelStrap>() {
        public ParcelStrap createFromParcel(Parcel in) {
            ParcelStrap strap = ParcelStrap.make();
            int size = in.readInt();
            for (int i = 0; i < size; i++) {
                strap.mo9946kv(in.readString(), in.readString());
            }
            return strap;
        }

        public ParcelStrap[] newArray(int size) {
            return new ParcelStrap[size];
        }
    };
    private final Strap strap;

    private ParcelStrap() {
        this.strap = Strap.make();
    }

    private ParcelStrap(Strap strap2) {
        this.strap = strap2;
    }

    public static ParcelStrap make() {
        return new ParcelStrap();
    }

    public static ParcelStrap make(Strap strap2) {
        return strap2 != null ? new ParcelStrap(strap2) : new ParcelStrap();
    }

    /* renamed from: kv */
    public ParcelStrap mo9946kv(String k, String v) {
        this.strap.put(k, v);
        return this;
    }

    /* renamed from: kv */
    public ParcelStrap mo9945kv(String k, int v) {
        this.strap.mo11637kv(k, v);
        return this;
    }

    /* renamed from: kv */
    public ParcelStrap mo9944kv(String k, double v) {
        this.strap.mo11635kv(k, v);
        return this;
    }

    /* renamed from: kv */
    public ParcelStrap mo9947kv(String k, boolean v) {
        this.strap.mo11640kv(k, v);
        return this;
    }

    public ParcelStrap mix(ParcelStrap otherStrap) {
        this.strap.mix(otherStrap.internal());
        return this;
    }

    public ParcelStrap mix(ParcelStrap otherStrap, boolean overrideFields) {
        this.strap.mix(otherStrap.internal(), overrideFields);
        return this;
    }

    public void clear() {
        this.strap.clear();
    }

    public boolean isEmpty() {
        return this.strap.isEmpty();
    }

    public Strap internal() {
        return this.strap;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.strap.size());
        for (Entry<String, String> key : this.strap.entrySet()) {
            dest.writeString((String) key.getKey());
            dest.writeString((String) key.getValue());
        }
    }
}
