package com.airbnb.android.lib.activities;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

final /* synthetic */ class DebugMenuActivity$$Lambda$5 implements OnClickListener {
    private final DebugMenuActivity arg$1;
    private final EditText arg$2;

    private DebugMenuActivity$$Lambda$5(DebugMenuActivity debugMenuActivity, EditText editText) {
        this.arg$1 = debugMenuActivity;
        this.arg$2 = editText;
    }

    public static OnClickListener lambdaFactory$(DebugMenuActivity debugMenuActivity, EditText editText) {
        return new DebugMenuActivity$$Lambda$5(debugMenuActivity, editText);
    }

    public void onClick(View view) {
        DebugMenuActivity.lambda$onClickGoToArticle$4(this.arg$1, this.arg$2, view);
    }
}
