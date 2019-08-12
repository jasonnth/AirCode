package com.airbnb.android.registration;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class EditPasswordRegistrationFragment$$Lambda$2 implements OnEditorActionListener {
    private final EditPasswordRegistrationFragment arg$1;

    private EditPasswordRegistrationFragment$$Lambda$2(EditPasswordRegistrationFragment editPasswordRegistrationFragment) {
        this.arg$1 = editPasswordRegistrationFragment;
    }

    public static OnEditorActionListener lambdaFactory$(EditPasswordRegistrationFragment editPasswordRegistrationFragment) {
        return new EditPasswordRegistrationFragment$$Lambda$2(editPasswordRegistrationFragment);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return this.arg$1.next();
    }
}
