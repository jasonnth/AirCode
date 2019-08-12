package com.airbnb.android.core.viewcomponents.models;

import android.view.View.OnClickListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.epoxy.GeneratedModel;
import com.airbnb.epoxy.OnModelBoundListener;
import com.airbnb.epoxy.OnModelClickListener;
import com.airbnb.epoxy.OnModelUnboundListener;
import com.airbnb.epoxy.WrappedEpoxyModelClickListener;
import com.airbnb.p027n2.components.SearchParamsRow;
import com.airbnb.p027n2.epoxy.NumCarouselItemsShown;
import com.airbnb.p027n2.epoxy.NumItemsInGridRow;

public class SearchParamsRowEpoxyModel_ extends SearchParamsRowEpoxyModel implements GeneratedModel<SearchParamsRow> {
    private OnModelBoundListener<SearchParamsRowEpoxyModel_, SearchParamsRow> onModelBoundListener_epoxyGeneratedModel;
    private OnModelUnboundListener<SearchParamsRowEpoxyModel_, SearchParamsRow> onModelUnboundListener_epoxyGeneratedModel;

    public void handlePreBind(EpoxyViewHolder holder, SearchParamsRow object, int position) {
        if (this.clickListener instanceof WrappedEpoxyModelClickListener) {
            ((WrappedEpoxyModelClickListener) this.clickListener).bind(holder, object);
        }
    }

    public void handlePostBind(SearchParamsRow object, int position) {
        if (this.onModelBoundListener_epoxyGeneratedModel != null) {
            this.onModelBoundListener_epoxyGeneratedModel.onModelBound(this, object, position);
        }
    }

    public SearchParamsRowEpoxyModel_ onBind(OnModelBoundListener<SearchParamsRowEpoxyModel_, SearchParamsRow> listener) {
        onMutation();
        this.onModelBoundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public void unbind(SearchParamsRow object) {
        super.unbind(object);
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            this.onModelUnboundListener_epoxyGeneratedModel.onModelUnbound(this, object);
        }
    }

    public SearchParamsRowEpoxyModel_ onUnbind(OnModelUnboundListener<SearchParamsRowEpoxyModel_, SearchParamsRow> listener) {
        onMutation();
        this.onModelUnboundListener_epoxyGeneratedModel = listener;
        return this;
    }

    public SearchParamsRowEpoxyModel_ location(String location) {
        onMutation();
        this.location = location;
        return this;
    }

    public String location() {
        return this.location;
    }

    public SearchParamsRowEpoxyModel_ details(String details) {
        onMutation();
        this.details = details;
        return this;
    }

    public String details() {
        return this.details;
    }

    public SearchParamsRowEpoxyModel_ clickListener(OnModelClickListener<SearchParamsRowEpoxyModel_, SearchParamsRow> clickListener) {
        onMutation();
        if (clickListener == null) {
            this.clickListener = null;
        } else {
            this.clickListener = new WrappedEpoxyModelClickListener(this, clickListener);
        }
        return this;
    }

    public SearchParamsRowEpoxyModel_ clickListener(OnClickListener clickListener) {
        onMutation();
        this.clickListener = clickListener;
        return this;
    }

    public OnClickListener clickListener() {
        return this.clickListener;
    }

    public SearchParamsRowEpoxyModel_ numCarouselItemsShown(NumCarouselItemsShown numCarouselItemsShown) {
        onMutation();
        this.numCarouselItemsShown = numCarouselItemsShown;
        super.numCarouselItemsShown(numCarouselItemsShown);
        return this;
    }

    public SearchParamsRowEpoxyModel_ showDivider(boolean showDivider) {
        super.showDivider(showDivider);
        return this;
    }

    public SearchParamsRowEpoxyModel_ numItemsInGridRow(NumItemsInGridRow numItemsInGridRow) {
        super.numItemsInGridRow(numItemsInGridRow);
        return this;
    }

    /* renamed from: id */
    public SearchParamsRowEpoxyModel_ m5506id(long id) {
        super.mo11716id(id);
        return this;
    }

    /* renamed from: id */
    public SearchParamsRowEpoxyModel_ m5511id(Number... ids) {
        super.mo11721id(ids);
        return this;
    }

    /* renamed from: id */
    public SearchParamsRowEpoxyModel_ m5507id(long id1, long id2) {
        super.mo11717id(id1, id2);
        return this;
    }

    /* renamed from: id */
    public SearchParamsRowEpoxyModel_ m5508id(CharSequence key) {
        super.mo11718id(key);
        return this;
    }

    /* renamed from: id */
    public SearchParamsRowEpoxyModel_ m5510id(CharSequence key, CharSequence... otherKeys) {
        super.mo11720id(key, otherKeys);
        return this;
    }

    /* renamed from: id */
    public SearchParamsRowEpoxyModel_ m5509id(CharSequence key, long id) {
        super.mo11719id(key, id);
        return this;
    }

    public SearchParamsRowEpoxyModel_ layout(int arg0) {
        super.layout(arg0);
        return this;
    }

    public SearchParamsRowEpoxyModel_ spanSizeOverride(SpanSizeOverrideCallback arg0) {
        super.spanSizeOverride(arg0);
        return this;
    }

    public SearchParamsRowEpoxyModel_ show() {
        super.show();
        return this;
    }

    public SearchParamsRowEpoxyModel_ show(boolean show) {
        super.show(show);
        return this;
    }

    public SearchParamsRowEpoxyModel_ hide() {
        super.hide();
        return this;
    }

    /* access modifiers changed from: protected */
    public int getDefaultLayout() {
        return C0716R.layout.n2_view_holder_search_params_row;
    }

    public SearchParamsRowEpoxyModel_ reset() {
        this.onModelBoundListener_epoxyGeneratedModel = null;
        this.onModelUnboundListener_epoxyGeneratedModel = null;
        this.location = null;
        this.details = null;
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
        if (!(o instanceof SearchParamsRowEpoxyModel_) || !super.equals(o)) {
            return false;
        }
        SearchParamsRowEpoxyModel_ that = (SearchParamsRowEpoxyModel_) o;
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
        if (this.location != null) {
            if (!this.location.equals(that.location)) {
                return false;
            }
        } else if (that.location != null) {
            return false;
        }
        if (this.details != null) {
            if (!this.details.equals(that.details)) {
                return false;
            }
        } else if (that.details != null) {
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
        int i5 = 1;
        int i6 = 0;
        int hashCode = ((super.hashCode() * 31) + (this.onModelBoundListener_epoxyGeneratedModel != null ? 1 : 0)) * 31;
        if (this.onModelUnboundListener_epoxyGeneratedModel != null) {
            i = 1;
        } else {
            i = 0;
        }
        int i7 = (hashCode + i) * 31;
        if (this.location != null) {
            i2 = this.location.hashCode();
        } else {
            i2 = 0;
        }
        int i8 = (i7 + i2) * 31;
        if (this.details != null) {
            i3 = this.details.hashCode();
        } else {
            i3 = 0;
        }
        int i9 = (i8 + i3) * 31;
        if (this.clickListener == null) {
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
        return "SearchParamsRowEpoxyModel_{location=" + this.location + ", details=" + this.details + ", clickListener=" + this.clickListener + ", showDivider=" + this.showDivider + ", numCarouselItemsShown=" + this.numCarouselItemsShown + "}" + super.toString();
    }
}
