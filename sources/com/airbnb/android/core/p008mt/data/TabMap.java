package com.airbnb.android.core.p008mt.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.ExploreTab;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/* renamed from: com.airbnb.android.core.mt.data.TabMap */
public class TabMap extends LinkedHashMap<String, ExploreTab> implements Parcelable {
    public static final Creator<TabMap> CREATOR = new Creator<TabMap>() {
        public TabMap createFromParcel(Parcel in) {
            return new TabMap(in);
        }

        public TabMap[] newArray(int size) {
            return new TabMap[size];
        }
    };

    public TabMap() {
    }

    protected TabMap(Parcel in) {
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            put(in.readString(), (ExploreTab) in.readParcelable(ExploreTab.class.getClassLoader()));
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int i) {
        out.writeInt(size());
        for (Entry<String, ExploreTab> entry : entrySet()) {
            out.writeString((String) entry.getKey());
            out.writeParcelable((Parcelable) entry.getValue(), i);
        }
    }
}
