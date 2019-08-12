package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.CalendarBubblePopUp_ViewBinding */
public class CalendarBubblePopUp_ViewBinding implements Unbinder {
    private CalendarBubblePopUp target;

    public CalendarBubblePopUp_ViewBinding(CalendarBubblePopUp target2, View source) {
        this.target = target2;
        target2.closeIcon = (AirImageView) Utils.findRequiredViewAsType(source, R.id.close_icon, "field 'closeIcon'", AirImageView.class);
        target2.message = (AirTextView) Utils.findRequiredViewAsType(source, R.id.message, "field 'message'", AirTextView.class);
    }

    public void unbind() {
        CalendarBubblePopUp target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.closeIcon = null;
        target2.message = null;
    }
}
