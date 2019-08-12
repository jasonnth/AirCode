package com.airbnb.p027n2.components;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.CircleCollageImageView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.daimajia.swipe.SwipeLayout;

/* renamed from: com.airbnb.n2.components.ThreadPreviewRow_ViewBinding */
public class ThreadPreviewRow_ViewBinding implements Unbinder {
    private ThreadPreviewRow target;

    public ThreadPreviewRow_ViewBinding(ThreadPreviewRow target2, View source) {
        this.target = target2;
        target2.swipeLayout = (SwipeLayout) Utils.findRequiredViewAsType(source, R.id.swipe_layout, "field 'swipeLayout'", SwipeLayout.class);
        target2.swipeTextView = (TextView) Utils.findRequiredViewAsType(source, R.id.archive_text, "field 'swipeTextView'", TextView.class);
        target2.threadPreviewView = Utils.findRequiredView(source, R.id.thread_preview, "field 'threadPreviewView'");
        target2.imageView = (CircleCollageImageView) Utils.findRequiredViewAsType(source, R.id.image, "field 'imageView'", CircleCollageImageView.class);
        target2.squareImageView = (AirImageView) Utils.findRequiredViewAsType(source, R.id.image_square, "field 'squareImageView'", AirImageView.class);
        target2.textProfilePhotoPlaceholder = (AirTextView) Utils.findRequiredViewAsType(source, R.id.thumbnail_text_placeholder, "field 'textProfilePhotoPlaceholder'", AirTextView.class);
        target2.unreadIndicator = (ImageView) Utils.findRequiredViewAsType(source, R.id.unread_indicator, "field 'unreadIndicator'", ImageView.class);
        target2.timeAgoText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.time_ago, "field 'timeAgoText'", AirTextView.class);
        target2.titleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.title_text, "field 'titleText'", AirTextView.class);
        target2.subtitleText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.subtitle_text, "field 'subtitleText'", AirTextView.class);
        target2.thirdRowText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.third_row_text, "field 'thirdRowText'", AirTextView.class);
        target2.fourthRowText = (AirTextView) Utils.findRequiredViewAsType(source, R.id.fourth_row_text, "field 'fourthRowText'", AirTextView.class);
        target2.actionButton = (AirButton) Utils.findRequiredViewAsType(source, R.id.action_button, "field 'actionButton'", AirButton.class);
    }

    public void unbind() {
        ThreadPreviewRow target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.swipeLayout = null;
        target2.swipeTextView = null;
        target2.threadPreviewView = null;
        target2.imageView = null;
        target2.squareImageView = null;
        target2.textProfilePhotoPlaceholder = null;
        target2.unreadIndicator = null;
        target2.timeAgoText = null;
        target2.titleText = null;
        target2.subtitleText = null;
        target2.thirdRowText = null;
        target2.fourthRowText = null;
        target2.actionButton = null;
    }
}
