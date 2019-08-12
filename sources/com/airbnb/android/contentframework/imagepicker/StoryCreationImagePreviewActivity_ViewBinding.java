package com.airbnb.android.contentframework.imagepicker;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class StoryCreationImagePreviewActivity_ViewBinding implements Unbinder {
    private StoryCreationImagePreviewActivity target;

    public StoryCreationImagePreviewActivity_ViewBinding(StoryCreationImagePreviewActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public StoryCreationImagePreviewActivity_ViewBinding(StoryCreationImagePreviewActivity target2, View source) {
        this.target = target2;
        target2.rootView = Utils.findRequiredView(source, C5709R.C5711id.root_view, "field 'rootView'");
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, C5709R.C5711id.media_preview, "field 'imageView'", AirImageView.class);
    }

    public void unbind() {
        StoryCreationImagePreviewActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.rootView = null;
        target2.imageView = null;
    }
}
