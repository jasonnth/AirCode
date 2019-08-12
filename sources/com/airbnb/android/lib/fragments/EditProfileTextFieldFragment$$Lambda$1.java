package com.airbnb.android.lib.fragments;

import android.view.inputmethod.InputMethodManager;

final /* synthetic */ class EditProfileTextFieldFragment$$Lambda$1 implements Runnable {
    private final EditProfileTextFieldFragment arg$1;

    private EditProfileTextFieldFragment$$Lambda$1(EditProfileTextFieldFragment editProfileTextFieldFragment) {
        this.arg$1 = editProfileTextFieldFragment;
    }

    public static Runnable lambdaFactory$(EditProfileTextFieldFragment editProfileTextFieldFragment) {
        return new EditProfileTextFieldFragment$$Lambda$1(editProfileTextFieldFragment);
    }

    public void run() {
        ((InputMethodManager) this.arg$1.getActivity().getSystemService("input_method")).toggleSoftInput(2, 0);
    }
}
