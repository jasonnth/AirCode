package com.airbnb.android.lib.views;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class EditableCell$$Lambda$1 implements OnClickListener {
    private final EditableCell arg$1;

    private EditableCell$$Lambda$1(EditableCell editableCell) {
        this.arg$1 = editableCell;
    }

    public static OnClickListener lambdaFactory$(EditableCell editableCell) {
        return new EditableCell$$Lambda$1(editableCell);
    }

    public void onClick(View view) {
        this.arg$1.focusOnValue();
    }
}
