package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelLongClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.ThreadPreviewRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import com.daimajia.swipe.SimpleSwipeListener;
import java.util.List;

public class ThreadPreviewEpoxyModel_ extends ThreadPreviewEpoxyModel implements GeneratedModel<ThreadPreviewRow> {
    private OnModelBoundListener<ThreadPreviewEpoxyModel_, ThreadPreviewRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ThreadPreviewEpoxyModel_, ThreadPreviewRow> onModelUnboundListener_epoxyGeneratedModel;

    public ThreadPreviewEpoxyModel_(long id) {
        super(id);
    }

    public void handlePreBind(EpoxyViewHolder holder, ThreadPreviewRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
        if (this.longClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.longClickListener).bind(holder, object);
        }
        if (this.actionButtonClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.actionButtonClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(ThreadPreviewRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ThreadPreviewEpoxyModel_ onBind(OnModelBoundListener<ThreadPreviewEpoxyModel_, ThreadPreviewRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ThreadPreviewRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ThreadPreviewEpoxyModel_ onUnbind(OnModelUnboundListener<ThreadPreviewEpoxyModel_, ThreadPreviewRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ThreadPreviewEpoxyModel_ timeAgo(AirDateTime timeAgo) {
        onMutation();
        this.timeAgo = timeAgo;
        return this;
    }

    public AirDateTime timeAgo() {
        return this.timeAgo;
    }

    public ThreadPreviewEpoxyModel_ showUnread(boolean showUnread) {
        onMutation();
        this.showUnread = showUnread;
        return this;
    }

    public boolean showUnread() {
        return this.showUnread;
    }

    public ThreadPreviewEpoxyModel_ showActionButton(boolean showActionButton) {
        onMutation();
        this.showActionButton = showActionButton;
        return this;
    }

    public boolean showActionButton() {
        return this.showActionButton;
    }

    public ThreadPreviewEpoxyModel_ multipleLineTitle(boolean multipleLineTitle) {
        onMutation();
        this.multipleLineTitle = multipleLineTitle;
        return this;
    }

    public boolean multipleLineTitle() {
        return this.multipleLineTitle;
    }

    public ThreadPreviewEpoxyModel_ swipeEnabled(boolean swipeEnabled) {
        onMutation();
        this.swipeEnabled = swipeEnabled;
        return this;
    }

    public boolean swipeEnabled() {
        return this.swipeEnabled;
    }

    public ThreadPreviewEpoxyModel_ hideProfilePhoto(boolean hideProfilePhoto) {
        onMutation();
        this.hideProfilePhoto = hideProfilePhoto;
        return this;
    }

    public boolean hideProfilePhoto() {
        return this.hideProfilePhoto;
    }

    public ThreadPreviewEpoxyModel_ shouldShowSquareImage(boolean shouldShowSquareImage) {
        onMutation();
        this.shouldShowSquareImage = shouldShowSquareImage;
        return this;
    }

    public boolean shouldShowSquareImage() {
        return this.shouldShowSquareImage;
    }

    public ThreadPreviewEpoxyModel_ imageUrls(List<String> imageUrls) {
        onMutation();
        this.imageUrls = imageUrls;
        return this;
    }

    public List<String> imageUrls() {
        return this.imageUrls;
    }

    public ThreadPreviewEpoxyModel_ imageRes(int imageRes) {
        onMutation();
        this.imageRes = imageRes;
        return this;
    }

    public int imageRes() {
        return this.imageRes;
    }

    public ThreadPreviewEpoxyModel_ titleText(CharSequence titleText) {
        onMutation();
        this.titleText = titleText;
        return this;
    }

    public CharSequence titleText() {
        return this.titleText;
    }

    public ThreadPreviewEpoxyModel_ profilePlaceholderText(CharSequence profilePlaceholderText) {
        onMutation();
        this.profilePlaceholderText = profilePlaceholderText;
        return this;
    }

    public CharSequence profilePlaceholderText() {
        return this.profilePlaceholderText;
    }

    public ThreadPreviewEpoxyModel_ thirdRowText(CharSequence thirdRowText) {
        onMutation();
        this.thirdRowText = thirdRowText;
        return this;
    }

    public CharSequence thirdRowText() {
        return this.thirdRowText;
    }

    public ThreadPreviewEpoxyModel_ fourthRowText(CharSequence fourthRowText) {
        onMutation();
        this.fourthRowText = fourthRowText;
        return this;
    }

    public CharSequence fourthRowText() {
        return this.fourthRowText;
    }

    public ThreadPreviewEpoxyModel_ actionButtonText(CharSequence actionButtonText) {
        onMutation();
        this.actionButtonText = actionButtonText;
        return this;
    }

    public CharSequence actionButtonText() {
        return this.actionButtonText;
    }

    public ThreadPreviewEpoxyModel_ actionButtonTextRes(int actionButtonTextRes) {
        onMutation();
        this.actionButtonTextRes = actionButtonTextRes;
        return this;
    }

    public int actionButtonTextRes() {
        return this.actionButtonTextRes;
    }

    public ThreadPreviewEpoxyModel_ swipeTextRes(int swipeTextRes) {
        onMutation();
        this.swipeTextRes = swipeTextRes;
        return this;
    }

    public int swipeTextRes() {
        return this.swipeTextRes;
    }

    public ThreadPreviewEpoxyModel_ subtitleText(CharSequence subtitleText) {
        onMutation();
        this.subtitleText = subtitleText;
        return this;
    }

    public CharSequence subtitleText() {
        return this.subtitleText;
    }

    public ThreadPreviewEpoxyModel_ clickListener(OnModelClickListener<ThreadPreviewEpoxyModel_, ThreadPreviewRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public ThreadPreviewEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public ThreadPreviewEpoxyModel_ longClickListener(OnModelLongClickListener<ThreadPreviewEpoxyModel_, ThreadPreviewRow> longClickListener) {
        onMutation();
        if (longClickListener == null) {
            this.longClickListener = null;
        } else {
            this.longClickListener = new WrappedEpoxyModelClickListener(this, longClickListener);
        }
        return this;
    }

    public ThreadPreviewEpoxyModel_ longClickListener(OnLongClickListener longClickListener) {
        onMutation();
        this.longClickListener = longClickListener;
        return this;
    }

    public OnLongClickListener longClickListener() {
        return this.longClickListener;
    }

    public ThreadPreviewEpoxyModel_ actionButtonClickListener(OnModelClickListener<ThreadPreviewEpoxyModel_, ThreadPreviewRow> actionButtonClickListener) {
        onMutation();
        if (actionButtonClickListener == null) {
            this.actionButtonClickListener = null;
        } else {
            this.actionButtonClickListener = new WrappedEpoxyModelClickListener(this, actionButtonClickListener);
        }
        return this;
    }

    public ThreadPreviewEpoxyModel_ actionButtonClickListener(OnClickListener actionButtonClickListener) {
        onMutation();
        this.actionButtonClickListener = actionButtonClickListener;
        return this;
    }

    public OnClickListener actionButtonClickListener() {
        return this.actionButtonClickListener;
    }

    public ThreadPreviewEpoxyModel_ swipeListener(SimpleSwipeListener swipeListener) {
        onMutation();
        this.swipeListener = swipeListener;
        return this;
    }

    public SimpleSwipeListener swipeListener() {
        return this.swipeListener;
    }

    public ThreadPreviewEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ThreadPreviewEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ThreadPreviewEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ThreadPreviewEpoxyModel_ m6275id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ThreadPreviewEpoxyModel_ m6280id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ThreadPreviewEpoxyModel_ m6276id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ThreadPreviewEpoxyModel_ m6277id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ThreadPreviewEpoxyModel_ m6279id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ThreadPreviewEpoxyModel_ m6278id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ThreadPreviewEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ThreadPreviewEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ThreadPreviewEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ThreadPreviewEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ThreadPreviewEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0880R.layout.view_holder_thread_preview_row;
    }

    public ThreadPreviewEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.timeAgo = null;
        this.showUnread = false;
        this.showActionButton = false;
        this.multipleLineTitle = false;
        this.swipeEnabled = false;
        this.hideProfilePhoto = false;
        this.shouldShowSquareImage = false;
        this.imageUrls = null;
        this.imageRes = 0;
        this.titleText = null;
        this.profilePlaceholderText = null;
        this.thirdRowText = null;
        this.fourthRowText = null;
        this.actionButtonText = null;
        this.actionButtonTextRes = 0;
        this.swipeTextRes = 0;
        this.subtitleText = null;
        this.clickListener = null;
        this.longClickListener = null;
        this.actionButtonClickListener = null;
        this.swipeListener = null;
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
        if (!(o instanceof ThreadPreviewEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ThreadPreviewEpoxyModel_ that = (ThreadPreviewEpoxyModel_) o;
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
        if (this.timeAgo != null) {
            if (!this.timeAgo.equals(that.timeAgo)) {
                return false;
            }
        } else if (that.timeAgo != null) {
            return false;
        }
        if (this.showUnread != that.showUnread || this.showActionButton != that.showActionButton || this.multipleLineTitle != that.multipleLineTitle || this.swipeEnabled != that.swipeEnabled || this.hideProfilePhoto != that.hideProfilePhoto || this.shouldShowSquareImage != that.shouldShowSquareImage) {
            return false;
        }
        if (this.imageUrls != null) {
            if (!this.imageUrls.equals(that.imageUrls)) {
                return false;
            }
        } else if (that.imageUrls != null) {
            return false;
        }
        if (this.imageRes != that.imageRes) {
            return false;
        }
        if (this.titleText != null) {
            if (!this.titleText.equals(that.titleText)) {
                return false;
            }
        } else if (that.titleText != null) {
            return false;
        }
        if (this.profilePlaceholderText != null) {
            if (!this.profilePlaceholderText.equals(that.profilePlaceholderText)) {
                return false;
            }
        } else if (that.profilePlaceholderText != null) {
            return false;
        }
        if (this.thirdRowText != null) {
            if (!this.thirdRowText.equals(that.thirdRowText)) {
                return false;
            }
        } else if (that.thirdRowText != null) {
            return false;
        }
        if (this.fourthRowText != null) {
            if (!this.fourthRowText.equals(that.fourthRowText)) {
                return false;
            }
        } else if (that.fourthRowText != null) {
            return false;
        }
        if (this.actionButtonText != null) {
            if (!this.actionButtonText.equals(that.actionButtonText)) {
                return false;
            }
        } else if (that.actionButtonText != null) {
            return false;
        }
        if (this.actionButtonTextRes != that.actionButtonTextRes || this.swipeTextRes != that.swipeTextRes) {
            return false;
        }
        if (this.subtitleText != null) {
            if (!this.subtitleText.equals(that.subtitleText)) {
                return false;
            }
        } else if (that.subtitleText != null) {
            return false;
        }
        if (this.clickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.clickListener == null)) {
            return false;
        }
        if (this.longClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.longClickListener == null)) {
            return false;
        }
        if (this.actionButtonClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.actionButtonClickListener == null)) {
            return false;
        }
        if (this.swipeListener == null) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (z5 != (that.swipeListener == null)) {
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
        int i17;
        int i18;
        int i19;
        int i20 = 1;
        int i21 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i22 = (hashCode + i) * 31;
        if (this.timeAgo != null) {
            i2 = this.timeAgo.hashCode();
        } else {
            i2 = 0;
        }
        int i23 = (i22 + i2) * 31;
        if (this.showUnread) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i24 = (i23 + i3) * 31;
        if (this.showActionButton) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i25 = (i24 + i4) * 31;
        if (this.multipleLineTitle) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i26 = (i25 + i5) * 31;
        if (this.swipeEnabled) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i27 = (i26 + i6) * 31;
        if (this.hideProfilePhoto) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i28 = (i27 + i7) * 31;
        if (this.shouldShowSquareImage) {
            i8 = 1;
        } else {
            i8 = 0;
        }
        int i29 = (i28 + i8) * 31;
        if (this.imageUrls != null) {
            i9 = this.imageUrls.hashCode();
        } else {
            i9 = 0;
        }
        int i30 = (((i29 + i9) * 31) + this.imageRes) * 31;
        if (this.titleText != null) {
            i10 = this.titleText.hashCode();
        } else {
            i10 = 0;
        }
        int i31 = (i30 + i10) * 31;
        if (this.profilePlaceholderText != null) {
            i11 = this.profilePlaceholderText.hashCode();
        } else {
            i11 = 0;
        }
        int i32 = (i31 + i11) * 31;
        if (this.thirdRowText != null) {
            i12 = this.thirdRowText.hashCode();
        } else {
            i12 = 0;
        }
        int i33 = (i32 + i12) * 31;
        if (this.fourthRowText != null) {
            i13 = this.fourthRowText.hashCode();
        } else {
            i13 = 0;
        }
        int i34 = (i33 + i13) * 31;
        if (this.actionButtonText != null) {
            i14 = this.actionButtonText.hashCode();
        } else {
            i14 = 0;
        }
        int i35 = (((((i34 + i14) * 31) + this.actionButtonTextRes) * 31) + this.swipeTextRes) * 31;
        if (this.subtitleText != null) {
            i15 = this.subtitleText.hashCode();
        } else {
            i15 = 0;
        }
        int i36 = (i35 + i15) * 31;
        if (this.clickListener != null) {
            i16 = 1;
        } else {
            i16 = 0;
        }
        int i37 = (i36 + i16) * 31;
        if (this.longClickListener != null) {
            i17 = 1;
        } else {
            i17 = 0;
        }
        int i38 = (i37 + i17) * 31;
        if (this.actionButtonClickListener != null) {
            i18 = 1;
        } else {
            i18 = 0;
        }
        int i39 = (i38 + i18) * 31;
        if (this.swipeListener == null) {
            i20 = 0;
        }
        int i40 = (i39 + i20) * 31;
        if (this.showDivider != null) {
            i19 = this.showDivider.hashCode();
        } else {
            i19 = 0;
        }
        int i41 = (i40 + i19) * 31;
        if (this.numCarouselItemsShown != null) {
            i21 = this.numCarouselItemsShown.hashCode();
        }
        return i41 + i21;
    }

    public String toString() {
        return "ThreadPreviewEpoxyModel_{timeAgo=" + this.timeAgo + ", showUnread=" + this.showUnread + ", showActionButton=" + this.showActionButton + ", multipleLineTitle=" + this.multipleLineTitle + ", swipeEnabled=" + this.swipeEnabled + ", hideProfilePhoto=" + this.hideProfilePhoto + ", shouldShowSquareImage=" + this.shouldShowSquareImage + ", imageUrls=" + this.imageUrls + ", imageRes=" + this.imageRes + ", titleText=" + this.titleText + ", profilePlaceholderText=" + this.profilePlaceholderText + ", thirdRowText=" + this.thirdRowText + ", fourthRowText=" + this.fourthRowText + ", actionButtonText=" + this.actionButtonText + ", actionButtonTextRes=" + this.actionButtonTextRes + ", swipeTextRes=" + this.swipeTextRes + ", subtitleText=" + this.subtitleText + ", clickListener=" + this.clickListener + ", longClickListener=" + this.longClickListener + ", actionButtonClickListener=" + this.actionButtonClickListener + ", swipeListener=" + this.swipeListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
