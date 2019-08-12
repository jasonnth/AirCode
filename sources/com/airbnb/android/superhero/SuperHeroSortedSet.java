package com.airbnb.android.superhero;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

public class SuperHeroSortedSet extends TreeSet<SuperHeroMessage> implements Parcelable {
    public static final Creator<SuperHeroSortedSet> CREATOR = new Creator<SuperHeroSortedSet>() {
        public SuperHeroSortedSet createFromParcel(Parcel in) {
            return new SuperHeroSortedSet(in);
        }

        public SuperHeroSortedSet[] newArray(int size) {
            return new SuperHeroSortedSet[size];
        }
    };
    private static final Comparator<SuperHeroMessage> SUPER_HERO_MESSAGE_COMPARATOR = new Comparator<SuperHeroMessage>() {
        public int compare(SuperHeroMessage o1, SuperHeroMessage o2) {
            int startsAtCmp = o1.starts_at().compareTo(o2.starts_at());
            return startsAtCmp != 0 ? startsAtCmp : Long.valueOf(o1.mo11531id()).compareTo(Long.valueOf(o2.mo11531id()));
        }
    };

    public SuperHeroSortedSet() {
        super(SUPER_HERO_MESSAGE_COMPARATOR);
    }

    protected SuperHeroSortedSet(Parcel in) {
        super(SUPER_HERO_MESSAGE_COMPARATOR);
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            add((SuperHeroMessage) in.readParcelable(SuperHeroMessage.class.getClassLoader()));
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int i) {
        out.writeInt(size());
        Iterator it = iterator();
        while (it.hasNext()) {
            out.writeParcelable((SuperHeroMessage) it.next(), i);
        }
    }
}
