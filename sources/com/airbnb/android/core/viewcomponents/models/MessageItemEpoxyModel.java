package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.Post;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.primitives.messaging.MessageItem;

public abstract class MessageItemEpoxyModel extends AirEpoxyModel<MessageItem> {
    OnClickListener clickListener;
    OnCreateContextMenuListener contextMenuListener;
    boolean hideProfilePhoto = false;
    OnLongClickListener longClickListener;
    CharSequence messageText;
    Post post;
    OnClickListener profileImageClickListener;
    int profileImageRes;
    String profileImageUrl;
    CharSequence profilePlaceholderText;
    OnClickListener reportTextClickListener;
    boolean reported = false;
    boolean sendFailed = false;
    boolean showReportLink = false;
    CharSequence statusText;
    boolean withLinks = false;

    public void bind(MessageItem message) {
        boolean z;
        boolean z2 = true;
        super.bind(message);
        Context context = message.getContext();
        message.setTag(this.post);
        message.setMessageText(this.reported ? context.getString(C0716R.string.flagged_post_text) : this.messageText);
        message.setStatusText(this.statusText);
        message.setReported(this.reported);
        message.setReportLinkText(this.reported ? context.getString(C0716R.string.show_message_text) : context.getString(C0716R.string.hide_message_text));
        message.showReportLink(this.showReportLink);
        message.setPlaceholderText(this.hideProfilePhoto ? this.profilePlaceholderText : null);
        if (this.hideProfilePhoto) {
            message.setProfileDrawable(C0716R.C0717drawable.no_profile_image);
        } else if (this.profileImageRes != 0) {
            message.setProfileDrawable(this.profileImageRes);
        } else {
            message.setProfileUrl(this.profileImageUrl);
        }
        message.setOnClickListener(this.clickListener);
        if (this.clickListener != null) {
            z = true;
        } else {
            z = false;
        }
        message.setClickable(z);
        message.setOnLongClickListener(this.longClickListener);
        if (this.longClickListener == null) {
            z2 = false;
        }
        message.setLongClickable(z2);
        message.setProfileClickLink(this.profileImageClickListener);
        message.setMessageState(this.sendFailed);
        message.setReportClickLink(this.reportTextClickListener);
        message.setOnCreateContextMenuListener(this.contextMenuListener);
        if (this.withLinks) {
            message.setLinksOnMessage();
        }
    }

    public void unbind(MessageItem message) {
        super.unbind(message);
        message.setOnClickListener(null);
        message.setOnLongClickListener(null);
        message.setOnCreateContextMenuListener(null);
        message.setProfileClickLink(null);
    }

    public int getDefaultLayout() {
        throw new IllegalStateException("senderNoTail(), receiverNoTail(), senderWithTail, or receiverWithTail() must be called on MessageItemEpoxyModel object");
    }

    public MessageItemEpoxyModel senderNoTail() {
        layout(C0716R.layout.view_holder_message_item_sender_no_tail);
        return this;
    }

    public MessageItemEpoxyModel receiverNoTail() {
        layout(C0716R.layout.view_holder_message_item_receiver_no_tail);
        return this;
    }

    public MessageItemEpoxyModel senderWithTail() {
        layout(C0716R.layout.view_holder_message_item_sender_with_tail);
        return this;
    }

    public MessageItemEpoxyModel receiverWithTail() {
        layout(C0716R.layout.view_holder_message_item_receiver_with_tail);
        return this;
    }
}
