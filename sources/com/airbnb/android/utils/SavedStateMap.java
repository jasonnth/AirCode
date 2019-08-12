package com.airbnb.android.utils;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.Fragment.SavedState;
import android.support.p000v4.app.FragmentManager;
import java.util.HashMap;
import java.util.Map;

public class SavedStateMap implements Parcelable {
    public static final Creator<SavedStateMap> CREATOR = new Creator<SavedStateMap>() {
        public SavedStateMap createFromParcel(Parcel in) {
            return new SavedStateMap(in);
        }

        public SavedStateMap[] newArray(int size) {
            return new SavedStateMap[size];
        }
    };
    private final Map<String, SavedState> map;

    public SavedStateMap() {
        this.map = new HashMap();
    }

    private SavedStateMap(Parcel in) {
        this.map = new HashMap();
        ParcelableUtils.readParcelableIntoMap(in, this.map, String.class, SavedState.class);
    }

    public void saveState(FragmentManager fragmentManager, Fragment fragment, String tag) {
        String cacheKey = cacheKey(fragment, tag);
        SavedState savedState = fragmentManager.saveFragmentInstanceState(fragment);
        if (savedState != null) {
            this.map.put(cacheKey, savedState);
        } else {
            this.map.remove(cacheKey);
        }
    }

    public void restoreState(Fragment fragment, String tag) {
        SavedState state = (SavedState) this.map.remove(cacheKey(fragment, tag));
        if (state != null) {
            fragment.setInitialSavedState(state);
        }
    }

    public void clearState(Fragment fragment, String tag) {
        this.map.remove(cacheKey(fragment, tag));
    }

    private static String cacheKey(Fragment fragment, String tag) {
        String fragmentClass = fragment.getClass().getSimpleName();
        return tag == null ? fragmentClass : fragmentClass + "-" + tag;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        ParcelableUtils.writeParcelableMap(dest, this.map);
    }
}
