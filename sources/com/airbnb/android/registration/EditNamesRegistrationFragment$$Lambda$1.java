package com.airbnb.android.registration;

import com.airbnb.android.utils.TextWatcherUtils.StringTextWatcher;

final /* synthetic */ class EditNamesRegistrationFragment$$Lambda$1 implements StringTextWatcher {
    private final EditNamesRegistrationFragment arg$1;

    private EditNamesRegistrationFragment$$Lambda$1(EditNamesRegistrationFragment editNamesRegistrationFragment) {
        this.arg$1 = editNamesRegistrationFragment;
    }

    public static StringTextWatcher lambdaFactory$(EditNamesRegistrationFragment editNamesRegistrationFragment) {
        return new EditNamesRegistrationFragment$$Lambda$1(editNamesRegistrationFragment);
    }

    public void textUpdated(String str) {
        this.arg$1.nextButton.setEnabled(this.arg$1.hasEnteredValidNames());
    }
}
