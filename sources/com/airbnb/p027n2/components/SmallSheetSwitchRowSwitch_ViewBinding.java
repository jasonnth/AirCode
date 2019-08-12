package com.airbnb.p027n2.components;

import android.content.Context;
import android.view.View;
import butterknife.Unbinder;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.components.SmallSheetSwitchRowSwitch_ViewBinding */
public class SmallSheetSwitchRowSwitch_ViewBinding implements Unbinder {
    @Deprecated
    public SmallSheetSwitchRowSwitch_ViewBinding(SmallSheetSwitchRowSwitch target, View source) {
        this(target, source.getContext());
    }

    public SmallSheetSwitchRowSwitch_ViewBinding(SmallSheetSwitchRowSwitch target, Context context) {
        target.strokeWidth = context.getResources().getDimensionPixelSize(R.dimen.n2_air_switch_stroke);
    }

    public void unbind() {
    }
}
