package com.airbnb.android.fixit;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class FixItItemController$$Lambda$1 implements OnClickListener {
    private final FixItItemController arg$1;

    private FixItItemController$$Lambda$1(FixItItemController fixItItemController) {
        this.arg$1 = fixItItemController;
    }

    public static OnClickListener lambdaFactory$(FixItItemController fixItItemController) {
        return new FixItItemController$$Lambda$1(fixItItemController);
    }

    public void onClick(View view) {
        this.arg$1.listener.onPhotoProofsItemSelected();
    }
}
