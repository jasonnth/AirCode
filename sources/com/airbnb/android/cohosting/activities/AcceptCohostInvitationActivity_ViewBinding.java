package com.airbnb.android.cohosting.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.RefreshLoader;

public class AcceptCohostInvitationActivity_ViewBinding implements Unbinder {
    private AcceptCohostInvitationActivity target;

    public AcceptCohostInvitationActivity_ViewBinding(AcceptCohostInvitationActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public AcceptCohostInvitationActivity_ViewBinding(AcceptCohostInvitationActivity target2, View source) {
        this.target = target2;
        target2.fullLoader = (RefreshLoader) Utils.findRequiredViewAsType(source, C5658R.C5660id.loading_row, "field 'fullLoader'", RefreshLoader.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5658R.C5660id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        AcceptCohostInvitationActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.fullLoader = null;
        target2.toolbar = null;
    }
}
