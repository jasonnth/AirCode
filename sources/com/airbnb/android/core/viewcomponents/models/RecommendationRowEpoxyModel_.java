package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.models.RecommendationItem;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.RecommendationRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class RecommendationRowEpoxyModel_ extends RecommendationRowEpoxyModel implements GeneratedModel<RecommendationRow> {
    private OnModelBoundListener<RecommendationRowEpoxyModel_, RecommendationRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<RecommendationRowEpoxyModel_, RecommendationRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, RecommendationRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(RecommendationRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public RecommendationRowEpoxyModel_ onBind(OnModelBoundListener<RecommendationRowEpoxyModel_, RecommendationRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(RecommendationRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public RecommendationRowEpoxyModel_ onUnbind(OnModelUnboundListener<RecommendationRowEpoxyModel_, RecommendationRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public RecommendationRowEpoxyModel_ recommendationItems(List<RecommendationItem> recommendationItems) {
        onMutation();
        this.recommendationItems = recommendationItems;
        return this;
    }

    public List<RecommendationItem> recommendationItems() {
        return this.recommendationItems;
    }

    public RecommendationRowEpoxyModel_ loading(boolean loading) {
        onMutation();
        this.loading = loading;
        return this;
    }

    public boolean loading() {
        return this.loading;
    }

    public RecommendationRowEpoxyModel_ displayOptions(DisplayOptions displayOptions) {
        onMutation();
        this.displayOptions = displayOptions;
        return this;
    }

    public DisplayOptions displayOptions() {
        return this.displayOptions;
    }

    public RecommendationRowEpoxyModel_ showBottomSpace(boolean showBottomSpace) {
        onMutation();
        this.showBottomSpace = showBottomSpace;
        return this;
    }

    public boolean showBottomSpace() {
        return this.showBottomSpace;
    }

    public RecommendationRowEpoxyModel_ clickListener(OnModelClickListener<RecommendationRowEpoxyModel_, RecommendationRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public RecommendationRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public RecommendationRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public RecommendationRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public RecommendationRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public RecommendationRowEpoxyModel_ m5422id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public RecommendationRowEpoxyModel_ m5427id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public RecommendationRowEpoxyModel_ m5423id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public RecommendationRowEpoxyModel_ m5424id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public RecommendationRowEpoxyModel_ m5426id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public RecommendationRowEpoxyModel_ m5425id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public RecommendationRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public RecommendationRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public RecommendationRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public RecommendationRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public RecommendationRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_recommendation_row;
    }

    public RecommendationRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.recommendationItems = null;
        this.loading = false;
        this.displayOptions = null;
        this.showBottomSpace = false;
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
        if (!(o instanceof RecommendationRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        RecommendationRowEpoxyModel_ that = (RecommendationRowEpoxyModel_) o;
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
        if (this.recommendationItems != null) {
            if (!this.recommendationItems.equals(that.recommendationItems)) {
                return false;
            }
        } else if (that.recommendationItems != null) {
            return false;
        }
        if (this.loading != that.loading) {
            return false;
        }
        if (this.displayOptions != null) {
            if (!this.displayOptions.equals(that.displayOptions)) {
                return false;
            }
        } else if (that.displayOptions != null) {
            return false;
        }
        if (this.showBottomSpace != that.showBottomSpace) {
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
        int i6;
        int i7 = 1;
        int i8 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i9 = (hashCode + i) * 31;
        if (this.recommendationItems != null) {
            i2 = this.recommendationItems.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.loading) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.displayOptions != null) {
            i4 = this.displayOptions.hashCode();
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.showBottomSpace) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.clickListener == null) {
            i7 = 0;
        }
        int i14 = (i13 + i7) * 31;
        if (this.showDivider != null) {
            i6 = this.showDivider.hashCode();
        } else {
            i6 = 0;
        }
        int i15 = (i14 + i6) * 31;
        if (this.numCarouselItemsShown != null) {
            i8 = this.numCarouselItemsShown.hashCode();
        }
        return i15 + i8;
    }

    public String toString() {
        return "RecommendationRowEpoxyModel_{recommendationItems=" + this.recommendationItems + ", loading=" + this.loading + ", displayOptions=" + this.displayOptions + ", showBottomSpace=" + this.showBottomSpace + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
