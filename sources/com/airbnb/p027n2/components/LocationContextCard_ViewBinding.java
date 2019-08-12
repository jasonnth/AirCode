package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.LocationContextCard_ViewBinding */
public class LocationContextCard_ViewBinding implements Unbinder {
    private LocationContextCard target;

    public LocationContextCard_ViewBinding(LocationContextCard target2, View source) {
        this.target = target2;
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleText'", AirTextView.class);
        target2.recommendedLabel = (AirTextView) Utils.findRequiredViewAsType(source, R.id.recommended_label, "field 'recommendedLabel'", AirTextView.class);
        target2.bodyText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.body_text, "field 'bodyText'", AirTextView.class);
        target2.button = (AirButton) Utils.findRequiredViewAsType(source, R.id.button, "field 'button'", AirButton.class);
        target2.locationImage = (AirImageView) Utils.findRequiredViewAsType(source, R.id.location_img, "field 'locationImage'", AirImageView.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
    }

    public void unbind() {
        LocationContextCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleText = null;
        target2.recommendedLabel = null;
        target2.bodyText = null;
        target2.button = null;
        target2.locationImage = null;
        target2.divider = null;
    }
}
