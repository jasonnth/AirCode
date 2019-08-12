package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.ReviewMarquee;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class ReviewMarqueeEpoxyModel_ extends ReviewMarqueeEpoxyModel implements GeneratedModel<ReviewMarquee> {
    private OnModelBoundListener<ReviewMarqueeEpoxyModel_, ReviewMarquee> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<ReviewMarqueeEpoxyModel_, ReviewMarquee> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ReviewMarquee object, int position) {
    }

    public void handlePostBind(ReviewMarquee object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public ReviewMarqueeEpoxyModel_ onBind(OnModelBoundListener<ReviewMarqueeEpoxyModel_, ReviewMarquee> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ReviewMarquee object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public ReviewMarqueeEpoxyModel_ onUnbind(OnModelUnboundListener<ReviewMarqueeEpoxyModel_, ReviewMarquee> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public ReviewMarqueeEpoxyModel_ numStars(float numStars) {
        onMutation();
        this.numStars = numStars;
        return this;
    }

    public float numStars() {
        return this.numStars;
    }

    public ReviewMarqueeEpoxyModel_ numReviews(int numReviews) {
        onMutation();
        this.numReviews = numReviews;
        return this;
    }

    public int numReviews() {
        return this.numReviews;
    }

    public ReviewMarqueeEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public ReviewMarqueeEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public ReviewMarqueeEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public ReviewMarqueeEpoxyModel_ m5470id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public ReviewMarqueeEpoxyModel_ m5475id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public ReviewMarqueeEpoxyModel_ m5471id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public ReviewMarqueeEpoxyModel_ m5472id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public ReviewMarqueeEpoxyModel_ m5474id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public ReviewMarqueeEpoxyModel_ m5473id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public ReviewMarqueeEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public ReviewMarqueeEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public ReviewMarqueeEpoxyModel_ show() {
        super.show();
        return this;
    }

    public ReviewMarqueeEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public ReviewMarqueeEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_review_marquee;
    }

    public ReviewMarqueeEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.numStars = 0.0f;
        this.numReviews = 0;
        this.showDivider = null;
        this.numCarouselItemsShown = null;
        super.reset();
        return this;
    }

    public boolean equals(Object o) {
        boolean z;
        if (o == this) {
            return true;
        }
        if (!(o instanceof ReviewMarqueeEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        ReviewMarqueeEpoxyModel_ that = (ReviewMarqueeEpoxyModel_) o;
        if ((this.onModelBoundListener_epoxyGeneratedModel == null) != (that.onModelBoundListener_epoxyGeneratedModel == null)) {
            return false;
        }
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            z = true;
        } else {
            z = false;
        }
        if (z != (that.onModelUnboundListener_epoxyGeneratedModel == null) || Float.compare(that.numStars, this.numStars) != 0 || this.numReviews != that.numReviews) {
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
        int i3 = 1;
        int i4 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel == null) {
            i3 = 0;
        }
        int i5 = (hashCode + i3) * 31;
        if (this.numStars != 0.0f) {
            i = Float.floatToIntBits(this.numStars);
        } else {
            i = 0;
        }
        int i6 = (((i5 + i) * 31) + this.numReviews) * 31;
        if (this.showDivider != null) {
            i2 = this.showDivider.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.numCarouselItemsShown != null) {
            i4 = this.numCarouselItemsShown.hashCode();
        }
        return i7 + i4;
    }

    public String toString() {
        return "ReviewMarqueeEpoxyModel_{numStars=" + this.numStars + ", numReviews=" + this.numReviews + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
