package com.airbnb.android.explore.viewcomponents.viewmodels;

import com.airbnb.android.core.models.FilterRemovalSuggestionItem;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.adapters.FilterRemovalSuggestionCarouselAdapter.CarouselItemClickListener;
import com.airbnb.android.explore.views.FilterRemovalSuggestionCard;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class FilterRemovalSuggestionCardEpoxyModel_ extends FilterRemovalSuggestionCardEpoxyModel implements GeneratedModel<FilterRemovalSuggestionCard> {
    private OnModelBoundListener<FilterRemovalSuggestionCardEpoxyModel_, FilterRemovalSuggestionCard> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<FilterRemovalSuggestionCardEpoxyModel_, FilterRemovalSuggestionCard> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, FilterRemovalSuggestionCard object, int position) {
    }

    public void handlePostBind(FilterRemovalSuggestionCard object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public FilterRemovalSuggestionCardEpoxyModel_ onBind(OnModelBoundListener<FilterRemovalSuggestionCardEpoxyModel_, FilterRemovalSuggestionCard> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(FilterRemovalSuggestionCard object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public FilterRemovalSuggestionCardEpoxyModel_ onUnbind(OnModelUnboundListener<FilterRemovalSuggestionCardEpoxyModel_, FilterRemovalSuggestionCard> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public FilterRemovalSuggestionCardEpoxyModel_ title(CharSequence title) {
        onMutation();
        this.title = title;
        return this;
    }

    public CharSequence title() {
        return this.title;
    }

    public FilterRemovalSuggestionCardEpoxyModel_ subtitle(CharSequence subtitle) {
        onMutation();
        this.subtitle = subtitle;
        return this;
    }

    public CharSequence subtitle() {
        return this.subtitle;
    }

    public FilterRemovalSuggestionCardEpoxyModel_ noTopPadding(boolean noTopPadding) {
        onMutation();
        this.noTopPadding = noTopPadding;
        return this;
    }

    public boolean noTopPadding() {
        return this.noTopPadding;
    }

    public FilterRemovalSuggestionCardEpoxyModel_ items(List<FilterRemovalSuggestionItem> items) {
        onMutation();
        this.items = items;
        return this;
    }

    public List<FilterRemovalSuggestionItem> items() {
        return this.items;
    }

    public FilterRemovalSuggestionCardEpoxyModel_ carouselItemClickListener(CarouselItemClickListener carouselItemClickListener) {
        onMutation();
        this.carouselItemClickListener = carouselItemClickListener;
        return this;
    }

    public CarouselItemClickListener carouselItemClickListener() {
        return this.carouselItemClickListener;
    }

    public FilterRemovalSuggestionCardEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public FilterRemovalSuggestionCardEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public FilterRemovalSuggestionCardEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public FilterRemovalSuggestionCardEpoxyModel_ m5891id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public FilterRemovalSuggestionCardEpoxyModel_ m5896id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public FilterRemovalSuggestionCardEpoxyModel_ m5892id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public FilterRemovalSuggestionCardEpoxyModel_ m5893id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public FilterRemovalSuggestionCardEpoxyModel_ m5895id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public FilterRemovalSuggestionCardEpoxyModel_ m5894id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public FilterRemovalSuggestionCardEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public FilterRemovalSuggestionCardEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public FilterRemovalSuggestionCardEpoxyModel_ show() {
        super.show();
        return this;
    }

    public FilterRemovalSuggestionCardEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public FilterRemovalSuggestionCardEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0857R.layout.view_holder_filter_removal_suggestion_card;
    }

    public FilterRemovalSuggestionCardEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.title = null;
        this.subtitle = null;
        this.noTopPadding = false;
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
        if (!(o instanceof FilterRemovalSuggestionCardEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        FilterRemovalSuggestionCardEpoxyModel_ that = (FilterRemovalSuggestionCardEpoxyModel_) o;
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
        if (this.subtitle != null) {
            if (!this.subtitle.equals(that.subtitle)) {
                return false;
            }
        } else if (that.subtitle != null) {
            return false;
        }
        if (this.noTopPadding != that.noTopPadding) {
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
        if (this.title != null) {
            i2 = this.title.hashCode();
        } else {
            i2 = 0;
        }
        int i10 = (i9 + i2) * 31;
        if (this.subtitle != null) {
            i3 = this.subtitle.hashCode();
        } else {
            i3 = 0;
        }
        int i11 = (i10 + i3) * 31;
        if (this.noTopPadding) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        int i12 = (i11 + i4) * 31;
        if (this.items != null) {
            i5 = this.items.hashCode();
        } else {
            i5 = 0;
        }
        int i13 = (i12 + i5) * 31;
        if (this.carouselItemClickListener == null) {
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
        return "FilterRemovalSuggestionCardEpoxyModel_{title=" + this.title + ", subtitle=" + this.subtitle + ", noTopPadding=" + this.noTopPadding + ", items=" + this.items + ", carouselItemClickListener=" + this.carouselItemClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
