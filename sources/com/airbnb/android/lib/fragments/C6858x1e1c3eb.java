package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.lib.fragments.ProPhotographyFragment.ProPhotoSelectListingDialogFragment;

/* renamed from: com.airbnb.android.lib.fragments.ProPhotographyFragment$ProPhotoSelectListingDialogFragment$$Lambda$1 */
final /* synthetic */ class C6858x1e1c3eb implements OnClickListener {
    private final ProPhotoSelectListingDialogFragment arg$1;

    private C6858x1e1c3eb(ProPhotoSelectListingDialogFragment proPhotoSelectListingDialogFragment) {
        this.arg$1 = proPhotoSelectListingDialogFragment;
    }

    public static OnClickListener lambdaFactory$(ProPhotoSelectListingDialogFragment proPhotoSelectListingDialogFragment) {
        return new C6858x1e1c3eb(proPhotoSelectListingDialogFragment);
    }

    public void onClick(View view) {
        this.arg$1.dismiss();
    }
}
