package com.airbnb.android.lib.fragments.managelisting;

import android.support.p000v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.lib.C0880R;

final /* synthetic */ class EditPriceFragment$$Lambda$1 implements OnClickListener {
    private final EditPriceFragment arg$1;

    private EditPriceFragment$$Lambda$1(EditPriceFragment editPriceFragment) {
        this.arg$1 = editPriceFragment;
    }

    public static OnClickListener lambdaFactory$(EditPriceFragment editPriceFragment) {
        return new EditPriceFragment$$Lambda$1(editPriceFragment);
    }

    public void onClick(View view) {
        ZenDialog.builder().withTitle(C0880R.string.smart_pricing_learn_more_dialog_title).withBodyText(C0880R.string.smart_pricing_learn_more_dialog_body).withSingleButton(C0880R.string.okay, 0, (Fragment) null).create().showAllowingStateLoss(this.arg$1.getFragmentManager(), null);
    }
}
