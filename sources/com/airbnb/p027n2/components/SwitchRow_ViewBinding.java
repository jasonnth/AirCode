package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirSwitch;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.SwitchRow_ViewBinding */
public class SwitchRow_ViewBinding implements Unbinder {
    private SwitchRow target;

    public SwitchRow_ViewBinding(SwitchRow target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.switch_row_title, "field 'title'", AirTextView.class);
        target2.description = (AirTextView) Utils.findRequiredViewAsType(source, R.id.switch_row_subtitle, "field 'description'", AirTextView.class);
        target2.switchView = (AirSwitch) Utils.findRequiredViewAsType(source, R.id.switch_row_switch, "field 'switchView'", AirSwitch.class);
    }

    public void unbind() {
        SwitchRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.description = null;
        target2.switchView = null;
    }
}
