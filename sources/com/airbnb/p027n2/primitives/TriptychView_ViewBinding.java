package com.airbnb.p027n2.primitives;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.primitives.TriptychView_ViewBinding */
public class TriptychView_ViewBinding implements Unbinder {
    private TriptychView target;

    public TriptychView_ViewBinding(TriptychView target2, View source) {
        this.target = target2;
        target2.leftImage = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image_left, "field 'leftImage'", AirImageView.class);
        target2.rightTopImage = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image_right_top, "field 'rightTopImage'", AirImageView.class);
        target2.rightBottomImage = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image_right_bottom, "field 'rightBottomImage'", AirImageView.class);
        target2.rightImagesContainer = (ViewGroup) Utils.findRequiredViewAsType(source, R.id.right_images_container, "field 'rightImagesContainer'", ViewGroup.class);
    }

    public void unbind() {
        TriptychView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.leftImage = null;
        target2.rightTopImage = null;
        target2.rightBottomImage = null;
        target2.rightImagesContainer = null;
    }
}
