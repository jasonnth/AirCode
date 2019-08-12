package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

/* renamed from: com.airbnb.n2.components.DocumentMarquee_ViewBinding */
public class DocumentMarquee_ViewBinding implements Unbinder {
    private DocumentMarquee target;

    public DocumentMarquee_ViewBinding(DocumentMarquee target2, View source) {
        this.target = target2;
        target2.titleTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleTextView'", AirTextView.class);
        target2.captionTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.caption_text, "field 'captionTextView'", AirTextView.class);
        target2.linkTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.link_text, "field 'linkTextView'", AirTextView.class);
        target2.userImage = (HaloImageView) Utils.findRequiredViewAsType(source, R.id.user_image, "field 'userImage'", HaloImageView.class);
    }

    public void unbind() {
        DocumentMarquee target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleTextView = null;
        target2.captionTextView = null;
        target2.linkTextView = null;
        target2.userImage = null;
    }
}
