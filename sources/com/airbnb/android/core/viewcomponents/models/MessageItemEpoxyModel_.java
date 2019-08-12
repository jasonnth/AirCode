package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.models.Post;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelLongClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.airbnb.p027n2.primitives.messaging.MessageItem;

public class MessageItemEpoxyModel_ extends MessageItemEpoxyModel implements GeneratedModel<MessageItem> {
    private OnModelBoundListener<MessageItemEpoxyModel_, MessageItem> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<MessageItemEpoxyModel_, MessageItem> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, MessageItem object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
        if (this.profileImageClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.profileImageClickListener).bind(holder, object);
        }
        if (this.reportTextClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.reportTextClickListener).bind(holder, object);
        }
        if (this.longClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.longClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(MessageItem object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public MessageItemEpoxyModel_ onBind(OnModelBoundListener<MessageItemEpoxyModel_, MessageItem> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(MessageItem object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public MessageItemEpoxyModel_ onUnbind(OnModelUnboundListener<MessageItemEpoxyModel_, MessageItem> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public MessageItemEpoxyModel_ post(Post post) {
        onMutation();
        this.post = post;
        return this;
    }

    public Post post() {
        return this.post;
    }

    public MessageItemEpoxyModel_ messageText(CharSequence messageText) {
        onMutation();
        this.messageText = messageText;
        return this;
    }

    public CharSequence messageText() {
        return this.messageText;
    }

    public MessageItemEpoxyModel_ statusText(CharSequence statusText) {
        onMutation();
        this.statusText = statusText;
        return this;
    }

    public CharSequence statusText() {
        return this.statusText;
    }

    public MessageItemEpoxyModel_ profilePlaceholderText(CharSequence profilePlaceholderText) {
        onMutation();
        this.profilePlaceholderText = profilePlaceholderText;
        return this;
    }

    public CharSequence profilePlaceholderText() {
        return this.profilePlaceholderText;
    }

    public MessageItemEpoxyModel_ profileImageUrl(String profileImageUrl) {
        onMutation();
        this.profileImageUrl = profileImageUrl;
        return this;
    }

    public String profileImageUrl() {
        return this.profileImageUrl;
    }

    public MessageItemEpoxyModel_ profileImageRes(int profileImageRes) {
        onMutation();
        this.profileImageRes = profileImageRes;
        return this;
    }

    public int profileImageRes() {
        return this.profileImageRes;
    }

    public MessageItemEpoxyModel_ sendFailed(boolean sendFailed) {
        onMutation();
        this.sendFailed = sendFailed;
        return this;
    }

    public boolean sendFailed() {
        return this.sendFailed;
    }

    public MessageItemEpoxyModel_ withLinks(boolean withLinks) {
        onMutation();
        this.withLinks = withLinks;
        return this;
    }

    public boolean withLinks() {
        return this.withLinks;
    }

    public MessageItemEpoxyModel_ reported(boolean reported) {
        onMutation();
        this.reported = reported;
        return this;
    }

    public boolean reported() {
        return this.reported;
    }

    public MessageItemEpoxyModel_ showReportLink(boolean showReportLink) {
        onMutation();
        this.showReportLink = showReportLink;
        return this;
    }

    public boolean showReportLink() {
        return this.showReportLink;
    }

    public MessageItemEpoxyModel_ hideProfilePhoto(boolean hideProfilePhoto) {
        onMutation();
        this.hideProfilePhoto = hideProfilePhoto;
        return this;
    }

    public boolean hideProfilePhoto() {
        return this.hideProfilePhoto;
    }

    public MessageItemEpoxyModel_ clickListener(OnModelClickListener<MessageItemEpoxyModel_, MessageItem> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public MessageItemEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public MessageItemEpoxyModel_ profileImageClickListener(OnModelClickListener<MessageItemEpoxyModel_, MessageItem> profileImageClickListener) {
        onMutation();
        if (profileImageClickListener == null) {
            this.profileImageClickListener = null;
        } else {
            this.profileImageClickListener = new WrappedEpoxyModelClickListener(this, profileImageClickListener);
        }
        return this;
    }

    public MessageItemEpoxyModel_ profileImageClickListener(OnClickListener profileImageClickListener) {
        onMutation();
        this.profileImageClickListener = profileImageClickListener;
        return this;
    }

    public OnClickListener profileImageClickListener() {
        return this.profileImageClickListener;
    }

    public MessageItemEpoxyModel_ reportTextClickListener(OnModelClickListener<MessageItemEpoxyModel_, MessageItem> reportTextClickListener) {
        onMutation();
        if (reportTextClickListener == null) {
            this.reportTextClickListener = null;
        } else {
            this.reportTextClickListener = new WrappedEpoxyModelClickListener(this, reportTextClickListener);
        }
        return this;
    }

    public MessageItemEpoxyModel_ reportTextClickListener(OnClickListener reportTextClickListener) {
        onMutation();
        this.reportTextClickListener = reportTextClickListener;
        return this;
    }

    public OnClickListener reportTextClickListener() {
        return this.reportTextClickListener;
    }

    public MessageItemEpoxyModel_ longClickListener(OnModelLongClickListener<MessageItemEpoxyModel_, MessageItem> longClickListener) {
        onMutation();
        if (longClickListener == null) {
            this.longClickListener = null;
        } else {
            this.longClickListener = new WrappedEpoxyModelClickListener(this, longClickListener);
        }
        return this;
    }

    public MessageItemEpoxyModel_ longClickListener(OnLongClickListener longClickListener) {
        onMutation();
        this.longClickListener = longClickListener;
        return this;
    }

    public OnLongClickListener longClickListener() {
        return this.longClickListener;
    }

    public MessageItemEpoxyModel_ contextMenuListener(OnCreateContextMenuListener contextMenuListener) {
        onMutation();
        this.contextMenuListener = contextMenuListener;
        return this;
    }

    public OnCreateContextMenuListener contextMenuListener() {
        return this.contextMenuListener;
    }

    public MessageItemEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public MessageItemEpoxyModel_ senderNoTail() {
        super.senderNoTail();
        return this;
    }

    public MessageItemEpoxyModel_ receiverNoTail() {
        super.receiverNoTail();
        return this;
    }

    public MessageItemEpoxyModel_ senderWithTail() {
        super.senderWithTail();
        return this;
    }

    public MessageItemEpoxyModel_ receiverWithTail() {
        super.receiverWithTail();
        return this;
    }

    public MessageItemEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public MessageItemEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public MessageItemEpoxyModel_ m5146id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public MessageItemEpoxyModel_ m5151id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public MessageItemEpoxyModel_ m5147id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public MessageItemEpoxyModel_ m5148id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public MessageItemEpoxyModel_ m5150id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public MessageItemEpoxyModel_ m5149id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public MessageItemEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public MessageItemEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public MessageItemEpoxyModel_ show() {
        super.show();
        return this;
    }

    public MessageItemEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public MessageItemEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    public MessageItemEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.post = null;
        this.messageText = null;
        this.statusText = null;
        this.profilePlaceholderText = null;
        this.profileImageUrl = null;
        this.profileImageRes = 0;
        this.sendFailed = false;
        this.withLinks = false;
        this.reported = false;
        this.showReportLink = false;
        this.hideProfilePhoto = false;
        this.clickListener = null;
        this.profileImageClickListener = null;
        this.reportTextClickListener = null;
        this.longClickListener = null;
        this.contextMenuListener = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        if (o == this) {
            return true;
        }
        if (!(o instanceof MessageItemEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        MessageItemEpoxyModel_ that = (MessageItemEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.post != null) {
            if (!this.post.equals(that.post)) {
                return false;
            }
        } else if (that.post != null) {
            return false;
        }
        if (this.messageText != null) {
            if (!this.messageText.equals(that.messageText)) {
                return false;
            }
        } else if (that.messageText != null) {
            return false;
        }
        if (this.statusText != null) {
            if (!this.statusText.equals(that.statusText)) {
                return false;
            }
        } else if (that.statusText != null) {
            return false;
        }
        if (this.profilePlaceholderText != null) {
            if (!this.profilePlaceholderText.equals(that.profilePlaceholderText)) {
                return false;
            }
        } else if (that.profilePlaceholderText != null) {
            return false;
        }
        if (this.profileImageUrl != null) {
            if (!this.profileImageUrl.equals(that.profileImageUrl)) {
                return false;
            }
        } else if (that.profileImageUrl != null) {
            return false;
        }
        if (this.profileImageRes != that.profileImageRes || this.sendFailed != that.sendFailed || this.withLinks != that.withLinks || this.reported != that.reported || this.showReportLink != that.showReportLink || this.hideProfilePhoto != that.hideProfilePhoto) {
            return false;
        }
        if ((this.clickListener == null) != (that.clickListener == null)) {
            return false;
        }
        if (this.profileImageClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.profileImageClickListener == null)) {
            return false;
        }
        if (this.reportTextClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.reportTextClickListener == null)) {
            return false;
        }
        if (this.longClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.longClickListener == null)) {
            return false;
        }
        if (this.contextMenuListener == null) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z5 != (that.contextMenuListener == null)) {
            return false;
        }
        if (this.showDivider != null) {
            if (!this.showDivider.equals(that.showDivider)) {
                return false;
            }
        } else if (that.showDivider != null) {
            return false;
        }
        if (this.numCarouselItemsShown != null) {
            if (!this.numCarouselItemsShown.equals(that.numCarouselItemsShown)) {
                return false;
            }
        } else if (that.numCarouselItemsShown != null) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17 = 1;
        int i18 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i19 = (hashCode + i) * 31;
        if (this.post != null) {
            i2 = this.post.hashCode();
        } else {
            i2 = 0;
        }
        int i20 = (i19 + i2) * 31;
        if (this.messageText != null) {
            i3 = this.messageText.hashCode();
        } else {
            i3 = 0;
        }
        int i21 = (i20 + i3) * 31;
        if (this.statusText != null) {
            i4 = this.statusText.hashCode();
        } else {
            i4 = 0;
        }
        int i22 = (i21 + i4) * 31;
        if (this.profilePlaceholderText != null) {
            i5 = this.profilePlaceholderText.hashCode();
        } else {
            i5 = 0;
        }
        int i23 = (i22 + i5) * 31;
        if (this.profileImageUrl != null) {
            i6 = this.profileImageUrl.hashCode();
        } else {
            i6 = 0;
        }
        int i24 = (((i23 + i6) * 31) + this.profileImageRes) * 31;
        if (this.sendFailed) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i25 = (i24 + i7) * 31;
        if (this.withLinks) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i26 = (i25 + i8) * 31;
        if (this.reported) {
            i9 = 1;
        } else {
            i9 = 0;
        }
        int i27 = (i26 + i9) * 31;
        if (this.showReportLink) {
            i10 = 1;
        } else {
            i10 = 0;
        }
        int i28 = (i27 + i10) * 31;
        if (this.hideProfilePhoto) {
            i11 = 1;
        } else {
            i11 = 0;
        }
        int i29 = (i28 + i11) * 31;
        if (this.clickListener != null) {
            i12 = 1;
        } else {
            i12 = 0;
        }
        int i30 = (i29 + i12) * 31;
        if (this.profileImageClickListener != null) {
            i13 = 1;
        } else {
            i13 = 0;
        }
        int i31 = (i30 + i13) * 31;
        if (this.reportTextClickListener != null) {
            i14 = 1;
        } else {
            i14 = 0;
        }
        int i32 = (i31 + i14) * 31;
        if (this.longClickListener != null) {
            i15 = 1;
        } else {
            i15 = 0;
        }
        int i33 = (i32 + i15) * 31;
        if (this.contextMenuListener == null) {
            i17 = 0;
        }
        int i34 = (i33 + i17) * 31;
        if (this.showDivider != null) {
            i16 = this.showDivider.hashCode();
        } else {
            i16 = 0;
        }
        int i35 = (i34 + i16) * 31;
        if (this.numCarouselItemsShown != null) {
            i18 = this.numCarouselItemsShown.hashCode();
        }
        return i35 + i18;
    }

    public String toString() {
        return "MessageItemEpoxyModel_{post=" + this.post + ", messageText=" + this.messageText + ", statusText=" + this.statusText + ", profilePlaceholderText=" + this.profilePlaceholderText + ", profileImageUrl=" + this.profileImageUrl + ", profileImageRes=" + this.profileImageRes + ", sendFailed=" + this.sendFailed + ", withLinks=" + this.withLinks + ", reported=" + this.reported + ", showReportLink=" + this.showReportLink + ", hideProfilePhoto=" + this.hideProfilePhoto + ", clickListener=" + this.clickListener + ", profileImageClickListener=" + this.profileImageClickListener + ", reportTextClickListener=" + this.reportTextClickListener + ", longClickListener=" + this.longClickListener + ", contextMenuListener=" + this.contextMenuListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
