package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.LoaderFrame;

public class FBAccountKitPhoneNumberVerificationActivity_ViewBinding implements Unbinder {
    private FBAccountKitPhoneNumberVerificationActivity target;

    public FBAccountKitPhoneNumberVerificationActivity_ViewBinding(FBAccountKitPhoneNumberVerificationActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public FBAccountKitPhoneNumberVerificationActivity_ViewBinding(FBAccountKitPhoneNumberVerificationActivity target2, View source) {
        this.target = target2;
        target2.loaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C6533R.C6535id.loader_frame, "field 'loaderFrame'", LoaderFrame.class);
    }

    public void unbind() {
        FBAccountKitPhoneNumberVerificationActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.loaderFrame = null;
    }
}
