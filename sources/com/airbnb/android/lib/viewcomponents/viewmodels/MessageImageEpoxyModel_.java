package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnLongClickListener;
import com.airbnb.android.core.models.AttachmentImage;
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
import com.airbnb.p027n2.primitives.messaging.MessageImage;
import com.airbnb.p027n2.primitives.messaging.MessageImage.MessageImageOnLoadedListener;
import java.util.List;

public class MessageImageEpoxyModel_ extends MessageImageEpoxyModel implements GeneratedModel<MessageImage> {
    private OnModelBoundListener<MessageImageEpoxyModel_, MessageImage> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<MessageImageEpoxyModel_, MessageImage> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, MessageImage object, int position) {
        if (this.profileImageClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.profileImageClickListener).bind(holder, object);
        }
        if (this.imageAttachmentClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.imageAttachmentClickListener).bind(holder, object);
        }
        if (this.imageAttachmentLongClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.imageAttachmentLongClickListener).bind(holder, object);
        }
        if (this.reportTextClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.reportTextClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(MessageImage object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public MessageImageEpoxyModel_ onBind(OnModelBoundListener<MessageImageEpoxyModel_, MessageImage> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(MessageImage object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public MessageImageEpoxyModel_ onUnbind(OnModelUnboundListener<MessageImageEpoxyModel_, MessageImage> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public MessageImageEpoxyModel_ post(Post post) {
        onMutation();
        this.post = post;
        return this;
    }

    public Post post() {
        return this.post;
    }

    public MessageImageEpoxyModel_ statusText(CharSequence statusText) {
        onMutation();
        this.statusText = statusText;
        return this;
    }

    public CharSequence statusText() {
        return this.statusText;
    }

    public MessageImageEpoxyModel_ profileImageUrl(String profileImageUrl) {
        onMutation();
        this.profileImageUrl = profileImageUrl;
        return this;
    }

    public String profileImageUrl() {
        return this.profileImageUrl;
    }

    public MessageImageEpoxyModel_ profilePlaceholderText(CharSequence profilePlaceholderText) {
        onMutation();
        this.profilePlaceholderText = profilePlaceholderText;
        return this;
    }

    public CharSequence profilePlaceholderText() {
        return this.profilePlaceholderText;
    }

    public MessageImageEpoxyModel_ sendFailed(boolean sendFailed) {
        onMutation();
        this.sendFailed = sendFailed;
        return this;
    }

    public boolean sendFailed() {
        return this.sendFailed;
    }

    public MessageImageEpoxyModel_ reported(boolean reported) {
        onMutation();
        this.reported = reported;
        return this;
    }

    public boolean reported() {
        return this.reported;
    }

    public MessageImageEpoxyModel_ showReportLink(boolean showReportLink) {
        onMutation();
        this.showReportLink = showReportLink;
        return this;
    }

    public boolean showReportLink() {
        return this.showReportLink;
    }

    public MessageImageEpoxyModel_ hideProfilePhoto(boolean hideProfilePhoto) {
        onMutation();
        this.hideProfilePhoto = hideProfilePhoto;
        return this;
    }

    public boolean hideProfilePhoto() {
        return this.hideProfilePhoto;
    }

    public MessageImageEpoxyModel_ profileImageClickListener(OnModelClickListener<MessageImageEpoxyModel_, MessageImage> profileImageClickListener) {
        onMutation();
        if (profileImageClickListener == null) {
            this.profileImageClickListener = null;
        } else {
            this.profileImageClickListener = new WrappedEpoxyModelClickListener(this, profileImageClickListener);
        }
        return this;
    }

    public MessageImageEpoxyModel_ profileImageClickListener(OnClickListener profileImageClickListener) {
        onMutation();
        this.profileImageClickListener = profileImageClickListener;
        return this;
    }

    public OnClickListener profileImageClickListener() {
        return this.profileImageClickListener;
    }

    public MessageImageEpoxyModel_ imageAttachmentClickListener(OnModelClickListener<MessageImageEpoxyModel_, MessageImage> imageAttachmentClickListener) {
        onMutation();
        if (imageAttachmentClickListener == null) {
            this.imageAttachmentClickListener = null;
        } else {
            this.imageAttachmentClickListener = new WrappedEpoxyModelClickListener(this, imageAttachmentClickListener);
        }
        return this;
    }

    public MessageImageEpoxyModel_ imageAttachmentClickListener(OnClickListener imageAttachmentClickListener) {
        onMutation();
        this.imageAttachmentClickListener = imageAttachmentClickListener;
        return this;
    }

    public OnClickListener imageAttachmentClickListener() {
        return this.imageAttachmentClickListener;
    }

    public MessageImageEpoxyModel_ onImageLoadedListener(MessageImageOnLoadedListener onImageLoadedListener) {
        onMutation();
        this.onImageLoadedListener = onImageLoadedListener;
        return this;
    }

    public MessageImageOnLoadedListener onImageLoadedListener() {
        return this.onImageLoadedListener;
    }

    public MessageImageEpoxyModel_ contextMenuListener(OnCreateContextMenuListener contextMenuListener) {
        onMutation();
        this.contextMenuListener = contextMenuListener;
        return this;
    }

    public OnCreateContextMenuListener contextMenuListener() {
        return this.contextMenuListener;
    }

    public MessageImageEpoxyModel_ imageAttachmentLongClickListener(OnModelLongClickListener<MessageImageEpoxyModel_, MessageImage> imageAttachmentLongClickListener) {
        onMutation();
        if (imageAttachmentLongClickListener == null) {
            this.imageAttachmentLongClickListener = null;
        } else {
            this.imageAttachmentLongClickListener = new WrappedEpoxyModelClickListener(this, imageAttachmentLongClickListener);
        }
        return this;
    }

    public MessageImageEpoxyModel_ imageAttachmentLongClickListener(OnLongClickListener imageAttachmentLongClickListener) {
        onMutation();
        this.imageAttachmentLongClickListener = imageAttachmentLongClickListener;
        return this;
    }

    public OnLongClickListener imageAttachmentLongClickListener() {
        return this.imageAttachmentLongClickListener;
    }

    public MessageImageEpoxyModel_ reportTextClickListener(OnModelClickListener<MessageImageEpoxyModel_, MessageImage> reportTextClickListener) {
        onMutation();
        if (reportTextClickListener == null) {
            this.reportTextClickListener = null;
        } else {
            this.reportTextClickListener = new WrappedEpoxyModelClickListener(this, reportTextClickListener);
        }
        return this;
    }

    public MessageImageEpoxyModel_ reportTextClickListener(OnClickListener reportTextClickListener) {
        onMutation();
        this.reportTextClickListener = reportTextClickListener;
        return this;
    }

    public OnClickListener reportTextClickListener() {
        return this.reportTextClickListener;
    }

    public MessageImageEpoxyModel_ attachmentImageUrl(String attachmentImageUrl) {
        onMutation();
        this.attachmentImageUrl = attachmentImageUrl;
        return this;
    }

    public String attachmentImageUrl() {
        return this.attachmentImageUrl;
    }

    public MessageImageEpoxyModel_ imageWidthPx(int imageWidthPx) {
        onMutation();
        this.imageWidthPx = imageWidthPx;
        return this;
    }

    public int imageWidthPx() {
        return this.imageWidthPx;
    }

    public MessageImageEpoxyModel_ imageFileSize(int imageFileSize) {
        onMutation();
        this.imageFileSize = imageFileSize;
        return this;
    }

    public int imageFileSize() {
        return this.imageFileSize;
    }

    public MessageImageEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public MessageImageEpoxyModel_ sender() {
        super.sender();
        return this;
    }

    public MessageImageEpoxyModel_ receiver() {
        super.receiver();
        return this;
    }

    public MessageImageEpoxyModel_ attachmentImages(List<AttachmentImage> images) {
        super.attachmentImages(images);
        return this;
    }

    public MessageImageEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public MessageImageEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public MessageImageEpoxyModel_ m6179id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public MessageImageEpoxyModel_ m6184id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public MessageImageEpoxyModel_ m6180id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public MessageImageEpoxyModel_ m6181id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public MessageImageEpoxyModel_ m6183id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public MessageImageEpoxyModel_ m6182id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public MessageImageEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public MessageImageEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public MessageImageEpoxyModel_ show() {
        super.show();
        return this;
    }

    public MessageImageEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public MessageImageEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    public MessageImageEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.post = null;
        this.statusText = null;
        this.profileImageUrl = null;
        this.profilePlaceholderText = null;
        this.sendFailed = false;
        this.reported = false;
        this.showReportLink = false;
        this.hideProfilePhoto = false;
        this.profileImageClickListener = null;
        this.imageAttachmentClickListener = null;
        this.onImageLoadedListener = null;
        this.contextMenuListener = null;
        this.imageAttachmentLongClickListener = null;
        this.reportTextClickListener = null;
        this.attachmentImageUrl = null;
        this.imageWidthPx = 0;
        this.imageFileSize = 0;
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
        boolean z6;
        if (o == this) {
            return true;
        }
        if (!(o instanceof MessageImageEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        MessageImageEpoxyModel_ that = (MessageImageEpoxyModel_) o;
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
        if (this.statusText != null) {
            if (!this.statusText.equals(that.statusText)) {
                return false;
            }
        } else if (that.statusText != null) {
            return false;
        }
        if (this.profileImageUrl != null) {
            if (!this.profileImageUrl.equals(that.profileImageUrl)) {
                return false;
            }
        } else if (that.profileImageUrl != null) {
            return false;
        }
        if (this.profilePlaceholderText != null) {
            if (!this.profilePlaceholderText.equals(that.profilePlaceholderText)) {
                return false;
            }
        } else if (that.profilePlaceholderText != null) {
            return false;
        }
        if (this.sendFailed != that.sendFailed || this.reported != that.reported || this.showReportLink != that.showReportLink || this.hideProfilePhoto != that.hideProfilePhoto) {
            return false;
        }
        if ((this.profileImageClickListener == null) != (that.profileImageClickListener == null)) {
            return false;
        }
        if (this.imageAttachmentClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.imageAttachmentClickListener == null)) {
            return false;
        }
        if (this.onImageLoadedListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.onImageLoadedListener == null)) {
            return false;
        }
        if (this.contextMenuListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.contextMenuListener == null)) {
            return false;
        }
        if (this.imageAttachmentLongClickListener == null) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z5 != (that.imageAttachmentLongClickListener == null)) {
            return false;
        }
        if (this.reportTextClickListener == null) {
            z6 = true;
        } else {
            z6 = false;
        }
        if (z6 != (that.reportTextClickListener == null)) {
            return false;
        }
        if (this.attachmentImageUrl != null) {
            if (!this.attachmentImageUrl.equals(that.attachmentImageUrl)) {
                return false;
            }
        } else if (that.attachmentImageUrl != null) {
            return false;
        }
        if (this.imageWidthPx != that.imageWidthPx || this.imageFileSize != that.imageFileSize) {
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
        if (this.statusText != null) {
            i3 = this.statusText.hashCode();
        } else {
            i3 = 0;
        }
        int i21 = (i20 + i3) * 31;
        if (this.profileImageUrl != null) {
            i4 = this.profileImageUrl.hashCode();
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
        if (this.sendFailed) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i24 = (i23 + i6) * 31;
        if (this.reported) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i25 = (i24 + i7) * 31;
        if (this.showReportLink) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i26 = (i25 + i8) * 31;
        if (this.hideProfilePhoto) {
            i9 = 1;
        } else {
            i9 = 0;
        }
        int i27 = (i26 + i9) * 31;
        if (this.profileImageClickListener != null) {
            i10 = 1;
        } else {
            i10 = 0;
        }
        int i28 = (i27 + i10) * 31;
        if (this.imageAttachmentClickListener != null) {
            i11 = 1;
        } else {
            i11 = 0;
        }
        int i29 = (i28 + i11) * 31;
        if (this.onImageLoadedListener != null) {
            i12 = 1;
        } else {
            i12 = 0;
        }
        int i30 = (i29 + i12) * 31;
        if (this.contextMenuListener != null) {
            i13 = 1;
        } else {
            i13 = 0;
        }
        int i31 = (i30 + i13) * 31;
        if (this.imageAttachmentLongClickListener != null) {
            i14 = 1;
        } else {
            i14 = 0;
        }
        int i32 = (i31 + i14) * 31;
        if (this.reportTextClickListener == null) {
            i17 = 0;
        }
        int i33 = (i32 + i17) * 31;
        if (this.attachmentImageUrl != null) {
            i15 = this.attachmentImageUrl.hashCode();
        } else {
            i15 = 0;
        }
        int i34 = (((((i33 + i15) * 31) + this.imageWidthPx) * 31) + this.imageFileSize) * 31;
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
        return "MessageImageEpoxyModel_{post=" + this.post + ", statusText=" + this.statusText + ", profileImageUrl=" + this.profileImageUrl + ", profilePlaceholderText=" + this.profilePlaceholderText + ", sendFailed=" + this.sendFailed + ", reported=" + this.reported + ", showReportLink=" + this.showReportLink + ", hideProfilePhoto=" + this.hideProfilePhoto + ", profileImageClickListener=" + this.profileImageClickListener + ", imageAttachmentClickListener=" + this.imageAttachmentClickListener + ", onImageLoadedListener=" + this.onImageLoadedListener + ", contextMenuListener=" + this.contextMenuListener + ", imageAttachmentLongClickListener=" + this.imageAttachmentLongClickListener + ", reportTextClickListener=" + this.reportTextClickListener + ", attachmentImageUrl=" + this.attachmentImageUrl + ", imageWidthPx=" + this.imageWidthPx + ", imageFileSize=" + this.imageFileSize + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
