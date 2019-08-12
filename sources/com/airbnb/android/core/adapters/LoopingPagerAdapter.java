package com.airbnb.android.core.adapters;

import android.support.p000v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.android.contentframework.imagepicker.MediaLoader;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.utils.Strap;
import java.util.List;

@Deprecated
public abstract class LoopingPagerAdapter<T> extends PagerAdapter {
    private static final int NUM_DUPLICATE_ITEMS = 4;
    private static final int NUM_DUPLICATE_ITEMS_END = 2;
    public static final int NUM_DUPLICATE_ITEMS_START = 2;
    private List<T> mItems;
    private boolean mLoopingEnabled;
    private SparseArray<View> mViews;

    /* access modifiers changed from: protected */
    public abstract View createView(ViewGroup viewGroup, int i);

    public LoopingPagerAdapter(List<T> items) {
        refreshItems(items);
    }

    private void refreshItems(List<T> items) {
        boolean z = true;
        this.mItems = items;
        this.mViews = new SparseArray<>();
        if (this.mItems.size() <= 1) {
            z = false;
        }
        this.mLoopingEnabled = z;
    }

    public int getCount() {
        return (this.mLoopingEnabled ? 4 : 0) + this.mItems.size();
    }

    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        View view = (View) this.mViews.get(position);
        if (view == null) {
            view = createView(container, position);
            this.mViews.put(position, view);
        }
        container.addView(view);
        return view;
    }

    public int getAdjustedPosition(int position) {
        int position2;
        if (!this.mLoopingEnabled) {
            return position;
        }
        int originalPos = position;
        if (position == 0) {
            position2 = this.mItems.size() - 2;
        } else if (position == 1) {
            position2 = this.mItems.size() - 1;
        } else if (position == getCount() - 2) {
            position2 = 0;
        } else if (position == getCount() - 1) {
            position2 = 1;
        } else {
            position2 = position - 2;
        }
        if (position2 >= this.mItems.size()) {
            position2 = this.mItems.size() - 1;
        }
        if (position2 < 0) {
            AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv("page", "looping_pager_adapter").mo11639kv("action", "return_negative_position").mo11637kv("input_position", originalPos).mo11637kv("offset_position", position2).mo11637kv("items_size", this.mItems.size()).mo11637kv(MediaLoader.COLUMN_COUNT, getCount()));
            position2 = 0;
        }
        return position2;
    }

    public int itemToPagePosition(int actualItemPos) {
        return this.mLoopingEnabled ? actualItemPos + 2 : actualItemPos;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public boolean isLoopingEnabled() {
        return this.mLoopingEnabled;
    }

    public int getItemPosition(Object object) {
        return -2;
    }
}
