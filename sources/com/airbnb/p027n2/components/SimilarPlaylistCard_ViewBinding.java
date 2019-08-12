package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.SimilarPlaylistCard_ViewBinding */
public class SimilarPlaylistCard_ViewBinding implements Unbinder {
    private SimilarPlaylistCard target;

    public SimilarPlaylistCard_ViewBinding(SimilarPlaylistCard target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageView'", AirImageView.class);
        target2.titleTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleTextView'", AirTextView.class);
        target2.subText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtext, "field 'subText'", AirTextView.class);
        target2.frame = Utils.findRequiredView(source, R.id.circular_frame, "field 'frame'");
    }

    public void unbind() {
        SimilarPlaylistCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.titleTextView = null;
        target2.subText = null;
        target2.frame = null;
    }
}
