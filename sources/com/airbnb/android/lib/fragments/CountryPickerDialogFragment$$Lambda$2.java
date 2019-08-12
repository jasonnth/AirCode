package com.airbnb.android.lib.fragments;

import android.widget.ListView;

final /* synthetic */ class CountryPickerDialogFragment$$Lambda$2 implements Runnable {
    private final int arg$1;
    private final ListView arg$2;

    private CountryPickerDialogFragment$$Lambda$2(int i, ListView listView) {
        this.arg$1 = i;
        this.arg$2 = listView;
    }

    public static Runnable lambdaFactory$(int i, ListView listView) {
        return new CountryPickerDialogFragment$$Lambda$2(i, listView);
    }

    public void run() {
        CountryPickerDialogFragment.lambda$onCreateView$1(this.arg$1, this.arg$2);
    }
}
