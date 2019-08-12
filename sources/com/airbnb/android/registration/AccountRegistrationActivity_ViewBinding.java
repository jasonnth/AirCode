package com.airbnb.android.registration;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.p027n2.components.AirToolbar;

public class AccountRegistrationActivity_ViewBinding implements Unbinder {
    private AccountRegistrationActivity target;

    public AccountRegistrationActivity_ViewBinding(AccountRegistrationActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public AccountRegistrationActivity_ViewBinding(AccountRegistrationActivity target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C1562R.C1564id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.loaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C1562R.C1564id.sheet_loader_frame, "field 'loaderFrame'", LoaderFrame.class);
        target2.fragmentContainer = Utils.findRequiredView(source, C1562R.C1564id.content_container, "field 'fragmentContainer'");
    }

    public void unbind() {
        AccountRegistrationActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.loaderFrame = null;
        target2.fragmentContainer = null;
    }
}
