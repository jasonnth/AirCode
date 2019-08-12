package com.airbnb.android.itinerary.viewmodels;

import com.airbnb.android.itinerary.C5755R;
import com.airbnb.android.itinerary.data.models.FreeTimeItem;
import com.airbnb.android.itinerary.views.ItineraryItemView;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.p027n2.components.RecommendationRow.Recommendation;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;
import java.util.List;

public class SuggestionsEpoxyModel_ extends SuggestionsEpoxyModel implements GeneratedModel<ItineraryItemView> {
    private OnModelBoundListener<SuggestionsEpoxyModel_, ItineraryItemView> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SuggestionsEpoxyModel_, ItineraryItemView> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, ItineraryItemView object, int position) {
    }

    public void handlePostBind(ItineraryItemView object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public SuggestionsEpoxyModel_ onBind(OnModelBoundListener<SuggestionsEpoxyModel_, ItineraryItemView> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(ItineraryItemView object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public SuggestionsEpoxyModel_ onUnbind(OnModelUnboundListener<SuggestionsEpoxyModel_, ItineraryItemView> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public SuggestionsEpoxyModel_ freeTimeItem(FreeTimeItem freeTimeItem) {
        onMutation();
        this.freeTimeItem = freeTimeItem;
        return this;
    }

    public FreeTimeItem freeTimeItem() {
        return this.freeTimeItem;
    }

    public SuggestionsEpoxyModel_ recommendations(List<List<Recommendation>> recommendations) {
        onMutation();
        this.recommendations = recommendations;
        return this;
    }

    public List<List<Recommendation>> recommendations() {
        return this.recommendations;
    }

    public SuggestionsEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SuggestionsEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SuggestionsEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SuggestionsEpoxyModel_ m4258id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SuggestionsEpoxyModel_ m4263id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SuggestionsEpoxyModel_ m4259id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SuggestionsEpoxyModel_ m4260id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SuggestionsEpoxyModel_ m4262id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SuggestionsEpoxyModel_ m4261id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SuggestionsEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SuggestionsEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SuggestionsEpoxyModel_ show() {
        super.show();
        return this;
    }

    public SuggestionsEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SuggestionsEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C5755R.layout.view_holder_itinerary_item;
    }

    public SuggestionsEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.freeTimeItem = null;
        this.recommendations = null;
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
        if (!(o instanceof SuggestionsEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        SuggestionsEpoxyModel_ that = (SuggestionsEpoxyModel_) o;
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
        if (this.freeTimeItem != null) {
            if (!this.freeTimeItem.equals(that.freeTimeItem)) {
                return false;
            }
        } else if (that.freeTimeItem != null) {
            return false;
        }
        if (this.recommendations == null) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z2 != (that.recommendations == null)) {
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
        if (this.freeTimeItem != null) {
            i2 = this.freeTimeItem.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.recommendations == null) {
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
        return "SuggestionsEpoxyModel_{freeTimeItem=" + this.freeTimeItem + ", recommendations=" + this.recommendations + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
