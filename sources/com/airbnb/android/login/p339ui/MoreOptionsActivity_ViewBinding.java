package com.airbnb.android.login.p339ui;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.login.C7331R;
import com.airbnb.p027n2.components.AirToolbar;

/* renamed from: com.airbnb.android.login.ui.MoreOptionsActivity_ViewBinding */
public class MoreOptionsActivity_ViewBinding implements Unbinder {
    private MoreOptionsActivity target;

    public MoreOptionsActivity_ViewBinding(MoreOptionsActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public MoreOptionsActivity_ViewBinding(MoreOptionsActivity target2, View source) {
        this.target = target2;
        target2.optionsContainer = (ViewGroup) Utils.findRequiredViewAsType(source, C7331R.C7333id.sign_in_options_container, "field 'optionsContainer'", ViewGroup.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7331R.C7333id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        MoreOptionsActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.optionsContainer = null;
        target2.toolbar = null;
    }
}
