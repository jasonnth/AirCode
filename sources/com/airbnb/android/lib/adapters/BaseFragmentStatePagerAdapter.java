package com.airbnb.android.lib.adapters;

import android.annotation.SuppressLint;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;
import java.util.List;

public abstract class BaseFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private final SparseArray<WeakReference<Fragment>> mFragmentCache = new SparseArray<>();

    @SuppressLint({"RestrictedApi"})
    public BaseFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
        List<Fragment> fragments = fm.getFragments();
        if (fragments != null) {
            for (int i = 0; i < fragments.size(); i++) {
                this.mFragmentCache.put(i, new WeakReference(fragments.get(i)));
            }
        }
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Object item = super.instantiateItem(container, position);
        if (item instanceof Fragment) {
            this.mFragmentCache.put(position, new WeakReference<>((Fragment) item));
        }
        return item;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        this.mFragmentCache.remove(position);
    }

    public Fragment getCachedFragment(int position) {
        WeakReference<Fragment> f = (WeakReference) this.mFragmentCache.get(position);
        if (f != null) {
            return (Fragment) f.get();
        }
        return null;
    }
}
