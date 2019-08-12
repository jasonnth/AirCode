package com.airbnb.android.core.adapters;

import android.content.Context;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;
import java.lang.ref.WeakReference;

public abstract class BaseTabFragmentPager extends FragmentStatePagerAdapter {
    protected final Context context;
    private final SparseArray<WeakReference<Fragment>> fragments = new SparseArray<>();

    public abstract int getPageIconId(int i);

    public abstract int getPageTitleId(int i);

    public abstract boolean isSwipePagingEnabled(int i);

    public BaseTabFragmentPager(Context context2, FragmentManager fm) {
        super(fm);
        this.context = context2;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        this.fragments.put(position, new WeakReference(fragment));
        return fragment;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
        this.fragments.delete(position);
        super.destroyItem(container, position, object);
    }

    public Fragment getFragment(int position) {
        WeakReference<Fragment> fragmentWeakReference = (WeakReference) this.fragments.get(position);
        if (fragmentWeakReference != null) {
            return (Fragment) fragmentWeakReference.get();
        }
        return null;
    }

    public CharSequence getPageTitle(int position) {
        return getContext().getString(getPageTitleId(position));
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.context;
    }
}
