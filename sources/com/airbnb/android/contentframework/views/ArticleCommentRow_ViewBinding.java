package com.airbnb.android.contentframework.views;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class ArticleCommentRow_ViewBinding implements Unbinder {
    private ArticleCommentRow target;

    public ArticleCommentRow_ViewBinding(ArticleCommentRow target2) {
        this(target2, target2);
    }

    public ArticleCommentRow_ViewBinding(ArticleCommentRow target2, View source) {
        this.target = target2;
        target2.authorName = (TextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.text_author_name, "field 'authorName'", TextView.class);
        target2.commentDate = (TextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.text_comment_date, "field 'commentDate'", TextView.class);
        target2.commentContent = (TextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.text_comment_content, "field 'commentContent'", TextView.class);
        target2.thumbnail = (AirImageView) Utils.findRequiredViewAsType(source, C5709R.C5711id.author_photo, "field 'thumbnail'", AirImageView.class);
        target2.likeOverlay = (LinearLayout) Utils.findRequiredViewAsType(source, C5709R.C5711id.like_overlay, "field 'likeOverlay'", LinearLayout.class);
        target2.likeIcon = (AirImageView) Utils.findRequiredViewAsType(source, C5709R.C5711id.like_icon, "field 'likeIcon'", AirImageView.class);
        target2.likeCount = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.like_count, "field 'likeCount'", AirTextView.class);
        target2.divider = Utils.findRequiredView(source, C5709R.C5711id.divider, "field 'divider'");
    }

    public void unbind() {
        ArticleCommentRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.authorName = null;
        target2.commentDate = null;
        target2.commentContent = null;
        target2.thumbnail = null;
        target2.likeOverlay = null;
        target2.likeIcon = null;
        target2.likeCount = null;
        target2.divider = null;
    }
}
