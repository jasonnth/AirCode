package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.DisplayOptions;
import com.airbnb.android.core.models.RecommendationItem;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.RecommendationCardSquare;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class RecommendationCardEpoxyModel_ extends RecommendationCardEpoxyModel implements GeneratedModel<RecommendationCardSquare> {
    private OnModelBoundListener<RecommendationCardEpoxyModel_, RecommendationCardSquare> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<RecommendationCardEpoxyModel_, RecommendationCardSquare> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, RecommendationCardSquare object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(RecommendationCardSquare object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public RecommendationCardEpoxyModel_ onBind(OnModelBoundListener<RecommendationCardEpoxyModel_, RecommendationCardSquare> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(RecommendationCardSquare object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public RecommendationCardEpoxyModel_ onUnbind(OnModelUnboundListener<RecommendationCardEpoxyModel_, RecommendationCardSquare> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public RecommendationCardEpoxyModel_ recommendationItem(RecommendationItem recommendationItem) {
        onMutation();
        this.recommendationItem = recommendationItem;
        return this;
    }

    public RecommendationItem recommendationItem() {
        return this.recommendationItem;
    }

    public RecommendationCardEpoxyModel_ title(String title) {
        onMutation();
        this.title = title;
        return this;
    }

    public String title() {
        return this.title;
    }

    public RecommendationCardEpoxyModel_ actionKicker(String actionKicker) {
        onMutation();
        this.actionKicker = actionKicker;
        return this;
    }

    public String actionKicker() {
        return this.actionKicker;
    }

    public RecommendationCardEpoxyModel_ imageUrl(String imageUrl) {
        onMutation();
        this.imageUrl = imageUrl;
        return this;
    }

    public String imageUrl() {
        return this.imageUrl;
    }

    public RecommendationCardEpoxyModel_ description(String description) {
        onMutation();
        this.description = description;
        return this;
    }

    public String description() {
        return this.description;
    }

    public RecommendationCardEpoxyModel_ loading(boolean loading) {
        onMutation();
        this.loading = loading;
        return this;
    }

    public boolean loading() {
        return this.loading;
    }

    public RecommendationCardEpoxyModel_ clickListener(OnModelClickListener<RecommendationCardEpoxyModel_, RecommendationCardSquare> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public RecommendationCardEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public RecommendationCardEpoxyModel_ displayOptions(DisplayOptions displayOptions) {
        onMutation();
        this.displayOptions = displayOptions;
        return this;
    }

    public DisplayOptions displayOptions() {
        return this.displayOptions;
    }

    public RecommendationCardEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public RecommendationCardEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public RecommendationCardEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public RecommendationCardEpoxyModel_ m5410id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public RecommendationCardEpoxyModel_ m5415id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public RecommendationCardEpoxyModel_ m5411id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public RecommendationCardEpoxyModel_ m5412id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public RecommendationCardEpoxyModel_ m5414id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public RecommendationCardEpoxyModel_ m5413id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public RecommendationCardEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public RecommendationCardEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public RecommendationCardEpoxyModel_ show() {
        super.show();
        return this;
    }

    public RecommendationCardEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public RecommendationCardEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    public RecommendationCardEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.recommendationItem = null;
        this.title = null;
        this.actionKicker = null;
        this.imageUrl = null;
        this.description = null;
        this.loading = false;
        this.clickListener = null;
        this.displayOptions = null;
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
        if (!(o instanceof RecommendationCardEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        RecommendationCardEpoxyModel_ that = (RecommendationCardEpoxyModel_) o;
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
        if (this.recommendationItem != null) {
            if (!this.recommendationItem.equals(that.recommendationItem)) {
                return false;
            }
        } else if (that.recommendationItem != null) {
            return false;
        }
        if (this.title != null) {
            if (!this.title.equals(that.title)) {
                return false;
            }
        } else if (that.title != null) {
            return false;
        }
        if (this.actionKicker != null) {
            if (!this.actionKicker.equals(that.actionKicker)) {
                return false;
            }
        } else if (that.actionKicker != null) {
            return false;
        }
        if (this.imageUrl != null) {
            if (!this.imageUrl.equals(that.imageUrl)) {
                return false;
            }
        } else if (that.imageUrl != null) {
            return false;
        }
        if (this.description != null) {
            if (!this.description.equals(that.description)) {
                return false;
            }
        } else if (that.description != null) {
            return false;
        }
        if (this.loading != that.loading) {
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
        if (this.displayOptions != null) {
            if (!this.displayOptions.equals(that.displayOptions)) {
                return false;
            }
        } else if (that.displayOptions != null) {
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
        int i10 = 1;
        int i11 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i12 = (hashCode + i) * 31;
        if (this.recommendationItem != null) {
            i2 = this.recommendationItem.hashCode();
        } else {
            i2 = 0;
        }
        int i13 = (i12 + i2) * 31;
        if (this.title != null) {
            i3 = this.title.hashCode();
        } else {
            i3 = 0;
        }
        int i14 = (i13 + i3) * 31;
        if (this.actionKicker != null) {
            i4 = this.actionKicker.hashCode();
        } else {
            i4 = 0;
        }
        int i15 = (i14 + i4) * 31;
        if (this.imageUrl != null) {
            i5 = this.imageUrl.hashCode();
        } else {
            i5 = 0;
        }
        int i16 = (i15 + i5) * 31;
        if (this.description != null) {
            i6 = this.description.hashCode();
        } else {
            i6 = 0;
        }
        int i17 = (i16 + i6) * 31;
        if (this.loading) {
            i7 = 1;
        } else {
            i7 = 0;
        }
        int i18 = (i17 + i7) * 31;
        if (this.clickListener == null) {
            i10 = 0;
        }
        int i19 = (i18 + i10) * 31;
        if (this.displayOptions != null) {
            i8 = this.displayOptions.hashCode();
        } else {
            i8 = 0;
        }
        int i20 = (i19 + i8) * 31;
        if (this.showDivider != null) {
            i9 = this.showDivider.hashCode();
        } else {
            i9 = 0;
        }
        int i21 = (i20 + i9) * 31;
        if (this.numCarouselItemsShown != null) {
            i11 = this.numCarouselItemsShown.hashCode();
        }
        return i21 + i11;
    }

    public String toString() {
        return "RecommendationCardEpoxyModel_{recommendationItem=" + this.recommendationItem + ", title=" + this.title + ", actionKicker=" + this.actionKicker + ", imageUrl=" + this.imageUrl + ", description=" + this.description + ", loading=" + this.loading + ", clickListener=" + this.clickListener + ", displayOptions=" + this.displayOptions + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
