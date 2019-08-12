package com.airbnb.android.contentframework.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.StoryTitleTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;

public class StoryCardFeedItem_ViewBinding implements Unbinder {
    private StoryCardFeedItem target;

    public StoryCardFeedItem_ViewBinding(StoryCardFeedItem target2) {
        this(target2, target2);
    }

    public StoryCardFeedItem_ViewBinding(StoryCardFeedItem target2, View source) {
        this.target = target2;
        target2.imageView = (AirImageView) Utils.findRequiredViewAsType(source, C5709R.C5711id.story_image, "field 'imageView'", AirImageView.class);
        target2.storyCategory = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.story_category, "field 'storyCategory'", AirTextView.class);
        target2.titleTextView = (StoryTitleTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.story_title, "field 'titleTextView'", StoryTitleTextView.class);
        target2.authorImageView = (AirImageView) Utils.findRequiredViewAsType(source, C5709R.C5711id.story_author_image, "field 'authorImageView'", AirImageView.class);
        target2.likeCount = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.like_count, "field 'likeCount'", AirTextView.class);
        target2.commentCount = (AirTextView) Utils.findRequiredViewAsType(source, C5709R.C5711id.comment_count, "field 'commentCount'", AirTextView.class);
    }

    public void unbind() {
        StoryCardFeedItem target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.imageView = null;
        target2.storyCategory = null;
        target2.titleTextView = null;
        target2.authorImageView = null;
        target2.likeCount = null;
        target2.commentCount = null;
    }
}
