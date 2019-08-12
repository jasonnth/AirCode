package com.airbnb.android.explore.viewcomponents.viewmodels;

import android.view.View.OnClickListener;
import com.airbnb.android.core.models.FilterRemovalSuggestionItem;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.views.FilterRemovalSuggestionPill;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class FilterRemovalPillEpoxyModel_ extends FilterRemovalPillEpoxyModel implements GeneratedModel<FilterRemovalSuggestionPill> {
    private OnModelBoundListener<FilterRemovalPillEpoxyModel_, FilterRemovalSuggestionPill> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<FilterRemovalPillEpoxyModel_, FilterRemovalSuggestionPill> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, FilterRemovalSuggestionPill object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(FilterRemovalSuggestionPill object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public FilterRemovalPillEpoxyModel_ onBind(OnModelBoundListener<FilterRemovalPillEpoxyModel_, FilterRemovalSuggestionPill> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(FilterRemovalSuggestionPill object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public FilterRemovalPillEpoxyModel_ onUnbind(OnModelUnboundListener<FilterRemovalPillEpoxyModel_, FilterRemovalSuggestionPill> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public FilterRemovalPillEpoxyModel_ item(FilterRemovalSuggestionItem item) {
        onMutation();
        this.item = item;
        return this;
    }

    public FilterRemovalSuggestionItem item() {
        return this.item;
    }

    public FilterRemovalPillEpoxyModel_ clickListener(OnModelClickListener<FilterRemovalPillEpoxyModel_, FilterRemovalSuggestionPill> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public FilterRemovalPillEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public FilterRemovalPillEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public FilterRemovalPillEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public FilterRemovalPillEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public FilterRemovalPillEpoxyModel_ m5879id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public FilterRemovalPillEpoxyModel_ m5884id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public FilterRemovalPillEpoxyModel_ m5880id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public FilterRemovalPillEpoxyModel_ m5881id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public FilterRemovalPillEpoxyModel_ m5883id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public FilterRemovalPillEpoxyModel_ m5882id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public FilterRemovalPillEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public FilterRemovalPillEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public FilterRemovalPillEpoxyModel_ show() {
        super.show();
        return this;
    }

    public FilterRemovalPillEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public FilterRemovalPillEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0857R.layout.view_holder_filter_removal_pill_carousel;
    }

    public FilterRemovalPillEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.item = null;
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
        if (!(o instanceof FilterRemovalPillEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        FilterRemovalPillEpoxyModel_ that = (FilterRemovalPillEpoxyModel_) o;
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
        if (this.item != null) {
            if (!this.item.equals(that.item)) {
                return false;
            }
        } else if (that.item != null) {
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
        int i4 = 1;
        int i5 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i6 = (hashCode + i) * 31;
        if (this.item != null) {
            i2 = this.item.hashCode();
        } else {
            i2 = 0;
        }
        int i7 = (i6 + i2) * 31;
        if (this.clickListener == null) {
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
        return "FilterRemovalPillEpoxyModel_{item=" + this.item + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
