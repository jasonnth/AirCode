package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final /* synthetic */ class ListingSelectDialogFragment$$Lambda$1 implements OnItemClickListener {
    private final ListingSelectDialogFragment arg$1;

    private ListingSelectDialogFragment$$Lambda$1(ListingSelectDialogFragment listingSelectDialogFragment) {
        this.arg$1 = listingSelectDialogFragment;
    }

    public static OnItemClickListener lambdaFactory$(ListingSelectDialogFragment listingSelectDialogFragment) {
        return new ListingSelectDialogFragment$$Lambda$1(listingSelectDialogFragment);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        ListingSelectDialogFragment.lambda$getItemClickListener$0(this.arg$1, adapterView, view, i, j);
    }
}
