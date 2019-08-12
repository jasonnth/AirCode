package com.airbnb.android.explore.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class MTSearchInputField_ViewBinding implements Unbinder {
    private MTSearchInputField target;

    public MTSearchInputField_ViewBinding(MTSearchInputField target2) {
        this(target2, target2);
    }

    public MTSearchInputField_ViewBinding(MTSearchInputField target2, View source) {
        this.target = target2;
        target2.icon = (AirImageView) Utils.findRequiredViewAsType(source, R.id.icon, "field 'icon'", AirImageView.class);
        target2.textView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.text_view, "field 'textView'", AirTextView.class);
    }

    public void unbind() {
        MTSearchInputField target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.icon = null;
        target2.textView = null;
    }
}
