package com.airbnb.android.lib.fragments.unlist;

import android.support.p000v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.lib.C0880R;

final /* synthetic */ class ListingVisibilityFragment$$Lambda$1 implements OnClickListener {
    private final ListingVisibilityFragment arg$1;

    private ListingVisibilityFragment$$Lambda$1(ListingVisibilityFragment listingVisibilityFragment) {
        this.arg$1 = listingVisibilityFragment;
    }

    public static OnClickListener lambdaFactory$(ListingVisibilityFragment listingVisibilityFragment) {
        return new ListingVisibilityFragment$$Lambda$1(listingVisibilityFragment);
    }

    public void onClick(View view) {
        ZenDialog.builder().withTitle(C0880R.string.ml_snooze_tooltip_title).withBodyText(this.arg$1.getString(C0880R.string.ml_snooze_tooltip_description)).withSingleButton(C0880R.string.okay, 0, (Fragment) null).create().show(this.arg$1.getFragmentManager(), (String) null);
    }
}
