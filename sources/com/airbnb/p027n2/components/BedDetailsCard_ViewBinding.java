package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.BedDetailsCard_ViewBinding */
public class BedDetailsCard_ViewBinding implements Unbinder {
    private BedDetailsCard target;

    public BedDetailsCard_ViewBinding(BedDetailsCard target2, View source) {
        this.target = target2;
        target2.iconsView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.icons, "field 'iconsView'", AirTextView.class);
        target2.titleView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleView'", AirTextView.class);
        target2.descriptionView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.description, "field 'descriptionView'", AirTextView.class);
    }

    public void unbind() {
        BedDetailsCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.iconsView = null;
        target2.titleView = null;
        target2.descriptionView = null;
    }
}
