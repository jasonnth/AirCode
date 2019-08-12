package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.PopularDestinationGroup;
import com.airbnb.android.core.views.SearchSuggestionPopularView;
import com.airbnb.android.core.views.SearchSuggestionPopularView.OnItemClickListener;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class SearchSuggestionPopularChinaEpoxyModel_ extends SearchSuggestionPopularChinaEpoxyModel implements GeneratedModel<SearchSuggestionPopularView> {
    private OnModelBoundListener<SearchSuggestionPopularChinaEpoxyModel_, SearchSuggestionPopularView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SearchSuggestionPopularChinaEpoxyModel_, SearchSuggestionPopularView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, SearchSuggestionPopularView object, int position) {
    }

    public void handlePostBind(SearchSuggestionPopularView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public SearchSuggestionPopularChinaEpoxyModel_ onBind(OnModelBoundListener<SearchSuggestionPopularChinaEpoxyModel_, SearchSuggestionPopularView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(SearchSuggestionPopularView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public SearchSuggestionPopularChinaEpoxyModel_ onUnbind(OnModelUnboundListener<SearchSuggestionPopularChinaEpoxyModel_, SearchSuggestionPopularView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public SearchSuggestionPopularChinaEpoxyModel_ popularDestinations(List<PopularDestinationGroup> popularDestinations) {
        onMutation();
        this.popularDestinations = popularDestinations;
        return this;
    }

    public List<PopularDestinationGroup> popularDestinations() {
        return this.popularDestinations;
    }

    public SearchSuggestionPopularChinaEpoxyModel_ itemClickListener(OnItemClickListener itemClickListener) {
        onMutation();
        this.itemClickListener = itemClickListener;
        return this;
    }

    public OnItemClickListener itemClickListener() {
        return this.itemClickListener;
    }

    public SearchSuggestionPopularChinaEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SearchSuggestionPopularChinaEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SearchSuggestionPopularChinaEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionPopularChinaEpoxyModel_ m5530id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionPopularChinaEpoxyModel_ m5535id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionPopularChinaEpoxyModel_ m5531id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionPopularChinaEpoxyModel_ m5532id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionPopularChinaEpoxyModel_ m5534id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionPopularChinaEpoxyModel_ m5533id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SearchSuggestionPopularChinaEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SearchSuggestionPopularChinaEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SearchSuggestionPopularChinaEpoxyModel_ show() {
        super.show();
        return this;
    }

    public SearchSuggestionPopularChinaEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SearchSuggestionPopularChinaEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_search_suggestion_popular_china;
    }

    public SearchSuggestionPopularChinaEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.popularDestinations = null;
        this.itemClickListener = null;
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
        if (!(o instanceof SearchSuggestionPopularChinaEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        SearchSuggestionPopularChinaEpoxyModel_ that = (SearchSuggestionPopularChinaEpoxyModel_) o;
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
        if (this.popularDestinations != null) {
            if (!this.popularDestinations.equals(that.popularDestinations)) {
                return false;
            }
        } else if (that.popularDestinations != null) {
            return false;
        }
        if (this.itemClickListener == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.itemClickListener == null)) {
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
        int i4 = 1;
        int i5 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i6 = (hashCode + i) * 31;
        if (this.popularDestinations != null) {
            i2 = this.popularDestinations.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.itemClickListener == null) {
            i4 = 0;
        }
        int i8 = (i7 + i4) * 31;
        if (this.showDivider != null) {
            i3 = this.showDivider.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.numCarouselItemsShown != null) {
            i5 = this.numCarouselItemsShown.hashCode();
        }
        return i9 + i5;
    }

    public String toString() {
        return "SearchSuggestionPopularChinaEpoxyModel_{popularDestinations=" + this.popularDestinations + ", itemClickListener=" + this.itemClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
