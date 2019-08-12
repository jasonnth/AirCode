package com.airbnb.android.lib.utils;

import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;

public abstract class EasyCache<From, To> {
    private final SparseArray<To> mBackingSparse = new SparseArray<>();

    public abstract To transform(From from);

    public To getEntry(int position, From item) {
        if (this.mBackingSparse.indexOfKey(position) >= 0) {
            return this.mBackingSparse.get(position);
        }
        To result = transform(item);
        this.mBackingSparse.append(position, result);
        return result;
    }

    public void clear() {
        this.mBackingSparse.clear();
    }

    public List<To> getValues() {
        List<To> results = new ArrayList<>();
        for (int i = 0; i < this.mBackingSparse.size(); i++) {
            results.add(this.mBackingSparse.valueAt(i));
        }
        return results;
    }
}
