package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.DisplayCard_ViewBinding */
public class DisplayCard_ViewBinding implements Unbinder {
    private DisplayCard target;

    public DisplayCard_ViewBinding(DisplayCard target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageView'", AirImageView.class);
        target2.textView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.text, "field 'textView'", AirTextView.class);
        target2.extraRow = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.extra_row, "field 'extraRow'", LinearLayout.class);
        target2.commentCount = (AirTextView) Utils.findRequiredViewAsType(source, R.id.comment_count, "field 'commentCount'", AirTextView.class);
        target2.likeCount = (AirTextView) Utils.findRequiredViewAsType(source, R.id.like_count, "field 'likeCount'", AirTextView.class);
        target2.bottomSpace = Utils.findRequiredView(source, R.id.bottom_space, "field 'bottomSpace'");
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
        target2.clickOverlay = Utils.findRequiredView(source, R.id.click_overlay, "field 'clickOverlay'");
    }

    public void unbind() {
        DisplayCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.textView = null;
        target2.extraRow = null;
        target2.commentCount = null;
        target2.likeCount = null;
        target2.bottomSpace = null;
        target2.divider = null;
        target2.clickOverlay = null;
    }
}
