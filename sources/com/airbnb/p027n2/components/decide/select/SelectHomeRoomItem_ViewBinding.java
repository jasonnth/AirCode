package com.airbnb.p027n2.components.decide.select;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.decide.select.SelectHomeRoomItem_ViewBinding */
public class SelectHomeRoomItem_ViewBinding implements Unbinder {
    private SelectHomeRoomItem target;

    public SelectHomeRoomItem_ViewBinding(SelectHomeRoomItem target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.room_image, "field 'imageView'", AirImageView.class);
        target2.titleTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.room_title, "field 'titleTextView'", AirTextView.class);
        target2.descriptionTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.room_description, "field 'descriptionTextView'", AirTextView.class);
    }

    public void unbind() {
        SelectHomeRoomItem target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.titleTextView = null;
        target2.descriptionTextView = null;
    }
}
