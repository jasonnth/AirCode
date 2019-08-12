package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final /* synthetic */ class CurrencySelectorDialogFragment$$Lambda$1 implements OnItemClickListener {
    private final CurrencySelectorDialogFragment arg$1;

    private CurrencySelectorDialogFragment$$Lambda$1(CurrencySelectorDialogFragment currencySelectorDialogFragment) {
        this.arg$1 = currencySelectorDialogFragment;
    }

    public static OnItemClickListener lambdaFactory$(CurrencySelectorDialogFragment currencySelectorDialogFragment) {
        return new CurrencySelectorDialogFragment$$Lambda$1(currencySelectorDialogFragment);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        CurrencySelectorDialogFragment.lambda$getItemClickListener$0(this.arg$1, adapterView, view, i, j);
    }
}
