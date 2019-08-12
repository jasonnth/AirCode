package com.airbnb.android.registration;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

final /* synthetic */ class EditNamesRegistrationFragment$$Lambda$2 implements OnEditorActionListener {
    private final EditNamesRegistrationFragment arg$1;

    private EditNamesRegistrationFragment$$Lambda$2(EditNamesRegistrationFragment editNamesRegistrationFragment) {
        this.arg$1 = editNamesRegistrationFragment;
    }

    public static OnEditorActionListener lambdaFactory$(EditNamesRegistrationFragment editNamesRegistrationFragment) {
        return new EditNamesRegistrationFragment$$Lambda$2(editNamesRegistrationFragment);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return EditNamesRegistrationFragment.lambda$setupViews$1(this.arg$1, textView, i, keyEvent);
    }
}
