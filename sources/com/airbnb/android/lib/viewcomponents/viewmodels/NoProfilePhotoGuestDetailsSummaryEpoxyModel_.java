package com.airbnb.android.lib.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.User;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.NoProfilePhotoDetailsSummary;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class NoProfilePhotoGuestDetailsSummaryEpoxyModel_ extends NoProfilePhotoGuestDetailsSummaryEpoxyModel implements GeneratedModel<NoProfilePhotoDetailsSummary> {
    private OnModelBoundListener<NoProfilePhotoGuestDetailsSummaryEpoxyModel_, NoProfilePhotoDetailsSummary> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<NoProfilePhotoGuestDetailsSummaryEpoxyModel_, NoProfilePhotoDetailsSummary> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, NoProfilePhotoDetailsSummary object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(NoProfilePhotoDetailsSummary object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ onBind(OnModelBoundListener<NoProfilePhotoGuestDetailsSummaryEpoxyModel_, NoProfilePhotoDetailsSummary> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(NoProfilePhotoDetailsSummary object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ onUnbind(OnModelUnboundListener<NoProfilePhotoGuestDetailsSummaryEpoxyModel_, NoProfilePhotoDetailsSummary> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ guest(User guest) {
        onMutation();
        this.guest = guest;
        return this;
    }

    public User guest() {
        return this.guest;
    }

    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ showNoProfilePhoto(boolean showNoProfilePhoto) {
        onMutation();
        this.showNoProfilePhoto = showNoProfilePhoto;
        return this;
    }

    public boolean showNoProfilePhoto() {
        return this.showNoProfilePhoto;
    }

    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ hideReviewsText(boolean hideReviewsText) {
        onMutation();
        this.hideReviewsText = hideReviewsText;
        return this;
    }

    public boolean hideReviewsText() {
        return this.hideReviewsText;
    }

    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ clickListener(OnModelClickListener<NoProfilePhotoGuestDetailsSummaryEpoxyModel_, NoProfilePhotoDetailsSummary> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ m6203id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ m6208id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ m6204id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ m6205id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ m6207id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ m6206id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ show() {
        super.show();
        return this;
    }

    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0880R.layout.view_holder_no_profile_photo_details_summary;
    }

    public NoProfilePhotoGuestDetailsSummaryEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.guest = null;
        this.showNoProfilePhoto = false;
        this.hideReviewsText = false;
        this.clickListener = null;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        boolean z2;
        if (o == this) {
            return true;
        }
        if (!(o instanceof NoProfilePhotoGuestDetailsSummaryEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        NoProfilePhotoGuestDetailsSummaryEpoxyModel_ that = (NoProfilePhotoGuestDetailsSummaryEpoxyModel_) o;
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
        if (this.guest != null) {
            if (!this.guest.equals(that.guest)) {
                return false;
            }
        } else if (that.guest != null) {
            return false;
        }
        if (this.showNoProfilePhoto != that.showNoProfilePhoto || this.hideReviewsText != that.hideReviewsText) {
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
        int i6 = 1;
        int i7 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i8 = (hashCode + i) * 31;
        if (this.guest != null) {
            i2 = this.guest.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.showNoProfilePhoto) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.hideReviewsText) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.clickListener == null) {
            i6 = 0;
        }
        int i12 = (i11 + i6) * 31;
        if (this.showDivider != null) {
            i5 = this.showDivider.hashCode();
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.numCarouselItemsShown != null) {
            i7 = this.numCarouselItemsShown.hashCode();
        }
        return i13 + i7;
    }

    public String toString() {
        return "NoProfilePhotoGuestDetailsSummaryEpoxyModel_{guest=" + this.guest + ", showNoProfilePhoto=" + this.showNoProfilePhoto + ", hideReviewsText=" + this.hideReviewsText + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
