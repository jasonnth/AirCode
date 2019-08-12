package com.airbnb.android.utils;

import android.support.p000v4.app.Fragment;

public class FragmentBundler<F extends Fragment> {
    private final FragmentBundleBuilder<F> bundle = new FragmentBundleBuilder<>();
    private final F fragment;

    public static final class FragmentBundleBuilder<F extends Fragment> extends ExtendableBundleBuilder<FragmentBundleBuilder<F>> {
        private final FragmentBundler<F> fragmentBundler;

        private FragmentBundleBuilder(FragmentBundler<F> fragmentBundler2) {
            this.fragmentBundler = fragmentBundler2;
        }

        public F build() {
            return this.fragmentBundler.build();
        }
    }

    private FragmentBundler(F fragment2) {
        this.fragment = fragment2;
    }

    public static <F extends Fragment> FragmentBundleBuilder<F> make(F fragment2) {
        return new FragmentBundler(fragment2).bundle();
    }

    public F build() {
        this.fragment.setArguments(this.bundle.toBundle());
        return this.fragment;
    }

    public FragmentBundleBuilder<F> bundle() {
        return this.bundle;
    }
}
