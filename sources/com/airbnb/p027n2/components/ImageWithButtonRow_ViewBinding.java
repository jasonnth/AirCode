package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.ImageWithButtonRow_ViewBinding */
public class ImageWithButtonRow_ViewBinding implements Unbinder {
    private ImageWithButtonRow target;

    public ImageWithButtonRow_ViewBinding(ImageWithButtonRow target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageView'", AirImageView.class);
        target2.buttonView = (AirButton) Utils.findRequiredViewAsType(source, R.id.button, "field 'buttonView'", AirButton.class);
    }

    public void unbind() {
        ImageWithButtonRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.buttonView = null;
    }
}
