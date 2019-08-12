package com.airbnb.android.insights;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.Insight;
import com.airbnb.android.core.models.Listing;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class LastInsightEpoxyModel_ extends LastInsightEpoxyModel implements GeneratedModel<LastInsightView> {
    private OnModelBoundListener<LastInsightEpoxyModel_, LastInsightView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<LastInsightEpoxyModel_, LastInsightView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, LastInsightView object, int position) {
        if (this.primaryButtonClickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.primaryButtonClickListener).bind(holder, object);
        }
    }

    public void handlePostBind(LastInsightView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public LastInsightEpoxyModel_ onBind(OnModelBoundListener<LastInsightEpoxyModel_, LastInsightView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(LastInsightView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public LastInsightEpoxyModel_ onUnbind(OnModelUnboundListener<LastInsightEpoxyModel_, LastInsightView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public LastInsightEpoxyModel_ nextListing(Listing nextListing) {
        onMutation();
        this.nextListing = nextListing;
        return this;
    }

    public Listing nextListing() {
        return this.nextListing;
    }

    public LastInsightEpoxyModel_ dummyInsight(Insight dummyInsight) {
        onMutation();
        this.dummyInsight = dummyInsight;
        return this;
    }

    public Insight dummyInsight() {
        return this.dummyInsight;
    }

    public LastInsightEpoxyModel_ isLoading(boolean isLoading) {
        onMutation();
        this.isLoading = isLoading;
        return this;
    }

    public boolean isLoading() {
        return this.isLoading;
    }

    public LastInsightEpoxyModel_ primaryButtonClickListener(OnModelClickListener<LastInsightEpoxyModel_, LastInsightView> primaryButtonClickListener) {
        onMutation();
        if (primaryButtonClickListener == null) {
            this.primaryButtonClickListener = null;
        } else {
            this.primaryButtonClickListener = new WrappedEpoxyModelClickListener(this, primaryButtonClickListener);
        }
        return this;
    }

    public LastInsightEpoxyModel_ primaryButtonClickListener(OnClickListener primaryButtonClickListener) {
        onMutation();
        this.primaryButtonClickListener = primaryButtonClickListener;
        return this;
    }

    public OnClickListener primaryButtonClickListener() {
        return this.primaryButtonClickListener;
    }

    public LastInsightEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public LastInsightEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public LastInsightEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public LastInsightEpoxyModel_ m6011id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public LastInsightEpoxyModel_ m6016id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public LastInsightEpoxyModel_ m6012id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public LastInsightEpoxyModel_ m6013id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public LastInsightEpoxyModel_ m6015id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public LastInsightEpoxyModel_ m6014id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public LastInsightEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public LastInsightEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public LastInsightEpoxyModel_ show() {
        super.show();
        return this;
    }

    public LastInsightEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public LastInsightEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C6552R.layout.last_insight_view_holder;
    }

    public LastInsightEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.nextListing = null;
        this.dummyInsight = null;
        this.isLoading = false;
        this.primaryButtonClickListener = null;
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
        if (!(o instanceof LastInsightEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        LastInsightEpoxyModel_ that = (LastInsightEpoxyModel_) o;
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
        if (this.nextListing != null) {
            if (!this.nextListing.equals(that.nextListing)) {
                return false;
            }
        } else if (that.nextListing != null) {
            return false;
        }
        if (this.dummyInsight != null) {
            if (!this.dummyInsight.equals(that.dummyInsight)) {
                return false;
            }
        } else if (that.dummyInsight != null) {
            return false;
        }
        if (this.isLoading != that.isLoading) {
            return false;
        }
        if (this.primaryButtonClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.primaryButtonClickListener == null)) {
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
        if (this.nextListing != null) {
            i2 = this.nextListing.hashCode();
        } else {
            i2 = 0;
        }
        int i9 = (i8 + i2) * 31;
        if (this.dummyInsight != null) {
            i3 = this.dummyInsight.hashCode();
        } else {
            i3 = 0;
        }
        int i10 = (i9 + i3) * 31;
        if (this.isLoading) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.primaryButtonClickListener == null) {
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
        return "LastInsightEpoxyModel_{nextListing=" + this.nextListing + ", dummyInsight=" + this.dummyInsight + ", isLoading=" + this.isLoading + ", primaryButtonClickListener=" + this.primaryButtonClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
