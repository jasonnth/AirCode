package com.airbnb.android.login.p339ui;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.login.C7331R;
import com.airbnb.p027n2.components.AirToolbar;

/* renamed from: com.airbnb.android.login.ui.LoginActivity_ViewBinding */
public class LoginActivity_ViewBinding implements Unbinder {
    private LoginActivity target;

    public LoginActivity_ViewBinding(LoginActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public LoginActivity_ViewBinding(LoginActivity target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7331R.C7333id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.loaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C7331R.C7333id.sheet_loader_frame, "field 'loaderFrame'", LoaderFrame.class);
        target2.contentContainer = (ViewGroup) Utils.findRequiredViewAsType(source, C7331R.C7333id.content_container, "field 'contentContainer'", ViewGroup.class);
    }

    public void unbind() {
        LoginActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.loaderFrame = null;
        target2.contentContainer = null;
    }
}
