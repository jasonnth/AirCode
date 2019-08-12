package com.airbnb.android.lib.fragments.managelisting;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class EditPriceFragment$$Lambda$2 implements OnClickListener {
    private final EditPriceFragment arg$1;

    private EditPriceFragment$$Lambda$2(EditPriceFragment editPriceFragment) {
        this.arg$1 = editPriceFragment;
    }

    public static OnClickListener lambdaFactory$(EditPriceFragment editPriceFragment) {
        return new EditPriceFragment$$Lambda$2(editPriceFragment);
    }

    public void onClick(View view) {
        EditPriceFragment.lambda$onResume$2(this.arg$1, view);
    }
}
