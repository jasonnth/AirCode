package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.content.Context;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.models.AttachmentImage;
import com.airbnb.android.core.models.Post;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.primitives.messaging.MessageImage;
import com.airbnb.p027n2.primitives.messaging.MessageImage.MessageImageOnLoadedListener;
import java.util.Collection;
import java.util.List;

public abstract class MessageImageEpoxyModel extends AirEpoxyModel<MessageImage> {
    String attachmentImageUrl;
    OnCreateContextMenuListener contextMenuListener;
    boolean hideProfilePhoto = false;
    OnClickListener imageAttachmentClickListener;
    OnLongClickListener imageAttachmentLongClickListener;
    int imageFileSize;
    int imageWidthPx;
    MessageImageOnLoadedListener onImageLoadedListener;
    Post post;
    OnClickListener profileImageClickListener;
    String profileImageUrl;
    CharSequence profilePlaceholderText;
    OnClickListener reportTextClickListener;
    boolean reported = false;
    boolean sendFailed = false;
    boolean showReportLink = false;
    CharSequence statusText;

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        throw new IllegalStateException("sender() or receiver() must be called on MessageImageEpoxyModel object");
    }

    public MessageImageEpoxyModel sender() {
        layout(C0880R.layout.view_holder_message_image_sender);
        return this;
    }

    public MessageImageEpoxyModel receiver() {
        layout(C0880R.layout.view_holder_message_image_receiver);
        return this;
    }

    public void bind(MessageImage message) {
        super.bind(message);
        Context context = message.getContext();
        message.setTag(this.post);
        message.setStatusText(this.statusText);
        message.setPlaceholderText(this.hideProfilePhoto ? this.profilePlaceholderText : null);
        if (this.hideProfilePhoto) {
            message.setProfileDrawable(C0880R.C0881drawable.no_profile_image);
        } else {
            message.setProfileUrl(this.profileImageUrl);
        }
        message.setProfileClickLink(this.profileImageClickListener);
        message.setMessageState(this.sendFailed);
        message.setImageAttachmentView(this.attachmentImageUrl, this.imageAttachmentClickListener, this.imageAttachmentLongClickListener, this.imageWidthPx);
        message.setReported(this.reported);
        message.showReported(this.showReportLink);
        message.setReportedText(this.reported ? context.getString(C0880R.string.show_image_text) : context.getString(C0880R.string.hide_image_text));
        message.setOnImageLoadedListener(this.onImageLoadedListener);
        message.setOnCreateContextMenuListener(this.contextMenuListener);
        message.setReportClickLink(this.reportTextClickListener);
        message.setImageFileSize(this.imageFileSize);
        message.setPostId(mo11715id());
    }

    public void unbind(MessageImage message) {
        super.unbind(message);
        message.setOnClickListener(null);
        message.setProfileClickLink(null);
    }

    public MessageImageEpoxyModel attachmentImages(List<AttachmentImage> images) {
        if (!ListUtils.isEmpty((Collection<?>) images)) {
            this.attachmentImageUrl = ((AttachmentImage) images.get(0)).getUrl();
            this.imageWidthPx = ((AttachmentImage) images.get(0)).getWidthPx();
            this.imageFileSize = ((AttachmentImage) images.get(0)).getFileSize();
        }
        return this;
    }
}
