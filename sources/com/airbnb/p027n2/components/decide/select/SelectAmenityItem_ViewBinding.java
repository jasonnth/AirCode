package com.airbnb.p027n2.components.decide.select;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.decide.select.SelectAmenityItem_ViewBinding */
public class SelectAmenityItem_ViewBinding implements Unbinder {
    private SelectAmenityItem target;

    public SelectAmenityItem_ViewBinding(SelectAmenityItem target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.amenity_image, "field 'imageView'", AirImageView.class);
        target2.amenityNameTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.amenity_name, "field 'amenityNameTextView'", AirTextView.class);
    }

    public void unbind() {
        SelectAmenityItem target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.amenityNameTextView = null;
    }
}
