package com.airbnb.android.explore.viewcomponents.viewmodels;

import com.airbnb.android.core.models.FilterSuggestionItem;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.adapters.FilterSuggestionCarouselController.CarouselClickListener;
import com.airbnb.android.explore.views.FilterSuggestionCard;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class FilterSuggestionCardEpoxyModel_ extends FilterSuggestionCardEpoxyModel implements GeneratedModel<FilterSuggestionCard> {
    private OnModelBoundListener<FilterSuggestionCardEpoxyModel_, FilterSuggestionCard> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<FilterSuggestionCardEpoxyModel_, FilterSuggestionCard> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, FilterSuggestionCard object, int position) {
    }

    public void handlePostBind(FilterSuggestionCard object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public FilterSuggestionCardEpoxyModel_ onBind(OnModelBoundListener<FilterSuggestionCardEpoxyModel_, FilterSuggestionCard> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(FilterSuggestionCard object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public FilterSuggestionCardEpoxyModel_ onUnbind(OnModelUnboundListener<FilterSuggestionCardEpoxyModel_, FilterSuggestionCard> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public FilterSuggestionCardEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public FilterSuggestionCardEpoxyModel_ items(List<FilterSuggestionItem> items) {
        onMutation();
        this.items = items;
        return this;
    }

    public List<FilterSuggestionItem> items() {
        return this.items;
    }

    public FilterSuggestionCardEpoxyModel_ carouselItemClickListener(CarouselClickListener carouselItemClickListener) {
        onMutation();
        this.carouselItemClickListener = carouselItemClickListener;
        return this;
    }

    public CarouselClickListener carouselItemClickListener() {
        return this.carouselItemClickListener;
    }

    public FilterSuggestionCardEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public FilterSuggestionCardEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public FilterSuggestionCardEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public FilterSuggestionCardEpoxyModel_ m5903id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public FilterSuggestionCardEpoxyModel_ m5908id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public FilterSuggestionCardEpoxyModel_ m5904id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public FilterSuggestionCardEpoxyModel_ m5905id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public FilterSuggestionCardEpoxyModel_ m5907id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public FilterSuggestionCardEpoxyModel_ m5906id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public FilterSuggestionCardEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public FilterSuggestionCardEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public FilterSuggestionCardEpoxyModel_ show() {
        super.show();
        return this;
    }

    public FilterSuggestionCardEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public FilterSuggestionCardEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0857R.layout.view_holder_filter_suggestion_card;
    }

    public FilterSuggestionCardEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.items = null;
        this.carouselItemClickListener = null;
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
        if (!(o instanceof FilterSuggestionCardEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        FilterSuggestionCardEpoxyModel_ that = (FilterSuggestionCardEpoxyModel_) o;
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
        if (this.title != null) {
            if (!this.title.equals(that.title)) {
                return false;
            }
        } else if (that.title != null) {
            return false;
        }
        if (this.items != null) {
            if (!this.items.equals(that.items)) {
                return false;
            }
        } else if (that.items != null) {
            return false;
        }
        if (this.carouselItemClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.carouselItemClickListener == null)) {
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
        int i5 = 1;
        int i6 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i7 = (hashCode + i) * 31;
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.items != null) {
            i3 = this.items.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.carouselItemClickListener == null) {
            i5 = 0;
        }
        int i10 = (i9 + i5) * 31;
        if (this.showDivider != null) {
            i4 = this.showDivider.hashCode();
        } else {
            i4 = 0;
        }
        int i11 = (i10 + i4) * 31;
        if (this.numCarouselItemsShown != null) {
            i6 = this.numCarouselItemsShown.hashCode();
        }
        return i11 + i6;
    }

    public String toString() {
        return "FilterSuggestionCardEpoxyModel_{title=" + this.title + ", items=" + this.items + ", carouselItemClickListener=" + this.carouselItemClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
