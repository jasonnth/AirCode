package com.airbnb.android.lib.tripassistant;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;

public class HelpThreadDialogActivity_ViewBinding implements Unbinder {
    private HelpThreadDialogActivity target;

    public HelpThreadDialogActivity_ViewBinding(HelpThreadDialogActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public HelpThreadDialogActivity_ViewBinding(HelpThreadDialogActivity target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.text = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.text, "field 'text'", TextView.class);
    }

    public void unbind() {
        HelpThreadDialogActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.text = null;
    }
}
