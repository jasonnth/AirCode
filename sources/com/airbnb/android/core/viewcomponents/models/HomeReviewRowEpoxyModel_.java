package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.AttributedTextRange;
import com.airbnb.android.core.models.Review;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.HomeReviewRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class HomeReviewRowEpoxyModel_ extends HomeReviewRowEpoxyModel implements GeneratedModel<HomeReviewRow> {
    private OnModelBoundListener<HomeReviewRowEpoxyModel_, HomeReviewRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<HomeReviewRowEpoxyModel_, HomeReviewRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, HomeReviewRow object, int position) {
        if (this.translateClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.translateClickListener).bind(holder, object);
        }
        if (this.onClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.onClickListener).bind(holder, object);
        }
        if (this.reportClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.reportClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(HomeReviewRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public HomeReviewRowEpoxyModel_ onBind(OnModelBoundListener<HomeReviewRowEpoxyModel_, HomeReviewRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(HomeReviewRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public HomeReviewRowEpoxyModel_ onUnbind(OnModelUnboundListener<HomeReviewRowEpoxyModel_, HomeReviewRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public HomeReviewRowEpoxyModel_ review(Review review) {
        onMutation();
        this.review = review;
        return this;
    }

    public Review review() {
        return this.review;
    }

    public HomeReviewRowEpoxyModel_ showPrivateComment(boolean showPrivateComment) {
        onMutation();
        this.showPrivateComment = showPrivateComment;
        return this;
    }

    public boolean showPrivateComment() {
        return this.showPrivateComment;
    }

    public HomeReviewRowEpoxyModel_ showPublicFeedback(boolean showPublicFeedback) {
        onMutation();
        this.showPublicFeedback = showPublicFeedback;
        return this;
    }

    public boolean showPublicFeedback() {
        return this.showPublicFeedback;
    }

    public HomeReviewRowEpoxyModel_ showStarRating(boolean showStarRating) {
        onMutation();
        this.showStarRating = showStarRating;
        return this;
    }

    public boolean showStarRating() {
        return this.showStarRating;
    }

    public HomeReviewRowEpoxyModel_ showListingName(boolean showListingName) {
        onMutation();
        this.showListingName = showListingName;
        return this;
    }

    public boolean showListingName() {
        return this.showListingName;
    }

    public HomeReviewRowEpoxyModel_ showTranslation(boolean showTranslation) {
        onMutation();
        this.showTranslation = showTranslation;
        return this;
    }

    public boolean showTranslation() {
        return this.showTranslation;
    }

    public HomeReviewRowEpoxyModel_ reviewDateString(String reviewDateString) {
        onMutation();
        this.reviewDateString = reviewDateString;
        return this;
    }

    public String reviewDateString() {
        return this.reviewDateString;
    }

    public HomeReviewRowEpoxyModel_ reportString(int reportString) {
        onMutation();
        this.reportString = reportString;
        return this;
    }

    public int reportString() {
        return this.reportString;
    }

    public HomeReviewRowEpoxyModel_ attributedTextRangeList(List<AttributedTextRange> attributedTextRangeList) {
        onMutation();
        this.attributedTextRangeList = attributedTextRangeList;
        return this;
    }

    public List<AttributedTextRange> attributedTextRangeList() {
        return this.attributedTextRangeList;
    }

    public HomeReviewRowEpoxyModel_ translateClickListener(OnModelClickListener<HomeReviewRowEpoxyModel_, HomeReviewRow> translateClickListener) {
        onMutation();
        if (translateClickListener == null) {
            this.translateClickListener = null;
        } else {
            this.translateClickListener = new WrappedEpoxyModelClickListener(this, translateClickListener);
        }
        return this;
    }

    public HomeReviewRowEpoxyModel_ translateClickListener(OnClickListener translateClickListener) {
        onMutation();
        this.translateClickListener = translateClickListener;
        return this;
    }

    public OnClickListener translateClickListener() {
        return this.translateClickListener;
    }

    public HomeReviewRowEpoxyModel_ onClickListener(OnModelClickListener<HomeReviewRowEpoxyModel_, HomeReviewRow> onClickListener) {
        onMutation();
        if (onClickListener == null) {
            this.onClickListener = null;
        } else {
            this.onClickListener = new WrappedEpoxyModelClickListener(this, onClickListener);
        }
        return this;
    }

    public HomeReviewRowEpoxyModel_ onClickListener(OnClickListener onClickListener) {
        onMutation();
        this.onClickListener = onClickListener;
        return this;
    }

    public OnClickListener onClickListener() {
        return this.onClickListener;
    }

    public HomeReviewRowEpoxyModel_ reportClickListener(OnModelClickListener<HomeReviewRowEpoxyModel_, HomeReviewRow> reportClickListener) {
        onMutation();
        if (reportClickListener == null) {
            this.reportClickListener = null;
        } else {
            this.reportClickListener = new WrappedEpoxyModelClickListener(this, reportClickListener);
        }
        return this;
    }

    public HomeReviewRowEpoxyModel_ reportClickListener(OnClickListener reportClickListener) {
        onMutation();
        this.reportClickListener = reportClickListener;
        return this;
    }

    public OnClickListener reportClickListener() {
        return this.reportClickListener;
    }

    public HomeReviewRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public HomeReviewRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public HomeReviewRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public HomeReviewRowEpoxyModel_ m4762id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public HomeReviewRowEpoxyModel_ m4767id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public HomeReviewRowEpoxyModel_ m4763id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public HomeReviewRowEpoxyModel_ m4764id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public HomeReviewRowEpoxyModel_ m4766id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public HomeReviewRowEpoxyModel_ m4765id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public HomeReviewRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public HomeReviewRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public HomeReviewRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public HomeReviewRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public HomeReviewRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_home_review_row;
    }

    public HomeReviewRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.review = null;
        this.showPrivateComment = false;
        this.showPublicFeedback = false;
        this.showStarRating = false;
        this.showListingName = false;
        this.showTranslation = false;
        this.reviewDateString = null;
        this.reportString = 0;
        this.attributedTextRangeList = null;
        this.translateClickListener = null;
        this.onClickListener = null;
        this.reportClickListener = null;
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
        if (o == this) {
            return true;
        }
        if (!(o instanceof HomeReviewRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        HomeReviewRowEpoxyModel_ that = (HomeReviewRowEpoxyModel_) o;
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
        if (this.review != null) {
            if (!this.review.equals(that.review)) {
                return false;
            }
        } else if (that.review != null) {
            return false;
        }
        if (this.showPrivateComment != that.showPrivateComment || this.showPublicFeedback != that.showPublicFeedback || this.showStarRating != that.showStarRating || this.showListingName != that.showListingName || this.showTranslation != that.showTranslation) {
            return false;
        }
        if (this.reviewDateString != null) {
            if (!this.reviewDateString.equals(that.reviewDateString)) {
                return false;
            }
        } else if (that.reviewDateString != null) {
            return false;
        }
        if (this.reportString != that.reportString) {
            return false;
        }
        if (this.attributedTextRangeList != null) {
            if (!this.attributedTextRangeList.equals(that.attributedTextRangeList)) {
                return false;
            }
        } else if (that.attributedTextRangeList != null) {
            return false;
        }
        if (this.translateClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.translateClickListener == null)) {
            return false;
        }
        if (this.onClickListener == null) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (z3 != (that.onClickListener == null)) {
            return false;
        }
        if (this.reportClickListener == null) {
            z4 = true;
        } else {
            z4 = false;
        }
        if (z4 != (that.reportClickListener == null)) {
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
        int i13 = 1;
        int i14 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i15 = (hashCode + i) * 31;
        if (this.review != null) {
            i2 = this.review.hashCode();
        } else {
            i2 = 0;
        }
        int i16 = (i15 + i2) * 31;
        if (this.showPrivateComment) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i17 = (i16 + i3) * 31;
        if (this.showPublicFeedback) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i18 = (i17 + i4) * 31;
        if (this.showStarRating) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i19 = (i18 + i5) * 31;
        if (this.showListingName) {
            i6 = 1;
        } else {
            i6 = 0;
        }
        int i20 = (i19 + i6) * 31;
        if (this.showTranslation) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i21 = (i20 + i7) * 31;
        if (this.reviewDateString != null) {
            i8 = this.reviewDateString.hashCode();
        } else {
            i8 = 0;
        }
        int i22 = (((i21 + i8) * 31) + this.reportString) * 31;
        if (this.attributedTextRangeList != null) {
            i9 = this.attributedTextRangeList.hashCode();
        } else {
            i9 = 0;
        }
        int i23 = (i22 + i9) * 31;
        if (this.translateClickListener != null) {
            i10 = 1;
        } else {
            i10 = 0;
        }
        int i24 = (i23 + i10) * 31;
        if (this.onClickListener != null) {
            i11 = 1;
        } else {
            i11 = 0;
        }
        int i25 = (i24 + i11) * 31;
        if (this.reportClickListener == null) {
            i13 = 0;
        }
        int i26 = (i25 + i13) * 31;
        if (this.showDivider != null) {
            i12 = this.showDivider.hashCode();
        } else {
            i12 = 0;
        }
        int i27 = (i26 + i12) * 31;
        if (this.numCarouselItemsShown != null) {
            i14 = this.numCarouselItemsShown.hashCode();
        }
        return i27 + i14;
    }

    public String toString() {
        return "HomeReviewRowEpoxyModel_{review=" + this.review + ", showPrivateComment=" + this.showPrivateComment + ", showPublicFeedback=" + this.showPublicFeedback + ", showStarRating=" + this.showStarRating + ", showListingName=" + this.showListingName + ", showTranslation=" + this.showTranslation + ", reviewDateString=" + this.reviewDateString + ", reportString=" + this.reportString + ", attributedTextRangeList=" + this.attributedTextRangeList + ", translateClickListener=" + this.translateClickListener + ", onClickListener=" + this.onClickListener + ", reportClickListener=" + this.reportClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
