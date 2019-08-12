package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.collections.AirVideoView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.EditorialMarquee_ViewBinding */
public class EditorialMarquee_ViewBinding implements Unbinder {
    private EditorialMarquee target;

    public EditorialMarquee_ViewBinding(EditorialMarquee target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageView'", AirImageView.class);
        target2.videoView = (AirVideoView) Utils.findRequiredViewAsType(source, R.id.video_view, "field 'videoView'", AirVideoView.class);
        target2.kickerTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.kicker, "field 'kickerTextView'", AirTextView.class);
        target2.titleTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'titleTextView'", AirTextView.class);
        target2.descriptionTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.description, "field 'descriptionTextView'", AirTextView.class);
    }

    public void unbind() {
        EditorialMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.videoView = null;
        target2.kickerTextView = null;
        target2.titleTextView = null;
        target2.descriptionTextView = null;
    }
}
