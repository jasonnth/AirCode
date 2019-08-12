package com.airbnb.android.contentframework.imagepicker;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class MediaGridItemView_ViewBinding implements Unbinder {
    private MediaGridItemView target;

    public MediaGridItemView_ViewBinding(MediaGridItemView target2) {
        this(target2, target2);
    }

    public MediaGridItemView_ViewBinding(MediaGridItemView target2, View source) {
        this.target = target2;
        target2.thumbnail = (AirImageView) Utils.findRequiredViewAsType(source, C5709R.C5711id.media_thumbnail, "field 'thumbnail'", AirImageView.class);
        target2.checkView = (CheckView) Utils.findRequiredViewAsType(source, C5709R.C5711id.check_view, "field 'checkView'", CheckView.class);
        target2.gridItemInnerPadding = source.getContext().getResources().getDimension(C5709R.dimen.story_creation_media_grid_inner_padding);
    }

    public void unbind() {
        MediaGridItemView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.thumbnail = null;
        target2.checkView = null;
    }
}
