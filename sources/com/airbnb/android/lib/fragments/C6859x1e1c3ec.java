package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.airbnb.android.lib.fragments.ProPhotographyFragment.ProPhotoSelectListingDialogFragment;

/* renamed from: com.airbnb.android.lib.fragments.ProPhotographyFragment$ProPhotoSelectListingDialogFragment$$Lambda$2 */
final /* synthetic */ class C6859x1e1c3ec implements OnItemClickListener {
    private final ProPhotoSelectListingDialogFragment arg$1;

    private C6859x1e1c3ec(ProPhotoSelectListingDialogFragment proPhotoSelectListingDialogFragment) {
        this.arg$1 = proPhotoSelectListingDialogFragment;
    }

    public static OnItemClickListener lambdaFactory$(ProPhotoSelectListingDialogFragment proPhotoSelectListingDialogFragment) {
        return new C6859x1e1c3ec(proPhotoSelectListingDialogFragment);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        ProPhotoSelectListingDialogFragment.lambda$setupListView$1(this.arg$1, adapterView, view, i, j);
    }
}
