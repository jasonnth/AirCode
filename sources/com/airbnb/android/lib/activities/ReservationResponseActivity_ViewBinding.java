package com.airbnb.android.lib.activities;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class ReservationResponseActivity_ViewBinding implements Unbinder {
    private ReservationResponseActivity target;

    public ReservationResponseActivity_ViewBinding(ReservationResponseActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public ReservationResponseActivity_ViewBinding(ReservationResponseActivity target2, View source) {
        this.target = target2;
        target2.contentContainer = (ViewGroup) Utils.findRequiredViewAsType(source, C0880R.C0882id.content_container, "field 'contentContainer'", ViewGroup.class);
    }

    public void unbind() {
        ReservationResponseActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.contentContainer = null;
    }
}
