package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.ImageButton;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirTextView;

public class GuestControlToggleView_ViewBinding implements Unbinder {
    private GuestControlToggleView target;

    public GuestControlToggleView_ViewBinding(GuestControlToggleView target2) {
        this(target2, target2);
    }

    public GuestControlToggleView_ViewBinding(GuestControlToggleView target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.row_title, "field 'title'", AirTextView.class);
        target2.border = Utils.findRequiredView(source, C0880R.C0882id.row_top_border, "field 'border'");
        target2.noButton = (ImageButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.toggle_no, "field 'noButton'", ImageButton.class);
        target2.yesButton = (ImageButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.toggle_yes, "field 'yesButton'", ImageButton.class);
    }

    public void unbind() {
        GuestControlToggleView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.border = null;
        target2.noButton = null;
        target2.yesButton = null;
    }
}
