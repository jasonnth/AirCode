package com.facebook.accountkit.p029ui;

import android.app.Fragment;
import android.os.Parcelable;
import com.facebook.accountkit.AccountKitError;

/* renamed from: com.facebook.accountkit.ui.UIManagerStub */
interface UIManagerStub extends Parcelable {
    Fragment getBodyFragment(LoginFlowState loginFlowState);

    ButtonType getButtonType(LoginFlowState loginFlowState);

    Fragment getFooterFragment(LoginFlowState loginFlowState);

    Fragment getHeaderFragment(LoginFlowState loginFlowState);

    TextPosition getTextPosition(LoginFlowState loginFlowState);

    void onError(AccountKitError accountKitError);
}
