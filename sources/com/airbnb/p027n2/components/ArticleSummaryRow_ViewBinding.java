package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

/* renamed from: com.airbnb.n2.components.ArticleSummaryRow_ViewBinding */
public class ArticleSummaryRow_ViewBinding implements Unbinder {
    private ArticleSummaryRow target;

    public ArticleSummaryRow_ViewBinding(ArticleSummaryRow target2, View source) {
        this.target = target2;
        target2.coverImage = (AirImageView) Utils.findRequiredViewAsType(source, R.id.cover_image, "field 'coverImage'", AirImageView.class);
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title, "field 'title'", AirTextView.class);
        target2.tag = (AirTextView) Utils.findRequiredViewAsType(source, R.id.tag, "field 'tag'", AirTextView.class);
        target2.commentCount = (AirTextView) Utils.findRequiredViewAsType(source, R.id.comment_count, "field 'commentCount'", AirTextView.class);
        target2.likeCount = (AirTextView) Utils.findRequiredViewAsType(source, R.id.like_count, "field 'likeCount'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
        target2.commentIcon = (AirImageView) Utils.findRequiredViewAsType(source, R.id.comment_icon, "field 'commentIcon'", AirImageView.class);
        target2.likeIcon = (AirImageView) Utils.findRequiredViewAsType(source, R.id.like_icon, "field 'likeIcon'", AirImageView.class);
    }

    public void unbind() {
        ArticleSummaryRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.coverImage = null;
        target2.title = null;
        target2.tag = null;
        target2.commentCount = null;
        target2.likeCount = null;
        target2.divider = null;
        target2.commentIcon = null;
        target2.likeIcon = null;
    }
}
