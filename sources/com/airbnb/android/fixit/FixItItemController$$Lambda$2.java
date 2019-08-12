package com.airbnb.android.fixit;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.FixItItem;

final /* synthetic */ class FixItItemController$$Lambda$2 implements OnClickListener {
    private final FixItItemController arg$1;
    private final FixItItem arg$2;

    private FixItItemController$$Lambda$2(FixItItemController fixItItemController, FixItItem fixItItem) {
        this.arg$1 = fixItItemController;
        this.arg$2 = fixItItem;
    }

    public static OnClickListener lambdaFactory$(FixItItemController fixItItemController, FixItItem fixItItem) {
        return new FixItItemController$$Lambda$2(fixItItemController, fixItItem);
    }

    public void onClick(View view) {
        this.arg$1.listener.onCommentItemSelected(this.arg$2);
    }
}
