package com.airbnb.android.hostcalendar.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.p027n2.primitives.AirTextView;

public class BlankCalendarFragment_ViewBinding implements Unbinder {
    private BlankCalendarFragment target;

    public BlankCalendarFragment_ViewBinding(BlankCalendarFragment target2, View source) {
        this.target = target2;
        target2.descriptiveText = (AirTextView) Utils.findRequiredViewAsType(source, C6418R.C6420id.descriptive_text, "field 'descriptiveText'", AirTextView.class);
    }

    public void unbind() {
        BlankCalendarFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.descriptiveText = null;
    }
}
