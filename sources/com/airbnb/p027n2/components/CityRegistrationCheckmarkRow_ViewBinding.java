package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.CityRegistrationCheckmarkRow_ViewBinding */
public class CityRegistrationCheckmarkRow_ViewBinding implements Unbinder {
    private CityRegistrationCheckmarkRow target;

    public CityRegistrationCheckmarkRow_ViewBinding(CityRegistrationCheckmarkRow target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", AirTextView.class);
        target2.subtitle = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle, "field 'subtitle'", AirTextView.class);
        target2.icon = (AirImageView) Utils.findRequiredViewAsType(source, R.id.icon, "field 'icon'", AirImageView.class);
    }

    public void unbind() {
        CityRegistrationCheckmarkRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.subtitle = null;
        target2.icon = null;
    }
}