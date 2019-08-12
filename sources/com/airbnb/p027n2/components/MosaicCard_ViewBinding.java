package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.TriptychView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.MosaicCard_ViewBinding */
public class MosaicCard_ViewBinding implements Unbinder {
    private MosaicCard target;

    public MosaicCard_ViewBinding(MosaicCard target2, View source) {
        this.target = target2;
        target2.titleTextView = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleTextView'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
        target2.triptychView = (TriptychView) Utils.findRequiredViewAsType(source, R.id.triptych, "field 'triptychView'", TriptychView.class);
        target2.bottomSpace = Utils.findRequiredView(source, R.id.bottom_space, "field 'bottomSpace'");
        target2.likeIcon = (AirImageView) Utils.findRequiredViewAsType(source, R.id.like_icon, "field 'likeIcon'", AirImageView.class);
        target2.commentIcon = (AirImageView) Utils.findRequiredViewAsType(source, R.id.comment_icon, "field 'commentIcon'", AirImageView.class);
        target2.likeCount = (AirTextView) Utils.findRequiredViewAsType(source, R.id.like_count, "field 'likeCount'", AirTextView.class);
        target2.commentCount = (AirTextView) Utils.findRequiredViewAsType(source, R.id.comment_count, "field 'commentCount'", AirTextView.class);
        target2.tag = (AirTextView) Utils.findRequiredViewAsType(source, R.id.tag, "field 'tag'", AirTextView.class);
        target2.accessory = (LinearLayout) Utils.findRequiredViewAsType(source, R.id.accessory, "field 'accessory'", LinearLayout.class);
    }

    public void unbind() {
        MosaicCard target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleTextView = null;
        target2.divider = null;
        target2.triptychView = null;
        target2.bottomSpace = null;
        target2.likeIcon = null;
        target2.commentIcon = null;
        target2.likeCount = null;
        target2.commentCount = null;
        target2.tag = null;
        target2.accessory = null;
    }
}
