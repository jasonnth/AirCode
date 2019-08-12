package com.airbnb.android.lib.utils;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import com.airbnb.android.lib.C0880R;

public class ThemeUtils {
    public static LayoutInflater inflaterForPhonePadding(LayoutInflater inflater) {
        return inflaterWithTheme(inflater, C0880R.C0885style.Theme_TabNav_PhonePadding);
    }

    public static LayoutInflater inflaterWithTheme(LayoutInflater inflater, int themeResId) {
        return inflater.cloneInContext(new ContextThemeWrapper(inflater.getContext(), themeResId));
    }
}
