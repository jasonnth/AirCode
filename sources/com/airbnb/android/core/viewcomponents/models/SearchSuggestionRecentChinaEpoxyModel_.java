package com.airbnb.android.core.viewcomponents.models;

import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.models.SavedSearch;
import com.airbnb.android.core.views.SearchSuggestionRecentView;
import com.airbnb.android.core.views.SearchSuggestionRecentView.OnItemClickListener;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class SearchSuggestionRecentChinaEpoxyModel_ extends SearchSuggestionRecentChinaEpoxyModel implements GeneratedModel<SearchSuggestionRecentView> {
    private OnModelBoundListener<SearchSuggestionRecentChinaEpoxyModel_, SearchSuggestionRecentView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SearchSuggestionRecentChinaEpoxyModel_, SearchSuggestionRecentView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, SearchSuggestionRecentView object, int position) {
    }

    public void handlePostBind(SearchSuggestionRecentView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public SearchSuggestionRecentChinaEpoxyModel_ onBind(OnModelBoundListener<SearchSuggestionRecentChinaEpoxyModel_, SearchSuggestionRecentView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(SearchSuggestionRecentView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public SearchSuggestionRecentChinaEpoxyModel_ onUnbind(OnModelUnboundListener<SearchSuggestionRecentChinaEpoxyModel_, SearchSuggestionRecentView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public SearchSuggestionRecentChinaEpoxyModel_ savedSearches(List<SavedSearch> savedSearches) {
        onMutation();
        this.savedSearches = savedSearches;
        return this;
    }

    public List<SavedSearch> savedSearches() {
        return this.savedSearches;
    }

    public SearchSuggestionRecentChinaEpoxyModel_ itemClickListener(OnItemClickListener itemClickListener) {
        onMutation();
        this.itemClickListener = itemClickListener;
        return this;
    }

    public OnItemClickListener itemClickListener() {
        return this.itemClickListener;
    }

    public SearchSuggestionRecentChinaEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SearchSuggestionRecentChinaEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SearchSuggestionRecentChinaEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionRecentChinaEpoxyModel_ m5542id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionRecentChinaEpoxyModel_ m5547id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionRecentChinaEpoxyModel_ m5543id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionRecentChinaEpoxyModel_ m5544id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionRecentChinaEpoxyModel_ m5546id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SearchSuggestionRecentChinaEpoxyModel_ m5545id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SearchSuggestionRecentChinaEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SearchSuggestionRecentChinaEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SearchSuggestionRecentChinaEpoxyModel_ show() {
        super.show();
        return this;
    }

    public SearchSuggestionRecentChinaEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SearchSuggestionRecentChinaEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.view_holder_search_suggestion_recent_china;
    }

    public SearchSuggestionRecentChinaEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.savedSearches = null;
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
        if (!(o instanceof SearchSuggestionRecentChinaEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        SearchSuggestionRecentChinaEpoxyModel_ that = (SearchSuggestionRecentChinaEpoxyModel_) o;
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
        if (this.savedSearches != null) {
            if (!this.savedSearches.equals(that.savedSearches)) {
                return false;
            }
        } else if (that.savedSearches != null) {
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
        if (this.savedSearches != null) {
            i2 = this.savedSearches.hashCode();
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
        return "SearchSuggestionRecentChinaEpoxyModel_{savedSearches=" + this.savedSearches + ", itemClickListener=" + this.itemClickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
