package com.airbnb.android.registration;

import com.airbnb.p027n2.components.SheetInputText.OnShowPasswordToggleListener;

final /* synthetic */ class EditPasswordRegistrationFragment$$Lambda$1 implements OnShowPasswordToggleListener {
    private final EditPasswordRegistrationFragment arg$1;

    private EditPasswordRegistrationFragment$$Lambda$1(EditPasswordRegistrationFragment editPasswordRegistrationFragment) {
        this.arg$1 = editPasswordRegistrationFragment;
    }

    public static OnShowPasswordToggleListener lambdaFactory$(EditPasswordRegistrationFragment editPasswordRegistrationFragment) {
        return new EditPasswordRegistrationFragment$$Lambda$1(editPasswordRegistrationFragment);
    }

    public void onToggled(boolean z) {
        EditPasswordRegistrationFragment.lambda$setupViews$0(this.arg$1, z);
    }
}
