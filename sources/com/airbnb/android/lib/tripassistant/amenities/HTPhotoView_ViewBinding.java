package com.airbnb.android.lib.tripassistant.amenities;

import android.view.View;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class HTPhotoView_ViewBinding implements Unbinder {
    private HTPhotoView target;

    public HTPhotoView_ViewBinding(HTPhotoView target2) {
        this(target2, target2);
    }

    public HTPhotoView_ViewBinding(HTPhotoView target2, View source) {
        this.target = target2;
        target2.image = (AirImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.image, "field 'image'", AirImageView.class);
        target2.loader = Utils.findRequiredView(source, C0880R.C0882id.loading_view, "field 'loader'");
        target2.deleteButton = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.delete_button, "field 'deleteButton'", ImageView.class);
    }

    public void unbind() {
        HTPhotoView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.image = null;
        target2.loader = null;
        target2.deleteButton = null;
    }
}
