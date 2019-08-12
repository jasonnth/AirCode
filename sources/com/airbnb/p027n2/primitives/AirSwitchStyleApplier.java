package com.airbnb.p027n2.primitives;

import android.content.res.Resources;
import com.airbnb.n2.R;
import com.airbnb.paris.Style;
import com.airbnb.paris.StyleApplier;
import com.airbnb.paris.TypedArrayWrapper;
import com.airbnb.paris.ViewStyleApplier;

/* renamed from: com.airbnb.n2.primitives.AirSwitchStyleApplier */
public final class AirSwitchStyleApplier extends StyleApplier<AirSwitchStyleApplier, AirSwitch> {
    public AirSwitchStyleApplier(AirSwitch view) {
        super(view);
    }

    /* access modifiers changed from: protected */
    public int[] attributes() {
        return R.styleable.n2_AirSwitch;
    }

    /* access modifiers changed from: protected */
    public void processAttributes(Style style, TypedArrayWrapper a) {
        Resources res = ((AirSwitch) getView()).getContext().getResources();
        if (a.hasValue(R.styleable.n2_AirSwitch_n2_switchStyle)) {
            ((AirSwitch) getView()).setSwitchStyle(a.getInt(R.styleable.n2_AirSwitch_n2_switchStyle, -1));
        } else {
            ((AirSwitch) getView()).setSwitchStyle(res.getInteger(R.integer.n2_air_switch_default_style));
        }
    }

    /* access modifiers changed from: protected */
    public void applyParent(Style style) {
        new ViewStyleApplier(getView()).apply(style);
    }
}
