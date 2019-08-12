package com.airbnb.android.lib.activities;

import android.support.p002v7.widget.Toolbar;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class EditProfileActivity_ViewBinding implements Unbinder {
    private EditProfileActivity target;

    public EditProfileActivity_ViewBinding(EditProfileActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public EditProfileActivity_ViewBinding(EditProfileActivity target2, View source) {
        this.target = target2;
        target2.activityToolbar = (Toolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'activityToolbar'", Toolbar.class);
    }

    public void unbind() {
        EditProfileActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.activityToolbar = null;
    }
}
