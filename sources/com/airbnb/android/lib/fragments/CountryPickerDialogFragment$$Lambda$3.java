package com.airbnb.android.lib.fragments;

import com.airbnb.android.core.models.NameCodeItem;
import java.util.Comparator;

final /* synthetic */ class CountryPickerDialogFragment$$Lambda$3 implements Comparator {
    private static final CountryPickerDialogFragment$$Lambda$3 instance = new CountryPickerDialogFragment$$Lambda$3();

    private CountryPickerDialogFragment$$Lambda$3() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ((NameCodeItem) obj).getName().compareTo(((NameCodeItem) obj2).getName());
    }
}
