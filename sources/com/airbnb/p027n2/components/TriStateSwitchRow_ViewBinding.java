package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.TriStateSwitch;

/* renamed from: com.airbnb.n2.components.TriStateSwitchRow_ViewBinding */
public class TriStateSwitchRow_ViewBinding implements Unbinder {
    private TriStateSwitchRow target;

    public TriStateSwitchRow_ViewBinding(TriStateSwitchRow target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", AirTextView.class);
        target2.description = (AirTextView) Utils.findRequiredViewAsType(source, R.id.description, "field 'description'", AirTextView.class);
        target2.triStateSwitchSelector = (TriStateSwitch) Utils.findRequiredViewAsType(source, R.id.tri_state_switch, "field 'triStateSwitchSelector'", TriStateSwitch.class);
        target2.dividerView = source.findViewById(R.id.section_divider);
    }

    public void unbind() {
        TriStateSwitchRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.description = null;
        target2.triStateSwitchSelector = null;
        target2.dividerView = null;
    }
}
