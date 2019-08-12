package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final /* synthetic */ class CountryPickerDialogFragment$$Lambda$1 implements OnItemClickListener {
    private final CountryPickerDialogFragment arg$1;

    private CountryPickerDialogFragment$$Lambda$1(CountryPickerDialogFragment countryPickerDialogFragment) {
        this.arg$1 = countryPickerDialogFragment;
    }

    public static OnItemClickListener lambdaFactory$(CountryPickerDialogFragment countryPickerDialogFragment) {
        return new CountryPickerDialogFragment$$Lambda$1(countryPickerDialogFragment);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        CountryPickerDialogFragment.lambda$onCreateView$0(this.arg$1, adapterView, view, i, j);
    }
}
